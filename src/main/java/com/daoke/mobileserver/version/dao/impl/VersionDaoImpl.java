package com.daoke.mobileserver.version.dao.impl;

import com.daoke.mobileserver.common.dao.BaseDao;
import com.daoke.mobileserver.version.dao.IVersionDao;
import com.daoke.mobileserver.version.entity.Version;
import org.springframework.stereotype.Repository;

/**
 * Created by chenmaomao on 5/30 0030.
 */
@Repository
public class VersionDaoImpl extends BaseDao implements IVersionDao {
    @Override
    public Version queryUpToDateVersion() {
        return this.selectOne("version.queryUpToDateVersion");
    }
}
