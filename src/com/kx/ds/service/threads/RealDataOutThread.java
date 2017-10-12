package com.kx.ds.service.threads;

import java.util.Date;

import org.apache.log4j.Logger;

import com.kx.ds.service.conf.Config;
import com.kx.ds.service.process.RealDataProcess;

public class RealDataOutThread implements Runnable {
	
	private static Logger logger = Logger.getLogger(RealDataOutThread.class);
	
	private long interval;

	private Config cof;
	/**
	 * 构造方法
	 * @param cycle 入库周期
	 * @param interval 初次入库等待时间
	 */
	public RealDataOutThread(Config cof,long interval){
		this.cof = cof;
		this.interval = interval;
	}
	
	@Override
	public void run() {
		try {
			Thread.sleep(interval);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		while(true){
			long time = System.currentTimeMillis();
			logger.info(this.cof.getType() + " 类型 入库线程开始");
			long millis = (long) (time - time % cof.getCycle());
			Date date = new Date(millis);
			RealDataProcess.writeDatabase(cof, date);
		    logger.info(this.cof.getType() + " 类型 入库完成,入库时间:" + date);
		    long cover = System.currentTimeMillis();
		    logger.info(this.cof.getType()+" 类型 入库消耗时间:" + (cover - time)+"ms");
		    long sleepTime = (long) (cof.getCycle() - (cover - time));
		    
		    try {
				Thread.sleep(sleepTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		
	}

	
}
