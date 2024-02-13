package com.hospital_app.dao;

import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.hospital_app.dto.Item;

public class ItemCrud {
	Scanner sc=new Scanner(System.in);
	EntityManagerFactory factory=Persistence.createEntityManagerFactory("vikas");
	EntityManager manager=factory.createEntityManager();
	EntityTransaction transaction=manager.getTransaction();
	EncounterCrud encountercrud=new EncounterCrud();
	public Item saveItem() {
		System.out.println("enter item id");
		int itemId=sc.nextInt();
		System.out.println("enter item name");
		String itemName=sc.next();
		System.out.println("enter quantity");
		int quantity=sc.nextInt();
		System.out.println("enter item prise");
		double prise=sc.nextDouble();
		System.out.println("enter item type");
		String type=sc.next();
		Item item=new Item();
		item.setItemCost(prise);
		item.setItemId(itemId);
		item.setItemName(itemName);
		item.setItemQuantity(quantity);
		item.setItemType(type);
		return saveItem(item);
	}
	public Item saveItem(Item item) {
		if(item!=null) {
			transaction.begin();
			manager.persist(item);
			transaction.commit();
			System.out.println("item saved sucessfully");
			return item;
		}
		else {
			System.out.println("something went wrong please try again");
			return item;
		}
	}
}
