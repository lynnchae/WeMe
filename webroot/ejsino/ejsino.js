$(document).ready(function() {
    var ctx = $("#ctx").val();
    $("#bjbutton").click(function(){

        if(($("#licenseno1").val() == '' ||  $("#licenseno2").val() == '') && $("#checkbox_nolicenseflag").is(":checked") == false){
            alert("请输入车牌号");
            $("#licenseno2").focus();
            return false;
        }

        if ($("#ownername").val() =='') {
            alert("请输入车主姓名");
            $("#ownername").focus();
            return false;
        }

        if($("#ownercertno").val() == ''){
            alert("请输入车主身份证号");
            $("#ownercertno").focus();
            return false;
        }

        if($("#checkbox_isownername").is(":checked") == false || $("#insuredperson").val() ==''){
            alert("请输入被保险人姓名");
            $("#insuredperson").focus();
            return false;
        }

        if($("#plcstartdate").val() == ''){
            alert("请选择保单生效日");
            $("#plcstartdate").focus();
            return false;
        }

        beofreSubmitForm();
        $("#companyno").val("0099");
        //中华联合保险公司
       if($("#reqType").val() == "2" && $("#0099").val() == "0099"){
           if($("#vehicleframeno").val() == ''){
               alert("请输入车架号");
               $("#vehicleframeno").focus();
               return false;
           }

           if($("#engineno").val() == ''){
               alert("请输入发动机号");
               $("#engineno").focus();
               return false;
           }

           if($("#vehiclemodelname").val() == ''){
               alert("请选择车型名称");
               $("#vehiclemodelname").focus();
               return false;
           }

           if($("#vehicleinvoicedate").val() == ''){
               alert("请选择购车发票开具日期");
               $("#vehicleinvoicedate").focus();
               return false;
           }


           if($("#runcardcertificatedate").val() == ''){
               alert("请选择开具车辆来历凭证所载日期");
               $("#runcardcertificatedate").focus();
               return false;
           }

           if($("#bizbegindate").val() == ''){
               alert("请选择商业险保单起期");
               $("#bizbegindate").focus();
               return false;
           }

           if($("#forcebegindate").val() == ''){
               alert("请选择交强险保单起期");
               $("#forcebegindate").focus();
               return false;
           }


           if($("#taxticketno").val() == ''){
               alert("请输入车辆来历凭证编号");
               $("#taxticketno").focus();
               return false;
           }

       }


        $("#bjbutton").attr("disabled",true);
        /***提交表单*/
        $('#form1').ajaxSubmit({
            url : ctx + "/offer_elements_query",
            dataType : 'json',
            type:"POST",
            success:function(data){

                $("#bjbutton").attr("disabled",false);

                var resultCode = data.resultCode;
                if(resultCode == 1) {
                    alert(data.message);
                    return false;
                }
                var tagList = data.tagList;
                var riskInfoFlag = data.riskInfoFlag;
              // var btnText = data.btnText;

                $("#step").val(data.step);
                if (riskInfoFlag == 1){
                    $("#tr-riskInfo1").show();
                    $("#tr-riskInfo2").show();
                };
             //$("#bjbutton").val(btnText);


                <!--获取要素处理-->
                if ($.isEmptyObject(tagList) == false) {

                    if($("#companyno").val == "0012"){
                        $("#tr-firstregisterdate").show();
                    }

                    for(var i = 0, len = tagList.length; i < len; i++){
                        var widget = $("#"+tagList[i].key+"");
                        //用于控制判断
                        $("#reqType").val("2");

                        //将隐藏的表格显示
                        $("#tr-"+tagList[i].key+"").show();

                        //替换表格lable的内容
                        if(tagList[i].label != ''){
                            $("#td-"+tagList[i].key+"").html(tagList[i].label + "：" + "<span style='color:#F00'>*</span>");
                        }

                        //如果是select ，则解析结果，填充option
                        if(tagList[i].type == "select" || tagList[i].type == "radio"){

                            //将值清空
                            widget.empty();

                            var strs1 = new Array();
                            strs1 = tagList[i].data.split(";")
                            var options = '';
                            $.each(strs1, function(index, tx){
                                strs2 = tx.split(":");
                                options += "<option value='"+strs2[1]+"'>"+strs2[0]+"</option>";
                            })

                            widget.html(options);
                        };

                        //赋值
                        if (tagList[i].value != '') {
                            widget.val(tagList[i].value);
                        }

                    };


                    //商业险起期
                    $("#bizbegindate").val($("#plcstartdate").val());
                    $("#bizbegindate").attr("min", getCurentDate());
                    //交强险起期
                    $("#forcebegindate").attr("min",getCurentDate());

                    //是否是过户车
                    $("#specialcarflag").val("1");
                };

                <!--保费信息(PremInfo)-->
                var premInfo = data.premInfo;
                //alert(premInfo);
                if ($.isEmptyObject(premInfo) == false) {

                    $("#tr-bjbutton").hide();
                    $("#preminfo").show();
                    $("#forcepremium").val(premInfo.forcepremium);
                    $("#vehicletaxamount").val(premInfo.vehicletaxamount);
                    $("#businesspremium").val(premInfo.businesspremium);
                    $("#realpremium").val(premInfo.realpremium);
                    $("#totalremium").val(premInfo.totalremium);
                    $("#span_forcepremium").html(premInfo.forcepremium);
                    $("#span_vehicletaxamount").html(premInfo.vehicletaxamount);
                    $("#span_businesspremium").html(premInfo.businesspremium);
                    $("#span_realpremium").html(premInfo.realpremium);
                    $("#span_totalremium").html(premInfo.totalremium);
                };


                <!--是否投保确认-->
                var inFrom = data.inFrom;
                if ($.isEmptyObject(inFrom) == false){
                    $("#configbeforejudge").val(inFrom.configbeforejudge);
                }
            },
            error: function (data){
                $("#bjbutton").attr("disabled",false);
                var json = JSON.parse(data.responseText);
                alert(json.message);
            }});
    }) ;


    //提交form前的准备工作
    var beofreSubmitForm = function(){


        /* 判断是否是新车*/
        if($("#checkbox_nolicenseflag").is(":checked") == true) {
            $("#nolicenseflag").val(1);
        } else {
            $("#nolicenseflag").val(0);
        }


        //车辆损失险 是否含不计免赔
        if($("#checkbox_remark9").is(":checked") == true) {
            $("#remark9").val("0");
        } else {
            $("#remark9").val("1");
        }

        //商业第三者责任险 是否含不计免赔
        if($("#checkbox_remark10").is(":checked") == true) {
            $("#remark10").val("0");
        } else {
            $("#remark10").val("-1");
        }

        //全车盗抢险 是否含不计免赔
        if($("#checkbox_remark11").is(":checked") == true) {
            $("#remark11").val("0");
        } else {
            $("#remark11").val("-1");
        }

        //司机座位责任险： 是否含不计免赔
        if($("#checkbox_remark12").is(":checked") == true) {
            $("#remark12").val("0");
        } else {
            $("#remark12").val("-1");
        }

        //乘客座位责任险： 是否含不计免赔
        if($("#checkbox_remark15").is(":checked") == true) {
            $("#remark15").val("0");
        } else {
            $("#remark15").val("-1");
        }

        if ($("#step").val() == "G"){
            $("#outerno").val(randomNum(10));
        };

        var nolicenseflag = $("#nolicenseflag").val();
        if (nolicenseflag == "0"){
            $("#licenseno").val($("#licenseno1").val() + "" + $("#licenseno2").val());
        }
    };

    //精确试算报价
    $("#buyproduct").click(function(){
        $("#background, #content").show();

        $("#certTypeNo").val($("#ownercertno").val());
        $("#name").val($("#ownername").val());


        //投保人信息
        $("#aplInfo_name").val($("#ownername").val());
        $("#aplInfo_certtype").val($("#certtype").val());
        $("#aplInfo_certno").val($("#ownercertno").val());

        //联系人信息
        $("#linkInfo_name").val($("#ownername").val());

        conPosition("content");
    });

    //购买页取消按钮
    $("#buyCancel").click(function(){
        $("#background, #content").hide();
    });


    //购买按钮点击事件
    $("#buyButton").click(function(){
         if($("#insurInfo_name").val() == ''){
           alert("请输入被保人姓名");
           $("#insurInfo_name").focus();
           return false;
         }

        if($("#insurInfo_certno").val() == ''){
            alert("请输入被保人证件号码");
            $("#insurInfo_certno").focus();
            return false;
        }

        if($("#insurInfo_birth").val() == ''){
            alert("请选择被保人出生日期");
            $("#insurInfo_birth").focus();
            return false;
        }

        if($("#aplInfo_name").val() == ''){
            alert("请输入投保人姓名");
            $("#aplInfo_name").focus();
            return false;
        }

        if($("#aplInfo_certno").val() == ''){
            alert("请输入投保人证件号码");
            $("#aplInfo_certno").focus();
            return false;
        }



        if($("#linkInfo_name").val() == ''){
            alert("请输入联系人姓名");
            $("#linkInfo_name").focus();
            return false;
        }

        if($("#linkInfo_mobile").val() == ''){
            alert("请输入联系人手机号");
            $("#linkInfo_mobile").focus();
            return false;
        }

        if($("#linkInfo_email").val() == ''){
            alert("请输入被保人email地址");
            $("#linkInfo_email").focus();
            return false;
        }

        if($("#linkInfo_zipcode").val() == ''){
            alert("请输入邮编号");
            $("#linkInfo_zipcode").focus();
            return false;
        }

        if($("#linkInfo_address").val() == ''){
            alert("请输入地址");
            $("#linkInfo_address").focus();
            return false;
        }


        $("#buyButton").attr("disabled", true);
        $('#form1').ajaxSubmit({
            url : ctx + "/surance_confirmation",
            dataType : 'json',
            type:"POST",
            async:false,
            success:function(data){
                $("#buyButton").attr("disabled", false);

                var resultCode = data.resultCode;
               // alert(data.message);
                if(resultCode == 1) {
                    alert(data.message);
                    return false;
                }


                <!--信息(InForm)-->
                var inForm = data.inForm;
                if($.isEmptyObject(inForm) == false){

                    $("#bizInform").val(inForm.bizInform);

                    $("#forceInform").val(inForm.forceInform);

                    $("#appnt").html(inForm.appnt);

                    $("#remark").html(inForm.remark);

                    $("#background,#content").hide();
                    $("#background, #confirmContent").show();
                    conPosition("confirmContent");
                }

                <!--支付-->
                var payURL = data.payURL;
                if($.isEmptyObject(payURL) == false) {
                    $("#paypath").val(payURL.paypath);
                    openUrl(payURL.paypath);
                }
            },
            error: function (data){
                $("#buyButton").attr("disabled", false);
                var json = JSON.parse(data.responseText);
                alert(json.message);
            }
        })
    });

    $("#applyBuybtn").click(function(){

        $("#applyBuybtn").attr("disabled", true);

        $("#configbeforejudge").val("N");
        $('#form1').ajaxSubmit({
            url : ctx + "/surance_confirmation",
            dataType : 'json',
            type:"POST",
            async:false,
            success:function(data){
                $("#applyBuybtn").attr("disabled", false);
                var resultCode = data.resultCode;
                if(resultCode == 1) {
                    alert(data.message);
                    return false;
                }
                <!--支付-->
                var payURL = data.payURL;

                if($.isEmptyObject(payURL) == false) {

                    $("#paypath").val(payURL.paypath);
                    openUrl(payURL.paypath);
                }
            },
            error: function (data){
                $("#applyBuybtn").attr("disabled", false);
                var json = JSON.parse(data.responseText);
                alert(json.message);
            }
        })
    });


    //获取城市配置数据
    var queryCity = function () {
        $.ajax({
            type: "post",
            url : ctx + "/query_city",
            dataType : "json",
            contentType : "application/json; charset=utf-8",
            success:function(data){
                var code = data.ERRORCODE;
                if (0 != code) {
                    alert(data.RESULT);
                    return false;
                };

                var array = data.RESULT;

                var selectEle = $("#parent_citycode");
                selectEle.empty();
                var options = '';
                for ( var i = 0, len = array.length; i < len; i++) {
                    options += "<option value='"+array[i].cityCode+"'>"+array[i].cityName+"</option>";
                };

                selectEle.html(options);
            },error:function(data) {
                var json = JSON.parse(data.responseText);
                alert(json.message);
            }
        })
    };
    queryCity();


    $("#parent_citycode").change(function (){

        var cityCode = $("#parent_citycode").val();
        $.ajax({
            type: "post",
            url : ctx + "/query_city?cityCode=" + cityCode,
            dataType : "json",
            contentType : "application/json; charset=utf-8",
            success:function(data){
                var code = data.ERRORCODE;
                if (0 != code) {
                    alert(data.RESULT);
                    return false;
                };

                var array = data.RESULT;

                var selectEle = $("#citycode");
                selectEle.empty();
                var options = '';
                for ( var i = 0, len = array.length; i < len; i++) {
                    options += "<option value='"+array[i].cityCode+"'>"+array[i].cityName+"</option>";
                };

                selectEle.html(options);
            },error:function(data) {
                var json = JSON.parse(data.responseText);
                alert(json.message);
            }
        })
    });


    //选择保险公司
    $("#insuranceCompony").change(function () {
        $("#companyno").val($("#insuranceCompony").val());
    });

    //商业险告知单 确认
    $("#bizInformBtn").click(function(){
        window.open($("#bizInform").val());
        return false;
    });

    //交强险告知单 确认
    $("#appntBtn").click(function(){
        window.open($("#forceInform").val());
        return false;
    });




    //根据输入的信息查询车辆信息
    $("#queryCarTypeBtn").click(function(){


        //判断查询车辆信息是否有录入
        if($("#brand").val() == ''){
            alert("请输入车辆品牌名");
            $("#brand").focus();
            return false;
        }
        if($("#chejiahao").val() == ''){
            alert("请输入车架号");
            $("#chejiahao").focus();
            return false;
        }

        if($("#fadongji").val() == ''){
            alert("请输入发动机号");
            $("#fadongji").focus();
            return false;
        }

        $("#vehicleid").val($("#brand").val());
        $("#vehicleframeno").val($("#chejiahao").val());
        $("#engineno").val($("#fadongji").val());

        $("#queryCarTypeBtn").attr("disable", true);
        $('#form1').ajaxSubmit({
            url : ctx + "/queryCarModel",
            dataType : 'json',
            type:"POST",
            async:false,
            success:function(data){
                $("#queryCarTypeBtn").attr("disabled", false);
                var resultCode = data.statuscode;
                if(resultCode == 1) {
                    alert(data.message);
                    return false;
                }

                var count = data.totalCount;
                if (count <= 0){
                    alert("没有获取到数据");
                    return false;
                }

                $("#tr-cartypeselect").show();
                $("#td-selectCarType").show();
                $("#sessionid").val(data.sessionid);

                var selectEle = $("#cartypeselect");
                selectEle.empty();
                var options = '';

                var array = data.root;
                for ( var i = 0, len = array.length; i < len; i++) {
                    options += "<option value='"+array[i].key+"'>"+array[i].value+"</option>";
                };

                selectEle.html(options);
            },
            error: function (data){
                $("#queryCarTypeBtn").attr("disabled", false);
                var json = JSON.parse(data.responseText);
                alert(json.message);
            }
        })
    });

    /**
     * 确认选择的车型
     */
    $("#selectCarTypeBtn").click(function(){
        $("#vehicleframeno").val($("#chejiahao").val());
        $("#engineno").val($("#fadongji").val());
        $("#vehicleid").val($("#cartypeselect").val());
        $("#vehiclemodelname").val($("#cartypeselect").find("option:selected").text());
        $("#vehiclebrand").val("重新选择品牌");

        $("#vehiclemodelnamedesc").html($("#cartypeselect").find("option:selected").text());
        $("#background, #carTypeDiv").hide();
    });


    /**
     * 取消查询车型按钮
     */
    $("#cancleQueryCar").click(function(){
        $("#background, #carTypeDiv").hide();
    });


    /**
     * 信息同车主
     */
    $("#infoAsOwner").click(function(){
        $("#infoAsInsur").attr("checked", false);
        $("#aplInfo_name").val($("#ownername").val());
        $("#aplInfo_certno").val($("#ownercertno").val());
    });


    /**
     * 信息同被保人
     */
    $("#infoAsInsur").click(function(){
        $("#infoAsOwner").attr("checked", false);
        $("#aplInfo_name").val($("#insurInfo_name").val());
        $("#aplInfo_sex").val($("#insurInfo_sex").val());
        $("#aplInfo_certtype").val($("#insurInfo_certtype").val());
        $("#aplInfo_certno").val($("#insurInfo_certno").val());
        $("#aplInfo_birth").val($("#insurInfo_birth").val());
    });

    //保单起日大于今天
    $("#plcstartdate").attr("min",getCurentDate());


    //选择保险公司按钮
  /*  $("#navBtn").click(function(){
        $("#nav").slideToggle("fast");
    });*/

});



