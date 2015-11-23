package com.daoke.mobileserver.version.service.impl;

import com.daoke.mobileserver.version.dao.IVersionDao;
import com.daoke.mobileserver.version.entity.Version;
import com.daoke.mobileserver.version.service.IVersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by chenmaomao on 5/30 0030.
 */
@Service
public class VersionServiceImpl implements IVersionService {
    @Autowired
    private IVersionDao versionDao;
    @Override
    public Version queryUpToDateVersion() {
        return versionDao.queryUpToDateVersion();
    }
}
