package com.daoke.mobileserver.ejsino.model;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by wangzp on 2014/12/16.
 */
public class EjsinoCityInfoRowMapper implements RowMapper<EjsinoCityInfo> {


    @Override
    public EjsinoCityInfo mapRow(ResultSet resultSet, int i) throws SQLException {

        EjsinoCityInfo cityInfo = new EjsinoCityInfo();
        cityInfo.setCityName(resultSet.getString("cityName"));
        cityInfo.setCityCode(resultSet.getInt("cityCode"));
        cityInfo.setPinYin(resultSet.getString("pinYin"));
        cityInfo.setAbbreviate(resultSet.getString("abbreviate"));
        cityInfo.setParentCode(resultSet.getInt("parentCode"));

        return cityInfo;
    }
}
