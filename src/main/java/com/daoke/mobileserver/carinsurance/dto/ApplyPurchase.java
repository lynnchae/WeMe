package com.daoke.mobileserver.carinsurance.dto;

/**
 * 申请购买信息
 * 
 * @author wangliming
 * @date 2014-10-11 上午11:35:46
 * @version 1.0
 */
public class ApplyPurchase {

	private String ordertype; // 订单类型
	private String outerno; // 渠道流水号
	private String username; // 渠道账户
	private String password; // 账户密码
	// 地区信息(AreaInfo)
	private String citycode; // 城市代码
	private String companyno; // 保险公司代码
	// 保单信息(PlcInfo)
	private String plcstartdate; // 保单起始日期
	private String plcenddate; // 保单截至日期
	// 车辆信息（VehicleInfo）
	private String licenseno; // 车牌号
	private boolean nolicenseflag; // 新车为上牌 1：新车，0：非新车
	private String ownername; // 车主姓名
	private String ownercerttype; // 车主证件类型
	private String ownercertno; // 车主证件号码 身份证号码
	private String vehicleid; // 车型ID 客户选择号车型后,e家通过回调接口返回
	// 险种(RiskInfo)
	private String trafficinsurance; // 交强险
	// 保费信息(PremInfo)
	private String businesspremium; // 商业险保费
	private String forcepremium; // 交强险保费
	private String vehicletaxamount; // 车船税
	private String realpremium; // 实际保费 商业险保费+交强险保费+车船税
	private String totalremium; // 应缴总保费 折扣前商业险保费+交强险保费+车船税
	// 投保人信息(AplInfo)
	private String aplName; // 姓名
	private String aplCerttype; // 证件类型
	private String aplCertno; // 证件号码
	private String aplSex; // 性别
	private String aplBirth; // 生日
	// 被保人信息(InsurInfo)
	private String insurName; // 姓名
	private String insurCerttype; // 证件类型
	private String insurCertno; // 证件号码
	private String insurSex; // 性别
	private String insurBirth; // 生日
	// 联系人(LinkInfo)
	private String linkName; // 姓名
	private String linkMobile; // 手机
	private String linkAddress; // 地址
	private String linkInvoice; // 发票抬头
	private String linkZipcode; // 邮编
	private String linkPaytype; // 支付方式 暂时固定:3: 线下支付
	private String linkRealpaymode; // 支付类型

	/**
	 * 获取 ordertype
	 * 
	 * @return ordertype
	 */
	public String getOrdertype() {
		return ordertype;
	}

	/**
	 * 设置 ordertype
	 * 
	 * @param ordertype
	 */
	public void setOrdertype(String ordertype) {
		this.ordertype = ordertype;
	}

	/**
	 * 获取 outerno
	 * 
	 * @return outerno
	 */
	public String getOuterno() {
		return outerno;
	}

	/**
	 * 设置 outerno
	 * 
	 * @param outerno
	 */
	public void setOuterno(String outerno) {
		this.outerno = outerno;
	}

	/**
	 * 获取 username
	 * 
	 * @return username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * 设置 username
	 * 
	 * @param username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * 获取 password
	 * 
	 * @return password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * 设置 password
	 * 
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * 获取 citycode
	 * 
	 * @return citycode
	 */
	public String getCitycode() {
		return citycode;
	}

	/**
	 * 设置 citycode
	 * 
	 * @param citycode
	 */
	public void setCitycode(String citycode) {
		this.citycode = citycode;
	}

	/**
	 * 获取 companyno
	 * 
	 * @return companyno
	 */
	public String getCompanyno() {
		return companyno;
	}

	/**
	 * 设置 companyno
	 * 
	 * @param companyno
	 */
	public void setCompanyno(String companyno) {
		this.companyno = companyno;
	}

	/**
	 * 获取 plcstartdate
	 * 
	 * @return plcstartdate
	 */
	public String getPlcstartdate() {
		return plcstartdate;
	}

	/**
	 * 设置 plcstartdate
	 * 
	 * @param plcstartdate
	 */
	public void setPlcstartdate(String plcstartdate) {
		this.plcstartdate = plcstartdate;
	}

	/**
	 * 获取 plcenddate
	 * 
	 * @return plcenddate
	 */
	public String getPlcenddate() {
		return plcenddate;
	}

	/**
	 * 设置 plcenddate
	 * 
	 * @param plcenddate
	 */
	public void setPlcenddate(String plcenddate) {
		this.plcenddate = plcenddate;
	}

	/**
	 * 获取 licenseno
	 * 
	 * @return licenseno
	 */
	public String getLicenseno() {
		return licenseno;
	}

	/**
	 * 设置 licenseno
	 * 
	 * @param licenseno
	 */
	public void setLicenseno(String licenseno) {
		this.licenseno = licenseno;
	}

