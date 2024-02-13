package com.hospital_app.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.hospital_app.dto.Encounter;
import com.hospital_app.dto.Item;
import com.hospital_app.dto.MedOrders;

public class MedOrdersCrud {
	Scanner sc=new Scanner(System.in);
	EntityManagerFactory factory=Persistence.createEntityManagerFactory("vikas");
	EntityManager manager=factory.createEntityManager();
	EntityTransaction transaction=manager.getTransaction();
	ItemCrud itemcrud=new ItemCrud();
	EncounterCrud encountercrud=new EncounterCrud();
	public MedOrders saveMedOrders() {
		System.out.println("enter medorders id");
		String orderId=sc.next();
		System.out.println("enter encounter id");
		String encounterId=sc.next();
		System.out.println("enter delivary location");
		String delivaryLocation=sc.next();
		System.out.println("enter order cost");
		double prise=sc.nextDouble();
		System.out.println("enter payment type");
		String paymentType=sc.next();
		MedOrders medorders=new MedOrders();
		medorders.setDelivaryLocation(delivaryLocation);
		medorders.setEncounter_id(encounterId);
		medorders.setOrderId(orderId);
		medorders.setOrderPrise(prise);
		medorders.setPayMentType(paymentType);
		List<Item> items=new ArrayList<Item>();
		medorders.setItems(items);
		Item item=itemcrud.saveItem();
		if(item!=null) {
			items=medorders.getItems();
			items.add(item);
			medorders.setItems(items);
			return saveMedOrders(medorders);
		}
		else {
			System.out.println("something went wrong please try again");
			return null;
		}
		
	}
	public MedOrders saveMedOrders(MedOrders medorders) {
		if(medorders!=null) {
			Encounter encounter=encountercrud.findEncounter(medorders.getEncounter_id());
			if(encounter!=null) {
				List<MedOrders> medorderslist=encounter.getMedorders();
				medorderslist.add(medorders);
				transaction.begin();
				manager.persist(medorders);
				manager.merge(encounter);
				transaction.commit();
				System.out.println("med orders saved sucessfully");
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
	//find medorders
	public MedOrders findMedorder() {
		System.out.println("enter med order id to find");
		String medOrderId=sc.next();
		return finMedOrders(medOrderId);
	}
	public MedOrders finMedOrders(String medorderId) {
		MedOrders medorder=manager.find(MedOrders.class, medorderId);
		if(medorder!=null) {
			System.out.println("med order id:"+medorder.getOrderId());
			System.out.println("encounter id:"+medorder.getEncounter_id());
			System.out.println("Delivary location:"+medorder.getDelivaryLocation());
			System.out.println("med order prise:"+medorder.getOrderPrise());
			System.out.println("payment mode:"+medorder.getPayMentType());
			return medorder;
		}
		else {
			System.out.println("medorders doesnot exist with given id:");
			return medorder;
		}
	}
}
