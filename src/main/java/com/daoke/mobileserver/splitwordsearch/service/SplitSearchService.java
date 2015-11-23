package com.daoke.mobileserver.splitwordsearch.service;

/**
 * 拆分词搜索 service 接口层
 * @author zhuoshuang
 * @data 2015-5-13
 */
public interface SplitSearchService {


    /**
     * 对文本获取关键字
     * @param text
     *        要拆分的
     */
    public String[] getKeyWords(String text);


    /**
     * 通过ansj对文本进行精确拆分
     * @param text
     *         要拆分的
     * @return
     */
    public String[] getToAnalysis(String text);

}
