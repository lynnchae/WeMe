package com.daoke.mobileserver.util;

import com.daoke.mobileserver.mall.WeMeMallCommonConfig;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

/**
 * Created by wangzp on 2014/12/1.
 * TODO:(MD5签名处理核心文件). <br/>
 */
public class MD5Signature {

    /**
     * 签名字符串
     * @param text 需要签名的字符串
     * @param key 密钥
     * @param input_charset 编码格式
     * @return 签名结果
     */
    public static String sign(String text, String key, String input_charset) {
        text = text + key;
        return DigestUtils.md5Hex(getContentBytes(text, input_charset));
    }

    /**
     * 签名字符串
     * @param text 需要签名的字符串
     * @param key 密钥
     * @return 签名结果
     */
    public static String sign(String text, String key) {

        return sign(text, key, "utf-8");
    }

    /**
     * 签名字符串
     * @param text 需要签名的字符串
     * @param sign 签名结果
     * @param key 密钥
     * @param input_charset 编码格式
     * @return 签名结果
     */
    public static boolean verify(String text, String sign, String key, String input_charset) {
        text = text + key;
        String mysign = DigestUtils.md5Hex(getContentBytes(text, input_charset));
        if(mysign.equals(sign)) {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * 签名字符串
     * @param text 需要签名的字符串
     * @param sign 签名结果
     * @param key 密钥
     * @return 签名结果
     */
    public static boolean verify(String text, String sign, String key) {
        return verify(text, sign, key, "utf-8");
    }

    /**
     * @param content
     * @param charset
     * @return
     * @throws java.security.SignatureException
     * @throws java.io.UnsupportedEncodingException
     */
    private static byte[] getContentBytes(String content, String charset) {
        if (charset == null || "".equals(charset)) {
            return content.getBytes();
        }
        try {
            return content.getBytes(charset);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("MD5签名过程中出现错误,指定的编码集不对,您目前指定的编码集是:" + charset);
        }
    }


    /**
     * 商城校验签名
     * @param params
     * @return
     * @author wangzp
     * @date Jun 5, 2014 3:47:55 PM
     */
    public static boolean verifyMall(Map<String, String> params) {
        boolean isSign = false;
        try {
            String sign = params.get("sign") != null ? params.get("sign") : "";
            String sign_type = params.get("sign_type") != null ? params.get("sign_type") : "";

            // 过滤空值、sign与sign_type参数
            Map<String, String> sParaNew = ParameterUtil.paraFilter(params);
            // 获取待签名字符串
            String preSignStr = ParameterUtil.getSignData(sParaNew);

            // 获得签名验证结果
            if(StringUtils.equalsIgnoreCase(WeMeMallCommonConfig.SIGN_TYPE_MD5, sign_type)) {
                isSign = MD5Signature.verify(preSignStr, sign, WeMeMallCommonConfig.WEME_MALL_MD5_KEY);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isSign;
    }



    /**
     * md5或者sha-1加密
     *
     * @param inputText     要加密的内容
     * @param algorithmName 加密算法名称：md5或者sha-1，不区分大小写
     * @return
     */
     public static String encryptImage(String inputText, String algorithmName) {
        if (inputText == null || "".equals(inputText.trim())) {
            throw new IllegalArgumentException("请输入要加密的内容");
        }
        if (algorithmName == null || "".equals(algorithmName.trim())) {
            algorithmName = "md5";
        }
        String encryptText = null;
        try {
            MessageDigest m = MessageDigest.getInstance(algorithmName);
            m.update(inputText.getBytes("UTF8"));
            byte s[] = m.digest();
            // m.digest(inputText.getBytes("UTF8"));
            return hex(s);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return encryptText;
    }

    // 返回十六进制字符串
    private static String hex(byte[] arr) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < arr.length; ++i) {
            sb.append(Integer.toHexString((arr[i] & 0xFF) | 0x100).substring(1,
                    3));
        }
        return sb.toString();
    }

}
