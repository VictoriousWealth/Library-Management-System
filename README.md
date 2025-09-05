This document explains the codebase for a Library Management System, a console-based Java application built with Gradle.

### Project Overview 📚

This is a command-line application that simulates the core functions of a library. It's built using the **Gradle** build system and demonstrates key Object-Oriented Programming (OOP) principles, including **inheritance**, **polymorphism**, and **custom exception handling**.

The system allows a user to:
* Add books and magazines to a library catalogue.
* Load book data from a BibTeX (`.bib`) file.
* Loan items to users and track due dates.
* Return and extend loans.
* Print the entire catalogue and a list of overdue items.

The project is structured with separate packages for catalogue items and exceptions, and it includes a suite of **JUnit 5** tests to ensure its functionality is correct.

---
### Codebase Structure

The project follows a standard Gradle layout, separating main source code, resources, and tests.

* `build.gradle`, `gradlew`, `settings.gradle`: Files that define the project as a **Gradle project**, managing dependencies and the build process.
* `src/main/java/uk/ac/sheffield/com1003/library/`: The root directory for the application's source code.
    * `catalogue/`: Contains classes for items that can be in the library. It uses an inheritance structure.
    * `exceptions/`: Contains custom exception classes for handling specific library-related errors.
* `src/main/resources/`: Contains non-code files. `Lee60.bib` is a data file in BibTeX format used to demonstrate file I/O.
* `src/test/java/uk/ac/sheffield/com1003/library/`: Contains the unit tests for the application, written using the JUnit 5 framework.

---
### Core Components Breakdown

#### `App.java` (The Entry Point)
This is the main driver class that demonstrates the library's features. Its `main` method simulates a user interacting with the library by:
1.  Creating a `Library` instance.
2.  Adding `Book` and `Magazine` objects to the catalogue.
3.  Reading book data from `Lee60.bib` and adding it to the catalogue.
4.  Creating `Loan` objects by loaning items to `Person` objects.
5.  Returning and extending loans.
6.  Printing formatted reports like the full catalogue and a list of overdue loans.

#### `Library.java` (The Engine)
This is the central class that manages the entire system.
* **State**: It holds the library's `name`, an array of `CatalogueItem` objects for the `catalogue`, an `ArrayList` of `Loan` objects to track current `loans`, and the default `loanLength`.
* **Functionality**:
    * `addItem()`: Adds a new `CatalogueItem` to the catalogue.
    * `loanItem()`: Checks for item availability and creates a `Loan` object. Throws `ItemNotFoundException` or `NoCopyAvailableException` if the loan cannot be made.
    * `returnItem()`: Ends a loan and makes the item's copy available again. Throws `ItemAlreadyReturnedException`.
    * `extendLoan()`: Pushes back the due date for an active loan.
    * `printCatalogue()`: Displays all items in the library, sorted by title.
    * `printOverdue()`: Displays all loans that are past their due date.

#### `catalogue` Package (The Items)
This package demonstrates **inheritance**, with `Book` and `Magazine` extending a common base class.
* `CatalogueItem.java`: This is the **base class** for all library items. It contains common properties like `title`, `year`, `copies`, and `copiesAvailable`, along with methods to manage the number of available copies.
* `Book.java`: Extends `CatalogueItem`. It adds book-specific properties: `author` (a `Person` object), `isbn`, and `genre`. A key feature is the `fromBibtex(String bibtex)` static factory method, which parses a string in BibTeX format to create a `Book` object.
* `Magazine.java`: Extends `CatalogueItem`. It adds a magazine-specific property: `number` (issue number).

#### Data Model Classes
* `Loan.java`: Represents a single loan transaction. It stores the `item`, the `user`, the `loanDate`, the `dueDate`, and the `returnedDate`. It uses Java's `java.time.LocalDateTime` for accurate date handling and provides methods like `extendLoan()` and `printReceipt()`.
* `Person.java`: A simple class to represent a person (either a library user or an author). Its constructor can parse a full name string (e.g., "Robert Martin") into a first name and a last name.

#### `exceptions` Package (Custom Error Handling)
This package defines **custom checked exceptions** to handle specific error conditions in the library, making the program more robust.
* `ItemNotFoundException`: Thrown when trying to loan an item that doesn't exist in the catalogue.
* `NoCopyAvailableException`: Thrown when all copies of an item are already on loan.
* `ItemAlreadyReturnedException`: Thrown when trying to return or extend a loan that has already been marked as returned.

#### Testing (`src/test/java/...`)
The project includes a comprehensive test suite to verify its correctness.
* `TestLibrary.java`: Contains **JUnit 5 tests** (`@Test`) that check various functionalities, such as printing the catalogue, handling overdue items, and ensuring exceptions are thrown correctly.
* `TestLibraryBase.java`: A base class for the tests. It contains helper methods for creating test objects and, importantly, code to **capture and analyze console output**. This allows the tests to verify that methods like `printCatalogue()` produce the correct text output.

---
### Files & Dependencies

* **Build System**: Gradle. Dependencies like JUnit are managed in `build.gradle`.
* **Source Data**: `src/main/resources/Lee60.bib` provides book data in a standard academic format.
* **Testing Framework**: **JUnit 5** is used for unit testing.
