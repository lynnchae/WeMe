package com.daoke.mobileserver.report.dao;

import com.daoke.mobileserver.report.dto.SuggestComment;
import com.daoke.mobileserver.util.Pagination;

import java.util.List;


/**
 * 用户反馈内容
 * 
 * @author wangliming
 * @date 2014-6-17 上午10:33:39
 * @version 1.0
 */
public interface SuggestionDao {

	/**
	 * 添加反馈信息
	 * 
	 * @param accountID
	 * @param nickName
	 * @param suggestContent
	 * @param phoneType
	 * @return
	 */
	public boolean addSuggestion(String accountID, String nickName,
			String suggestContent, int phoneType);

	/**
	 * 分页查询数据
	 * 
	 * @param currentPage
	 * @param numPerPage
	 * @return
	 */
	public Pagination queryPageSuggest(Integer currentPage, Integer numPerPage);

	/**
	 * 添加反馈回复
	 * 
	 * @param suggestID
	 * @param replyName
	 * @param replyContent
	 * @return
	 */
	public boolean addSuggestComment(String suggestID, String replyName, String replyContent);

	/**
	 * 查询用户反馈回复
	 * 
	 * @param appKey
	 * @param secret
	 * @param accountID
	 * @return
	 */
	public List<SuggestComment> querySuggestComment(String accountID, String suggestID);
	
}