//被保人姓名是否同行驶证车主姓名
var onChangeIsOwnerName = function(){
    if ($("#checkbox_isownername").is(":checked") == true) {
        $("#insuredperson").val($("#ownername").val());
        $("#insuredperson").attr("disabled",true);
    } else{
        $("#insuredperson").attr("disabled",false);
    }
};

//新车是否上牌
var onChangeLicenseNo = function(){
    if($("#checkbox_nolicenseflag").is(":checked") == true){
        $("#licenseno1").attr("disabled",true);
        $("#licenseno2").attr("disabled",true);
    }else{
        $("#licenseno1").attr("disabled",false);
        $("#licenseno2").attr("disabled",false);
        $("#licenseno2").focus();
    }
};


//产生10位随机数
function randomNum(n){
    var t = getCurentTime();
    for(var i=0;i<n;i++){
        t+=Math.floor(Math.random()*10);
    }
    return t;
};

//购买确认弹出层
function conPosition(content) {
    var oBackground = document.getElementById("background");
    var dw = $(document).width();
    var dh = $(document).height();
    oBackground.style.width = dw+'px';
    oBackground.style.height = dh+'px';
    var oContent = document.getElementById(content);
    var scrollTop = document.documentElement.scrollTop || document.body.scrollTop;
    var l = (document.documentElement.clientWidth - oContent.offsetWidth) / 2;
    var t = ((document.documentElement.clientHeight - oContent.offsetHeight) / 2) + scrollTop;
    oContent.style.left = l + 'px';
    oContent.style.top = t + 'px';
};


