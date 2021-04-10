package com.reecegroup.addressbooktracker;

public class CustomerContact {
	
	private String customerName;
	private long customerPhoneNumber;
	
	public CustomerContact(String customerName,long customerPhoneNumber) {
		this.customerName = customerName;
		this.customerPhoneNumber = customerPhoneNumber;
	}

	public String getCustomerName() {
		return customerName;
	}

	public long getCustomerPhoneNumber() {
		return customerPhoneNumber;
	}

}