package com.hospital_app.dao;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.hospital_app.dto.Branch;
import com.hospital_app.dto.Encounter;
import com.hospital_app.dto.MedOrders;
import com.hospital_app.dto.Person;

public class EncounterCrud {
	Scanner sc=new Scanner(System.in);
	EntityManagerFactory factory=Persistence.createEntityManagerFactory("vikas");
	EntityManager manager=factory.createEntityManager();
	EntityTransaction transaction=manager.getTransaction();
	BranchCrud branchcrud=new BranchCrud();
	PersonCrud personcrud=new PersonCrud();
	public Encounter saveEncounter() {
		System.out.println("enter encounter id");
		String encounterId=sc.next();
		System.out.println("enter branch id:");
		String branchId=sc.next();
		System.out.println("enter person id");
		String personId=sc.next();
		System.out.println("enter dactor name");
		String dactorNamr=sc.next();
		System.out.println("enter medicine name");
		String medicineName=sc.next();
		Encounter encounter=new Encounter();
		encounter.setbranch_id(branchId);
		encounter.setDactorName(dactorNamr);
		encounter.setEncounterId(encounterId);
		encounter.setMedicineName(medicineName);
		encounter.setPerson_id(personId);
		return saveEncounter(encounter);
	}
	public Encounter saveEncounter(Encounter encounter) {
		if(encounter!=null) {
			Branch branch=branchcrud.findBranchById(encounter.getbranch_id());
			Person person=personcrud.findPerson(encounter.getPerson_id());
			if(branch!=null && person!=null) {
				List<Encounter> encounters=branch.getEncounters();
				encounters.add(encounter);
				encounter.setPerson(person);
				transaction.begin();
				manager.persist(encounter);
				manager.merge(branch);
				transaction.commit();
				System.out.println("encounter saved sucessfully");
				return encounter;
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
		String encounterId=sc.next();
		return findEncounter(encounterId);
	}
	public Encounter findEncounter(String encounterId) {
		Encounter encounter=manager.find(Encounter.class,encounterId);
		if(encounter!=null) {
			System.out.println("Encounter id:"+encounter.getEncounterId());
			System.out.println("person id:"+encounter.getPerson_id());
			System.out.println("branch id:"+encounter.getbranch_id());
			System.out.println("Dactor name:"+encounter.getDactorName());
			System.out.println("medicine name:"+encounter.getMedicineName());
			return encounter;
		}
		else {
			System.out.println("encounter doesnot exist with given id:");
			return encounter;
		}
	}
}
