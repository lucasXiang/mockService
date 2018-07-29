package com.ideacome.mock.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	private static String pattern = "yyyy-MM-dd HH:mm:SS";
	public static String date2Str(Date date,String pattern){
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(date);
	}
	public static String defaultDate2Str(Date date){
		return date2Str(date,pattern);
	}
	
	public static void main(String[] args) {
		System.out.println(Integer.valueOf(12).toString());
	}
}
