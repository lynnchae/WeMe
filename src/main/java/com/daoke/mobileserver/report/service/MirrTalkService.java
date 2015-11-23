package com.daoke.mobileserver.report.service;

import java.util.List;

import cn.jpush.api.push.PushResult;
import com.daoke.mobileserver.report.dto.SuggestComment;
import com.daoke.mobileserver.util.Pagination;


/**
 * 语境信息接口
 * 
 * @author wangliming
 * @date 2014-5-14 上午9:40:00
 * @version 1.0
 */
public interface MirrTalkService {

	/**
	 * 添加反馈信息
	 * 
	 * @param senderAccountID
	 * @param nickName
	 * @param text
	 * @param createTime
	 * @param suggestionType
	 * @param phoneType
	 * @return
	 */
	public boolean addSuggestion(String accountID, String nickName, String suggestContent,
			int phoneType);

	/**
	 * 发送推送消息
	 * 
	 * @param msgTitle
	 * @param msgContent
	 * @param imei
	 * @return messageResult
	 * @throws Exception
	 */
	public PushResult pushMessage(String msgTitle, String msgContent, String imei) throws Exception;

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
	 * 查询用户反馈回复信息
	 * 
	 * @param appKey
	 * @param secret
	 * @param accountID
	 * @return
	 */
	public List<SuggestComment> querySuggestComment(String accountID, String suggestID);

}