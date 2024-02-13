package com.hospital_app.dao;

import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.hospital_app.dto.Hospital;

public class HospitalCrud {
	Scanner sc=new Scanner(System.in);
	EntityManagerFactory factory=Persistence.createEntityManagerFactory("vikas");
	EntityManager manager=factory.createEntityManager();
	EntityTransaction transaction=manager.getTransaction();
	public  Hospital saveHospital() {
		System.out.println("enter hospital id:");
		String hoipitalID=sc.next();
		System.out.println("enter hospital name:");
		String hospitanName=sc.next();
		System.out.println("enter hospital type");
		String hospitalType=sc.next();
		System.out.println("enter contact email address");
		String hospitalEmail=sc.next();
		Hospital hospital=new Hospital();
		hospital.setHospitalId(hoipitalID);
		hospital.sethospitanName(hospitanName);
		hospital.sethospitalType(hospitalType);
		hospital.setHospitalEmail(hospitalEmail);
		 return saveHospital(hospital);
	}
	public Hospital saveHospital(Hospital hospital) {
		if(hospital!=null) {
			transaction.begin();
			manager.persist(hospital);
			transaction.commit();
			System.out.println("hospital saved sycessfully");
		}
		else {
			System.out.println("something went wrong please try again");
		}
		return hospital;
	}
	//find hospital by id
	public Hospital findHospitalById() {
		System.out.println("enter hospital id");
		String hospitalId=sc.next();
		return findHospitalById(hospitalId);
	}
	public Hospital findHospitalById(String hospitalId) {
		Hospital hospital=manager.find(Hospital.class, hospitalId);
		if(hospital!=null) {
			System.out.println("hospital id:"+hospital.getHospitalId());
			System.out.println("hospital name:"+hospital.gethospitanName());
			System.out.println("hospital type:"+hospital.gethospitalType());
			System.out.println("hospital contact email:"+hospital.getHospitalEmail());
			System.out.println("number of branches:"+hospital.getNumberOfBranches());
			return hospital;
		}
		else {
			System.out.println("hospital doesnot exist with given id");
			return hospital;
		}
	}
	public Hospital removeHospital() {
		System.out.println("enter hospital id to remove");
		String hospitalId=sc.next();
		return removeHospital(hospitalId);
	}
	public Hospital removeHospital(String hospitalId) {
		Hospital hospital=findHospitalById(hospitalId);
		if(hospital!=null) {
			transaction.begin();
			manager.remove(hospital);
			transaction.commit();
			System.out.println("hospital removed sucessfully");
			return hospital;
		}
		else {
			System.out.println("cannoy remove hospital thet doesnot exist");
			return hospital;
		}
	}
}
