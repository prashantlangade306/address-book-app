package com.reecegroup.addressbooktracker.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to hold Address Book information such as name and associated customer contacts.
 */
public class AddressBook {
	
	private String addressBookName;
	private static List<CustomerContact> customerContacts;

	public String getAddressBookName() {
		return addressBookName;
	}
	public void removeCustomerContacts() {
		this.customerContacts = new ArrayList<>();
	}

	public static List<CustomerContact> getCustomerContacts() {
		return customerContacts;
	}

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
}
