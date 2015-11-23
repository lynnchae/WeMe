package com.daoke.mobileserver.more.service.impl;

import com.daoke.mobileserver.more.dao.UserMoreDao;
import com.daoke.mobileserver.more.entity.UserMore;
import com.daoke.mobileserver.more.service.UserMoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by cailingfeng on 2015/5/29.
 */
@Service
public class UserMoreServiceImpl implements UserMoreService {

    @Autowired
    private UserMoreDao userMoreDao;

    @Override
    public List<UserMore> getMoreList() {
        return userMoreDao.getMoreList();
    }

    @Override
    public Map getAd() {
        return userMoreDao.getAd();
    }
}
