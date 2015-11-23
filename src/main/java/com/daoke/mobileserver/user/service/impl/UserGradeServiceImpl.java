package com.daoke.mobileserver.user.service.impl;

import com.daoke.mobileserver.ratingRules.dao.IRatingRulesDao;
import com.daoke.mobileserver.ratingRules.entity.RatingRules;
import com.daoke.mobileserver.user.dao.IFriendSettingDao;
import com.daoke.mobileserver.user.dao.IUserGradeDao;
import com.daoke.mobileserver.user.dao.IUserRochelleDetailDao;
import com.daoke.mobileserver.user.dao.IUserRochelleRuleDao;
import com.daoke.mobileserver.user.entity.UserDetailVo;
import com.daoke.mobileserver.user.entity.UserGrade;
import com.daoke.mobileserver.user.entity.UserRochelleDetail;
import com.daoke.mobileserver.user.service.IUserGradeService;
import com.daoke.mobileserver.user.service.UserService;
import com.daoke.mobileserver.util.*;
import me.daoke.common.mq.client.ReceiveMessage;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 用户等级Service
 * Created by chenmaomao on 2015/4/1.
 */
@Service
public class UserGradeServiceImpl implements IUserGradeService {


    private org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(UserGradeServiceImpl.class);

    @Autowired
    private IUserGradeDao userGradeDao;
    @Autowired
    private IRatingRulesDao ratingRulesDao;

    @Autowired
    private IUserRochelleRuleDao userRochelleRuleDao;



    @Autowired
    private IFriendSettingDao friendSettingDao;


    @Autowired
    private IUserRochelleDetailDao userRochelleDetailDao;

    @Transactional
    @Override
    public Object[] updateUserGrade(String accountID,String nickname, Integer rochelle, Integer recordID) throws Exception {
        UserGrade userGrade = userGradeDao.queryByAccountID(accountID);
        if (userGrade == null) {
            userGrade = new UserGrade();
            userGrade.setAccountID(accountID);
            userGrade.setGrade(0);
            userGrade.setRochelle(rochelle);
            userGrade.setMonthRochelle(rochelle);
            userGrade.setNickName(nickname);
            userGrade.setIsValid(1);
            userGrade.setRewardRochelleMonth(AbDateUtil.getCurrentMonth());
            userGradeDao.insert(userGrade);
            return null;
        } else {
            if (rochelle != null && rochelle > 0) {
                Integer totalRochelle = userGrade.getRochelle();
                if (totalRochelle == null) {
                    totalRochelle = 0;
                }
                totalRochelle = totalRochelle + rochelle;
                userGrade.setRochelle(totalRochelle);

                //谢尔值所属月份
                Integer rewardRochelleMonth =  userGrade.getRewardRochelleMonth();
                // 获取月谢尔值
                Integer monthRochelle = userGrade.getMonthRochelle();
                if (monthRochelle == null) {
                    monthRochelle = 0;
                }
                //如果谢尔值所属月份与本月相等，则累加
                if(AbDateUtil.getCurrentMonth().intValue() == rewardRochelleMonth.intValue()){
                    monthRochelle += rochelle;
                    userGrade.setMonthRochelle(monthRochelle);
                }else{
                    //否则，月谢尔值设为领取的rochelle值
                    userGrade.setMonthRochelle(rochelle);
                    userGrade.setRewardRochelleMonth(AbDateUtil.getCurrentMonth());
                }
                if(StringUtils.isNotBlank(nickname) ){
                    userGrade.setNickName(nickname);
                }
                RatingRules ratingRules = getGrade(totalRochelle);

                Integer grade = ratingRules.getRecordID();
                Integer gradeRochelle = 0;
                String ruleCode = "";
                boolean smallGrade =false;
                //如果用户 当前等级大于 总谢尔值等级  需对达到等级进行谢尔值预奖励
                if (userGrade.getGrade() < grade) {
                    smallGrade = true;
                    userGrade.setGrade(grade);
                    switch (grade) {
                        case 10:
                            ruleCode = ConstantsUtil.Grade.GRADE_10;
                            break;
                        case 20:
                            ruleCode = ConstantsUtil.Grade.GRADE_20;
                            break;
                        case 30:
                            ruleCode = ConstantsUtil.Grade.GRADE_30;
                            break;
                        case 40:
                            ruleCode = ConstantsUtil.Grade.GRADE_40;
                            break;
                        case 50:
                            ruleCode = ConstantsUtil.Grade.GRADE_50;
                            break;
                        case 60:
                            ruleCode = ConstantsUtil.Grade.GRADE_60;
                            break;
                    }
                    //根据规则 查询 谢尔值  并做预奖励
                    if (StringUtils.isNotBlank(ruleCode)) {
                        UserRochelleDetail userRochelleDetail = new UserRochelleDetail();
                        userRochelleDetail.setAccountID(accountID);
                        userRochelleDetail.setRuleCode(ruleCode);
                        List<UserRochelleDetail> list = userRochelleDetailDao.queryByAccountIDAndRuleCode(userRochelleDetail);
                        if (list == null || list.size() == 0) {
                            gradeRochelle = userRochelleRuleDao.queryRochelleByRuleCode(ruleCode);
                            userRochelleDetail.setIsValid(1);
                            userRochelleDetail.setReceiveStatus(0);
                            userRochelleDetail.setRochelle(gradeRochelle);
                            userRochelleDetail.setRuleCode(ruleCode);
                            userRochelleDetailDao.insertUserRochelleDetail(userRochelleDetail);
                            recordID = userRochelleDetail.getRecordID();
                        }
                    }
                }
                //更新领取状态
                userRochelleDetailDao.updateUserRochelleReceiveStatus(1,accountID,recordID);
                userGradeDao.update(userGrade);
                //针对 小等级 如 非10 /20 /30 /40 /50 /60
                if (smallGrade) {
                    return new Object[]{ruleCode, gradeRochelle, recordID,ratingRules.getTerminalText(),ratingRules.getMobileText(),ratingRules.getMessageCenterText()};
                } else {
                    return null;
                }

            }
            return null;
        }
    }



