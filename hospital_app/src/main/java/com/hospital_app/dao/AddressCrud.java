package com.hospital_app.dao;

import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.hospital_app.dto.Address;

public class AddressCrud {
	Scanner sc=new Scanner(System.in);
	HospitalCrud hospitalcrud=new HospitalCrud();
	EntityManagerFactory factory=Persistence.createEntityManagerFactory("vikas");
	EntityManager manager=factory.createEntityManager();
	EntityTransaction transaction=manager.getTransaction();
	
	public Address addAddress() {
		System.out.println("enter branch area pincode:");
		int addressPincode=sc.nextInt();
		System.out.println("enter branch name:");
		String branchName=sc.next();
		System.out.println("enter city");
		String city=sc.next();
		System.out.println("enter area name");
		String areaName=sc.next();
		System.out.println("enter landmark");
		String landmark=sc.next();
		Address address=new Address();
		address.setArea(areaName);;
		address.setBranchName(branchName);
		address.setCity(city);
		address.setLandMark(landmark);
		address.setponcode(addressPincode);
		return addAddress(address);
	}
	public Address addAddress(Address address) {
		if(address!=null) {
			transaction.begin();
			manager.persist(address);
			transaction.commit();
			System.out.println("address saved");
			return address;
		}
		else {
			System.out.println("something went wrong please try again after some time");
			return address;
		}
	}
	public Address removeAddress() {
		System.out.println("enter address pincode to remove");
		int pincode=sc.nextInt();
		return removeAddress(pincode);
	}
	public Address removeAddress(int pincode) {
		Address address=manager.find(Address.class,pincode);
		if(address!=null) {
			return address;
		}
		else {
			System.out.println("address not found");
			return address;
		}
	}
	
}