	/**
	 * 获取 nolicenseflag
	 * 
	 * @return nolicenseflag
	 */
	public boolean isNolicenseflag() {
		return nolicenseflag;
	}

	/**
	 * 设置 nolicenseflag
	 * 
	 * @param nolicenseflag
	 */
	public void setNolicenseflag(boolean nolicenseflag) {
		this.nolicenseflag = nolicenseflag;
	}

	/**
	 * 获取 ownername
	 * 
	 * @return ownername
	 */
	public String getOwnername() {
		return ownername;
	}

	/**
	 * 设置 ownername
	 * 
	 * @param ownername
	 */
	public void setOwnername(String ownername) {
		this.ownername = ownername;
	}

	/**
	 * 获取 ownercerttype
	 * 
	 * @return ownercerttype
	 */
	public String getOwnercerttype() {
		return ownercerttype;
	}

	/**
	 * 设置 ownercerttype
	 * 
	 * @param ownercerttype
	 */
	public void setOwnercerttype(String ownercerttype) {
		this.ownercerttype = ownercerttype;
	}

	/**
	 * 获取 ownercertno
	 * 
	 * @return ownercertno
	 */
	public String getOwnercertno() {
		return ownercertno;
	}

	/**
	 * 设置 ownercertno
	 * 
	 * @param ownercertno
	 */
	public void setOwnercertno(String ownercertno) {
		this.ownercertno = ownercertno;
	}

	/**
	 * 获取 vehicleid
	 * 
	 * @return vehicleid
	 */
	public String getVehicleid() {
		return vehicleid;
	}

	/**
	 * 设置 vehicleid
	 * 
	 * @param vehicleid
	 */
	public void setVehicleid(String vehicleid) {
		this.vehicleid = vehicleid;
	}

	/**
	 * 获取 trafficinsurance
	 * 
	 * @return trafficinsurance
	 */
	public String getTrafficinsurance() {
		return trafficinsurance;
	}

	/**
	 * 设置 trafficinsurance
	 * 
	 * @param trafficinsurance
	 */
	public void setTrafficinsurance(String trafficinsurance) {
		this.trafficinsurance = trafficinsurance;
	}

	/**
	 * 获取 businesspremium
	 * 
	 * @return businesspremium
	 */
	public String getBusinesspremium() {
		return businesspremium;
	}

	/**
	 * 设置 businesspremium
	 * 
	 * @param businesspremium
	 */
	public void setBusinesspremium(String businesspremium) {
		this.businesspremium = businesspremium;
	}

	/**
	 * 获取 forcepremium
	 * 
	 * @return forcepremium
	 */
	public String getForcepremium() {
		return forcepremium;
	}

	/**
	 * 设置 forcepremium
	 * 
	 * @param forcepremium
	 */
	public void setForcepremium(String forcepremium) {
		this.forcepremium = forcepremium;
	}

	/**
	 * 获取 vehicletaxamount
	 * 
	 * @return vehicletaxamount
	 */
	public String getVehicletaxamount() {
		return vehicletaxamount;
	}

	/**
	 * 设置 vehicletaxamount
	 * 
	 * @param vehicletaxamount
	 */
	public void setVehicletaxamount(String vehicletaxamount) {
		this.vehicletaxamount = vehicletaxamount;
	}

	/**
	 * 获取 realpremium
	 * 
	 * @return realpremium
	 */
	public String getRealpremium() {
		return realpremium;
	}

	/**
	 * 设置 realpremium
	 * 
	 * @param realpremium
	 */
	public void setRealpremium(String realpremium) {
		this.realpremium = realpremium;
	}

	/**
	 * 获取 totalremium
	 * 
	 * @return totalremium
	 */
	public String getTotalremium() {
		return totalremium;
	}

	/**
	 * 设置 totalremium
	 * 
	 * @param totalremium
	 */
	public void setTotalremium(String totalremium) {
		this.totalremium = totalremium;
	}

	/**
	 * 获取 aplName
	 * 
	 * @return aplName
	 */
	public String getAplName() {
		return aplName;
	}

	/**
	 * 设置 aplName
	 * 
	 * @param aplName
	 */
	public void setAplName(String aplName) {
		this.aplName = aplName;
	}

	/**
	 * 获取 aplCerttype
	 * 
	 * @return aplCerttype
	 */
	public String getAplCerttype() {
		return aplCerttype;
	}

	/**
	 * 设置 aplCerttype
	 * 
	 * @param aplCerttype
	 */
	public void setAplCerttype(String aplCerttype) {
		this.aplCerttype = aplCerttype;
	}

	/**
	 * 获取 aplCertno
	 * 
	 * @return aplCertno
	 */
	public String getAplCertno() {
		return aplCertno;
	}

	/**
	 * 设置 aplCertno
	 * 
	 * @param aplCertno
	 */
	public void setAplCertno(String aplCertno) {
		this.aplCertno = aplCertno;
	}

