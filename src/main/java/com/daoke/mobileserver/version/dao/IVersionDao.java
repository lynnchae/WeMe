package com.daoke.mobileserver.version.dao;

import com.daoke.mobileserver.version.entity.Version;

/**
 * Created by chenmaomao on 5/30 0030.
 */
public interface IVersionDao {
    /**
     * 查询最新的版本
     * @return
     */
    public Version queryUpToDateVersion();
}
