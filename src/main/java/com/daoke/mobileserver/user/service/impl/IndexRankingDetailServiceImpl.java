package com.daoke.mobileserver.user.service.impl;

import com.daoke.mobileserver.user.dao.IIndexRankingDetailDao;
import com.daoke.mobileserver.user.dao.IUserFriendDao;
import com.daoke.mobileserver.user.entity.IndexRankingDetail;
import com.daoke.mobileserver.user.entity.UserFriend;
import com.daoke.mobileserver.user.service.IIndexRankingDetailService;
import com.daoke.mobileserver.util.CommonUtils;
import com.daoke.mobileserver.util.RankingStarsTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by huanghongyang on 2015/4/20.
 */
@Service
public class IndexRankingDetailServiceImpl implements IIndexRankingDetailService {

    @Autowired
    private IIndexRankingDetailDao indexRankingDetailDao;
    @Autowired
    private IUserFriendDao userFriendDao;

    private final static String IS_FRIEND = "1";
    private final static String NOT_FRIEND = "0";
    @Override
    public List<IndexRankingDetail> getMileageCostTimeList(String accountID,Integer start, Integer end) {
        List<IndexRankingDetail> indexRankingDetails = indexRankingDetailDao.getMileageCostTimeList(start,end);
        List<UserFriend> userFriends = userFriendDao.getAllFriends(accountID);
        this.setFriendTag(indexRankingDetails,userFriends);
        RankingStarsTool.setMileageCostTimeStar(indexRankingDetails);
        return indexRankingDetails;
    }

    @Override
    public List<IndexRankingDetail> getMileageSumList(String accountID, Integer start, Integer end) {
        List<IndexRankingDetail> indexRankingDetails = indexRankingDetailDao.getMileageSumList(start, end);
        List<UserFriend> userFriends = userFriendDao.getAllFriends(accountID);
        this.setFriendTag(indexRankingDetails,userFriends);
        RankingStarsTool.setMileageSumStar(indexRankingDetails);
        return indexRankingDetails;
    }

    @Override
    public List<IndexRankingDetail> getMePointList(String accountID,Integer start, Integer end) {
        List<IndexRankingDetail> indexRankingDetails = indexRankingDetailDao.getMePointList(start, end);
        List<UserFriend> userFriends = userFriendDao.getAllFriends(accountID);
        this.setFriendTag(indexRankingDetails,userFriends);
        RankingStarsTool.setMePointStar(indexRankingDetails);
        return indexRankingDetails;
    }

    @Override
    public List<IndexRankingDetail> getDriverDaysList(String accountID,Integer start, Integer end) {
        List<IndexRankingDetail> indexRankingDetails = indexRankingDetailDao.getDriverDaysList(start, end);
        List<UserFriend> userFriends = userFriendDao.getAllFriends(accountID);
        this.setFriendTag(indexRankingDetails,userFriends);
        RankingStarsTool.setDriverDaysStar(indexRankingDetails);
        return indexRankingDetails;
    }

    @Override
    public List<IndexRankingDetail> getTweetCountList(String accountID,Integer start, Integer end) {
        List<IndexRankingDetail> indexRankingDetails = indexRankingDetailDao.getTweetCountList(start, end);
        List<UserFriend> userFriends = userFriendDao.getAllFriends(accountID);
        this.setFriendTag(indexRankingDetails,userFriends);
        RankingStarsTool.setTweetCountStar(indexRankingDetails);
        return indexRankingDetails;
    }

    @Override
    public List<IndexRankingDetail> getDriverGradeList(String accountID,Integer start, Integer end) {
        List<IndexRankingDetail> indexRankingDetails = indexRankingDetailDao.getDriverGradeList(start, end);
        List<UserFriend> userFriends = userFriendDao.getAllFriends(accountID);
        this.setFriendTag(indexRankingDetails,userFriends);
        RankingStarsTool.setDriverGradeStar(indexRankingDetails);
        return indexRankingDetails;
    }

    private void setFriendTag(List<IndexRankingDetail> indexRankingDetails,List<UserFriend> userFriends){
        if(CommonUtils.notEmpty(indexRankingDetails)&&CommonUtils.notEmpty(userFriends)){
            for(IndexRankingDetail indexRankingDetail:indexRankingDetails){
                for(UserFriend userFriend:userFriends){
                    if(indexRankingDetail.getAccountID().equals(userFriend.getFriendAccountID())){
                        indexRankingDetail.setFriendTag("1");
                    }
                }
            }
        }
    }
}
