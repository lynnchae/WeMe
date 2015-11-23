package com.daoke.mobileserver.test;

import com.daoke.mobileserver.user.dao.IUserRochelleDetailDao;
import com.daoke.mobileserver.user.entity.IndexRankingDetail;
import com.daoke.mobileserver.user.entity.UserRankDetail;
import com.daoke.mobileserver.user.entity.UserRochelleDetail;
import com.daoke.mobileserver.user.service.IIndexRankingDetailService;
import com.daoke.mobileserver.user.service.IUserGradeService;
import com.daoke.mobileserver.user.service.IUserRankDetailService;
import junit.framework.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

/**
 * Created by huanghongyang on 2015/4/20.
 */
public class UserRankDetailServiceTest extends BaseTest {
    @Autowired
    private IUserRankDetailService userRankDetailService;

    @Autowired
    private IIndexRankingDetailService indexRankingDetailService;

    @Autowired
    private IUserRochelleDetailDao userRochelleDetailDao;


    @Autowired
    private IUserGradeService userGradeService;

    @Test
    public void testRocheelDetail() throws Exception {
        UserRochelleDetail userRochelleDetail = new UserRochelleDetail();
        userRochelleDetail.setAccountID("ik4uyo6lQr");
        userRochelleDetail.setRuleCode("drive30KmDay");
        userRochelleDetail.setCreateDate(new Date());
        List<UserRochelleDetail>  list =  userRochelleDetailDao.queryByAccountIDAndRuleCode(userRochelleDetail);
        System.out.println(list);
    }


    @Test
    public void testCurrentRanking(){
        String accountID = "test140";
        UserRankDetail userRankDetail = userRankDetailService.getCurrentRank(accountID);
        System.out.println(userRankDetail);
        Assert.assertNotNull(userRankDetail);
    }

    @Test
    public void testIndexRanking(){
        String accountID = "wkwel8lzcT";
        List<IndexRankingDetail> details = indexRankingDetailService.getMileageCostTimeList(accountID, 0, 100);
        System.out.println(details);
    }
    @Test
    public void testGetMileageSumList(){
        String accountID = "wkwel8lzcT";
        List<IndexRankingDetail> details = indexRankingDetailService.getMileageSumList(accountID, 0, 100);
        System.out.println(details);
    }
    @Test
     public void testGetDriverDaysList(){
        String accountID = "wkwel8lzcT";
        List<IndexRankingDetail> details = indexRankingDetailService.getDriverDaysList(accountID, 0, 100);
        System.out.println(details);
    }
    @Test
    public void testGetDriverGradeList(){
        String accountID = "wkwel8lzcT";
        List<IndexRankingDetail> details = indexRankingDetailService.getDriverGradeList(accountID, 0, 100);
        System.out.println(details);
    }
    @Test
    public void testGetMePointList(){
        String accountID = "wkwel8lzcT";
        List<IndexRankingDetail> details = indexRankingDetailService.getMePointList(accountID, 0, 100);
        System.out.println(details);
    }

    @Test
    public void testGetTweetCountList(){
        String accountID = "wkwel8lzcT";
        List<IndexRankingDetail> details = indexRankingDetailService.getTweetCountList(accountID, 0, 100);
        System.out.println(details);
    }

    @Test
    public void testUserGradeDao(){
    try {
        userGradeService.updateUserGrade("3ysmSwQQl2","",13,1000);
    }catch (Exception e){
        e.printStackTrace();
    }

    }
}
