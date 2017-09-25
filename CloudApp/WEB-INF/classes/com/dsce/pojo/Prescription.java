package com.dsce.pojo;

public class Prescription {

	private String id;
	private String patientId;
	private String medicineName;
	private String usageRules;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public String getMedicineName() {
		return medicineName;
	}

	public void setMedicineName(String medicineName) {
		this.medicineName = medicineName;
	}

	public String getUsageRules() {
		return usageRules;
	}

	public void setUsageRules(String usageRules) {
		this.usageRules = usageRules;
	}

}