/**
 * 打开支付页面
 * @param paypath
 */
function openUrl(paypath){
    $("#payForm").attr("action", paypath);
    $('#payForm').submit();
}

/**
 * 查询车型页面
 */
function showSelectBrand(){
    $("#background, #carTypeDiv").show();
    conPosition("carTypeDiv");
}


/**
 * 是否是过户车
 */
var onSpecialcarFlag = function (){
    var selectVal = $("#specialcarflag").val();
    if (0 == selectVal) {
        $("#tr-specialcardate").hide();
    } else if (1 == selectVal) {
        $("#tr-specialcardate").show();
    }
};

/**
 * 获取当前时间
 * @returns {string}
 */
function getCurentTime() {
    var now = new Date();

    var year = now.getFullYear();       //年
    var month = now.getMonth() + 1;     //月
    var day = now.getDate();            //日

    var hh = now.getHours();            //时
    var mm = now.getMinutes();          //分

    var clock = year + "";

    if(month < 10)
        clock += "0";

    clock += month + "";

    if(day < 10)
        clock += "0";

    clock += day + "";

    if(hh < 10)
        clock += "0";

    clock += hh + "";
    if (mm < 10) clock += '0';
    clock += mm;

    return(clock);
}

/**
 * 获取年月日
 * @returns {string}
 */
function getCurentDate() {
    var now = new Date();

    var year = now.getFullYear();       //年
    var month = now.getMonth() + 1;     //月
    var day = now.getDate();            //日

    var clock = year + "-";

    if(month < 10)
        clock += "0";

    clock += month + "-";

    if(day < 10)
        clock += "0";

    clock += day + "";

    return(clock);
}

//选择保险公司
function choseCompany(companyno){
    $("#companyno").val(companyno);

    $("#nav").slideUp().removeClass('in');

    $("#companyMenu li").click(function(){
        $(this).addClass("active").siblings().removeClass("active");  //首先添加然后移除active
    });
}


