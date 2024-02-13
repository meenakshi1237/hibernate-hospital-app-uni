package com.hospital_app.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.CreationTimestamp;
@Entity
public class MedOrder {
	@Id
	@GeneratedValue(generator = "medorder_id_gen")
	@SequenceGenerator(name = "medorder_id_gen",initialValue = 601,allocationSize = 1,sequenceName ="medorder_id_seq" )
	private int medOrderId;
	private int numberOfItems;
	private String delivaryLocation;
	private double cost;
	@CreationTimestamp
	private LocalDateTime orderedTime;
	//encounters
	@ManyToOne
	@JoinColumn
	private Encounter encounter;
	@ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
	@JoinTable(joinColumns = @JoinColumn,inverseJoinColumns = @JoinColumn)
	private List<Item> items;
	public int getMedOrderId() {
		return medOrderId;
	}
	public void setMedOrderId(int medOrderId) {
		this.medOrderId = medOrderId;
	}
	public int getnumberOfItems() {
		return numberOfItems;
	}
	public void setnumberOfItems(int numberOfItems) {
		this.numberOfItems = numberOfItems;
	}
	public String getDelivaryLocation() {
		return delivaryLocation;
	}
	public void setDelivaryLocation(String delivaryLocation) {
		this.delivaryLocation = delivaryLocation;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	public LocalDateTime getOrderedTime() {
		return orderedTime;
	}
	public void setOrderedTime(LocalDateTime orderedTime) {
		this.orderedTime = orderedTime;
	}
	public Encounter getEncounter() {
		return encounter;
	}
	public void setEncounter(Encounter encounter) {
		this.encounter = encounter;
	}
	public List<Item> getItems() {
		return items;
	}
	public void setItems(List<Item> items) {
		this.items = items;
	}
	
	
	
}
