package com.hospital_app.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

@Entity
public class Person {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "person_id_gen")
	@SequenceGenerator(name ="person_id_gen",initialValue = 401,allocationSize = 1,sequenceName = "person_id_seq")
	@Column(insertable = false)
	private int personId;
	private String personName;
	private String bloodGroup;
	private long perconContactNumber;
	//encounters
	@OneToMany(mappedBy = "person",cascade = CascadeType.REMOVE)
	List<Encounter> encounters;
	public int getPersonId() {
		return personId;
	}
	public void setPersonId(int personId) {
		this.personId = personId;
	}
	public String getPersonName() {
		return personName;
	}
	public void setPersonName(String personName) {
		this.personName = personName;
	}
	public String getBloodGroup() {
		return bloodGroup;
	}
	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}
	public long getPerconContactNumber() {
		return perconContactNumber;
	}
	public void setPerconContactNumber(long perconContactNumber) {
		this.perconContactNumber = perconContactNumber;
	}
	public List<Encounter> getEncounters() {
		return encounters;
	}
	public void setEncounters(List<Encounter> encounters) {
		this.encounters = encounters;
	}
	
	
}
