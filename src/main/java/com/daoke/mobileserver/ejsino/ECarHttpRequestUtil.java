package com.daoke.mobileserver.ejsino;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;

/**
 * Created by wangzp on 2014/12/9.
 * 电子车险请求工具类
 */
public class ECarHttpRequestUtil {

    private static final String DEFAULT_CHARSET = "utf-8";
    private static final String CHARSET_UTF8 = DEFAULT_CHARSET;
    private static final String CHARSET_GBK = "gbk";

    /**
     *
     * @param requestUrl
     * @param requestData
     * @return
     * @throws Exception
     */
    public static String sendRequest(String requestUrl, String requestData) throws Exception {
        //拼凑请求
        HttpURLConnection conn;
        OutputStream outStream = null;
        try {
            //构建请求url
            URL url = new URL(requestUrl);

            //设置连接信息，并进行连接
            conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5* 1000);
            conn.setDoOutput(true);
            conn.setUseCaches(false);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setRequestProperty("Charset", CHARSET_GBK);
            conn.setRequestProperty("Content-Type", "text/xml; charset=" + CHARSET_GBK);
            conn.connect();

            //写入byte数据
            outStream = conn.getOutputStream();
            outStream.write(requestData.getBytes(CHARSET_GBK));

            //获取响应信息
            return getResponse(conn);

        } finally {
            if (outStream != null){

                outStream.flush();
                outStream.close();
            }
        }
    }

    /**
     *
     * @param conn
     * @return
     * @throws Exception
     */
    private static String getResponse(HttpURLConnection conn) throws Exception {
        byte[] msgBody = null;

        if (null != conn && HttpStatus.SC_OK != conn.getResponseCode()) {
            throw new RuntimeException("url出错");
        }

        InputStream is = null;//获取返回数据
        BufferedReader in = null;
        try {
            // 获取响应流
            is = conn.getInputStream();

            in = new BufferedReader(new InputStreamReader(is, CHARSET_GBK));
            StringBuilder buffer = new StringBuilder();
            String line = "";
            while ((line = in.readLine()) != null) {
                buffer.append(line);
            }

            String responseStr = URLDecoder.decode(buffer.toString(), DEFAULT_CHARSET);

            return responseStr;
        } finally {

            if (null != is) {
                is.close();
            }

            if (null != in) {
                in.close();
            }

            if (null != conn) {
                conn.disconnect();
            }
        }
    }

    public static String sendPost(String requestUrl, String requestData){
        try {
            //xml=URLEncoder.encode(xml, "GBK");
            HttpClient client = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(requestUrl);
            // 设置连接超时时间(单位毫秒)
            HttpConnectionParams.setConnectionTimeout(client.getParams(), 20000);
            // 设置读数据超时时间(单位毫秒)
            HttpConnectionParams.setSoTimeout(client.getParams(), 300000);
            StringEntity reqEntity = new StringEntity(requestData, "UTF-8");
            httppost.setEntity(reqEntity);

            HttpResponse httpResponse = client.execute(httppost);

            if (httpResponse.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
                throw new Exception("系统异常, 与服务器响应失败:" + httpResponse.getStatusLine().getStatusCode());
            }

            HttpEntity entity = httpResponse.getEntity();
            if (entity == null) {
                throw new Exception("与服务器响应失败");
            }
            String returnxml = EntityUtils.toString(entity, "GBK");

            return returnxml;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

}
