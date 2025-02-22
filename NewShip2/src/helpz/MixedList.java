package helpz;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MixedList implements Iterable<String> {
    private List<String> list;

    public MixedList() {
        list = new ArrayList<>();
    }

    // Method to add an Integer to the list (converts to String)
    public void add(Integer value) {
        list.add(String.valueOf(value));
    }

    // Method to add a String to the list (just adds the String as is)
    public void add(String value) {
        list.add(value);
    }

    // Method to add an Object to the list (converts to String using toString)
    public void add(Object value) {
        list.add(value.toString());
    }

    // Method to get the list as a String array
    public String[] getList() {
        return list.toArray(new String[0]);
    }

    // Method to print the list
    public void printList() {
        for (String s : list) {
            System.out.println(s);
        }
    }

	@Override
	public Iterator<String> iterator() {
		// TODO Auto-generated method stub
		return null;
	}
}
