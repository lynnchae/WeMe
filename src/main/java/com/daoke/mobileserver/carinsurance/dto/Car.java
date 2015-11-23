package com.daoke.mobileserver.carinsurance.dto;

import javax.servlet.GenericServlet;
import java.io.Serializable;

/**
 * 车险
 * 
 * @author wangliming
 * @date 2014-10-10 下午3:03:37
 * @version 1.0
 */
public class Car implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5682992238753887372L;
	private String ordertype; // 订单类型
	private String outerno; //渠道唯一标识
	private String username; // 渠道账户
	private String password; // 账户密码
	private String companyno; // 保险公司代码
	private String citycode; // 城市代码
	private String licenseno; // 车牌号码
	private boolean nolicenseflag; // 是否新车
	private String ownername; // 车主
	private String callback; // 车型回调接口

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
	 * 获取 callback
	 * 
	 * @return callback
	 */
	public String getCallback() {
		return callback;
	}

	/**
	 * 设置 callback
	 * 
	 * @param callback
	 */
	public void setCallback(String callback) {
		this.callback = callback;
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

}
