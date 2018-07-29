package com.ideacome.mock.cache;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.ideacome.mock.utils.DateUtil;

public class CacheOS {
	private static Map<String,MetaData> mapList = new HashMap<>();
	
	public static MetaData buildMetaData(Date timeStamp,Object data){
		MetaData metaData = new MetaData();
		metaData.setTimeStamp(DateUtil.defaultDate2Str(timeStamp));
		metaData.setData(data);
		return metaData;
	}
	public static void put(String key,MetaData value){
		mapList.put(key, value);
	}
	public static void put(String key,Date timeStamp,Object data){
		put(key,buildMetaData(timeStamp,data));
	}
	public static MetaData getMetaData(String key){
		return mapList.get(key);
	}
	
	public static Map<String,MetaData> getMapList(){
		return mapList;
	}
}

