package com.daoke.mobileserver.user.dao.impl;

import com.daoke.mobileserver.common.dao.BaseDao;
import com.daoke.mobileserver.user.dao.IUserRochelleRuleDao;
import com.daoke.mobileserver.user.entity.UserRochelleRule;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**用户谢尔规则Dao
 * Created by chenmaomao on 2015/4/1.
 */
@Repository
class UserRochelleRuleDaoImpl extends BaseDao implements IUserRochelleRuleDao {

    public Integer queryRochelleByRuleCode(String ruleCode) throws Exception {
        return  this.selectOne("userRochelleRule.queryRochelleByRuleCode", ruleCode);
    }



    @Override
    public List<UserRochelleRule> queryAllRochelle() throws Exception {
        return this.selectList("userRochelleRule.queryAllRochelle");
    }

    @Override
    public  List<Map<String, Object>> queryRochellReceiveStatus(String accountID,int ruleType,String createDate) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("accountID",accountID);
        map.put("ruleType",ruleType);
        map.put("createDate",createDate);
        return  this.selectList("queryRochellReceiveStatus",map);
    }
}
