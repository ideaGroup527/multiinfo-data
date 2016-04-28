package org.forecast.db.operate;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.forecast.db.DB;


public abstract class DBAction extends DB {

	protected DB db;
	protected ResultSet rs;

	public void setDb(DB db) {
		this.db = db;
	}
	public Object work() {
		Object obj = null;
		if (null != db) {
			obj = db.work();
		}
		return obj;
	}
	public void resultSetClose() {
		try {
			if (null != rs) {
				rs.close();
				rs = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
