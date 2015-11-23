package com.daoke.mobileserver.ejsino.controller;

import com.daoke.mobileserver.common.model.CommonJsonResult;
import com.daoke.mobileserver.ejsino.*;
import com.daoke.mobileserver.ejsino.model.EjsinoCityInfo;
import com.daoke.mobileserver.ejsino.model.EjsinoFormModel;
import com.daoke.mobileserver.ejsino.model.EjsinoCommonResult;
import com.daoke.mobileserver.ejsino.model.request._Req_PackageList;
import com.daoke.mobileserver.ejsino.model.response.*;
import com.daoke.mobileserver.ejsino.service.IEjsinoService;
import com.daoke.mobileserver.util.JsonMapper;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by wangzp on 2014/12/11.
 */
@Controller
public class EjsinoController {

    private  static Logger log = LoggerFactory.getLogger(EjsinoController.class) ;

    @Autowired
    private IEjsinoService ejsinoService;


    @RequestMapping(value = "/ejsino",method = RequestMethod.GET)
    public String car(){
        return "ejsino";
    }



    @RequestMapping(value = "/offer_elements_query", method = RequestMethod.POST)
    public void offerElementsQuery(HttpServletRequest request,
                                     HttpServletResponse response,
                                     EjsinoFormModel eCarFormModel) throws Exception{
        PrintWriter out = null;
        _Req_PackageList req_PackageList = null;
        EjsinoCommonResult result = new EjsinoCommonResult();
        try {
            out = response.getWriter();

            //记录e车险表单信息
             if(eCarFormModel != null && StringUtils.isNotBlank(eCarFormModel.getOuterno())){
                 if(ejsinoService.getEjsinoCountByOuterno(eCarFormModel.getOuterno()) <1){
                     ejsinoService.insertEjsinoInfo(eCarFormModel);
                 } else{
                     ejsinoService.updateEjsinoInfo(eCarFormModel);
                 }
             }

            req_PackageList = FormUtil.convertFormModelToRequestModel(eCarFormModel);

            log.warn("req_PackageList:" + req_PackageList);

            String requestXml = JaxbUtil.object2Xml(req_PackageList, "GBK");
            log.warn("requestXml:" + requestXml);

            String reponseXml = ECarHttpRequestUtil.sendPost(EjsinoConfig.API_URL, requestXml);
            _Res_PackageList res_packageList = JaxbUtil.xml2JavaBean(reponseXml, _Res_PackageList.class);

            int statuscode = res_packageList.get_package().getHeader().getStatuscode();


            response.setCharacterEncoding("UTF-8");

            if (EjsinoConstants.ResponseStatus.FAIL == statuscode) {
                String message = res_packageList.get_package().getHeader().getMessage();
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                result.setMessage(message);
                result.setResultCode(EjsinoConstants.ResponseStatus.FAIL);

            } else if (EjsinoConstants.ResponseStatus.COMPLETE_ELEMENTS == statuscode){
                List<_Res_Tag> tagList = new ArrayList<_Res_Tag>();

                boolean riskInfoFlag = false;

                List<_Res_Tag> carSubjList = res_packageList.get_package().getResponse().getTagsCarSubjList();
                if (carSubjList != null && carSubjList.size() > 0){

                    tagList.addAll(carSubjList);
                }

                List<_Res_Tag> carRiskList = res_packageList.get_package().getResponse().getTagsCarRiskList();
                if (carRiskList != null && carRiskList.size() > 0){
                    tagList.addAll(carRiskList);

                    riskInfoFlag = true;
                }

                List<Object> tags = FormUtil.covertToTagList(tagList);

                String step = EjsinoConstants.RequestTypes.OFFER_ELEMENTS_QUERY;

                result.setTagList(tags);
                result.setMessage("成功");
                result.setBtnText(riskInfoFlag ? "精确试算报价" : "获取报价/承保方案");
                result.setRiskInfoFlag(riskInfoFlag ? 1 : 0);
                result.setStep(step = riskInfoFlag ? EjsinoConstants.RequestTypes.OFFERING_LETTER : step);
                result.setResultCode(EjsinoConstants.ResponseStatus.SUCCESS);

            } else if (EjsinoConstants.ResponseStatus.SUCCESS == statuscode) {
                _Res_PremInfo _res_premInfo = res_packageList.get_package().getResponse().getPremInfo();
                _Res_InFrom _res_inFrom = res_packageList.get_package().getResponse().getInFrom();

                response.setStatus(HttpServletResponse.SC_OK);
                result.setPremInfo(_res_premInfo);
                result.setInFrom(_res_inFrom);
                result.setTagList(null);
                result.setMessage("成功");
                result.setBtnText("test");
                result.setRiskInfoFlag(1);
                result.setStep(EjsinoConstants.RequestTypes.SURANCE_CONFIRMATION);
                result.setResultCode(EjsinoConstants.ResponseStatus.SUCCESS);
            }

        } catch (Exception e) {
            e.printStackTrace();
            result.setMessage("操作异常");
            result.setResultCode(EjsinoConstants.ResponseStatus.FAIL);
        }
        String json = JsonMapper.toJson(result, false);
        out.print(json.toString());

        out.flush();
        out.close();
    }


