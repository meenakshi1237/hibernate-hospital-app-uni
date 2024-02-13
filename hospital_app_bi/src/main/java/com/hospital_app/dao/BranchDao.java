package com.hospital_app.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.entityManagers.CreateEntitys;
import com.hospital_app.entity.Address;
import com.hospital_app.entity.Branch;
import com.hospital_app.entity.Encounter;
import com.hospital_app.entity.Hospital;

public class BranchDao {
	Scanner sc=new Scanner(System.in);
	EntityManagerFactory factory=CreateEntitys.factory;
	EntityManager manager=CreateEntitys.manager;
	EntityTransaction transaction=CreateEntitys.transaction;
	HospitalDao hospitaldao=HospitalDao.createHospoitalDao();
	AddressDao addressdao=AddressDao.createAddressDao();
	public  Branch saveBranch() {
		System.out.println("enter branch name:");
		String branchName=sc.next();
		System.out.println("enter hospital id");
		int hospitalid=sc.nextInt();
		System.out.println("enter number of dactors");
		int nubmerOfDactors=sc.nextInt();
		System.out.println("enter branch city");
		String city=sc.next();
		List<Encounter> encounters=new ArrayList<Encounter>();
		Branch branch=new Branch();
		branch.setBranchName(branchName);
		branch.setBranchCity(city);
		branch.setNumberOfDactors(nubmerOfDactors);
		branch.setHospital_id(hospitalid);
		branch.setEncounters(encounters);
		return saveBranch(branch);
	}
	public Branch saveBranch(Branch branch) {
		if(branch!=null) {
			Hospital hospital=hospitaldao.findHospitalById(branch.getHospital_id());
			if(hospital!=null) {
				Address address=addressdao.addAddress();
				branch.setHospital(hospital);
				branch.setAddress(address);
				List<Branch> branches=hospital.getBranches();
				branches.add(branch);
				hospital.setBranches(branches);
				hospital.setNumberOfBranches(branches.size());
				transaction.begin();
				/*
				 * here iam only updating the hospital as it has cascading it reflects in branches
				 * branches automatically update if you update the hospital
				 * if you update branch address will automatically update
				 * 
				 * and update means it can also used to save
				 */
//				manager.persist(branch);
				manager.merge(hospital);
				transaction.commit();
				System.out.println("branch saved sycessfully");
				return branch;
			}
			else {
				System.out.println("cannot add branch to  hospital doesnot exist");
				return null;
			}
				
			}
		else {
			System.out.println("something went wrong please try again");

		}
		return null;
	}
	public Branch findBranchById() {
		System.out.println("enter Branch id");
		int branchId=sc.nextInt();
		return findBranchById(branchId);
	}
	public Branch findBranchById(int branchId) {
		Branch branch=manager.find(Branch.class, branchId);
		if(branch!=null) {
			System.out.println("Branch id:"+branch.getBranchId());
			System.out.println("branch name:"+branch.getBranchName());
			System.out.println("hospital id:"+branch.getHospital_id());
			System.out.println("number of dactors:"+branch.getNumberOfDactors());
			System.out.println("hospital location:"+branch.getBranchCity());
			return branch;
		}
		else {
			System.out.println("branch doesnot exist with given id");
			return branch;
		}
	}
	public boolean removeBranch() {
		System.out.println("enter branch id to remove");
		int branchid=sc.nextInt();
		return removeBranch(branchid);
	}
	public boolean removeBranch(int branchid) {
		Branch branch=findBranchById(branchid);
		if(branch!=null) {
			transaction.begin();
			manager.remove(branch);
			transaction.commit();
			System.out.println("branch removed sucessfully");
			return true;
		}
		else {
			return false;
		}
		/*
		 * as cascading is applied no need to remove manually
		 * if branch is removed all encounters to the branch will be removed
		 * if encounter is removed all medorders to encounter will be removed
		 */
	}
	public Branch updateBranch() {
		System.out.println("enter branch id to update");
		int branchId=sc.nextInt();
		Branch branch=findBranchById(branchId);
		if(branch!=null) {
			return updateBranch(branchId, branch);
		}
		return null;
	}
	public Branch updateBranch(int branchId,Branch branch) {
		if(branch!=null) {
			System.out.println("enter new values to update remaining enter same asitis");
			System.out.println("enter branch name:");
			String branchName=sc.next();
			System.out.println("enter number of dactors");
			int nubmerOfDactors=sc.nextInt();
			System.out.println("enter branch city");
			String city=sc.next();
			branch.setBranchName(branchName);
			branch.setBranchCity(city);
			branch.setNumberOfDactors(nubmerOfDactors);
			transaction.begin();
			manager.merge(branch);
			transaction.commit();
			System.out.println("branch updated sucessfully");
			return branch;
		}
		else {
			System.out.println("cannot update branch which doesnot exist");
			return null;
		}
	}
	public static BranchDao createBranchDao() {
		return new BranchDao();
	}
}
