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
	 * ��һ���������������ݿ�ı�Ҫ����
	 */
	public CommonFactory(DataBaseMessage dbm, String tableName, String col) {
		this.dbm = dbm;
		this.tableName = tableName;
		this.col = new String[1];
		this.col[0] = col;
	}

	/**
	 * ��һ���������������ݿ�ı�Ҫ����
	 */
	public CommonFactory(DataBaseMessage dbm, String tableName, String[] col) {
		this.dbm = dbm;
		this.tableName = tableName;
		this.col = col;
	}

	public List factory() {
		List result = null;

		ConnStaticParam csp = new ConnStaticParam();// ���ݿ����������
		String databaseType = dbm.getDatabaseKind().toUpperCase();// ���ݿ�����
		String url = null;// url
		ConnParam cp = new ConnParam();// ���Ӳ���

		if ("ORACLE".equals(databaseType)) {
			csp.setDatabaseType(csp.ORACLE);// �������ݿ�����
			url = csp.getUrl(dbm.getIp(), dbm.getDataBase());// ƴװURL
			cp.setDriverName(csp.ORACLE_Driver_NAME);// ����������

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

		DB conn = new CreateConnection(cp);// �������
		CreateStatement stmt = new CreateStatement(); // ���statement
		Search s = new Search();// ��ѯ
		s.chainCol(col);
		s.chainTable(tableName) ;
		
		stmt.setDb(conn);
		s.setDb(stmt);
		result = s.work();// ִ��

		return result;
	}
}
