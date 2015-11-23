package com.daoke.mobileserver.user.dao;

import com.daoke.mobileserver.user.entity.IndexRankingDetail;

import java.util.List;

/**
 * Created by huanghongyang on 2015/4/20.
 */
public interface IIndexRankingDetailDao {
    /**
     * 获取达标用时指数详情
     * @param start
     * @param end
     * @return
     */
    List<IndexRankingDetail> getMileageCostTimeList(Integer start,Integer end);
    /**
     * 获取新增里程
     * @param start
     * @param end
     * @return
     */
    List<IndexRankingDetail> getMileageSumList(Integer start,Integer end);

    /**
     * 获取捐献密点
     * @param start
     * @param end
     * @return
     */
    List<IndexRankingDetail> getMePointList(Integer start,Integer end);

    /**
     * 获取驾驶天数
     * @param start
     * @param end
     * @return
     */
    List<IndexRankingDetail> getDriverDaysList(Integer start,Integer end);

    /**
     * 获取吐槽数
     * @param start
     * @param end
     * @return
     */
    List<IndexRankingDetail> getTweetCountList(Integer start,Integer end);

    /**
     * 获取驾驶天数
     * @param start
     * @param end
     * @return
     */
    List<IndexRankingDetail> getDriverGradeList(Integer start,Integer end);
}
