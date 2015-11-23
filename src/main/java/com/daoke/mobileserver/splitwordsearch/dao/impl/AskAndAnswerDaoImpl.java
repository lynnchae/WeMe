package com.daoke.mobileserver.splitwordsearch.dao.impl;

import com.daoke.mobileserver.common.dao.BaseDao;
import com.daoke.mobileserver.splitwordsearch.dao.AskAndAnswerDao;
import com.daoke.mobileserver.splitwordsearch.dto.AskAndAnswerRowMapper;
import com.daoke.mobileserver.splitwordsearch.entity.AskAndAnswer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 问答模块 dao的实现层
 * @author zhuoshuang
 * @data 2015-5-13
 */
@Repository
public class AskAndAnswerDaoImpl extends BaseDao implements AskAndAnswerDao {

    @Autowired
    private JdbcTemplate wemeJdbcTemplate;


    @Override
    public int queryCountNum(String sql) {
        int num = wemeJdbcTemplate.queryForInt(sql);
        return num;
    }

    @Override
    public List<AskAndAnswer> getAskAndAnswerRecordList(String sql) {
        List<AskAndAnswer> askAndAnswerList = wemeJdbcTemplate.query(sql, new AskAndAnswerRowMapper());
        return askAndAnswerList;
    }

    @Override
    public int queryCountByMapper(String target, Map<String, Object> map) {
        return (Integer)this.selectOne(target,map);
    }

    @Override
    public List<AskAndAnswer> getListByMapper(String target, Map<String, Object> map) {
        return this.selectList(target,map);
    }
}
