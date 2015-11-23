package com.daoke.mobileserver.mall.entity;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by wangzp on 2014/12/23.
 */
public class MallBannerRowMapper implements RowMapper<MallBanner> {


    @Override
    public MallBanner mapRow(ResultSet resultSet, int i) throws SQLException {

        MallBanner entity = new MallBanner();

        entity.setBannerId(resultSet.getInt("bannerId"));
        entity.setCityCode(resultSet.getInt("cityCode"));
        entity.setContent(resultSet.getString("content"));
        entity.setRedirectType(resultSet.getInt("redirectType"));

        entity.setBannerImg(resultSet.getString("bannerImg"));
        return entity;
    }
}
