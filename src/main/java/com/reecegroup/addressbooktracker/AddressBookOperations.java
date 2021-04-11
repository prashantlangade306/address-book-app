package com.reecegroup.addressbooktracker;

import com.reecegroup.addressbooktracker.entity.CustomerContact;
import com.reecegroup.addressbooktracker.exception.NoAddressBookFoundException;

import java.util.List;

/**
 * Interface for Address Book related operations.
 */
public interface AddressBookOperations {

	void printAllContactsInAddressBook(String addressBookName);
  	//maintain multiple address book - add, remove
  	void addAddressBook(String name, List<CustomerContact> customerContacts);
  	void removeAddressBook(String addressBookName) throws NoAddressBookFoundException;

}
