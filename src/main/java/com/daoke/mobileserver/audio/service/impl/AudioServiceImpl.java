package com.daoke.mobileserver.audio.service.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.daoke.mobileserver.audio.service.AudioService;
import com.daoke.mobileserver.util.ConstantsUtil;
import com.daoke.mobileserver.util.JsonPuserUtil;
import com.daoke.mobileserver.util.Sha1;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.ByteArrayPartSource;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.StringPart;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


/**
 * 
 * @author wangliming
 * @date 2014-9-26 上午11:12:41
 * @version 1.0
 */
@Service
public class AudioServiceImpl implements AudioService {

	@Override
	public String url2multimedia(String appKey, String secret, String fileURLS,
			String url2multimedia) throws Exception {
		String[] keys = { "appKey", "secret", "fileURLS" };
		Object[] values = { appKey, secret, fileURLS };
		String result = Sha1.httpPost(keys, values, url2multimedia);
		return result;
	}

	@Override
	public String txt2voice(String appKey, String secret, String txt, String txt2voice)
			throws HttpException, IOException {
		String result = null;
		String[] keys = { "appKey", "secret", "text" };
		Object[] values = { appKey, secret, txt };
		String sign = Sha1.calculationSign(keys, values);

		HttpClient client = new HttpClient();
		PostMethod post = new PostMethod(txt2voice);
		post.setRequestHeader(new Header("Content-Type",
				"application/x-www-form-urlencoded; charset=utf-8"));
		NameValuePair[] valuePairs = new NameValuePair[] { new NameValuePair("appKey", appKey),
				new NameValuePair("sign", sign), new NameValuePair("text", txt), };
		post.addParameters(valuePairs);

		int statusCode = client.executeMethod(post);
		if (statusCode == 200) {
			result = post.getResponseBodyAsString();
		} else {
			result = JsonPuserUtil.jsonToString(ConstantsUtil.ERRORCODE_SERVICE_ERROR,
                    ConstantsUtil.RESULT_SERVICE_ERROR);
		}

		return result;
	}

	@Override
	public String saveSound(String appKey, String secret, String saveSound,
			MultipartFile file) throws IOException {
		String result = null;
		String length = file.getSize() + "";
		String[] keys = { "appKey", "secret", "length" };
		Object[] values = { appKey, secret, length };
		String sign = Sha1.calculationSign(keys, values);

		HttpClient client = new HttpClient();
		PostMethod post = new PostMethod(saveSound);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("appKey", appKey);
		map.put("sign", sign);
		map.put("length", length);

		Part[] parts = new Part[map.size() + 1];
		int i = 0;
		for (Iterator<String> iter = map.keySet().iterator(); iter.hasNext();) {
			String paramname = iter.next();
			String paramvalue = (String) map.get(paramname);
			StringPart part = new StringPart(paramname, paramvalue, "utf-8");
			parts[i] = part;
			i++;
		}
		ByteArrayPartSource arrayPartSource = new ByteArrayPartSource(file.getOriginalFilename(), file.getBytes());
		FilePart part = new FilePart("filename", arrayPartSource);
		parts[i] = part;
		post.setRequestEntity(new MultipartRequestEntity(parts, post.getParams()));

		int statusCode = client.executeMethod(post);
		if (HttpStatus.SC_OK == statusCode) {
			result = post.getResponseBodyAsString();
		} else {
			result = JsonPuserUtil.jsonToString(ConstantsUtil.ERRORCODE_SERVICE_ERROR,
					ConstantsUtil.RESULT_SERVICE_ERROR);
		}
		return result;
	}

	@Override
	public String wav2voice(String appKey, String secret, MultipartFile file, String wav2voice)
			throws Exception {
		String result = null;
		String[] keys = { "appKey", "secret", "length" };
		Object[] values = { appKey, secret, file.getSize() + "" };
		String sign = Sha1.calculationSign(keys, values);

		HttpClient client = new HttpClient();
		PostMethod post = new PostMethod(wav2voice);
		StringPart appKeyPart = new StringPart("appKey", appKey, "utf-8");
		StringPart signPart = new StringPart("sign", sign, "utf-8");
		StringPart lengthPart = new StringPart("length", file.getSize() + "", "utf-8");
		ByteArrayPartSource arrayPartSource = new ByteArrayPartSource(file.getOriginalFilename(),
				file.getBytes());
		FilePart filePart = new FilePart("mmfile", arrayPartSource);
		Part[] parts = new Part[] { appKeyPart, signPart, lengthPart, filePart };

		post.setRequestEntity(new MultipartRequestEntity(parts, post.getParams()));
		int statusCode = client.executeMethod(post);
		if (HttpStatus.SC_OK == statusCode) {
			result = post.getResponseBodyAsString();
		} else {
			result = JsonPuserUtil.jsonToString(ConstantsUtil.ERRORCODE_SERVICE_ERROR,
					ConstantsUtil.RESULT_SERVICE_ERROR);
		}
		return result;
	}

	@Override
	public String saveFile(String appKey, String secret, MultipartFile file, String saveFile)
			throws Exception {
		String result = null;
		String filename = file.getOriginalFilename();
		String fileType = filename.substring(filename.indexOf(".") + 1);
		String[] keys = { "appKey", "secret", "length", "fileType" };
		Object[] values = { appKey, secret, String.valueOf(file.getSize()), fileType };
		String sign = Sha1.calculationSign(keys, values);

		HttpClient client = new HttpClient();
		PostMethod post = new PostMethod(saveFile);
		StringPart appKeyPart = new StringPart("appKey", appKey, "utf-8");
		StringPart signPart = new StringPart("sign", sign, "utf-8");
		StringPart lengthPart = new StringPart("length", String.valueOf(file.getSize()), "utf-8");
		StringPart fileTypePart = new StringPart("fileType", fileType, "utf-8");
		ByteArrayPartSource arrayPartSource = new ByteArrayPartSource(file.getOriginalFilename(),
				file.getBytes());
		FilePart filePart = new FilePart("mmfile", arrayPartSource);
		Part[] parts = new Part[] { appKeyPart, signPart, lengthPart, fileTypePart, filePart };

		post.setRequestEntity(new MultipartRequestEntity(parts, post.getParams()));
		int statusCode = client.executeMethod(post);
		if (HttpStatus.SC_OK == statusCode) {
			result = post.getResponseBodyAsString();
		} else {
			result = JsonPuserUtil.jsonToString(ConstantsUtil.ERRORCODE_SERVICE_ERROR,
					ConstantsUtil.RESULT_SERVICE_ERROR);
		}
		return result;
	}

}
