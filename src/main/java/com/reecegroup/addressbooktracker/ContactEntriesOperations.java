package com.reecegroup.addressbooktracker;

import java.util.List;

public interface ContactEntriesOperations {
	
	//add new contact entries
  	public void addNewContactEntries(String addressBookName, List<CustomerContact> customerContacts);
  	//remove existing contact entry
  	public void removeContactEntries(String addressBookName);
  	public void printCustomerContacts(List<CustomerContact> customerContacts);
}
