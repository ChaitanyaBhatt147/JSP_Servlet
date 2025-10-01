package com.rays.util;
// Step 1: Make class as Final so no child can be created

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public final class JDBCDataSource {
//	Step 2: Create attribute/ variable of private, static and selfType
	private static JDBCDataSource dataSource = null;
	private static ComboPooledDataSource cpds = null;
//	Step 3: Create default Constructor as private so no other class cannot create it's instance
	private JDBCDataSource() {
		
	}
//	Step 4: create getInstance() whose return type is same as instance
	private static JDBCDataSource getInstance() {
		if (dataSource == null) {
			dataSource = new JDBCDataSource();
			dataSource.cpds = new ComboPooledDataSource();
			ResourceBundle rb = ResourceBundle.getBundle("com.rays.bundle.app");
			try {
				dataSource.cpds.setDriverClass(rb.getString("driver"));
				dataSource.cpds.setJdbcUrl(rb.getString("url"));
				dataSource.cpds.setUser(rb.getString("username"));
				dataSource.cpds.setPassword(rb.getString("password"));
				dataSource.cpds.setAcquireIncrement(Integer.parseInt(rb.getString("acquireIncriment")));
				dataSource.cpds.setInitialPoolSize(Integer.parseInt(rb.getString("initialePoolSize")));
				dataSource.cpds.setMaxPoolSize(Integer.parseInt(rb.getString("maxPoolSize")));
				dataSource.cpds.setMinPoolSize(Integer.parseInt(rb.getString("minPoolSize")));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return dataSource;
	}
	public static Connection getConnection() throws SQLException {
		return getInstance().cpds.getConnection();
	}
	public static void closeConnection(Connection comm) throws SQLException {
		if (comm != null) {
			comm.close();
		}
	}
}
