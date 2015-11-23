<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:set var="ctx" value="${pageContext.request.contextPath }" />
<html>
<script type="text/javascript" src="${ctx}/js/jquery.min.js"></script>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>suggest comment</title>
    <script type="text/javascript">
        $(function () {
            $("input").keypress(
                    function (e) {
                        var keyCode = e.keyCode ? e.keyCode : e.which ? e.which : e.charCode;
                        if (keyCode == 13) {
                            for (var i = 0; i < this.form.elements.length; i++) {
                                if (this == this.form.elements[i])
                                    break;
                            }
                            i = (i + 1) % this.form.elements.length;
                            this.form.elements[i].focus();
                            return false;
                        } else {
                            return true;
                        }
                    });
        });

        function submitForm() {
            var ctx = $("#ctx").val();
            var replyContent = $.trim($('#replyContent').val());
            var replyName = $.trim($('#replyName').val());
            if (replyContent != null && replyName != null && replyContent != '' && replyName != '') {
                $("#submit").attr("disabled", true);
                $.ajax({
                    cache: true,
                    type : "post",
                    url : ctx + "/addSuggestComment",
                    data:$("form").serialize(),
                    async: false,
                    success: function (data) {
                        alert("提交成功！");
                        $("#submit").attr("disabled", false);
                        history.go(-1);
                    },
                    error: function (request) {
                        alert("addSuggestComment fail");
                    }
                });
            } else {
                alert("回复内容或昵称不能为空！");
                return false;
            }
        }
    </script>
</head>
<body>
<input id = "ctx" type="hidden" value="${ctx}">
<div align="center">
    <h2>WeMe用户反馈回复</h2>

    <div>
        <textarea rows="25" cols="150" disabled="disabled" style='resize: none; wrap: physical'>
            <c:forEach
                items="${suggestComments}"
                var="suggestComment">${suggestComment.nickName }反馈：${suggestComment.suggestContent }&#13;
                <c:if test="${!empty suggestComment.replyName }">${suggestComment.replyName }回复：${suggestComment.replyContent }&#13;&#13;
                </c:if>
            </c:forEach>
        </textarea>
    </div>
    <div>
        <form id="form">
            <label>回复内容：</label><br/>
            <input type="hidden" name="suggestID" value="${suggestID}"/>

            <textarea id="replyContent" name="replyContent" rows="25" cols="150" style='resize: none; wrap: physical'></textarea>

            <br/> 请输入您的昵称：
            <input id="replyName" name="replyName" align="left"/>
            <input type="button" value="提交" id="submit" onclick="submitForm();window.close();"/>
            <!--<a href="javascript:void(history.go(-1))">返回</a> -->

            <a href="javascript:window.opener=null;window.close();">返回</a>
        </form>
    </div>
</div>
</body>
</html>