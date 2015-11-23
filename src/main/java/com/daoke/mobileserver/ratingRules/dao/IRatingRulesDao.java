package com.daoke.mobileserver.ratingRules.dao;

import com.daoke.mobileserver.ratingRules.entity.RatingRules;

import java.util.List;

/**等级规则Dao
 * Created by chenmaomao on 2015/4/1.
 */
public interface IRatingRulesDao {
    /**
     * 查询所有等级规则
     * @return
     * @throws Exception
     */
    public List<RatingRules> queryList() throws  Exception;

    /**
     * 根据总谢尔值判断所在等级
     * @param rochelle
     * @return
     * @throws Exception
     */
    public RatingRules getRatingRulesByRochelle(Integer rochelle) throws  Exception;

    public RatingRules getMinGrade() throws Exception;
}
