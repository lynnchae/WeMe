package com.daoke.mobileserver.shop.service;

/**
 * 
 * @author wangliming
 * @date 2014-9-26 上午9:52:58
 * @version 1.0
 */
public interface ShopService {

	/**
	 * 获取有效的商品列表
	 * 
	 * @param goodsType
	 * @param startPage
	 * @param pageCount
	 * @param getGoodsList
	 * @return
	 * @throws Exception
	 */
	public String getGoodsList(String goodsType, int startPage, int pageCount, String getGoodsList)
			throws Exception;

	/**
	 * 获取单个商品的详细信息
	 * 
	 * @param goodsID
	 * @param getGoodsByGoodsID
	 * @return
	 * @throws Exception
	 */
	public String getGoodsByGoodsID(String goodsID, String getGoodsByGoodsID) throws Exception;
}
