# address-book-app - Coding assignment for Reece Group

This is an application that covers all functionalities as part of acceptance criteria for a Address Book application used by Reece Group branch manager.

[Table Of Contents]()
* [User Story and Acceptance Criteria](#user-story-and-acceptance-criteria)
* [Key Information](#key-information)
* [Technology Stack](#technology-stack)
* [Code Structure](#code-structure)
* [Significance](#significance)
* [TestCases Covered] (#testcases-covered)
* [License and Copyright](#license-and-copyright)

## User Story and Acceptance Criteria

User Story:

As a Reece Branch Manager
I would like an address book application
So that I can keep track of my customer contacts

Acceptance Criteria:

o Address book will hold name and phone numbers of contact entries
o Users should be able to add new contact entries
o Users should be able to remove existing contact entries
o Users should be able to print all contacts in an address book
o Users should be able to maintain multiple address books
o Users should be able to print a unique set of all contacts across multiple address books

## Key Information

a. This README.md file has all the necessary information to understand the approach taken to develop the user story although the code is well documented.
b. The application does not take any user input
c. Application functionalities can be tested using the junit testcases (unit/integration)
d. Application covers only the acceptance criteria defined for the user story.

## Technology Stack

   The application is a maven based java application with junit dependency developed using Intellij IDE.
   
## Code Structure
   
com/reecegroup/addressbooktracker/entity/AddressBook
com/reecegroup/addressbooktracker/entity/CustomerContact
com/reecegroup/addressbooktracker/exception/NoAddressBookFoundException
com/reecegroup/addressbooktracker/manager/AddressBookManager
com/reecegroup/addressbooktracker/manager/CustomerContactManager
com/reecegroup/addressbooktracker/AddressBookOperations
com/reecegroup/addressbooktracker/CustomerContactEntriesOperations
com/reecegroup/addressbooktracker/tests/AddressBookTest
com/reecegroup/addressbooktracker/tests/CustomerContactTest

## Significance

com/reecegroup/addressbooktracker/entity/AddressBook : Class to hold Address Book information such as name and associated customer contacts.
com/reecegroup/addressbooktracker/entity/CustomerContact : Class to holder Customer Contact information associated with a Address Book such as name and phone number.
com/reecegroup/addressbooktracker/exception/NoAddressBookFoundException : Exception handler class to handle no address book found scenario and is thrown while user trying to remove non-existent address book
com/reecegroup/addressbooktracker/manager/AddressBookManager : Class used for implementation of various Address Book related use cases.
com/reecegroup/addressbooktracker/manager/CustomerContactManager : Class used for implementation of various Customer Contact related use cases.
com/reecegroup/addressbooktracker/AddressBookOperations : Interface for Address Book related operations.
com/reecegroup/addressbooktracker/CustomerContactEntriesOperations : Interface for Contract entries related operations.
com/reecegroup/addressbooktracker/tests/AddressBookTest : Class used for testing Address Book related acceptance criteria's.
com/reecegroup/addressbooktracker/tests/CustomerContactTest : Class used for testing Customer Contact related acceptance criteria's.

## TestCases Covered

com/reecegroup/addressbooktracker/tests/AddressBookTest :

testAddAddressBook() : Testing addition of Address Book (Part of address book maintainance)
testRemoveAddressBook() : esting removal of Address Book (Part of address book maintainance)

com/reecegroup/addressbooktracker/tests/CustomerContactTest  :

testAddNewCustomerContactEntries() : Testing addition of new contact entries
testRemoveCustomerContactEntries() : Testing removal of contact entries - Integration test
testprintAllCustomerContactsInAddressBook() : Testing printing of all customer contacts for a address book
testPrintUniqueCustomerContacts() : Testing printing of unique customer contacts across multiple address books


## License & Copyright
Prashant Langade
