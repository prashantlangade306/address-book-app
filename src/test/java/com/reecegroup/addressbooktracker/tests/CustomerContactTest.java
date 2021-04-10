package com.reecegroup.addressbooktracker.tests;

import com.reecegroup.addressbooktracker.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CustomerContactTest {

    private ContactEntriesOperations contactEntriesOperations = null;
    private AddressBookOperations addressBookOperations = null;
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @Before
    public void setUp() {
        contactEntriesOperations = new CustomerContactManager();
        addressBookOperations = new AddressBookManager();
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @After
    public void tearDown(){
        AddressBookManager.resetAddressBooks();
    }
    @Test
    @DisplayName("Testing addition of new contact entries")
    public void testAddNewContactEntries() {
        String addressBookName = "AddressBook1";
        List<CustomerContact> customerContacts = new ArrayList<>();
        customerContacts.add(new CustomerContact("Mark", 1234567));
        customerContacts.add(new CustomerContact("Travis", 7654321));

        contactEntriesOperations.addNewContactEntries(addressBookName,customerContacts);

        assertEquals(2, customerContacts.size());
        assertEquals(1, CustomerContactManager.getAddressBooks().size());
        assertEquals(customerContacts, CustomerContactManager.getCustomerContactsForAddressBookName(addressBookName));

        List<CustomerContact> customerContactList = CustomerContactManager.getCustomerContactsForAddressBookName(addressBookName);
        assertEquals("Mark", customerContactList.get(0).getCustomerName());
        assertEquals("Travis", customerContactList.get(1).getCustomerName());

        assertEquals(1234567,customerContactList.get(0).getCustomerPhoneNumber());
        assertEquals(7654321,customerContactList.get(1).getCustomerPhoneNumber());
    }

    @Test
    @DisplayName("Testing removal of contact entries - Integration test")
    public void testRemoveContactEntries() {
        String addressBookName = "AddressBook1";
        List<CustomerContact> customerContacts = new ArrayList<>();
        customerContacts.add(new CustomerContact("Mark", 1234567));
        customerContacts.add(new CustomerContact("Travis", 7654321));

        addressBookOperations.addAddressBook(addressBookName, customerContacts);
        contactEntriesOperations.removeContactEntries(addressBookName);

        assertTrue(CustomerContactManager.doesAddressBookExists(addressBookName));
        assertEquals(0, AddressBook.getCustomerContacts().size());
        assertEquals(1, AddressBookManager.getAddressBooks().size());
        assertEquals(0, CustomerContactManager.getCustomerContactsForAddressBookName(addressBookName).size());
    }

    @Test
    @DisplayName("Testing printing of all customer contacts for a address book")
    public void testprintAllContactsInAddressBook(){
        String addressBookName = "AddressBook1";
        List<CustomerContact> customerContacts = new ArrayList<>();
        customerContacts.add(new CustomerContact("Mark", 1234567));
        customerContacts.add(new CustomerContact("Travis", 7654321));
        addressBookOperations.addAddressBook("AddressBook1", customerContacts);

        addressBookOperations.printAllContactsInAddressBook(addressBookName);

        String expected = "Customer Contact Name: Mark\n" +
                "Customer Contact Phone Number: 1234567\n" +
                "Customer Contact Name: Travis\n" +
                "Customer Contact Phone Number: 7654321";

        assertEquals(expected,outputStreamCaptor.toString().trim());
    }

    @Test
    @DisplayName("Testing printing of unique customer contacts across multiple address books")
    public void testPrintUniqueContacts(){
        String addressBookName = "AddressBook1";
        List<CustomerContact> customerContacts = new ArrayList<>();
        customerContacts.add(new CustomerContact("Mark", 1234567));
        customerContacts.add(new CustomerContact("Travis", 7654321));

        contactEntriesOperations.addNewContactEntries(addressBookName,customerContacts);

        String addressBookName1 = "AddressBook1";
        List<CustomerContact> customerContacts1 = new ArrayList<>();
        customerContacts1.add(new CustomerContact("Mark", 1234567));
        customerContacts1.add(new CustomerContact("Nathan", 7654321));
        customerContacts.add(new CustomerContact("Travis", 765421));

        contactEntriesOperations.addNewContactEntries(addressBookName1,customerContacts1);

        contactEntriesOperations.printUniqueContacts();
        String expected = "Customer Name: Nathan\n" +
                "Customer Phone Number: 7654321\n" +
                "Customer Name: Travis\n" +
                "Customer Phone Number: 7654321\n" +
                "Customer Name: Mark\n" +
                "Customer Phone Number: 1234567";

        assertEquals(expected,outputStreamCaptor.toString().trim());

    }

}
