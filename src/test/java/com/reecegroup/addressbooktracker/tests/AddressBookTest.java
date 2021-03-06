package com.reecegroup.addressbooktracker.tests;

import com.reecegroup.addressbooktracker.entity.AddressBook;
import com.reecegroup.addressbooktracker.manager.AddressBookManager;
import com.reecegroup.addressbooktracker.AddressBookOperations;
import com.reecegroup.addressbooktracker.entity.CustomerContact;
import com.reecegroup.addressbooktracker.exception.NoAddressBookFoundException;
import org.junit.*;

import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Class used for testing Address Book related acceptance criteria's.
 *
 */
public class AddressBookTest {

    private AddressBookOperations addressBookOperations = null;

    /**
     * Method to pre-initialise required objects.
     */
    @Before
    public void setUp(){
        addressBookOperations = new AddressBookManager();
    }

    /**
     * Method to clear remove objects from the memory.
     */
    @After
    public void tearDown(){
        AddressBookManager.resetAddressBooks();
    }

    /**
     * Testcase to test addition of address book
     */
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

    /**
     * Testcase to test removal of address book and exception thrown by the method.
     * @throws NoAddressBookFoundException
     */
    @Test(expected = NoAddressBookFoundException.class)
    @DisplayName("Testing removal of Address Book (Part of address book maintainance) ")
    public void testRemoveAddressBook() throws NoAddressBookFoundException {
        List<CustomerContact> customerContactList = new ArrayList<>();
        customerContactList.add(new CustomerContact("Henry", 123456));
        customerContactList.add(new CustomerContact("Ben", 6342651));

        addressBookOperations.addAddressBook("AddressBook1", customerContactList);
        addressBookOperations.removeAddressBook("AddressBook1");

        List<AddressBook> addressBookList = AddressBookManager.getAddressBooks();
        assertEquals(0, addressBookList.size());
        assertEquals(0, AddressBook.getCustomerContacts().size());

        //Additional method call to test NoAddressBookFoundException
        addressBookOperations.removeAddressBook("AddressBook1");
    }

}
