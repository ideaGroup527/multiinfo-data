package org.plot.db.factory;

import java.util.List;

import org.plot.db.CreateConnection;
import org.plot.db.DB;
import org.plot.db.operate.CreateStatement;
import org.plot.db.operate.Search;
import org.plot.db.param.ConnStaticParam;
import org.plot.pojo.ConnParam;
import org.plot.pojo.DataBaseMessage;

public class CommonFactory {

	private DataBaseMessage dbm = null;
	private String tableName = null;
	private String[] col = null;

	/**
	 * 第一个参数是连接数据库的必要参数
	 */
	public CommonFactory(DataBaseMessage dbm, String tableName, String col) {
		this.dbm = dbm;
		this.tableName = tableName;
		this.col = new String[1];
		this.col[0] = col;
	}

	/**
	 * 第一个参数是连接数据库的必要参数
	 */
	public CommonFactory(DataBaseMessage dbm, String tableName, String[] col) {
		this.dbm = dbm;
		this.tableName = tableName;
		this.col = col;
	}

	public List factory() {
		List result = null;

		ConnStaticParam csp = new ConnStaticParam();// 数据库连接相关类
		String databaseType = dbm.getDatabaseKind().toUpperCase();// 数据库类型
		String url = null;// url
		ConnParam cp = new ConnParam();// 连接参数

		if ("ORACLE".equals(databaseType)) {
			csp.setDatabaseType(csp.ORACLE);// 设置数据库类型
			url = csp.getUrl(dbm.getIp(), dbm.getDataBase());// 拼装URL
			cp.setDriverName(csp.ORACLE_Driver_NAME);// 设置驱动名

		} else if ("SQLSERVER".equals(databaseType)) {
			csp.setDatabaseType(csp.SQLSERVER);
			url = csp.getUrl(dbm.getIp(), dbm.getDataBase());
			cp.setDriverName(csp.SQLSERVER_Driver_NAME);

		} else if ("MYSQL".equals(databaseType)) {
			csp.setDatabaseType(csp.MYSQL);
			url = csp.getUrl(dbm.getIp(), dbm.getDataBase());
			cp.setDriverName(csp.MYSQL_Driver_NAME);
		}

		cp.setUrl(url);
		cp.setUserName(dbm.getUserName());
		cp.setPassword(dbm.getPassword());

		DB conn = new CreateConnection(cp);// 获得连接
		CreateStatement stmt = new CreateStatement(); // 获得statement
		Search s = new Search();// 查询
		s.chainCol(col);
		s.chainTable(tableName) ;
		
		stmt.setDb(conn);
		s.setDb(stmt);
		result = s.work();// 执行

		return result;
	}
}
