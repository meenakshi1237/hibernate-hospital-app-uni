package com.entityManagers;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class CreateEntitys {
	/*
	 * insted of creating entity in every dao 
	 * creating here and using them with classname.veriablename
	 */
	public static EntityManagerFactory factory=Persistence.createEntityManagerFactory("vikas");
	public static EntityManager manager=factory.createEntityManager();
	public static EntityTransaction transaction=manager.getTransaction();
}
