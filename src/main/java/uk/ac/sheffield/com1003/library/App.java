package uk.ac.sheffield.com1003.library;

import uk.ac.sheffield.com1003.library.catalogue.Book;
import uk.ac.sheffield.com1003.library.catalogue.Magazine;
import uk.ac.sheffield.com1003.library.exceptions.ItemAlreadyReturnedException;
import uk.ac.sheffield.com1003.library.exceptions.ItemNotFoundException;
import uk.ac.sheffield.com1003.library.exceptions.NoCopyAvailableException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;


/**
 * This is the class from which the application is run.
 * @author Nick Efe Oni.
 * **/
public class App {
    public static void main(String[] args) throws ParseException {
        Library library = new Library("Sheffield Central Library", 10);

        // adding catalogue items to library
        library.addItem(new Book("Clean Code", new Person("Robert Martin"), "9780136083238", 2008));
        library.addItem(new Book("Mad Code", new Person("Robert Steward Lee"), "9780146053678", 2018, Book.Genre.NONFICTION, 3));
        try {
            library.addItem(Book.fromBibtex(Files.readString(Paths
                    .get("src/main/resources/Lee60.bib"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // printing such catalogue
        library.printCatalogue();

        // loaning a couple of items, returning one and extending the loan length time
        try {
            Loan loan1 = library.loanItem("9780136083238", new Person("Elliot Trammarde"));
            Loan loan2 = library.loanItem("9780099549482", new Person("Nick Efe Oni"));
            loan1.printReceipt();
            loan2.printReceipt();
            library.returnItem(loan1);
            library.extendLoan(loan2, 5);
            loan1.printReceipt();
            loan2.printReceipt();
        } catch (ItemNotFoundException | NoCopyAvailableException | ItemAlreadyReturnedException e) {
            throw new RuntimeException(e);
        }

        library.printOverdue();

        // Magazine section
        Magazine magazine = new Magazine(10, "Struggle For Life", 2004, 17);
        library.addItem(magazine);
        try {
            Loan loan = library.loanItem(magazine.getTitle(), new Person("Nick Efe Oni"));
        } catch (NoCopyAvailableException | ItemNotFoundException e) {
            throw new RuntimeException(e);
        }
        // to check if the magazine was added, note that a printing successful message will have been already printed
        // to confirm this.
        library.printCatalogue();
    }
}
