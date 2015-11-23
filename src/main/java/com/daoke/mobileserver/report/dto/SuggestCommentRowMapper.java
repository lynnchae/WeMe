package com.daoke.mobileserver.report.dto;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author wangliming
 * @date 2014-8-26 下午1:47:32
 * @version 1.0
 */
public class SuggestCommentRowMapper implements RowMapper<SuggestComment>{

	@Override
	public SuggestComment mapRow(ResultSet rs, int arg1) throws SQLException {
		SuggestComment suggestComment = new SuggestComment();
		suggestComment.setId(rs.getInt("id"));
		suggestComment.setSuggestID(rs.getInt("suggestID"));
		suggestComment.setReplyName(rs.getString("replyName"));
		suggestComment.setReplyContent(rs.getString("replyContent"));
		suggestComment.setCommentTime(rs.getInt("commentTime"));
		suggestComment.setAccountID(rs.getString("accountID"));
		suggestComment.setNickName(rs.getString("nickName"));
		suggestComment.setSuggestContent(rs.getString("suggestContent"));
		suggestComment.setSuggestTime(rs.getInt("suggestTime"));
		return suggestComment;
	}

}
