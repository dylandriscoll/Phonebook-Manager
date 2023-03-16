/*
1/29/23
Daniel Venable and Dylan Driscoll
CS 145
Assignment 2 - Phone Book
*/

import java.util.Scanner;

// This class is a user interface for interacting with the phonebook.
// It lets you add, edit, and delete entries, as well as view all contacts.

public class TestClass {
   // main method
   // asks the user for a command, then does it and repeats
   public static void main(String args[]) {
      int[][] directions ={ 
      {1,0}, {-1,0}, {0,1}, {0,-1}, {1,1}, {-1,1}, {1,-1}, {-1,-1} };
      PhoneBook contacts = new PhoneBook();
      Scanner input = new Scanner(System.in);
      boolean stillGoing;
      do { 
         menu();
         stillGoing = userChoice(input, contacts);
      } while (stillGoing);
      input.close();
   }

   // shows the menu
   public static void menu() {
      System.out.println();
      System.out.println("Welcome to the phonebook manager tool.");
      System.out.println("Would you like to....");
      System.out.println(
         "1. Add an entry\n2. Remove an entry\n" +
         "3. View your contacts\n4. Search by name\n" +
         "5. Search by address\n6. Search by phone #\n" +
         "7. Edit a name\n8. Edit an address\n" +
         "9. Edit a phone #\n0. Edit a city\n" +
         "Otherwise type Quit, Q, or Exit to close the program");
   }
   
   // executes a command given by the user
   // returns false if the program quits and true otherwise
   public static boolean userChoice(Scanner input, PhoneBook contacts) {
      switch (input.nextLine().toLowerCase()) {
         case "1":
         case "add":
            addAnEntry(input, contacts);
            break;
         case "2":
         case "delete":
         case "remove":
            removeAnEntry(input, contacts);
            break;
         case "3":
         case "view":
            viewContacts(contacts);
            break;
         case "4":
         case "search by name":
            search(input, contacts, Entry.Field.NAME);
            break;
         case "5":
         case "search by address":
            search(input, contacts, Entry.Field.ADDRESS);
            break;
         case "6":
         case "search by phone #":
         case "search by phone":
         case "search by number":
         case "search by phone number":
            search(input, contacts, Entry.Field.NUMBER);
            break;
         case "7":
         case "edit name":
            edit(input, contacts, Entry.Field.NAME);
            break;
         case "8":
         case "edit address":
            edit(input, contacts, Entry.Field.ADDRESS);
            break;
         case "9":
         case "edit phone #":
         case "edit phone":
         case "edit number":
         case "edit phone number":
            edit(input, contacts, Entry.Field.NUMBER);
            break;
         case "0":
         case "edit city":
            edit(input, contacts, Entry.Field.CITY);
            break;
         case "q":
         case "quit":
         case "exit":
            System.out.println(
               "Program will now exit. Come back anytime.");
            return false; // quit the program
         default:
            System.out.println(
               "Please enter a valid command from the menu listed below.");
      }
      return true; // do not quit
   }
   
   // adds an entry to the front of the phonebook
   public static void addAnEntry(Scanner input, PhoneBook contacts) {
      System.out.println(
         "Please enter the first and last name: ");
      String entryName = input.nextLine();
      System.out.println(
         "Please enter their address: ");
      String entryAddress = input.nextLine();
      System.out.println(
         "Please enter their phone #: ");
      String entryNumber = input.nextLine();
      System.out.println(
         "Please enter their city: ");
      String entryCity = input.nextLine();
      Entry newEntry = new Entry(entryNumber, entryAddress, entryName, entryCity);
      contacts.add(newEntry);
      System.out.println(
         entryName + " has been added to your phonebook!");
   }
   
   // removes an entry given their name
   public static void removeAnEntry(Scanner input, PhoneBook contacts) {
      System.out.println("Enter name of contact to remove:");
      String name = input.nextLine();
      if (contacts.delete(Entry.Field.NAME, name)) {
         System.out.printf("%s successfully deleted!\n", name);
      } else {
         System.out.println("Not found.");
      }
   }
   
   // displays all the contacts
   public static void viewContacts(PhoneBook contacts) {
      contacts.display();
   }

   // converts a field to a string
   private static String fieldToString(Entry.Field field) {
      switch (field) {
         case NAME:
            return "name";
         case NUMBER:
            return "phone number";
         case ADDRESS:
            return "address";
         case CITY:
            return "city";
         default:
            throw new IllegalArgumentException();
      }
   }
   
   // seraches for a contact by a specific field
   public static void search(
         Scanner input, PhoneBook contacts, Entry.Field field) {
      System.out.printf(
         "Please enter the %s you wish to search for:\n",
         fieldToString(field));
      Entry result = contacts.search(field, input.nextLine());
      if (result != null) {
         result.print(System.out);
      } else {
         System.out.println("Not found.");
      }
   }

   // edits a field of a contact after searching for them by name
   public static void edit(Scanner input, PhoneBook contacts, Entry.Field field) {
      System.out.println(
         "Please enter the full name of the contact you wish to edit: ");
      Entry contact = contacts.search(Entry.Field.NAME, input.nextLine());
      if (contact != null) {
         System.out.printf(
            "Their %s is currently %s. What do you want to change it to?\n",
            fieldToString(field), contact.get(field));
         contact.set(field, input.nextLine().trim());
         while (contact.get(field).isEmpty()) {
            System.out.printf(
               "Please enter a new %s to replace the old one:\n",
               fieldToString(field));
               contact.set(field, input.nextLine().trim());
         }
         System.out.printf("Successfully updated their %s to %s.\n", 
            fieldToString(field), contact.get(field));
      } else {
         System.out.println("Not found.");
      }
   }
}
