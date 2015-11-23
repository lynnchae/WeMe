package com.daoke.mobileserver.more.service;

import com.daoke.mobileserver.more.entity.UserMore;

import java.util.List;
import java.util.Map;

/**
 * Created by cailingfeng on 2015/5/29.
 */
public interface UserMoreService {

    List<UserMore> getMoreList();

    Map getAd();

}
