package com.kx.ds.service.def;

import java.util.HashMap;
import java.util.Map;

public class DSDef {
	/**设备类型*/
	public static class EQTYPE{
		
		public static String NBQ = "NBQ";
		
		public static String XB = "XB";
		
		public static String HLX = "HLX";
		
		public static String KGG = "KGG";
		
		public static String QXY = "QXY";
		
		private static Map<String,String> descMap = new HashMap<String,String>();
		static{
			descMap.put(NBQ, "逆变器");
			descMap.put(XB, "箱变");
			descMap.put(HLX, "汇流箱");
			descMap.put(KGG, "开关柜");
			descMap.put(QXY, "气象仪");
		}
		/**
		 * 获得类型名称
		 * @param code
		 * @return
		 */
		public static String getDesc(String code){
			return descMap.get(code);
		}
		
	}

	public static final String _ID = "_id";
	public static final String ID = "id";
	
}
