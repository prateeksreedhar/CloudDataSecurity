package com.dsce.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.dsce.util.MySQLUtility;
import com.dsce.util.Queries;

public class PolicyDAO {

	public void grantAccess(String email, Map<String, Integer> accessMap)
			throws SQLException {
		Connection con = MySQLUtility.getConnectionToAppDB();

		System.out.println(accessMap);
		try {
			String qry = Queries.getQuery("INSERT_POLICY");
			PreparedStatement ps = con.prepareStatement(qry);
			int i = 1;

			for (Entry<String, Integer> entry : accessMap.entrySet()) {
				ps.setString(i++, email);
				ps.setString(i++, entry.getKey());
				ps.setInt(i++, entry.getValue());
			}
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			MySQLUtility.closeConnection(con);
		}
	}

	public int getPrivileges(String email, String resourceType)
			throws SQLException {
		Connection con = MySQLUtility.getConnectionToAppDB();

		try {
			String qry = Queries.getQuery("GET_ACCESS");
			PreparedStatement ps = con.prepareStatement(qry);
			ps.setString(1, email);
			ps.setString(2, resourceType);
			ResultSet rs = ps.executeQuery();
			rs.next();
			return rs.getInt("access");
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			MySQLUtility.closeConnection(con);
		}

	}

	public Map<String, Integer> getAllPrivileges(String email) throws Exception {
		Connection con = MySQLUtility.getConnectionToAppDB();
		Map<String, Integer> result = new HashMap<String, Integer>();
		try {
			String qry = Queries.getQuery("GET_ALL_PRIVILEGES");
			PreparedStatement ps = con.prepareStatement(qry);
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				result.put(rs.getString("resourceType"), rs.getInt("access"));
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			MySQLUtility.closeConnection(con);
		}

	}

	public void updateAccess(String email, Map<String, Integer> accessMap)
			throws SQLException {
		Connection con = MySQLUtility.getConnectionToAppDB();
		try {
			PreparedStatement ps1 = con.prepareStatement(Queries.getQuery("DELETE_POLICY"));
			ps1.setString(1, email);
			ps1.execute();
			String qry = Queries.getQuery("INSERT_POLICY");
			PreparedStatement ps = con.prepareStatement(qry);
			int i = 1;

			for (Entry<String, Integer> entry : accessMap.entrySet()) {
				ps.setString(i++, email);
				ps.setString(i++, entry.getKey());
				ps.setInt(i++, entry.getValue());
			}
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			MySQLUtility.closeConnection(con);
		}

	}
}
