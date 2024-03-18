package uk.ac.sheffield.com1003.library.catalogue;

public class CatalogueItem {
    private String title;
    private int year;
    private int copies=0;
    private int copiesAvailable = copies;

    /**
     * A constructor that creates a catalogue item without taking in any parameters.
     * It also adds a single copy of this item.
     * **/
    public CatalogueItem() {
        this("Item");
        addCopy();
    }

    /**
     * A constructor that takes in the parameter title
     * It also adds a single copy of this item.
     *
     * @param title being the title of the catalogue item to be created
     * **/
    public CatalogueItem(String title) {
        this.title = title;
        addCopy();
    }

    /**
     * A getter that returns {@link this#title}
     *
     * @return the title of the catalogue item.
     * **/
    public String getTitle() {
        return title;
    }

    /**
     * A setter that sets {@link this#title} of the catalogue item
     *
     * @param title of the catalogue item
     * **/
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * A getter that returns {@link this#year}
     *
     * @return the year of the catalogue item.
     * **/
    public int getYear() {
        return year;
    }

    /**
     * A setter that sets {@link this#year} in which the catalogue item was published
     *
     * @return the year in which the catalogue item.
     * **/
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * A getter that returns the number of {@link this#copies} of the catalogue
     *
     * @return the number of copies of the catalogue item owned by the library.
     * **/
    public int getCopies() {
        return copies;
    }

    /**
     * A getter that returns the number of {@link this#copiesAvailable} of a certain catalogue item
     *
     * @return the number of copies left to loan
     * **/
    public int getCopiesAvailable() {
        return this.copiesAvailable;
    }

    /**
     * A setter that sets the number of copies owned by the library.
     *
     * @param copies the numeber of copies of the catalogue item owned by the library
     * **/
    public void setCopies(int copies) {
        this.copies = copies;
        this.copiesAvailable = this.copies;
    }

    /**
     * Adds copies of a catalogue item
     * @param n number of copies of catalogue item to add
     */
    public void addCopies(int n) {
        this.setCopies(getCopies()+n);
        this.copiesAvailable += n;
    }

    /**
     * Adds a single copy of a catalogue item
     */
    public void addCopy() {
        this.setCopies(getCopies()+1);
        this.copiesAvailable++;
    }

    /**
     * A method that re-adds the copy that was previously taken as a loan.
     * **/
    public void reAddAvailableCopy() {
        if (this.copiesAvailable<getCopies()) {
            this.copiesAvailable++;
        }
    }

    /**
     * A method that removes the copy that was previously taken as a loan.
     * **/
    public void removeOneAvailableCopy() {
        if (isAvailable()) {
            this.copiesAvailable--;
        }
    }

    /**
     * Checks whether there is at least one copy of the catalogue item available
     * @return True if a copy of the catalogue item is available and false otherwise
     */
    public boolean isAvailable() {
        if (this.copiesAvailable>0) {
            return true;
        }
        return false;
    }

}
