package com.daoke.mobileserver.user.service;

import com.daoke.mobileserver.user.entity.UserDetailVo;
import com.daoke.mobileserver.user.entity.UserGrade;

import java.util.List;
import java.util.Map;

/**
 * 用户等级Service
 * Created by chenmaomao on 2015/4/1.
 */
public interface IUserGradeService {
    /**
     * 更新用户等级
     * @param accountID
     * @param rochelle
     * @throws Exception
     */
    public Object[] updateUserGrade(String accountID , String nickname, Integer rochelle,Integer recordID)throws  Exception;


    /**
     * 更新用户头像
     * @param accountID
     * @param userHeadUrl
     * @throws Exception
     */
    public int uploadHeadImage(String accountID, String userHeadUrl);



    /**
     * 查询全国share值排名
     */
    public List<UserGrade> getAllRochelleRanking(int start,int finish);

    /**
     * 查询当月share值排名
     */
    public List<UserGrade> getMonthRochelleRanking(long startTime,long endTime,int start,int finish);


    /**
     * 查询用户详情列表
     * @param list
     * @return
     */
    public List<UserDetailVo> queryUserFriendDetailList(List<String>  list);


    /**
     * 根据用户ID查询用户等级
     * @param accountID
     * @return
     * @throws Exception
     */
    public UserGrade queryByAccountID(String accountID)throws Exception;

    public int updateUserInfo(String accountID,String userArea,Integer gender,String nickName) throws Exception;

    /**
     * 预奖励
     * @param accountID
     * @param imei
     * @param ruleCode
     * @return
     */
    public Object[] toReward(String accountID, String imei, String ruleCode);

    /**
     * 修改用户信息
     */
    public int  updateUserInfo(Map<String,String> map);


}
