package com.daoke.mobileserver.util;

public class AppVerification {
	public static String verification(String[] content, Object... args) {
		for (int i = 0; i < content.length; i++) {
			if (args[i] == null || ("").equals(args[i])) {
				return JsonPuserUtil.jsonToString(ConstantsUtil.ERRORCODE_PARAMETERS_ERROR, content[i] + " can not be empty!");
			} else if (("appKey").equals(content[i])) {
				if (args[i].toString().length() > 10 || args[i].toString().length() < 1) {
					return JsonPuserUtil.jsonToString(ConstantsUtil.ERRORCODE_PARAMETERS_ERROR, content[i] + " should in 1~10!");
				}
			} else if (("accountID").equals(content[i])) {
				if (args[i].toString().length() > 10 || args[i].toString().length() < 10) {
					return JsonPuserUtil.jsonToString(ConstantsUtil.ERRORCODE_PARAMETERS_ERROR, content[i] + "should be equals 10");
				}
			} else if (("groupgroupAbbreviation").equals(content[i])) {
				if (args[i].toString().length() >= 17 || args[i].toString().length() < 0) {
					return JsonPuserUtil.jsonToString(ConstantsUtil.ERRORCODE_PARAMETERS_ERROR, content[i] + " should in 1~16");
				}
			} else if (("username").equals(content[i])) {
				if (args[i].toString().length() > 16 || args[i].toString().length() < 0) {
					return JsonPuserUtil.jsonToString(ConstantsUtil.ERRORCODE_PARAMETERS_ERROR, content[i] + " should in 1~16");
				}
			}
		}
		return ConstantsUtil.AppVerification_OK;
	}
}
