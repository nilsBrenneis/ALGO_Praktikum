package nbcn.termin1.aufgabe1;

import java.util.ArrayList;
import java.util.Iterator;

public class Runner {

	private static final int K_FREUNDE = 2;
	private static ArrayList<Friend> friendsToInv = new ArrayList<Friend>();

	private void fillFriendsList() {
		friendsToInv.add(new Friend(0, "Peters", "Peter"));
		friendsToInv.add(new Friend(1, "Spex", "Richtael"));
		friendsToInv.add(new Friend(2, "Meyer", "Michael"));
		friendsToInv.add(new Friend(3, "Mustermann", "Max"));
		friendsToInv.add(new Friend(4, "Musterfrau", "Erika"));

		friendsToInv.get(0).addFriends(1);
		friendsToInv.get(0).addFriends(2);

		friendsToInv.get(1).addFriends(0);
		friendsToInv.get(1).addFriends(2);

		friendsToInv.get(2).addFriends(0);
		friendsToInv.get(2).addFriends(1);

		friendsToInv.get(3).addFriends(0);
		friendsToInv.get(3).addFriends(4);

		friendsToInv.get(4).addFriends(3);
		friendsToInv.get(4).addFriends(5);
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
				friendsToInv.remove(friend);
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