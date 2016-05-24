package com.kxm.db.param;

public class ConnStaticParam {

	// 数据库种类
	public static final String ORACLE = "ORACLE";
	public static final String SQLSERVER = "SQLSERVER";
	public static final String MYSQL = "MYSQL";
	public String databaseType = "";
	// 数据库驱动名
	public static final String ORACLE_Driver_NAME = "oracle.jdbc.driver.OracleDriver";
	public static final String SQLSERVER_Driver_NAME = "net.sourceforge.jtds.jdbc.Driver";
	public static final String MYSQL_Driver_NAME = "com.mysql.jdbc.Driver";
	// 数据库连接URL
	private static final String ORACLE_URL = "jdbc:oracle:thin:@";
	private static final String SQLSERVER_URL = "jdbc:jtds:sqlserver://";
	private static final String MYSQL_URL = "jdbc:mysql://";
	// 查找所有表
	public static final String ORACLE_ALL_TABLE = "select table_Name from user_tables";
	public static final String SQLSERVER_ALL_TABLE = "select name from sysobjects where xtype='u'";
	public static final String MYSQL_ALL_TABLE = "select table_name from information_schema.tables where table_schema='";
	// 查找所有字段
	public static final String ORACLE_ALL_COL = "select COLUMN_NAME,DATA_TYPE from user_tab_cols  where TABLE_NAME='";
	public static final String SQLSERVER_ALL_COL = "select name from syscolumns where id=OBJECT_ID('";
	public static final String MYSQL_ALL_COL = "select column_name from information_schema.columns where table_name='";

	/**
	 * 获得连接的URL
	 */
	public String getUrl(String ip, String database) {
		StringBuffer result = new StringBuffer();

		if ("ORACLE".equals(databaseType)) {
			result.append(this.ORACLE_URL);
			result.append(ip);
			result.append(":1521:");
			result.append(database);
		} else if ("SQLSERVER".equals(databaseType)) {
			result.append(this.SQLSERVER_URL);
			result.append(ip);
			result.append(":1433/");
			result.append(database);
		} else if ("MYSQL".equals(databaseType)) {
			 result.append(this.MYSQL_URL);
			 result.append(ip);
			 result.append(":3306/") ;
			 result.append(database);
		}
		return result.toString();
	}

	/**
	 * 获得查找某个表字段信息的select语句
	 */
	public String getTableCol(String tableName) {
		StringBuffer result = new StringBuffer();

		if ("ORACLE".equals(databaseType)) {
			result.append(this.ORACLE_ALL_COL);
			result.append(tableName);
			result.append("'");
		} else if ("SQLSERVER".equals(databaseType)) {
			result.append(this.SQLSERVER_ALL_COL);
			result.append(tableName);
			result.append("')");
		} else if ("MYSQL".equals(databaseType)) {
			result.append(this.MYSQL_ALL_COL);
			result.append(tableName);
			result.append("'");
		}
		return result.toString();
	}

	public String getDatabaseType() {
		return databaseType;
	}

	public void setDatabaseType(String databaseType) {
		this.databaseType = databaseType;
	}
}

