package com.hospital_app.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.entityManagers.CreateEntitys;
import com.hospital_app.entity.Branch;
import com.hospital_app.entity.Encounter;
import com.hospital_app.entity.MedOrder;
import com.hospital_app.entity.Person;


public class EncounterDao {
	Scanner sc=new Scanner(System.in);
	EntityManagerFactory factory=CreateEntitys.factory;
	EntityManager manager=CreateEntitys.manager;
	EntityTransaction transaction=CreateEntitys.transaction;
	HospitalDao hospitaldao=HospitalDao.createHospoitalDao();
	
	BranchDao branchdao=BranchDao.createBranchDao();
	PersonDao persondao=PersonDao.createPersonDao();
	public Encounter saveEncounter() {
		System.out.println("enter token number:");
		int tokenNumber=sc.nextInt();
		System.out.println("enter person age");
		int personAge=sc.nextInt();
		System.out.println("enter deptartment number");
		int departNumber=sc.nextInt();
		System.out.println("enter dactor name");
		String dactorName=sc.next();
		System.out.println("enter Branch id");
		int BranchId=sc.nextInt();
		System.out.println("enter person id");
		List<MedOrder> medorders=new ArrayList<MedOrder>();
		int personId=sc.nextInt();
		Encounter encounter=new Encounter();
		encounter.settokenNumber(tokenNumber);
		encounter.setpersonAge(personAge);
		encounter.setDeptNumber(departNumber);
		encounter.setDactorName(dactorName);
		encounter.setMedorders(medorders);
		return saveEncounter(encounter,personId,BranchId);
	}
	public Encounter saveEncounter(Encounter encounter,int personId,int branchId) {
		if(encounter!=null) {
			Branch branch=branchdao.findBranchById(branchId);
			Person person=persondao.findPerson(personId);
			if(branch!=null && person!=null) {
				List<Encounter> branchencounters=branch.getEncounters();
				//adding encounters into bhranch
				branchencounters.add(encounter);
				//adding branch to encounter as it is bidirection
				encounter.setBranch(branch);
				//adding person to encounter
				encounter.setPerson(person);
				//adding encounter to person arraylist
				List<Encounter> personencounters=person.getEncounters();
				personencounters.add(encounter);
				person.setEncounters(personencounters);
				transaction.begin();
//				manager.persist(encounter);
				manager.merge(branch);
				transaction.commit();
				System.out.println("encounter saved sucessfully");
				return encounter;
				/*
				 * here iam not persisting encounter and not merging person
				 * beacuse if i want to store encounter i should add them to person and branch and i need to megre them
				 * but i applied cascadingn as i perform merge in branch it effects in encounter 
				 * and cascading in encounters to person will effect in person
				 */
			}
			else {
				System.out.println("person id or branch id doesnot exist in the hospital");
				return null;
			}
		}
		else {
			System.out.println("something went wrong please try again");
			return encounter;
		}
	}
	public Encounter findEncounter() {
		System.out.println("enter encounter id");
		int encounterId=sc.nextInt();
		return findEncounter(encounterId);
	}
	public Encounter findEncounter(int encounterId) {
		Encounter encounter=manager.find(Encounter.class,encounterId);
		if(encounter!=null) {
			System.out.println("Encounter id:"+encounter.getEncounterId());
			System.out.println("Token number:"+encounter.gettokenNumber());
			System.out.println("person age:"+encounter.getpersonAge());
			System.out.println("Dactor name:"+encounter.getDactorName());
			System.out.println("department number:"+encounter.getDeptNumber());
			return encounter;
		}
		else {
			System.out.println("encounter doesnot exist with given id:");
			return encounter;
		}
	}
	//remove encounters
	public boolean removeEncounters() {
		System.out.println("enter encounter id to remove");
		int encounterid=sc.nextInt();
		return remomeEncounters(encounterid);
	}
	public boolean remomeEncounters(int encounterId) {
		Encounter encounter=findEncounter(encounterId);
		if(encounter!=null) {
			transaction.begin();
			manager.remove(encounter);
			transaction.commit();
			System.out.println("encounter removed sucessfully");
			return true;
		}
		else {
			return false;
		}
	}
	public Encounter updateEncounter() {
		System.out.println("enter encounter id to update");
		int encounterId=sc.nextInt();
		Encounter encounter=findEncounter(encounterId);
		if(encounter!=null) {
			return updateEncounter(encounterId, encounter);
		}
		else {
			System.out.println("cannnot update as encounter is not present with given id");
			return null;
		}
	}
	public Encounter updateEncounter(int encounterId,Encounter encounter) {
		if(encounter!=null && encounterId==encounter.getEncounterId()) {
			System.out.println("enter new values to update else enter same values asitis");
			System.out.println("enter token number:");
			int tokenNumber=sc.nextInt();
			System.out.println("enter person age");
			int personAge=sc.nextInt();
			System.out.println("enter deptartment number");
			int departNumber=sc.nextInt();
			System.out.println("enter dactor name");
			String dactorName=sc.next();
			encounter.settokenNumber(tokenNumber);
			encounter.setpersonAge(personAge);
			encounter.setDeptNumber(departNumber);
			encounter.setDactorName(dactorName);
			transaction.begin();
			manager.merge(encounter);
			transaction.commit();
			System.out.println("encounter updated sucessfully");
			return encounter;
		}
		else {
			System.out.println("cannot update encounter that doesnt exist");
			return null;
		}
	}
	public static EncounterDao createEncounterDao() {
		return new EncounterDao();
	}
}
