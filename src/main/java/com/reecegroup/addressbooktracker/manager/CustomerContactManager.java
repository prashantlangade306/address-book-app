package com.reecegroup.addressbooktracker.manager;

import com.reecegroup.addressbooktracker.CustomerContactEntriesOperations;
import com.reecegroup.addressbooktracker.entity.AddressBook;
import com.reecegroup.addressbooktracker.entity.CustomerContact;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Class used for implementation of various Customer Contact related use cases.
 * e.g.
 *
 *
 */
public class CustomerContactManager implements CustomerContactEntriesOperations {

    static List<AddressBook> addressBooks = AddressBookManager.getAddressBooks();

    public static List<AddressBook> getAddressBooks() {
        return addressBooks;
    }

    /**
     * Helper method used in unit/integration tests for getting associated customer contacts for a address book name.
     *
     * @param addBookName
     * @return
     */
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

    /**
     * Helper method used in unit/integration tests for checking if a address book exists based on its name.
     *
     * @param addressBookName
     * @return
     */
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

    /**
     * Method used for adding a new Customer Contact which contains name and associated customer contacts.
     *
     * @param addressBookName
     * @param customerContacts
     */
    @Override
    public void addNewCustomerContactEntries(String addressBookName,List<CustomerContact> customerContacts) {
        boolean isContactEntriesAdded = false;
        if(addressBooks != null) {
            for(AddressBook addressBook : addressBooks) {
                if(addressBook.getAddressBookName().equals(addressBookName)) {
                    if(AddressBook.getCustomerContacts() != null) {
                        AddressBook.getCustomerContacts().addAll(customerContacts);
                        isContactEntriesAdded = true;
                    }
                }
            }
        }

        if(!isContactEntriesAdded) {
            if(addressBooks != null){
                addressBooks.add(new AddressBook.Builder(addressBookName)
                        .withCustomerContacts(customerContacts)
                        .build());
            } else {
                AddressBook addressBook = new AddressBook.Builder(addressBookName)
                        .withCustomerContacts(customerContacts)
                        .build();
                addressBooks = new ArrayList<>();
                addressBooks.add(addressBook);
            }
        }
    }

    /**
     * Method used for removing a pre-existing Customer Contacts for a address book based on its name.
     *
     * @param addressBookName
     */
    @Override
    public void removeCustomerContactEntries(String addressBookName) {
        if(addressBooks != null) {
            for(AddressBook addressBook : addressBooks) {
                if(addressBook.getAddressBookName().equals(addressBookName)) {
                    List<CustomerContact> contactList = AddressBook.getCustomerContacts();
                    if(contactList != null) {
                        addressBook.removeCustomerContacts();
                    }
                }
            }
        }
    }

    /**
     * Method used for printing unique Customer Contact names across multiple address books.
     *
     */
    @Override
    public void printUniqueCustomerContacts() {
        Set<CustomerContact> uniqueCustomerContacts = new HashSet<CustomerContact>();
        List<CustomerContact> customerContactList = AddressBook.getCustomerContacts();
        if(customerContactList != null) {
            for(CustomerContact customerContact : customerContactList) {
                uniqueCustomerContacts.add(customerContact);
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
}
