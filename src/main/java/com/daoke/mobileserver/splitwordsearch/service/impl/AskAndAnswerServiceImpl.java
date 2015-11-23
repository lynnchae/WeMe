package com.daoke.mobileserver.splitwordsearch.service.impl;

import com.daoke.mobileserver.common.page.PageList;
import com.daoke.mobileserver.splitwordsearch.dao.AskAndAnswerDao;
import com.daoke.mobileserver.splitwordsearch.entity.AskAndAnswer;
import com.daoke.mobileserver.splitwordsearch.service.AskAndAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 问答模块 service的实现
 * @author zhuoshuang
 * @data 2015-5-13
 */
@Service
public class AskAndAnswerServiceImpl implements AskAndAnswerService{

    /***问答dao层对象*/
    @Autowired
    private AskAndAnswerDao askAndAnswerDao;


    @Override
    public void getAskAndAnswerRecordList(PageList pageList, String[] wordArr) {
        if(wordArr == null || wordArr.length == 0){
            return;
        }

        StringBuffer splitWordLike = new StringBuffer("");
        StringBuffer contentLike = new StringBuffer("");
        for(int i=0; i<wordArr.length ; i++){
            splitWordLike.append("word like '%"+wordArr[i]+"%'");
            contentLike.append("answer like '%"+wordArr[i]+"%'");
            if(i<wordArr.length-1){
                splitWordLike.append(" or ");
                contentLike.append(" or ");
            }
        }
        if(splitWordLike.length() == 0 || contentLike.length() == 0){
            splitWordLike.append(" 1=0");
            contentLike.append(" 1=0");
        }
        //查询符合条件的记录总数
        String countSql = "select count(id) from askAndAnswer where 1=1 and id in (select askAndAnswerID from titleIndex " +
                " where 1=1 and "+ splitWordLike.toString()  +") or ("+contentLike+")";
        int num = askAndAnswerDao.queryCountNum(countSql);

        //初始化分页信息
        pageList.setTotalCount(num);
        int startPlace = PageList.getStartOfPage(pageList.getCurrentPageNo(),pageList.getPageSize());
        int size = pageList.getPageSize();
        //查询当前指定也得记录
        String listSql = "select id,questionType,question,answer,fileUrl,createDate,updateDate from askAndAnswer where 1=1 and id in (select askAndAnswerID from titleIndex " +
                " where 1=1 and "+ splitWordLike.toString()  +") or ("+contentLike+") limit "+startPlace+"," +size;
        List<AskAndAnswer> list = askAndAnswerDao.getAskAndAnswerRecordList(listSql);

        pageList.setRecords(list);
    }

    @Override
    public void getAskAndAnswerRecordByQuestionType(PageList pageList, Integer questionType) {
//        String countSql = "select count(id) from askAndAnswer where 1=1 and questionType =" + questionType;
//        int num = askAndAnswerDao.queryCountNum(countSql);
        Map map= new HashMap<String,Object>();
        map.put("questionType",questionType);
        int num = askAndAnswerDao.queryCountByMapper("getCountByQuestionType", map);

        //初始化分页信息
        pageList.setTotalCount(num);
        int startPlace = PageList.getStartOfPage(pageList.getCurrentPageNo(),pageList.getPageSize());
        int size = pageList.getPageSize();

//        String listSql = "select id,questionType,question,answer,fileUrl,createDate,updateDate from askAndAnswer where 1=1 and questionType =" + questionType + " limit "+startPlace+"," +size;
//        List<AskAndAnswer> list = askAndAnswerDao.getAskAndAnswerRecordList(listSql);
        map.put("startPlace",startPlace);
        map.put("size", size);
        List<AskAndAnswer> list = askAndAnswerDao.getListByMapper("getListByQuestionType",map);
        pageList.setRecords(list);
    }
}
