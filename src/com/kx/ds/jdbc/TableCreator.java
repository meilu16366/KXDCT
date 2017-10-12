package com.kx.ds.jdbc;

import java.sql.SQLException;

/**
 * 建表
 * @author ml
 * @date 2017-10
 * @company 广东振森智能科技有限公司
 */

public interface TableCreator {
	
	/**
	 * 以模板表建新表
	 * @param modelTableName
	 * @param newTableName
	 */
	void createTableForModel(String modelTableName,String newTableName) throws SQLException;
	
	
}
