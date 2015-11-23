package com.daoke.mobileserver.carinsurance.dto;

/**
 * 要素以及承包方案
 * 
 * @author wangliming
 * @date 2014-10-11 上午10:42:19
 * @version 1.0
 */
public class ElementInsurance {

	private String ordertype; // 订单类型
	private String outerno; // 渠道流水号
	private String username; // 渠道账户
	private String password; // 账户密码
	private String citycode; // 城市代码
	private String companyno; // 保险公司代码
	private String plcstartdate; // 保单起始日期
	private String plcenddate; // 保单截至日期
	private String licenseno; // 车牌号
	private boolean nolicenseflag; // 新车未上牌 1：新车，0：非新车
	private String ownername; // 车主姓名
	private String ownercerttype; // 车主证件类型 固定:01---身份证
	private String ownercertno; // 车主证件号码 身份证号码
	private String name; // 被保人姓名

	// 补录信息
	private String subType; // 控件类型 Select,text,date,hidden,readonly…
	private String subKey; // 控件标示 调用接口时,需传递该标示,具体标示请参考报价接口参数
	private String subLabel; // 控件显示名称
	private String subValue; // 控件值
	private String subPremium; // 控件值
	private boolean subDisable; // 控件是否可编辑
	private String subData; // 控件数据来源 对于select,radio控件,会提供数据,供客户选择;

	// 承保方案
	private String riskType; // 控件类型 Select,text,date,hidden,readonly…
	private String riskKey; // 控件标示 调用接口时,需传递该标示,具体标示请参考报价接口参数
	private String riskLabel; // 控件显示名称
	private String riskValue; // 控件值
	private String riskPremium; // 控件值
	private boolean riskDisable; // 控件是否可编辑
	private String riskData; // 控件数据来源 对于select,radio控件,会提供数据,供客户选择;

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
	 * 获取 name
	 * 
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置 name
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获取 subType
	 * 
	 * @return subType
	 */
	public String getSubType() {
		return subType;
	}

	/**
	 * 设置 subType
	 * 
	 * @param subType
	 */
	public void setSubType(String subType) {
		this.subType = subType;
	}

	/**
	 * 获取 subKey
	 * 
	 * @return subKey
	 */
	public String getSubKey() {
		return subKey;
	}

	/**
	 * 设置 subKey
	 * 
	 * @param subKey
	 */
	public void setSubKey(String subKey) {
		this.subKey = subKey;
	}

	/**
	 * 获取 subLabel
	 * 
	 * @return subLabel
	 */
	public String getSubLabel() {
		return subLabel;
	}

	/**
	 * 设置 subLabel
	 * 
	 * @param subLabel
	 */
	public void setSubLabel(String subLabel) {
		this.subLabel = subLabel;
	}

	/**
	 * 获取 subValue
	 * 
	 * @return subValue
	 */
	public String getSubValue() {
		return subValue;
	}

	/**
	 * 设置 subValue
	 * 
	 * @param subValue
	 */
	public void setSubValue(String subValue) {
		this.subValue = subValue;
	}

	/**
	 * 获取 subPremium
	 * 
	 * @return subPremium
	 */
	public String getSubPremium() {
		return subPremium;
	}

	/**
	 * 设置 subPremium
	 * 
	 * @param subPremium
	 */
	public void setSubPremium(String subPremium) {
		this.subPremium = subPremium;
	}

	/**
	 * 获取 subDisable
	 * 
	 * @return subDisable
	 */
	public boolean isSubDisable() {
		return subDisable;
	}

	/**
	 * 设置 subDisable
	 * 
	 * @param subDisable
	 */
	public void setSubDisable(boolean subDisable) {
		this.subDisable = subDisable;
	}

	/**
	 * 获取 subData
	 * 
	 * @return subData
	 */
	public String getSubData() {
		return subData;
	}

	/**
	 * 设置 subData
	 * 
	 * @param subData
	 */
	public void setSubData(String subData) {
		this.subData = subData;
	}

	/**
	 * 获取 riskType
	 * 
	 * @return riskType
	 */
	public String getRiskType() {
		return riskType;
	}

	/**
	 * 设置 riskType
	 * 
	 * @param riskType
	 */
	public void setRiskType(String riskType) {
		this.riskType = riskType;
	}

	/**
	 * 获取 riskKey
	 * 
	 * @return riskKey
	 */
	public String getRiskKey() {
		return riskKey;
	}

	/**
	 * 设置 riskKey
	 * 
	 * @param riskKey
	 */
	public void setRiskKey(String riskKey) {
		this.riskKey = riskKey;
	}

	/**
	 * 获取 riskLabel
	 * 
	 * @return riskLabel
	 */
	public String getRiskLabel() {
		return riskLabel;
	}

	/**
	 * 设置 riskLabel
	 * 
	 * @param riskLabel
	 */
	public void setRiskLabel(String riskLabel) {
		this.riskLabel = riskLabel;
	}

	/**
	 * 获取 riskValue
	 * 
	 * @return riskValue
	 */
	public String getRiskValue() {
		return riskValue;
	}

	/**
	 * 设置 riskValue
	 * 
	 * @param riskValue
	 */
	public void setRiskValue(String riskValue) {
		this.riskValue = riskValue;
	}

	/**
	 * 获取 riskPremium
	 * 
	 * @return riskPremium
	 */
	public String getRiskPremium() {
		return riskPremium;
	}

	/**
	 * 设置 riskPremium
	 * 
	 * @param riskPremium
	 */
	public void setRiskPremium(String riskPremium) {
		this.riskPremium = riskPremium;
	}

	/**
	 * 获取 riskDisable
	 * 
	 * @return riskDisable
	 */
	public boolean isRiskDisable() {
		return riskDisable;
	}

	/**
	 * 设置 riskDisable
	 * 
	 * @param riskDisable
	 */
	public void setRiskDisable(boolean riskDisable) {
		this.riskDisable = riskDisable;
	}

	/**
	 * 获取 riskData
	 * 
	 * @return riskData
	 */
	public String getRiskData() {
		return riskData;
	}

	/**
	 * 设置 riskData
	 * 
	 * @param riskData
	 */
	public void setRiskData(String riskData) {
		this.riskData = riskData;
	}

}
