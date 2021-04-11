package com.reecegroup.addressbooktracker;

import com.reecegroup.addressbooktracker.entity.CustomerContact;

import java.util.List;

/**
 * Interface for Contract entries related operations.
 */
public interface CustomerContactEntriesOperations {
    void addNewCustomerContactEntries(String addressBookName, List<CustomerContact> customerContacts);
    void removeCustomerContactEntries(String addressBookName);
    void printUniqueCustomerContacts();
}

