package nbcn.termin1.aufgabe2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import nbcn.termin1.shared.Parser;
import nbcn.termin1.shared.Person;

public class Runner {

	private static ArrayList<Person> persToCheck = new ArrayList<Person>();
	
	private void fillPersList() {
		try {
			persToCheck = Parser.readFile("./Files/Termin1/Aufgabe2/persList1.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private Person knowsPers(Person a, Person b) {
		boolean aKnows = false;
		
		if (a.getPersonKnowsList().isEmpty()) {
			return a;
		}
		
		if (a.getPersonKnowsList().contains(b.getId())) {
			aKnows = true;
		}
		
		if (aKnows) {
			return b;
		}else{
			return a;
		}
		
	}
	
	private boolean isCelebrity(Person a) {
		boolean isCeleb = true;
		
		for (Person listPerson : persToCheck) {
			if (listPerson.getId() != a.getId()) {
				if (!listPerson.getPersonKnowsList().contains(a.getId())) {
					isCeleb = false;
				}
			}
		}
		
		return isCeleb;
	}
	
	private void printCelebrity(Person celeb) {
			System.out.print(celeb.getId() + " " + celeb.getName() + ", " + celeb.getSurname() +" ist Celebrity!");
	}
	
	public static void main(String[] args) {
		Runner run = new Runner();
		
		run.fillPersList();
		
		Person posCeleb = persToCheck.get(0);
		
		for (int i = 1; i < persToCheck.size(); i++) {
			
			posCeleb = run.knowsPers(posCeleb, persToCheck.get(i));
			
		}
		if (run.isCelebrity(posCeleb)) {
			run.printCelebrity(posCeleb);
		} else{
			System.out.println("Kein Celebrity vorhanden!");
		}
	}
}
