package com.daoke.mobileserver.ratingRules.dao.impl;

import com.daoke.mobileserver.common.dao.BaseDao;
import com.daoke.mobileserver.ratingRules.dao.IRatingRulesDao;
import com.daoke.mobileserver.ratingRules.entity.RatingRules;
import org.springframework.stereotype.Repository;

import java.util.List;

/**等级规则Dao
 * Created by chenmaomao on 2015/4/1.
 */
@Repository
public class RatingRulesDaoImpl extends BaseDao implements IRatingRulesDao {

    public List<RatingRules> queryList() throws  Exception{
        List<RatingRules> ratingRulesList = this.selectList("ratingRules.queryList");
        return ratingRulesList;
    }

    @Override
    public RatingRules getRatingRulesByRochelle(Integer rochelle) throws Exception {
        return this.selectOne("ratingRules.getRatingRulesByRochelle",rochelle);
    }

    @Override
    public RatingRules getMinGrade() throws Exception {
        return this.selectOne("ratingRules.getMinRecord");
    }
}
