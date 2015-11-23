<%--
  Created by IntelliJ IDEA.
  User: wangzp
  Date: 2014/12/2
  Time: 9:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" />
<html>
<script type="text/javascript" src="${ctx}/js/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/js/page.js"></script>
<head>
</head>
<body>
<input id = "ctx" type="hidden" value="${ctx}">
<div align="center">
    <div>
        <h2>WeMe用户反馈</h2>
        <table border="1" cellpadding="0" cellspacing="0" width="80%">
            <thead>
            <tr>
                <th width="10%">反馈日期</th>
                <th width="10%">反馈者账户</th>
                <th width="15%">反馈者昵称</th>
                <th width="55%">反馈的具体内容</th>
                <th width="6%">客户端</th>
                <th width="5%">操作</th>
            </tr>
            </thead>
            <tbody id="tby">

            </tbody>
        </table>
    </div>
    <div>
        <table>
            <tr>
                <td><a href="javascript:void(0);" id="firstPage">首页</a></td>
                <td><a href="javascript:void(0);" id="shang">上一页</a></td>
                <td><a href="javascript:void(0);" id="xia">下一页</a></td>
                <td><a href="javascript:void(0);" id="lastPage">末页</a></td>
                <td>共<lable id="totalPage"></lable>页</td>
                <td>第<lable id="currentPage"></lable>页</td>
                <td>共<lable id="totalRows"></lable>条记录</td>
            </tr>
        </table>
    </div>
</div>
</body>
</html>
