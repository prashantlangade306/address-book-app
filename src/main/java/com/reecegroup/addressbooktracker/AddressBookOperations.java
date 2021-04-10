package com.reecegroup.addressbooktracker;

import java.util.List;

public interface AddressBookOperations {

	//print all contacts in an address book
  	public void printAllContacts(String addressBookName);
  	
  //maintain multiple address book - view/print, add, remove
  	public void printAllAddressBooks();
  	public void addAddressBook(String name, List<CustomerContact> customerContacts);
  	public void removeAddressBook(AddressBook addressBook);
  	
  //print unique set of all contacts across multiple address books
    public void printUniqueContacts();
}
