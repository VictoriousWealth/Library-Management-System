package uk.ac.sheffield.com1003.library;

import uk.ac.sheffield.com1003.library.catalogue.CatalogueItem;
import uk.ac.sheffield.com1003.library.exceptions.ItemAlreadyReturnedException;
import uk.ac.sheffield.com1003.library.exceptions.ItemNotFoundException;
import uk.ac.sheffield.com1003.library.exceptions.NoCopyAvailableException;

import java.time.LocalDateTime;
import java.util.*;

public class Library {
    /**
     * Use this constant to initialise the catalogue
     */
    private final int CATALOGUE_CAPACITY = 100;
    private String name;
    private CatalogueItem[] catalogue;
    private ArrayList<Loan> loans = new ArrayList<>();
    private int loanLength;

    /**
     * Constructor that initialises the library name to "Library"
     * and the loan length to 10 days
     */
    public Library() {
        this.name = "Library";
        this.loanLength = 10;
    }

    /**
     * Constructor that takes a library name as parameter and
     * initialises the loan length to 10 days
     * @param name The name of the library
     */
    public Library(String name) {
        this.name = name;
        this.loanLength = 10;
    }

    /**
     * Constructor that takes a library name and the loan length
     * as parameters
     * @param name The name of the library
     * @param loanLength The number of days books should be loaned for
     */
    public Library(String name, int loanLength) {
        this.name = name;
        this.loanLength = loanLength;
    }

    /**
     * Returns a simple welcome message
     * @return "Welcome to <library name>"
     */
    public String welcome() {
        return "Welcome to ".concat(getName());
    }

    /**
     * Returns the library name
     * @return Library name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Add the given catalogue item to the catalogue.
     * 
     * @param newItem Item to be added to catalogue
     * @return true if there is space in the catalogue and new item is successfully added; false otherwise
     */
    public boolean addItem(CatalogueItem newItem) {
        // initializing catalogue array if needed
        if (catalogue == null) {
            catalogue = new CatalogueItem[CATALOGUE_CAPACITY];
        }

        // adding new item to catalogue
        int nextFreeIndex = findFirstUnusedIndex(catalogue);
        if (nextFreeIndex != -1) {
            catalogue[nextFreeIndex] = newItem;
            return true;
        }

        return false;
    }

    /**
     * auxiliary method that finds the first, starting from index 0 to array.length-1, unassigned index
     *
     * @param array to be operated on to find the next unoccupied/unfilled space.
     * @return the index of the array that has not been assigned a value of type Object, if array is filled of values of
     * type Object, then returns -1.
     * **/
    private static int findFirstUnusedIndex(Object[] array) {
        for (int index = 0; index < array.length; index++) {
            if (array[index] == null) {
                return index;
            }
        }
        return -1;
    }

    /**
     * Finds item with given title or ISBN and removes a copy from the catalogue.
     * Assumes there are no duplicated title or ISBN.
     * @param titleOrISBN Exact title or ISBN of the item to be removed.
     */
    public void removeItem(String titleOrISBN) {
        // TODO: Implement
    }

    /**
     * Find item with given title or ISBN and remove a given number of copies from the catalogue.
     * Assumes there are no duplicated title or ISBN.
     * @param titleOrISBN Exact title or ISBN of the item to be removed
     * @param n Number of copies to remove
     */
    public void removeItem(String titleOrISBN, int n) {
        // TODO: Implement
    }

    /**
     * Registers the loan of a book given its exact title or ISBN and prints out a successful or unsuccessful message.
     * 
     * @param titleOrISBN The title or ISBN of the book to be loaned
     * @param user Name of user loaning catalogue item
     * @return an instance of type Loan, the one created.
     * @throws ItemNotFoundException if a book with the given title does not exist in the catalogue
     * @throws NoCopyAvailableException if there are no copies available of the given book
     */
    public Loan loanItem(String titleOrISBN, Person user) throws ItemNotFoundException, NoCopyAvailableException {
        CatalogueItem c  = findItem(titleOrISBN);

        // checking if item exist
        if (c == null) {
            System.out.println("Unsuccessful! The item with title or isbn ".concat(titleOrISBN)
                    .concat(" cannot be added."));
            throw new ItemNotFoundException();
        }

        // checking if item cannot be loaned
        if (!c.isAvailable()) {
            System.out.println("Unsuccessful! The item with title or isbn "
                    .concat(titleOrISBN).concat(" cannot be added."));
            throw new NoCopyAvailableException();
        }

        // loaning the item
        Loan loan = new Loan(findItem(titleOrISBN), user, this.loanLength);
        loan.getItem().removeOneAvailableCopy();
        loans.add(loan);
        System.out.println("Successful! The item ".concat(c.toString()).concat(" has been added to the current ongoing loans."));
        return loan;
    }

