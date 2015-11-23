package com.daoke.mobileserver.util;

public class RandomSendNo {

   	private static final int MAX = Integer.MAX_VALUE;
	private static final int MIN = MAX/2;
	public static final String APP_KEY="984c766f15804e9a73e0a251";
	public static final String MASTER_SECRET = "23d78c543aec3b773de99124";
	
	/**
	 * 保持 sendNo 的唯一性是有必要的
	 * It is very important to keep sendNo unique.
	 * @return sendNo
	 */
	public static int getRandomSendNo() {
	    return (int) (MIN + Math.random() * (MAX - MIN));
	}

}
