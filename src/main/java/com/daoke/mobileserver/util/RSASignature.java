//package com.daoke.mobileserver.util;
//
//import org.bouncycastle.jce.provider.BouncyCastleProvider;
//
//import javax.crypto.BadPaddingException;
//import javax.crypto.Cipher;
//import javax.crypto.IllegalBlockSizeException;
//import javax.crypto.NoSuchPaddingException;
//import java.io.ByteArrayInputStream;
//import java.io.ByteArrayOutputStream;
//import java.io.InputStream;
//import java.security.*;
//import java.security.interfaces.RSAPrivateKey;
//import java.security.interfaces.RSAPublicKey;
//import java.security.spec.PKCS8EncodedKeySpec;
//import java.security.spec.X509EncodedKeySpec;
//
//
///**
// * @author  陈龙
// *  Rsa签名工具类
// */
//public class RSASignature {
//
//    private static final String  SIGN_ALGORITHMS = "SHA1WithRSA";
//
//    private static final String DEFAULT_CHARSET = "utf-8";
//
//	/**
//	* 解密
//	* @param content 密文
//	* @param privateKey 商户私钥
//	* @return 解密后的字符串
//
//	*/
//	public static String decrypt(String content, String privateKey) throws Exception {
//        PrivateKey prikey = getPrivateKey(privateKey);
//
//        Cipher cipher = Cipher.getInstance("RSA");
//        cipher.init(Cipher.DECRYPT_MODE, prikey);
//
//        InputStream ins = new ByteArrayInputStream(Base64.decode(content));
//        ByteArrayOutputStream writer = new ByteArrayOutputStream();
//        //rsa解密的字节大小最多是128，将需要解密的内容，按128位拆开解密
//        byte[] buf = new byte[128];
//        int bufl;
//
//        while ((bufl = ins.read(buf)) != -1) {
//            byte[] block = null;
//
//            if (buf.length == bufl) {
//                block = buf;
//            } else {
//                block = new byte[bufl];
//                for (int i = 0; i < bufl; i++) {
//                    block[i] = buf[i];
//                }
//            }
//
//            writer.write(cipher.doFinal(block));
//        }
//
//        return new String(writer.toByteArray(), DEFAULT_CHARSET);
//    }
//
//
//
//    /**
//     * 加密过程
//     *
//     * @param publicKey
//     *            公钥
//     * @param plainTextData
//     *            明文数据
//     * @return
//     * @throws Exception
//     *             加密过程中的异常信息
//     */
//    public static byte[] encrypt(RSAPublicKey publicKey, byte[] plainTextData)
//            throws Exception {
//        if (publicKey == null) {
//            throw new Exception("加密公钥为空, 请设置");
//        }
//        Cipher cipher = null;
//        try {
//            cipher = Cipher.getInstance("RSA", new BouncyCastleProvider());
//            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
//            byte[] output = cipher.doFinal(plainTextData);
//            return output;
//        } catch (NoSuchAlgorithmException e) {
//            throw new Exception("无此加密算法");
//        } catch (NoSuchPaddingException e) {
//            e.printStackTrace();
//            return null;
//        } catch (InvalidKeyException e) {
//            throw new Exception("加密公钥非法,请检查");
//        } catch (IllegalBlockSizeException e) {
//            throw new Exception("明文长度非法");
//        } catch (BadPaddingException e) {
//            throw new Exception("明文数据已损坏");
//        }
//    }
//
//
//    /**
//     * 解密过程
//     *
//     * @param privateKey
//     *            私钥
//     * @param cipherData
//     *            密文数据
//     * @return 明文
//     * @throws Exception
//     *             解密过程中的异常信息
//     */
//    public static byte[] decrypt(RSAPrivateKey privateKey, byte[] cipherData)
//            throws Exception {
//        if (privateKey == null) {
//            throw new Exception("解密私钥为空, 请设置");
//        }
//        Cipher cipher = null;
//        try {
//            cipher = Cipher.getInstance("RSA", new BouncyCastleProvider());
//            cipher.init(Cipher.DECRYPT_MODE, privateKey);
//            byte[] output = cipher.doFinal(cipherData);
//            return output;
//        } catch (NoSuchAlgorithmException e) {
//            throw new Exception("无此解密算法");
//        } catch (NoSuchPaddingException e) {
//            e.printStackTrace();
//            return null;
//        } catch (InvalidKeyException e) {
//            throw new Exception("解密私钥非法,请检查");
//        } catch (IllegalBlockSizeException e) {
//            throw new Exception("密文长度非法");
//        } catch (BadPaddingException e) {
//            throw new Exception("密文数据已损坏");
//        }
//    }
//
//
//	/**
//
//	* 得到私钥
//
//	* @param key 密钥字符串（经过base64编码）
//
//	* @throws Exception
//
//	*/
//
//	private static PrivateKey getPrivateKey(String key) throws Exception {
//
//		byte[] keyBytes;
//
//		keyBytes = Base64.decode(key);
//
//		PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
//
//		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
//
//		PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
//
//		return privateKey;
//
//	}
//
//
//	/**
//	* RSA签名
//	* @param content 待签名数据
//	* @param privateKey 商户私钥
//	* @param encode 字符集编码
//	* @return 签名值
//	*/
//	public static String sign(String content, String privateKey, String encode) {
//        if(null == encode || "".equals(encode)){
//            encode = DEFAULT_CHARSET;
//        }
//        try
//        {
//        	PKCS8EncodedKeySpec priPKCS8 	= new PKCS8EncodedKeySpec( Base64.decode(privateKey) );
//        	KeyFactory keyf 				= KeyFactory.getInstance("RSA");
//        	PrivateKey priKey 				= keyf.generatePrivate(priPKCS8);
//
//            java.security.Signature signature = java.security.Signature.getInstance(SIGN_ALGORITHMS);
//
//            signature.initSign(priKey);
//            signature.update( content.getBytes(encode));
//
//            byte[] signed = signature.sign();
//
//            return Base64.encode(signed);
//        }
//        catch (Exception e)
//        {
//        	e.printStackTrace();
//        }
//
//        return null;
//    }
//
//
//    /**
//     * RSA签名
//     * @param content 待签名数据
//     * @param privateKey 商户私钥
//     * @return 签名值
//     */
//    public static String sign(String content, String privateKey) {
//        return sign(content, privateKey, DEFAULT_CHARSET);
//    }
//
//
//	/**
//	* RSA验签名检查
//	* @param content 待签名数据
//	* @param sign 签名值
//	* @param publicKey 支付宝公钥
//	* @param encode 字符集编码
//	* @return 布尔值
//	*/
//	public static boolean verify(String content, String sign, String publicKey,String encode) {
//        if(null == encode || "".equals(encode)){
//            encode = DEFAULT_CHARSET;
//        }
//
//		try
//		{
//			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
//	        byte[] encodedKey = Base64.decode(publicKey);
//	        PublicKey pubKey = keyFactory.generatePublic(new X509EncodedKeySpec(encodedKey));
//
//
//			java.security.Signature signature = java.security.Signature
//			.getInstance(SIGN_ALGORITHMS);
//
//			signature.initVerify(pubKey);
//			signature.update( content.getBytes(encode) );
//
//			boolean bverify = signature.verify( Base64.decode(sign) );
//			return bverify;
//
//		}
//		catch (Exception e)
//		{
//			e.printStackTrace();
//		}
//
//		return false;
//	}
//	/**
//	* RSA验签名检查
//	* @param content 待签名数据
//	* @param sign 签名值
//	* @param publicKey 支付宝公钥
//	* @return 布尔值
//	*/
//	public static boolean verify(String content, String sign, String publicKey) {
//        return verify(content, sign, publicKey, DEFAULT_CHARSET);
//	}
//
//}
