package com.reecegroup.addressbooktracker;

import com.reecegroup.addressbooktracker.exception.NoAddressBookFoundException;

import java.util.*;

public class AddressBookManager implements AddressBookOperations{
	
	private AddressBookOperations addressBookOperations;
	private static ContactEntriesOperations contactEntriesOperations = new CustomerContactManager();

	public static void setAddressBooks(List<AddressBook> addressBooks) {
		addressBooks.addAll(addressBooks);
	}

	static List<AddressBook> addressBooks;
	public static List<AddressBook> getAddressBooks() {
		return addressBooks;
	}

	public static void resetAddressBooks() {
		addressBooks  = null;
	}



	@Override
	public void printAllAddressBooks() {
		for(AddressBook addressBook : addressBooks) {
			System.out.println("Address Book Name: "+addressBook.getAddressBookName());
		}

	}

	@Override
	public void addAddressBook(String name, List<CustomerContact> customerContacts) {
		AddressBook addressBook = new AddressBook.Builder(name)
				.withCustomerContacts(customerContacts)
				.build();
		if(addressBooks != null) {
			addressBooks.add(addressBook);
		} else {
			addressBooks = new ArrayList<AddressBook>();
			addressBooks.add(addressBook);
		}
	}

	@Override
	public void removeAddressBook(String addressBookName) {
		if(addressBooks != null) {
			for (Iterator<AddressBook> it = addressBooks.iterator(); it.hasNext(); ) {
				AddressBook addressBook = it.next();
				if (addressBook.getAddressBookName().equals(addressBookName)) {
					addressBook.removeCustomerContacts();
					it.remove();
				}
			}
		}
	}

	@Override
	public void printUniqueContacts() {
		Set<CustomerContact> uniqueCustomerContacts = new HashSet<CustomerContact>();
		for(AddressBook addressBook : addressBooks) {
			List<CustomerContact> customerContactList = addressBook.getCustomerContacts();
			if(customerContactList != null) {
				for(CustomerContact customerContact : customerContactList) {
					uniqueCustomerContacts.add(customerContact);
				}
			}
		}

		printCustomerContacts(new ArrayList<CustomerContact>(uniqueCustomerContacts));

	}



	public void printCustomerContacts(List<CustomerContact> customerContacts) {
		for(CustomerContact customerContact : customerContacts) {
			System.out.println("Customer Name: "+customerContact.getCustomerName());
			System.out.println("Customer Phone Number: "+customerContact.getCustomerPhoneNumber());
		}

	}

	@Override
	public void printAllContactsInAddressBook(String addressBookName) {
		if(addressBooks != null) {
			for(AddressBook addressBook : addressBooks) {
				if(addressBook.getAddressBookName().equals(addressBookName)){
					List<CustomerContact> customerContactList = addressBook.getCustomerContacts();

					if(customerContactList != null) {
						for(CustomerContact customerContact : customerContactList) {
							System.out.println("Customer Contact Name: "+customerContact.getCustomerName());
							System.out.println("Customer Contact Phone Number: "+customerContact.getCustomerPhoneNumber());
						}
					}
				}
			}
		}
	}
}
