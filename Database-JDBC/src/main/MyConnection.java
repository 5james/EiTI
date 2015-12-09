package main;

import java.sql.*;

public class MyConnection {
	private String user = "jguzek";
	private String password = "jguzek";
	private String connString = "jdbc:oracle:thin:@ora3.elka.pw.edu.pl:1521:ora3inf";
	private Connection conn;
	private Statement stmt;
	private ResultSet rset;
	public MyConnection() {
		try {
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		} catch (SQLException e) {
			System.out.println(e.getMessage() + "-> problem z po³šczeniem1");
		}
		conn = null;
		stmt = null;
		rset = null;
	}

	Object[] download (String table, int id, String idName, String columns[]) {
		String sql = "SELECT ";
		for (int i = 0; i < columns.length; i++) { 
			sql += columns[i];
			if (i < columns.length-1)
				sql += ", ";
			else
				sql += " ";
		}
		sql += "FROM " + table + " WHERE " + idName + "=" + id;
		Object[] result = new String [columns.length+1];
		for (int i=0; i<columns.length; i++) {
			result[i] = "";
		}
		try {
			conn = DriverManager.getConnection(connString, user, password);	
			stmt = conn.createStatement();
			rset = stmt.executeQuery(sql);

			while (rset.next()) {
				for (int i = 0; i < columns.length; i++) {
					result[i] = rset.getObject(columns[i]);
					result[columns.length] = String.valueOf(id);
				}
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage() + "-> problem z po³šczeniem2");
		}
		finally {
			if (conn != null)
				try { conn.close();}
			catch (Exception e) {}
		}
		return result;

	}


	Object selectMax (String table, String idName) {
		String sql = "SELECT MAX(";
		sql += idName;
		sql += ") FROM " + table;
		//System.out.println(sql);
		Object result = null;
		try {
			conn = DriverManager.getConnection(connString, user, password);	
			stmt = conn.createStatement();
			rset = stmt.executeQuery(sql);

			while (rset.next()) {
				result = rset.getObject("MAX(ID)");
				//System.out.println(result);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage() + "-> problem z po³šczeniem2");
		}
		finally {
			if (conn != null)
				try { conn.close();}
			catch (Exception e) {}
		}
		//System.out.println((String) result); ZLE - BEZ (STRING)
		return result;

	}


	void upload(String table, int id, String idName, String columns[], Object values[]) {
		String sql = "UPDATE " + table + " SET ";
		for (int i = 0; i < columns.length; i++) { 
			sql += columns[i] + " = '" + values[i] + "'";
			if (i < columns.length-1)
				sql += ", ";
			else
				sql += " ";
		}
		sql += " WHERE " + idName + "=" + id;
		try {
			conn = DriverManager.getConnection(connString, user, password);	
			stmt = conn.createStatement();
			stmt.executeQuery(sql);
		} catch (SQLException e) {
			System.out.println(e.getMessage() + "-> problem z po³šczeniem2");
		}
		finally {
			if (conn != null)
				try { conn.close();}
			catch (Exception e) {}
		}

	}

	//INSERT INTO OBRAZ (BUD¯ET, TYTU£, TYTU£_ORYGINALNY, GATUNEK, ROK, D£UGOŒÆ, FLAGA) VALUES (1000000, 'test', 'test', 'drama', 1999, 100, 'F')
	void insert(String table, int id, String columns[], String values[]) {
		String sql = "INSERT INTO " + table + " ( ";
		for (int i = 0; i < columns.length; i++) { 
			sql += columns[i];
			if (i < columns.length-1)
				sql += ", ";
			else
				sql += " ";
		}
		sql += ") VALUES ( ";
		for (int i = 0; i < columns.length; i++) { 
			sql += "'" + values[i] + "'";
			if (i < columns.length-1)
				sql += ", ";
			else
				sql += " )";
		}
		System.out.println(sql);
		try {
			conn = DriverManager.getConnection(connString, user, password);	
			stmt = conn.createStatement();
			stmt.executeQuery(sql);
		} catch (SQLException e) {
			System.out.println(e.getMessage() + "-> problem z po³šczeniem2");
		}
		finally {
			if (conn != null)
				try { conn.close();}
			catch (Exception e) {}
		}
	}
}