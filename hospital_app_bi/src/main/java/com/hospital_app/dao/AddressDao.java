package com.hospital_app.dao;

import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.entityManagers.CreateEntitys;
import com.hospital_app.entity.Address;
import com.hospital_app.entity.Branch;

public class AddressDao {
	Scanner sc=new Scanner(System.in);
	
	EntityManagerFactory factory=CreateEntitys.factory;
	EntityManager manager=CreateEntitys.manager;
	EntityTransaction transaction=CreateEntitys.transaction;
	public Address addAddress() {
		System.out.println("enter branch area pincode:");
		int addressPincode=sc.nextInt();

		System.out.println("enter area name");
		String areaName=sc.next();
		System.out.println("enter landmark");
		String landmark=sc.next();
		Address address=new Address();
		address.setAreaName(areaName);;
		address.setLandmark(landmark);
		address.setPincode(addressPincode);
		return addAddress(address);
	}
	public Address addAddress(Address address) {
		if(address!=null) {
//			transaction.begin();
//			manager.persist(address);
//			transaction.commit();
//			System.out.println("address saved");
			return address;
		}
		else {
			System.out.println("something went wrong please try again after some time");
			return address;
		}
	}
	
	public Address updateAddress() {
		BranchDao branchdao=BranchDao.createBranchDao();
		System.out.println("enter branch id to which you want to update address");
		int branchId=sc.nextInt();
		Branch branch=branchdao.findBranchById(branchId);
		if(branch!=null) {
			Address address=branch.getAddress();
			int addressId=address.getAddressId();
			return updateAddress(addressId, address);
		}
		else {
			System.out.println("cannot update address which is not present");
			return null;
		}
	}
	public Address updateAddress(int addressId,Address address) {
		if(address!=null) {
			System.out.println("enter new values to update otherwist provide  same values");
			System.out.println("enter branch area pincode:");
			int addressPincode=sc.nextInt();
			System.out.println("enter city");
			String city=sc.next();
			System.out.println("enter area name");
			String areaName=sc.next();
			System.out.println("enter landmark");
			String landmark=sc.next();
			address.setAreaName(areaName);;
			address.setLandmark(landmark);
			address.setPincode(addressPincode);
			address.setPincode(addressPincode);
			transaction.begin();
			manager.merge(address);
			transaction.commit();
			return address;
		}
		else {
			System.out.println("cannot update address which is not preaent");
			return null;
		}
	}
	public static AddressDao createAddressDao() {
		return new AddressDao();
	}
}
