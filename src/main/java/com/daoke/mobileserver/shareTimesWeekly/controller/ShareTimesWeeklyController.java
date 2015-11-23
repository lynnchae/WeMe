package com.daoke.mobileserver.shareTimesWeekly.controller;

import com.daoke.mobileserver.common.model.CommonJsonResult;
import com.daoke.mobileserver.shareTimesWeekly.entity.ShareTimesWeekly;
import com.daoke.mobileserver.shareTimesWeekly.service.IShareTimesWeeklyService;
import com.daoke.mobileserver.user.service.IUserGradeService;
import com.daoke.mobileserver.user.service.IUserRochelleDetailService;
import com.daoke.mobileserver.user.service.IUserRochelleRuleService;
import com.daoke.mobileserver.util.ConstantsUtil;
import com.daoke.mobileserver.util.JsonMapperUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Timestamp;


/**
 * 每周分享次数Controller
 * Created by chenmaomao on 2015/4/8.
 */
@Controller
public class ShareTimesWeeklyController {

    private final Logger logger = Logger.getLogger(ShareTimesWeeklyController.class);
    @Autowired
    private IShareTimesWeeklyService IShareTimesWeeklyService;

    @Autowired
    private IUserRochelleRuleService userRochelleRuleService;
    @Autowired
    private IUserRochelleDetailService userRochelleDetailService;
    @Autowired
    private IUserGradeService userGradeService;

    /**
     * 记录每周分享次数,满3次奖励谢尔值
     * @param accountID 用户ID
     * @return
     */
    @RequestMapping("/recordShareRochelle")
    @ResponseBody
    public CommonJsonResult recordShareRochelle(@RequestParam String accountID){

        CommonJsonResult jsonResult = new CommonJsonResult();

        try {

            ShareTimesWeekly shareTimesWeekly = IShareTimesWeeklyService.queryByAccountID(accountID);
            if (shareTimesWeekly!=null){
                Integer shareTimes = shareTimesWeekly.getShareTimesWeekly();
                if(shareTimes==null) {
                    shareTimes = 0;
                }
                boolean isThisWeek = IShareTimesWeeklyService.judgeThisWeek(shareTimesWeekly.getFirstShareTimeWeekly());
                if(isThisWeek){
                    shareTimes = shareTimes+1;
                    if(shareTimes==3){
                        //添加谢尔值
                        String ruleCode = "share3Weekly";
                            Integer rochelle = userRochelleRuleService.queryRochelleByRuleCode(ruleCode);
                            userGradeService.updateUserGrade(accountID,"", rochelle,0);
                            userRochelleDetailService.insertUserRochelleDetail(accountID, "0", rochelle, ruleCode);
                    }
                }else{
                    shareTimes=1;
                    shareTimesWeekly.setFirstShareTimeWeekly(new Timestamp(System.currentTimeMillis()));
                }
                shareTimesWeekly.setShareTimesWeekly(shareTimes);
                IShareTimesWeeklyService.update(shareTimesWeekly);
            }else{
                    shareTimesWeekly = new ShareTimesWeekly();
                    shareTimesWeekly.setAccountID(accountID);
                    shareTimesWeekly.setFirstShareTimeWeekly(new Timestamp(System.currentTimeMillis()));
                    shareTimesWeekly.setShareTimesWeekly(1);
                    IShareTimesWeeklyService.insert(shareTimesWeekly);
                }
                jsonResult.setERRORCODE(ConstantsUtil.ERRORCODE_OK);
        }catch (Exception e){
            jsonResult.setInfoMap(ConstantsUtil.ERRORCODE_SERVICE_ERROR,
                    ConstantsUtil.RESULT_SERVICE_ERROR);
            logger.error(JsonMapperUtil.mapperJson2String(ConstantsUtil.ERRORCODE_SERVICE_ERROR,e.toString()));
        }
        return jsonResult;
    }
}
