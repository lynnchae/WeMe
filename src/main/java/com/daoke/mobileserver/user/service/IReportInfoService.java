package com.daoke.mobileserver.user.service;

import com.daoke.mobileserver.user.entity.ReportInfo;

/**
 * 举报service
 * Created by chenmaomao on 2015/5/9.
 */
public interface IReportInfoService {
    /**
     * 添加举报信息
     * @param reportInfo
     */
    public void insertReportInfo(ReportInfo reportInfo);
}
