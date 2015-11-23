package com.daoke.mobileserver.user.service;

/**
 * Created by zhangyaqin on 2015/5/27.
 */
public interface IUserResetPasswordService {
    /**
     * 用户获取密码重置验证码
     *
     * @param appKey                      应用标识
     * @param secret                      安全签名
     * @param mobile                      手机号码
     * @param resetPasswordInitVerifyCode
     * @return
     * @throws Exception
     */
    public String resetPasswordInitVerifyCode(String appKey, String secret, String mobile, String resetPasswordInitVerifyCode) throws Exception;

    /**
     * 手机用户根据验证码重置新密码
     *
     * @param appKey	应用标识
     * @param secret	安全签名
     * @param mobile	手机号码
     * @param verifyCode 验证码
     * @param newPassword 新密码
     * @param resetPasswordCheckVerifyCode 接口
     * @return
     * @throws Exception
     */
    public String resetPasswordCheckVerifyCode(String appKey, String secret, String mobile, String verifyCode, String newPassword, String resetPasswordCheckVerifyCode) throws Exception;

}
