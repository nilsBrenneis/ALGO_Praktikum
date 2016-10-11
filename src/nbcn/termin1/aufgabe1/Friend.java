package nbcn.termin1.aufgabe1;

import java.util.ArrayList;

public class Friend {

	private int id;
	private String name;
	private String surname;
	private ArrayList<Integer> friendsArrLi;

	Friend(int id, String name, String surname) {
		this.id = id;
		this.name = name;
		this.surname = surname;
		friendsArrLi = new ArrayList<Integer>();
	}
	
	public void addFriends(int id) {
		friendsArrLi.add(id);
	}
	
	public int[] getFriends() {
		int[] friendsArr = new int[friendsArrLi.size()];
		for (int i = 0; i < friendsArr.length; i++) {
			friendsArr[i] = friendsArrLi.get(i);
		}
		return friendsArr;
	}
	
	public int getFriendsCount() {
		return friendsArrLi.size();
	}	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	
}