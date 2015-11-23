package com.daoke.mobileserver.user.service.impl;

import com.daoke.mobileserver.user.service.IUserResetPasswordService;
import com.daoke.mobileserver.util.Sha1;
import org.springframework.stereotype.Service;

/**
 * Created by zhangyaqin on 2015/5/27.
 */
@Service
public class UserResetPasswordServiceImpl implements IUserResetPasswordService {
    @Override
    public String resetPasswordInitVerifyCode(String appKey, String secret, String mobile, String resetPasswordInitVerifyCode) throws Exception {
        String[] keys={"appKey","secret","mobile"};
        Object[] values ={appKey,secret,mobile};
        String result = Sha1.httpPost(keys, values, resetPasswordInitVerifyCode);
        return result;
    }

    @Override
    public String resetPasswordCheckVerifyCode(String appKey, String secret, String mobile, String verifyCode, String newPassword, String resetPasswordCheckVerifyCode) throws Exception {
        String[] keys = {"appKey","secret","mobile","verifyCode","newPassword"};
        Object[] values = {appKey,secret,mobile,verifyCode,newPassword};
        String result = Sha1.httpPost(keys,values,resetPasswordCheckVerifyCode);
        return  result;
    }
}
