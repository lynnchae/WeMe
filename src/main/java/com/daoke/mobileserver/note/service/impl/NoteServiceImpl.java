package com.daoke.mobileserver.note.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.daoke.mobileserver.note.service.NoteService;
import com.daoke.mobileserver.util.Sha1;
import org.springframework.stereotype.Service;


/**
 *
 * @author wangliming
 * @date 2014-10-29 上午9:39:30
 * @version 1.0
 */
@Service
public class NoteServiceImpl implements NoteService {

	@Override
	public String updateVoiceNotepad(String appKey, String secret, String accountID, String idx,
			String remark, String updateVoiceNotepad) throws Exception {
		String[] keys = { "appKey", "secret", "accountID", "idx", "remark" };
		Object[] values = { appKey, secret, accountID, idx, remark };
		String result = Sha1.httpPost(keys, values, updateVoiceNotepad);
		return result;
	}
	
	@Override
	public String deleteVoiceNotepad(String appKey, String secret, String accountID, String idx,
			String deleteVoiceNotepad) throws Exception {
		String[] keys = { "appKey", "secret", "accountID", "idx" };
		Object[] values = { appKey, secret, accountID, idx };
		String result = Sha1.httpPost(keys, values, deleteVoiceNotepad);
		return result;
	}

	@Override
	public String fetchVoiceNotepad(String appKey, String secret, String accountID,
			String currentPage, String maxCount, String remark, String fileType,
			String fetchVoiceNotepad) throws Exception {
		List<String> keys = new ArrayList<String>();
		keys.add("appKey");
		keys.add("secret");
		keys.add("accountID");

		List<Object> values = new ArrayList<Object>();
		values.add(appKey);
		values.add(secret);
		values.add(accountID);

		String[] keyContent = { "currentPage", "maxCount", "remark", "fileType" };
		Object[] valueContent = { currentPage, maxCount, remark, fileType };
		Sha1.addContent(keys, values, keyContent, valueContent);
		String result = Sha1.httpPost(keys.toArray(new String[0]), values.toArray(),
				fetchVoiceNotepad);
		return result;
	}

}
