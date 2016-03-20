package org.plot.test;

import java.util.HashMap;
import java.util.Map;

import org.plot.db.CreateConnection;
import org.plot.db.DB;
import org.plot.db.operate.CreateStatement;
import org.plot.db.operate.DBAction;
import org.plot.db.operate.Search;
import org.plot.db.operate.SearchBySQL;
import org.plot.db.param.ConnStaticParam;
import org.plot.pojo.ConnParam;

public class Test {

	public static void main(String[] args) {

		ConnStaticParam csp = new ConnStaticParam();
		csp.setDatabaseType(csp.SQLSERVER);
		String url = csp.getUrl("localhost", "house");

		ConnParam cp = new ConnParam();
		cp.setDriverName(csp.SQLSERVER_Driver_NAME);
		cp.setUrl(url);
		cp.setUserName("house");
		cp.setPassword("house");

		DB conn = new CreateConnection(cp);
		CreateStatement stmt = new CreateStatement();
		// SearchBySQL sbs = new SearchBySQL(csp.getTableCol("TABLE1"));

		Search s = new Search();
		

		// Map w = new HashMap();
		// w.put("id", "OBJECT_ID('TABLE1')");
		// s.chainWhere(w);

		stmt.setDb(conn);
		// sbs.setDb(stmt) ;

		 System.out.println(s.work());

	}
}