	/**
	 * 获取 aplSex
	 * 
	 * @return aplSex
	 */
	public String getAplSex() {
		return aplSex;
	}

	/**
	 * 设置 aplSex
	 * 
	 * @param aplSex
	 */
	public void setAplSex(String aplSex) {
		this.aplSex = aplSex;
	}

	/**
	 * 获取 aplBirth
	 * 
	 * @return aplBirth
	 */
	public String getAplBirth() {
		return aplBirth;
	}

	/**
	 * 设置 aplBirth
	 * 
	 * @param aplBirth
	 */
	public void setAplBirth(String aplBirth) {
		this.aplBirth = aplBirth;
	}

	/**
	 * 获取 insurName
	 * 
	 * @return insurName
	 */
	public String getInsurName() {
		return insurName;
	}

	/**
	 * 设置 insurName
	 * 
	 * @param insurName
	 */
	public void setInsurName(String insurName) {
		this.insurName = insurName;
	}

	/**
	 * 获取 insurCerttype
	 * 
	 * @return insurCerttype
	 */
	public String getInsurCerttype() {
		return insurCerttype;
	}

	/**
	 * 设置 insurCerttype
	 * 
	 * @param insurCerttype
	 */
	public void setInsurCerttype(String insurCerttype) {
		this.insurCerttype = insurCerttype;
	}

	/**
	 * 获取 insurCertno
	 * 
	 * @return insurCertno
	 */
	public String getInsurCertno() {
		return insurCertno;
	}

	/**
	 * 设置 insurCertno
	 * 
	 * @param insurCertno
	 */
	public void setInsurCertno(String insurCertno) {
		this.insurCertno = insurCertno;
	}

	/**
	 * 获取 insurSex
	 * 
	 * @return insurSex
	 */
	public String getInsurSex() {
		return insurSex;
	}

	/**
	 * 设置 insurSex
	 * 
	 * @param insurSex
	 */
	public void setInsurSex(String insurSex) {
		this.insurSex = insurSex;
	}

	/**
	 * 获取 insurBirth
	 * 
	 * @return insurBirth
	 */
	public String getInsurBirth() {
		return insurBirth;
	}

	/**
	 * 设置 insurBirth
	 * 
	 * @param insurBirth
	 */
	public void setInsurBirth(String insurBirth) {
		this.insurBirth = insurBirth;
	}

	/**
	 * 获取 linkName
	 * 
	 * @return linkName
	 */
	public String getLinkName() {
		return linkName;
	}

	/**
	 * 设置 linkName
	 * 
	 * @param linkName
	 */
	public void setLinkName(String linkName) {
		this.linkName = linkName;
	}

	/**
	 * 获取 linkMobile
	 * 
	 * @return linkMobile
	 */
	public String getLinkMobile() {
		return linkMobile;
	}

	/**
	 * 设置 linkMobile
	 * 
	 * @param linkMobile
	 */
	public void setLinkMobile(String linkMobile) {
		this.linkMobile = linkMobile;
	}

	/**
	 * 获取 linkAddress
	 * 
	 * @return linkAddress
	 */
	public String getLinkAddress() {
		return linkAddress;
	}

	/**
	 * 设置 linkAddress
	 * 
	 * @param linkAddress
	 */
	public void setLinkAddress(String linkAddress) {
		this.linkAddress = linkAddress;
	}

	/**
	 * 获取 linkInvoice
	 * 
	 * @return linkInvoice
	 */
	public String getLinkInvoice() {
		return linkInvoice;
	}

	/**
	 * 设置 linkInvoice
	 * 
	 * @param linkInvoice
	 */
	public void setLinkInvoice(String linkInvoice) {
		this.linkInvoice = linkInvoice;
	}

	/**
	 * 获取 linkZipcode
	 * 
	 * @return linkZipcode
	 */
	public String getLinkZipcode() {
		return linkZipcode;
	}

	/**
	 * 设置 linkZipcode
	 * 
	 * @param linkZipcode
	 */
	public void setLinkZipcode(String linkZipcode) {
		this.linkZipcode = linkZipcode;
	}

	/**
	 * 获取 linkPaytype
	 * 
	 * @return linkPaytype
	 */
	public String getLinkPaytype() {
		return linkPaytype;
	}

	/**
	 * 设置 linkPaytype
	 * 
	 * @param linkPaytype
	 */
	public void setLinkPaytype(String linkPaytype) {
		this.linkPaytype = linkPaytype;
	}

	/**
	 * 获取 linkRealpaymode
	 * 
	 * @return linkRealpaymode
	 */
	public String getLinkRealpaymode() {
		return linkRealpaymode;
	}

	/**
	 * 设置 linkRealpaymode
	 * 
	 * @param linkRealpaymode
	 */
	public void setLinkRealpaymode(String linkRealpaymode) {
		this.linkRealpaymode = linkRealpaymode;
	}

}
