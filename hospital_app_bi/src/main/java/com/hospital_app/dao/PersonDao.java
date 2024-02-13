package com.hospital_app.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import com.entityManagers.CreateEntitys;
import com.hospital_app.entity.Encounter;
import com.hospital_app.entity.Person;

public class PersonDao {
	Scanner sc=new Scanner(System.in);
	EntityManagerFactory factory=CreateEntitys.factory;
	EntityManager manager=CreateEntitys.manager;
	EntityTransaction transaction=CreateEntitys.transaction;
	public Person savePerson() {
		System.out.println("enter person name:");
		String personName=sc.next();
		System.out.println("enter person contact number");
		long personMobile=sc.nextLong();
		System.out.println("enter person boloodgrop");
		String bolldGroup=sc.next();
		List<Encounter> encounters=new ArrayList<>();
		Person person=new Person();
		person.setPersonName(personName);
		person.setBloodGroup(bolldGroup);
		person.setPerconContactNumber(personMobile);
		person.setEncounters(encounters);
		return savePerson(person);
	}
	public Person savePerson(Person person) {
		if(person!=null) {
			transaction.begin();
			manager.persist(person);
			transaction.commit();
			System.out.println("person saves sucessfully");
			return person;
		}
		else {
			System.out.println("something went wrong please try again");
			return person;
		}
	}
	public Person findPerson() {
		System.out.println("enter person id to search");
		int personId=sc.nextInt();
		return findPerson(personId);
	}
	public Person findPerson(int personId) {
		Person person= manager.find(Person.class, personId);
		if(person!=null) {
			System.out.println("perdon details are");
			System.out.println("person id:"+person.getPersonId());
			System.out.println("Person name:"+person.getPersonName());
			System.out.println("person email:"+person.getPerconContactNumber());
			System.out.println("person bloodgroup:"+person.getBloodGroup());
			return person;
		}
		else {
			System.out.println("person doesnot exist with given id");
			return person;
		}
	}
	
	public boolean removePerson() {
		System.out.println("enter person id to remove");
		int personId=sc.nextInt();
		return removePerson(personId);
	}
	public boolean removePerson(int personId) {
		Person person=findPerson(personId);
		if(person!=null) {
			transaction.begin();
			manager.remove(person);
			transaction.commit();
			System.out.println("person removed sucessfully");
			return true;
		}
		else {
			return false;
		}
		/*
		 * if i remove the persom all the encounters releted to person will be removed
		 * beacuse cascading is applied in persons to encounters
		 */
	}
	
	public Person updatePerson() {
		System.out.println("enter person id to update");
		int personId=sc.nextInt();
		Person person=findPerson(personId);
		if(person!=null) {
			return updatePerson(personId, person);
		}
		else {
			System.out.println("cannot update person which doesnot exist");
			return null;
		}
	}
	public Person updatePerson(int personId,Person person) {
		if(person!=null && personId==person.getPersonId()) {
			System.out.println("enter values which you want to updare else enter old valuesnitself");
			System.out.println("enter person name:");
			String personName=sc.next();
			System.out.println("enter person contact number");
			long personMobile=sc.nextLong();
			System.out.println("enter person boloodgrop");
			String bolldGroup=sc.next();
			person.setPersonName(personName);
			person.setBloodGroup(bolldGroup);
			person.setPerconContactNumber(personMobile);
			transaction.begin();
			manager.merge(person);
			transaction.commit();
			System.out.println("person updated sucessfully");
			return person;
		}
		else {
			System.out.println("cannot update person which doesnot exist");
			return null;
		}
	}
	public static PersonDao createPersonDao() {
		return new PersonDao();
	}
}
