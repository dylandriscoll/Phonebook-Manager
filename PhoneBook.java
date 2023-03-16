/*
Daniel Venable and Dylan Driscoll
1/30/23
CS 145
Assignment 2 - Phone Book
*/

// This class represents a phonebook

public class PhoneBook {
    private List<Entry> next;

    // displays the entire list
    public void display() {
        List<Entry> contacts = next;

        while (contacts != null) {
            contacts.getData().print(System.out);
            contacts = contacts.getNext();
        }
    }

    // adds an item to the start of the list
    public void add(Entry node) {
        next = new List<Entry>(node, next);
    }

    // adds an item to the list at a specified index
    public void add(Entry node, int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException();
        } else if (index == 0) {
            add(node);
        } else {
            List<Entry> list = next;
            try {
                for (int i = 1; i < index; i++) {
                    list = list.getNext();
                }
            } catch (NullPointerException e) {
                // this happens if the index is too large
                // and it tries to call getNext on null
                throw new IndexOutOfBoundsException();
            }
            list.setNext(new List<Entry>(node, list.getNext()));
        }
    }

    // adds an item to the end of the list
    public void addToEnd(Entry node) {
        List<Entry> last = null;
        List<Entry> list = next;
        while (list != null) {
            last = list;
            list = list.getNext();
        }
        if (last == null) {
            // this happens when the list is empty
            add(node);
        } else {
            last.setNext(new List<Entry>(node));
        }
    }

    
    // gets the number of contacts
    public int getSize() {
        List<Entry> contacts = next;
        int size = 0;
        while (contacts != null) {
            contacts = contacts.getNext();
            size++;
        }
        return size;
    }

    // searches the list for an entry where a specific field matches
    public Entry search(Entry.Field field, String value) {
        List<Entry> list = next;
        while (list != null) {
            Entry data = list.getData();
            if (data.get(field).equalsIgnoreCase(value)) {
                return data;
            } else {
                list = list.getNext();
            }
        }
        return null; // if nothing is found
    }

    // deletes an entry where a specific field matches
    // returns true if an entry is found and deleted, false otherwise
    public boolean delete(Entry.Field field, String name) {
        List<Entry> last = null;
        List<Entry> list = next;
        while (list != null) {
            if (list.getData().get(field).equalsIgnoreCase(name)) {
                // delete the entry by making the previous
                // entry's next be the deleted entry's next
                if (last == null) {
                    next = list.getNext();
                } else {
                    last.setNext(list.getNext());
                }
                return true; // lets you know something was deleted
            } else {
                last = list;
                list = list.getNext();
            }
        }
        return false; // if nothing is found
    }
}