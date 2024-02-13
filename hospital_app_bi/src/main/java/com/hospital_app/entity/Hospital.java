package com.hospital_app.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;

@Entity
public class Hospital {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(insertable = false)
	private int hospitalId;
	@Column(nullable = false)
	private String hospitalName;
	private int numberOfBranches;
	private String hospitalType;
	@CreationTimestamp
	private LocalDateTime hospitalCreatedTime;
	//branch
	@OneToMany(mappedBy = "hospital",cascade = CascadeType.ALL)
	private List<Branch> branches;
	public int getHospitalId() {
		return hospitalId;
	}
	public void setHospitalId(int hospitalId) {
		this.hospitalId = hospitalId;
	}
	public String getHospitalName() {
		return hospitalName;
	}
	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}
	public int getNumberOfBranches() {
		return numberOfBranches;
	}
	public void setNumberOfBranches(int numberOfBranches) {
		this.numberOfBranches = numberOfBranches;
	}
	public String getHospitalType() {
		return hospitalType;
	}
	public void setHospitalType(String hospitalType) {
		this.hospitalType = hospitalType;
	}
	public LocalDateTime getHospitalCreatedTime() {
		return hospitalCreatedTime;
	}
	public void setHospitalCreatedTime(LocalDateTime hospitalCreatedTime) {
		this.hospitalCreatedTime = hospitalCreatedTime;
	}
	public List<Branch> getBranches() {
		return branches;
	}
	public void setBranches(List<Branch> branches) {
		this.branches = branches;
	}
	
	
}
