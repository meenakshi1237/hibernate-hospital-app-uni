package com.hospital_app.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.entityManagers.CreateEntitys;
import com.hospital_app.entity.Encounter;
import com.hospital_app.entity.Item;
import com.hospital_app.entity.MedOrder;



public class MedOrderDao {
	Scanner sc=new Scanner(System.in);
	EntityManagerFactory factory=CreateEntitys.factory;
	EntityManager manager=CreateEntitys.manager;
	EntityTransaction transaction=CreateEntitys.transaction;
	EncounterDao encounterDao=EncounterDao.createEncounterDao();
	ItemDao itemDao=ItemDao.createItemDao();
	public MedOrder saveMedOrders() {
		System.out.println("enter number of items");
		int numberOfItems=sc.nextInt();
		System.out.println("enter delivary location");
		String delivaryLocation=sc.next();
		System.out.println("enter order cost");
		double cost=sc.nextDouble();
		List<Item> items=new ArrayList<Item>();
		System.out.println("enter encounter id");
		int encounterId=sc.nextInt();
		MedOrder medorder=new MedOrder();
		medorder.setDelivaryLocation(delivaryLocation);
		medorder.setnumberOfItems(numberOfItems);
		medorder.setCost(cost);
		medorder.setItems(items);
		return saveMedOrders(medorder,encounterId);
	}
	public MedOrder saveMedOrders(MedOrder medorders,int encounterId) {
		if(medorders!=null) {
			Encounter encounter=encounterDao.findEncounter(encounterId);
			if(encounter!=null) {
				List<MedOrder> medorderslist=encounter.getMedorders();
				//adding medorders to encounters
				medorderslist.add(medorders);
				encounter.setMedorders(medorderslist);
				//adding encounter to medorder
				medorders.setEncounter(encounter);
				//save items
				for(int i=0;i<medorders.getnumberOfItems();i++) {
					System.out.println("enter itemId if you want to add already existing item");
					int itemId=sc.nextInt();
					Item existinngItem=itemDao.findItem(itemId);
					if(existinngItem!=null) {
						List<Item> items=medorders.getItems();
						items.add(existinngItem);
						medorders.setItems(items);
						transaction.begin();
						/*
						 * since cascading is there no need to save or update medorders and items
						 * if you save or update encounters it will effect medorders and medorders will effect items
						 * 
						 */
						manager.merge(encounter);
						transaction.commit();
					}
					else {
						System.out.println("item doesnot exist with given id /n creatinng new item");
						Item item=itemDao.saveItem();
						//adding items to medorders
						List<Item> items=medorders.getItems();
						items.add(item);
						medorders.setItems(items);
						transaction.begin();
//						manager.persist(medorders);
						manager.merge(encounter);
						transaction.commit();
						System.out.println("med orders saved sucessfully");
					}
				}
				return medorders;
			}
			else {
				System.out.println("cannot store medorders into encounter which doesnot exist");
				return null;
			}
		}
		else {
			System.out.println("something went wrong please try again");
			return medorders;
		}
	}
	public MedOrder findMedorder() {
		System.out.println("enter med order id to find");
		int medOrderId=sc.nextInt();
		return finMedOrders(medOrderId);
	}
	public MedOrder finMedOrders(int medorderId) {
		MedOrder medorder=manager.find(MedOrder.class, medorderId);
		if(medorder!=null) {
			System.out.println("med order id:"+medorder.getMedOrderId());
			System.out.println("number of items:"+medorder.getnumberOfItems());
			System.out.println("delivary location:"+medorder.getDelivaryLocation());
			System.out.println("cost:"+medorder.getCost());
			System.out.println("dactor name:"+medorder.getOrderedTime());
			return medorder;
		}
		else {
			System.out.println("medorders doesnot exist with given id:");
			return medorder;
		}
	}
	public boolean removeMedOrders() {
		System.out.println("enter medorderd id to remove");
		int medorderid=sc.nextInt();
		return removeMedOrders(medorderid);
	}
	public boolean removeMedOrders(int medorderId) {
		MedOrder medorder=finMedOrders(medorderId);
		if(medorder!=null) {
			transaction.begin();
			manager.remove(medorder);
			transaction.commit();
			System.out.println("medordder removed sucessfully");
			return true;
		}
		else {
			return false;
		}
		
	}
	public static MedOrderDao createMedOrderDao() {
		return new MedOrderDao();
	}
	
	public MedOrder updateMedOrders() {
		System.out.println("enter med orderid to update");
		int medOrderId=sc.nextInt();
		MedOrder medorder=finMedOrders(medOrderId);
		if(medorder!=null) {
			return updateMedOrders(medOrderId, medorder);
		}
		else {
			System.out.println("cannot update medordder which doesnot exist");
			return null;
		}
	}
	public MedOrder updateMedOrders(int medOrderId,MedOrder medorder) {
		if(medorder!=null && medOrderId==medorder.getMedOrderId()) {
			System.out.println("enter new values to update else enter old values asitis");
			System.out.println("enter delivary location");
			String delivaryLocation=sc.next();
			medorder.setDelivaryLocation(delivaryLocation);
			transaction.begin();
			manager.merge(medorder);
			transaction.commit();
			return medorder;
		}
		else {
			System.out.println("cannot update which medorder doesnt exist");
			return null;
		}
		
	}
	
}
