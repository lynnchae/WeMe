package com.daoke.mobileserver.report.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.daoke.mobileserver.report.dao.SuggestionDao;
import com.daoke.mobileserver.report.dto.SuggestComment;
import com.daoke.mobileserver.report.dto.SuggestCommentRowMapper;
import com.daoke.mobileserver.util.Pagination;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


/**
 * 反馈持久层
 * 
 * @author wangliming
 * @date 2014-6-17 上午10:51:19
 * @version 1.0
 */
@Repository
public class SuggestionDaoImpl implements SuggestionDao {

	@Autowired
	private JdbcTemplate wemeJdbcTemplate;

	@Override
	public boolean addSuggestion(String accountID, String nickName,
			String suggestContent, int phoneType) {
		long createTime = System.currentTimeMillis() / 1000;
		String sql = "insert into userSuggestInfo(suggestId, accountID, nickName, suggestContent, phoneType, createTime) values(null, ?, ?, ?, ?, ?)";
		Object[] args = { accountID, nickName, suggestContent, phoneType, createTime };
		int count = this.wemeJdbcTemplate.update(sql, args);
		return count == 0 ? false : true;
	}

	@Override
	public Pagination queryPageSuggest(Integer currentPage, Integer numPerPage) {
		String sql = "SELECT * FROM userSuggestInfo ORDER BY createTime DESC";
		Pagination Pagination = new Pagination(sql, currentPage, numPerPage, wemeJdbcTemplate);
		return Pagination;
	}

	@Override
	public boolean addSuggestComment(String suggestID, String replyName, String replyContent) {
		long createTime = System.currentTimeMillis() / 1000;
		String sql = "INSERT INTO userSuggestCommentInfo VALUES(null, ?, ?, ?, ?)";
		Object[] args = { suggestID, replyName, replyContent, createTime };
		int count = this.wemeJdbcTemplate.update(sql, args);
		return count == 0 ? false : true;
	}

	@Override
	public List<SuggestComment> querySuggestComment(String accountID, String suggestID) {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT comm.id, comm.suggestID, comm.replyName, comm.replyContent, comm.createTime AS commentTime,"
				+ " suggest.accountID, suggest.nickName, suggest.suggestContent, suggest.createTime AS suggestTime"
				+ " FROM userSuggestCommentInfo comm RIGHT JOIN userSuggestInfo suggest"
				+ " ON comm.suggestID = suggest.suggestID WHERE 1=1");
		List<String> args = new ArrayList<String>();
		if (StringUtils.isNotEmpty(accountID)) {
			sb.append(" AND suggest.accountID = ?");
			args.add(accountID);
		}
		/*if (StringUtils.isNotEmpty(suggestID)) {
			sb.append(" AND suggest.suggestID = ?");
			args.add(suggestID);
		}*/
		sb.append(" ORDER BY comm.createTime DESC");
		List<SuggestComment> suggestComments = this.wemeJdbcTemplate.query(sb.toString(),
				args.toArray(new String[0]), new SuggestCommentRowMapper());
		return suggestComments != null && suggestComments.size() > 0 ? suggestComments : null;
	}

}
