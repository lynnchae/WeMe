package com.daoke.mobileserver.ejsino;

import com.daoke.mobileserver.ejsino.model.EjsinoFormModel;
import com.daoke.mobileserver.ejsino.model.request.*;
import com.daoke.mobileserver.ejsino.model.response._Res_Definition;
import com.daoke.mobileserver.ejsino.model.response._Res_Tag;
import com.daoke.mobileserver.util.ConstantsUtil;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by wangzp on 2014/12/10.
 * 封装form表单提交的参数
 */
public class FormUtil {


    /**
     * 将form表单提交的参数封装为请求实体对象
     * @param form
     * @return
     * @throws Exception
     */
    public static _Req_PackageList convertFormModelToRequestModel(EjsinoFormModel form) throws Exception{
        _Req_PackageList packageList = new _Req_PackageList();

        _Req_Package req_package = new _Req_Package();

        // =========== start _Req_Header  ===========
        _Req_Header req_header =  new _Req_Header();

        String step = form.getStep() != null ? form.getStep() : EjsinoConstants.RequestTypes.OFFER_ELEMENTS_QUERY;

        if (EjsinoConstants.RequestTypes.OFFER_ELEMENTS_QUERY.equalsIgnoreCase(step)) {

            req_header.setOrdertype(EjsinoConstants.RequestTypes.OFFER_ELEMENTS_QUERY);
        } else if (EjsinoConstants.RequestTypes.OFFERING_LETTER.equalsIgnoreCase(step)) {

            req_header.setOrdertype(EjsinoConstants.RequestTypes.OFFERING_LETTER);
        } else if (EjsinoConstants.RequestTypes.APPLY_BUY.equalsIgnoreCase(step)) {

            req_header.setOrdertype(EjsinoConstants.RequestTypes.APPLY_BUY);
        } else if (EjsinoConstants.RequestTypes.CAR_MODEL_QUERY.equalsIgnoreCase(step)) {

            req_header.setOrdertype(EjsinoConstants.RequestTypes.CAR_MODEL_QUERY);
        } else if (EjsinoConstants.RequestTypes.SURANCE_CONFIRMATION.equalsIgnoreCase(step)) {

            req_header.setOrdertype(EjsinoConstants.RequestTypes.SURANCE_CONFIRMATION);
        } else if (EjsinoConstants.RequestTypes.PAY.equals(step)){

            req_header.setOrdertype(EjsinoConstants.RequestTypes.PAY);
        } else {

            req_header.setOrdertype(EjsinoConstants.RequestTypes.OFFER_ELEMENTS_QUERY);
        }

        req_header.setPassword(EjsinoConfig.PASSWORD);
        req_header.setUsername(EjsinoConfig.USERNAME);
        req_header.setVersion(EjsinoConfig.VERSION);
        req_header.setOuterno(form.getOuterno());
        req_package.setHeader(req_header);

        // =========== end  _Req_Header  ===========

        _Req_Request req_request =  new _Req_Request();

        // =========== start  _Req_AreaInfo  ===========
        _Req_AreaInfo req_areaInfo = new _Req_AreaInfo();
        req_areaInfo.setCompanyno(form.getCompanyno());
        req_areaInfo.setCitycode(form.getCitycode());

        req_request.setAreaInfo(req_areaInfo);
        // =========== end  _Req_AreaInfo  ===========



        // =========== start  _Req_PlcInfo  ===========
        _Req_PlcInfo req_plcInfo = new _Req_PlcInfo();

        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Date plcstartdate = sdf.parse(form.getPlcstartdate());
        c.setTime(plcstartdate);
        c.add(Calendar.YEAR, 1);
        req_plcInfo.setPlcenddate(sdf.format(c.getTime()));
        req_plcInfo.setPlcstartdate(form.getPlcstartdate());

        req_request.setPlcInfo(req_plcInfo);
        // =========== end  _Req_PlcInfo  ===========


        // =========== start  _Req_VehicleInfo  ===========
        _Req_VehicleInfo req_vehicleInfo = new _Req_VehicleInfo();

        req_vehicleInfo.setNolicenseflag(form.getNolicenseflag());

        //判断是否有车牌号
        if (EjsinoConstants.LicenseFlag.HAVE == form.getNolicenseflag()) {
            req_vehicleInfo.setLicenseno(form.getLicenseno());
        } else {
            req_vehicleInfo.setLicenseno("");
        }


        req_vehicleInfo.setOwnername(form.getOwnername());
        req_vehicleInfo.setOwneridno(form.getOwnercertno());
        req_vehicleInfo.setOwnercerttype("1");
        req_vehicleInfo.setOwnercertno(form.getOwnercertno());
        req_vehicleInfo.setVehicleframeno(form.getVehicleframeno());
        req_vehicleInfo.setCitycode(form.getCitycode());
        req_vehicleInfo.setCardno(form.getCardno());
        req_vehicleInfo.setFirstregisterdate(form.getFirstregisterdate());
        req_vehicleInfo.setVehiclemodelname(form.getVehiclemodelname());
        req_vehicleInfo.setVehicleid(form.getVehicleid());
        req_vehicleInfo.setEngineno(form.getEngineno());
        req_vehicleInfo.setVehicleinvoiceno(form.getVehicleinvoiceno());
        req_vehicleInfo.setVehicleinvoicedate(form.getVehicleinvoicedate());
        req_vehicleInfo.setRuncardcertificatedate(form.getRuncardcertificatedate());
        req_vehicleInfo.setForcebegindate(form.getForcebegindate());

        req_vehicleInfo.setBizbegindate(form.getBizbegindate());

        req_vehicleInfo.setTaxvehicletype(form.getTaxvehicletype());
        req_vehicleInfo.setFueltype(form.getFueltype());
        req_vehicleInfo.setSpecialcarflag(form.getSpecialcarflag());
        req_vehicleInfo.setSpecialcardate(form.getSpecialcardate());
        req_vehicleInfo.setPersontaxcode(form.getPersontaxcode());
        req_vehicleInfo.setTaxticketno(form.getTaxticketno());
        req_vehicleInfo.setTaxtickettype(form.getTaxtickettype());
        req_vehicleInfo.setTaxbureauname(form.getTaxbureauname());
        req_vehicleInfo.setSettledaddress(form.getSettledaddress());
        req_vehicleInfo.setVehicleseats(form.getVehicleseats());
        req_vehicleInfo.setAveragemile(form.getAveragemile());
        req_vehicleInfo.setTrafficviolation(form.getTrafficviolation());
        req_vehicleInfo.setBizquestion(form.getBizquestion());
        req_vehicleInfo.setBizanswer(form.getBizanswer());
        req_vehicleInfo.setForcequestion(form.getForcequestion());
        req_vehicleInfo.setForcequestion(form.getForceanswer());
        req_vehicleInfo.setVehiclecode(form.getVehiclecode());
        req_vehicleInfo.setVehiclecodename(form.getVehiclecodename());
        req_vehicleInfo.setSessionid(form.getSessionid());

        if(EjsinoConstants.RequestTypes.CAR_MODEL_QUERY.equals(form.getOrdertype())) {
            req_vehicleInfo.setStartNo(1);
            req_vehicleInfo.setMaxCount(Integer.MAX_VALUE);
        }
        req_request.setVehicleInfo(req_vehicleInfo);

        // =========== end  _Req_VehicleInfo  ===========


        // =========== start  _Req_RiskInfo  ===========

        _Req_RiskInfo req_riskInfo = new _Req_RiskInfo();

        req_riskInfo.setTraveltax(form.getTraveltax());
        req_riskInfo.setTrafficinsurance(form.getTrafficinsurance());
        req_riskInfo.setRemark1(form.getRemark1());
        req_riskInfo.setRemark2(form.getRemark2());
        req_riskInfo.setRemark3(form.getRemark3());
        req_riskInfo.setRemark4(form.getRemark4());
        req_riskInfo.setRemark5(form.getRemark5());
        req_riskInfo.setRemark6(form.getRemark6());
        req_riskInfo.setRemark7(form.getRemark7());
        req_riskInfo.setRemark8(form.getRemark8());
        req_riskInfo.setRemark9(form.getRemark9());
        req_riskInfo.setRemark10(form.getRemark10());
        req_riskInfo.setRemark11(form.getRemark11());
        req_riskInfo.setRemark12(form.getRemark12());
        req_riskInfo.setRemark13(form.getRemark13());
        req_riskInfo.setRemark14(form.getRemark14());
        req_riskInfo.setRemark15(form.getRemark15());

        req_request.setRiskInfo(req_riskInfo);

        // =========== end  _Req_RiskInfo  ===========


        // =========== start  _Req_InsurInfo  ===========
        _Req_InsurInfo req_insurInfo = new _Req_InsurInfo();
        req_insurInfo.setName(form.getOwnername());
        req_insurInfo.setBirth(form.getInsurInfo_birth());
        req_insurInfo.setCertno(form.getInsurInfo_certno());
        req_insurInfo.setCerttype(form.getInsurInfo_certtype());
        //都用同一邮箱
        req_insurInfo.setEmail(form.getLinkInfo_email());
        req_insurInfo.setSex(form.getInsurInfo_sex());

        req_request.setInsurInfo(req_insurInfo);
        // =========== end  _Req_InsurInfo  ===========


        // =========== start  _Req_PremInfo  ===========
        _Req_PremInfo premInfo = new _Req_PremInfo();
        premInfo.setBusinesspremium(form.getBusinesspremium());
        premInfo.setForcepremium(form.getForcepremium());
        premInfo.setRealpremium(form.getRealpremium());
        premInfo.setTotalremium(form.getTotalremium());
        premInfo.setVehicletaxamount(form.getVehicletaxamount());

        req_request.setPremInfo(premInfo);
        // =========== end  _Req_PremInfo  ===========


        // =========== start  _Req_AplInfo  ===========
        _Req_AplInfo aplInfo = new _Req_AplInfo();
        aplInfo.setName(form.getAplInfo_name());
        aplInfo.setSex(form.getAplInfo_sex());
        aplInfo.setBirth(form.getAplInfo_birth());
        aplInfo.setCertno(form.getAplInfo_certno());
        aplInfo.setCerttype(form.getAplInfo_certtype());
        //都用同一邮箱
        aplInfo.setEmail(form.getLinkInfo_email());
        req_request.setAplInfo(aplInfo);
        // =========== end  _Req_AplInfo  ===========



        // =========== start  _Req_LinkInfo  ===========
        _Req_LinkInfo linkInfo = new _Req_LinkInfo();
        linkInfo.setName(form.getLinkInfo_name());
        linkInfo.setMobile(form.getLinkInfo_mobile());
        linkInfo.setAddress(form.getLinkInfo_address());
        linkInfo.setInvoice(form.getLinkInfo_invoice());
        linkInfo.setZipcode(form.getLinkInfo_zipcode());
        linkInfo.setEmail(form.getLinkInfo_email());
        linkInfo.setPaytype(EjsinoConstants.PayType.PAY_ONLINE);
        linkInfo.setRealpaymode(form.getLinkInfo_realpaymode());

        req_request.setLinkInfo(linkInfo);
        // =========== end  _Req_LinkInfo  ===========


        req_package.setRequest(req_request);
        packageList.set_package(req_package);

        return packageList;
    }

    public static List<Object> covertToTagList(List<_Res_Tag> tagsCarSubjList){

        List<Object> result = new ArrayList<Object>();
        for (_Res_Tag t : tagsCarSubjList) {

            HashMap<String, Object> tag = new HashMap<String, Object>();
            for(_Res_Definition def : t.getDefinitions()){
                tag.put(def.getName(), def.getValue());
            }
            result.add(tag);
        }

        return result;

    }
}
