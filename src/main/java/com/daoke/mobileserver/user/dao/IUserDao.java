package com.daoke.mobileserver.user.dao;

import java.util.Map;

/**
 * Created by wangzp on 2015/5/23.
 */
public interface IUserDao {
    /**
     * 记录验证码信息
     * @param map
     * @return
     */
    public int insertIdentifyingCode(Map<String,Object> map);
    public int findValidCode(Map<String, Object> map);
}
