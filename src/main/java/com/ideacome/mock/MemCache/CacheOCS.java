package com.ideacome.mock.MemCache;

import java.io.IOException;

import net.spy.memcached.AddrUtil;
import net.spy.memcached.BinaryConnectionFactory;
import net.spy.memcached.MemcachedClient;



public class CacheOCS {

	private static final String host = Constant.CACHE_RB.getString("main.memcached.host").trim(); //

	private static final String port = Constant.CACHE_RB.getString("main.memcached.port").trim(); //

	private static MemcachedClient cache = null;

	/** 缓存1分钟 **/
	public final static int Minute = 60;
	/** 缓存1小时 **/
	public final static int Hour = 60 * 60;
	/** 缓存1天 **/
	public final static int Day = 60 * 60 * 24;
	/** 长期缓存 **/
	public final static int Ever = 0;

	static {
		if (cache == null) {
			try {
				cache = new MemcachedClient(new BinaryConnectionFactory(), AddrUtil.getAddresses(host + ":" + port));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	/** 将对象放入缓存 ***/
	public static void cacheSet(String table, String key, Object value, int time) {
		if (table != null && key != null)
			if (table.trim().length() > 0)
				cache.set(table + "_" + key, time, value);
	}

	/** 读取缓存数据 ***/
	public static Object cacheGet(String table, String key) {
		if (table != null && key != null)
			if (table.trim().length() > 0)
				return cache.get(table + "_" + key);
		return null;
	}

	/** 读取缓存数据 ***/
	public static Object cacheGetAndTouch(String table, String key, int exp) {
		if (table != null && key != null)
			if (table.trim().length() > 0)
				return cache.getAndTouch(table + "_" + key, exp);
		return null;
	}
	
	public static long cacheIncr(String table, String key, int by) {
		if (table != null && key != null)
			if (table.trim().length() > 0)
				return cache.incr(table + "_" + key, by);
		return -1;
	}

	public static void cacheDelete(String table, String key) {
		if (table != null && key != null)
			if (table.trim().length() > 0)
				cache.delete(table + "_" + key);
	}
	
	public static void main(String[] args) {

		// cache.set("1", CacheOCS.Day, "ddddddddddddddddddddd");

		System.out.print(cache.get("1"));

	}

}
