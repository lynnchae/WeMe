package com.daoke.mobileserver.user.service;

import com.daoke.mobileserver.user.entity.IndexRankingDetail;

import java.util.List;

/**
 * Created by huanghongyang on 2015/4/20.
 */
public interface IIndexRankingDetailService {
    /**
     * 获取达标用时指数详情
     * @param start
     * @param end
     * @return
     */
    List<IndexRankingDetail> getMileageCostTimeList(String accountID,Integer start,Integer end);
    /**
     * 获取新增里程
     * @param start
     * @param end
     * @return
     */
    List<IndexRankingDetail> getMileageSumList(String accountID,Integer start,Integer end);

    /**
     * 获取捐献密点
     * @param start
     * @param end
     * @return
     */
    List<IndexRankingDetail> getMePointList(String accountID,Integer start,Integer end);

    /**
     * 获取驾驶天数
     * @param start
     * @param end
     * @return
     */
    List<IndexRankingDetail> getDriverDaysList(String accountID,Integer start,Integer end);

    /**
     * 获取吐槽数
     * @param start
     * @param end
     * @return
     */
    List<IndexRankingDetail> getTweetCountList(String accountID,Integer start,Integer end);

    /**
     * 获取驾驶天数
     * @param start
     * @param end
     * @return
     */
    List<IndexRankingDetail> getDriverGradeList(String accountID,Integer start,Integer end);
}
