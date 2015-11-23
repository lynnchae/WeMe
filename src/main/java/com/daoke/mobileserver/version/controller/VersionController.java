package com.daoke.mobileserver.version.controller;

import com.daoke.mobileserver.common.model.CommonJsonResult;
import com.daoke.mobileserver.util.ConstantsUtil;
import com.daoke.mobileserver.version.entity.Version;
import com.daoke.mobileserver.version.service.IVersionService;
import com.daoke.mobileserver.version.service.impl.VersionServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * Created by chenmaomao on 5/30 0030.
 */
@Controller
public class VersionController {

    private Logger logger = Logger.getLogger(VersionController.class);
    @Autowired
    private IVersionService versionService;
    /**
     * 查询最新版本
     * @return
     */
    @RequestMapping("/queryUpToDateVersion")
    @ResponseBody
    public CommonJsonResult queryUpToDateVersion() {
        CommonJsonResult result = new CommonJsonResult();
        try{
            Version version = versionService.queryUpToDateVersion();
            result.setERRORCODE(ConstantsUtil.ERRORCODE_OK);
            result.setRESULT(version);
        }catch (Exception e){
            logger.error("查询版本失败",e);
            result.setERRORCODE(ConstantsUtil.ERRORCODE_OK);
            result.setRESULT("查询版本失败");
        }
        return result;
    }
}
