package com.hospital_app.dao;

import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.hospital_app.dto.Person;

public class PersonCrud {
	Scanner sc=new Scanner(System.in);
	EntityManagerFactory factory=Persistence.createEntityManagerFactory("vikas");
	EntityManager manager=factory.createEntityManager();
	EntityTransaction transaction=manager.getTransaction();
	
	public Person savePerson() {
		System.out.println("enter person id");
		String personId=sc.next();
		System.out.println("enter person name:");
		String personName=sc.next();
		System.out.println("enter person email");
		String personEmail=sc.next();
		System.out.println("enter person boloodgrop");
		String bolldGroup=sc.next();
		Person person=new Person();
		person.setBloodGroup(bolldGroup);
		person.setName(personName);
		person.setPersonEmail(personEmail);
		person.setPersonId(personId);
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
		String personId=sc.next();
		return findPerson(personId);
	}
	public Person findPerson(String personId) {
		Person person= manager.find(Person.class, personId);
		if(person!=null) {
			System.out.println("perdon details are");
			System.out.println("person id:"+person.getPersonId());
			System.out.println("Person name:"+person.getName());
			System.out.println("person email:"+person.getPersonEmail());
			System.out.println("person bloodgroup:"+person.getBloodGroup());
			return person;
		}
		else {
			System.out.println("person doesnot exist with given id");
			return person;
		}
	}
	
	
}
