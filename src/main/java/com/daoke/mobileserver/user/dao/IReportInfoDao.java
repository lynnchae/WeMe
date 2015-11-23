package com.daoke.mobileserver.user.dao;

import com.daoke.mobileserver.user.entity.FriendSetting;
import com.daoke.mobileserver.user.entity.ReportInfo;
import com.daoke.mobileserver.user.entity.UserDetailVo;

import java.util.List;
import java.util.Map;

/**
 * Created by chenmaomao on 2015/5/9.
 */
public interface IReportInfoDao {
    /**
     * 添加举报信息
     * @param reportInfo
     */
    public void insertReportInfo(ReportInfo reportInfo);
}
