package nbcn.termin1.aufgabe1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Parser {

	private static final String CSV_SPLIT = ";";

	public static ArrayList<Friend> readFile() throws IOException {
		ArrayList<Friend> friends = new ArrayList<Friend>();

		try (BufferedReader br = new BufferedReader(new FileReader("./Files/Termin1/Aufgabe1/invList.txt"))) {
			String line = br.readLine();

			while (line != null) {
				Friend toAdd = convertFriend(line.split(CSV_SPLIT));
				friends.add(toAdd);
				line = br.readLine();
			}
		}
		return friends;
	}

	private static Friend convertFriend(String[] friendDataSet) {
		int id = Integer.parseInt(friendDataSet[0]);
		String surname = friendDataSet[1];
		String name = friendDataSet[2];
		Friend friend = new Friend(id, surname, name);

		for (int i = 3; i < friendDataSet.length; i++) {
			int knowsId = Integer.parseInt(friendDataSet[i]);
			friend.addFriends(knowsId);
		}
		return friend;
	}
}