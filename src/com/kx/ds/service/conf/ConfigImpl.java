package com.kx.ds.service.conf;

import java.util.Map;

public class ConfigImpl implements Config {
	/**逆变器实时库历史库对应信息*/
	private Map<String,String> configMap;
	/**设备类型*/
	private String type;
	/**入库表名*/
	private String table;
	/**入库周期*/
	private Double cycle;
	
	private String idRange;
	
	private String id;
	
	private String date;
	
	private String psid;
	
	private String psRange;
	@Override
	public Map<String, String> getConfigMap() {
		return configMap;
	}
	@Override
	public String getType() {
		return type;
	}
	@Override
	public String getTable() {
		return table;
	}
	@Override
	public Double getCycle() {
		return cycle;
	}
	public void setConfigMap(Map<String, String> configMap) {
		this.configMap = configMap;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void setTable(String table) {
		this.table = table;
	}

	public void setCycle(Double cycle) {
		this.cycle = cycle * 60 * 1000;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getIdRange() {
		return idRange;
	}
	public void setIdRange(String idRange) {
		this.idRange = idRange;
	}
	@Override
	public String getPsid() {
		return psid;
	}
	public void setPsid(String psid) {
		this.psid = psid;
	}
	@Override
	public String getPsRange() {
		return psRange;
	}
	public void setPsRange(String psRange) {
		this.psRange = psRange;
	}
	
}
