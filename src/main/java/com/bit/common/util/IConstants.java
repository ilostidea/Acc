/**
 * 2016年6月8日
 * @author Zhou Liang
 */
package com.bit.common.util;

public interface IConstants {

	/**
	 * 保存在session中的user信息对应的key的名称
	 */
	public static String CURRENT_USER_SESSION_KEY = "currentUser";

	/**
	 * 密碼校驗次數
	 */
	public static int PASSWORD_RETRY_LIMIT_TIMES = 5;
	
	/**
	 * 密碼校驗次數
	 */
	public static String SHIRO_SESSION_USER = "sysUser";

	/**
	 * 正则表达式：验证用户名
	 */
	public static final String REGEX_USERNAME = "^[a-zA-Z]\\w{5,17}$";

	/**
	 * 正则表达式：验证密码
	 */
	public static final String REGEX_PASSWORD = "^[a-zA-Z0-9]{6,16}$";

	/**
	 * 正则表达式：验证手机号
     * 手机号码: 
     * 13[0-9], 14[5,7], 15[0, 1, 2, 3, 5, 6, 7, 8, 9], 17[6, 7, 8], 18[0-9], 170[0-9]
     * 移动号段: 134,135,136,137,138,139,150,151,152,157,158,159,182,183,184,187,188,147,178,1705
     * 联通号段: 130,131,132,155,156,185,186,145,176,1709
     * 电信号段: 133,153,180,181,189,177,1700
     */
	public static final String REGEX_MOBILE = "^((1[3,5,8][0-9])|(14[5,7])|(17[0,1,6,7,8]))\\d{8}$";

	/**
	 * 正则表达式：验证邮箱
	 */
	public static final String REGEX_EMAIL = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";

	/**
	 * 正则表达式：验证汉字
	 */
	public static final String REGEX_CHINESE = "^[\u4e00-\u9fa5],{0,}$";

	/**
	 * 正则表达式：验证身份证
	 */
	public static final String REGEX_ID_CARD = "(^\\d{18}$)|(^\\d{15}$)";
	
	/**
	 * 正则表达式：验证QQ
	 */
	public static final String REGEX_QQ = "^\\d+$";//^\d{5,10}$
	
	/**
	 * 正则表达式：验证Wechat
	 */
	public static final String REGEX_WECHAT = "^[a-zA-Z\\d_]{5,}$";

	/**
	 * 正则表达式：验证URL
	 */
	public static final String REGEX_URL = "http(s)?:\\([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?";

	/**
	 * 正则表达式：验证IP地址
	 */
	public static final String REGEX_IP_ADDR = "(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)";
	
	/**
	 * 正则表达式：验证邮编
	 */
	public static final String REGEX_POSTCODE = "(^[1-9]\\d{5}$)|(^$)";
	
	/**
	 * 正则表达式：手机或邮箱，可以为空
	 */
	public static final String REGEX_PHONE = "^((0[0-9]{2,3}\\-)?([2-9][0-9]{6,7})+(\\-[0-9]{1,3})?$)|(^((1[3,5,8][0-9])|(14[5,7])|(17[0,1,6,7,8]))\\d{8}$)|(^$)";
}
