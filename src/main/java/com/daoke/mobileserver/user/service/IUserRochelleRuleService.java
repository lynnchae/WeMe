package com.daoke.mobileserver.user.service;

import com.daoke.mobileserver.user.entity.UserRochelleRule;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 用户谢尔值规则Service
 * Created by chenmaomao on 2015/4/1.
 */
public interface IUserRochelleRuleService {
    /**
     *根据规则码查询谢尔值
     * @param ruleCode
     * @return
     * @throws Exception
     */
    public Integer queryRochelleByRuleCode(String ruleCode)throws Exception;
    public List<Map<String, Object>> queryRochellReceiveStatus(String accountID,int ruleType,String createDate);
}
