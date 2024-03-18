package uk.ac.sheffield.com1003.library.catalogue;

import uk.ac.sheffield.com1003.library.Person;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.*;

public class Book extends CatalogueItem {

    public enum Genre {UNSPECIFIED, NONFICTION};
    private Person author;
    private String isbn;
    private Genre genre;

    /**
     * A constructor that takes in the parameter title of the book.
     *
     * @param title of the book.
     * **/
    public Book(String title) {
        super(title);
    }

    /**
     * A constructor that takes in the parameter title of the book,
     * and the author of the book.
     *
     * @param title of the book.
     * @param author of the book of type Person.
     * **/
    public Book(String title, Person author) {
        super(title);
        this.author = author;
    }


    /**
     * A constructor that takes in the parameter title of the book,
     * the author of the book,
     * and the isbn of the book.
     *
     * @param title of the book.
     * @param author of the book of type Person.
     * @param isbn of the book.
     * **/
    public Book(String title, Person author, String isbn) {
        super(title);
        this.author = author;
        this.isbn = isbn;
    }

    /**
     * A constructor that takes in the parameter title of the book,
     * the author of the book,
     * the isbn of the book,
     * and the year in which the book has been published.
     *
     * @param title of the book.
     * @param author of the book of type Person.
     * @param isbn of the book.
     * @param year in which the book has been
     * **/
    public Book(String title, Person author, String isbn, int year) {
        super(title);
        this.author = author;
        this.isbn = isbn;
        this.setYear(year);
    }

    /**
     * A constructor that takes in the parameter title of the book,
     * the author of the book,
     * the isbn of the book,
     * the year in which the book has been published,
     * and the genre of the book.
     *
     * @param title of the book.
     * @param author of the book of type Person.
     * @param isbn of the book.
     * @param year in which this book has been published.
     * @param genre of the book.
     * **/
    public Book(String title, Person author, String isbn, int year, Genre genre) {
        super(title);
        this.author = author;
        this.isbn = isbn;
        this.setYear(year);
        this.genre =  genre;
    }

    /**
     * A constructor that takes in the parameter title of the book,
     * the author of the book,
     * the isbn of the book,
     * the year in which the book has been published,
     * the genre of the book,
     * and the number of copies owned by the library.
     *
     * @param title of the book.
     * @param author of the book of type Person.
     * @param isbn of the book.
     * @param year in which this book has been published.
     * @param genre of the book.
     * @param nCopies of the book that are owned by the library.
     * **/
    public Book(String title, Person author, String isbn, int year, Genre genre, int nCopies) throws IllegalArgumentException {
        super(title);
        this.author = author;
        this.isbn = isbn;
        this.setYear(year);
        this.genre =  genre;
        setCopies(nCopies);
    }

    /**
     * A getter that returns {@link this#author}
     *
     * @return the author of the book of type Person
     * **/
    public Person getAuthor() {
        return author;
    }

    /**
     * A setter that sets the {@link this#author} of the book.
     *
     * @param person being the author of the book.
     * **/
    public void setAuthor(Person person) {
        this.author = person;
    }

    /**
     * An overridden method that compares this current object to another and asserts if they are equal.
     * Looks at the {@link this#getTitle()} and see if this is a prefix for the other object or vice versa
     * And the author last name of this book coincides with the one of the other book
     * And the author first name (without including middle names) of this book coincides with the other book's author's
     * first name or vice versa.
     * @see this#author#equals(Object) for the last 2 bullet points actually implemented
     *
     * @param other the object to be compared with.
     * **/
    @Override
    public boolean equals(Object other) {
        return (this.getTitle().startsWith(((Book) other).getTitle()) ||
                ((Book) other).getTitle().startsWith(this.getTitle()))
                && (this.author.equals(((Book) other).author));
    }

    /**
     * should throw new IllegalArgumentException("Invalid BibTex entry")
     * **/
    public static Book fromBibtex(String bibtex) throws ParseException {
        Scanner scanner = new Scanner(bibtex);
        String title = "";
        String author = "";
        String isbn = "";
        String year = "";

        // getting the important info to fill in the required attributes to create an instance
        while (scanner.hasNextLine()) {
            String word  = scanner.next();
            if (word.equals("title")) {
                title  = scanner.nextLine().replace("=", "").replace("{", "")
                        .replace("}", "").replace(",", "").stripLeading().stripTrailing();
            } else if (word.equals("author")) {
                author = scanner.nextLine().replace("=", "").replace("{", "")
                        .replace("}", "").stripLeading().stripTrailing();
                if (author.contains(", ")) {
                    author = author.substring(author.indexOf(',')+1).concat(" ")
                            .concat(author.substring(0, author.indexOf(','))).replaceAll(",", "")
                            .stripLeading();
                } else {
                    author = author.replace(",", "");
                }
            } else if (word.equals("isbn")) {
                isbn = scanner.nextLine().replace("=", "").replace("{", "")
                        .replace("}", "").replace(",", "").stripLeading().stripTrailing();
            } else if (word.equals("year")) {
                year = scanner.nextLine().replace("=", "").replace("{", "")
                        .replace("}", "").replace(",", "").stripLeading().stripTrailing();
            }
        }

        // validating entry of certain fields before creating actual book instance.
        if (year.length()!= 4 || isbn.length()!=13 || (!isbn.startsWith("979") && !isbn.startsWith("978"))) {
            throw new IllegalArgumentException("Invalid BibTex entry");
        }
        scanner.close();
        return new Book(title, new Person(author), isbn, Integer.parseInt(year));
    }

    /**
     * Returns a string representation of a book
     *
     * @return A string representation of a book in
     * the format "Book: Title= <{@link #getTitle()}>; Author=<{@link Person#toString()}>; ISBN=<ISBN>; Genre=<Book genre>"
     */
    @Override
    public String toString() {
        return "Book: Title=".concat(getTitle()).concat("; Author=".concat(getAuthor().toString()))
                .concat("; ISBN=").concat(isbn).concat("; Genre=")
                .concat(genre == null?Genre.UNSPECIFIED.toString():genre.toString());
    }

    @Override
    public int hashCode() {
        return 0;
    }

}
