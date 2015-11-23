package com.daoke.mobileserver.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.lang.StringUtils;
import org.apache.http.params.CoreConnectionPNames;

/**
 * 
 * @author yangzhi
 * @author wangliming
 *
 */
public class Sha1 {
	public static Map<String, Object> paramMap;
	
	/**
	 * 计算sign
	 * （算sign时将API文档上所需参数除sign外全部传入并带上secret）
	 * @param keys  参数键的数组
	 * @param values  参数值的数组
	 * @return
	 */
	public static  String calculationSign(String[] keys,Object[] values){
		if(paramMap==null){
		 paramMap = new HashMap<String, Object>();
		}else{
			paramMap.clear();
		}
		for (int i = 0,j=keys.length; i < j; i++) {
          /*  if(values[i] == null){
                continue;
            }*/
            paramMap.put(keys[i], values[i]);
		}
		StringBuilder stringBuilder = new StringBuilder();
		String[] keyArray = paramMap.keySet().toArray(new String[0]);
		Arrays.sort(keyArray);
		for (String key : keyArray) {
			stringBuilder.append(key).append(paramMap.get(key) == null ? "" :paramMap.get(key) );
		}
		String codes = stringBuilder.toString();
		
		return DigestUtils.shaHex(codes).toUpperCase();
	}
	
	/**
	 * 执行http post请求
	 * 
	 * @param keys
	 * @param values
	 * @param url
	 * @return
	 * @throws org.apache.commons.httpclient.HttpException
	 * @throws java.io.IOException
	 */
	public static String httpPost(String[] keys, Object[] values, String url) throws Exception{
		HttpClient client = new HttpClient();
		PostMethod post = new PostMethod(url);
		post.setRequestHeader(new Header("Content-Type", "application/x-www-form-urlencoded; charset=utf-8"));
		String sign = calculationSign(keys, values);
		NameValuePair valuePair = null;
		NameValuePair[] valuePairs = new NameValuePair[keys.length];
		for(int i = 0; i < keys.length; i++){
			if(("secret").equals(keys[i])){
				valuePair = new NameValuePair("sign", sign);
			}else{
				valuePair = new NameValuePair(keys[i], values[i] == null ? "" :values[i].toString());
			}
			valuePairs[i] = valuePair;
		}
		post.addParameters(valuePairs);
        //设置超时时间
        client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT,6000);
		String result = null;
		int statusCode = client.executeMethod(post);
		if(HttpStatus.SC_OK == statusCode){
			result = getContent(post);
		}else{
			result = JsonPuserUtil.jsonToString(ConstantsUtil.ERRORCODE_SERVICE_ERROR, ConstantsUtil.RESULT_SERVICE_ERROR);
		}
		post.releaseConnection() ;
		
		return result;
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
	public static String httpGet(String[] keys, Object[] values, String url) throws Exception{
		HttpClient client = new HttpClient();
		GetMethod get = new GetMethod(url);
		get.setRequestHeader(new Header("Content-Type", "application/x-www-form-urlencoded; charset=utf-8"));
		String params = null;
		if(keys != null && values != null){
			for(int i = 0; i < keys.length; i++){
				if(i == 0){
					params = keys[i] + "=" + values[i];
				}else{
					params += "&" + keys[i] + "=" + values[i];
				}
			}
		}
		get.setQueryString(params);
        //设置超时时间
        client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT,6000);
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
		BufferedReader br = new BufferedReader(new InputStreamReader(method.getResponseBodyAsStream()));
		String line = null;
		StringBuffer sb = new StringBuffer();
		while((line = br.readLine()) != null){
			sb.append(line).append(System.getProperty("line.separator"));
		}
		br.close();
		
		return sb.toString();
	}
	
	/**
	 * 添加不为空的key
	 * 
	 * @param keys
	 * @return
	 */
	public static List<String> getKeys(String... keys){
		List<String> list = new ArrayList<String>();
		for(String key : keys){
			if(StringUtils.isNotEmpty(key)){
				list.add(key);
			}
		}
		
		return list;
	}
	
	/**
	 * 添加不为空的value
	 * 
	 * @param values
	 * @return
	 */
	public static List<Object> getValues(Object... values){
		List<Object> list = new ArrayList<Object>();
		for(Object value : values){
			if(value != null && !("").equals(value)){
				list.add(value);
			}
		}
		
		return list;
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
