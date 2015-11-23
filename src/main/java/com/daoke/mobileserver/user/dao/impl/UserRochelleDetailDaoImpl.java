package com.daoke.mobileserver.user.dao.impl;

import com.daoke.mobileserver.common.dao.BaseDao;
import com.daoke.mobileserver.common.dao.DataSourceContextHolder;
import com.daoke.mobileserver.common.dao.DataSourceType;
import com.daoke.mobileserver.user.dao.IUserRochelleDetailDao;
import com.daoke.mobileserver.user.entity.UserRochelleDetail;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**用户谢尔明细Dao
 * Created by chenmaomao on 2015/4/1.
 */
@Repository
public class UserRochelleDetailDaoImpl extends BaseDao implements IUserRochelleDetailDao {
    @Override
    public void insertUserRochelleDetail(UserRochelleDetail userRochelleDetail) throws Exception {
       this.insert("userRochelleDetail.insertUserRochelleDetail", userRochelleDetail);
    }

    @Override
    public List<UserRochelleDetail> queryByAccountIDAndRuleCode(UserRochelleDetail userRochelleDetail) throws Exception {
        return this.selectList("userRochelleDetail.queryByAccountIDAndRuleCode",userRochelleDetail);
    }
    @Override
    public Map<String,Object> getDetailUserList(String accountID,List accountIDList) {
        if(accountIDList ==null){
            accountIDList = new ArrayList();
        }
        Map<String ,Object> map = new HashMap<String, Object>();
        map.put("accountID",accountID);
        accountIDList.add(map);
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("accountID",accountID);
        param.put("list",accountIDList);
        return (Map<String,Object>)this.selectOne("userRochelleDetail.getDetailUserList",param);
    }


    @Override
    public int updateUserGradeWhitTotalChelle(Map map) {
        return this.update("updateUserGradeWhitTotalChelle",map);
    }

    @Override
    public int updateUserRochelleReceiveStatus(int receiveStatus,String accountID,Integer recordID) {
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("receiveStatus",receiveStatus);
        param.put("accountID",accountID);
        param.put("recordID",recordID);
        return this.update("updateUserRochelleReceiveStatus",param);
    }


}
