package com.daoke.mobileserver.mall.controller;

import com.daoke.mobileserver.common.model.CommonJsonResult;
import com.daoke.mobileserver.mall.WeMeMallCommonConfig;
import com.daoke.mobileserver.mall.service.IMallService;
import com.daoke.mobileserver.util.ConstantsUtil;
import com.daoke.mobileserver.util.HttpRequester;
import com.daoke.mobileserver.util.HttpRespons;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;

/**
 * Created by wangzp on 2014/12/24.
 *
 * 车三百对接
 */
@Controller
@RequestMapping("/mall")
public class Car300Controller {


    @Autowired
    private IMallService mallService;

    /**
     * 获得所有城市
     * @param response
     */
    @RequestMapping("/che300/getAllCity")
    public void  getAllCity(HttpServletResponse response) {

        HttpRequester requester = new HttpRequester();
        String url = WeMeMallCommonConfig.CAR_300_API + "?oper=getAllCity&token=" + WeMeMallCommonConfig.CAR_300_TOKEN;

        sendRequest(response, requester, url);
    }


    /**
     * 返回所有的品牌列表。
     * @param response
     */
    @RequestMapping("/che300/getCarBrandList")
    public void  getCarBrandList(HttpServletResponse response) {

        HttpRequester requester = new HttpRequester();
        String url = WeMeMallCommonConfig.CAR_300_API + "?oper=getCarBrandList&token=" + WeMeMallCommonConfig.CAR_300_TOKEN;

        sendRequest(response, requester, url);
    }

    /**
     * 返回指定品牌下面的所有车系列表。。
     * @param response
     * @param brandId  品牌标识，可以通过车三百品牌数据接口拿回所有的品牌信息，从而提取品
     */
    @RequestMapping("/che300/getCarSeriesList")
    public void  getCarSeriesList(HttpServletResponse response,
                                 String brandId) {

        HttpRequester requester = new HttpRequester();
        String url = WeMeMallCommonConfig.CAR_300_API + "?oper=getCarSeriesList&token=" + WeMeMallCommonConfig.CAR_300_TOKEN + "&brandId=" + brandId;

        sendRequest(response, requester, url);
    }


    /**
     * 返回指定品牌下面的所有车系列表。。
     * @param response
     * @param seriesId   车系标识，可以通过车三百车系数据接口拿回车系信息，从而提前车系标识
     */
    @RequestMapping("/che300/getCarModelList")
    public void  getCarModelList(HttpServletResponse response,
                                  String seriesId) {

        HttpRequester requester = new HttpRequester();
        String url = WeMeMallCommonConfig.CAR_300_API + "?oper=getCarModelList&token=" + WeMeMallCommonConfig.CAR_300_TOKEN + "&seriesId=" + seriesId;

        sendRequest(response, requester, url);
    }


    /**
     * 返回所估车型在指定地区的精确估值信息。
     * @param response
     * @param modelId  车型标识，可以通过车三百车型列表数据接口拿回车三百所支持的所有车型相关信息，
     *                 也可以申请合作成功之后提供网站自己的车型信息进行两者之间的映射
     * @param regDate   待估车辆的上牌时间（格式：yyyy-MM）。
     * @param mile   待估车辆的公里数，单位万公里。
     * @param zone  城市标识，可以通过车三百城市列表数据接口拿回车三百所支持的所有城市相关信息
     *              ，也可以申请合作成功之后提供网站自己的城市信息进行两者之间的映射
     * @param title 待估车辆的标题信息，可选参数。
     * @param price 待估车辆在贵网站上面的卖价（不是指导价，是用户标的价格），可选参数
     */
    @RequestMapping("/che300/getUsedCarPrice")
    public void  getUsedCarPrice(HttpServletResponse response,
                                 String modelId,
                                 String regDate,
                                 Double mile,
                                 String zone,
                                 @RequestParam(required = false) String title,
                                 @RequestParam(required = false) Double price) {

        if(mile == null ){
            mile = 0.0d;
        }
        BigDecimal a = new BigDecimal(mile);
        BigDecimal b = new BigDecimal(10000);

        HttpRequester requester = new HttpRequester();
        StringBuilder url = new StringBuilder();
        url
                .append(WeMeMallCommonConfig.CAR_300_API)
                .append("?oper=getUsedCarPrice&token=")
                .append(WeMeMallCommonConfig.CAR_300_TOKEN)
                .append("&modelId=")
                .append(modelId)
                .append("&regDate=")
                .append(regDate)
                .append("&mile=")
                .append(mile==0.0d?0.0d:a.divide(b).toString())
                .append("&zone=")
                .append(zone);

        if (StringUtils.isNotBlank(title)) {
            url
                    .append("&title=")
                    .append(title);
        }

        if (price != null && price > 0){
            url
                    .append("&price=")
                    .append(price);
        }

        sendRequest(response, requester, url.toString());
    }

