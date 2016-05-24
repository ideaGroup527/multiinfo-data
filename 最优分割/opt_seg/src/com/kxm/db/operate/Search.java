package com.kxm.db.operate;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
//import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * ͨ�õĲ�ѯ����
 */
public class Search extends DBAction {

	private String selectStr = "";
	private String fromStr = "";
	private String whereStr = "";

	private String[] colName;

	public Search() {
	}

	/**
	 * ƴװѡ���ֶ�,����select���ʣ�����ֶ�
	 */
	public void chainCol(String[] colName) {
		this.colName = colName;
		StringBuffer sb = new StringBuffer();
		sb.append("select ");// ����select����
		// ѭ��������
		String stopStr = ",";
		for (int i = 0; i < colName.length; i++) {
			sb.append(colName[i]);
			sb.append(stopStr);
		}
		int strLength = sb.length() - stopStr.length();// ȥ�������and֮��ʣ�µ��ַ�������
		if (strLength > 0) {
			this.selectStr = sb.substring(0, strLength);
		}
	}

	/**
	 * ƴװѡ���ֶ�,����select���ʣ�һ���ֶ�
	 */
	public void chainCol(String colName) {

		if ("*".equals(colName) || "".equals(colName) || null == colName) {
			throw new RuntimeException("������Ч����ȷ��");
		}

		this.colName = new String[1];//?
		this.colName[0] = colName;
		StringBuffer sb = new StringBuffer();
		sb.append("select ");// ����select����
		sb.append(colName);
		sb.append(" ");
		this.selectStr = sb.toString();
	}

	/**
	 * ƴװ����,�����
	 */
	public void chainTable(String[] tableName) {
		StringBuffer sb = new StringBuffer();
		sb.append(" from ");// ����select����
		// ѭ��������
		String stopStr = ",";
		for (int i = 0; i < tableName.length; i++) {
			sb.append(tableName[i]);
			sb.append(stopStr);
		}
		int strLength = sb.length() - stopStr.length();// ȥ�������"'"֮��ʣ�µ��ַ�������
		if (strLength > 0) {
			this.fromStr = sb.substring(0, strLength);
		}
	}

	/**
	 * ƴװ������һ����
	 */
	public void chainTable(String tableName) {
		StringBuffer sb = new StringBuffer();
		sb.append(" from ");// ����select����
		sb.append(tableName);
		sb.append(" ");
		this.fromStr = sb.toString();
	}

	/**
	 * ƴװwhere����
	 */
	public void chainWhere(Map whereMap) {
		StringBuffer sb = new StringBuffer();
		sb.append(" where ");// д��where����
		Set keys = whereMap.keySet();// �õ�key����
		Iterator it = keys.iterator();// ����
		String andStr = " and ";// and�ַ���
		while (it.hasNext()) {
			Object key = it.next();// ������KEY
			Object value = whereMap.get(key);// key��Ӧ��ֵ
			sb.append(key + "='" + value+"'");// ƴװ����
			sb.append(andStr);// ����and�ַ���
		}
		int strLength = sb.length() - andStr.length();// ȥ�������and֮��ʣ�µ��ַ�������
		if (strLength > 0) {
			this.whereStr = sb.substring(0, strLength);// �õ����
		}
	}

	@Override
	public List work() {
		List dbList = new ArrayList();
		Statement stmt = (Statement) super.work();

		String sql = this.selectStr + this.fromStr + this.whereStr;
		try {
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				List temp = new ArrayList();
				for (int i = 0; i < colName.length; i++) {
					try {
						temp.add(rs.getString(colName[i]));
					} catch (RuntimeException e) {
						throw new RuntimeException("������Ч����ȷ��", e);
					}
				}
				dbList.add(temp);  //���ݵ���ʽ��һ�з���һ��temp������ Ȼ���ٰ����е�temp��������dblist��
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// �ر�resultset
		this.resultSetClose();

		return dbList;
	}
}
