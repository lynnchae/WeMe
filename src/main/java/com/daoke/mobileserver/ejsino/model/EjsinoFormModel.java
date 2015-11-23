package com.daoke.mobileserver.ejsino.model;

import java.io.Serializable;

/**
 * Created by wangzp on 2014/12/10.
 * 表单参数类
 */
public class EjsinoFormModel implements Serializable{

    /**
     * Header
     */
    private Integer version;

    private String ordertype;

    private String username;

    private String password;

    private String outerno;


    /**
     * 地区信息(AreaInfo)
     */
    private String companyno;

    private String  insuredperson;


    /**
     * 保单信息(PlcInfo)
     */
    private String plcstartdate;

    private String plcenddate;


    /**
     * 车辆信息（VehicleInfo）
     */
    /**车牌*/
    private String licenseno;

    /**新车未上牌*/
    private Integer nolicenseflag;

    /**车主姓名*/
    private String ownername;

    /**车主身份证号码*/
    private String owneridno;

    /**车主证件类型*/
    private String ownercerttype;

    /**车主证件号码*/
    private String ownercertno;

    /**城市代码*/
    private String citycode;

    /**车牌号码*/
    private String cardno;

    /**车辆登记日期*/
    private String firstregisterdate;

    /**车辆品牌*/
    private String vehiclemodelname;

    /**车辆品牌 对应的  key     */
    private String vehicleid;

    /**车架号*/
    private String vehicleframeno;

    /**发动机号*/
    private String engineno;

    /**购车发票号*/
    private String vehicleinvoiceno;

    /**购车发票日期*/
    private String vehicleinvoicedate;

    /**发证日期     */
    private String runcardcertificatedate;

    /**交强险起期*/
    private String forcebegindate;

    /**商业险起期*/
    private String bizbegindate;

    /**纳税车辆类型*/
    private String taxvehicletype;

    /**燃料(能源)类型*/
    private String fueltype;

    /**是否过户车*/
    private Integer  specialcarflag;

    /**过户日期*/
    private String specialcardate;

    /**纳税人编码*/
    private String persontaxcode;

    /**税票号*/
    private String taxticketno;

    /**税票号类型*/
    private String taxtickettype;

    /**开具税务机关名称*/
    private String taxbureauname;

    /**车辆落户地址*/
    private String settledaddress;

    /**车座数*/
    private Integer vehicleseats;

    /**年平均行驶里程*/
    private Double averagemile;

    /**交通违章记录*/
    private String trafficviolation;

    /**商业险问题*/
    private String bizquestion;

    /**商业险问题答案*/
    private String bizanswer;

    /**交强险问题*/
    private String forcequestion;

    /**交强险问题答案*/
    private String forceanswer;

    /**二次选择车型  代码*/
    private String vehiclecode;

    /**二次选择车型  名称*/
    private String vehiclecodename;

    /**sessionid*/
    private String sessionid;


    /**
     * 险种(RiskInfo)
     */
    private String trafficinsurance; //	交强险

    private String traveltax;//	车船税

    private String remark1;//	车辆损失险

    private String remark2;//	商业第三者责任险

    private String remark3;//	全车盗抢险

    private String remark4;//	司机座位责任险

    private String remark5;//	乘客座位责任险

    private String remark6;//	自燃损失险

    private String remark7;//	车身划痕险

    private String remark8;//	玻璃单独破碎险

    private String remark9;//	车辆损失险不计免赔

    private String remark10;//	商业第三者责任险 不计免赔

    private String remark11;//	全车盗抢险不计免赔

    private String remark12;//	司机座位责任险不计免赔

    private String remark13;//	涉水行使损失险

    private String remark14;//	附加险不计免赔

    private String remark15;//	乘客座位责任险不计免赔



    private String step;// 步骤


    /**
     * 联系人(LinkInfo)
     */
    private String linkInfo_name;	//姓名

    private String linkInfo_mobile;	//手机

    private String linkInfo_address;	//地址

    private String linkInfo_invoice;	//发票抬头

    private String linkInfo_zipcode;	//邮编

    private String linkInfo_email;	//邮箱

    private String linkInfo_paytype;	//支付方式

