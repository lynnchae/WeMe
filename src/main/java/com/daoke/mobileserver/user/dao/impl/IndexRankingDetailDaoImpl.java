package com.daoke.mobileserver.user.dao.impl;

import com.daoke.mobileserver.common.dao.BaseDao;
import com.daoke.mobileserver.user.dao.IIndexRankingDetailDao;
import com.daoke.mobileserver.user.entity.IndexRankingDetail;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by huanghongyang on 2015/4/20.
 */
@Repository
public class IndexRankingDetailDaoImpl extends BaseDao implements IIndexRankingDetailDao {

    @Override
    public List<IndexRankingDetail> getMileageCostTimeList(Integer start, Integer end) {
        Map<String,Object> param = new HashMap<String, Object>(2);
        param.put("start",start);
        param.put("end",end);
        return this.selectList("getMileageCostTimeDetail",param);
    }

    @Override
    public List<IndexRankingDetail> getMileageSumList(Integer start, Integer end) {
        Map<String,Object> param = new HashMap<String, Object>(2);
        param.put("start",start);
        param.put("end",end);
        return this.selectList("getMileageSumList",param);
    }

    @Override
    public List<IndexRankingDetail> getMePointList(Integer start, Integer end) {
        Map<String,Object> param = new HashMap<String, Object>(2);
        param.put("start",start);
        param.put("end",end);
        return this.selectList("getMePointList",param);
    }

    @Override
    public List<IndexRankingDetail> getDriverDaysList(Integer start, Integer end) {
        Map<String,Object> param = new HashMap<String, Object>(2);
        param.put("start",start);
        param.put("end",end);
        return this.selectList("getDriverDaysList",param);
    }

    @Override
    public List<IndexRankingDetail> getTweetCountList(Integer start, Integer end) {
        Map<String,Object> param = new HashMap<String, Object>(2);
        param.put("start",start);
        param.put("end",end);
        return this.selectList("getTweetCountList",param);
    }

    @Override
    public List<IndexRankingDetail> getDriverGradeList(Integer start, Integer end) {
        Map<String,Object> param = new HashMap<String, Object>(2);
        param.put("start",start);
        param.put("end",end);
        return this.selectList("getDriverGradeList",param);
    }
}
