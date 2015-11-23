package com.daoke.mobileserver.ejsino;

/**
 * Created by wangzp on 2014/12/9.
 * 电子车险枚举值
 */
public  interface EjsinoConstants {

    /**
     * 证件类型
     */
    public class CertificateType {

        /**
         * 居民身份证
         */
        public static final int IDENTITY_CARD = 1;
        /**
         * 军官证
         */
        public static final int OFFICER_CARD = 2;
        /**
         * 护照
         */
        public static final int PASSPORT = 3;
        /**
         * 港澳回乡证或台胞证
         */
        public static final int HOME_VISIT_PERMIT = 4;
        /**
         * 驾驶证
         */
        public static final int DRIVING_LICENSE = 5;
        /**
         * 其他
         */
        public static final int OTHER = 99;

    }

    /**
     * 保险公司代码
     */
    public class InsuranceCompanyCode {

        /**
         * 太平洋保险
         */
        public static final String PACIFIC = "0012";
        /**
         * 天平保险
         */
        public static final String TPAIC = "0021";
        /**
         * 阳光保险
         */
        public static final String SUNSHINE = "0020";
        /**
         * 中华联合
         */
        public static final String CHINA_UNITED = "0099";

    }

    /**
     * 请求类型
     */
    public class RequestTypes {

        /**
         * 车型查询
         */
        public static final String CAR_MODEL_QUERY = "7";
        /**
         * 获取报价要素
         */
        public static final String OFFER_ELEMENTS_QUERY = "G";
        /**
         * 报价
         */
        public static final String OFFERING_LETTER = "0";
        /**
         * 投保确认
         */
        public static final String SURANCE_CONFIRMATION = "6";
        /**
         * 申请购买
         */
        public static final String APPLY_BUY = "2";

        /**
         * 支付
         */
        public static final String PAY = "P";
    }

    /**
     * 响应状态吗
     */
    public class ResponseStatus {

        /**
         * 成功
         */
        public static final int SUCCESS = 0;
        /**
         * 失败
         */
        public static final int FAIL = 1;
        /**
         * 补录要素信息
         */
        public static final int COMPLETE_ELEMENTS = 2;

    }

    /**
     * 支付方式
     */
    public class PayType {

        /**
         * 线上支付
         */
        public static final int PAY_ONLINE = 1;

        /**
         * 线下支付
         */
        public static final int PAY_OFFLINE = 2;
    }

    /**
     * 付款类型
     */
    public class PayMode {

        /**
         * 现金支付
         */
        public static final int CASH = 2;

        /**
         * Pos拉卡
         */
        public static final int POS = 3;
    }

    /**
     * 车牌号
     */
    public class LicenseFlag {
        /**
         * 有车牌号
         */
        public static final int HAVE = 0;

        /**
         * 没有车牌号
         */
        public static final int NONE = 1;
    }
}
