package uk.ac.sheffield.com1003.library.catalogue;

public class Magazine extends CatalogueItem {
    /**
     * The number of copies of the magazine
     * **/
    private int number;

    /**
     * A constructor that takes in the parameter of the number of this magazine published this year
     *
     * @param number of this magazine published this year
     * **/
    public Magazine(int number) {
        this.number = number;
    }

    /**
     * A constructor that takes in the parameters of the number of this magazine published this year,
     * the title of the magazine
     *
     * @param number of this magazine published this year
     * @param title of the magazine
     * **/
    public Magazine(int number, String title) {
        this(number);
        this.setTitle(title);
    }

    /**
     * A constructor that takes in the parameters of the number of this magazine published this year,
     * the title of the magazine,
     * the year in which the magazine was published
     *
     * @param number of this magazine published this year
     * @param title of the magazine
     * @param year in which the magazine is published
     * **/
    public Magazine(int number, String title, int year) {
        this(number, title);
        this.setYear(year);
    }
    /**
     * A constructor that takes in the parameters of the number of this magazine published this year,
     * the title of the magazine,
     * the year in which the magazine was published,
     * the number of copies owned by the library
     *
     * @param number of this magazine published this year
     * @param title of the magazine
     * @param year in which the magazine is published
     * @param nCopies of the magazine owned by the library
     * **/
    public Magazine(int number, String title, int year, int nCopies) {
        this(number, title, year);
        this.setCopies(nCopies);
    }

    /**
     * An overridden method that returns the string representation of an instance of a magazine.
     * **/
    @Override
    public String toString() {
        return "Magazine: title="+getTitle()+"; year="+getYear()+"; number="+number+"; copies="+getCopies();
    }
}
