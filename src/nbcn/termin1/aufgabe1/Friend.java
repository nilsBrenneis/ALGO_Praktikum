package nbcn.termin1.aufgabe1;

import java.util.ArrayList;

// nilsi brilsi

public class Friend {

	private int id;
	private String name;
	private String surname;
	private ArrayList<Integer> friends;
	
	Friend(int id, String name, String surname) {
		this.id = id;
		this.name = name;
		this.surname = surname;
		friends = new ArrayList<Integer>();
	}
	
	public void addFriends(int id) {
		friends.add(id);
	}
}