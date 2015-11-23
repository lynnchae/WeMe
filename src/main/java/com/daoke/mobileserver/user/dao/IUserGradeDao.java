package com.daoke.mobileserver.user.dao;

import com.daoke.mobileserver.common.dao.BaseDao;
import com.daoke.mobileserver.user.entity.UserDetailVo;
import com.daoke.mobileserver.user.entity.UserGrade;

import java.util.List;
import java.util.Map;

/**用户等级Dao
 * Created by chenmaomao on 2015/4/1.
 */
public interface IUserGradeDao {
    /**
     * 根据用户ID查询用户等级
     * @param accountID
     * @return
     * @throws Exception
     */
    public UserGrade queryByAccountID(String accountID)throws Exception;

    /**
     * 添加用户等级
     * @param userGrade
     * @throws Exception
     */
    public  int insert(UserGrade userGrade)throws Exception;

    /**
     * 修改用户等级
     * @param userGrade
     * @throws Exception
     */
    public  void update(UserGrade userGrade)throws Exception;

    /**
     * 更新用户头像
     * @param accountID
     * @param userHeadUrl
     */

    public int uploadHeadImage(String accountID, String userHeadUrl);
    /**
     * 查询全国share值
     */
    public List<UserGrade> getAllRochelleRanking(Object params);

    /**
     * 查询当月share值排名
     */
    public List<UserGrade> getMonthRochelleRanking(Object params);
    public int updateUserInfo(String accountID,String userArea,Integer gender,String nickName);

    /**
     * 查询用户好友详情列表
     * @param map
     * @return
     */
    public List<UserDetailVo> queryUserFriendDetailList(Map<String,Object> map);
    public List<Map<String, Object>> getRankListInfoByShellAll(Map map) ;
    public int  updateUserInfo(Map<String,String> map);
}