    /**
     * Find an item in the catalogue given a title or ISBN.
     * 
     * @param titleOrISBN Title or ISBN of item to find
     * @return The catalogue item found or null otherwise
     */
    private CatalogueItem findItem(String titleOrISBN) {
        for (CatalogueItem catalogueItem:getCatalogue()) {
            if (catalogueItem.toString().contains(titleOrISBN)) {
                return catalogueItem;
            }
        }
        return null;
    }

    /**
     * Mark the item in a Returns a given loan
     *
     * @param loan the actual item on loan
     * @exception ItemAlreadyReturnedException is throw if loaned item has been already returned.
     * See {@link Loan#returnItem()} to see how and why exception is thrown.
     * Updates {@link this#loans} by removing the loan if item is not already returned.
     * Uses {@link CatalogueItem#reAddAvailableCopy()} to add back the copy that has been previously loaned.
     */
    public void returnItem(Loan loan) throws ItemAlreadyReturnedException {
        loan.returnItem();
        loan.getItem().reAddAvailableCopy();
        loans.remove(loan);
    }

    /**
     * Extends the given loan
     *
     * @param loan is the current item on loan that is requested to have a later due date
     * @param days is the amount of days the loan is to be extended
     * {@see Loan#extendLoan(int)} to see how and why {@link ItemAlreadyReturnedException} is thrown.
     */
    public void extendLoan(Loan loan, int days) throws ItemAlreadyReturnedException {
        loan.extendLoan(days);
    }

    /**
     * Return the number of copies available for a given catalogue item
     * @param item the item
     * @return number of copies available for the given item
     */
    public int copiesAvailable(CatalogueItem item) {
        return item.getCopiesAvailable();
    }

    /**
     * Returns the current list of books in the catalogue excluding nulls
     *
     * @return Array of books contained in the catalogue (excluding nulls)
     */
    public CatalogueItem[] getCatalogue() {
        CatalogueItem[] catalogueItemsWithoutNulls = new CatalogueItem[findFirstUnusedIndex(this.catalogue)];
        System.arraycopy(this.catalogue, 0, catalogueItemsWithoutNulls, 0, catalogueItemsWithoutNulls.length);
        return catalogueItemsWithoutNulls;
    }

    /**
     * Print the full catalogue in the following format (sorted by title)
     * ====================
     * <Output of call to {@link Library#welcome()}>
     * Catalogue
     * ====================
     * <Output of call to method toString in the item>
     * ====================
     */
    public void printCatalogue() {
        System.out.println("====================");
        System.out.println(this.welcome());
        System.out.println("Catalogue");
        System.out.println("====================");

        // listing the sorted array of catalogue
        List<CatalogueItem> list = new ArrayList<>(List.of(getCatalogue()));
        list.sort(Comparator.comparing(CatalogueItem::getTitle));

        for (int index = 0; index < getCatalogue().length; index++) {
            System.out.println(catalogue[index].toString());
        }
        System.out.println("====================");
    }

    /**
     * Print the list of loans that are overdue (i.e., due date has passed),
     * sorted by the number of days they are overdue by, and in the following
     * format:
     * ====================
     * Loans Overdue
     * ====================
     * <Output of call to {@link Loan#toString()}; one overdue loan>
     * ====================
     */
    public void printOverdue() {
        System.out.println("====================");
        System.out.println("Loans Overdue");
        System.out.println("====================");


        // filtering for loans that have been overdue
        ArrayList<Loan> loansOverdue = new ArrayList<>();
        for (Loan loan : loans) {
            if (LocalDateTime.now().toLocalDate().isAfter(loan.getDueDate().toLocalDate())) {
                loansOverdue.add(loan);
            }
        }

        // sorting the overdue loans list
        loansOverdue.sort(Comparator.comparingInt(o -> o.getReturnedDate().getDayOfYear() - o.getDueDate().getDayOfYear()));

        // printing out results
        for (Loan loan : loansOverdue) {
            System.out.println(loan.toString().concat("; one overdue loan"));
        }


        System.out.println("====================");
    }
}
