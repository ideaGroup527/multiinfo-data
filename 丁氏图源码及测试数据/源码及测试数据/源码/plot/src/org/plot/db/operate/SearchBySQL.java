package org.plot.db.operate;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 通用的查询方法
 */
public class SearchBySQL extends DBAction {

	private String sql;

	public SearchBySQL(String sql) {
		this.sql = sql;
	}

	@Override
	public List work() {
		List dbList = new ArrayList();
		Statement stmt = (Statement) super.work();

		String sql = this.sql;
		try {
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
					try {
						dbList.add(rs.getString(1));
					} catch (RuntimeException e) {
						throw new RuntimeException("列名无效，请确认", e);
					}
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// 关闭resultset
		this.resultSetClose();

		return dbList;
	}
}
