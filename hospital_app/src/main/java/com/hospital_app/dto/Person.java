package com.hospital_app.dto;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Person {
	@Id
	private String personId;
	private String name;
	private String personEmail;
	private String bloodGroup;
	public String getPersonId() {
		return personId;
	}
	public void setPersonId(String personId) {
		this.personId = personId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPersonEmail() {
		return personEmail;
	}
	public void setPersonEmail(String personEmail) {
		this.personEmail = personEmail;
	}
	public String getBloodGroup() {
		return bloodGroup;
	}
	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}
	@Override
	public String toString() {
		return "Person [personId=" + personId + ", name=" + name + ", personEmail=" + personEmail + ", bloodGroup="
				+ bloodGroup + "]";
	}
	
	
}
