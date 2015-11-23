package com.daoke.mobileserver.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.apache.commons.httpclient.util.URIUtil;

/**
 * 大众点评API请求工具
 * 
 * @author wangliming
 * @date 2014-8-29 上午11:11:03
 * @version 1.0
 */
public class DianPingSha1 {

public static Map<String, Object> paramMap;
	
	/**
	 * 签名算法如下： 
	 * 1. 对除appkey,secret以外的所有请求参数进行字典升序排列； 
	 * 2. 将以上排序后的参数表进行字符串连接，如key1value1key2value2key3value3...keyNvalueN； 
	 * 3. 将app key作为前缀，将app secret作为后缀，对该字符串进行SHA-1计算，并转换成16进制编码； 
	 * 4. 转换为全大写形式后即获得签名串 
	 * 
	 * @param appKey
	 * @param secret
	 * @param keys
	 * @param values
	 * @return
	 */
	public static synchronized String calculationSign(String appKey, String secret, String[] keys,Object[] values){
		if(paramMap==null){
		 paramMap = new HashMap<String, Object>();
		}else{
			paramMap.clear();
		}
		for (int i = 0,j=keys.length; i < j; i++) {
			paramMap.put(keys[i], values[i]);
		}
		
		StringBuilder stringBuilder = new StringBuilder();
		//对参数名进行字典排序
		String[] keyArray = paramMap.keySet().toArray(new String[0]);
		Arrays.sort(keyArray);
		//拼接有序的参数名-值串
		stringBuilder.append(appKey);
		for (String key : keyArray) {
			stringBuilder.append(key).append(paramMap.get(key));
		}
		String codes = stringBuilder.append(secret).toString();
		//SHA-1编码，首先将中文转换为UTF8编码然后进行sha1计算
		return DigestUtils.shaHex(codes).toUpperCase();
	}
	
	/**
	 * 执行http get请求
	 * 
	 * @param keys
	 * @param values
	 * @param url
	 * @return
	 * @throws org.apache.commons.httpclient.HttpException
	 * @throws java.io.IOException
	 */
	public static String httpGet(String appKey, String secret, String[] keys, Object[] values, String url) throws Exception{
		HttpClientParams httpClientParams = new HttpClientParams();
		httpClientParams.setConnectionManagerTimeout(1000);
		HttpClient client = new HttpClient(httpClientParams);
		HttpMethod get = new GetMethod(url);
		
		String sign = calculationSign(appKey, secret, keys, values);
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("appkey=").append(appKey).append("&sign=").append(sign);
		for (int i = 0; i < keys.length; i++) {
			stringBuilder.append("&")
				.append(keys[i])
				.append("=")
				.append(values[i]);
		}
		String queryString = stringBuilder.toString();
		String encodeQuery = URIUtil.encodeQuery(queryString, "UTF-8");
		get.setQueryString(encodeQuery);
		client.executeMethod(get);
		
		String result = null;
		int statusCode = client.executeMethod(get);
		if(HttpStatus.SC_OK == statusCode){
			result = getContent(get);
		}else{
			result = JsonPuserUtil.jsonToString(ConstantsUtil.ERRORCODE_SERVICE_ERROR, ConstantsUtil.RESULT_SERVICE_ERROR);
		}
		get.releaseConnection();
		
		return result;
	}
	
	/**
	 * 获取返回内容
	 * 
	 * @param method
	 * @return
	 * @throws java.io.IOException
	 */
	public static String getContent(HttpMethod method) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(method.getResponseBodyAsStream(), "UTF-8"));
		String line = null;
		StringBuffer sb = new StringBuffer();
		while((line = br.readLine()) != null){
			sb.append(line).append(System.getProperty("line.separator"));
		}
		br.close();
		
		return sb.toString();
	}
	
	/**
	 * 添加不为空的属性
	 * 
	 * @param keys
	 * @param values
	 * @param keyContent
	 * @param valueContent
	 */
	public static void addContent(List<String> keys, List<Object> values, 
			String[] keyContent, Object[] valueContent){
		for (int i = 0; i < valueContent.length; i++) {
			if(valueContent[i] != null){
				keys.add(keyContent[i]);
				values.add(valueContent[i]);
			}
		}
	}
}
