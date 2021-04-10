package com.reecegroup.addressbooktracker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class BranchManager implements ContactEntriesOperations, AddressBookOperations {

	static List<AddressBook> addressBooks;
	public static List<AddressBook> getAddressBooks() {
		return addressBooks;
	}

	public static void resetAddressBooks() {
		addressBooks  = null;
	}

	public static List<CustomerContact> getCustomerContactsForAddressBookName(String addBookName) {
		if (addressBooks == null) {
			return null;
		}

		for (AddressBook addressBook : addressBooks) {
			if (addressBook.getAddressBookName().equals(addBookName)) {
				return addressBook.getCustomerContacts();
			}
		}
		return null;
	}

	public static boolean doesAddressBookExists(String addressBookName){
		if(addressBooks == null){
			return false;
		}
		for (AddressBook addressBook : addressBooks) {
			if(addressBook.getAddressBookName().equals(addressBookName)){
				return true;
			}
		}
		return false;
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
	public void removeAddressBook(AddressBook addressBook) {
		if(addressBooks != null) {
			addressBooks.remove(addressBook);
		} else {

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

	@Override
	public void addNewContactEntries(String addressBookName,List<CustomerContact> customerContacts) {
		boolean isContactEntriesAdded = false;
		if(addressBooks != null) {
			for(AddressBook addressBook : addressBooks) {
				if(addressBook.getAddressBookName().equals(addressBookName)) {
					if(addressBook.getCustomerContacts() != null) {
						addressBook.getCustomerContacts().addAll(customerContacts);
						isContactEntriesAdded = true;
					}
				}
			}
		}
		
		if(!isContactEntriesAdded) {
			//Add the address book first along with its customer contacts
			this.addAddressBook(addressBookName, customerContacts);
		}
		
		
	}

	@Override
	public void removeContactEntries(String addressBookName) {
		
		if(addressBooks != null) {
			for(AddressBook addressBook : addressBooks) {
				if(addressBook.getAddressBookName().equals(addressBookName)) {
					List<CustomerContact> contactList = addressBook.getCustomerContacts();
					if(contactList != null) {
						addressBook.removeCustomerContacts();
					}
				}
			}	
		}
	}

	@Override
	public void printCustomerContacts(List<CustomerContact> customerContacts) {
		for(CustomerContact customerContact : customerContacts) {
			System.out.println("Customer Name: "+customerContact.getCustomerName());
			System.out.println("Customer Phone Number: "+customerContact.getCustomerPhoneNumber());
		}
		
	}

	@Override
	public void printAllContacts(String addressBookName) {
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
