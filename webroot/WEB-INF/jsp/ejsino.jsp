<%--
  Created by IntelliJ IDEA.
  User: wangzp
  Date: 2014/12/10
  Time: 15:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="ctx" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" />
    <meta name="description" content="">
    <meta name="author" content="">
    <title>在线汽车保险报价-基本信息</title>
    <link href="${ctx}/ejsino/bootstrap.min.css" rel="stylesheet">
</head>

<style TYPE="text/css">
    .popupsBackground {position:absolute; z-index:995; top:0px; left:0px; background:rgb(50,50,50);background:rgba(0,0,0,0.5); display:none;}
    .popupsContent {position:absolute; width:auto; z-index:996; padding:10px; background:#fff; border-radius:5px; display:none;}
</style>
<body>
<input id = "ctx" type="hidden" value="${ctx}">
<div class="navbar navbar-inverse navbar-static-top" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse" id="navBtn">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">在线汽车保险报价</a>
        </div>
        <div class="navbar-collapse collapse" id="nav">
            <ul class="nav navbar-nav navbar-right" id="companyMenu">
                <%--<li class="active"><a href="javascript:void(0)" onclick="choseCompany('0099')" id="0099">中华联合报价</a></li>--%>
                    <li class="active"><a href="javascript:void(0)" onclick="choseCompany('0099')" id="0099">中华联合报价</a></li>
            </ul>
        </div><!--/.nav-collapse  -->
    </div>
</div>


<div class="container-fluid">
    <div class="row">
        <div class="col-md-6 col-xs-12">
            <form class="form-horizontal" role="form" id="form1">

                <div>
                    <input type="hidden" name="reqType" id="reqType" value="1"/>
                    <input type="hidden" name="ordertype" id="ordertype" value="G"/>
                    <input type="hidden" name="companyno" id="companyno"/>
                    <input type="hidden" name="nolicenseflag" id="nolicenseflag" value="0"/>
                    <input type="hidden" name="licenseno" id="licenseno"/>


                    <input type="hidden" name="remark9" id="remark9" value="-1"/>
                    <input type="hidden" name="remark10" id="remark10" value="-1"/>
                    <input type="hidden" name="remark11" id="remark11" value="-1"/>
                    <input type="hidden" name="remark12" id="remark12" value="-1"/>
                    <input type="hidden" name="remark15" id="remark15" value="-1"/>

                    <input type="hidden" name="step" id = "step" value="G"/>

                    <!--流水号-->
                    <input type="hidden" name="outerno" id="outerno"/>

                    <!--是否投保确认-->
                    <input type="hidden" name="configbeforejudge" id="configbeforejudge"/>

                    <input type="hidden" name="businesspremium" id="businesspremium"/>
                    <input type="hidden" name="vehicletaxamount" id="vehicletaxamount"/>
                    <input type="hidden" name="forcepremium" id="forcepremium"/>
                    <input type="hidden" name="realpremium" id="realpremium"/>
                    <input type="hidden" name="totalremium" id="totalremium"/>


                    <!--商业险告知单-->
                    <input type="hidden" name="bizInform" id="bizInform"/>
                    <!--交强险告知单-->
                    <input type="hidden" name="forceInform" id="forceInform"/>

                    <input type="hidden" name="paypath" id="paypath" />

                    <input type="hidden" name="sessionid" id="sessionid"/>
                </div>

                <div class="form-group">
                    <div class="col-sm-12 col-xs-12">
                        <label class="control-label">车辆行驶城市</label>
                    </div>
                    <div class="col-sm-6 col-xs-6">
                        <select name="parent_citycode" id="parent_citycode" class="form-control">
                        </select>
                    </div>
                    <div class="col-sm-6 col-xs-6">
                        <select name="citycode" id="citycode" class="form-control">
                            <option value="110100">北京市</option>
                        </select>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-sm-12 col-xs-12">
                        <label class="control-label">车牌号码  <span style='color:#F00'>*</span></label>
                    </div>
                    <div class="col-sm-3 col-xs-3">
                        <input type="text" class="form-control" name="licenseno1" id="licenseno1" placeholder = "沪A">
                    </div>
                    <div class="col-sm-4 col-xs-4">
                        <INPUT type="text" class="form-control" name="licenseno2" id="licenseno2">
                    </div>
                    <div class="col-sm-5 col-xs-5">
                        <input type="checkbox" name="checkbox_nolicenseflag" id="checkbox_nolicenseflag" onclick="onChangeLicenseNo()"/>
                        <em style="color:#000;">新车未上牌</em>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-sm-12 col-xs-12">
                        <label class="control-label">行驶证车主姓名
                        <span style='color:#F00'>*</span>
                        </label>
                    </div>
                    <div class="col-sm-12 col-xs-12">
                        <input type="text" class="form-control" name="ownername" id="ownername" maxlength="20" />
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-sm-12 col-xs-12">
                        <label class="control-label">车主身份证号
                            <span style='color:#F00'>*</span>
                        </label>
                    </div>
                    <div class="col-sm-12 col-xs-12">
                        <input type="text" class="form-control" name="ownercertno" id="ownercertno"
                               maxlength="18" size="20" pattern="^[1-9]([0-9]{14}|[0-9]{17})$"
                               placeholder="请输入15/18位身份证号码" required >
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-sm-12 col-xs-12">
                        <label class="control-label">被保险人姓名
                            <span style='color:#F00'>*</span>
                        </label>
                    </div>
                    <div class="col-sm-5 col-xs-5">
                        <input type="text" name="insuredperson" id="insuredperson"  disabled class="form-control"/>
                    </div>
                    <div class="col-sm-7 col-xs-7">
                        <input type="checkbox"  name="checkbox_isownername" id="checkbox_isownername"
                               onclick="onChangeIsOwnerName()"/> <em style="color:#000;">同行驶证车主姓名</em>
                    </div>
                </div>



                <!--新车,外地车必须提供 begin-->
                <div id="tr-firstregisterdate" style="display:none" class="form-group">
                    <div class="col-sm-12 col-xs-12">
                        <label class="control-label" id="td-firstregisterdate">注册登记日期
                            <span style='color:#F00'>*</span>
                        </label>
                    </div>
                    <div class="col-sm-12 col-xs-12">
                        <input type="date" name="firstregisterdate" id="firstregisterdate" class="form-control"/>
                    </div>
                </div>


                <div id="tr-vehiclemodelname" style="display:none" class="form-group">
                    <div class="col-sm-12 col-xs-12">
                        <label id="td-vehiclemodelname" class="control-label">车辆品牌 </label>
                    </div>
                    <div class="col-sm-12 col-xs-12">
                        <input type="text" name="vehiclebrand" id="vehiclebrand" class="form-control"
                               onClick="showSelectBrand();" readonly="readonly" value="选择车辆品牌" /><br/>
                        <input type="hidden" id="vehicleid" name="vehicleid" class="form-control" value=""/>
                        <input type="hidden" id="vehiclemodelname" name="vehiclemodelname" value="" class="form-control"/>
                        <div id="vehiclemodelnamedesc" style="color:black;"></div>
                    </div>
                </div>

                <div id="tr-vehicleframeno" style="display:none" class="form-group">
                    <div class="col-sm-12 col-xs-12">
                        <label id="td-vehicleframeno" class="control-label">车架号</label>
                    </div>
                    <div class="col-sm-12 col-xs-12">
                        <input type="text" name="vehicleframeno" id="vehicleframeno" class="form-control"
                                minlength="17" maxlength="17" />
                    </div>
                </div>

                <div id="tr-engineno" style="display:none" class="form-group">
                    <div class="col-sm-12 col-xs-12">
                        <label id="td-engineno" class="control-label">发动机号</label>
                    </div>
                    <div class="col-sm-12 col-xs-12">
                        <input type="text" name="engineno" id="engineno" maxlength="40" class="form-control"/>
                    </div>
                </div>

                <!--新车,外地车必须提供 over-->
                <div id="tr-vehicleinvoiceno" style="display:none" class="form-group">
                    <div class="col-sm-12 col-xs-12">
                        <label id="td-vehicleinvoiceno" class="control-label">购车发票号 </label>
                    </div>
                    <div class="col-sm-12 col-xs-12">
                        <input type="text" id="vehicleinvoiceno" name="vehicleinvoiceno" value="" maxlength="25" size="20" class="form-control"/>
                    </div>
                </div>


                <div id="tr-vehicleinvoicedate" style="display:none" class="form-group">
                    <div class="col-sm-12 col-xs-12">
                        <label class="control-label" id="td-vehicleinvoicedate">购车发票日期 </label>
                    </div>
                    <div class="col-sm-12 col-xs-12">
                        <input type="date" name="vehicleinvoicedate" id="vehicleinvoicedate" class="form-control"/>
                    </div>
                </div>


                <div id="tr-runcardcertificatedate" style="display:none" class="form-group">
                    <div class="col-sm-12 col-xs-12">
                        <label class="control-label" id="td-runcardcertificatedate">发证日期 </label>
                    </div>
                    <div class="col-sm-12 col-xs-12">
                        <input type="date" name="runcardcertificatedate" id="runcardcertificatedate" class="form-control"/>
                    </div>
                </div>


                <div id="tr-bizbegindate" style="display:none" class="form-group">
                    <div class="col-sm-12 col-xs-12">
                        <label class="control-label" id="td-bizbegindate">商业险起期</label>
                    </div>
                    <div class="col-sm-12 col-xs-12">
                        <input type="date" name="bizbegindate" id="bizbegindate" class="form-control"/>
                    </div>
                </div>

                <div id="tr-forcebegindate" style="display:none" class="form-group">
                    <div class="col-sm-12 col-xs-12">
                        <label class="control-label" id="td-forcebegindate">交强险起期 <span style='color:#F00'>*</span></label>
                    </div>
                    <div class="col-sm-12 col-xs-12">
                        <input type="date" name="forcebegindate" id="forcebegindate" class="form-control"/>
                    </div>
                </div>


                <div id="tr-taxvehicletype" style="display:none" class="form-group" >
                    <div class="col-sm-12 col-xs-12">
                        <label class="control-label" id="td-taxvehicletype">纳税车辆类型</label>
                    </div>
                    <div class="col-sm-12 col-xs-12">
                        <select id="taxvehicletype" name="taxvehicletype" class="form-control">
                        </select>
                    </div>
                </div>


                <div id="tr-fueltype" style="display:none" class="form-group">
                    <div class="col-sm-12 col-xs-12">
                        <label id="td-fueltype" class="control-label">能源种类</label>
                    </div>
                    <div class="col-sm-12 col-xs-12">
                        <select id="fueltype" name="fueltype" class="form-control">
                        </select>
                    </div>
                </div>

                <div id="tr-specialcarflag" style="display:none" class="form-group">
                    <div class="col-sm-12 col-xs-12">
                        <label id="td-specialcarflag" class="control-label">被保险人姓名</label>
                    </div>
                    <div class="col-sm-12 col-xs-12">
                        <select id="specialcarflag" name="specialcarflag" onchange="onSpecialcarFlag()" class="form-control">
                            <option value="1">是</option>
                            <option value="0">否</option>
                        </select>
                    </div>
                </div>


                <div id="tr-specialcardate" style="display:none" class="form-group">
                    <div class="col-sm-12 col-xs-12">
                        <label class="control-label" id="td-specialcardate">过户日期</label>
                    </div>
                    <div class="col-sm-12 col-xs-12">
                        <input type="date" name="specialcardate" id="specialcardate" class="form-control"/>
                    </div>
                </div>


                <div id="tr-persontaxcode" style="display:none" class="form-group">
                    <div class="col-sm-12 col-xs-12">
                        <label  id="td-persontaxcode" class="control-label">纳税人编码</label>
                    </div>
                    <div class="col-sm-12 col-xs-12">
                        <input type="text" id="persontaxcode" name="persontaxcode" value="" maxlength="30" size="20" class="form-control"/>
                    </div>
                </div>

                <div id="tr-taxticketno" style="display:none" class="form-group">
                    <div class="col-sm-12 col-xs-12">
                        <label id="td-taxticketno"  class="control-label">税票号</label>
                    </div>
                    <div class="col-sm-12 col-xs-12">
                        <input type="text" id="taxticketno" name="taxticketno" value="" maxlength="30" size="20" class="form-control"/>
                    </div>
                </div>


                <div id="tr-taxtickettype" style="display:none" class="form-group">
                    <div class="col-sm-12 col-xs-12">
                        <label id="td-taxtickettype" class="control-label">税票号类型</label>
                    </div>
                    <div class="col-sm-12 col-xs-12">
                        <select id="taxtickettype" name="taxtickettype" class="form-control">
                        </select>
                    </div>
                </div>

                <div id="tr-taxbureauname" style="display:none" class="form-group">
                    <div class="col-sm-12 col-xs-12">
                        <label id="td-taxbureauname" class="control-label">开具税务机关名称</label>
                    </div>
                    <div class="col-sm-12 col-xs-12">
                        <select id="taxbureauname" name="taxbureauname"  class="form-control">
                        </select>
                    </div>
                </div>

                <div id="tr-settledaddress" style="display:none" class="form-group">
                    <div class="col-sm-12 col-xs-12">
                        <label d="td-settledaddress" class="control-label">车辆落户地址</label>
                    </div>
                    <div class="col-sm-12 col-xs-12">
                        <select id="settledaddress" name="settledaddress" class="form-control">
                        </select>
                    </div>
                </div>

                <div id="tr-vehicleseats" style="display:none" class="form-group">
                    <div class="col-sm-12 col-xs-12">
                        <label id="td-vehicleseats"  class="control-label">车座数</label>
                    </div>
                    <div class="col-sm-12 col-xs-12">
                        <select id="vehicleseats" name="vehicleseats" class="form-control">
                        </select>
                    </div>
                </div>

                <div id="tr-averagemile" style="display:none" class="form-group">
                    <div class="col-sm-12 col-xs-12">
                        <label id="td-averagemile" class="control-label">年平均行驶里程</label>
                    </div>
                    <div class="col-sm-12 col-xs-12">
                        <select id="averagemile" name="averagemile" onchange="onRepeatGetSubjInfo()">
                        </select>
                    </div>
                </div>

                <div id="tr-trafficviolation" style="display:none" class="form-group">
                    <div class="col-sm-12 col-xs-12">
                        <label id="td-trafficviolation" class="control-label">交通违章记录</label>
                    </div>
                    <div class="col-sm-12 col-xs-12">
                        <select id="trafficviolation" name="trafficviolation" class="form-control"></select>
                    </div>
                </div>

                <div id="tr-bizquestion" style="display:none" class="form-group">
                    <div class="col-sm-12 col-xs-12">
                        <label class="control-label" id="td-bizquestion">商业险问题：</label>
                    </div>
                    <div class="col-sm-12 col-xs-12">
                        <input id="bizquestion" name="bizquestion" readonly class="form-control">
                    </div>
                 </div>

                <div id="tr-bizanswer" style="display:none" class="form-group">
                    <div class="col-sm-12 col-xs-12">
                        <label class="control-label" id="td-bizanswer">商业险问题答案</label>
                    </div>
                    <div class="col-sm-12 col-xs-12">
                        <input id="bizanswer" name="bizanswer"  class="form-control">
                    </div>
                </div>

                <div id="tr-forcequestion" style="display:none" class="form-group">
                    <div class="col-sm-12 col-xs-12">
                        <label id="td-forcequestion" class="control-label">交强险问题</label>
                    </div>
                    <div class="col-sm-12 col-xs-12">
                        <input id="forcequestion" name="forcequestion" readonly class="form-control">
                    </div>
                </div>

                <div id="tr-forceanswer" style="display:none" class="form-group">
                    <div class="col-sm-12 col-xs-12">
                        <label id="td-forceanswer" class="control-label">交强险问题答案</label>
                    </div>
                    <div class="col-sm-12 col-xs-12">
                        <input id="forceanswer" name="forceanswer" class="form-control">
                    </div>
                </div>

                <div id="tr-vehiclecode" style="display:none" class="form-group">
                    <div class="col-sm-12 col-xs-12">
                        <label class="control-label" id="td-vehiclecode">二次选择车型</label>
                    </div>
                    <div class="col-sm-12 col-xs-12">
                        <select id="vehiclecode" name="vehiclecode" class="form-control">
                        </select>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-sm-12 col-xs-12">
                        <label class="control-label">保单生效日 <span style='color:#F00'>*</span></label>
                    </div>
                    <div class="col-sm-12 col-xs-12">
                        <input type="date" id="plcstartdate" name="plcstartdate" min="" class="form-control"/>
                    </div>
                </div>


                <div id="tr-riskInfo1" style="display: none" class="form-group">
                    <div class="col-sm-12 col-xs-12">
                        <label class="control-label">强制保险</label>
                    </div>
                    <div class="col-sm-12 col-xs-12">

                    </div>
                </div>

                <div id="tr-trafficinsurance" desc="交强险" style="display:none" class="form-group">
                    <div class="col-sm-12 col-xs-12">
                        <label id="td-trafficinsurance" class="control-label">交强险</label>
                    </div>
                    <div class="col-sm-12 col-xs-12">
                        <select name="trafficinsurance" id="trafficinsurance"
                                onchange="onChangeRisk()" class="form-control">
                        </select>
                    </div>
                </div>


                <div id="tr-traveltax" desc="车船税" style="display:none" class="form-group">
                    <div class="col-sm-12 col-xs-12">
                        <label class="control-label" id="td-traveltax">车船税</label>
                    </div>
                    <div class="col-sm-12 col-xs-12">
                        <select name="traveltax" size="1" id="traveltax" onchange="onChangeRisk()" class="form-control">
                        </select>
                    </div>
                </div>

                <div id="tr-riskInfo2" style="display: none" class="form-group">
                    <div class="col-sm-12 col-xs-12">
                        <label class="control-label">商业保险</label>
                    </div>
                </div>

                <div id="tr-remark1" style="display: none" class="form-group">
                    <div class="col-sm-12 col-xs-12">
                        <label id="td-remark1"  class="control-label">车辆损失险</label>
                    </div>
                    <div class="col-sm-5 col-xs-5">
                        <select name="remark1" id="remark1" class="form-control">
                            <option value="-1">不投保</option>
                            <option value="300000">300000</option>
                        </select>
                    </div>
                    <div class="col-sm-7 col-xs-7">
                        <input type="checkbox" name="checkbox_remark9" id="checkbox_remark9">
                        <em>含不计免赔</em>
                    </div>
                </div>

                <div id="tr-remark2" desc="商业第三者责任险" style="display:none" class="form-group">
                    <div class="col-sm-12 col-xs-12">
                        <label id="td-remark2" class="control-label">商业第三者责任险</label>
                    </div>
                    <div class="col-sm-5 col-xs-5">
                        <select name="remark2" size="1" id="remark2" class="form-control"></select>
                    </div>
                    <div class="col-sm-7 col-xs-7">
                        <input type="checkbox" name="checkbox_remark10" id="checkbox_remark10">
                        <em>含不计免赔</em>
                    </div>
                </div>

                <div id="tr-remark3" desc="全车盗抢险" style="display:none" class="form-group">
                    <div class="col-sm-12 col-xs-12">
                        <label id="td-remark3"  class="control-label">全车盗抢险</label>
                    </div>
                    <div class="col-sm-5 col-xs-5">
                        <select name="remark3" size="1" id="remark3" class="form-control">
                        </select>
                    </div>
                    <div class="col-sm-7 col-xs-7">
                        <input type="checkbox" name="checkbox_remark11" id="checkbox_remark11">
                        <em>含不计免赔</em>
                    </div>
                </div>


                <div id="tr-remark4" desc="司机座位责任险" style="display:none" class="form-group">
                    <div class="col-sm-12 col-xs-12">
                        <label id="td-remark4" class="control-label">司机座位责任险</label>
                    </div>
                    <div class="col-sm-5 col-xs-5">
                        <select name="remark4" size="1" id="remark4" class="form-control">
                        </select>
                    </div>
                    <div class="col-sm-7 col-xs-7">
                        <input type="checkbox" name="checkbox_remark12" id="checkbox_remark12">
                        <em>含不计免赔</em>
                    </div>
                </div>


                <div id="tr-remark5" style="display:none" class="form-group">
                    <div class="col-sm-12 col-xs-12">
                        <label id="td-remark5" class="control-label">乘客座位责任险</label>
                    </div>
                    <div class="col-sm-5 col-xs-5">
                        <select name="remark5" size="1" id="remark5" class="form-control">
                        </select>
                    </div>
                    <div class="col-sm-7 col-xs-7">
                        <input type="checkbox" name="checkbox_remark15" id="checkbox_remark15">
                        <em>含不计免赔</em>
                    </div>
                </div>


                <div id="tr-remark6" style="display:none" class="form-group">
                    <div class="col-sm-12 col-xs-12">
                        <label id="td-remark6"  class="control-label">自燃损失险</label>
                    </div>
                    <div class="col-sm-12 col-xs-12">
                        <select name="remark6" size="1" id="remark6" class="form-control">
                        </select>
                    </div>
                </div>


                <div id="tr-remark13" desc="涉水行驶损失险" style="display:none" class="form-group">
                    <div class="col-sm-12 col-xs-12">
                        <label id="td-remark13"  class="control-label">涉水行驶损失险</label>
                    </div>
                    <div class="col-sm-12 col-xs-12">
                        <select name="remark13" size="1" id="remark13" class="form-control">
                        </select>
                    </div>
                </div>


                <div id="tr-remark7" desc="车身划痕险" style="display:none" class="form-group">
                    <div class="col-sm-12 col-xs-12">
                        <label  id="td-remark7"  class="control-label">车身划痕险</label>
                    </div>
                    <div class="col-sm-12 col-xs-12">
                        <select name="remark7" size="1" id="remark7" class="form-control">
                        </select>
                    </div>
                </div>


                <div id="tr-remark14" desc="附加险不计免赔" style="display:none" class="form-group">
                    <div class="col-sm-12 col-xs-12">
                        <label id="td-remark14" class="control-label">附加险不计免赔</label>
                    </div>
                    <div class="col-sm-12 col-xs-12">
                        <select name="remark14" size="1" id="remark14" class="form-control">
                        </select>
                    </div>
                </div>


                <div id="tr-remark8" desc="玻璃单独破碎险" style="display:none" class="form-group">
                    <div class="col-sm-12 col-xs-12">
                        <label id="td-remark8" class="control-label">玻璃单独破碎险</label>
                    </div>
                    <div class="col-sm-12 col-xs-12">
                        <select name="remark8" size="1" id="remark8" class="form-control">
                        </select>
                    </div>
                </div>


                <div id="tr-bjbutton" class="form-group" align="center">
                    <div class="col-sm-12 col-xs-12">
                        <input type="button"  class="btn btn-primary" name="bjbutton" id="bjbutton" value="获取报价/承保方案"/></td>
                    </div>
                </div>


                <!--报价信息-->
                <div id="preminfo"  class="form-group" style="display:none">
                    <table width="100%" border="0" cellpadding="0" cellspacing="0">
                        <tr>
                            <td width="21%" colspan="7" align="center" height="30" style="background-color: lightgrey">
                                <strong>保费合计</strong>
                            </td>
                        </tr>
                        <tr>
                            <td align="center"><strong>商业险</strong></td>
                            <td align="center">&nbsp;</td>
                            <td align="center"><strong>车船税</strong></td>
                            <td align="center">&nbsp;</td>
                            <td align="center"><strong>交强险</strong></td>
                            <td align="center">&nbsp;</td>
                            <td align="center"><strong>优惠价</strong></td>
                        </tr>
                        <tr align="center" id="premiumTotalSpan" style="height: 35px;">
                            <td><span id="span_businesspremium" name="span_businesspremium"></span></td>
                            <td>+</td>
                            <td><span id="span_vehicletaxamount" name="span_vehicletaxamount"></span></td>
                            <td>+</td>
                            <td><span id="span_forcepremium" name="span_forcepremium"></span></td>
                            <td>=</td>
                            <td><span id="span_realpremium" name="span_realpremium"></span>元</td>
                        </tr>
                        <tr>
                            <td colspan="6" align="center">
                              <span>原价：
                                <span id="span_totalremium" name="span_totalremium"></span>&nbsp;元&nbsp; 为您省去
                                <span id="discount1">0</span> 元
                              </span>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="7" align="center" style="height: 45px">
                                <input type="button" name="buyproduct" id="buyproduct" class="btn btn-primary" value="现在就申请出保单"/>
                            </td>
                        </tr>
                    </table>
                </div>


                <div id="content" class="popupsContent">
                    <div class="form-group">
                        <div class="col-sm-12 col-xs-12">
                            <label class="control-label">车主信息</label>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-sm-12 col-xs-12">
                            <label class="control-label">姓名</label><span style='color:#F00'>*</span>
                        </div>
                        <div class="col-sm-12 col-xs-12">
                            <input type="text" id="name" value="name" class="form-control" disabled/>
                        </div>
                    </div>


                    <div class="form-group">
                        <div class="col-sm-12 col-xs-12">
                            <label class="control-label">证件类型</label><span style='color:#F00'>*</span>
                        </div>
                        <div class="col-sm-12 col-xs-12">
                            <select id="certtype" name="certtype" class="form-control" disabled>
                                <option value="1">居民身份证</option>
                                <option value="2">军官证</option>
                                <option value="3">护照</option>
                                <option value="5">驾驶证</option>
                                <option value="4">港澳回乡证或台胞证  </option>
                                <option value="99">其他</option>
                            </select>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-sm-12 col-xs-12">
                            <label class="control-label">证件号码</label><span style='color:#F00'>*</span>
                        </div>
                        <div class="col-sm-12 col-xs-12">
                            <input type="text" id="certTypeNo" name="certTypeNo" class="form-control" disabled/>
                        </div>
                    </div>


                    <div class="form-group">
                        <div class="col-sm-12 col-xs-12">
                            <label class="control-label">被保人信息</label>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-sm-12 col-xs-12">
                            <label class="control-label">姓名</label> <span style='color:#F00'>*</span>
                        </div>
                        <div class="col-sm-12 col-xs-12">
                            <input type="text" id="insurInfo_name" class="form-control"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-sm-12 col-xs-12">
                            <label class="control-label">证件类型：</label><span style='color:#F00'>*</span>
                        </div>
                        <div class="col-sm-12 col-xs-12">
                            <select id="insurInfo_certtype" name="insurInfo_certtype" class="form-control">
                                <option value="1">居民身份证</option>
                                <option value="2">军官证</option>
                                <option value="3">护照</option>
                                <option value="5">驾驶证</option>
                                <option value="4">港澳回乡证或台胞证  </option>
                                <option value="99">其他</option>
                            </select>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-sm-12 col-xs-12">
                            <label class="control-label">证件号码</label><span style='color:#F00'>*</span>
                        </div>
                        <div class="col-sm-12 col-xs-12">
                            <input type="text" id="insurInfo_certno" name="insurInfo_certno" class="form-control"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-sm-12 col-xs-12">
                            <label class="control-label">性别</label><span style='color:#F00'>*</span>
                        </div>
                        <div class="col-sm-12 col-xs-12">
                            <select id="insurInfo_sex" name="insurInfo_sex" class="form-control">
                                <option value="M">男</option>
                                <option value="F">女</option>
                            </select>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-sm-12 col-xs-12">
                            <label class="control-label">出生日期</label><span style='color:#F00'>*</span>
                        </div>
                        <div class="col-sm-12 col-xs-12">
                            <input type="date" id="insurInfo_birth" name="insurInfo_birth" class="form-control"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-sm-4 col-xs-4">
                            <label class="control-label">投保人信息</label>
                        </div>
                        <div class="col-sm-4 col-xs-4">
                            <input type="checkbox" id="infoAsOwner" name="infoAsOwner" checked>同车主
                        </div>
                        <div class="col-sm-4 col-xs-4">
                            <input type="checkbox" id="infoAsInsur" name="infoAsInsur">同被保人
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-sm-12 col-xs-12">
                            <label class="control-label">姓名</label><span style='color:#F00'>*</span>
                        </div>
                        <div class="col-sm-12 col-xs-12">
                            <input type="text" id="aplInfo_name" name="aplInfo_name" class="form-control"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-sm-12 col-xs-12">
                            <label class="control-label">性别</label><span style='color:#F00'>*</span>
                        </div>
                        <div class="col-sm-12 col-xs-12">
                            <select id="aplInfo_sex" name="aplInfo_sex" class="form-control">
                                <option value="M">男</option>
                                <option value="F">女</option>
                            </select>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-sm-12 col-xs-12">
                            <label class="control-label">证件类型</label><span style='color:#F00'>*</span>
                        </div>
                        <div class="col-sm-12 col-xs-12">
                            <select id="aplInfo_certtype" name="aplInfo_certtype" class="form-control">
                                <option value="1">居民身份证</option>
                                <option value="2">军官证</option>
                                <option value="3">护照</option>
                                <option value="5">驾驶证</option>
                                <option value="4">港澳回乡证或台胞证  </option>
                                <option value="99">其他</option>
                            </select>
                        </div>
                    </div>


                    <div class="form-group">
                        <div class="col-sm-12 col-xs-12">
                            <label class="control-label">证件号码</label><span style='color:#F00'>*</span>
                        </div>
                        <div class="col-sm-12 col-xs-12">
                            <input type="text" id="aplInfo_certno" name="aplInfo_certno" class="form-control"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-sm-12 col-xs-12">
                            <label class="control-label">出生日期</label><span style='color:#F00'>*</span>
                        </div>
                        <div class="col-sm-12 col-xs-12">
                            <input type="date" id="aplInfo_birth" name="aplInfo_birth" class="form-control"/>
                        </div>
                    </div>

                <%--    <div class="form-group">
                        <div class="col-sm-12 col-xs-12">
                            <label class="control-label">email</label><span style='color:#F00'>*</span>
                        </div>
                        <div class="col-sm-12 col-xs-12">
                            <input type="email" id="aplInfo_email" name="aplInfo_email" class="form-control"/>
                        </div>
                    </div>--%>

                    <div class="form-group">
                        <div class="col-sm-12 col-xs-12">
                            <label class="control-label">配送信息</label>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-sm-12 col-xs-12">
                            <label class="control-label">联系人</label><span style='color:#F00'>*</span>
                        </div>
                        <div class="col-sm-12 col-xs-12">
                            <input type="text" id="linkInfo_name" name="linkInfo_name" class="form-control"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-sm-12 col-xs-12">
                            <label class="control-label">手机</label> <span style='color:#F00'>*</span>
                        </div>
                        <div class="col-sm-12 col-xs-12">
                            <input type="text" id="linkInfo_mobile" name="linkInfo_mobile" class="form-control"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-sm-12 col-xs-12">
                            <label class="control-label">email</label><span style='color:#F00'>*</span>
                        </div>
                        <div class="col-sm-12 col-xs-12">
                            <input type="email" id="linkInfo_email" name="linkInfo_email" class="form-control"/>
                        </div>
                    </div>


                    <div class="form-group">
                        <div class="col-sm-12 col-xs-12">
                            <label class="control-label">邮编</label><span style='color:#F00'>*</span>
                        </div>
                        <div class="col-sm-12 col-xs-12">
                            <input type="text" id="linkInfo_zipcode" name="linkInfo_zipcode" class="form-control"/>
                        </div>
                    </div>


                    <div class="form-group">
                        <div class="col-sm-12 col-xs-12">
                            <label class="control-label">地址</label><span style='color:#F00'>*</span>
                        </div>
                        <div class="col-sm-12 col-xs-12">
                            <input type="text" id="linkInfo_address" name="linkInfo_address" class="form-control"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-sm-12 col-xs-12">
                            <label class="control-label">发票抬头</label>
                        </div>
                        <div class="col-sm-12 col-xs-12">
                            <input type="text" id="linkInfo_invoice" name="linkInfo_invoice" class="form-control"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-sm-12 col-xs-12">
                            <label class="control-label">支付方式</label>
                        </div>
                        <div class="col-sm-12 col-xs-12">
                            <select id="linkInfo_realpaymode" name="linkInfo_realpaymode" class="form-control">
                                <option value="2">现金</option>
                                <option value="3" selected>POS机</option>
                            </select>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-sm-12 col-xs-12" align="center">
                            <input type="button" id = "buyButton" name="buyButton" class="btn btn-primary"  value="购买"/>
                            <input type="button" id="buyCancel" name="buyCancel" class="btn btn-primary"  value="返回"/>
                        </div>
                    </div>

                </div>
            </form>
        </div>
    </div>
</div>


<!--确认页面-->
<div id="confirmContent" style="display:none" class="form-horizontal popupsContent">
    <div class="form-group">
        <div class="col-sm-12 col-xs-12" align="center">
            <label class="control-label">请阅读投保提示及条款，并确认告知单内容</label>
        </div>
        <div class="col-sm-12 col-xs-12" align="center">
            <input type="button" name="bizInformBtn" id="bizInformBtn" class="btn btn-default" value="商业险告知单"/>
        </div>
        <div class="col-sm-12 col-xs-12" align="center">
            <input type="button" name="appntBtn" id="appntBtn" class="btn btn-default" value="交强险告知单"/>
        </div>
    </div>

    <div class="form-group">
        <div class="col-sm-12 col-xs-12" align="left">
            <label class="control-label">特别约定</label>
        </div>
        <div class="col-sm-12 col-xs-12">
            <textarea id="appnt" name="appnt" class="form-control" rows="3" cols="80"></textarea>
        </div>
    </div>

    <div class="form-group">
        <div class="col-sm-12 col-xs-12" align="left">
            <label class="control-label">投保声明</label>
        </div>
        <div class="col-sm-12 col-xs-12">
            <textarea id="remark" name="remark" class="form-control" rows="3"></textarea>
        </div>
    </div>

    <div>
        <div class="col-sm-12 col-xs-12" align="center">
            <input type="button" name="applyBuybtn" id="applyBuybtn" class="btn btn-primary"  value="确认"/>
        </div>
    </div>
</div>


<!--获取车型页面-->
<div id="carTypeDiv" class="popupsContent">
    <table border="0" cellpadding="0" cellspacing="0" class="table table-condensed">
        <tr>
            <td  style="border: none">
                <label class="control-label">车型信息</label>
            </td>
            <td align="right" style="border:none;display: none" id="td-selectCarType">
                <input type="button" id="selectCarTypeBtn" name="selectCarTypeBtn" class="btn btn-primary btn-sm" value="确认选择"/>
            </td>
        </tr>
        <tr>
            <td>
                车辆品牌
            </td>
            <td>
                <input type="text" id="brand" name="brand"  class="form-control"/>
            </td>
        </tr>
        <tr>
            <td>
                车架号
            </td>
            <td>
                <input type="text" id="chejiahao" name="chejiahao" class="form-control"/>
            </td>
        </tr>

        <tr>
            <td>
                发动机号
            </td>
            <td>
                <input type="text" id="fadongji" name="fadongji" class="form-control" />
            </td>
        </tr>
        <tr>
            <td colspan="2" align="center">
                <input type="button" name="queryCarTypeBtn" id="queryCarTypeBtn" class="btn btn-primary" value="查询车型" />
                <input type="button" name="cancleQueryCar" id="cancleQueryCar" class="btn btn-warning" value="取消"/>
            </td>
        </tr>
        <tr id="tr-cartypeselect" style="display: none">
            <td colspan="2">
                <select id="cartypeselect" name="cartypeselect" class="form-control"></select>
            </td>
        </tr>
    </table>
</div>

<form id="payForm" name="payForm" method="POST"></form>

<div id="background" class="popupsBackground"></div>
<script src="${ctx}/ejsino/jquery-1.11.1.min.js"></script>
<script src="${ctx}/ejsino/bootstrap.min.js"></script>
<script src="${ctx}/ejsino/jquery.form.js"></script>
<script src="${ctx}/ejsino/ejsino.js"></script>
</body>
</html>
