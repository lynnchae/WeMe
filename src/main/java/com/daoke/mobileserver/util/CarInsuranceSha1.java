package com.daoke.mobileserver.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;

/**
 *
 * @author wangliming
 * @date 2014-9-11 下午2:33:43
 * @version 1.0
 */
public class CarInsuranceSha1 {

	@SuppressWarnings("deprecation")
	public static String httpPost(String xml, String url) throws Exception{
		HttpClient client = new HttpClient();
        PostMethod post = new PostMethod(url); 
        post.setRequestBody(xml);
        post.setRequestHeader("Content-type", "text/xml; charset=GBK");
        
        String result = null;
        int statusCode = client.executeMethod(post);
        if (HttpStatus.SC_OK == statusCode) {
			result = getContent(post);
		}else {
			result = JsonPuserUtil.jsonToString(ConstantsUtil.ERRORCODE_SERVICE_ERROR, ConstantsUtil.RESULT_SERVICE_ERROR);
		}
        post.releaseConnection();
        
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
}
