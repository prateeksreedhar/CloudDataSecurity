package com.dsce.dao;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import com.dsce.util.MySQLUtility;

public class KDCDao {

	public int insertKeys(BigInteger m1, BigInteger e1, BigInteger m2,
			BigInteger e2) throws Exception {
		Connection con = MySQLUtility.getConnectionToKDCDB();
		try {
			Statement st = con.createStatement();
			st.execute("insert into ENCKEYS (pubModulus, pubexponent, privModulus, privExponent) values ('"
					+ m1 + "', '" + e1 + "', '" + m2 + "', '" + e2 + "') ");
			Statement st2 = con.createStatement();
			ResultSet rs = st2
					.executeQuery("select max(id) as id from ENCKEYS");
			rs.next();
			return rs.getInt("id");
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			MySQLUtility.closeConnection(con);
		}
	}

	public Map<String, String> getKeys(String enckeyId) throws Exception {
		Connection con = MySQLUtility.getConnectionToKDCDB();
		Map<String, String> result = new HashMap<String, String>();
		System.out.println("Enc key id.. " + enckeyId);
		try {
			ResultSet rs = con.createStatement().executeQuery(
					"select * from ENCKEYS where id='" + enckeyId + "'");
			rs.next();
			result.put("pubModulus", rs.getString("pubModulus"));
			result.put("pubexponent", rs.getString("pubexponent"));
			result.put("privModulus", rs.getString("privModulus"));
			result.put("privExponent", rs.getString("privExponent"));
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			MySQLUtility.closeConnection(con);
		}

	}

}
