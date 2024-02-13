package com.hospital_app.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.entityManagers.CreateEntitys;
import com.hospital_app.entity.Item;
import com.hospital_app.entity.MedOrder;

public class ItemDao {
	Scanner sc=new Scanner(System.in);
	EntityManagerFactory factory=CreateEntitys.factory;
	EntityManager manager=CreateEntitys.manager;
	EntityTransaction transaction=CreateEntitys.transaction;
	public Item saveItem() {
		
		System.out.println("enter item name");
		String itemName=sc.next();
		System.out.println("enter quantity");
		int quantity=sc.nextInt();
		System.out.println("enter item prise");
		double prise=sc.nextDouble();
		Item item=new Item();
		item.setItemPrise(prise);
		item.setItemName(itemName);
		item.setItemQuantity(quantity);
		return saveItem(item);
		
	}
	public Item saveItem(Item item) {
		if(item!=null) {
			//cascading is applied only for save and update in many to mant 
			//if you apply for remove ypu will get lot of consequences
//			transaction.begin();
//			manager.persist(item);
//			transaction.commit();
//			System.out.println("item saved sucessfully");
			return item;
		}
		else {
			System.out.println("something went wrong please try again");
			return item;
		}
	}
	public Item findItem() {
		System.out.println("enter item id to find");
		int itemId=sc.nextInt();
		return findItem(itemId);
	}
	public Item findItem(int itemId) {
		Item item=manager.find(Item.class,itemId);
		if(item!=null) {
			return item;
		}
		else {
			return null;
		}
	}
	
	public boolean remoVeItem() {
		System.out.println("enter item id to remove");
		int itemId=sc.nextInt();
		return removeItem(itemId);
	}
	public boolean removeItem(int itemId) {
		Item item=findItem(itemId);
/*
 * item cannot be removed directly as it reference stored in medorders 
 * and we cannot apply cascading to remove bescuase many-To-Many
 * so we need to remove the reference of item in medorderd list
 */
		
		if(item!=null) {
			//get medorders list from item
			List<MedOrder> medorders=item.getMedorders();
			//iterating medorders list to get each medorder
			for(int i=0;i<medorders.size();i++) {
				//accesing each medorder
				MedOrder medorder=medorders.get(i);
				List<Item> items=medorder.getItems();
				for(int j=0;j<items.size();j++) {
					Item itemremove=items.get(j);
					if(itemremove.getItemId()==itemId) {
						items.remove(j);
						medorder.setItems(items);
						transaction.begin();
						manager.merge(medorder);
						transaction.commit();
						break;
					}
				}
				
			}
			transaction.begin();
			manager.remove(item);
			transaction.commit();
			System.out.println("item removed");
			return true;
		}
		else {
			return false;
		}
	}
	
	public static ItemDao createItemDao() {
		return new ItemDao();
	}
}
