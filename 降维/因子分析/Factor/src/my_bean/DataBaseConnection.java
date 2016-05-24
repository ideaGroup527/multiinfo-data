package my_bean;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class DataBaseConnection {
	private int dataBaseKind;
	private String dataBaseName;
	private String userName;
	private String password;
	private String ip;
	private String[] tableNames;
	private Connection conn;

	public DataBaseConnection() {

	}

	public void setDataBaseKind(int dataBaseKind) {
		this.dataBaseKind = dataBaseKind;
	}

	public void setDataBaseName(String dataBaseName) {
		this.dataBaseName = dataBaseName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public void setConn() {
		String driverClassName = "";
		String url = "";
		switch (dataBaseKind) {
		case 1: {
			driverClassName = "com.microsoft.jdbc.sqlserver.SQLServerDriver";
			url = "jdbc:microsoft:sqlserver://" + ip + ":1433;DatabaseName="
					+ dataBaseName;
			try {
				Class.forName(driverClassName).newInstance();
				conn = DriverManager.getConnection(url, userName, password);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("连接错误" + e);
			}
			break;
		}
		case 2: {
			driverClassName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
			url = "jdbc:sqlserver://" + ip + ":1433;DatabaseName="
					+ dataBaseName;
			try {
				Class.forName(driverClassName).newInstance();
				conn = DriverManager.getConnection(url, userName, password);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("连接错误" + e);
			}
			break;
		}
		case 3: {
			driverClassName = "org.gjt.mm.mysql.Driver";
			url = "jdbc:mysql://" + ip + "/" + dataBaseName + "?user="
					+ userName + "&password=" + password
					+ "&useUnicode=true&characterEncoding=gb2312";
			try {
				Class.forName(driverClassName).newInstance();
				conn = DriverManager.getConnection(url);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("连接错误" + e);
			}
			break;
		}
		}
	}

	public int getDataBaseKind() {
		return dataBaseKind;
	}

	public String getDataBaseName() {
		return dataBaseName;
	}

	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}

	public String getIp() {
		return ip;
	}

	public String[] getTableNames() {
		int i = 0;
		try {
			setConn();
			DatabaseMetaData dmd = conn.getMetaData();
			ResultSet rs = dmd.getTables(null, null, null, null);
			rs.last();
			tableNames = new String[rs.getRow()];
			rs.first();
			rs.previous();
			while (rs.next()) {
				String name = rs.getString("TABLE_NAME");
				tableNames[i] = name;
				i++;
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("获取数据失败" + e);
		}
		return tableNames;
	}

	public Connection getConn() {
		return conn;
	}
}
