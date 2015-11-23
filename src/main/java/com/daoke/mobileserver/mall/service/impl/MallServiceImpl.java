package com.daoke.mobileserver.mall.service.impl;

import com.daoke.mobileserver.common.service.ServiceException;
import com.daoke.mobileserver.mall.dao.IMallDao;
import com.daoke.mobileserver.mall.entity.DaokeWallet;
import com.daoke.mobileserver.mall.entity.HuafeiduoOrder;
import com.daoke.mobileserver.mall.entity.HuafeiduoOrderNotify;
import com.daoke.mobileserver.mall.entity.MallBanner;
import com.daoke.mobileserver.mall.service.IMallService;
import com.daoke.mobileserver.util.AbDateUtil;
import com.daoke.mobileserver.util.RandomNumberUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * Created by wangzp on 2014/12/23.
 */

@Service
public class MallServiceImpl implements IMallService {

    private static final Logger log = LoggerFactory.getLogger(MallServiceImpl.class);

    @Autowired
    private IMallDao mallDao;


    @Override
    public List<MallBanner> queryMallBannerByCityCode(Integer cityCode) {
        return mallDao.queryMallBannerByCityCode(cityCode);
    }

    @Transactional
    @Override
    public String insertHuaFeiDuoOrder(HuafeiduoOrder huafeiduoOrder) throws ServiceException{
        try {
            //生成订单
            String orderId = generateOrderSn();

            huafeiduoOrder.setOrderId(orderId);

            int count = mallDao.insertHuaFeiDuoOrder(huafeiduoOrder);
            if(count > 0){
               return orderId;
            }else{
                throw new ServiceException("生成订单失败");
            }
        } catch (DataAccessException dae) {
            log.error("ExceptionMessage: 创建话费多订单发生数据访问异常: huafeiduoOrder:" + huafeiduoOrder.toString(), dae.getMessage());
            log.error("StackTrace: ", dae);
            dae.printStackTrace();
            throw new ServiceException("创建话费多订单发生数据访问异常 ", dae);
        }
    }

    @Transactional
    @Override
    public int updateHuafeiduoOrderStatus(String orderId, short status,double price) {
        try {
            return this.mallDao.updateHuafeiduoOrderStatus(orderId, status,price);
        } catch (DataAccessException dae) {
            log.error("ExceptionMessage: 修改订单状态时发生数据访问异常: orderId:" + orderId +" status:" + status, dae.getMessage());
            log.error("StackTrace: ", dae);
            dae.printStackTrace();
            throw new ServiceException(" 修改订单状态时发生数据访问异常 ", dae);
        }
    }

    @Transactional
    @Override
    public int insertHuaFeiDuoOrderNotify(HuafeiduoOrderNotify huafeiduoOrderNotify) {
        try {
            return this.mallDao.insertHuaFeiDuoOrderNotify(huafeiduoOrderNotify);
        } catch (DataAccessException dae) {
            log.error("ExceptionMessage: 记录话费多回调信息发生数据库访问异常: huafeiduoOrderNotify:" + huafeiduoOrderNotify.toString(), dae.getMessage());
            log.error("StackTrace: ", dae);
            dae.printStackTrace();
            throw new ServiceException("记录话费多回调信息发生数据库访问异常", dae);
        }
    }

    @Override
    public HuafeiduoOrder getHuafeiduoOrder(String orderId) {
        return this.mallDao.getHuafeiduoOrder(orderId);
    }

    /**
     * 生成订单号
     *
     * @return
     */
    private String generateOrderSn() {
        StringBuilder sb = new StringBuilder();
        // 添加上时间
        String orderSn = AbDateUtil.getStringByFormat(new Date(), AbDateUtil.dateFormat);
        sb.append(orderSn);
        // 生成随机码
        for (int i = 0; i < 3000; i++) {
            String randCode = String.valueOf(RandomNumberUtil.genRandomNum(8));
            sb.append(randCode);
            if (this.mallDao.isExistsHuafeiduoOrderNo(sb.toString())) {
                break;
            }
        }
        //如果生成的长度小于22位则生成订单失败
        if (StringUtils.hasText(sb.toString()) && sb.toString().length() < 22) {
            orderSn = "";
        }
        return orderSn;
    }

    @Override
    public Integer saveNewCarTestDriveInfo(String accountID,String userName,
                                           String mobile, String provId, String provName, String cityId,
                                           String cityName, String brandId, String brandName, String seriesId,
                                           String seriesName, String modelId, String modelName) {

        return mallDao.saveNewCarTestDriveInfo(accountID,userName,mobile,provId,provName,cityId,cityName,brandId,brandName,
                seriesId,seriesName,modelId,modelName);
    }

    @Override
    public Integer saveSellingCarInfo(String accountID, String userName, String userMobile, String url) {
        return mallDao.saveSellingCarInfo(accountID,userName,userMobile,url);
    }

    @Override
    public List<DaokeWallet> queryDaokeWallet() {
        return mallDao.queryDaokeWallet();
    }
}
