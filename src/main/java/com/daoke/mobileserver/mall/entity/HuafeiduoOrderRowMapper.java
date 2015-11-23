package com.daoke.mobileserver.mall.entity;

import org.springframework.jdbc.core.RowMapper;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 * 话费多订单实体
 * <p/>
 * User: chenlong
 * Date: 2014/12/24
 * Time: 19:13
 */
public class HuafeiduoOrderRowMapper implements RowMapper<HuafeiduoOrder> {

    //recordId,accountId,orderId,cardWorth,price,status,phoneNumber,isValid,createTime
    @Override
    public HuafeiduoOrder mapRow(ResultSet resultSet, int i) throws SQLException {
        HuafeiduoOrder huafeiduoOrder = new HuafeiduoOrder();
        huafeiduoOrder.setRecordId(resultSet.getInt("recordId"));
        huafeiduoOrder.setAccountId(resultSet.getString("accountId"));
        huafeiduoOrder.setOrderId(resultSet.getString("orderId"));
        huafeiduoOrder.setCardWorth(resultSet.getDouble("cardWorth"));
        huafeiduoOrder.setPrice(resultSet.getDouble("price"));
        huafeiduoOrder.setStatus(resultSet.getShort("status"));
        huafeiduoOrder.setPhoneNumber(resultSet.getString("phoneNumber"));
        return huafeiduoOrder;
    }
}
