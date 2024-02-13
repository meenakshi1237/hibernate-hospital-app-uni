package com.hospital_app.dto;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class MedOrders {
	@Id
	private String orderId;
	private String encounter_id;
	private String  delivaryLocation;
	private double orderPrise;
	private String payMentType;
	//itema
	@ManyToMany
	private List<Item> items;
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getEncounter_id() {
		return encounter_id;
	}
	public void setEncounter_id(String encounter_id) {
		this.encounter_id = encounter_id;
	}
	public String getDelivaryLocation() {
		return delivaryLocation;
	}
	public void setDelivaryLocation(String delivaryLocation) {
		this.delivaryLocation = delivaryLocation;
	}
	public double getOrderPrise() {
		return orderPrise;
	}
	public void setOrderPrise(double orderPrise) {
		this.orderPrise = orderPrise;
	}
	public String getPayMentType() {
		return payMentType;
	}
	public void setPayMentType(String payMentType) {
		this.payMentType = payMentType;
	}
	public List<Item> getItems() {
		return items;
	}
	public void setItems(List<Item> items) {
		this.items = items;
	}
	
}
