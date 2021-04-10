package com.reecegroup.addressbooktracker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CustomerContactManager implements ContactEntriesOperations {

    static List<AddressBook> addressBooks = AddressBookManager.getAddressBooks();

    public static List<AddressBook> getAddressBooks() {
        return addressBooks;
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
    public void addNewContactEntries(String addressBookName,List<CustomerContact> customerContacts) {
        boolean isContactEntriesAdded = false;
        if(addressBooks != null) {
            for(AddressBook addressBook : addressBooks) {
                if(addressBook.getAddressBookName().equals(addressBookName)) {
                    if(addressBook.getCustomerContacts() != null) {
                        addressBook.getCustomerContacts().addAll(customerContacts);
                        //AddressBookManager.setAddressBooks(addressBooks);
                        isContactEntriesAdded = true;
                    }
                }
            }
        }

        if(!isContactEntriesAdded) {

            //Add the address book first along with its customer contacts
            //this.addAddressBook(addressBookName, customerContacts);
            if(addressBooks != null){
                addressBooks.add(new AddressBook.Builder(addressBookName)
                        .withCustomerContacts(customerContacts)
                        .build());
            } else{
                AddressBook addressBook = new AddressBook.Builder(addressBookName)
                        .withCustomerContacts(customerContacts)
                        .build();
                addressBooks = new ArrayList<>();
                addressBooks.add(addressBook);
            }

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


}
