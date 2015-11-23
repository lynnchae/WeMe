package com.daoke.mobileserver.test;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 测试https的访问
 * User: chenlong
 * Date: 2014/12/30
 * Time: 13:33
 */
public class TestHttps {

    static Logger log = LoggerFactory.getLogger(TestHttps.class);

    private static final String METHOD_POST = "POST";
    private static final String DEFAULT_CHARSET = "utf-8";



    public static void main(String args[]){
/*
        AtomicInteger a = new AtomicInteger();

        a.getAndIncrement();*/
        String str  ="version,ordertype,username,outerno,companyno,insuredperson,plcstartdate,plcenddate,licenseno,nolicenseflag,ownername,owneridno,ownercerttype,ownercertno,citycode,cardno,firstregisterdate,vehiclemodelname,vehicleid,vehicleframeno,engineno,vehicleinvoiceno,vehicleinvoicedate,runcardcertificatedate,forcebegindate,bizbegindate,taxvehicletype,fueltype,specialcarflag,specialcardate,persontaxcode,taxticketno,taxtickettype,taxbureauname,settledaddress,vehicleseats,averagemile,trafficviolation,bizquestion,bizanswer,forcequestion,forceanswer,vehiclecode,vehiclecodename,sessionid,trafficinsurance,traveltax,remark1,remark2,remark3,remark4,remark5,remark6,remark7,remark8,remark9,remark10,remark11,remark12,remark13,remark14,remark15,step,linkInfo_name,linkInfo_mobile,linkInfo_address,linkInfo_invoice,linkInfo_zipcode,linkInfo_email,linkInfo_paytype,linkInfo_realpaymode,insurInfo_name,insurInfo_certtype,insurInfo_certno,insurInfo_sex,insurInfo_birth,insurInfo_email,aplInfo_name,aplInfo_certtype,aplInfo_certno,aplInfo_sex,aplInfo_birth,aplInfo_email,businesspremium,forcepremium,vehicletaxamount,realpremium,totalremium,configbeforejudge";
        String[] strs = str.split(",");
        for (String str1 : strs){
            String str2=str1.replace(str1.charAt(0), (char)(str1.charAt(0)-32));
            System.out.print("ejsinoFormModel.get"+str2+"(),");
        }



     /*   Date a = new Date(123);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf.format(a);
        System.out.println(a.getTime());
        System.out.println( sdf.format(a));*/

     /*   TestHttps test = new TestHttps();
        try {
           String resp = test.doPost("https://192.168.11.93:1500/lua",null,DEFAULT_CHARSET,2000,1000);
            System.out.println(resp);
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }

    public static String doPost(String url, String params, String charset, int connectTimeout, int readTimeout) throws Exception {
        String ctype = "application/json;charset=" + charset;
        byte[] content = {};
        if(params != null){
            content = params.getBytes(charset);
        }

        return doPost(url, ctype, content, connectTimeout, readTimeout);
    }
    public static String doPost(String url, String ctype, byte[] content,int connectTimeout,int readTimeout) throws Exception {
        HttpsURLConnection conn = null;
        OutputStream out = null;
        String rsp = null;
        try {
            try{
                SSLContext ctx = SSLContext.getInstance("TLS");
                ctx.init(new KeyManager[0], new TrustManager[] {new DefaultTrustManager()}, new SecureRandom());
                SSLContext.setDefault(ctx);

                conn = getConnection(new URL(url), METHOD_POST, ctype);
                conn.setHostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String hostname, SSLSession session) {
                        return true;
                    }
                });
                conn.setConnectTimeout(connectTimeout);
                conn.setReadTimeout(readTimeout);
            }catch(Exception e){
                log.error("GET_CONNECTOIN_ERROR, URL = " + url, e);
                throw e;
            }
            try{
                out = conn.getOutputStream();
                out.write(content);
                rsp = getResponseAsString(conn);
            }catch(IOException e){
                log.error("REQUEST_RESPONSE_ERROR, URL = " + url, e);
                throw e;
            }

        }finally {
            if (out != null) {
                out.close();
            }
            if (conn != null) {
                conn.disconnect();
            }
        }

        return rsp;
    }

    private static class DefaultTrustManager implements X509TrustManager {

        @Override
        public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {}

        @Override
        public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {}

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }

    }

    private static HttpsURLConnection getConnection(URL url, String method, String ctype)
            throws IOException {
        HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
        conn.setRequestMethod(method);
        conn.setDoInput(true);
        conn.setDoOutput(true);
        conn.setRequestProperty("Accept", "text/xml,text/javascript,text/html");
        conn.setRequestProperty("User-Agent", "stargate");
        conn.setRequestProperty("Content-Type", ctype);
        return conn;
    }

    protected static String getResponseAsString(HttpURLConnection conn) throws IOException {
        String charset = getResponseCharset(conn.getContentType());
        InputStream es = conn.getErrorStream();
        if (es == null) {
            return getStreamAsString(conn.getInputStream(), charset);
        } else {
            String msg = getStreamAsString(es, charset);
            if (StringUtils.isEmpty(msg)) {
                throw new IOException(conn.getResponseCode() + ":" + conn.getResponseMessage());
            } else {
                throw new IOException(msg);
            }
        }
    }

    private static String getStreamAsString(InputStream stream, String charset) throws IOException {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream, charset));
            StringWriter writer = new StringWriter();

            char[] chars = new char[256];
            int count = 0;
            while ((count = reader.read(chars)) > 0) {
                writer.write(chars, 0, count);
            }

            return writer.toString();
        } finally {
            if (stream != null) {
                stream.close();
            }
        }
    }

    private static String getResponseCharset(String ctype) {
        String charset = DEFAULT_CHARSET;

        if (!StringUtils.isEmpty(ctype)) {
            String[] params = ctype.split(";");
            for (String param : params) {
                param = param.trim();
                if (param.startsWith("charset")) {
                    String[] pair = param.split("=", 2);
                    if (pair.length == 2) {
                        if (!StringUtils.isEmpty(pair[1])) {
                            charset = pair[1].trim();
                        }
                    }
                    break;
                }
            }
        }

        return charset;
    }
}
