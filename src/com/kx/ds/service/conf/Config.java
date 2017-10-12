package com.kx.ds.service.conf;

import java.util.Map;

public interface Config {
	/**
	 * 获得配置Map
	 * @return
	 */
	Map<String,String> getConfigMap();
	/**
	 * 获得类型
	 * @return
	 */
	String getType();
	/**
	 * 获得表名
	 * @return
	 */
	String getTable();
	
	/**
	 * 入库周期
	 * @return
	 */
	Double getCycle();
	/**
	 * 获得历史库id
	 * @return
	 */
	String getId();
	/**
	 * 获得历史库日期字段
	 * @return
	 */
	String getDate();
	/**
	 * 获得设备编号范围配置
	 * @return
	 */
	String getIdRange();
	/**获得电站id字段*/
	String getPsid();
	/**获取电站编号范围*/
	String getPsRange();
}
