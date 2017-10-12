package com.kx.ds.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class MysqlTableCreatorImp implements TableCreator {
	
	@Autowired
	private ConnectionFatory fc;
	
	@Override
	public void createTableForModel(String modelTableName, String newTableName) throws SQLException {
		Connection conn = null;
		try {
			conn = fc.getConnection();
			Statement stm = conn.createStatement();
			ResultSet rest = stm.executeQuery("SHOW CREATE TABLE " + modelTableName);
			if(rest!=null) {
				rest.first();
				String createSql = rest.getString(2);
				createSql = createSql.replace(modelTableName, newTableName);
				stm.execute(createSql);
			}else {
				throw new Exception("模板表不存在");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(conn!=null) {
				conn.close();
			}
		}
	}

}
