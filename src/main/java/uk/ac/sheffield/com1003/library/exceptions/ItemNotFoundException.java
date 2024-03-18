package uk.ac.sheffield.com1003.library.exceptions;

/**
 * An exception class specifically made for this application
 * **/
public class ItemNotFoundException extends Exception {
    /**
     * The exception constructor that when thrown returns the message that a catalogue item does not exist in
     * the catalogue.
     * **/
    public ItemNotFoundException() {
        super("Item Does Not Exist In Catalogue");
    }
}