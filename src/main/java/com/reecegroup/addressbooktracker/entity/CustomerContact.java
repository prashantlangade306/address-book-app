package com.reecegroup.addressbooktracker.entity;

/**
 * Class to holder Customer Contact information associated with a Address Book such as name and phone number.
 */
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

	@Override
	public boolean equals(Object other) {
		if (!(other instanceof CustomerContact)) {
			return false;
		}
		return customerName.equals(((CustomerContact)other).customerName);
	}

	@Override
	public int hashCode() {
		return customerName.hashCode();
	}
}
