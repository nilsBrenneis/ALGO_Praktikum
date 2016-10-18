package nbcn.termin1.aufgabe2;

import java.io.IOException;
import java.util.ArrayList;

import nbcn.termin1.shared.Parser;
import nbcn.termin1.shared.Person;

public class Runner {

	private static ArrayList<Person> persToCheck = new ArrayList<Person>();
	
	private void fillPersList() {
		try {
			persToCheck = Parser.readFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private Person knowsPers(Person a, Person b) {
		boolean aKnows = false;
		
		for (int persId : a.getPersonKnows()) {
			if (b.getId()== persId) {
				aKnows = true;
			}
		}
		
		if (aKnows) {
			return b;
		}else{
			return a;
		}
		
	}
	
	public static void main(String[] args) {
		
	}
}
