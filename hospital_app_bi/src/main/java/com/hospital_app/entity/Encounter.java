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
import javax.persistence.SequenceGenerator;

@Entity
public class Encounter {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "encounter_id_gen")
	@SequenceGenerator(name = "encounter_id_gen",initialValue = 501,allocationSize = 1,sequenceName = "encounter_id_seq")
	@Column(insertable = false)
	private int encounterId;
	private int tokenNumber;
	private int personAge;
	private int deptNumber;
	private String dactorName;
	//branch
	@ManyToOne
	@JoinColumn
	private Branch branch;
	//persons
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn
	private Person person;
	//medorders
	@OneToMany(mappedBy = "encounter",cascade = CascadeType.ALL)
	private List<MedOrder> medorders;
	public int getEncounterId() {
		return encounterId;
	}
	public void setEncounterId(int encounterId) {
		this.encounterId = encounterId;
	}
	public int gettokenNumber() {
		return tokenNumber;
	}
	public void settokenNumber(int tokenNumber) {
		this.tokenNumber = tokenNumber;
	}
	public int getpersonAge() {
		return personAge;
	}
	public void setpersonAge(int personAge) {
		this.personAge = personAge;
	}
	public int getDeptNumber() {
		return deptNumber;
	}
	public void setDeptNumber(int deptName) {
		this.deptNumber = deptName;
	}
	public String getDactorName() {
		return dactorName;
	}
	public void setDactorName(String dactorName) {
		this.dactorName = dactorName;
	}
	public Branch getBranch() {
		return branch;
	}
	public void setBranch(Branch branch) {
		this.branch = branch;
	}
	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
	public List<MedOrder> getMedorders() {
		return medorders;
	}
	public void setMedorders(List<MedOrder> medorders) {
		this.medorders = medorders;
	}
	
	
	
}
