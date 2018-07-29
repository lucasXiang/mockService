package com.ideacome.mock.MemCache;

import java.util.Locale;
import java.util.ResourceBundle;


public class Constant {
	
	/**
	 * 位数为单，测试版
	 * 位数为双，正式版
	 */
	public static final String version = "2.1.3";
	public static final ResourceBundle CACHE_RB = ResourceBundle.getBundle("config.cache", Locale.getDefault(), PushConfig.class.getClassLoader());

}
