package com.qa.ims.persistence.domain;

import java.util.ArrayList;
import java.util.List;

public class Order {

	private Long id;
	private int orderId;
	private int customerId;
	private int itemId;
	public List<Item> items = new ArrayList<Item>();
	public Customer customers ;
	public List<String> itemIDs = new ArrayList<>();


	public Order(Long id,int orderId, int customerId,int itemId){
		this.orderId = orderId;
		this.customerId = customerId;
		this.itemId = itemId;
		this.id = id;
	}


	public Order(int customerId,List<String> items){
		this.customerId = customerId;
		this.itemIDs = items;
	}

	public List<String> getItemIDs() {
		return itemIDs;
	}

	public void setItemIDs(List<String> itemIDs) {
		this.itemIDs = itemIDs;
	}

	public int getOrderId() {
		return orderId;
	}

	@Override
	public String toString() {
		return "id=" + id + " , orderId=" + orderId + ", customerId=" + customerId + ", itemId=" + itemId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	
	public Customer getCustomers() {
		return customers;
	}
	public void setCustomers(Customer customers) {
		this.customers = customers;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	
	public void addItem(Item item) {
		items.add(item);
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
