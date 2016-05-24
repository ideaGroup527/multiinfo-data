package com.kxm.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.kxm.db.DB;
import com.kxm.pojo.ConnParam;

/**
 * ����Connection����
 * @author kexinmei
 * @author kexinmeip@126.com
 * @version 1.0
 */
public class CreateConnection extends DB {

	public Connection conn = null;
	private ConnParam cp;

	public CreateConnection(ConnParam cp) {
		this.cp = cp;
	}
	/**
	 * @return Connection conn
	 * */
	@Override
	public Connection work() {

		try {
			if (null == conn) {
				Class.forName(cp.getDriverName());
				conn = DriverManager.getConnection(cp.getUrl(), cp
						.getUserName(), cp.getPassword());
				System.out.println("���connection�ɹ���");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}catch(NullPointerException e){
			System.out.println("����д�������ݣ�");
		}
		return conn;
	}

	public void close() {
		try {
			if (null != conn) {
				conn.close();
				conn = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}

