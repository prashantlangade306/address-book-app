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
        contactEntriesOperations = new BranchManager();
        addressBookOperations = new BranchManager();
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @After
    public void tearDown(){
        BranchManager.resetAddressBooks();
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
        assertEquals(1, BranchManager.getAddressBooks().size());
        assertEquals(customerContacts, BranchManager.getCustomerContactsForAddressBookName(addressBookName));

        List<CustomerContact> customerContactList = BranchManager.getCustomerContactsForAddressBookName(addressBookName);
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

        assertTrue(BranchManager.doesAddressBookExists(addressBookName));
        assertEquals(0, AddressBook.getCustomerContacts().size());
        assertEquals(1, BranchManager.getAddressBooks().size());
        assertEquals(0, BranchManager.getCustomerContactsForAddressBookName(addressBookName).size());
    }

    @Test
    @DisplayName("Testing printing of customer contacts - Integration Test")
    public void testPrintCustomerContacts(){
        String addressBookName = "AddressBook1";
        List<CustomerContact> customerContacts = new ArrayList<>();
        customerContacts.add(new CustomerContact("Mark", 1234567));
        customerContacts.add(new CustomerContact("Travis", 7654321));

        addressBookOperations.addAddressBook(addressBookName, customerContacts);

        contactEntriesOperations.printCustomerContacts(customerContacts);

        String expected ="Customer Name: Mark\n" +
                "Customer Phone Number: 1234567\n" +
                "Customer Name: Travis\n" +
                "Customer Phone Number: 7654321";

        assertEquals(expected,outputStreamCaptor.toString().trim());
    }

    @Test
    @DisplayName("Testing printing of all customer contacts for a address book")
    public void testPrintAllContacts(){
        String addressBookName = "AddressBook1";
        List<CustomerContact> customerContacts = new ArrayList<>();
        customerContacts.add(new CustomerContact("Mark", 1234567));
        customerContacts.add(new CustomerContact("Travis", 7654321));
        addressBookOperations.addAddressBook("AddressBook1", customerContacts);

        addressBookOperations.printAllContacts(addressBookName);

        String expected = "Customer Contact Name: Mark\n" +
                "Customer Contact Phone Number: 1234567\n" +
                "Customer Contact Name: Travis\n" +
                "Customer Contact Phone Number: 7654321";

        assertEquals(expected,outputStreamCaptor.toString().trim());
    }


}
