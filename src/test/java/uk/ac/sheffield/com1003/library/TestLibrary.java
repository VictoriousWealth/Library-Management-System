package uk.ac.sheffield.com1003.library;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import uk.ac.sheffield.com1003.library.catalogue.Book;

import java.text.ParseException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Do not change this test class.
 * If you wish to write more JUnit tests, do so in {@link MyTests}.
 */
public class TestLibrary extends TestLibraryBase {

    @DisplayName("The welcome function is implemented")
    @Test
    public void testWelcome() {
        Library cafe = new Library("Sheffield Central Library");
        System.out.println("Test being executed");
        assertEquals("Welcome to Sheffield Central Library", cafe.welcome());
    }

    @DisplayName("Incorrect format in author name")
    @Test
    public void testAuthorIncorrectFormat() {
        Book book = null;
        assertThrows(ParseException.class, () -> {
            new Book("A Book", new Person("Doe++John"), "978XXXXXXXXXX", 1);
        });
    }

    @DisplayName("The catalogue has the correct size")
    @Test
    public void testCatalogueSize() {
        Library library = new Library("Sheffield Library");

        Book book = createBook();
        library.addItem(book);

        assertEquals(1, library.getCatalogue().length);
    }

    @DisplayName("The catalogue is printed correctly")
    @Test
    public void testPrintCatalogue() {
        Library library = new Library("Sheffield Central Library");

        Book book1 = createBook();
        library.addItem(book1);

        Book book2 = createBookAlt();
        library.addItem(book2);

        library.printCatalogue();
        ArrayList<String> lines = getOutLines();
        assertEquals(8, lines.size());
        assertEquals("====================", lines.get(0));
        assertEquals("Welcome to Sheffield Central Library", lines.get(1));
        assertEquals("Catalogue", lines.get(2));
        assertEquals("====================", lines.get(3));
        assertEquals("Book: Author=Doe, John; ISBN=9780136083238; Genre=NONFICTION", lines.get(4));
        assertEquals("Book: Author=Doe, John; ISBN=9781111111111; Genre=UNSPECIFIED", lines.get(5));
        assertEquals("====================", lines.get(6));
    }

    @DisplayName("Loan book and print list of overdue items")
    @Test
    public void printOverdue() throws Exception {
        Library library = new Library("Sheffield Central Library", 10);
        Book book = createBook();
        library.addItem(book);
        library.loanItem(book.getTitle(), new Person("Jose Rojas"));
        library.printOverdue();
        ArrayList<String> lines = getOutLines();
        assertEquals(2, lines.size());
        assertEquals("Overdue Loans:", lines.get(0));
        assertTrue(lines.get(1).startsWith("Loan: User=Rojas, Jose; Item=Book 1;"));
    }

    @DisplayName("Loaning an item which does not exist in catalogue")
    @Test
    public void loan_NonExistentItem() {
        Exception thrown = assertThrows(Exception.class, () -> {
            Library library = new Library("Sheffield Central Library", 10);
            library.addItem(createBook());
            library.loanItem("Random book which does not exist", new Person("Jose Rojas"));
        });
        assertEquals("uk.ac.sheffield.com1003.library.exceptions.ItemNotFoundException", thrown.getClass().getName());
    }

    @DisplayName("App class exists in the right package")
    @Test
    public void testAppClassExists() {
        try {
            Class.forName("uk.ac.sheffield.com1003.library.App");
        } catch (ClassNotFoundException e) {
            fail("App class does not exist");
        }
    }
}
