package com.daoke.mobileserver.carinsurance.service;

/**
 *
 * @author wangliming
 * @date 2014-9-11 下午2:52:45
 * @version 1.0
 */
public interface CarInsuranceService {

	/**
	 * 车型查询
	 * 
	 * @param xml
	 * @param url
	 * @return
	 * @throws Exception 
	 */
	public String queryCarType(String xml, String url) throws Exception;
	
	/**
	 * 获取要素以及承保方案信息
	 * 
	 * @param xml
	 * @param url
	 * @return
	 * @throws Exception 
	 */
	public String getElementAndInsurance(String xml, String url) throws Exception;
	
	/**
	 * 申请购买
	 * 
	 * @param xml
	 * @param url
	 * @return
	 * @throws Exception 
	 */
	public String applyPurchase(String xml, String url) throws Exception;
	
	/**
	 * 报价
	 * 
	 * @param xml
	 * @param url
	 * @return
	 * @throws Exception 
	 */
	public String getPrice(String xml, String url) throws Exception;
}
