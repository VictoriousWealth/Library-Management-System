package uk.ac.sheffield.com1003.library;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.expr.*;
import com.github.javaparser.ast.visitor.VoidVisitor;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import uk.ac.sheffield.com1003.library.catalogue.Book;
import uk.ac.sheffield.com1003.library.catalogue.Book.Genre;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class TestLibraryBase {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    protected Book createBook() {
        try {
            Person author = new Person("John", "Doe");
            Book book = new Book("Book 1", author, "9780136083238", 2010, Book.Genre.NONFICTION, 2);
            return book;
        } catch (IllegalArgumentException exc) {
            System.err.println(exc.getMessage());
            return null;
        }
    }

    protected Book createBookAlt() {
        try {
            Person author = new Person("John", "Doe");
            Book book = new Book("Book 1", author, "9781111111111", 2020, Genre.UNSPECIFIED, 1);
            return book;
        } catch (IllegalArgumentException exc) {
            System.err.println(exc.getMessage());
            return null;
        }
    }

    protected ArrayList<String> getOutLines() {
        Stream<String> lines = outContent.toString().lines();
        ArrayList<String> arrayList = new ArrayList<>();
        lines.forEach(arrayList::add);
        return arrayList;
    }

    protected void resetOutLines() {
        outContent.reset();
    }

    protected ArrayList<String> getErrLines() {
        Stream<String> lines = errContent.toString().lines();
        ArrayList<String> arrayList = new ArrayList<>();
        lines.forEach(arrayList::add);
        return arrayList;
    }
}
