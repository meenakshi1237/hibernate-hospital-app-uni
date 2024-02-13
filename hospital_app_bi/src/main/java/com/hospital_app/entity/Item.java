package com.hospital_app.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
@Entity
public class Item {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "item_id_gen")
	@SequenceGenerator(name = "item_id_gen",initialValue = 701,allocationSize = 1,sequenceName = "item_id_seq")
	private int itemId;
	private String itemName;
	private int itemQuantity;
	private double itemPrise;
	
	//items
	@ManyToMany(mappedBy = "items")
	private List<MedOrder> medorders;

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public int getItemQuantity() {
		return itemQuantity;
	}

	public void setItemQuantity(int itemQuantity) {
		this.itemQuantity = itemQuantity;
	}

	public double getItemPrise() {
		return itemPrise;
	}

	public void setItemPrise(double itemPrise) {
		this.itemPrise = itemPrise;
	}

	public List<MedOrder> getMedorders() {
		return medorders;
	}

	public void setMedorders(List<MedOrder> medorders) {
		this.medorders = medorders;
	}
	
	
	
}
