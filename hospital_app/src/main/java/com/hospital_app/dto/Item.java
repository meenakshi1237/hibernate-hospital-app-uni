package com.hospital_app.dto;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Item {
	@Id
	private int itemId;
	private String itemName;
	private int itemQuantity;
	private double itemCost;
	private String itemType;
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
	public double getItemCost() {
		return itemCost;
	}
	public void setItemCost(double itemCost) {
		this.itemCost = itemCost;
	}
	public String getItemType() {
		return itemType;
	}
	public void setItemType(String itemType) {
		this.itemType = itemType;
	}
	@Override
	public String toString() {
		return "Item [itemId=" + itemId + ", itemName=" + itemName + ", itemQuantity=" + itemQuantity + ", itemCost="
				+ itemCost + ", itemType=" + itemType + "]";
	}
	
}
