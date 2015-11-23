package com.daoke.mobileserver.carinsurance.service.impl;

import com.daoke.mobileserver.carinsurance.service.CarInsuranceService;
import com.daoke.mobileserver.util.CarInsuranceSha1;
import org.springframework.stereotype.Service;


/**
 *
 * @author wangliming
 * @date 2014-9-11 下午2:55:17
 * @version 1.0
 */
@Service
public class CarInsuranceServiceImpl implements CarInsuranceService {

	@Override
	public String queryCarType(String xml, String url) throws Exception {
		return CarInsuranceSha1.httpPost(xml, url);
	}

	@Override
	public String getElementAndInsurance(String xml, String url) throws Exception {
		return CarInsuranceSha1.httpPost(xml, url);
	}

	@Override
	public String applyPurchase(String xml, String url) throws Exception {
		return CarInsuranceSha1.httpPost(xml, url);
	}

	@Override
	public String getPrice(String xml, String url) throws Exception {
		return CarInsuranceSha1.httpPost(xml, url);
	}

}
