package com.daoke.mobileserver.more.controller;

import com.daoke.mobileserver.common.model.CommonJsonResult;
import com.daoke.mobileserver.more.service.UserMoreService;
import com.daoke.mobileserver.more.entity.UserMore;
import com.daoke.mobileserver.util.ConstantsUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by cailingfeng on 2015/5/29.
 */
@Controller
public class UserMoreController {

    private Logger logger = Logger.getLogger(UserMoreController.class);

    @Autowired
    private UserMoreService userMoreService;

    /**
     * weme更多页面的内容
     * @return
     */
    @RequestMapping("/getMoreList")
    @ResponseBody
    public CommonJsonResult getMoreList(String accountID , String longitude, String latitude){
        //三个参数预留备用
        CommonJsonResult jsonResult = new CommonJsonResult();
        Map<String,Object> resultMap = new HashMap<String, Object>();
        try {
            Map adMap  = userMoreService.getAd();
            List<UserMore> moreList = userMoreService.getMoreList();
            resultMap.put("ad",adMap);
            resultMap.put("appList",moreList);
            //ios 上架，暫時返回 -1，上架成功後，修改會errorcode_ok
//            jsonResult.setERRORCODE(ConstantsUtil.ERRORCODE_OK);
//            jsonResult.setRESULT(resultMap);
            jsonResult.setERRORCODE("-1");
            jsonResult.setRESULT(new HashMap());
        } catch (Exception e) {
            logger.error("获取'更多'信息失败",e);
            jsonResult.setERRORCODE(ConstantsUtil.ERRORCODE_SERVICE_ERROR);
            jsonResult.setRESULT("获取'更多'信息失败");
            e.printStackTrace();
        }
        return jsonResult;
    }
}
