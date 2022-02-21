package com.qa.ims.persistence.domain;

public class Item {

	private Long itemId;
	private String name;
	private int value;

	public Item(Long itemId, String name, int value) {
		this.itemId = itemId;
		this.name = name;
		this.value = value;
	}

	public Item(String name, int value) {
		this.name = name;
		this.value = value;
	}
	@Override
	public String toString() {
		return "id=" + itemId + ", name=" + name + ", value=" + value;
	}

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}


}
