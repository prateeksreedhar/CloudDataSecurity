package com.dsce.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.dsce.util.MySQLUtility;
import com.dsce.util.Queries;

public class DecryptionDAO {

	public void insertEncDecId(int id, String type, int encdecId)
			throws SQLException {
		Connection con = MySQLUtility.getConnectionToCloudDB();
		try {
			con = MySQLUtility.getConnectionToCloudDB();
			PreparedStatement ps = con.prepareStatement(Queries
					.getQuery("INSERT_ENCDEC_ID"));
			ps.setInt(1, id);
			ps.setString(2, type);
			ps.setInt(3, encdecId);
			ps.execute();

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			MySQLUtility.closeConnection(con);
		}

	}

	public int getEncDecId(int id, String type) throws SQLException {
		Connection con = MySQLUtility.getConnectionToCloudDB();
		try {
			con = MySQLUtility.getConnectionToCloudDB();
			PreparedStatement ps = con.prepareStatement(Queries
					.getQuery("GET_ENCDEC_ID"));
			ps.setInt(1, id);
			ps.setString(2, type);
			ResultSet rs = ps.executeQuery();
			rs.next();
			return rs.getInt("ENCKEYSID");
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			MySQLUtility.closeConnection(con);
		}

	}
}
