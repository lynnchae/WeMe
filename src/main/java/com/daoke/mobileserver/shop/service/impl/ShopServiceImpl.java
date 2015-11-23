package com.daoke.mobileserver.shop.service.impl;

import com.daoke.mobileserver.shop.service.ShopService;
import com.daoke.mobileserver.util.Sha1;
import org.springframework.stereotype.Service;


/**
 * 
 * @author wangliming
 * @date 2014-9-26 上午9:55:28
 * @version 1.0
 */
@Service
public class ShopServiceImpl implements ShopService {

	@Override
	public String getGoodsList(String goodsType, int startPage, int pageCount, String getGoodsList)
			throws Exception {
		String[] keys = { "goodsType", "startPage", "pageCount" };
		Object[] values = { goodsType, startPage, pageCount };
		String result = Sha1.httpPost(keys, values, getGoodsList);
		return result;
	}

	@Override
	public String getGoodsByGoodsID(String goodsID, String getGoodsByGoodsID) throws Exception {
		String[] keys = { "goodsID" };
		Object[] values = { goodsID };
		String result = Sha1.httpPost(keys, values, getGoodsByGoodsID);
		return result;
	}

}
