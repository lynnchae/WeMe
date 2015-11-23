package com.daoke.mobileserver.report.service.impl;

import java.util.List;

import com.daoke.mobileserver.report.dao.SuggestionDao;
import com.daoke.mobileserver.report.dto.SuggestComment;
import com.daoke.mobileserver.report.service.MirrTalkService;
import com.daoke.mobileserver.util.Pagination;
import com.daoke.mobileserver.util.RandomSendNo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;


/**
 * 语境信息业务层实现类
 * 
 * @author wangliming
 * @date 2014-5-14 上午9:44:48
 * @version 1.0
 */
@Service
public class MirrTalkServiceImpl implements MirrTalkService {

	private JPushClient jpush = new JPushClient(RandomSendNo.MASTER_SECRET, RandomSendNo.APP_KEY);

	@Autowired
	private SuggestionDao suggestionDao;

	@Override
	public boolean addSuggestion(String accountID, String nickName, String suggestContent,
			int phoneType) {
		return suggestionDao.addSuggestion(accountID, nickName, suggestContent, phoneType);
	}

	@Override
	public PushResult pushMessage(String msgTitle, String msgContent, String accountID)
			throws Exception {
		PushResult result = null;
		if (accountID.length() == 32) {
			result = jpush.sendIosMessageWithAlias(msgTitle, msgContent, accountID);
		} else {
			result = jpush.sendAndroidMessageWithAlias(msgTitle, msgContent, accountID);

		}
		return result;
	}

	@Override
	public Pagination queryPageSuggest(Integer currentPage, Integer numPerPage) {
		return suggestionDao.queryPageSuggest(currentPage, numPerPage);
	}

	@Override
	public boolean addSuggestComment(String suggestID, String replyName, String replyContent) {
		return suggestionDao.addSuggestComment(suggestID, replyName, replyContent);
	}

	@Override
	public List<SuggestComment> querySuggestComment(String accountID, String suggestID) {
		return suggestionDao.querySuggestComment(accountID, suggestID);
	}

}
