package com.daoke.mobileserver.mall.dao.impl;

import com.daoke.mobileserver.common.dao.BaseDao;
import com.daoke.mobileserver.mall.dao.IMallDao;
import com.daoke.mobileserver.mall.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by wangzp on 2014/12/23.
 */

@Repository
public class MallDaoImpl extends BaseDao implements IMallDao{


    @Autowired
    private JdbcTemplate wemeJdbcTemplate;


    @Override
    public List<MallBanner> queryMallBannerByCityCode(Integer cityCode) {

        String sql = "select * from mallBanner where cityCode = ?";

        return this.wemeJdbcTemplate.query(sql, new Object[]{cityCode}, new MallBannerRowMapper());
    }

    @Override
    public int insertHuaFeiDuoOrder(HuafeiduoOrder hfd) {
        String sql ="insert into huafeiduoOrder (orderId,cardWorth,price,status,phoneNumber,remark,isValid,createTime,accountId)values(?,?,?,?,?,?,1,now(),?)";

        return this.wemeJdbcTemplate.update(sql,new Object[]{hfd.getOrderId(),hfd.getCardWorth(),hfd.getPrice(),hfd.getStatus(),hfd.getPhoneNumber(),hfd.getRemark(),hfd.getAccountId()});
    }

    @Override
    public boolean isExistsHuafeiduoOrderNo(String orderId) {
        String sql ="select count(*) from huafeiduoOrder where orderId = ?";
        int count   = this.wemeJdbcTemplate.queryForInt(sql, new Object[]{orderId});
        return count >0 ? false : true;
    }

    @Override
    public int updateHuafeiduoOrderStatus(String orderId, int status,double price) {
        StringBuilder buf = new  StringBuilder();
        buf.append(" update huafeiduoOrder set status = ?  ");
        Object[] obj = null;
        if(price > 0){
            buf.append(" , price = ? ") ;
            obj =    new Object[]{status,price,orderId};
        } else{
            obj = new Object[]{status,orderId};
        }
        buf.append(" where orderId = ?");
        return this.wemeJdbcTemplate.update(buf.toString(),obj);
    }

    @Override
    public int insertHuaFeiDuoOrderNotify(HuafeiduoOrderNotify hfd) {
        String sql ="insert into huafeiduoOrderNotify (orderId,cardWorth,price,status,phoneNumber,remark,isValid,createTime,huafeiduoOrderId)values(?,?,?,?,?,?,1,now(),?)";
        return this.wemeJdbcTemplate.update(sql,new Object[]{hfd.getOrderId(),hfd.getCardWorth(),hfd.getPrice(),hfd.getStatus(),hfd.getPhoneNumber(),hfd.getRemark(),hfd.getHuafeiduoOrderId()});
    }

    @Override
    public HuafeiduoOrder getHuafeiduoOrder(String orderId) {
        String sql = "select   recordId,accountId,orderId,cardWorth,price,status,phoneNumber,isValid,createTime from huafeiduoOrder  where isValid = 1 and  orderId = ? ";
        List<HuafeiduoOrder> huafeiduoOrderList = this.wemeJdbcTemplate.query(sql, new Object[]{orderId}, new HuafeiduoOrderRowMapper());
        if (huafeiduoOrderList != null && huafeiduoOrderList.size() > 0) {
            return huafeiduoOrderList.get(0);
        }
        return null;
    }

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
                                           String modelName) {
        String sql = "INSERT INTO testDriveInfo (accountID,userName,mobile, provId, provName, cityId, cityName, brandId, brandName, seriesId, seriesName, modelId, modelName, createDate)" +
                " values (?,?,?,?,?,?,?,?,?,?,?,?,?,sysdate()) ";

        return this.wemeJdbcTemplate.update(sql, new Object[]{
                                                                accountID,
                                                                userName,
                                                                mobile,
                                                                provId,
                                                                provName,
                                                                cityId,
                                                                cityName,
                                                                brandId,
                                                                brandName,
                                                                seriesId,
                                                                seriesName,
                                                                modelId,
                                                                modelName});
    }

    @Override
    public Integer saveSellingCarInfo(String accountID, String userName, String userMobile, String url) {
        String sql = "INSERT INTO sellingCarInfo(accountID,userName,userMobile,url,createDate) VALUES (?,?,?,?,SYSDATE())";
        return this.wemeJdbcTemplate.update(sql, new Object[]{accountID,userName,userMobile,url});
    }

    @Override
    public List<DaokeWallet> queryDaokeWallet() {
        return this.selectList("daokeWallet.queryDaokeWallet");
    }


}
