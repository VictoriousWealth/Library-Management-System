package uk.ac.sheffield.com1003.library;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Person {
    String firstName;
    String lastName;

    /**
     * A constructor that takes in as parameter the full name of a person (in this case author or user).
     *
     * @param fullName of the person (user or author).
     * @exception ParseException may be thrown.
     * **/
    public Person(String fullName) throws ParseException {
        firstName = "";

        ArrayList<String> fullNameSplitIntoParts = new ArrayList<>();
        Scanner scanner = new Scanner(fullName);
        while (scanner.hasNext()) {
            fullNameSplitIntoParts.add(scanner.next());
        }
        scanner.close();

        lastName = fullNameSplitIntoParts.getLast();
        fullNameSplitIntoParts.removeLast();
        while (fullNameSplitIntoParts.size()>0) {
            firstName = firstName.concat(" ").concat(fullNameSplitIntoParts.getFirst());
            fullNameSplitIntoParts.removeFirst();
        }
        if (firstName.equals("")) {
            throw new ParseException("Invalid Author entry", fullName.length()-1);
        }
    }

    /**
     * A constructor that takes in as parameter the full name of a person (in this case author or user).
     *
     * @param firstName of the person (user or author), which may include all middle names if any.
     * @param lastName of the person (user or author).
     * **/
    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * A getter that returns {@link this#firstName}.
     *
     * @return the first name of a person (in this case author or user), including any middle names if any.
     * **/
    public String getFirstName() {
        return firstName;
    }

    /**
     * A getter that returns {@link this#lastName}.
     *
     * @return the last name of a person (in this case author or user).
     * **/
    public String getLastName() {
        return lastName;
    }

    /**
     * Returns a string representation of a person
     *
     * @return A string representation of a person in
     * the format "<last name>, <first name>"
     */
    @Override
    public String toString() {
        return lastName.concat(",").concat(firstName);
    }

    /**
     * An overridden method that compares if two objects of type Person are the same.
     *
     * @param o is the object to which the current object this is to be compared with.
     * **/
    @Override
    public boolean equals(Object o) {
        return (this.firstName.startsWith(((Person) o).firstName) || ((Person) o).firstName.startsWith(this.firstName))
                && this.lastName.equals(((Person) o).lastName);
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
