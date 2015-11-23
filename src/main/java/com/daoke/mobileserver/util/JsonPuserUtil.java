package com.daoke.mobileserver.util;

import net.sf.json.JSONObject;

public class JsonPuserUtil {

	public static String jsonToString(String errorcode, Object object) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("ERRORCODE", errorcode);
		jsonObject.put("RESULT", object);
		return jsonObject.toString();
	}

}
