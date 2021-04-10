package com.reecegroup.addressbooktracker;

import java.util.ArrayList;
import java.util.List;

public class AddressBook {
	
	private String addressBookName;
	private static List<CustomerContact> customerContacts;
	public static class Builder {
		
		private String addressBookName;
		private List<CustomerContact> customerContacts;
		
		public Builder(String addressBookName) {
			this.addressBookName = addressBookName;
		}
		
		public Builder withCustomerContacts(List<CustomerContact> customerContacts) {
			this.customerContacts = customerContacts;
			return this;
		}
		
		public AddressBook build() {
			AddressBook addressBook = new AddressBook();
			addressBook.addressBookName = this.addressBookName;
			addressBook.customerContacts = this.customerContacts;
			return addressBook;
		}
	}
	

	public String getAddressBookName() {
		return addressBookName;
	}

	public void setAddressBookName(String addressBookName) {
		this.addressBookName = addressBookName;
	}

	public static List<CustomerContact> getCustomerContacts() {
		return customerContacts;
	}


	public void setCustomerContacts(List<CustomerContact> customerContacts) {
		this.customerContacts = customerContacts;
	}

	public void removeCustomerContacts() {
		this.customerContacts = new ArrayList<>();
	}

}
