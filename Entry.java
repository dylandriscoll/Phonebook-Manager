/*
Daniel Venable and Dylan Driscoll
1/26/23
CS 145
Assignment 2 - Phone Book
*/

import java.io.PrintStream;
import java.util.*;

// This class represents a single entry in the phone book

public class Entry {
    private Map<Field, String> data = new HashMap<>();

    // initializes the object
    public Entry(String number, String address, String name, String city) {
        data.put(Field.NUMBER, number);
        data.put(Field.ADDRESS, address);
        data.put(Field.NAME, name);
        data.put(Field.CITY, city);
    }

    // gets some data
    public String get(Field field) {
        return data.get(field);
    }
    
    // sets some data
    public String set(Field field, String value) {
        return data.replace(field, value);
    }

    // prints the contact to the screen
    public void print(PrintStream stream) {
        stream.printf(
            "Name: %s\nNumber: %s\nAddress: %s\nCity: %s\n",
            get(Field.NAME),
            get(Field.NUMBER),
            get(Field.ADDRESS),
            get(Field.CITY));
    }

    // the different fields of the entry
    public enum Field {
        NAME, NUMBER, ADDRESS, CITY
    }
}
