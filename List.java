// This class represents a list of phonebook entries

public class List<Data> {
    private List<Data> next;
    private Data data;
 
    // initializes the object
    public List(Data data) {
        this.data = data;
    }

    // initializes the object with a subsequent object
    public List(Data data, List<Data> next) {
        this.data = data;
        this.next = next;
    }

    // gets the next element in the list
    public List<Data> getNext() {
        return this.next;
    }

    // sets the next element in the list
    public void setNext(List<Data> next) {
        this.next = next;
    }

    // gets the data attached to this node
    public Data getData() {
        return data;
    }
}
