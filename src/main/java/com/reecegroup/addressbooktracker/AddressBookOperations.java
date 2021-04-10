package com.reecegroup.addressbooktracker;

import com.reecegroup.addressbooktracker.exception.NoAddressBookFoundException;

import java.util.List;

public interface AddressBookOperations {

	//print all contacts in an address book
  	public void printAllContactsInAddressBook(String addressBookName);
  	
  //maintain multiple address book - view/print, add, remove
  	public void printAllAddressBooks();
  	public void addAddressBook(String name, List<CustomerContact> customerContacts);
  	public void removeAddressBook(String addressBookName) throws NoAddressBookFoundException;
  	


	//public void printCustomerContacts(List<CustomerContact> customerContacts);
}
