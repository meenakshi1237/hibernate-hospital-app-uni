package com.hospital_app.dto;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
@Entity
public class Hospital {
	@Id
	private String hospitalId;
	private String hospitanName;
	private String hospitalType;
	private String	hospitalEmail;
	private int numberOfBranches;
	//branches 
	@OneToMany
	private List<Branch> branches;
	public String getHospitalId() {
		return hospitalId;
	}
	public void setHospitalId(String hospitalId) {
		this.hospitalId = hospitalId;
	}
	public String gethospitanName() {
		return hospitanName;
	}
	public void sethospitanName(String hospitanName) {
		this.hospitanName = hospitanName;
	}
	public String gethospitalType() {
		return hospitalType;
	}
	public void sethospitalType(String hospitalType) {
		this.hospitalType = hospitalType;
	}
	public String getHospitalEmail() {
		return hospitalEmail;
	}
	public void setHospitalEmail(String hospitalEmail) {
		this.hospitalEmail = hospitalEmail;
	}
	public int getNumberOfBranches() {
		return numberOfBranches;
	}
	public void setNumberOfBranches(int numberOfBranches) {
		this.numberOfBranches = numberOfBranches;
	}
	public List<Branch> getBranches() {
		return branches;
	}
	public void setBranches(List<Branch> branches) {
		this.branches = branches;
	}
	@Override
	public String toString() {
		return "Hospital [hospitalId=" + hospitalId + ", hospitanName=" + hospitanName + ", hospitalType="
				+ hospitalType + ", hospitalEmail=" + hospitalEmail + ", numberOfBranches=" + numberOfBranches
				+ ", branches=" + branches + "]";
	}
	
}
