package com.daoke.mobileserver.user.dao.impl;

import com.daoke.mobileserver.common.dao.BaseDao;
import com.daoke.mobileserver.user.dao.IReportInfoDao;
import com.daoke.mobileserver.user.entity.ReportInfo;
import org.springframework.stereotype.Repository;

/**
 * Created by chenmaomao on 5/21 0021.
 */
@Repository
public class ReportInfoDaoImpl extends BaseDao implements IReportInfoDao {
    @Override
    public void insertReportInfo(ReportInfo reportInfo) {
        this.insert("reportInfo.insertReportInfo",reportInfo);
    }
}
