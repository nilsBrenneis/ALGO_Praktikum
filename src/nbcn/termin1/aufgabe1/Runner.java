package nbcn.termin1.aufgabe1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class Runner {

	private static final int K_FREUNDE = 2;
	private static ArrayList<Friend> friendsToInv = new ArrayList<Friend>();

	private void fillFriendsList() {
		Parser parser = new Parser();
		try {
			friendsToInv = parser.readFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void siftList() {
		for (Iterator<Friend> iterator = friendsToInv.iterator(); iterator.hasNext();) {
			int friendsInList = 0;
			Friend friend = iterator.next();
			for (int friend_id : friend.getFriends()) {
				if (containsId(friend_id)) {
					friendsInList++;
				}
			}
			if (friendsInList < K_FREUNDE) {
				iterator.remove();
				siftList();
			}
		}
	}

	private boolean containsId(int id) {
		for (Friend friend : friendsToInv) {
			if (friend.getId() == id) {
				return true;
			}
		}
		return false;
	}

	public static void main(String[] args) {
		Runner run = new Runner();
		run.fillFriendsList();
		run.siftList();		
		for (Friend friend : friendsToInv) {
			System.out.print(friend.getId() + " " + friend.getSurname() + ", ");
		}
	}
}