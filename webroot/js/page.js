$(document).ready(function() {
    var ctx = $("#ctx").val();
    var currentPage = 1;
    var numPerPage = 10;

    var queryByPage = function (){
        $.ajax({
            type: "post",
            url : ctx + "/querySuggest?username=mirrtalk&tip=success&currentPage=" + currentPage + "&numPerPage=" + numPerPage,
            dataType : "json",
            contentType : "application/json; charset=utf-8",
            success:function(data){
                var array = data.suggestList;
                var tby = $("#tby");
                var totalPage = data.totalPage;
                $("#totalPage").val(totalPage);
                $("#currentPage").html(currentPage);
                $("#totalRows").html(data.totalRows);
                $("#totalPage").html(totalPage);
                $("#tby tr").remove();
                for ( var i = 0, len = array.length; i < len; i++) {
                    var phoneType = array[i].phoneType == 0 ? "Android" : "iOS";
                    var td1 = $("<td align='center'>" + getDate(array[i].createTime) + "</td>");
                    var td2 = $("<td align='center'>" + array[i].accountID + "</td>");
                    var td3 = $("<td>" + array[i].nickName + "</td>");
                    var td4 = $("<td>" + array[i].suggestContent + "</td>");
                    var td5 = $("<td align='center'>" + phoneType + "</td>");
                    var td6 = $("<td align='center'><a  target='_blank' href='toAddSuggestComment.do?accountID="+ array[i].accountID + "&suggestID=" + array[i].suggestID + "'>回复</a></td>");
                    var tr = $("<tr></tr>");
                    tr.append(td1).append(td2).append(td3).append(td4).append(td5).append(td6);
                    tr.appendTo(tby);
                }
            },
            error:function(data){
                alert("erro" + data);
            }

        })
    };
    // 初始化列表
    queryByPage();
    // 首页
    $("#firstPage").bind("click", function() {
        currentPage = 1;
        queryByPage(currentPage, numPerPage);
    });

    // 上一页
    $("#shang").click(function() {
        if (currentPage == 1) {
            alert("已经到达第一页");
            return;
        } else {
            currentPage--;
            queryByPage();
        }
    });

    // 下一页
    $("#xia").click(function() {
        if (currentPage == $("#totalPage").val()) {
            alert("已经到达最后一页");
            return;
        } else {
            currentPage++;
            queryByPage();
        }
    });

    // 末页
    $("#lastPage").bind("click", function() {
        currentPage = $("#totalPage").val();
        queryByPage(currentPage, numPerPage);
    });
});

function bench(m){
    return m < 10 ? '0' + m : m;
};
function getDate(timestamp){
    var date = new Date(timestamp*1000);
    var year = date.getFullYear();
    var month = date.getMonth() + 1;
    var day = date.getDate();
    var hour = date.getHours();
    var minute = date.getMinutes();
    var seconds = date.getSeconds();
    return bench(year) + "-" + bench(month) + "-" + bench(day) + " " + bench(hour) + ":" + bench(minute) + ":" + bench(seconds);
}