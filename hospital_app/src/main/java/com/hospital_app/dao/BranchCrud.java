package com.hospital_app.dao;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.hospital_app.dto.Address;
import com.hospital_app.dto.Branch;
import com.hospital_app.dto.Hospital;

public class BranchCrud {
	Scanner sc=new Scanner(System.in);
	HospitalCrud hospitalcrud=new HospitalCrud();
	AddressCrud addresscrud=new AddressCrud();
	EntityManagerFactory factory=Persistence.createEntityManagerFactory("vikas");
	EntityManager manager=factory.createEntityManager();
	EntityTransaction transaction=manager.getTransaction();
	public  Branch saveBranch() {
		System.out.println("enter branch id:");
		String branchID=sc.next();
		System.out.println("enter branch name:");
		String branchName=sc.next();
		System.out.println("enter hospital id");
		String hospitalid=sc.next();
		System.out.println("enter number of dactors");
		int nubmerOfDactors=sc.nextInt();
		System.out.println("enter total number of beds");
		int numberOfBeds=sc.nextInt();
		Branch branch=new Branch();
		branch.setBranchId(branchID);
		branch.setBranchName(branchName);
		branch.sethospital_id(hospitalid);
		branch.setNumberOfDactors(nubmerOfDactors);
		branch.setNumberOfBeds(numberOfBeds);
		Address address=addresscrud.addAddress();
		branch.setAddress(address);
		 return saveBranch(branch);
	}
	public Branch saveBranch(Branch branch) {
		if(branch!=null) {
			Hospital hospital=hospitalcrud.findHospitalById(branch.gethospital_id());
			if(hospital!=null) {
				List<Branch> branches=hospital.getBranches();
				
				branches.add(branch);
				hospital.setBranches(branches);
				hospital.setNumberOfBranches(branches.size());
				transaction.begin();
				manager.persist(branch);
				manager.merge(hospital);
				transaction.commit();
				System.out.println("branch saved sycessfully");
			}
			else {
				System.out.println("cannot add branch to  hospital doesnot exist");
				return null;
			}
		}
		else {
			System.out.println("something went wrong please try again");
		}
		return branch;
	}
	//find Branch
	public Branch findBranchById() {
		System.out.println("enter Branch id");
		String branchId=sc.next();
		return findBranchById(branchId);
	}
	public Branch findBranchById(String branchId) {
		Branch branch=manager.find(Branch.class, branchId);
		if(branch!=null) {
			System.out.println("Branch id:"+branch.getBranchId());
			System.out.println("branch name:"+branch.getBranchName());
			System.out.println("hospital id:"+branch.gethospital_id());
			System.out.println("number of dactors:"+branch.getNumberOfDactors());
			System.out.println("number of beds:"+branch.getNumberOfBeds());
			return branch;
		}
		else {
			System.out.println("branch doesnot exist with given id");
			return branch;
		}
	}
	public Branch removeBranch() {
		System.out.println("enter hospital id in which you want to remove");
		String hospitalId=sc.next();
		System.out.println("enter branch id to remove");
		String branchid=sc.next();
		return removeBranch(branchid, hospitalId);
	}
	public Branch removeBranch(String branchId,String hospitalId) {
		Hospital hospital=hospitalcrud.findHospitalById(hospitalId);
		Branch branch=null;
		if(hospital!=null) {
			List<Branch> branches=hospital.getBranches();
			Iterator<Branch> i=branches.iterator();
			while(i.hasNext()) {
				branch=i.next();
				if(branch.getBranchId().equals(branchId)) {
					Address address=addresscrud.removeAddress(branch.getAddress().getponcode());
					i.remove();
					hospital.setBranches(branches);
					transaction.begin();
					manager.merge(hospital);
					manager.remove(branch);
					manager.remove(address);
					transaction.commit();
					System.out.println("branch removed sucessfully");
					break;
				}
			}
		}
		return branch;
	}

}
