package com.ideacome.mock.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileOperateUtil {
	
	public static String jsonFile2String(String path) throws Exception {
		String absolutePath = FileOperateUtil.class.getClassLoader().getResource(path).getPath();
		BufferedReader br = null;
		StringBuffer sb = new StringBuffer();
		try {
			br = new BufferedReader(new FileReader(new File(absolutePath)));
			
			String sname = null;  
            while ((sname = br.readLine()) != null) {  
                sb.append(sname);
            }
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally {
			if(br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		return sb.toString();
	}
	
	public static String defaultJsonFile2String() throws Exception {
		return jsonFile2String("json/rule.json");
	}
	
	public static void main(String[] args) throws Exception {
		System.out.println(jsonFile2String("json/rule.json"));
		
	}
}
