package com.dsce.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.dsce.pojo.LabReport;
import com.dsce.pojo.Patient;
import com.dsce.pojo.Prescription;
import com.dsce.util.MySQLUtility;
import com.dsce.util.Queries;

public class ResourceDAO {
	

	public List<Patient> readPatient(String id) throws SQLException {
		List<Patient> result = new ArrayList<>();

		Connection con = MySQLUtility.getConnectionToCloudDB();

		try {
			ResultSet rs = null;
			if (id == null || id.trim().length() == 0) {
				Statement st = con.createStatement();
				rs = st.executeQuery(Queries.getQuery("READ_PATIENT"));
			} else {
				PreparedStatement ps = con.prepareStatement(Queries
						.getQuery("READ_PATIENT_BY_ID"));
				ps.setString(1, id);
				rs = ps.executeQuery();
			}
			while (rs.next()) {
				Patient patient = new Patient();
				patient.setId(rs.getString("id"));
				patient.setName(rs.getString("name"));
				patient.setAddress(rs.getString("address"));
				patient.setAge(rs.getString("age"));
				patient.setGender(rs.getString("gender"));
				patient.setMobile(rs.getString("mobile"));
				result.add(patient);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			MySQLUtility.closeConnection(con);
		}

		return result;
	}

	public List<LabReport> readLab(String id) throws SQLException {
		List<LabReport> result = new ArrayList<>();

		Connection con = MySQLUtility.getConnectionToCloudDB();

		try {
			ResultSet rs = null;
			if (id == null || id.trim().length() == 0) {
				Statement st = con.createStatement();
				rs = st.executeQuery(Queries.getQuery("READ_LAB"));
			} else {
				PreparedStatement ps = con.prepareStatement(Queries
						.getQuery("READ_LAB_BY_ID"));
				ps.setString(1, id);
				rs = ps.executeQuery();
			}
			while (rs.next()) {
				LabReport lab = new LabReport();
				lab.setId(rs.getString("id"));
				lab.setPatientId(rs.getString("patientid"));
				lab.setTestName(rs.getString("testname"));
				lab.setTestResults(rs.getString("testresults"));
				result.add(lab);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			MySQLUtility.closeConnection(con);
		}

		return result;
	}

	public List<Prescription> readPrescription(String id) throws SQLException {
		List<Prescription> result = new ArrayList<>();

		Connection con = MySQLUtility.getConnectionToCloudDB();

		try {
			ResultSet rs = null;
			if (id == null || id.trim().length() == 0) {
				Statement st = con.createStatement();
				rs = st.executeQuery(Queries.getQuery("READ_PRESCRIPTION"));
			} else {
				PreparedStatement ps = con.prepareStatement(Queries
						.getQuery("READ_PRESCRIPTION_BY_ID"));
				ps.setString(1, id);
				rs = ps.executeQuery();
			}
			while (rs.next()) {
				Prescription presc = new Prescription();
				presc.setId(rs.getString("id"));
				presc.setPatientId(rs.getString("patientID"));
				presc.setMedicineName(rs.getString("medicineName"));
				presc.setUsageRules(rs.getString("usageRules"));
				result.add(presc);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			MySQLUtility.closeConnection(con);
		}

		return result;
	}

	public int insertPatient(Patient patient) throws SQLException {

		Connection con = MySQLUtility.getConnectionToCloudDB();
		try {
			PreparedStatement ps = con.prepareStatement(Queries
					.getQuery("INSERT_PATIENT"));
			ps.setString(1, patient.getName());
			ps.setString(2, patient.getGender());
			ps.setString(3, patient.getMobile());
			ps.setString(4, patient.getAge());
			ps.setString(5, patient.getAddress());
			ps.execute();

			ResultSet rs = con.createStatement().executeQuery(
					Queries.getQuery("GET_PATIENT_MAX_ID"));
			rs.next();
			return rs.getInt("id");
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			MySQLUtility.closeConnection(con);
		}
	}

	public void insertLab(LabReport lab) throws SQLException {
		Connection con = MySQLUtility.getConnectionToCloudDB();
		try {
			PreparedStatement ps = con.prepareStatement(Queries
					.getQuery("INSERT_LAB"));

			ps.setString(1, lab.getPatientId());
			ps.setString(2, lab.getTestName());
			ps.setString(3, lab.getTestResults());
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			MySQLUtility.closeConnection(con);
		}

	}

	public void insertPrescription(Prescription presc) throws SQLException {
		Connection con = MySQLUtility.getConnectionToCloudDB();
		try {
			PreparedStatement ps = con.prepareStatement(Queries
					.getQuery("INSERT_PRESCRIPTION"));
			ps.setString(1, presc.getPatientId());
			ps.setString(2, presc.getMedicineName());
			ps.setString(3, presc.getUsageRules());
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			MySQLUtility.closeConnection(con);
		}

	}

	public void updatePatient(Patient patient) throws SQLException {
		Connection con = MySQLUtility.getConnectionToCloudDB();
		try {
			con = MySQLUtility.getConnectionToCloudDB();
			PreparedStatement ps = con.prepareStatement(Queries
					.getQuery("UPDATE_PATIENT"));
			ps.setString(1, patient.getName());
			ps.setString(2, patient.getGender());
			ps.setString(3, patient.getMobile());
			ps.setString(4, patient.getAge());
			ps.setString(5, patient.getAddress());
			ps.setString(6, patient.getId());
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			MySQLUtility.closeConnection(con);
		}

	}

	public void updateLab(LabReport lab) throws SQLException {
		Connection con = MySQLUtility.getConnectionToCloudDB();
		try {
			con = MySQLUtility.getConnectionToCloudDB();
			PreparedStatement ps = con.prepareStatement(Queries
					.getQuery("UPDATE_LAB"));
			ps.setString(1, lab.getTestName());
			ps.setString(2, lab.getTestResults());
			ps.setString(3, lab.getId());
			ps.execute();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			MySQLUtility.closeConnection(con);
		}

	}

	public void updatePrescription(Prescription presc) throws SQLException {
		Connection con = MySQLUtility.getConnectionToCloudDB();
		try {
			con = MySQLUtility.getConnectionToCloudDB();
			PreparedStatement ps = con.prepareStatement(Queries
					.getQuery("UPDATE_PRESCRIPTION"));
			ps.setString(1, presc.getMedicineName());
			ps.setString(2, presc.getUsageRules());
			ps.setString(3, presc.getId());
			ps.execute();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			MySQLUtility.closeConnection(con);
		}

	}

}
