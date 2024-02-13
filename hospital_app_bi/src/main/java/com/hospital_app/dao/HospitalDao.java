package com.hospital_app.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.transaction.Transaction;

import com.entityManagers.CreateEntitys;
import com.hospital_app.entity.Branch;
import com.hospital_app.entity.Hospital;


public class HospitalDao {
	Scanner sc=new Scanner(System.in);
	EntityManagerFactory factory=CreateEntitys.factory;
	EntityManager manager=CreateEntitys.manager;
	EntityTransaction transaction=CreateEntitys.transaction;
	public  Hospital saveHospital() {
		System.out.println("enter hospital name:");
		String hospitanName=sc.next();
		System.out.println("enter hospital type");
		String hospitalType=sc.next();
		List<Branch> branches=new ArrayList<Branch>();
		Hospital hospital=new Hospital();
		hospital.setHospitalName(hospitanName);
		hospital.setHospitalType(hospitalType);
		hospital.setBranches(branches);
		return saveHospital(hospital);
	}
	public Hospital saveHospital(Hospital hospital) {
		if(hospital!=null) {
			transaction.begin();
			manager.persist(hospital);
			transaction.commit();
			System.out.println("hospital added sucessfully");
		}
		else {
			System.out.println("something went wrong please try again");
		}
		return hospital;
	}
	//find hospital by id
		public Hospital findHospitalById() {
			System.out.println("enter hospital id");
			int hospitalId=sc.nextInt();
			return findHospitalById(hospitalId);
		}
		public Hospital findHospitalById(int hospitalId) {
			Hospital hospital=manager.find(Hospital.class, hospitalId);
			if(hospital!=null) {
				System.out.println("hospital id:"+hospital.getHospitalId());
				System.out.println("hospital namel:"+hospital.getHospitalName());
				System.out.println("hospital created time:"+hospital.getHospitalCreatedTime());
				System.out.println("hospital type:"+hospital.getHospitalType());
				System.out.println("number of branches:"+hospital.getNumberOfBranches());
				return hospital;
			}
			else {
				System.out.println("hospital doesnot exist with given id");
				return hospital;
			}
		}
		public boolean removeHospital() {
			System.out.println("enter hospital id to remove");
			int id=sc.nextInt();
			return removeHospital(id);
		}
		public boolean removeHospital(int hospitalId) {
			Hospital hospital=findHospitalById(hospitalId);
			if(hospital!=null) {
				transaction.begin();
				manager.remove(hospital);
				transaction.commit();
				return true;
			}
			else {
				return false;
			}
			/*
			 * if you delete hospital all branches will be deleted as it branches 
			 * beacuse cascading is applied no need to remove manually
			 * same way if you delete hospital all branches will be removed
			 * if branch is removed all encounters to the branch will be removed
			 * if encounter is removed all medorders to encounter will be removed
			 */
			
		}
		public Hospital updateHospital() {
			System.out.println("enter id to update");
			int hospitalId=sc.nextInt();
			Hospital hospital=findHospitalById(hospitalId);
			if(hospital!=null) {
				return updateHospital(hospitalId, hospital);
			}
			else {
				System.out.println("cannot update the hospital which doesnot exist");
				return null;
			}
		}
		public Hospital updateHospital(int hospitaild,Hospital hospital) {
			if(hospital!=null && hospitaild==hospital.getHospitalId()) {
				System.out.println("enter new values which you want to update and remaining enter old values asitis");
				System.out.println("enter hospital name:");
				String hospitanName=sc.next();
				System.out.println("enter hospital type");
				String hospitalType=sc.next();
				hospital.setHospitalName(hospitanName);
				hospital.setHospitalType(hospitalType);
				transaction.begin();
				manager.merge(hospital);
				transaction.commit();
				return hospital;
			}
			else {
				System.out.println("xcannot update hospital which doesnot exist");
				return null;
			}
		}
	public static HospitalDao createHospoitalDao() {
		return new HospitalDao();
	}
}
