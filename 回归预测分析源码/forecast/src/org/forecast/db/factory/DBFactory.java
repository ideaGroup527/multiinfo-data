package org.forecast.db.factory;

import java.util.List;

import org.forecast.db.CreateConnection;
import org.forecast.db.DB;
import org.forecast.db.operate.CreateStatement;
import org.forecast.db.operate.SearchBySQL;
import org.forecast.db.param.ConnStaticParam;
import org.forecast.pojo.ConnParam;
import org.forecast.pojo.DataBaseMessage;

public class DBFactory {

	private DataBaseMessage dbm = null;
	private String tableName = null;

	/**
	 * 第一个参数是连接数据库的必要参数
	 */
	public DBFactory(DataBaseMessage dbm) {
		this.dbm = dbm;
	}

	/**
	 * 第一个参数是连接数据库的必要参数、第二个为表名
	 */
	public DBFactory(DataBaseMessage dbm, String tableName) {
		this.dbm = dbm;
		this.tableName = tableName;
	}

	public List factory() {
		List result = null;

		ConnStaticParam csp = new ConnStaticParam();// 数据库连接相关类
		String databaseType = dbm.getDatabaseKind().toUpperCase();// 数据库类型
		String url = null;// url
		String sql = "";// 查询的sql
		ConnParam cp = new ConnParam();// 连接参数

		if ("ORACLE".equals(databaseType)) {
			csp.setDatabaseType(csp.ORACLE);// 设置数据库类型
			url = csp.getUrl(dbm.getIp(), dbm.getDataBase());// 拼装URL
			cp.setDriverName(csp.ORACLE_Driver_NAME);// 设置驱动名
			if (null == tableName) {
				sql = csp.ORACLE_ALL_TABLE;
			} else {
				sql = csp.getTableCol(this.tableName);
			}

		} else if ("SQLSERVER".equals(databaseType)) {
			csp.setDatabaseType(csp.SQLSERVER);
			url = csp.getUrl(dbm.getIp(), dbm.getDataBase());
			cp.setDriverName(csp.SQLSERVER_Driver_NAME);
			if (null == tableName) {
				sql = csp.SQLSERVER_ALL_TABLE;
			} else {
				sql = csp.getTableCol(this.tableName);
			}

		} else if ("MYSQL".equals(databaseType)) {
			csp.setDatabaseType(csp.MYSQL);
			url = csp.getUrl(dbm.getIp(), dbm.getDataBase());
			cp.setDriverName(csp.MYSQL_Driver_NAME);
			if (null == tableName) {
				sql = csp.MYSQL_ALL_TABLE + dbm.getDataBase() + "'";
			} else {
				sql = csp.getTableCol(this.tableName);
			}

		}

		cp.setUrl(url);
		cp.setUserName(dbm.getUserName());
		cp.setPassword(dbm.getPassword());

		DB conn = new CreateConnection(cp);// 获得连接
		CreateStatement stmt = new CreateStatement(); // 获得statement
		SearchBySQL sbs = new SearchBySQL(sql);// 按sql查询
		stmt.setDb(conn);
		sbs.setDb(stmt);
		result = sbs.work();// 执行

		return result;
	}
}
