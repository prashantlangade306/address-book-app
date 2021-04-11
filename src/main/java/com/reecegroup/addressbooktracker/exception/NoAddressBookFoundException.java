package com.reecegroup.addressbooktracker.exception;

/**
 * Exception handler class to handle no address book found scenario.
 * Thrown while user trying to remove non-existent address book
 *
 */
public class NoAddressBookFoundException extends Exception {

    public NoAddressBookFoundException(String message) {
        super(message);
    }
}
