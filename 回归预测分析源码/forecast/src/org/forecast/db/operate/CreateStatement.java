package org.forecast.db.operate;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateStatement extends DBAction {

	public Statement stmt = null;

	@Override
	public Statement work() {
		try {
			if (null == stmt) {
				Connection conn = (Connection) super.work();
				stmt = conn.createStatement();
				System.out.println("获得statement成功！");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return stmt;
	}

	public void close() {
		try {
			if (null != stmt) {
				stmt.close();
				stmt = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
