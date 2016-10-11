package nbcn.termin1.aufgabe1;

import java.util.ArrayList;

public class Runner {

	private static final int N_FREUNDE = 5;
	private static final int K_FREUNDE = 2;
	private ArrayList<Friend> friendsToInv;

	private void fillFriendsList() {
		friendsToInv.add(new Friend(0, "Peters", "Peter"));
		friendsToInv.add(new Friend(1, "Müller", "Meike"));
		friendsToInv.add(new Friend(2, "Meyer", "Michael"));
		friendsToInv.add(new Friend(3, "Mustermann", "Max"));
		friendsToInv.add(new Friend(4, "Musterfrau", "Erika"));
		
		
		friendsToInv.get(0).addFriends(4);
		friendsToInv.get(0).addFriends(3);
		
		friendsToInv.get(1).addFriends(0);
		friendsToInv.get(1).addFriends(5);
		
		friendsToInv.get(2).addFriends(1);
		friendsToInv.get(2).addFriends(4);
		
		friendsToInv.get(3).addFriends(0);
		friendsToInv.get(3).addFriends(4);
		
		friendsToInv.get(4).addFriends(3);
		friendsToInv.get(4).addFriends(5);
	}
}