    @Override
    public Object[] toReward(String accountID, String imei, String ruleCode) {
        try {
            if (UserRochelleRuleServiceImpl.RULE_MAP.get(ruleCode) != null) {
                int rochelle = UserRochelleRuleServiceImpl.RULE_MAP.get(ruleCode);
                UserRochelleDetail userRochelleDetail = new UserRochelleDetail();
                userRochelleDetail.setAccountID(accountID);
                userRochelleDetail.setImei(imei);
                userRochelleDetail.setIsValid(1);
                userRochelleDetail.setRochelle(rochelle);

                userRochelleDetail.setRuleCode(ruleCode);

                userRochelleDetailDao.insertUserRochelleDetail(userRochelleDetail);
                return new Object[]{ruleCode,rochelle,userRochelleDetail.getRecordID(),UserRochelleRuleServiceImpl.ruleTerminalTextMap.get(ruleCode),
                        UserRochelleRuleServiceImpl.ruleMobileTextMap.get(ruleCode),UserRochelleRuleServiceImpl.ruleMessageCenterTextMap.get(ruleCode)};
            } else {
                log.warn("奖励失败，ruleCode不存在:" + ruleCode);
                return null;
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public int uploadHeadImage(String accountID, String userHeadUrl) {
        return userGradeDao.uploadHeadImage(accountID, userHeadUrl);
    }

    @Override
    public int updateUserInfo(String accountID,String userArea,Integer gender,String nickName) throws Exception {
        UserGrade userGrade = userGradeDao.queryByAccountID(accountID);
        if (userGrade == null || StringUtils.isBlank(userGrade.getAccountID())) {
            userGrade = new UserGrade();
            userGrade.setUserAreaCode(userArea);
            userGrade.setGender(gender == null ? 0 : gender.shortValue());
            userGrade.setAccountID(accountID);
            userGrade.setGrade(0);
            userGrade.setRochelle(0);
            userGrade.setMonthRochelle(0);
            userGrade.setNickName(nickName);
            userGrade.setRewardRochelleMonth(AbDateUtil.getCurrentMonth());
            userGrade.setIsValid(1);
            return userGradeDao.insert(userGrade);
        }else{
            return userGradeDao.updateUserInfo(accountID, userArea, gender, nickName);
        }


    }

    /**
     * 获取等级
     *
     * @param totalRochelle
     * @return
     * @throws Exception
     */
    public RatingRules getGrade(Integer totalRochelle) throws Exception {
       return ratingRulesDao.getRatingRulesByRochelle(totalRochelle);
    }

    public RatingRules getMinGrade()  throws Exception {
        return ratingRulesDao.getMinGrade();
    }

    @Override
    public List<UserGrade> getAllRochelleRanking(int start, int finish) {
        Map<String, Object> params = new HashMap<String, Object>(2);
        params.put("start", start);
        params.put("finish", finish);
        return userGradeDao.getAllRochelleRanking(params);
    }

    @Override
    public List<UserGrade> getMonthRochelleRanking(long startTime, long endTime, int start, int finish) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("startTime", startTime);
        params.put("endTime", endTime);
        params.put("start", start);
        params.put("finish", finish);
        return userGradeDao.getMonthRochelleRanking(params);
    }

    @Override
    public List<UserDetailVo> queryUserFriendDetailList(List<String> list) {
        Map<String, Object> map = new HashMap<String, Object>(1);
        map.put("list", list);
        List<UserDetailVo> userGradeList = userGradeDao.queryUserFriendDetailList(map);
        map.put("list", userGradeList);
        List<UserDetailVo> userDetailVoList = friendSettingDao.queryUserSettingList(map);
        for (UserDetailVo userDetailGrade : userGradeList) {
            for (UserDetailVo userDetailFriendSetting : userDetailVoList) {
                if (userDetailGrade.getAccountID().equals(userDetailFriendSetting.getAccountID())) {
                    userDetailGrade.setIsVerifyOpinion(userDetailFriendSetting.getIsVerifyOpinion());
                    userDetailGrade.setIsAllowedOpinion(userDetailFriendSetting.getIsAllowedOpinion());
                }
            }
            if (userDetailGrade.getIsAllowedOpinion() == null && userDetailGrade.getIsVerifyOpinion() == null) {
                userDetailGrade.setIsVerifyOpinion(0);
                userDetailGrade.setIsAllowedOpinion(1);
            }
        }
        return userGradeList;
    }


    public UserGrade queryByAccountID(String accountID) throws Exception {
        return userGradeDao.queryByAccountID(accountID);
    }



    @Autowired
    private ReceiveMessage receiveRochelleMessage;


    @Autowired
    private UserService userService;

    @PostConstruct
    public void initMq() {
        try {
            PropertiesUtil propertiesUtil = PropertiesUtil.getInstance();
            final String sendPersonalWeibo = propertiesUtil.getProperty("sendPersonalWeibo");
            final String callBackReceiveRochelle = propertiesUtil.getProperty("callBackReceiveRochelle");

            final String  testAccountID =     propertiesUtil.getProperty("testAccountIDs");
            //创建新线程运行接收消息
            final ExecutorService threadPool_thread = Executors.newFixedThreadPool(6);
            for (int i = 0; i < 6; i++) {
                threadPool_thread.execute(
                        new Runnable() {
                            @Override
                            public void run() {
                                while (true) {
                                    try {
                                        //获取消息队列信息（订单ID）
                                        final String message = receiveRochelleMessage.receive();
                                        try {
                                            RuleRewardMq ruleRewardMq = (RuleRewardMq) me.daoke.common.mq.util.JsonMapper.fromJson(message, RuleRewardMq.class);

                                            //测试使用
                                            boolean isTestAccountID = false;
                                            if (org.springframework.util.StringUtils.hasText(testAccountID)) {
                                                StringTokenizer tokens = new StringTokenizer(testAccountID, ",");
                                                while (tokens.hasMoreTokens()){
                                                    String token = tokens.nextToken();
                                                    if (ruleRewardMq.getAccountID().equals(token)) {
                                                        isTestAccountID = true;
                                                        break;
                                                    }
                                                }
                                                if(!isTestAccountID ){
                                                    continue;

                                                }
                                            }

                                            if(isTestAccountID){

                                            log.info("threadName: "+ Thread.currentThread().getName() + "  ruleRewardMq:"+ ruleRewardMq.toString());
                                            // System.out.println("ruleRewardMq: " + ruleRewardMq);
                                            //判断是否已奖励
                                            UserRochelleDetail userRochelleDetail = new UserRochelleDetail();
                                            userRochelleDetail.setAccountID(ruleRewardMq.getAccountID());
                                            userRochelleDetail.setRuleCode(ruleRewardMq.getRuleCode());
                                            userRochelleDetail.setCreateDate(new Date());
                                            List<UserRochelleDetail>  list =  userRochelleDetailDao.queryByAccountIDAndRuleCode(userRochelleDetail);
                                            if(list == null || list.size() <1) {
                                                //预奖励
                                                Object[] obj = toReward(ruleRewardMq.getAccountID(), ruleRewardMq.getIMEI(), ruleRewardMq.getRuleCode());
                                                log.info("预奖励:" + obj.toString());
                                                UserGrade userGrade = queryByAccountID(ruleRewardMq.getAccountID());
                                                String terminalText = "";
                                                if (userGrade != null && StringUtils.isNotBlank(userGrade.getNickName()) && obj != null && obj[3] != null) {
                                                    terminalText = obj[3].toString().replace("昵称", userGrade.getNickName());
                                                    //terminalText = "任务奖励," + String.format(obj[3].toString(), userGrade.getNickName(), obj[1]);
                                                } else {
                                                    // 取IMEI后四位
                                                    if (StringUtils.isNotBlank(ruleRewardMq.getIMEI())) {
                                                        terminalText = obj[3].toString().replace("昵称", "道客" + ruleRewardMq.getIMEI().substring(ruleRewardMq.getIMEI().length() - 4, ruleRewardMq.getIMEI().length()));
                                                    } else {
                                                        terminalText = obj[3].toString().replace("昵称", "");
                                                    }
                                                }
                                                //记录到消息表中
                                                Map<String, Object> param = new HashMap<String, Object>();
                                                //推送到手机
                                                param.put("rewardID", (Integer) obj[2]);
                                                param.put("ruleType", UserRochelleRuleServiceImpl.ruleTypeMap.get(ruleRewardMq.getRuleCode()));
                                                userService.pushMessage("任务奖励", ruleRewardMq.getAccountID(), obj[4].toString(), "rochelleReward", param);

                                                //推送消息
                                                SendMessageUtil.sendToTerminal(sendPersonalWeibo, callBackReceiveRochelle, (Integer) obj[2], ruleRewardMq.getAccountID(), terminalText);
                                            }
                                            }
                                        } catch (Exception e) {
                                            log.error("接收消息 处理异常", e);
                                            e.printStackTrace();
                                        }
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                        log.error("ExceptionMessage: {" + e.getMessage() + "}");
                                        log.error("StackTrace: ", e);
                                    }
                                }
                            }
                        });
            }
            threadPool_thread.shutdown();
        } catch (Exception e) {
            e.printStackTrace();
        }
//        try {
//
//            PropertiesUtil propertiesUtil = PropertiesUtil.getInstance();
//
//            final String sendPersonalWeibo = propertiesUtil.getProperty("sendPersonalWeibo");
//            final String callBackReceiveRochelle = propertiesUtil.getProperty("callBackReceiveRochelle");
//
//            //创建新线程运行接收消息
//            ExecutorService threadPool = Executors.newSingleThreadExecutor();
//            final ExecutorService threadPool_thread = Executors.newFixedThreadPool(4);
//            threadPool.execute(
//                    new Runnable() {
//                        @Override
//                        public void run() {
//                            while (true) {
//                                try {
//                                    //获取消息队列信息（订单ID）
//                                    final String message = receiveRochelleMessage.receive();
//                                    threadPool_thread.execute(new Runnable() {
//                                        @Override
//                                        public void run() {
//                                            try {
//                                                RuleRewardMq ruleRewardMq = (RuleRewardMq) me.daoke.common.mq.util.JsonMapper.fromJson(message, RuleRewardMq.class);
//                                                System.out.println("ruleRewardMq: "+ ruleRewardMq);
//                                                //预奖励
//                                                Object[] obj = toReward(ruleRewardMq.getAccountID(), ruleRewardMq.getIMEI(), ruleRewardMq.getRuleCode());
//                                                UserGrade userGrade =  userGradeDao.queryByAccountID(ruleRewardMq.getAccountID());
//                                                String  terminalText ="";
//                                                if(userGrade != null && StringUtils.isNotBlank(userGrade.getNickName()) && obj != null && obj[3] != null){
//                                                    terminalText =  String.format(obj[3].toString(),userGrade.getNickName(),obj[1]);
//                                                }
//                                                //推送消息
//                                                SendMessageUtil.sendToTerminal(sendPersonalWeibo, callBackReceiveRochelle, (Integer) obj[2], ruleRewardMq.getAccountID(),terminalText);
//                                            } catch (Exception e) {
//                                                log.error("接收消息 处理异常", e);
//                                                e.printStackTrace();
//                                            }
//                                        }
//                                    });
//                                } catch (Exception e) {
//                                    log.error("error", e);
//                                    e.printStackTrace();
//                                    log.error("ExceptionMessage: {" + e.getMessage() + "}");
//                                    log.error("StackTrace: ", e);
//                                }
//                            }
//                        }
//                    });
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

    }
    @Override
    public int  updateUserInfo(Map<String,String> map){
        return  userGradeDao.updateUserInfo(map);
    }

}
