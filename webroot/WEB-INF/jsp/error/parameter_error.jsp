<%@ page language="java" contentType="application/octet-stream; charset=utf-8"%>
<%@page import="org.springframework.web.bind.MissingServletRequestParameterException"%>
<%@page import="com.daoke.mobileserver.common.model.CommonJsonResult"%>
<%@page import ="com.daoke.mobileserver.util.JsonMapper" %>
<%@page import ="com.daoke.mobileserver.util.ConstantsUtil" %>
<%
	CommonJsonResult rs = new CommonJsonResult();
    response.setCharacterEncoding("UTF-8");
    response.setContentType("application/json");
	MissingServletRequestParameterException exception=(MissingServletRequestParameterException)request.getAttribute("exception");
    rs.setERRORCODE(ConstantsUtil.ERRORCODE_PARAMETERS_ERROR);
    rs.setRESULT("Required " + exception.getParameterType() + " parameter '" + exception.getParameterName() + "' is not present");
    response.getWriter().write(JsonMapper.toJson(rs,true));


%>



