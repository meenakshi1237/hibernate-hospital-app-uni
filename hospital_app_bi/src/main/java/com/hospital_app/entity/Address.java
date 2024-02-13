package com.hospital_app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
@Entity
public class Address {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "address_id_gen")
	@SequenceGenerator(name = "address_id_gen",initialValue = 201,allocationSize = 1,sequenceName = "adress_id_seq")
	@Column(insertable = false)
	private int AddressId;
	private int pincode;
	private String areaName;
	private String landmark;
	//branch
	@OneToOne(mappedBy = "address")
	private Branch branch;
	public int getAddressId() {
		return AddressId;
	}
	public void setAddressId(int addressId) {
		AddressId = addressId;
	}
	public int getPincode() {
		return pincode;
	}
	public void setPincode(int pincode) {
		this.pincode = pincode;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public String getLandmark() {
		return landmark;
	}
	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}
	public Branch getBranch() {
		return branch;
	}
	public void setBranch(Branch branch) {
		this.branch = branch;
	}
	
	
}
