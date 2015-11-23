package com.daoke.mobileserver.ejsino.model.request;

import javax.xml.bind.annotation.*;

/**
 * Created by wangzp on 2014/12/10.
 */
@XmlRootElement(name = "VehicleInfo")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "_Req_VehicleInfo")
public class _Req_VehicleInfo {

    /**车牌*/
    @XmlElement(name = "licenseno")
    private String licenseno;

    /**新车未上牌*/
    @XmlElement(name = "nolicenseflag")
    private Integer nolicenseflag;

    /**车主姓名*/
    @XmlElement(name = "ownername")
    private String ownername;

    /**车主身份证号码*/
    @XmlElement(name = "owneridno")
    private String owneridno;

    /**车主证件类型*/
    @XmlElement(name = "ownercerttype")
    private String ownercerttype;

    /**车主证件号码*/
    @XmlElement(name = "ownercertno")
    private String ownercertno;

    /**城市代码*/
    @XmlElement(name = "citycode")
    private String citycode;

    /**车牌号码*/
    @XmlElement(name = "cardno")
    private String cardno;

    /**车辆登记日期*/
    @XmlElement(name = "firstregisterdate")
    private String firstregisterdate;

    /**车辆品牌*/
    @XmlElement(name = "vehiclemodelname")
    private String vehiclemodelname;

    /**车辆品牌 对应的  key     */
    @XmlElement(name = "vehicleid")
    private String vehicleid;

    /**车架号*/
    @XmlElement(name = "vehicleframeno")
    private String vehicleframeno;

    /**发动机号*/
    @XmlElement(name = "engineno")
    private String engineno;

    /**购车发票号*/
    @XmlElement(name = "vehicleinvoiceno")
    private String vehicleinvoiceno;

    /**购车发票日期*/
    @XmlElement(name = "vehicleinvoicedate")
    private String vehicleinvoicedate;

    /**发证日期     */
    @XmlElement(name = "runcardcertificatedate")
    private String runcardcertificatedate;

    /**交强险起期*/
    @XmlElement(name = "forcebegindate")
    private String forcebegindate;

    /**商业险起期*/
    @XmlElement(name = "bizbegindate")
    private String bizbegindate;

    /**纳税车辆类型*/
    @XmlElement(name = "taxvehicletype")
    private String taxvehicletype;

    /**燃料(能源)类型*/
    @XmlElement(name = "fueltype")
    private String fueltype;

    /**是否过户车*/
    @XmlElement(name = "specialcarflag")
    private Integer  specialcarflag;

    /**过户日期*/
    @XmlElement(name = "specialcardate")
    private String specialcardate;

    /**纳税人编码*/
    @XmlElement(name = "persontaxcode")
    private String persontaxcode;

    /**税票号*/
    @XmlElement(name = "taxticketno")
    private String taxticketno;

    /**税票号类型*/
    @XmlElement(name = "taxtickettype")
    private String taxtickettype;

    /**开具税务机关名称*/
    @XmlElement(name = "taxbureauname")
    private String taxbureauname;

    /**车辆落户地址*/
    @XmlElement(name = "settledaddress")
    private String settledaddress;

    /**车座数*/
    @XmlElement(name = "vehicleseats")
    private Integer vehicleseats;

    /**年平均行驶里程*/
    @XmlElement(name = "averagemile")
    private Double averagemile;

    /**交通违章记录*/
    @XmlElement(name = "trafficviolation")
    private String trafficviolation;

    /**商业险问题*/
    @XmlElement(name = "bizquestion")
    private String bizquestion;

    /**商业险问题答案*/
    @XmlElement(name = "bizanswer")
    private String bizanswer;

    /**交强险问题*/
    @XmlElement(name = "forcequestion")
    private String forcequestion;

    /**交强险问题答案*/
    @XmlElement(name = "forceanswer")
    private String forceanswer;

    /**二次选择车型  代码*/
    @XmlElement(name = "vehiclecode")
    private String vehiclecode;

    /**二次选择车型  名称*/
    @XmlElement(name = "vehiclecodename")
    private String vehiclecodename;

    /**sessionid*/
    @XmlElement(name = "sessionid")
    private String sessionid;

