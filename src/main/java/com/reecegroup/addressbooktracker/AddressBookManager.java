package com.reecegroup.addressbooktracker;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AddressBookManager {
	
	private AddressBookOperations addressBookOperations;
	private static ContactEntriesOperations contactEntriesOperations = new BranchManager();
	
	 public static void performOperations(String option) {
		 
		 Scanner reader = new Scanner(System.in);
	     String input = null;
	     String[] multilineInput = null;
	     
		 switch (option) {
	  
			  case "1": {
				  //Take list of customer contacts from user input in the form of values separated by ,
				  System.out.println("Please enter ");
				  //contactEntriesOperations.printCustomerContacts(customerContactsList);
				  break; 
			  }
			  
			  case "2": { 
				  System.out.println("Enter the address book name.");
				  input = reader.nextLine();
				  System.out.println("Enter the number of contact entries to be added for a address book.");
				  multilineInput = new String [reader.nextInt()];   
				  System.out.println("Please enter user input in the form of Customer Name, Customer Phone Number. e.g. Prashant, 12345678");
				  reader.nextLine(); 
				  
				  List<CustomerContact> customerContacts = new ArrayList<CustomerContact>();
				  for (int i = 0; i < multilineInput.length; i++)   
				  {  
					  multilineInput[i] = reader.nextLine();
					  String[] splitVal = multilineInput[i].split(",");
					  customerContacts.add(new CustomerContact(splitVal[0], Long.valueOf(splitVal[1])));
					   
				  }
				  
				  contactEntriesOperations.addNewContactEntries(input, customerContacts);
				  break; 
				}
			  
		 
			 case "3": {
				
				 //For testing purpose - start
				 System.out.println("Enter the address book name.");
				  input = reader.nextLine();
				  System.out.println("Enter the number of contact entries to be added for a address book.");
				  multilineInput = new String [reader.nextInt()];   
				  System.out.println("Please enter user input in the form of Customer Name, Customer Phone Number. e.g. Prashant, 12345678");
				  reader.nextLine(); 
				  
				  List<CustomerContact> customerContacts = new ArrayList<CustomerContact>();
				  for (int i = 0; i < multilineInput.length; i++)   
				  {  
					  multilineInput[i] = reader.nextLine();
					  String[] splitVal = multilineInput[i].split(",");
					  customerContacts.add(new CustomerContact(splitVal[0], Long.valueOf(splitVal[1])));
					   
				  }
				  
				  contactEntriesOperations.addNewContactEntries(input, customerContacts); 
				//For testing purpose - end
				 
				 contactEntriesOperations.removeContactEntries(input);
				 break;
			 }
			 
			 case "4": {
				 
				 AddressBook addressBook1 = new AddressBook.Builder("Address1")
						 					.withCustomerContacts(new ArrayList<CustomerContact>())
						 					.build();
				 
				 //addressBookOperations.printAllContacts(addressBook);
				 break;
			 }
			 
			 case "5": {
				 //addressBookOperations.printAllAddressBooks();
				 break;
			 }
			 
			 case "6": {
				 //addressBookOperations.addAddressBook(name, customerContacts);
				 break;
			 }
			 
			 case "7": {
				 //addressBookOperations.removeAddressBook(addressBook);
				 break;
			 }	 
		 }
	 }
}
