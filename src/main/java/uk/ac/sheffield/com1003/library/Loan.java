package uk.ac.sheffield.com1003.library;

import uk.ac.sheffield.com1003.library.catalogue.CatalogueItem;
import uk.ac.sheffield.com1003.library.exceptions.ItemAlreadyReturnedException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Loan {
    
    private CatalogueItem item;
    private Person user;
    private LocalDateTime loanDate;
    private LocalDateTime dueDate;
    private LocalDateTime returnedDate;

    /**
     * Constructor that takes as parameters the item being loaned,
     * the user borrowing the item, and the loan length.
     * Sets the loan date and time to
     * the current date using {@link LocalDateTime#now()},
     * the due date to the current date plus loan length
     * and the returned date to null.
     * 
     * @param item CatalogueItem being loaned
     * @param user Person loaning item
     * @param loanLength Number of days the loan is valid for
     */
    public Loan(CatalogueItem item, Person user, int loanLength) {
        // TODO: Implement //DONE
        this.item = item;
        this.user = user;
        this.loanDate = LocalDateTime.now();
        this.dueDate = LocalDateTime.of(loanDate.toLocalDate().plusDays(loanLength), loanDate.toLocalTime());
        this.returnedDate = null;
    }

    /**
     * A getter to get the {@link this#item} on loan.
     *
     * @return the actual item on loan.
     * **/
    public CatalogueItem getItem() {
        return this.item;
    }

    /**
     * A getter to get the {@link this#user} loaning the item.
     *
     * @return the user of type Person loaning the item.
     * **/
    public Person getUser() {
        return this.user;
    }

    /**
     * A getter that returns the {@link this#dueDate}.
     *
     * @return the date when item was loaned with the time.
     * **/
    public LocalDateTime getLoanDate() {
        return this.loanDate;
    }


    /**
     * A getter that returns the {@link this#returnedDate}.
     *
     * @return the date when the item was returned with the time. If the item was not returned, it returns null.
     * **/
    public LocalDateTime getReturnedDate() {
        return this.returnedDate;
    }

    /**
     * A getter that returns the {@link this#dueDate}.
     *
     * @return the date which loan is due.
     * **/
    public LocalDateTime getDueDate() {
        return this.dueDate;
    }

    /**
     * Prints loan receipt in the following format:
     * <Loan date in format dd-MM-yyyy HH-mm-ss>
     * <User name> has borrowed <Book title>
     * Date due: <Date item is due to be returned>
     * Returned: <Returned date and time in format dd-MM-yyyy HH-mm-ss if already returned; otherwise "n/a">
     * Thank you!
     */
    public void printReceipt() {
        System.out.println("Loan date: ".concat(getLoanDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH-mm-ss"))));
        System.out.println(getUser().toString().concat(" has borrowed ")
                .concat(getItem().getTitle()));
        System.out.println("Date due: ".concat(getDueDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))));
        System.out.println("Returned: ".concat(getReturnedDate()==null?"n/a"
                :getReturnedDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH-mm-ss"))));
        System.out.println("Thank you!");
    }

    /**
     * Updates field {@link Loan#returnedDate} with current date and time
     * @throws ItemAlreadyReturnedException if the item was already returned
     */
    public void returnItem() throws ItemAlreadyReturnedException{
        if (getReturnedDate() == null){
            this.returnedDate = LocalDateTime.now();
        } else {
            throw new ItemAlreadyReturnedException();
        }
    }

    /**
     * Extends the loan term by adding the given amount of days to field {@link Loan#dueDate}
     * @throws ItemAlreadyReturnedException if the item was already returned
     */
    public void extendLoan(int days) throws ItemAlreadyReturnedException {
        if (getReturnedDate() != null) {
            throw new ItemAlreadyReturnedException();
        } else {
            this.dueDate = LocalDateTime.of(getDueDate().toLocalDate().plusDays(days), getDueDate().toLocalTime());
        }
    }

    /**
     * Returns a string representation of a Loan
     *
     * @return A string representation of a loan in
     * the format "Loan: User=<User name>; Item=<Book title>; Type=<"Book" or "Magazine">; Due=<Due date dd-MM-yyyy>"
     */
    @Override
    public String toString() {
        return "Loan: User="+getUser()+"; Item="+getItem().getTitle()+"; Type="+getItem().getClass().getSimpleName()+"; " +
                "Due="+getDueDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }
}