package com.reecegroup.addressbooktracker;

import com.reecegroup.addressbooktracker.exception.NoAddressBookFoundException;

import java.util.*;

/**
 * Class used for implementation of various Address Book related use cases.
 * e.g.
 * 1. Printing name of all the address books
 * 2. Adding a Address Book which contains name and associated customer contacts.
 * 3. Removing address book based on address book name.
 * 4. Printing all the customer contact information for a address book based on its name.
 *
 */
public class AddressBookManager implements AddressBookOperations{

	private static List<AddressBook> addressBooks;

	public static List<AddressBook> getAddressBooks() {
		return addressBooks;
	}

	public static void resetAddressBooks() {
		addressBooks  = null;
	}

	/**
	 * Method used for adding a Address Book which contains name and associated customer contacts.
	 * @param name
	 * @param customerContacts
	 */
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

	/**
	 * Method used for removing address book based on address book name.
	 * Throws NoAddressBookFoundException when address book name is not found which user wishes to remove.
	 *
	 * @param addressBookName
	 * @throws NoAddressBookFoundException
	 */
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

	/**
	 * Method used for printing all the customer contact information for a address book based on its name.
	 *
	 * @param addressBookName
	 */
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
