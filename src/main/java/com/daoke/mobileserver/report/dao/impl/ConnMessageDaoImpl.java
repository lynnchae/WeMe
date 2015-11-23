package com.daoke.mobileserver.report.dao.impl;

import java.util.List;

import com.daoke.mobileserver.report.dao.ConnMessgeDao;
import com.daoke.mobileserver.report.dto.ConnMessage;
import com.daoke.mobileserver.report.dto.ConnMessageRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


/**
 * 
 * @author wangliming
 * @date 2014-8-11 上午9:54:00
 * @version 1.0
 */
@Repository
public class ConnMessageDaoImpl implements ConnMessgeDao {

	@Autowired
	private JdbcTemplate wemeJdbcTemplate;

	@Override
	public boolean addConnMessage(ConnMessage connMessage) {
		Long createTime = System.currentTimeMillis() / 1000;
		String sql = "insert into connMessage(id, accountID, nickName, phoneImei, " +
				"fileURL, fileDuration, isRead, createTime) values (null, ?, ?, ?, ?, ?, ?, ?)";
		Object[] args = { connMessage.getAccountID(), connMessage.getNickName(),
				connMessage.getPhoneImei(), connMessage.getFileURL(),
				connMessage.getFileDuration(), connMessage.getIsRead(), createTime };
		int count = this.wemeJdbcTemplate.update(sql, args);
		return count == 0 ? false : true;
	}

	@Override
	public List<ConnMessage> getConnMessages(String phoneImei, String accountID) {
		String sql = "SELECT * FROM connMessage WHERE phoneImei = ? AND accountID = ? AND isRead = 0";
		Object[] args = { phoneImei, accountID };
		List<ConnMessage> list = this.wemeJdbcTemplate.query(sql, args, new ConnMessageRowMapper());
		if (list != null && list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}

	@Override
	public boolean updateConnMessage(String phoneImei, String accountID) {
		Long updateTime = System.currentTimeMillis() / 1000;
		String sql = "UPDATE connMessage SET isRead = 1, updateTime = ? WHERE phoneImei = ? AND accountID = ? AND isRead = 0";
		Object[] args = { updateTime, phoneImei, accountID };
		int count = this.wemeJdbcTemplate.update(sql, args);
		return count == 0 ? false : true;
	}

}
