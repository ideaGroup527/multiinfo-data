package org.forecast.pojo;

import java.io.Serializable;

/**
 * 封装页面填写的数据库连接信息
 */
public class DataBaseMessage implements Serializable{
	private String databaseKind;
	private String dataBase;
	private String userName;
	private String password;
	private String ip;
	private String driverName ;
	private String url ;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	public String getDatabaseKind() {
		return databaseKind;
	}

	public void setDatabaseKind(String databaseKind) {
		this.databaseKind = databaseKind;
	}

	public String getDataBase() {
		return dataBase;
	}

	public void setDataBase(String dataBase) {
		this.dataBase = dataBase;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
}
