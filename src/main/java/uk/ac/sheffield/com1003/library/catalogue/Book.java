package uk.ac.sheffield.com1003.library.catalogue;

import uk.ac.sheffield.com1003.library.Person;

import java.text.ParseException;
import java.util.*;

public class Book extends CatalogueItem {

    public enum Genre {UNSPECIFIED, NONFICTION};
    private Person author;
    private String isbn;
    private Genre genre;

    public Book(String title) {
        // TODO: Implement
    }
    public Book(String title, Person author) {
        // TODO: Implement
    }

    public Book(String title, Person author, String isbn) {
        // TODO: Implement
    }

    public Book(String title, Person author, String isbn, int year) {
        // TODO: Implement
    }

    public Book(String title, Person author, String isbn, int year, Genre genre) {
        // TODO: Implement
    }

    public Book(String title, Person author, String isbn, int year, Genre genre, int nCopies) throws IllegalArgumentException {
        // TODO: Implement
    }

    public Person getAuthor() {
        return author;
    }

    public void setAuthor(Person person) {
        this.author = person;
    }

    @Override
    public boolean equals(Object other) {
        // TODO: Implement
        return false;
    }

    public static Book fromBibtex(String bibtex) {
        // TODO: Implement
        return null;
    }

    /**
     * Returns a string representation of a book
     *
     * @return A string representation of a book in
     * the format "Book: Author=<{@link Person#toString()}>; ISBN=<ISBN>; Genre=<Book genre>"
     */
    @Override
    public String toString() {
        // TODO: Implement
        return null;
    }

    @Override
    public int hashCode() {
        // TODO: Implement
        return 0;
    }

}
