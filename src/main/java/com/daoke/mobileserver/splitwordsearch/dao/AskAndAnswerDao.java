package com.daoke.mobileserver.splitwordsearch.dao;

import com.daoke.mobileserver.splitwordsearch.entity.AskAndAnswer;

import java.util.List;
import java.util.Map;

/**
 * 问答模块 dao的接口层
 * @author zhuoshuang
 * @data 2015-5-13
 */
public interface  AskAndAnswerDao{


    /**
     *执行指定的sql语句，查询符合条件的记录总数
     * @param sql
     * @return int
     *         符合条件德尔记录数
     */
    public int queryCountNum(String sql);


    /**
     *根据指定的sql 查询符合条件的问答集合
     * @param sql
     * @return list
     */
    public List<AskAndAnswer> getAskAndAnswerRecordList(String sql);


    /**
     * 通过映射文件中的sql 查询记录的条数
     * @param target
     *        映射文件中sql对应的ID
     * @param map
     *        map
     * @return
     */
    public int queryCountByMapper(String target,Map<String,Object> map);


    /**
     * 通过映射文件中的sql 查询记录
     * @param target
     *        映射文件中sql对应的ID
     * @param map
     *        map
     * @return
     */
    public List<AskAndAnswer> getListByMapper(String target,Map<String,Object> map);


}
