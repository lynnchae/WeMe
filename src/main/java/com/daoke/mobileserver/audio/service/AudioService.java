package com.daoke.mobileserver.audio.service;

import java.io.IOException;

import org.apache.commons.httpclient.HttpException;
import org.springframework.web.multipart.MultipartFile;

/**
 * 
 * @author wangliming
 * @date 2014-9-26 上午11:12:25
 * @version 1.0
 */
public interface AudioService {

	/**
	 * amr转mp3
	 * 
	 * @param appKey
	 * @param secret
	 * @param fileURLS
	 * @param url2multimedia
	 * @return
	 * @throws Exception
	 */
	public String url2multimedia(String appKey, String secret, String fileURLS,
                                 String url2multimedia) throws Exception;

	/**
	 * 文本信息转换成tts语音
	 * 
	 * @param appKey
	 * @param secret
	 * @param text
	 * @param type
	 * @return
	 * @throws java.io.IOException
	 * @throws org.apache.commons.httpclient.HttpException
	 */
	public String txt2voice(String appKey, String secret, String txt, String txt2voice)
			throws HttpException, IOException;

	/**
	 * 向服务器保存音频文件
	 * 
	 * @param appKey
	 * @param secret
	 * @param saveSound
	 * @param file
	 * @return
	 * @throws java.io.IOException
	 */
	public String saveSound(String appKey, String secret, String saveSound,
                            MultipartFile file) throws IOException;

	/**
	 * wav转amr
	 * 
	 * @param appKey
	 * @param secret
	 * @param mmfile
	 * @param wav2voice
	 * @return
	 * @throws Exception
	 */
	public String wav2voice(String appKey, String secret, MultipartFile file, String wav2voice)
			throws Exception;

	/**
	 * 存储任何文件类型
	 * 
	 * @param appKey
	 * @param secret
	 * @param file
	 * @param saveFile
	 * @return
	 * @throws Exception
	 */
	public String saveFile(String appKey, String secret, MultipartFile file, String saveFile)
			throws Exception;
}
