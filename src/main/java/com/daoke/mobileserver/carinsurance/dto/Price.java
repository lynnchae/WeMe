package com.daoke.mobileserver.carinsurance.dto;

/**
 * 
 * @author wangliming
 * @date 2014-10-11 下午5:04:53
 * @version 1.0
 */
public class Price {

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
	private String ownercertno; // 车主证件号码
	private String vehicleid; // 车型ID

	// 险种(RiskInfo)
	private String trafficinsurance; // 交强险
	private String traveltax; // 车船税
	private String remark1; // 车辆损失险
	private String remark2; // 商业第三者责任险
	private String remark3; // 全车盗抢险
	private String remark4; // 司机座位责任险
	private String remark5; // 乘客座位责任险
	private String remark6; // 自燃损失险
	private String remark13; // 涉水行使损失险
	private String remark7; // 车身划痕险
	private String remark8; // 玻璃单独破碎险
	private String remark9; // 车辆损失险不计免赔
	private String remark10; // 商业第三者责任险 不计免赔
	private String remark11; // 全车盗抢险不计免赔
	private String remark12; // 司机座位责任险不计免赔
	private String remark14; // 附加险不计免赔
	private String remark15; // 乘客座位责任险不计免赔

	// 被保人信息(InsurInfo)
	private String name; // 姓名

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
	 * 获取 traveltax
	 * 
	 * @return traveltax
	 */
	public String getTraveltax() {
		return traveltax;
	}

	/**
	 * 设置 traveltax
	 * 
	 * @param traveltax
	 */
	public void setTraveltax(String traveltax) {
		this.traveltax = traveltax;
	}

	/**
	 * 获取 remark1
	 * 
	 * @return remark1
	 */
	public String getRemark1() {
		return remark1;
	}

	/**
	 * 设置 remark1
	 * 
	 * @param remark1
	 */
	public void setRemark1(String remark1) {
		this.remark1 = remark1;
	}

	/**
	 * 获取 remark2
	 * 
	 * @return remark2
	 */
	public String getRemark2() {
		return remark2;
	}

	/**
	 * 设置 remark2
	 * 
	 * @param remark2
	 */
	public void setRemark2(String remark2) {
		this.remark2 = remark2;
	}

	/**
	 * 获取 remark3
	 * 
	 * @return remark3
	 */
	public String getRemark3() {
		return remark3;
	}

	/**
	 * 设置 remark3
	 * 
	 * @param remark3
	 */
	public void setRemark3(String remark3) {
		this.remark3 = remark3;
	}

	/**
	 * 获取 remark4
	 * 
	 * @return remark4
	 */
	public String getRemark4() {
		return remark4;
	}

	/**
	 * 设置 remark4
	 * 
	 * @param remark4
	 */
	public void setRemark4(String remark4) {
		this.remark4 = remark4;
	}

	/**
	 * 获取 remark5
	 * 
	 * @return remark5
	 */
	public String getRemark5() {
		return remark5;
	}

	/**
	 * 设置 remark5
	 * 
	 * @param remark5
	 */
	public void setRemark5(String remark5) {
		this.remark5 = remark5;
	}

	/**
	 * 获取 remark6
	 * 
	 * @return remark6
	 */
	public String getRemark6() {
		return remark6;
	}

	/**
	 * 设置 remark6
	 * 
	 * @param remark6
	 */
	public void setRemark6(String remark6) {
		this.remark6 = remark6;
	}

	/**
	 * 获取 remark13
	 * 
	 * @return remark13
	 */
	public String getRemark13() {
		return remark13;
	}

	/**
	 * 设置 remark13
	 * 
	 * @param remark13
	 */
	public void setRemark13(String remark13) {
		this.remark13 = remark13;
	}

	/**
	 * 获取 remark7
	 * 
	 * @return remark7
	 */
	public String getRemark7() {
		return remark7;
	}

	/**
	 * 设置 remark7
	 * 
	 * @param remark7
	 */
	public void setRemark7(String remark7) {
		this.remark7 = remark7;
	}

	/**
	 * 获取 remark8
	 * 
	 * @return remark8
	 */
	public String getRemark8() {
		return remark8;
	}

	/**
	 * 设置 remark8
	 * 
	 * @param remark8
	 */
	public void setRemark8(String remark8) {
		this.remark8 = remark8;
	}

	/**
	 * 获取 remark9
	 * 
	 * @return remark9
	 */
	public String getRemark9() {
		return remark9;
	}

	/**
	 * 设置 remark9
	 * 
	 * @param remark9
	 */
	public void setRemark9(String remark9) {
		this.remark9 = remark9;
	}

	/**
	 * 获取 remark10
	 * 
	 * @return remark10
	 */
	public String getRemark10() {
		return remark10;
	}

	/**
	 * 设置 remark10
	 * 
	 * @param remark10
	 */
	public void setRemark10(String remark10) {
		this.remark10 = remark10;
	}

	/**
	 * 获取 remark11
	 * 
	 * @return remark11
	 */
	public String getRemark11() {
		return remark11;
	}

	/**
	 * 设置 remark11
	 * 
	 * @param remark11
	 */
	public void setRemark11(String remark11) {
		this.remark11 = remark11;
	}

	/**
	 * 获取 remark12
	 * 
	 * @return remark12
	 */
	public String getRemark12() {
		return remark12;
	}

	/**
	 * 设置 remark12
	 * 
	 * @param remark12
	 */
	public void setRemark12(String remark12) {
		this.remark12 = remark12;
	}

	/**
	 * 获取 remark14
	 * 
	 * @return remark14
	 */
	public String getRemark14() {
		return remark14;
	}

	/**
	 * 设置 remark14
	 * 
	 * @param remark14
	 */
	public void setRemark14(String remark14) {
		this.remark14 = remark14;
	}

	/**
	 * 获取 remark15
	 * 
	 * @return remark15
	 */
	public String getRemark15() {
		return remark15;
	}

	/**
	 * 设置 remark15
	 * 
	 * @param remark15
	 */
	public void setRemark15(String remark15) {
		this.remark15 = remark15;
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

}
