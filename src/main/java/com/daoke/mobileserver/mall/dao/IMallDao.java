package com.daoke.mobileserver.mall.dao;

import com.daoke.mobileserver.mall.entity.DaokeWallet;
import com.daoke.mobileserver.mall.entity.HuafeiduoOrder;
import com.daoke.mobileserver.mall.entity.HuafeiduoOrderNotify;
import com.daoke.mobileserver.mall.entity.MallBanner;

import java.util.List;

/**
 * Created by wangzp on 2014/12/23.
 */
public interface IMallDao {

    /**
     * 根据城市id获取商城banner图
     * @param cityCode
     * @return
     */
    public List<MallBanner> queryMallBannerByCityCode(Integer cityCode);


    /**
     * 新增话费多订单
     * @param huafeiduoOrder
     * @return
     */
    public int insertHuaFeiDuoOrder(HuafeiduoOrder huafeiduoOrder);

    /**
     * 判断订单号是否已存在
     * @param orderId
     * @return
     */
    public boolean isExistsHuafeiduoOrderNo(String orderId);

    /**
     * 修改订单状态
      * @param orderId
     * @param status
     * @return
     */
    public int updateHuafeiduoOrderStatus(String orderId,int status,double price);

    /**
     * 记录话费多回调信息
     * @param huafeiduoOrderNotify
     * @return
     */
    public int insertHuaFeiDuoOrderNotify(HuafeiduoOrderNotify huafeiduoOrderNotify);

    /**
     * 查询话费多订单
     * @param orderId   本地订单ID
     * @return
     */
    public HuafeiduoOrder getHuafeiduoOrder(String orderId);


    /**
     * 保存试驾信息
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
    public Integer saveNewCarTestDriveInfo(String accountID,
                                           String userName,
                                           String mobile,
                                           String provId,
                                           String provName,
                                           String cityId,
                                           String cityName,
                                           String brandId,
                                           String brandName,
                                           String seriesId,
                                           String seriesName,
                                           String modelId,
                                           String modelName);


    /**
     * 保存卖车用户信息
     * @param accountID
     * @param userName
     * @param userMobile
     * @param url
     * @return
     */
    public Integer saveSellingCarInfo(String accountID,
                                      String userName,
                                      String userMobile,
                                      String url);
    public List<DaokeWallet> queryDaokeWallet();
}
