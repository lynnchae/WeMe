package com.daoke.mobileserver.user.dao.impl;

import com.daoke.mobileserver.common.dao.BaseDao;
import com.daoke.mobileserver.user.dao.IUserGradeDao;
import com.daoke.mobileserver.user.entity.UserDetailVo;
import com.daoke.mobileserver.user.entity.UserGrade;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**用户等级Dao
 * Created by chenmaomao on 2015/4/1.
 */
@Repository
public class UserGradeDaoImpl extends BaseDao implements IUserGradeDao {
    @Override
    public UserGrade queryByAccountID(String accountID) throws Exception {
        UserGrade userGrade = this.selectOne("userGrade.queryByAccountID",accountID);
        return userGrade;
    }

    @Override
    public int insert(UserGrade userGrade) throws Exception {
       return  this.insert("userGrade.insertUserGrade",userGrade);
    }
    @Override
    public void update(UserGrade userGrade) throws Exception {
        this.insert("userGrade.updateUserGrade",userGrade);
    }

    @Override
    public int uploadHeadImage(String accountID, String userHeadUrl) {
        Map map = new HashMap();
        map.put("accountID",accountID);
        map.put("userHeadUrl",userHeadUrl);
       return this.update("userGrade.uploadHeadImage",map);
    }

    public List<UserGrade> getAllRochelleRanking(Object params) {
        return this.selectList("rankingAllRochelle",params);
    }

    @Override
    public List<UserGrade> getMonthRochelleRanking(Object params) {
        return this.selectList("rankingMonthRochelle",params);
    }
    public int updateUserInfo(String accountID,String userArea,Integer gender,String nickName) {
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("accountID",accountID);
        map.put("userArea",userArea);
        map.put("gender",gender);
        map.put("nickName",nickName);
        return this.update("updateUserInfo",map);
    }

    @Override
    public List<UserDetailVo> queryUserFriendDetailList(Map<String,Object> map) {
        return this.selectList("userGrade.queryUserFriendDetailList", map);
    }

    @Override
    public List<Map<String, Object>> getRankListInfoByShellAll(Map map) {
        return this.selectList("getRankListInfoByShellAll", map);
    }
    @Override
    public int  updateUserInfo(Map<String,String> map){
        return this.update("updateUserInfo",map);
    }

}
