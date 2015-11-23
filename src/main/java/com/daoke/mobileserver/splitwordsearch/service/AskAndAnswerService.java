package com.daoke.mobileserver.splitwordsearch.service;

import com.daoke.mobileserver.common.page.PageList;

/**
 * 问答模块 service的接口层
 * @author zhuoshuang
 * @data 2015-5-13
 */
public interface AskAndAnswerService {


    /**
     * 根据分词查询指定页具体的问答列表
     * @param pageList
     *        分页信息
     * @param wordArr
     *        分词数组
     */
    public void getAskAndAnswerRecordList(PageList pageList, String[] wordArr);


    /**
     * 根据问题的类型查?
     * @param pageList
     *        分页信息
     * @param questionType
     *        问题的类?
     *
     */
    public void getAskAndAnswerRecordByQuestionType(PageList pageList, Integer questionType);
}
