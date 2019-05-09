package com.aeothod.utils;

import java.util.List;

import javafx.collections.ObservableList;

/**
 * @author weijian.wu
 * @description:工具类
 * @date 2019年4月11日 上午10:41:31
 */
public class BussinessUtils {
	/**
	 * @description:字符串是否为空(null以及lenth=0)
	 * @param in
	 * @return
	 */
	public static boolean isEmpty(String in) {
		boolean flag = true;
		if (null != in && in.trim().length() > 0) {
			flag = false;
		}
		return flag;
	}

	@SuppressWarnings("rawtypes")
	public static boolean isEmpty(List list) {
		boolean flag = true;
		if (null != list && list.size() > 0) {
			flag = false;
		}
		return flag;
	}

	@SuppressWarnings("rawtypes")
	public static boolean isEmpty(ObservableList list) {
		boolean flag = true;
		if (null != list && list.size() > 0) {
			flag = false;
		}
		return flag;
	}

	/**
	 * @author fengxing.wen
	 * @description: 拆分错误信息得到错误码
	 * @param list
	 * @return
	 */
	public static String getErrorCode(String message) {
		if (BussinessUtils.isEmpty(message)) {
			return "Error!";
		}
		 String codes = message.substring(message.indexOf("(")+1, message.indexOf(")"));
		 codes.split(" ");
		 String code =codes.split(" ")[1];
		 System.out.println(codes.split(" ")[1]);
		/* ege: */
//		String message = "PT60 The maximum allowable weight of the reel is 75KG!";
//		String codes = message.substring(message.indexOf("(") + 1, message.indexOf(")"));
//		codes.split(" ");
//		String code = codes.split(" ")[1];

		System.out.println(codes.split(" ")[1]);
		return code;
	}

	/**
	 * @description: 拼接报错信息
	 * @param message
	 * @return
	 */
	public static String getWeight(String message) {
		String firstMessage = message.substring(0, message.lastIndexOf("KG"));
		System.out.println(firstMessage.split(" ")[firstMessage.split(" ").length - 1]);
		return firstMessage.split(" ")[firstMessage.split(" ").length - 1];
	}
}