    //执行请求
    private void sendRequest(HttpServletResponse response, HttpRequester requester, String url) {
        String content = "";

        PrintWriter writer = null;
        try {

            writer = response.getWriter();
            HttpRespons httpRespons = requester.sendGet(url);

            content = httpRespons.getContent();

            writer.print(content);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            writer.flush();
            writer.close();
        }
    }



    /**
     * 新车试驾
     * @param accountID
     * @param provId  省份id
     * @param provName  省份名称
     * @param cityId 城市id
     * @param cityName 城市名称
     * @param brandId 品牌id
     * @param brandName 品牌名称
     * @param seriesId 车系id
     * @param seriesName 车型名称
     * @param modelId 车型id
     * @param modelName 车型名称
     * @return
     */
    @RequestMapping("/che300/newCarTestDrive")
    public @ResponseBody CommonJsonResult newCarTestDrive(@RequestParam(required = true) String accountID,
                                                          @RequestParam(required = true) String accountName,
                                                          @RequestParam(required = true) String mobile,
                                                          @RequestParam(required = false) String provId,
                                                          @RequestParam(required = false) String provName,
                                                          @RequestParam(required = false) String cityId,
                                                          @RequestParam(required = false) String cityName,
                                                          @RequestParam(required = false) String brandId,
                                                          @RequestParam(required = false) String brandName,
                                                          @RequestParam(required = false) String seriesId,
                                                          @RequestParam(required = false) String seriesName,
                                                          @RequestParam(required = false) String modelId,
                                                          @RequestParam(required = false) String modelName){

        CommonJsonResult jsonResult = new CommonJsonResult();

        try {
            mallService.saveNewCarTestDriveInfo(accountID,accountName,mobile,provId,provName,cityId,cityName,brandId,brandName,seriesId,
                    seriesName,modelId,modelName);

            jsonResult.setERRORCODE(ConstantsUtil.ERRORCODE_OK);
            jsonResult.setRESULT("预约成功");
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.setERRORCODE(ConstantsUtil.ERRORCODE_SERVICE_ERROR);
            jsonResult.setRESULT("操作异常");
        }

        return jsonResult;
    }


    /**
     * 保存要卖车的用户
     * @param accountID
     * @return
     */
    @RequestMapping("/che300/saveSellingCarInfo")
    public @ResponseBody CommonJsonResult saveSellingCarInfo(@RequestParam(required = true) String accountID,
                                                             @RequestParam(required = false) String userName,
                                                             @RequestParam(required = false) String userMobile,
                                                             @RequestParam(required = false) String url){

        CommonJsonResult jsonResult = new CommonJsonResult();

        try {
            this.mallService.saveSellingCarInfo(accountID, userName, userMobile,url);
            jsonResult.setERRORCODE(ConstantsUtil.ERRORCODE_OK);
            jsonResult.setRESULT("保存成功");
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.setERRORCODE(ConstantsUtil.ERRORCODE_SERVICE_ERROR);
            jsonResult.setRESULT("操作异常");
        }

        return jsonResult;
    }

}
