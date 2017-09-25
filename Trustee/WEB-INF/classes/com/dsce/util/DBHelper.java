package com.dsce.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBHelper {

	public static Connection getConnection() throws Exception {
		try {

			Class.forName("com.mysql.jdbc.Driver");
			return DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/Trustee", "root",
					"root");
		} catch (Exception e) {
			throw e;
		}
	}

	public static void closeConnection(Connection con) throws Exception {
		try {
			con.close();
		} catch (Exception e) {
			throw e;
		}

	}

}
