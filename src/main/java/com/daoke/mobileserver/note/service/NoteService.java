package com.daoke.mobileserver.note.service;

/**
 *
 * @author wangliming
 * @date 2014-10-29 上午9:26:15
 * @version 1.0
 */
public interface NoteService {

	/**
	 * 更新用户语音记事本备注
	 * 
	 * @param appKey
	 * @param secret
	 * @param accountID
	 * @param idx
	 * @param remark
	 * @param updateVoiceNotepad
	 * @return
	 * @throws Exception
	 */
	public String updateVoiceNotepad(String appKey, String secret, String accountID, String idx,
			String remark, String updateVoiceNotepad) throws Exception;
	
	/**
	 * 删除用户语音记事本
	 * 
	 * @param appKey
	 * @param secret
	 * @param accountID
	 * @param idx
	 * @return
	 * @throws Exception
	 */
	public String deleteVoiceNotepad(String appKey, String secret, String accountID, String idx,
			String deleteVoiceNotepad) throws Exception;
	
	/**
	 * 获取用户语音记事本内容
	 * 
	 * @param appKey
	 * @param secret
	 * @param accountID
	 * @param currentPage
	 * @param maxCount
	 * @param remark
	 * @param fetchVoiceNotepad
	 * @return
	 * @throws Exception
	 */
	public String fetchVoiceNotepad(String appKey, String secret, String accountID,
			String currentPage, String maxCount, String remark, String fileType,
			String fetchVoiceNotepad) throws Exception;
	
}
