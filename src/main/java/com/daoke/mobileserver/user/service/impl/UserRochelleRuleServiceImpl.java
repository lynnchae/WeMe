package com.daoke.mobileserver.user.service.impl;

import com.daoke.mobileserver.user.dao.IUserRochelleRuleDao;
import com.daoke.mobileserver.user.entity.UserRochelleRule;
import com.daoke.mobileserver.user.service.IUserRochelleRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户谢尔值规则Service
 * Created by chenmaomao on 2015/4/1.
 */
@Service
public class UserRochelleRuleServiceImpl implements IUserRochelleRuleService {

    @Autowired
    private IUserRochelleRuleDao userRochelleRuleDao;

    /**
     * 规则对应的谢尔值
     */
    public static Map<String, Integer> RULE_MAP = new HashMap<String, Integer>();

    /**
     * 规则终端文件
     */
    public static Map<String, String> ruleTerminalTextMap = new HashMap<String, String>();
    /**
     * 规则消息中心文本
     */
    public static Map<String, String> ruleMessageCenterTextMap = new HashMap<String, String>();

    /**
     * 规则推送到手机端文本
     */
    public static Map<String, String> ruleMobileTextMap = new HashMap<String, String>();
    /**
     * 任务规则类型 1 每日任务  2 每周任务  3 每月任务  4 成就任务
     */
    public static Map<String, Short> ruleTypeMap = new HashMap<String, Short>();

    public Integer queryRochelleByRuleCode(String ruleCode)throws Exception{
      return   userRochelleRuleDao.queryRochelleByRuleCode(ruleCode);
    }

    @Override
    public List<Map<String, Object>> queryRochellReceiveStatus(String accountID,int ruleType,String createDate) {
         return   userRochelleRuleDao.queryRochellReceiveStatus(accountID,ruleType,createDate);
    }


    /**
     * 获取所有谢尔规则
     *
     * @return
     * @throws Exception
     */
    public void queryAllRochelleWithMap() throws Exception {
        //Map<String, Integer> rullMap = new HashMap<String, Integer>();
        List<UserRochelleRule> lis = userRochelleRuleDao.queryAllRochelle();
        for (UserRochelleRule entity : lis) {
            RULE_MAP.put(entity.getRuleCode(), entity.getRochell());
            ruleTerminalTextMap.put(entity.getRuleCode(), entity.getTerminalText());
            ruleMessageCenterTextMap.put(entity.getRuleCode(), entity.getMessageCenterText());
            ruleMobileTextMap.put(entity.getRuleCode(), entity.getMobileText());
            ruleTypeMap.put(entity.getRuleCode(),entity.getRuleType());
        }
    }

    @PostConstruct
    public void initAllRochelle() {
        try {
            this.queryAllRochelleWithMap();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
