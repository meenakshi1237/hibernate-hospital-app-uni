package com.hospital_app.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class Branch {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "brench_id_gen")
	@SequenceGenerator(name = "brench_id_gen",initialValue = 101,allocationSize = 1,sequenceName = "branch_id_seq")
	@Column(insertable = false)
	private int branchId;
	private String branchName;
	private String BranchCity;
	private int numberOfDactors;
	private int hospital_id;
	//hospital
	@ManyToOne
	@JoinColumn
	private Hospital hospital;
	//address
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn
	private Address address;
	//encounters
	@OneToMany(mappedBy = "branch",cascade = CascadeType.ALL)
	private List<Encounter> encounters;
	public int getBranchId() {
		return branchId;
	}
	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public String getBranchCity() {
		return BranchCity;
	}
	public void setBranchCity(String branchCity) {
		BranchCity = branchCity;
	}
	public int getNumberOfDactors() {
		return numberOfDactors;
	}
	public void setNumberOfDactors(int numberOfDactors) {
		this.numberOfDactors = numberOfDactors;
	}
	public int getHospital_id() {
		return hospital_id;
	}
	public void setHospital_id(int hospital_id) {
		this.hospital_id = hospital_id;
	}
	public Hospital getHospital() {
		return hospital;
	}
	public void setHospital(Hospital hospital) {
		this.hospital = hospital;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public List<Encounter> getEncounters() {
		return encounters;
	}
	public void setEncounters(List<Encounter> encounters) {
		this.encounters = encounters;
	}
	
	
	
	
}
