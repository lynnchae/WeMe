package com.daoke.mobileserver.shop.controller;

import com.daoke.mobileserver.shop.service.ShopService;
import com.daoke.mobileserver.util.ConstantsUtil;
import com.daoke.mobileserver.util.JsonPuserUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 
 * @author wangliming
 * @date 2014-9-26 上午10:00:26
 * @version 1.0
 */
@Controller
public class ShopController {

	private final Logger logger = Logger.getLogger(ShopController.class);

	@Value("#{apiConfig[getGoodsList]}")
	private String getGoodsList;

	@Value("#{apiConfig[getGoodsByGoodsID]}")
	private String getGoodsByGoodsID;

	@Autowired
	private ShopService shopService;

	/**
	 * 获取有效的商品列表
	 * 
	 * @param goodsType
	 * @param startPage
	 * @param pageCount
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getGoodsList")
	public String getGoodsList(String goodsType, int startPage, int pageCount) {
		String result = null;
		try {
			result = shopService.getGoodsList(goodsType, startPage, pageCount, getGoodsList);
			logger.info(result);
		} catch (Exception e) {
			result = JsonPuserUtil.jsonToString(ConstantsUtil.ERRORCODE_SERVICE_ERROR,
                    ConstantsUtil.RESULT_SERVICE_ERROR);
			logger.error(JsonPuserUtil.jsonToString(ConstantsUtil.ERRORCODE_SERVICE_ERROR,
					e.toString()));
		}
		return result;
	}

	/**
	 * 获取单个商品的详细信息
	 * 
	 * @param goodsID
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getGoodsByGoodsID")
	public String getGoodsByGoodsID(String goodsID) {
		String result = null;
		try {
			result = shopService.getGoodsByGoodsID(goodsID, getGoodsByGoodsID);
			logger.info(result);
		} catch (Exception e) {
			result = JsonPuserUtil.jsonToString(ConstantsUtil.ERRORCODE_SERVICE_ERROR,
					ConstantsUtil.RESULT_SERVICE_ERROR);
			logger.error(JsonPuserUtil.jsonToString(ConstantsUtil.ERRORCODE_SERVICE_ERROR,
					e.toString()));
		}
		return result;
	}
}