    private String linkInfo_realpaymode;	//支付类型


    /**
     * 被保人信息(InsurInfo)
     */
    private String insurInfo_name;

    private String insurInfo_certtype	;//证件类型

    private String insurInfo_certno;//证件号码

    private String insurInfo_sex;//性别

    private String insurInfo_birth;//生日

    private String insurInfo_email;//邮箱


    /**
     * 投保人信息(AplInfo)
     */
    private String aplInfo_name;//姓名

    private String aplInfo_certtype;//证件类型

    private String aplInfo_certno;//证件号码

    private String aplInfo_sex; //性别

    private String aplInfo_birth;//	生日

    private String aplInfo_email;//	邮箱


    /**
     * 保费信息(PremInfo)
     */
    private String businesspremium ;//商业险保费

    private String forcepremium;    //交强险保费

    private String vehicletaxamount;//车船税

    private String realpremium;     //实际保费

    private String totalremium;     //应缴总保费


    /**
     * InFrom(其他要素补充信息)
     */

    private String configbeforejudge; //是否投保确认


    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getOrdertype() {
        return ordertype;
    }

    public void setOrdertype(String ordertype) {
        this.ordertype = ordertype;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getOuterno() {
        return outerno;
    }

    public void setOuterno(String outerno) {
        this.outerno = outerno;
    }

    public String getCompanyno() {
        return companyno;
    }

    public void setCompanyno(String companyno) {
        this.companyno = companyno;
    }

    public String getInsuredperson() {
        return insuredperson;
    }

    public void setInsuredperson(String insuredperson) {
        this.insuredperson = insuredperson;
    }

    public String getPlcstartdate() {
        if(plcenddate == ""){
            plcenddate = null;
        }
        return plcstartdate;
    }

    public void setPlcstartdate(String plcstartdate) {
        this.plcstartdate = plcstartdate;
    }

    public String getPlcenddate() {
        if(plcenddate == ""){
            plcenddate = null;
        }

        return plcenddate;
    }

    public void setPlcenddate(String plcenddate) {
        this.plcenddate = plcenddate;
    }

    public String getLicenseno() {
        return licenseno;
    }

    public void setLicenseno(String licenseno) {
        this.licenseno = licenseno;
    }


    public String getOwnername() {
        return ownername;
    }

    public void setOwnername(String ownername) {
        this.ownername = ownername;
    }

    public String getOwneridno() {
        return owneridno;
    }

    public void setOwneridno(String owneridno) {
        this.owneridno = owneridno;
    }

    public String getOwnercerttype() {
        return ownercerttype;
    }

    public void setOwnercerttype(String ownercerttype) {
        this.ownercerttype = ownercerttype;
    }

    public String getOwnercertno() {
        return ownercertno;
    }

    public void setOwnercertno(String ownercertno) {
        this.ownercertno = ownercertno;
    }

    public String getCitycode() {
        return citycode;
    }

    public void setCitycode(String citycode) {
        this.citycode = citycode;
    }

    public String getCardno() {
        return cardno;
    }

    public void setCardno(String cardno) {
        this.cardno = cardno;
    }

    public String getFirstregisterdate() {
        if(firstregisterdate == ""){
            firstregisterdate = null;
        }
        return firstregisterdate;
    }

    public void setFirstregisterdate(String firstregisterdate) {
        this.firstregisterdate = firstregisterdate;
    }

    public String getVehiclemodelname() {
        return vehiclemodelname;
    }

    public void setVehiclemodelname(String vehiclemodelname) {
        this.vehiclemodelname = vehiclemodelname;
    }

    public String getVehicleid() {
        return vehicleid;
    }

    public void setVehicleid(String vehicleid) {
        this.vehicleid = vehicleid;
    }

    public String getVehicleframeno() {
        return vehicleframeno;
    }

    public void setVehicleframeno(String vehicleframeno) {
        this.vehicleframeno = vehicleframeno;
    }

    public String getEngineno() {
        return engineno;
    }

    public void setEngineno(String engineno) {
        this.engineno = engineno;
    }

    public String getVehicleinvoiceno() {
        return vehicleinvoiceno;
    }

    public void setVehicleinvoiceno(String vehicleinvoiceno) {
        this.vehicleinvoiceno = vehicleinvoiceno;
    }

    public String getVehicleinvoicedate() {
        if(vehicleinvoicedate == ""){
            vehicleinvoicedate = null;
        }
        return vehicleinvoicedate;
    }

    public void setVehicleinvoicedate(String vehicleinvoicedate) {
        this.vehicleinvoicedate = vehicleinvoicedate;
    }

    public String getRuncardcertificatedate() {
        if(runcardcertificatedate == ""){
            runcardcertificatedate = null;
        }
        return runcardcertificatedate;
    }

    public void setRuncardcertificatedate(String runcardcertificatedate) {
        this.runcardcertificatedate = runcardcertificatedate;
    }

    public String getForcebegindate() {
        if(forcebegindate == ""){
            forcebegindate = null;
        }
        return forcebegindate;
    }

    public void setForcebegindate(String forcebegindate) {
        this.forcebegindate = forcebegindate;
    }

    public String getBizbegindate() {
        if(bizbegindate == ""){
            bizbegindate = null;
        }

        return bizbegindate;
    }

    public void setBizbegindate(String bizbegindate) {
        this.bizbegindate = bizbegindate;
    }

    public String getTaxvehicletype() {
        return taxvehicletype;
    }

    public void setTaxvehicletype(String taxvehicletype) {
        this.taxvehicletype = taxvehicletype;
    }

    public String getFueltype() {
        return fueltype;
    }

    public void setFueltype(String fueltype) {
        this.fueltype = fueltype;
    }

    public Integer getSpecialcarflag() {
        return specialcarflag;
    }

    public void setSpecialcarflag(Integer specialcarflag) {
        this.specialcarflag = specialcarflag;
    }

    public String getSpecialcardate() {
        if(specialcardate == ""){
            specialcardate = null;
        }
        return specialcardate;
    }

    public void setSpecialcardate(String specialcardate) {
        this.specialcardate = specialcardate;
    }

    public String getPersontaxcode() {
        return persontaxcode;
    }

    public void setPersontaxcode(String persontaxcode) {
        this.persontaxcode = persontaxcode;
    }

    public String getTaxticketno() {
        return taxticketno;
    }

    public void setTaxticketno(String taxticketno) {
        this.taxticketno = taxticketno;
    }

    public String getTaxtickettype() {
        return taxtickettype;
    }

    public void setTaxtickettype(String taxtickettype) {
        this.taxtickettype = taxtickettype;
    }

    public String getTaxbureauname() {
        return taxbureauname;
    }

    public void setTaxbureauname(String taxbureauname) {
        this.taxbureauname = taxbureauname;
    }

    public String getSettledaddress() {
        return settledaddress;
    }

    public void setSettledaddress(String settledaddress) {
        this.settledaddress = settledaddress;
    }

    public Integer getVehicleseats() {
        return vehicleseats;
    }

    public void setVehicleseats(Integer vehicleseats) {
        this.vehicleseats = vehicleseats;
    }

    public Double getAveragemile() {
        return averagemile;
    }

    public void setAveragemile(Double averagemile) {
        this.averagemile = averagemile;
    }

    public String getTrafficviolation() {
        return trafficviolation;
    }

    public void setTrafficviolation(String trafficviolation) {
        this.trafficviolation = trafficviolation;
    }

    public String getBizquestion() {
        return bizquestion;
    }

    public void setBizquestion(String bizquestion) {
        this.bizquestion = bizquestion;
    }

    public String getBizanswer() {
        return bizanswer;
    }

    public void setBizanswer(String bizanswer) {
        this.bizanswer = bizanswer;
    }

    public String getForcequestion() {
        return forcequestion;
    }

    public void setForcequestion(String forcequestion) {
        this.forcequestion = forcequestion;
    }

    public String getForceanswer() {
        return forceanswer;
    }

    public void setForceanswer(String forceanswer) {
        this.forceanswer = forceanswer;
    }

    public String getVehiclecode() {
        return vehiclecode;
    }

    public void setVehiclecode(String vehiclecode) {
        this.vehiclecode = vehiclecode;
    }

    public String getVehiclecodename() {
        return vehiclecodename;
    }

    public void setVehiclecodename(String vehiclecodename) {
        this.vehiclecodename = vehiclecodename;
    }

    public String getSessionid() {
        return sessionid;
    }

    public void setSessionid(String sessionid) {
        this.sessionid = sessionid;
    }

    public Integer getNolicenseflag() {
        return nolicenseflag;
    }

    public void setNolicenseflag(Integer nolicenseflag) {
        this.nolicenseflag = nolicenseflag;
    }

    public String getTrafficinsurance() {
        return trafficinsurance;
    }

    public void setTrafficinsurance(String trafficinsurance) {
        this.trafficinsurance = trafficinsurance;
    }

    public String getTraveltax() {
        return traveltax;
    }

    public void setTraveltax(String traveltax) {
        this.traveltax = traveltax;
    }

    public String getRemark1() {
        return remark1;
    }

    public void setRemark1(String remark1) {
        this.remark1 = remark1;
    }

    public String getRemark2() {
        return remark2;
    }

    public void setRemark2(String remark2) {
        this.remark2 = remark2;
    }

    public String getRemark3() {
        return remark3;
    }

    public void setRemark3(String remark3) {
        this.remark3 = remark3;
    }

    public String getRemark4() {
        return remark4;
    }

    public void setRemark4(String remark4) {
        this.remark4 = remark4;
    }

    public String getRemark5() {
        return remark5;
    }

    public void setRemark5(String remark5) {
        this.remark5 = remark5;
    }

    public String getRemark6() {
        return remark6;
    }

    public void setRemark6(String remark6) {
        this.remark6 = remark6;
    }

    public String getRemark7() {
        return remark7;
    }

    public void setRemark7(String remark7) {
        this.remark7 = remark7;
    }

    public String getRemark8() {
        return remark8;
    }

    public void setRemark8(String remark8) {
        this.remark8 = remark8;
    }

    public String getRemark9() {
        return remark9;
    }

    public void setRemark9(String remark9) {
        this.remark9 = remark9;
    }

    public String getRemark10() {
        return remark10;
    }

    public void setRemark10(String remark10) {
        this.remark10 = remark10;
    }

    public String getRemark11() {
        return remark11;
    }

    public void setRemark11(String remark11) {
        this.remark11 = remark11;
    }

    public String getRemark12() {
        return remark12;
    }

    public void setRemark12(String remark12) {
        this.remark12 = remark12;
    }

    public String getRemark13() {
        return remark13;
    }

    public void setRemark13(String remark13) {
        this.remark13 = remark13;
    }

    public String getRemark14() {
        return remark14;
    }

    public void setRemark14(String remark14) {
        this.remark14 = remark14;
    }

    public String getRemark15() {
        return remark15;
    }

    public void setRemark15(String remark15) {
        this.remark15 = remark15;
    }

    public String getStep() {
        return step;
    }

    public void setStep(String step) {
        this.step = step;
    }


    public String getLinkInfo_name() {
        return linkInfo_name;
    }

    public void setLinkInfo_name(String linkInfo_name) {
        this.linkInfo_name = linkInfo_name;
    }

    public String getLinkInfo_mobile() {
        return linkInfo_mobile;
    }

    public void setLinkInfo_mobile(String linkInfo_mobile) {
        this.linkInfo_mobile = linkInfo_mobile;
    }

    public String getLinkInfo_address() {
        return linkInfo_address;
    }

    public void setLinkInfo_address(String linkInfo_address) {
        this.linkInfo_address = linkInfo_address;
    }

    public String getLinkInfo_invoice() {
        return linkInfo_invoice;
    }

    public void setLinkInfo_invoice(String linkInfo_invoice) {
        this.linkInfo_invoice = linkInfo_invoice;
    }

    public String getLinkInfo_zipcode() {
        return linkInfo_zipcode;
    }

    public void setLinkInfo_zipcode(String linkInfo_zipcode) {
        this.linkInfo_zipcode = linkInfo_zipcode;
    }

    public String getLinkInfo_email() {
        return linkInfo_email;
    }

    public void setLinkInfo_email(String linkInfo_email) {
        this.linkInfo_email = linkInfo_email;
    }

    public String getLinkInfo_paytype() {
        return linkInfo_paytype;
    }

    public void setLinkInfo_paytype(String linkInfo_paytype) {
        this.linkInfo_paytype = linkInfo_paytype;
    }

    public String getLinkInfo_realpaymode() {
        return linkInfo_realpaymode;
    }

    public void setLinkInfo_realpaymode(String linkInfo_realpaymode) {
        this.linkInfo_realpaymode = linkInfo_realpaymode;
    }

    public String getInsurInfo_name() {
        return insurInfo_name;
    }

    public void setInsurInfo_name(String insurInfo_name) {
        this.insurInfo_name = insurInfo_name;
    }

    public String getInsurInfo_certtype() {
        return insurInfo_certtype;
    }

    public void setInsurInfo_certtype(String insurInfo_certtype) {
        this.insurInfo_certtype = insurInfo_certtype;
    }

    public String getInsurInfo_certno() {
        return insurInfo_certno;
    }

    public void setInsurInfo_certno(String insurInfo_certno) {
        this.insurInfo_certno = insurInfo_certno;
    }

    public String getInsurInfo_sex() {
        return insurInfo_sex;
    }

    public void setInsurInfo_sex(String insurInfo_sex) {
        this.insurInfo_sex = insurInfo_sex;
    }

    public String getInsurInfo_birth() {
        if(insurInfo_birth == ""){
            insurInfo_birth = null;
        }
        return insurInfo_birth;
    }

    public void setInsurInfo_birth(String insurInfo_birth) {
        this.insurInfo_birth = insurInfo_birth;
    }

    public String getInsurInfo_email() {
        //使用同一邮箱
        insurInfo_email = linkInfo_email;

        return insurInfo_email;
    }

    public void setInsurInfo_email(String insurInfo_email) {
        this.insurInfo_email = insurInfo_email;
    }

    public String getAplInfo_name() {
        return aplInfo_name;
    }

    public void setAplInfo_name(String aplInfo_name) {
        this.aplInfo_name = aplInfo_name;
    }

    public String getAplInfo_certtype() {
        return aplInfo_certtype;
    }

    public void setAplInfo_certtype(String aplInfo_certtype) {
        this.aplInfo_certtype = aplInfo_certtype;
    }

    public String getAplInfo_certno() {
        return aplInfo_certno;
    }

    public void setAplInfo_certno(String aplInfo_certno) {
        this.aplInfo_certno = aplInfo_certno;
    }

    public String getAplInfo_sex() {
        return aplInfo_sex;
    }

    public void setAplInfo_sex(String aplInfo_sex) {
        this.aplInfo_sex = aplInfo_sex;
    }

    public String getAplInfo_birth() {
         if(aplInfo_birth == ""){
             aplInfo_birth = null;
         }
        return aplInfo_birth;
    }

    public void setAplInfo_birth(String aplInfo_birth) {
        this.aplInfo_birth = aplInfo_birth;
    }

    public String getAplInfo_email() {
        aplInfo_email = linkInfo_email;
        return aplInfo_email;
    }

    public void setAplInfo_email(String aplInfo_email) {
        this.aplInfo_email = aplInfo_email;
    }

    public String getBusinesspremium() {
        return businesspremium;
    }

    public void setBusinesspremium(String businesspremium) {
        this.businesspremium = businesspremium;
    }

    public String getForcepremium() {
        return forcepremium;
    }

    public void setForcepremium(String forcepremium) {
        this.forcepremium = forcepremium;
    }

    public String getVehicletaxamount() {
        return vehicletaxamount;
    }

    public void setVehicletaxamount(String vehicletaxamount) {
        this.vehicletaxamount = vehicletaxamount;
    }

    public String getRealpremium() {
        return realpremium;
    }

    public void setRealpremium(String realpremium) {
        this.realpremium = realpremium;
    }

    public String getTotalremium() {
        return totalremium;
    }

    public void setTotalremium(String totalremium) {
        this.totalremium = totalremium;
    }

    public String getConfigbeforejudge() {
        return configbeforejudge;
    }

    public void setConfigbeforejudge(String configbeforejudge) {
        this.configbeforejudge = configbeforejudge;
    }
}
