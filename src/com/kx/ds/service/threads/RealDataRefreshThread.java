package com.kx.ds.service.threads;

import java.util.Iterator;

import org.apache.log4j.Logger;
import org.bson.Document;

import com.kx.ds.service.def.DSDef;
import com.kx.ds.service.managers.RamManager;
import com.kx.ds.utils.ServiceBean;
import com.kx.mongo.services.MongoDao;
import com.kx.mongo.services.MongoDef;

/**
 * 实时数据刷新线程
 * @author ml
 *
 */
public class RealDataRefreshThread implements Runnable {
	
	private static Logger logger = Logger.getLogger(RealDataRefreshThread.class);
	
	private MongoDao mongoDao = ServiceBean.getBean("mongoDao");
	
	@Override
	public void run() {
		while(true){
			Iterator<Document> its = mongoDao.findAll(MongoDef.REALDATA);
			while(its.hasNext()){
				try{
					Document doc = its.next();
					Object id = doc.get(DSDef._ID);
					Object value = doc.get("value");
					RamManager.putValue(id+"", value);
				}catch(Exception e){
					e.printStackTrace();
					logger.info("实时数据刷新线程出错！"+ e.getMessage());
				}
			}
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
