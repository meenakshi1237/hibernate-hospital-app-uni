package com.hospital_app.dto;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Branch {
	@Id
	private String branchId;
	private String branchName;
	private String hospital_id;
	private int numberOfDactors;
	private int NumberOfBeds;
	//Adress
	@OneToOne
	private Address address;
	//Encounter
	@OneToMany
	private List<Encounter> encounters;
	
	
	public String getBranchId() {
		return branchId;
	}
	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public String gethospital_id() {
		return hospital_id;
	}
	public void sethospital_id(String hospital_id) {
		this.hospital_id = hospital_id;
	}
	public int getNumberOfDactors() {
		return numberOfDactors;
	}

	public void setNumberOfDactors(int numberOfDactors) {
		this.numberOfDactors = numberOfDactors;
	}
	public int getNumberOfBeds() {
		return NumberOfBeds;
	}

	public void setNumberOfBeds(int numberOfBeds) {
		NumberOfBeds = numberOfBeds;
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
	
	@Override
	public String toString() {
		return "Branch [branchId=" + branchId + ", branchName=" + branchName + ", hospital_id=" + hospital_id
				+ ", numberOfDactors=" + numberOfDactors + ", NumberOfBeds=" + NumberOfBeds + "]";
	}
	
}
