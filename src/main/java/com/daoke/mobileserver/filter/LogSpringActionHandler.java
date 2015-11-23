package com.daoke.mobileserver.filter;

import com.daoke.mobileserver.user.entity.WemeUserLog;
import org.apache.commons.lang.StringUtils;
import org.perf4j.StopWatch;
import org.perf4j.log4j.Log4JStopWatch;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * Created by jason on 2014/11/25.
 * <p/>
 * 请求日志拦截器
 */
public class LogSpringActionHandler extends HandlerInterceptorAdapter {

    private Logger log = org.slf4j.LoggerFactory.getLogger(LogSpringActionHandler.class);

    private StopWatch stopWatch = null;

    private String action = "";

    @Value("#{apiConfig[filterAction]}")
    private String filterActionUrl;

    @Autowired
    private JdbcTemplate wemeJdbcTemplate;

    private static List<String> filterAction;

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
          //记录用户操作行为日志
        try{
        if (filterAction(request.getRequestURI())) {
            action = request.getRequestURL().toString();
            WemeUserLog userLog = new WemeUserLog();
            String appKey = request.getParameter("appKey");
            userLog.setApp(appKey);
            userLog.setAction((request.getRequestURL().toString()));
            Map<String, Object> requestParams = request.getParameterMap();
            StringBuilder buf = new StringBuilder();
            for (String key : requestParams.keySet()) {
                buf.append(key).append("=").append(request.getParameter(key)).append("&");
            }
            userLog.setParameterInfos(buf.toString());
            userLog.setApp(appKey);
            userLog.setAccessTime(new Date());
            //保存到数据库
            addLogToDB(userLog);

        }
        }catch (Exception e){
            e.printStackTrace();
        }

        //根据日志级别判断是否打印日志
        if (log.isInfoEnabled()) {
            action = request.getRequestURL().toString();
            Map map = request.getParameterMap();
            StringBuilder buf = new StringBuilder();
            for (Object key : map.keySet()) {
                buf.append(key).append("=").append(request.getParameter(key.toString())).append("&");
            }
            if (log.isInfoEnabled()) {
                 log.info(request.getRequestURL().toString() + "?" + buf.toString());
                this.stopWatch = new Log4JStopWatch();
            }
        }

        return true;
    }

    @Override
    public void postHandle(
            HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
            throws Exception {
        if (log.isInfoEnabled()) {
            stopWatch.stop(this.action);
        }
        super.postHandle(request, response, handler, modelAndView);
    }


    private boolean filterAction(String requestUri) {
        if (StringUtils.isBlank(requestUri))
            return true;

        if (filterAction == null || filterAction.size() == 0) {
            initFilterActionList();
        }

        for (String actionUri : filterAction) {
            if (requestUri.endsWith(actionUri))
                return true;
        }

        return false;
    }

    private void initFilterActionList() {
        filterAction = new ArrayList<String>();
        for(String filterId : StringUtils.split(filterActionUrl, ",")){
            filterAction.add(filterId) ;
        }
        //Collections.copy(filterAction, Arrays.asList(StringUtils.split(filterActionUrl, ",")));
    }


    private void addLogToDB(WemeUserLog userLog) {
        //记录日志信息
        wemeJdbcTemplate.update("insert into wemeUserLog(action,accessTime,sys,app,parameterInfos)" +
                        "VALUES(?,?,?,?,?)",
                new Object[]{userLog.getAction(),//
                        userLog.getAccessTime(),//
                        userLog.getSys(),//
                        userLog.getApp(),//
                        userLog.getParameterInfos()});
    }

}
