package nbcn.termin1.aufgabe1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class Runner {

	private static final int K_FREUNDE = 2;
	private static ArrayList<Person> friendsToInv = new ArrayList<Person>();

	private void fillFriendsList() {
		try {
			friendsToInv = Parser.readFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void siftList() {
		for (Iterator<Person> iterator = friendsToInv.iterator(); iterator.hasNext();) {
			int friendsInList = 0;
			Person friend = iterator.next();
			for (int friend_id : friend.getPersonKnows()) {
				if (containsId(friend_id)) {
					friendsInList++;
				}
			}
			if (friendsInList < K_FREUNDE) {
				iterator.remove();
				siftList();
				break;
			}
		}
	}

	private boolean containsId(int id) {
		for (Person friend : friendsToInv) {
			if (friend.getId() == id) {
				return true;
			}
		}
		return false;
	}
	
	private static void printFriendList() {
		for (Person friend : friendsToInv) {
			System.out.print(friend.getId() + " " + friend.getSurname() + ", ");
		}
	}

	public static void main(String[] args) {
		Runner run = new Runner();
		run.fillFriendsList();
		run.siftList();
		printFriendList();
	}
}