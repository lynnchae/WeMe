package com.daoke.mobileserver.report.dao.impl;

import java.util.List;

import com.daoke.mobileserver.report.dao.HomeConnDao;
import com.daoke.mobileserver.report.dto.HomeMessage;
import com.daoke.mobileserver.report.dto.HomeMessageRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


/**
 *
 * @author wangliming
 * @date 2014-8-8 下午5:26:26
 * @version 1.0
 */
@Repository
public class HomeConnDaoImpl implements HomeConnDao {

	@Autowired
	private JdbcTemplate wemeJdbcTemplate;
	
	@Override
	public boolean addHomeMessage(HomeMessage homeMessage) {
		Long createTime = System.currentTimeMillis() / 1000;
		String sql = "insert into homeMessage(id, accountID, phoneImei, createTime) values (null, ?, ?, ?)";
		Object[] args = {homeMessage.getAccountID(), homeMessage.getPhoneImei(), createTime};
		int count = this.wemeJdbcTemplate.update(sql, args);
		return count == 0 ? false : true;
	}

	@Override
	public HomeMessage getHomeMessage(String accountID) {
		String sql = "SELECT * FROM homeMessage WHERE accountID = ?";
		Object[] args = {accountID};
		List<HomeMessage> list = this.wemeJdbcTemplate.query(sql, args, new HomeMessageRowMapper());
		if (list != null && list.size() > 0) {
			return list.get(0);
		}else {
			return null;
		}
	}

	@Override
	public boolean updateHomeMessage(String accountID, String phoneImei) {
		Long updateTime = System.currentTimeMillis() / 1000;
		String sql = "UPDATE homeMessage SET phoneImei = ?, updateTime = ? WHERE accountID = ?";
		Object[] args = {phoneImei, updateTime, accountID};
		int count = this.wemeJdbcTemplate.update(sql, args);
		return count == 0?false:true;
	}

	@Override
	public HomeMessage getHomeMessage(String accountID, String phoneImei) {
		String sql = "SELECT * FROM homeMessage WHERE accountID = ? AND phoneImei = ?";
		Object[] args = {accountID, phoneImei};
		List<HomeMessage> list = this.wemeJdbcTemplate.query(sql, args, new HomeMessageRowMapper());
		if (list != null && list.size() > 0) {
			return list.get(0);
		}else {
			return null;
		}
	}

}
