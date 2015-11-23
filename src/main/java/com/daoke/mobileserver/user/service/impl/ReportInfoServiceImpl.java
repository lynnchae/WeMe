package com.daoke.mobileserver.user.service.impl;

import com.daoke.mobileserver.user.dao.IReportInfoDao;
import com.daoke.mobileserver.user.entity.ReportInfo;
import com.daoke.mobileserver.user.service.IReportInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by chenmaomao on 5/21 0021.
 */
@Service
public class ReportInfoServiceImpl implements IReportInfoService{

    @Autowired
    private IReportInfoDao reportInfoDao;
    @Override
    public void insertReportInfo(ReportInfo reportInfo) {
        reportInfoDao.insertReportInfo(reportInfo);
    }
}
