package uk.ac.sheffield.com1003.library.exceptions;

/**
 * An exception class specifically made for this application
 * **/
public class NoCopyAvailableException extends Exception {

    /**
     * The exception constructor that when thrown returns the message that a catalogue item has no copies left
     * for loaning.
     * **/
    public NoCopyAvailableException() {
        super("No Copy Are Left To Loan");
    }
}