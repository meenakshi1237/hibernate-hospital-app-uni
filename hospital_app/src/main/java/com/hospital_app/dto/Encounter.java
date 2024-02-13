package com.hospital_app.dto;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Encounter {
	@Id
	private String encounterId;
	private String branch_id;
	private String person_id;
	private String dactorName;
	private String medicineName;
	
	//person
	@ManyToOne
	private Person person;
	//medOrders
	@OneToMany
	private List<MedOrders> medorders;
	
	public String getEncounterId() {
		return encounterId;
	}
	public void setEncounterId(String encounterId) {
		this.encounterId = encounterId;
	}
	public String getbranch_id() {
		return branch_id;
	}
	public void setbranch_id(String branch_id) {
		this.branch_id = branch_id;
	}
	public String getPerson_id() {
		return person_id;
	}
	public void setPerson_id(String person_id) {
		this.person_id = person_id;
	}
	public String getDactorName() {
		return dactorName;
	}
	public void setDactorName(String dactorName) {
		this.dactorName = dactorName;
	}
	public String getMedicineName() {
		return medicineName;
	}
	public void setMedicineName(String medicineName) {
		this.medicineName = medicineName;
	}
	
	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
	public List<MedOrders> getMedorders() {
		return medorders;
	}
	public void setMedorders(List<MedOrders> medorders) {
		this.medorders = medorders;
	}
	@Override
	public String toString() {
		return "Encounter [encounterId=" + encounterId + ", branch_id=" + branch_id + ", dactorName=" + dactorName
				+ ", medicineName=" + medicineName + ", person="
				+ person + ", medorders=" + medorders + "]";
	}
	
}
