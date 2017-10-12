package com.kx.ds.service.managers;

import java.util.HashMap;
import java.util.Map;

public class RamManager {
	
	
	
	/**所有实时数据*/
	private static Map<String, Object> realData = new HashMap<String, Object>();
	
	
	/**
	 * 放值
	 * @param key
	 * @param value
	 */
	public static void putValue(String key,Object value){
		realData.put(key, value);
	}
	
	/**
	 * 获得值ֵ
	 * @param key
	 * @param value
	 * @return
	 */
	public static Object getValue(String key){
		return realData.get(key);
	}
}
