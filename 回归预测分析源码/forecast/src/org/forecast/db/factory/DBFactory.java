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
	 * ��һ���������������ݿ�ı�Ҫ����
	 */
	public DBFactory(DataBaseMessage dbm) {
		this.dbm = dbm;
	}

	/**
	 * ��һ���������������ݿ�ı�Ҫ�������ڶ���Ϊ����
	 */
	public DBFactory(DataBaseMessage dbm, String tableName) {
		this.dbm = dbm;
		this.tableName = tableName;
	}

	public List factory() {
		List result = null;

		ConnStaticParam csp = new ConnStaticParam();// ���ݿ����������
		String databaseType = dbm.getDatabaseKind().toUpperCase();// ���ݿ�����
		String url = null;// url
		String sql = "";// ��ѯ��sql
		ConnParam cp = new ConnParam();// ���Ӳ���

		if ("ORACLE".equals(databaseType)) {
			csp.setDatabaseType(csp.ORACLE);// �������ݿ�����
			url = csp.getUrl(dbm.getIp(), dbm.getDataBase());// ƴװURL
			cp.setDriverName(csp.ORACLE_Driver_NAME);// ����������
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

		DB conn = new CreateConnection(cp);// �������
		CreateStatement stmt = new CreateStatement(); // ���statement
		SearchBySQL sbs = new SearchBySQL(sql);// ��sql��ѯ
		stmt.setDb(conn);
		sbs.setDb(stmt);
		result = sbs.work();// ִ��

		return result;
	}
}
