package nbcn.termin1.shared;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Parser {

	private static final String CSV_SPLIT = ";";

	public static ArrayList<Person> readFile() throws IOException {
		ArrayList<Person> friends = new ArrayList<Person>();

//		try (BufferedReader br = new BufferedReader(new FileReader("./Files/Termin1/Aufgabe1/invList.txt"))) {
//		try (BufferedReader br = new BufferedReader(new FileReader("./Files/Termin1/Aufgabe1/invList2.txt"))) {
//		try (BufferedReader br = new BufferedReader(new FileReader("./Files/Termin1/Aufgabe2/persList1.txt"))) {
		try (BufferedReader br = new BufferedReader(new FileReader("./Files/Termin1/Aufgabe2/persList2.txt"))) {
			String line = br.readLine();

			while (line != null) {
				Person toAdd = convertDatasetToPerson(line.split(CSV_SPLIT));
				friends.add(toAdd);
				line = br.readLine();
			}
		}
		return friends;
	}

	private static Person convertDatasetToPerson(String[] personDataSet) {
		int id = Integer.parseInt(personDataSet[0]);
		String surname = personDataSet[1];
		String name = personDataSet[2];
		Person friend = new Person(id, surname, name);

		for (int i = 3; i < personDataSet.length; i++) {
			int knowsId = Integer.parseInt(personDataSet[i]);
			friend.addPersonKnows(knowsId);
		}
		return friend;
	}
}