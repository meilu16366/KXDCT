package com.kx.ds.service.process;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.log4j.Logger;

import com.kx.ds.jdbc.ConnectionFatory;
import com.kx.ds.service.conf.Config;
import com.kx.ds.service.managers.RamManager;
import com.kx.ds.utils.ServiceBean;

public class RealDataProcess {
	
	private static Logger logger = Logger.getLogger(RealDataProcess.class);
	
	private static ConnectionFatory fc = ServiceBean.getBean("connectionFatory");
	
	
	public static void writeDatabase(Config cof,Date date) {
		if(StringUtils.isNotEmpty(cof.getPsid())) {
			String[] psidd = cof.getPsRange().split(",");
			for(String psids: psidd) {
				String[] total = psids.split("-");
				int snumber = NumberUtils.toInt(total[0]);
				int enumber = NumberUtils.toInt(total.length == 2?total[1]:total[0]);
				while(snumber<=enumber) {
					writeDatabaseOne(cof,date,snumber+"");
					snumber ++;
				}
			}
		}else {
			writeDatabaseOne(cof,date,null);
		}
	}
	
	
	/**
	 * 写入数据
	 * @param cof 配置信息
	 * @param date 入库时间
	 */
	public static void writeDatabaseOne(Config cof,Date date,String psid){
		Connection conn = null;
		try{
			conn = fc.getConnection();
			String first = "insert into "+cof.getTable();
			
			
			String idRange = cof.getIdRange();
			String[] ranges = idRange.split(",");
			PreparedStatement ste = null;
			int count=0;
			for(String oneRange:ranges){
				
				String[] tols = oneRange.split("-");
				int s = NumberUtils.toInt(tols[0]);
				int e = NumberUtils.toInt(tols.length == 2?tols[1]:tols[0]);
				
				while(s <= e){
					StringBuilder columns = new StringBuilder(" ( "+cof.getId()+","+cof.getDate());
					if(StringUtils.isNotEmpty(psid)) {
						columns.append(","+cof.getPsid());
					}
					List<Double> factValues = new ArrayList<Double>();//所有数据
					StringBuilder values = new StringBuilder(" (");
					values.append("?,?");
					String firstId = cof.getType() + "_YC_" + s + "_";
					if(StringUtils.isNotEmpty(psid)) {
						firstId = psid + "_" + cof.getType() + "_YC_" + s + "_";
						values.append(",?");
					}
					for(Map.Entry<String, String> cofMap:cof.getConfigMap().entrySet()){
						String keyId = firstId + cofMap.getKey();
						Object val = RamManager.getValue(keyId);
						columns.append(",").append(cofMap.getValue());
						values.append(",?");
						factValues.add(val==null?null:NumberUtils.toDouble(""+val));
					}
					String inser = first + columns + ") values " + values + ")";
					try {
						if(ste == null)ste = conn.prepareStatement(inser);
						ste.setInt(1, s);
						ste.setTimestamp(2, new Timestamp(date.getTime()));
						int detal = 3;
						if(StringUtils.isNotEmpty(psid)) {
							ste.setLong(3, NumberUtils.toLong(psid));
							detal = 4;
						}
						
						
						for (int i = 0; i < factValues.size(); i++) {
							Double v = factValues.get(i);
							if(v == null){
								ste.setNull(i+detal, Types.DOUBLE);
							}else{
								ste.setDouble(i+detal, v);
							}
						}
						//System.out.println(columns+"," + values);
						ste.addBatch();
						count++;
						if(ste != null && count==200){
						
							try {
								ste.executeBatch();
								count=0;
								ste=null;
							} catch (SQLException e1) {
								e1.printStackTrace();
							}
						}
					} catch (Exception e1) {
						e1.printStackTrace();
						logger.error(e1.getMessage());
					}
					s++;
				}
			}
			if(ste != null){
				
				try {
					ste.executeBatch();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			
		}catch(Exception e){
			logger.info("入库出错！" + cof.getType());
			e.printStackTrace();
		}finally {
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
}
