package com.dsce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.dsce.pojo.Token;
import com.dsce.util.DBHelper;

public class TokenDAO {

	public void insert(Token t) throws Exception {
		Connection con = null;
		try {
			con = DBHelper.getConnection();

			PreparedStatement ps1 = con
					.prepareStatement("select count(*) as cnt from TOKENS where emailID=? and status=?");
			ps1.setString(1, t.getEmail());
			ps1.setString(2, t.getStatus());
			ResultSet rs1 = ps1.executeQuery();
			rs1.next();
			int cnt = rs1.getInt("cnt");
			ps1.close();
			if (cnt == 0) {
				PreparedStatement ps = con
						.prepareStatement("insert into TOKENS values (?,?,?,?) ");

				ps.setString(1, t.getEmail());
				ps.setTimestamp(3, t.getRequestTime());
				ps.setString(2, t.getToken());
				ps.setString(4, t.getStatus());

				ps.execute();
			} else {
				throw new Exception();
			}
		} catch (Exception e) {
			throw e;
		} finally {
			DBHelper.closeConnection(con);
		}
	}

	public String grantToken(String email) throws Exception {
		Connection con = null;

		try {
			con = DBHelper.getConnection();
			PreparedStatement ps = con
					.prepareStatement("select token from TOKENS where emailID=? and status='NEW' ");
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();
			rs.next();
			String token = rs.getString(1);
			ps.close();
			PreparedStatement ps1 = con
					.prepareStatement("update TOKENS set status='GRANTED' where emailID=? and status='NEW' ");
			ps1.setString(1, email);
			ps1.execute();
			return token;
		} catch (Exception e) {
			throw e;
		} finally {
			DBHelper.closeConnection(con);
		}
	}

	public void rejectToken(String email) throws Exception {
		Connection con = null;

		try {
			con = DBHelper.getConnection();
			PreparedStatement ps1 = con
					.prepareStatement("update TOKENS set status='REJECTED' where emailID=? and status='NEW' ");
			ps1.setString(1, email);
			ps1.execute();
		} catch (Exception e) {
			throw e;
		} finally {
			DBHelper.closeConnection(con);
		}

	}

	public boolean isValidToken(String token, String email) throws Exception {
		Connection con = null;

		try {
			con = DBHelper.getConnection();
			PreparedStatement ps = con
					.prepareStatement("select count(*) as cnt from TOKENS where emailID=? and status='GRANTED' and token=?");
			ps.setString(1, email);
			ps.setString(2, token);
			ResultSet rs = ps.executeQuery();
			rs.next();
			if (rs.getInt(1) == 1)
				return true;
			else
				return false;
		} catch (Exception e) {
			throw e;
		} finally {
			DBHelper.closeConnection(con);
		}
	}

	public void markToken(String email, String token) throws Exception {
		Connection con = null;

		try {
			con = DBHelper.getConnection();
			PreparedStatement ps = con
					.prepareStatement("update TOKENS set status='EXPIRED' where emailID=? and token=? ");
			ps.setString(1, email);
			ps.setString(2, token);
			ps.execute();
		} catch (Exception e) {
			throw e;
		} finally {
			DBHelper.closeConnection(con);
		}

	}

	public List<Token> getAllTokens() throws Exception {
		Connection con = null;
		List<Token> tokens = new ArrayList<>();

		try {
			con = DBHelper.getConnection();
			PreparedStatement ps = con
					.prepareStatement("select * from TOKENS ");
			ResultSet res = ps.executeQuery();
			while (res.next()) {
				Token token = new Token();
				token.setEmail(res.getString("emailID"));
				token.setStatus(res.getString("status"));
				token.setToken(res.getString("token"));
				token.setRequestTime(res.getTimestamp("entryTime"));
				tokens.add(token);
			}

			return tokens;
		} catch (Exception e) {
			throw e;
		} finally {
			DBHelper.closeConnection(con);
		}

	}

}
