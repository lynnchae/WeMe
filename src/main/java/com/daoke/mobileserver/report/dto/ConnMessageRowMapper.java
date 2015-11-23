package com.daoke.mobileserver.report.dto;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;


/**
 *
 * @author wangliming
 * @date 2014-8-11 上午10:39:06
 * @version 1.0
 */
public class ConnMessageRowMapper implements RowMapper<ConnMessage>{

	@Override
	public ConnMessage mapRow(ResultSet rs, int arg1) throws SQLException {
		ConnMessage connMessage = new ConnMessage();
		connMessage.setId(rs.getInt("id"));
		connMessage.setAccountID(rs.getString("accountID"));
		connMessage.setNickName(rs.getString("nickName"));
		connMessage.setPhoneImei(rs.getString("phoneImei"));
		connMessage.setFileURL(rs.getString("fileURL"));
		connMessage.setFileDuration(rs.getString("fileDuration"));
		connMessage.setIsRead(rs.getString("isRead"));
		connMessage.setCreateTime(rs.getLong("createTime"));
		return connMessage;
	}

}
