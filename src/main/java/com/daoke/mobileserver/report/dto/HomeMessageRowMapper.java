package com.daoke.mobileserver.report.dto;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author wangliming
 * @date 2014-8-11 下午2:13:55
 * @version 1.0
 */
public class HomeMessageRowMapper implements RowMapper<HomeMessage>{

	@Override
	public HomeMessage mapRow(ResultSet rs, int arg1) throws SQLException {
		HomeMessage homeMessage = new HomeMessage();
		homeMessage.setId(rs.getInt("id"));
		homeMessage.setAccountID(rs.getString("accountID"));
		homeMessage.setPhoneImei(rs.getString("phoneImei"));
		homeMessage.setCreateTime(rs.getLong("createTime"));
		return homeMessage;
	}

}
