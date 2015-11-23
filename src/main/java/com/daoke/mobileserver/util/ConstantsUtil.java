package com.daoke.mobileserver.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConstantsUtil {

	public static String appKey = "286302235";
	public static String secret = "CD5ED55440C21DAF3133C322FEDE2B63D1E85949";

	public static String iOSppKey = "184269830";
	public static String iOSSecret = "931E498698AB2D9B1D93F419E572D2ACCA981488";

	public static String ACCOUNTSID="aaf98fda4351e36201438a4c7c961049";
	public static String TOKEN="c0bde57aa2a64528938dec0b56eb0410";
	public static String APPID="aaf98fda4351e36201438b6f75a310cf";

	public static int NEED_VERIFY_OPINION = 1;
	public static int NO_NEED_VERIFY_OPINION = 0;
	public static int IS_WEME_ACCOUNT = 1;

    public static class Push{

	public static String PUSH_TITLE = "微密";

		//添加好友标题
		//消息简介
		public static final String ADD_FRIEND_ALERT = " 请求添加你为好友，快去看看吧！";
		//添加好友
		public static final String ADD_FRIEND_TYPE = "addFriend";

//		public static final String AGREE_ADD_FRIEND_ALERT = " 同意了你的好友申请，快去和TA聊天吧！";
		//同意添加好友
		public static final String AGREE_ADD_FRIEND_TYPE = "agreeAddFriend";

//		public static final String DIS_AGREE_ADD_FRIEND_ALERT = " 拒绝了你的好友申请，去通讯录再看看其他好友吧！";
		//拒绝添加好友
		public static final String DIS_AGREE_ADD_FRIEND_TYPE = "disAgreeAddFriend";

//		public static final String FRIEND_MESSAGE_ALERT = " 给你留言了，快去回复TA吧！";
		//聊天
		public static final String FRIEND_MESSAGE_TYPE = "talk";

//		public static final String JOIN_SECRET_CHANNEL_ALERT = " 【道客昵称】请求加入你创建的群聊频道【群聊频道昵称】，快去看看吧！";
		//申请加入频道
		public static final String JOIN_SECRET_CHANNEL_TYPE = "joinSecretChannel";

		public static final String AGREE_SECRET_CHANNEL_MESSAGE_ALERT = " 管理员同意您加入频道";
		//同意加入频道
		public static final String AGREE_SECRET_CHANNEL_MESSAGE_TYPE = "agreeSecretChannelMessage";

		public static final String DISAGREE_SECRET_CHANNEL_MESSAGE_ALERT = " 管理员不同意您加入频道";
		//拒绝加入频道
		public static final String DISAGREE_SECRET_CHANNEL_MESSAGE_TYPE = "disAgreeSecretChannelMessage";

//		public static final String QUIT_SECRET_CHANNEL_ALERT = " 退出了频道";
		//退出群聊频道
		public static final String QUIT_SECRET_CHANNEL_TYPE = "quitSecretChannel";
        //转移频道(给老管理员)
		public static final String TRANSFER_SECRET_CHANNEL_TO_ADMIN = "transferSecretChannel2Admin";
        //转移频道(给成员)
		public static final String TRANSFER_SECRET_CHANNEL_TO_MEMBER = "transferSecretChannel2Member";
        //解散频道(给老管理员)
		public static final String DISSOLVE_SECRET_CHANNEL_TO_ADMIN = "dissolveSecretChannel2Admin";
        //解散频道(给成员)
		public static final String DISSOLVE_SECRET_CHANNEL_TO_MEMBER = "dissolveSecretChannel2Member";
	}

	public static ConstantsUtil obj;

	public ConstantsUtil(String string) {
		obj = this;
	}

	public Map<Object, Object> currTypes;

	public static ConstantsUtil getObj() {
		return obj;
	}

	public static void setObj(ConstantsUtil obj) {
		ConstantsUtil.obj = obj;
	}

	public static String getAppKey(String appKey) {
		if ("iOS".equalsIgnoreCase(appKey)) {
			return ConstantsUtil.iOSppKey;
		} else if ("Android".equalsIgnoreCase(appKey)) {
			return ConstantsUtil.appKey;
		} else {
			return ConstantsUtil.appKey;
		}
	}

	public static String getSecret(String appKey) {
		if ("iOS".equalsIgnoreCase(appKey)) {
			return ConstantsUtil.iOSSecret;
		} else if ("Android".equalsIgnoreCase(appKey)) {
			return ConstantsUtil.secret;
		} else {
			return ConstantsUtil.secret;
		}
	}

	// 返回码
	public static final String AppVerification_OK = "9527";

	public static final String ERRORCODE_OK = "0";
	public static final String RESULT_OK = "ok";

	public static final String ERRORCODE_SERVICE_ERROR = "ME01001";
	public static final String RESULT_SERVICE_ERROR = "Service is error!";

	public static final String ERRORCODE_PARAMETERS_ERROR = "ME01023";// paramenter is error 请检查输入参数

	public static final String ERRORCODE_JSON_ERROR = "ME01006";//请检查输入参数
	public static final String RESULT_JSON_ERROR = "Json is error!";

	public static final String ERRORCODE_MEMBER_EMPTY_ERROR = "ME22004";
	public static final String RESULT_MEMBER_EMPTY_ERROR = "The team member is empty!";

	public static final String ERRORCODE_EMPTY_ERROR = "ME22005";
	public static final String RESULT_EMPTY_ERROR = "The content is empty!";

	public static final String ERRORCODE_NOT_EXISTS_NAME = "ME22006";
	public static final String RESULT_NOT_EXISTS_NAME = "Username is not exist in db!";

	public static final String ERRORCODE_VERICODE = "ME22007";
	public static final String RESULT_VERICODE = "The verificationCode is error!";

	public static final String ERRORCODE_UNBIND = "ME22008";
	public static final String RESULT_UNBIND = "The mobile phone number did not bind IMEI!";

	public static final String ERRORCODE_USER_EXIST = "ME22009";
	public static final String RESULT_USER_EXIST = "The user has exist!";

	public static final String ERRORCODE_USER_EXIST_OTHRE = "ME22010";
	public static final String RESULT_USER_EXIST_OTHER = "The user has joined other teams!";

	public static final String ERRORCODE_FIELD_EMPTY = "ME22011";
	
	public static final String ERRORCODE_PHONE = "ME22012";
	public static final String RESULT_PHONE = "The phone number length is less than 6!";


    public static final String ERRORCODE_SIGN_FAIL = "ME01019";//请阅读语镜公司提供的签名算法
    public static final String ERRORCODE_PHONE_NOT_BIND = "ME09007";
    public static final String RESULT_PHONE_NOT_BIND = "ME09007";

    //用户获取密码重置验证码--错误编码
    public static final String ERRORCODE_APPKEY_ERROR = "ME01002";//appKey需使用从语镜公司申请得到的appKey
    public static final String ERRORCODE_MYSQL_ERROR = "ME01020";//mysql failed! 请与公司客服联系
    public static final String ERRORCODE_REDIS_ERROR = "ME01021";//redis failed!请与公司客服联系
    public static final String ERRORCODE_INTERNAL_DATA_ERROR = "ME01022";//internal data error! 请与公司客服联系
    public static final String ERRORCODE_HTTP_BODY_NULL = "ME01024";//http body is null! 请检查输入参数
    public static final String ERRORCODE_HTTP_ERROR = "ME01025";//http failed! 请与公司客服联系
    public static final String ERRORCODE_USERNAME_NULL = "ME18061";//user name is not exist! 请用该手机号码注册道客账户
    public static final String ERRORCODE_VERIFYCODE_MATCH = "ME18912";//VerifyCode isn't match! 请输入正确的验证码
    public static final String ERRORCODE_VERIFYCODE_USED = "ME18913";//VerifyCode already used!  请重新获取一个验证码
    public static final String ERRORCODE_VERIFYCODE_GET = "ME18914";//Please get a verifyCode first! 请先获取验证码


	/**
	 * 返回常量
	 */
	public static final String ERRORCODE  ="ERRORCODE";
	public static final String RESULT  ="RESULT";

	/**
	 * Http状态码
	 */
	public class HttpStatusCode{
		//成功
		public static final int OK =200;

	}


    /**


    /**
     * 极光推送
     */
    public class JPush {
        public static final String APP_KEY = "984c766f15804e9a73e0a251";
        public static final String MASTER_SECRET = "23d78c543aec3b773de99124";
    }


    /**
     * 成就任务
     */
    public class Grade {
        /**
         * 绑定微密
         */
        public static final String BINDING_WEME = "bindWEME";
        /**
         * 设置昵称
         */
        public static final String SET_NICKNAME = "settingNickName";
        /**
         * 第一次周边吐槽
         */
        public static final String FIRST_PERIPHERY_TUCAO = "firstPeripheryTucao";
        /**
         * 第一次频道吐槽
         */
        public static final String FIRST_CHANNEL_TUCAO = "firstChannelTucao";
        /**
         * 第一次回复吐槽
         */
        public static final String FIRST_REPLY_TUCAO = "firstReplyTucao";

        /**
         * 等级达到10级
         */
        public static final String GRADE_10 = "grade10";
        /**
         * 等级达到20级
         */
        public static final String GRADE_20 = "grade20";
        /**
         * 等级达到30级
         */
        public static final String GRADE_30 = "grade30";
        /**
         * 等级达到40级
         */
        public static final String GRADE_40 = "grade40";
        /**
         * 等级达到50级
         */
        public static final String GRADE_50 = "grade50";
        /**
         * 等级达到60级
         */
        public static final String GRADE_60 = "grade60";

    }
   public static List<Map<String,String>> rankCofig = null;
    static {
        rankCofig = new ArrayList<Map<String, String>>();


        HashMap map =  new HashMap<String, String>() ;
        map.put("title","达标用时");
        map.put("type","2");
        map.put("directionType","1");  //系统页面
        map.put("url","");
        rankCofig.add(map);

        HashMap map1 =  new HashMap<String, String>() ;
        map1.put("title","捐献密点");
        map1.put("type","3");
        map1.put("directionType","1");  //系统页面
        map1.put("url","");
        rankCofig.add(map1);

        HashMap map7 =  new HashMap<String, String>() ;
        map7.put("title","驾驶天数");
        map7.put("type","4");
        map7.put("directionType","1");  //系统页面
        map7.put("url","");
        rankCofig.add(map7);

        HashMap map0 =  new HashMap<String, String>() ;
        map0.put("title","新增里程");
        map0.put("type","1");
        map0.put("directionType","1");  //系统页面
        map0.put("url","");
        rankCofig.add(map0);



        HashMap map3 =  new HashMap<String, String>() ;
        map3.put("title","驾驶评分");
        map3.put("type","5");
        map3.put("directionType","1");  //系统页面
        map3.put("url","");
        rankCofig.add(map3);

        HashMap map5 =  new HashMap<String, String>() ;
        map5.put("title","环保指数");
        map5.put("type","8");
        map5.put("directionType","1");  //系统页面
        map5.put("url","");
        rankCofig.add(map5);

        HashMap map4 =  new HashMap<String, String>() ;
        map4.put("title","任务指数");
        map4.put("type","7");
        map4.put("directionType","1");  //系统页面
        map4.put("url","");
        rankCofig.add(map4);

        HashMap map6 =  new HashMap<String, String>() ;
        map6.put("title","敬请期待");
        map6.put("type","");
        map6.put("directionType","2");  //html5
        map6.put("url","http://daoke.me");
        rankCofig.add(map6);



    }
    public static List<Map<String,String>> accountViewConfig = null;
    static {
        accountViewConfig = new ArrayList<Map<String, String>>();
        HashMap map =  new HashMap<String, String>() ;
        map.put("title","商城");
        map.put("type","1");
        map.put("directionType","2");  //系统页面
        map.put("url","");
        accountViewConfig.add(map);

        HashMap map1 =  new HashMap<String, String>() ;
        map1.put("title","购买车险");
        map1.put("type","2");
        map1.put("directionType","2");  //系统页面
        map1.put("url","");
        accountViewConfig.add(map1);

        HashMap map7 =  new HashMap<String, String>() ;
        map7.put("title","保险预购金");
        map7.put("type","4");
        map7.put("directionType","1");  //系统页面
        map7.put("url","");
        accountViewConfig.add(map7);

        HashMap map0 =  new HashMap<String, String>() ;
        map0.put("title","捐献社区");
        map0.put("type","1");
        map0.put("directionType","1");  //系统页面
        map0.put("url","");
        accountViewConfig.add(map0);

        HashMap map3 =  new HashMap<String, String>() ;
        map3.put("title","充值");
        map3.put("type","5");
        map3.put("directionType","1");  //系统页面
        map3.put("url","");
        accountViewConfig.add(map3);

        HashMap map5 =  new HashMap<String, String>() ;
        map5.put("title","提现");
        map5.put("type","8");
        map5.put("directionType","1");  //系统页面
        map5.put("url","");
        accountViewConfig.add(map5);


    }
}
