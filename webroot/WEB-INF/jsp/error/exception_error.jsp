<%@ page language="java" contentType="application/octet-stream; charset=utf-8"
    pageEncoding="UTF-8"%>
<%@page import="com.daoke.mobileserver.common.model.CommonJsonResult"%>
<%@page import ="com.daoke.mobileserver.util.JsonMapper" %>
<%@page import ="com.daoke.mobileserver.util.ConstantsUtil" %>
<%
    CommonJsonResult rs = new CommonJsonResult();
    rs.setERRORCODE(ConstantsUtil.ERRORCODE_SERVICE_ERROR);
    try {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        if (request.getAttribute("exception") instanceof Exception) {
            Exception exception = (Exception) request.getAttribute("exception");
            if (exception != null) {
                rs.getInfoMap().put("message", exception.getMessage());
            }
        }
        response.getWriter().write(JsonMapper.toJson(rs, true));
    }catch (Exception e){
        e.printStackTrace();
    }finally {
        rs.setRESULT("Server is Error");
        response.getWriter().write(JsonMapper.toJson(rs, true));
    }
%>