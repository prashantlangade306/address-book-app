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
	public void removeAddressBook(String addressBookName) throws NoAddressBookFoundException {
		boolean isAddressBookRemoved = false;
		if(addressBooks != null) {
			for (Iterator<AddressBook> it = addressBooks.iterator(); it.hasNext(); ) {
				AddressBook addressBook = it.next();
				if (addressBook.getAddressBookName().equals(addressBookName)) {
					addressBook.removeCustomerContacts();
					it.remove();
					isAddressBookRemoved = true;
				}
			}
		}

		if(!isAddressBookRemoved){
			throw new NoAddressBookFoundException("No Address Book found with name: "+addressBookName+" to remove.");
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
