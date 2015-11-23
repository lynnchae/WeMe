package com.daoke.mobileserver.splitwordsearch.controller;

import com.alibaba.fastjson.JSONObject;
import com.daoke.mobileserver.common.page.PageList;
import com.daoke.mobileserver.splitwordsearch.entity.AskAndAnswer;
import com.daoke.mobileserver.splitwordsearch.service.AskAndAnswerService;
import com.daoke.mobileserver.splitwordsearch.service.SplitSearchService;
import com.daoke.mobileserver.util.AbDateUtil;
import com.daoke.mobileserver.util.ConstantsUtil;
import com.daoke.mobileserver.util.JsonPuserUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 拆分词搜索controller层
 * @author zhuoshuang
 * @data 2015-5-13
 */
@Controller
@RequestMapping(value="/splitWord")
public class SplitSearchController {

    private final Logger logger = Logger.getLogger(SplitSearchController.class);

    /***分词搜索 service对象*/
    @Autowired
    private SplitSearchService splitSearchService;

    /***问答 service对象*/
    @Autowired
    private AskAndAnswerService askAndAnswerService;



    /**
     * 根据内容做搜索查询
     * @param text
     *        输入要搜素的内容
     * @param currentPage
     *        当前的页数
     * @param pageSize
     *        每页显示的条数
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/searchContent")
    public void search(@RequestParam String text,@RequestParam Integer pageSize,@RequestParam Integer currentPage,HttpServletResponse response){
        String result= "";
        try{
            String[] wordArr = splitSearchService.getToAnalysis(text);

            //初始化分页信息
            PageList pageList = new PageList(currentPage,0,pageSize,new ArrayList());
            //查询当前页指定的记录
            askAndAnswerService.getAskAndAnswerRecordList(pageList, wordArr);

            Map map = new HashMap<String,Object>();
            map.put("currentPage",pageList.getCurrentPageNo());
            map.put("pageSize", pageList.getPageSize());
            map.put("totalPage",pageList.getTotalPage());
            map.put("totalCount",pageList.getTotalCount());

            List<Map<String,Object>> recordList = new ArrayList<Map<String, Object>>();
            for(int i=0; i<pageList.getRecords().size(); i++){
                AskAndAnswer askAndAnswer = (AskAndAnswer) pageList.getRecords().get(i);
                Map<String,Object> answerMap = new HashMap<String, Object>();
                answerMap.put("id",askAndAnswer.getId());
                answerMap.put("questionType",askAndAnswer.getQuestionType());
                answerMap.put("question",markHighColor(wordArr,askAndAnswer.getQuestion()));
                answerMap.put("fileUrl",askAndAnswer.getFileUrl());
                answerMap.put("createDate", AbDateUtil.getStringByFormat(askAndAnswer.getCreateDate(), AbDateUtil.dateFormatYMDHMS));
                answerMap.put("answer",markHighColor(wordArr,askAndAnswer.getAnswer()));
                recordList.add(answerMap);
            }
            map.put("record", recordList);

            Map<String,Object> resultMap = new HashMap<String, Object>();
            resultMap.put("ERRORCODE",ConstantsUtil.ERRORCODE_OK);
            resultMap.put("RESULT",map);

            result = JSONObject.toJSONString(resultMap);


        }catch(Exception e){
            result = JsonPuserUtil.jsonToString(ConstantsUtil.ERRORCODE_SERVICE_ERROR, "查询失败");
            e.printStackTrace();
        }


        OutputStream os = null;
        try {
            os  = response.getOutputStream();
            os.write(result.getBytes("utf-8"));
        } catch (IOException e) {

        }finally {
            if(os != null){
                try {
                    os.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

    /**
     * 对数据进行高亮
     * @param strArr
     * @param content
     * @return
     */
    public String markHighColor(String[] strArr,String content){
        for(int i=0; strArr != null && i<strArr.length; i++){
            content = content.replace(strArr[i],"<font color='red'>"+strArr[i]+"</font>");
        }
        return content;
    }


    /**
     * 根据问题的类型查询
     * @param questionType
     *        问题类型
     * @param pageSize
     *        每页显示的条数
     * @param currentPage
     *        当前的页数
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/queryByOperationType")
    public void queryBuOperation(@RequestParam Integer questionType,@RequestParam Integer pageSize,@RequestParam Integer currentPage,HttpServletResponse response){
        String result= "";
        //初始化分页信息
        try {
            PageList pageList = new PageList(currentPage, 0, pageSize, new ArrayList());
            //查询当前页指定的记录
            askAndAnswerService.getAskAndAnswerRecordByQuestionType(pageList,questionType);

            Map map = new HashMap<String,Object>();
            map.put("currentPage",pageList.getCurrentPageNo());
            map.put("pageSize", pageList.getPageSize());
            map.put("totalPage",pageList.getTotalPage());
            map.put("totalCount", pageList.getTotalCount());

            List<Map<String,Object>> recordList = new ArrayList<Map<String, Object>>();
            for(int i=0; i<pageList.getRecords().size(); i++){
                AskAndAnswer askAndAnswer = (AskAndAnswer) pageList.getRecords().get(i);
                Map<String,Object> answerMap = new HashMap<String, Object>();
                answerMap.put("id",askAndAnswer.getId());
                answerMap.put("questionType",askAndAnswer.getQuestionType());
                answerMap.put("question",askAndAnswer.getQuestion());
                answerMap.put("fileUrl",askAndAnswer.getFileUrl());
                answerMap.put("createDate", AbDateUtil.getStringByFormat(askAndAnswer.getCreateDate(), AbDateUtil.dateFormatYMDHMS));
                answerMap.put("answer",askAndAnswer.getAnswer());
                recordList.add(answerMap);
            }
            map.put("record",recordList);


            Map<String,Object> resultMap = new HashMap<String, Object>();
            resultMap.put("ERRORCODE",ConstantsUtil.ERRORCODE_OK);
            resultMap.put("RESULT",map);

            result = JSONObject.toJSONString(resultMap);

        }catch (Exception e){
            result = JsonPuserUtil.jsonToString(ConstantsUtil.ERRORCODE_SERVICE_ERROR,"查询失败");
            e.printStackTrace();
        }
        OutputStream os = null;
        try {
            os  = response.getOutputStream();
            os.write(result.getBytes("utf-8"));
        } catch (IOException e) {

        }finally {
            if(os != null){
                try {
                    os.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }

    }
}
