package com.daoke.mobileserver.util;

/**
 * User: chenlong
 * Date: 2014/12/24
 * Time: 14:48
 */
public class ChongzhiAPI {

    //api key
    public static final String API_KEY ="Kbteu2TechSU0Dy6r9DPJj6W59ESgyiQzCUTtDYwLFfL9EFpB96ahZXaYqT5P3Kd";
    //md5 安全密钥
    public static final String SECRET_KEY="uuXNYK0yff9dJEtaBnbJcYK1dsUTkuWEOJhBjh1omZsWsMXUZYs2L3RPkBxuVjYz";

    //检查手机号
    public static final String ORDER_PHONE_CHECK_API ="http://api.huafeiduo.com/gateway.cgi";

    public static final String ORDER_PHONE_CHECK ="order.phone.check";

    //检查当前订单状态（实时询问运营商）
    public static final String ORDER_PHONE_STATUS_API ="http://api.huafeiduo.com/gateway.cgi?mod=order.phone.status";

    public static final String ORDER_PHONE_STATUS ="order.phone.status";

    //单接口，下单后接口会立即返回成功，如遇运营商维护等情况，会立即返回失败。
    public static final String ORDER_PHONE_SUBMIT_API ="http://api.huafeiduo.com/gateway.cgi";


    public static final String ORDER_PHONE_SUBMIT ="order.phone.submit";

}