    //起始号码
    @XmlElement(name = "startNo")
    private Integer startNo;

    //每页条数
    @XmlElement(name = "maxCount")
    private Integer maxCount;

    public String getLicenseno() {
        return licenseno;
    }

    public void setLicenseno(String licenseno) {
        this.licenseno = licenseno;
    }

    public Integer getNolicenseflag() {
        return nolicenseflag;
    }

    public void setNolicenseflag(Integer nolicenseflag) {
        this.nolicenseflag = nolicenseflag;
    }

    public String getOwnername() {
        return ownername;
    }

    public void setOwnername(String ownername) {
        this.ownername = ownername;
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

    public String getOwneridno() {
        return owneridno;
    }

    public void setOwneridno(String owneridno) {
        this.owneridno = owneridno;
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
        return vehicleinvoicedate;
    }

    public void setVehicleinvoicedate(String vehicleinvoicedate) {
        this.vehicleinvoicedate = vehicleinvoicedate;
    }

    public String getRuncardcertificatedate() {
        return runcardcertificatedate;
    }

    public void setRuncardcertificatedate(String runcardcertificatedate) {
        this.runcardcertificatedate = runcardcertificatedate;
    }

    public String getForcebegindate() {
        return forcebegindate;
    }

    public void setForcebegindate(String forcebegindate) {
        this.forcebegindate = forcebegindate;
    }

    public String getBizbegindate() {
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

    public Integer getStartNo() {
        return startNo;
    }

    public void setStartNo(Integer startNo) {
        this.startNo = startNo;
    }

    public Integer getMaxCount() {
        return maxCount;
    }

    public void setMaxCount(Integer maxCount) {
        this.maxCount = maxCount;
    }

    @Override
    public String toString() {
        return "_Req_VehicleInfo{" +
                "licenseno='" + licenseno + '\'' +
                ", nolicenseflag=" + nolicenseflag +
                ", ownername='" + ownername + '\'' +
                ", owneridno='" + owneridno + '\'' +
                ", ownercerttype='" + ownercerttype + '\'' +
                ", ownercertno='" + ownercertno + '\'' +
                ", citycode='" + citycode + '\'' +
                ", cardno='" + cardno + '\'' +
                ", firstregisterdate='" + firstregisterdate + '\'' +
                ", vehiclemodelname='" + vehiclemodelname + '\'' +
                ", vehicleid='" + vehicleid + '\'' +
                ", vehicleframeno='" + vehicleframeno + '\'' +
                ", engineno='" + engineno + '\'' +
                ", vehicleinvoiceno='" + vehicleinvoiceno + '\'' +
                ", vehicleinvoicedate='" + vehicleinvoicedate + '\'' +
                ", runcardcertificatedate='" + runcardcertificatedate + '\'' +
                ", forcebegindate='" + forcebegindate + '\'' +
                ", bizbegindate='" + bizbegindate + '\'' +
                ", taxvehicletype='" + taxvehicletype + '\'' +
                ", fueltype='" + fueltype + '\'' +
                ", specialcarflag=" + specialcarflag +
                ", specialcardate='" + specialcardate + '\'' +
                ", persontaxcode='" + persontaxcode + '\'' +
                ", taxticketno='" + taxticketno + '\'' +
                ", taxtickettype='" + taxtickettype + '\'' +
                ", taxbureauname='" + taxbureauname + '\'' +
                ", settledaddress='" + settledaddress + '\'' +
                ", vehicleseats=" + vehicleseats +
                ", averagemile=" + averagemile +
                ", trafficviolation='" + trafficviolation + '\'' +
                ", bizquestion='" + bizquestion + '\'' +
                ", bizanswer='" + bizanswer + '\'' +
                ", forcequestion='" + forcequestion + '\'' +
                ", forceanswer='" + forceanswer + '\'' +
                ", vehiclecode='" + vehiclecode + '\'' +
                ", vehiclecodename='" + vehiclecodename + '\'' +
                ", sessionid='" + sessionid + '\'' +
                ", startNo=" + startNo +
                ", maxCount=" + maxCount +
                '}';
    }
}
