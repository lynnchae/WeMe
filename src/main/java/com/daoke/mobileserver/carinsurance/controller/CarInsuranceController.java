package com.daoke.mobileserver.carinsurance.controller;

import com.daoke.mobileserver.carinsurance.dto.ApplyPurchase;
import com.daoke.mobileserver.carinsurance.dto.Car;
import com.daoke.mobileserver.carinsurance.dto.ElementInsurance;
import com.daoke.mobileserver.carinsurance.dto.Price;
import com.daoke.mobileserver.carinsurance.service.CarInsuranceService;
import com.daoke.mobileserver.util.ConstantsUtil;
import com.daoke.mobileserver.util.JsonPuserUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 *
 * @author wangliming
 * @date 2014-9-11 下午2:56:49
 * @version 1.0
 */
@Controller
public class CarInsuranceController {

	private final Logger logger = Logger.getLogger(CarInsuranceController.class);
	
	@Value("#{apiConfig[carApi]}")
	private String carApi;
	
	@Autowired
	private CarInsuranceService carInsuranceService;
	
	/**
	 * 车型查询
	 * 
	 * @param car
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/queryCarType")
	public String queryCarType(Car car){
		String result = null;
		Document doc = null;
		try {
			String xml = "<?xml version=\"1.0\" encoding=\"GBK\"?>" +
							"<PackageList>" +
								"<Package>" +
									"<Header>" +
										"<version>2</version>" +
										"<ordertype>3</ordertype>" +
										"<username>" + car.getUsername() + "</username>" +
										"<password>" + car.getPassword() + "</password>" +
									"</Header>" +
									"<Request>" +
										"<AreaInfo>" +
											"<companyno>" + car.getCompanyno() + "</companyno>" +
											"<citycode>" + car.getCitycode() + "</citycode>" +
										"</AreaInfo>" +
										"<CarmoduleInfo>" +
											"<callback>" + car.getCallback() + "</callback>" +
										"</CarmoduleInfo>" +
										"<VehicleInfo>" +
											"<licenseno>" + car.getLicenseno() + "</licenseno>" +
											"<nolicenseflag>" + car.isNolicenseflag() + "</nolicenseflag>" +
											"<ownername>" + car.getOwnername() + "</ownername>" +
										"</VehicleInfo>" +
									"</Request>" +
								"</Package>" +
							"</PackageList>";
			result = carInsuranceService.queryCarType(xml, carApi);
			doc = DocumentHelper.parseText(result);
			Element rootElement = doc.getRootElement();
			Element element = rootElement.element("Package");
			Element header = element.element("Header");
			String statuscode = header.elementTextTrim("statuscode");
			if (("0").equals(statuscode)) {
				Element response = element.element("Response");
				Element carmoduleInfo = response.element("CarmoduleInfo");
				String page = carmoduleInfo.elementTextTrim("page");
			}else {
				String message = header.elementTextTrim("message");
			}
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
	 * 获取要素以及承保方案信息
	 * 
	 * @param elementInsurance
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getElementAndInsurance")
	public String getElementAndInsurance(ElementInsurance elementInsurance){
		String result = null;
		Document doc = null;
		try {
			String type = elementInsurance.getSubType();
			if (StringUtils.isNotEmpty(type)) {
				type = "<TagsCarSubjList>" +
							"<Tag>" +
								"<Definition name=\"type\">" + elementInsurance.getSubType() + "</ Definition>" +
								"<Definition name=\"key\">" + elementInsurance.getSubKey() + "</ Definition>" +
								"<Definition name=\"label\">" + elementInsurance.getSubLabel() + "</Definition>" +
								"<Definition name=\"value\"/>" +
								"<Definition name=\"premium\"/>" +
								"<Definition name=\"disable\"/>" +
								"<Definition name=\"data\"> <![CDATA新车:1;非新车: 0]]> </ Definition>" +
							"</Tag>" +
					   "</TagsCarSubjList>" +
					   "<TagsCarRiskList>" +
			            	"<Tag>" +
		                        "<Definition name=\"type\">" + elementInsurance.getRiskType() + "</Definition>" +
		                        "<Definition name=\"key\">" + elementInsurance.getRiskKey() + "</Definition>" +
		                        "<Definition name=\"label\">" + elementInsurance.getRiskLabel() + "</Definition>" +
		                        "<Definition name=\"value\">" + elementInsurance.getRiskValue() + "</Definition>" +
		                        "<Definition name=\"premium\">" + elementInsurance.getRiskPremium() + "</Definition>" +
		                        "<Definition name=\"disable\"/>" +
		                        "<Definition name=\"data\"><![CDATA[投保:1]]></Definition>" +
		                    "</Tag>" +
		               "</TagsCarRiskList>";
			}else {
				type = "";
			}
			String xml = "<?xml version=\"1.0\" encoding=\"GBK\"?>" +
					"<PackageList>" +
						"<Package>" +
							"<Header>" +
								"<version>2</version>" +
								"<ordertype>3</ordertype>" +
								"<username>" + elementInsurance.getUsername() + "</username>" +
								"<password>" + elementInsurance.getPassword() + "</password>" +
							"</Header>" +
							"<Request>" +
								"<AreaInfo>" +
									"<companyno>" + elementInsurance.getCompanyno() + "</companyno>" +
									"<citycode>" + elementInsurance.getCitycode() + "</citycode>" +
								"</AreaInfo>" +
								"<PlcInfo>" +
									"<plcstartdate>" + elementInsurance.getPlcstartdate() + "</plcstartdate>" +
								    "<plcenddate>" + elementInsurance.getPlcenddate() + "</plcenddate>" +
								"</PlcInfo>" +
								"<VehicleInfo>" +
									"<licenseno>" + elementInsurance.getLicenseno() + "</licenseno>" +
									"<nolicenseflag>" + elementInsurance.isNolicenseflag() + "</nolicenseflag>" +
									"<ownername>" + elementInsurance.getOwnername() + "</ownername>" +
									"<ownercerttype>" + elementInsurance.getOwnercerttype() + "</ownercerttype>" +
									"<ownercertno>" + elementInsurance.getOwnercertno() + "</ownercertno>" +
								"</VehicleInfo>" +
								"<InsurInfo>" +
									"<name>" + elementInsurance.getName() + "</name>" +
								"</InsurInfo>" + 
								type +
							"</Request>" +
						"</Package>" +
					"</PackageList>";
			result = carInsuranceService.getElementAndInsurance(xml, carApi);
			doc = DocumentHelper.parseText(result);
			Element rootElement = doc.getRootElement();
			Element element = rootElement.element("Package");
			Element header = element.element("Header");
			String statuscode = header.elementTextTrim("statuscode");
			if (("2").equals(statuscode)) {
				//补录信息
			}
		} catch (Exception e) {
			result = JsonPuserUtil.jsonToString(ConstantsUtil.ERRORCODE_SERVICE_ERROR,
					ConstantsUtil.RESULT_SERVICE_ERROR);
			logger.error(JsonPuserUtil.jsonToString(ConstantsUtil.ERRORCODE_SERVICE_ERROR,
					e.toString()));
		}
		return result;
	}
	
	/**
	 * 报价
	 * 
	 * @param price
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getPrice")
	public String getPrice(Price price){
		String result = null;
		try {
			String xml = "<?xml version=\"1.0\" encoding=\"GBK\"?>" +
					"<PackageList>" +
						"<Package>" +
							"<Header>" +
								"<version>2</version>" +
								"<ordertype>3</ordertype>" +
								"<username>" + price.getUsername() + "</username>" +
								"<password>" + price.getPassword() + "</password>" +
							"</Header>" +
							"<Request>" +
								"<AreaInfo>" +
									"<companyno>" + price.getCompanyno() + "</companyno>" +
									"<citycode>" + price.getCitycode() + "</citycode>" +
								"</AreaInfo>" +
								"<PlcInfo>" +
									"<plcstartdate>" + price.getPlcstartdate() + "</plcstartdate>" +
								    "<plcenddate>" + price.getPlcenddate() + "</plcenddate>" +
								"</PlcInfo>" +
								"<VehicleInfo>" +
									"<licenseno>" + price.getLicenseno() + "</licenseno>" +
									"<nolicenseflag>" + price.isNolicenseflag() + "</nolicenseflag>" +
									"<ownername>" + price.getOwnername() + "</ownername>" +
									"<ownercerttype>" + price.getOwnercerttype() + "</ownercerttype>" +
									"<ownercertno>" + price.getOwnercertno() + "</ownercertno>" +
									"<vehicleid>" + price.getVehicleid() + "</vehicleid>" +
								"</VehicleInfo>" +
								"<RiskInfo>" +
									"<trafficinsurance>" + price.getTrafficinsurance() + "</trafficinsurance>" +
								"<RiskInfo>" +
								"<InsurInfo>" +
									"<name>" + price.getName() + "</name>" +
								"</InsurInfo>" + 
							"</Request>" +
						"</Package>" +
					"</PackageList>";
			result = carInsuranceService.getPrice(xml, carApi);
		} catch (Exception e) {
			result = JsonPuserUtil.jsonToString(ConstantsUtil.ERRORCODE_SERVICE_ERROR,
                    ConstantsUtil.RESULT_SERVICE_ERROR);
			logger.error(JsonPuserUtil.jsonToString(ConstantsUtil.ERRORCODE_SERVICE_ERROR,
					e.toString()));
		}
		
		return result;
	}
	
	/**
	 * 申请购买
	 * 
	 * @param applyPurchase
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/applyPurchase")
	public String applyPurchase(ApplyPurchase applyPurchase){
		String result = null;
		try {
			String xml = "<?xml version=\"1.0\" encoding=\"GBK\"?>" +
					"<PackageList>" +
						"<Package>" +
							"<Header>" +
								"<version>2</version>" +
								"<ordertype>3</ordertype>" +
								"<username>" + applyPurchase.getUsername() + "</username>" +
								"<password>" + applyPurchase.getPassword() + "</password>" +
							"</Header>" +
							"<Request>" +
								"<AreaInfo>" +
									"<companyno>" + applyPurchase.getCompanyno() + "</companyno>" +
									"<citycode>" + applyPurchase.getCitycode() + "</citycode>" +
								"</AreaInfo>" +
								"<PlcInfo>" +
									"<plcstartdate>" + applyPurchase.getPlcstartdate() + "</plcstartdate>" +
								    "<plcenddate>" + applyPurchase.getPlcenddate() + "</plcenddate>" +
								"</PlcInfo>" +
								"<VehicleInfo>" +
									"<licenseno>" + applyPurchase.getLicenseno() + "</licenseno>" +
									"<nolicenseflag>" + applyPurchase.isNolicenseflag() + "</nolicenseflag>" +
									"<ownername>" + applyPurchase.getOwnername() + "</ownername>" +
									"<ownercerttype>" + applyPurchase.getOwnercerttype() + "</ownercerttype>" +
									"<ownercertno>" + applyPurchase.getOwnercertno() + "</ownercertno>" +
									"<vehicleid>" + applyPurchase.getVehicleid() + "</vehicleid>" +
								"</VehicleInfo>" +
								"<RiskInfo>" +
									"<trafficinsurance>" + applyPurchase.getTrafficinsurance() + "</trafficinsurance>" +
								"</RiskInfo>" +
								"<PremInfo>" + 
									"<businesspremium>" + applyPurchase.getBusinesspremium() + "</businesspremium>" +
								    "<forcepremium>" + applyPurchase.getForcepremium() + "</forcepremium>" +
									"<vehicletaxamount>" + applyPurchase.getVehicletaxamount() + "</vehicletaxamount>" +
								    "<realpremium>" + applyPurchase.getRealpremium() + "</realpremium>" +
									"<totalremium>" + applyPurchase.getTotalremium() + "</totalremium>" +
								"</PremInfo>" +
								"<AplInfo>" +
									"<name>" + applyPurchase.getAplName() + "</name>" +
								    "<certtype>" + applyPurchase.getAplCerttype() + "</certtype>" +
									"<certno>" + applyPurchase.getAplCertno() + "</certno>" +
								    "<sex>" + applyPurchase.getAplSex() + "</sex>" +
									"<birth>" + applyPurchase.getAplBirth() + "</birth>" +
								"</AplInfo>" +
								"<InsurInfo>" +
									"<name>" + applyPurchase.getInsurName() + "</name>" +
									"<cerrtype>" + applyPurchase.getInsurCerttype() + "</cerrtype>" +
									"<certno>" + applyPurchase.getInsurCertno() + "</certno>" +
									"<sex>" + applyPurchase.getInsurSex() + "</sex>" +
									"<birth>" + applyPurchase.getInsurBirth() + "</birth>" +
								"</InsurInfo>" +
								"<LinkInfo>" +
									"<name>" + applyPurchase.getLinkName() + "</name>" +
								    "<mobile>" + applyPurchase.getLinkMobile() + "</mobile>" +
									"<address>" + applyPurchase.getLinkAddress() + "</address>" +
								    "<invoice>" + applyPurchase.getLinkInvoice() + "</invoice>" +
									"<zipcode>" + applyPurchase.getLinkZipcode() + "</zipcode>" +
								    "<paytype>" + applyPurchase.getLinkPaytype() + "</paytype>" +
									"<realpaymode>" + applyPurchase.getLinkRealpaymode() + "</realpaymode>" +
								"</LinkInfo>" +
							"</Request>" +
						"</Package>" +
					"</PackageList>";
			result = carInsuranceService.applyPurchase(xml, carApi);
		} catch (Exception e) {
			result = JsonPuserUtil.jsonToString(ConstantsUtil.ERRORCODE_SERVICE_ERROR,
					ConstantsUtil.RESULT_SERVICE_ERROR);
			logger.error(JsonPuserUtil.jsonToString(ConstantsUtil.ERRORCODE_SERVICE_ERROR,
					e.toString()));
		}
		return result;
	}
}