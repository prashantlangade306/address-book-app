package com.reecegroup.addressbooktracker.tests;

import com.reecegroup.addressbooktracker.AddressBook;
import com.reecegroup.addressbooktracker.AddressBookManager;
import com.reecegroup.addressbooktracker.AddressBookOperations;
import com.reecegroup.addressbooktracker.CustomerContact;
import com.reecegroup.addressbooktracker.exception.NoAddressBookFoundException;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
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

public class AddressBookTest {

    private AddressBookOperations addressBookOperations = null;
    @Before
    public void setUp(){
        addressBookOperations = new AddressBookManager();

    }

    //add address books
    @Test
    @DisplayName("Testing addition of Address Book (Part of address book maintainance)")
    public void testAddAddressBook(){
        List<CustomerContact> customerContactList = new ArrayList<>();
        customerContactList.add(new CustomerContact("Henry", 123456));
        customerContactList.add(new CustomerContact("Ben", 6342651));

        addressBookOperations.addAddressBook("AddressBook1", customerContactList);

        List<CustomerContact> customerContactList1 = new ArrayList<>();
        customerContactList1.add(new CustomerContact("Simon", 666666));
        customerContactList1.add(new CustomerContact("Trait", 222222));

        addressBookOperations.addAddressBook("AddressBook2", customerContactList1);

        List<AddressBook> addressBookList = AddressBookManager.getAddressBooks();

        assertEquals(2,addressBookList.size());
        assertEquals("AddressBook1", addressBookList.get(0).getAddressBookName());
        assertEquals("AddressBook2", addressBookList.get(1).getAddressBookName());
        assertEquals(2, AddressBook.getCustomerContacts().size());
        assertEquals("Simon",AddressBook.getCustomerContacts().get(0).getCustomerName());
        assertEquals("Trait",AddressBook.getCustomerContacts().get(1).getCustomerName());
    }

    //remove address books
    @Test
    @DisplayName("Testing removal of Address Book (Part of address book maintainance) ")
    public void testRemoveAddressBook() throws NoAddressBookFoundException {
        //AddressBook
        List<CustomerContact> customerContactList = new ArrayList<>();
        customerContactList.add(new CustomerContact("Henry", 123456));
        customerContactList.add(new CustomerContact("Ben", 6342651));

        addressBookOperations.addAddressBook("AddressBook1", customerContactList);
        addressBookOperations.removeAddressBook("AddressBook1");

        List<AddressBook> addressBookList = AddressBookManager.getAddressBooks();
        assertEquals(0, addressBookList.size());
        assertEquals(0, AddressBook.getCustomerContacts().size());

    }

}
