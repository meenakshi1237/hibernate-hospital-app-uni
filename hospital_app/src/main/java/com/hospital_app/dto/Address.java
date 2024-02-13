package com.hospital_app.dto;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Address {
	@Id
	private int poncode;
	private String branchName;
	private String city;
	private String area;
	private String landMark;
	public int getponcode() {
		return poncode;
	}
	public void setponcode(int poncode) {
		this.poncode = poncode;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getLandMark() {
		return landMark;
	}
	public void setLandMark(String landMark) {
		this.landMark = landMark;
	}
	@Override
	public String toString() {
		return "Address [poncode=" + poncode + ", branchName=" + branchName + ", city=" + city + ", area=" + area
				+ ", landMark=" + landMark + "]";
	}
	
}