    /**
     * 投保确认/申请购买
     * @param request
     * @param response
     * @param eCarFormModel
     * @throws IOException
     */
    @RequestMapping(value = "/surance_confirmation", method = RequestMethod.POST)
    public void suranceConfirmation(HttpServletRequest request,
                                     HttpServletResponse response,
                                     EjsinoFormModel eCarFormModel) throws IOException{

        PrintWriter out = response.getWriter();
        _Req_PackageList req_PackageList = null;
        EjsinoCommonResult result = new EjsinoCommonResult();
        try {


            //记录e车险表单信息
            if(eCarFormModel != null && StringUtils.isNotBlank(eCarFormModel.getOuterno())){
                    ejsinoService.updateEjsinoInfo(eCarFormModel);
            }else{
                log.error("输入参数错误");
            }
            /**
             * 是否需要投保确认;
             如果需要投保确认,请先调用投保确认接口,在调用申请购买接口;
             如果不需要,直接调用申请购买接口;
             */
            if (StringUtils.equalsIgnoreCase("Y",eCarFormModel.getConfigbeforejudge())) {
                eCarFormModel.setOrdertype(EjsinoConstants.RequestTypes.SURANCE_CONFIRMATION);
                eCarFormModel.setStep(EjsinoConstants.RequestTypes.SURANCE_CONFIRMATION);
            } else {
                eCarFormModel.setOrdertype(EjsinoConstants.RequestTypes.APPLY_BUY);
                eCarFormModel.setStep(EjsinoConstants.RequestTypes.APPLY_BUY);
            }

            req_PackageList = FormUtil.convertFormModelToRequestModel(eCarFormModel);
            String requestXml = JaxbUtil.object2Xml(req_PackageList, "GBK");
            String reponseXml = ECarHttpRequestUtil.sendPost(EjsinoConfig.API_URL, requestXml);
            _Res_PackageList res_packageList = JaxbUtil.xml2JavaBean(reponseXml, _Res_PackageList.class);

            int statuscode = res_packageList.get_package().getHeader().getStatuscode();

            response.setCharacterEncoding("UTF-8");

            if (EjsinoConstants.ResponseStatus.FAIL == statuscode) {
                String message = res_packageList.get_package().getHeader().getMessage();
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                result.setMessage(message);
                result.setResultCode(EjsinoConstants.ResponseStatus.FAIL);

            } else if (EjsinoConstants.ResponseStatus.SUCCESS == statuscode) {
                _Res_InForm _res_inForm = res_packageList.get_package().getResponse().getInForm();

                if(StringUtils.equalsIgnoreCase("Y",eCarFormModel.getConfigbeforejudge())) {
                    result.setInForm(_res_inForm);
                    result.setPremInfo(null);
                    result.setTagList(null);
                    result.setMessage("成功");
                    result.setRiskInfoFlag(1);
                    response.setStatus(HttpServletResponse.SC_OK);
                    result.setStep(EjsinoConstants.RequestTypes.APPLY_BUY);
                    result.setResultCode(EjsinoConstants.ResponseStatus.SUCCESS);
                } else {
                    _Res_PlcInfo _res_plcInfo = res_packageList.get_package().getResponse().getPlcInfo();
                    pay(request, response, eCarFormModel);
                    return;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.setMessage("操作异常");
            response.setStatus(HttpServletResponse.SC_OK);
            result.setResultCode(EjsinoConstants.ResponseStatus.FAIL);
        }

        String json = JsonMapper.toJson(result, false);
        out.print(json.toString());
        out.flush();
        out.close();
    }


    /**
     * 支付
     * @param request
     * @param response
     */
    @RequestMapping("/pay")
    public void pay(HttpServletRequest request, HttpServletResponse response, EjsinoFormModel eCarFormModel) throws  IOException{

        PrintWriter out = response.getWriter();

        _Req_PackageList req_PackageList = null;

        EjsinoCommonResult result = new EjsinoCommonResult();

        try {

            eCarFormModel.setOrdertype(EjsinoConstants.RequestTypes.PAY);
            eCarFormModel.setStep(EjsinoConstants.RequestTypes.PAY);

            req_PackageList = FormUtil.convertFormModelToRequestModel(eCarFormModel);
            String requestXml = JaxbUtil.object2Xml(req_PackageList, "GBK");
            String reponseXml = ECarHttpRequestUtil.sendPost(EjsinoConfig.API_URL, requestXml);
            _Res_PackageList res_packageList = JaxbUtil.xml2JavaBean(reponseXml, _Res_PackageList.class);

            int statuscode = res_packageList.get_package().getHeader().getStatuscode();

            response.setCharacterEncoding("UTF-8");

            if (EjsinoConstants.ResponseStatus.FAIL == statuscode) {
                result.setResultCode(EjsinoConstants.ResponseStatus.FAIL);
                result.setMessage(res_packageList.get_package().getHeader().getMessage());
            }  else if (EjsinoConstants.ResponseStatus.SUCCESS == statuscode) {
                _Res_PayURL payURL = res_packageList.get_package().getResponse().getPayURL();

                result.setPayURL(payURL);
                result.setInForm(null);
                result.setPremInfo(null);
                result.setTagList(null);
                result.setMessage("成功");
                result.setRiskInfoFlag(1);
                response.setStatus(HttpServletResponse.SC_OK);
                result.setResultCode(EjsinoConstants.ResponseStatus.SUCCESS);
                result.setStep(EjsinoConstants.RequestTypes.PAY);
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.setMessage("操作异常");
            response.setStatus(HttpServletResponse.SC_OK);
            result.setResultCode(EjsinoConstants.ResponseStatus.FAIL);
        }

        String json = JsonMapper.toJson(result, false);
        out.print(json.toString());
        out.flush();
        out.close();
    }


    /**
     * 城市查询
     * @param request
     * @param response
     * @param cityCode
     * @throws IOException
     */
    @RequestMapping("/query_city")
    public void queryCity(HttpServletRequest request, HttpServletResponse response,
                          @RequestParam(required = false) String cityCode) throws IOException{


        List<EjsinoCityInfo> cityList = null;
        PrintWriter out = response.getWriter();

        CommonJsonResult jsonResult = new CommonJsonResult();
        try {

            if (StringUtils.isNotBlank(cityCode)) {
                Map<Integer, List<EjsinoCityInfo>> map = this.ejsinoService.getCityMap();
                if (null != map){
                    cityList = map.get(Integer.valueOf(cityCode));
                }
            } else {
                cityList = this.ejsinoService.getParentCityList();
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            jsonResult.setERRORCODE("1");
        }

        jsonResult.setERRORCODE("0");
        jsonResult.setRESULT(cityList);

        String json = JsonMapper.toJson(jsonResult, false);

        out.print(json.toString());
        out.flush();
        out.close();
    }



    @RequestMapping("/queryCarModel")
    public void queryCarType (HttpServletRequest request, HttpServletResponse response,
                              EjsinoFormModel eCarFormModel) throws IOException{

        PrintWriter out = response.getWriter();
        _Req_PackageList req_PackageList = null;
        EjsinoCommonResult result = new EjsinoCommonResult();
        String reponseJson = "";
        try {
            eCarFormModel.setOrdertype(EjsinoConstants.RequestTypes.CAR_MODEL_QUERY);
            eCarFormModel.setStep(EjsinoConstants.RequestTypes.CAR_MODEL_QUERY);

            req_PackageList = FormUtil.convertFormModelToRequestModel(eCarFormModel);
            String requestXml = JaxbUtil.object2Xml(req_PackageList, "GBK");
            reponseJson = ECarHttpRequestUtil.sendPost(EjsinoConfig.API_URL, requestXml);

        } catch (Exception e) {
            e.printStackTrace();
            result.setMessage("操作异常");
            response.setStatus(HttpServletResponse.SC_OK);
            result.setResultCode(EjsinoConstants.ResponseStatus.FAIL);
        }

        out.print(reponseJson);
        out.flush();
        out.close();




    }











}
