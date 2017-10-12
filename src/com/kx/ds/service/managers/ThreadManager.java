package com.kx.ds.service.managers;

import java.util.Map;

import org.apache.log4j.Logger;

import com.kx.ds.service.conf.Config;
import com.kx.ds.service.threads.RealDataOutThread;
import com.kx.ds.service.threads.RealDataRefreshThread;

public class ThreadManager {
	
	private static Logger logger = Logger.getLogger(ThreadManager.class);
	/**线程异常睡眠时间*/
	public static final long RESTART = 20000;
	/**线程刷新随眠时间*/
	public static final long SLEEP = 1000;
	
	
	/**所有设备类型配置*/
	private Map<String,Config> configMap;

	public Map<String, Config> getConfigMap() {
		return configMap;
	}

	public void setConfigMap(Map<String, Config> configMap) {
		this.configMap = configMap;
	}
	
	public void startThread(){
		//启动实时数据刷新线程
		new Thread(new RealDataRefreshThread()).start();
		logger.info("实时数据刷新线程启动完成！");
		//启动设备数据周期入库线程
		for(Map.Entry<String,Config> oneConfig : configMap.entrySet()){
			Config cof = oneConfig.getValue();
			long now = System.currentTimeMillis();
			long Interval =  (long) (cof.getCycle() - ( now % cof.getCycle()));
			RealDataOutThread outThread = new RealDataOutThread(cof,Interval);
			new Thread(outThread,oneConfig.getKey()).start();
			logger.info("周期入库线程启动："+cof.getType());
		} 
		logger.info("周期入库线程启动完成！");
	}
	
}
