package uk.ac.sheffield.com1003.library.exceptions;

/**
 * An exception class specific for our application.
 * **/
public class ItemAlreadyReturnedException extends Exception {

    /**
     * The exception constructor that when thrown returns the message that a catalogue item has been already returned.
     * **/
    public ItemAlreadyReturnedException() {
        super("Item Was Already Returned");
    }
}