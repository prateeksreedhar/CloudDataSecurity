package com.dsce.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dsce.pojo.User;
import com.dsce.util.MySQLUtility;
import com.dsce.util.Queries;

public class UserDAO {

	public void insertUser(User user) throws SQLException {
		Connection con = MySQLUtility.getConnectionToAppDB();
		try {
			PreparedStatement ps = con.prepareStatement(Queries
					.getQuery("INSERT_USER"));
			ps.setString(1, user.getEmail());
			ps.setString(2, user.getFirstName());
			ps.setString(3, user.getLastName());
			ps.setString(4, user.getPassword());
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			MySQLUtility.closeConnection(con);
		}

	}

	public List<User> getNewUsers() throws SQLException {
		Connection con = MySQLUtility.getConnectionToAppDB();
		try {
			PreparedStatement ps = con.prepareStatement(Queries
					.getQuery("GET_NEW_USERS"));
			ResultSet rs = ps.executeQuery();
			List<User> newUsers = new ArrayList<User>();
			while (rs.next()) {
				User user = new User();
				user.setEmail(rs.getString("email"));
				user.setFirstName(rs.getString("firstname"));
				user.setLastName(rs.getString("lastname"));
				newUsers.add(user);
			}
			return newUsers;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			MySQLUtility.closeConnection(con);
		}

	}

	public void addRole(String email, String role) throws SQLException {
		Connection con = MySQLUtility.getConnectionToAppDB();
		try {
			PreparedStatement ps = con.prepareStatement(Queries
					.getQuery("ADD_ROLE"));
			ps.setString(1, role);
			ps.setString(2, email);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			MySQLUtility.closeConnection(con);
		}
	}

	public boolean isValidUser(String email, String password)
			throws SQLException {
		Connection con = MySQLUtility.getConnectionToAppDB();
		try {
			PreparedStatement ps = con.prepareStatement(Queries
					.getQuery("USER_LOGIN"));
			ps.setString(1, email);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			rs.next();
			if (rs.getInt("cnt") > 0) {
				return true;
			} else
				return false;

		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			MySQLUtility.closeConnection(con);
		}

	}

	public String getRole(String email) throws SQLException {
		Connection con = MySQLUtility.getConnectionToAppDB();
		try {
			PreparedStatement ps = con.prepareStatement(Queries
					.getQuery("GET_ROLE"));
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();
			rs.next();
			return rs.getString("role");

		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			MySQLUtility.closeConnection(con);
		}

	}

	public void approveUser(String email) throws SQLException {
		Connection con = MySQLUtility.getConnectionToAppDB();
		try {
			PreparedStatement ps = con.prepareStatement(Queries
					.getQuery("APPROVE_USER"));
			ps.setString(1, email);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			MySQLUtility.closeConnection(con);
		}

	}

	public List<User> getAllUsers(String email) throws SQLException {
		Connection con = MySQLUtility.getConnectionToAppDB();
		List<User> users = new ArrayList<User>();
		try {
			PreparedStatement ps = null;
			if (email!=null && !email.trim().equals("") ) {
				ps = con.prepareStatement(Queries.getQuery("GET_USER"));
				ps.setString(1, email);
			} else {
			ps = con.prepareStatement(Queries
					.getQuery("GET_ALL_USERS"));
			}	
			ResultSet rs = ps.executeQuery(); 
			while (rs.next()) {
				User u = new User();
				u.setEmail(rs.getString("email"));
				u.setFirstName(rs.getString("firstname"));
				u.setLastName(rs.getString("lastname"));
				u.setRole(rs.getString("role"));
				users.add(u);
			}
			return users;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			MySQLUtility.closeConnection(con);
		}

	}

	public void revokeUser(String email) throws SQLException {
		Connection con = MySQLUtility.getConnectionToAppDB();
		try {
			PreparedStatement ps = con.prepareStatement(Queries
					.getQuery("REVOKE_USER"));
			ps.setString(1, email);
			ps.execute();
			
			
			PreparedStatement ps2 = con.prepareStatement(Queries
					.getQuery("REVOKE_FROM_POLICY"));
			ps2.setString(1, email);
			ps2.execute();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			MySQLUtility.closeConnection(con);
		}


	}
}
