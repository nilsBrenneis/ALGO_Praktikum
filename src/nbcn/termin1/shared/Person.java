package nbcn.termin1.shared;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class Person {

	private int id;
	private String name;
	private String surname;
	private ArrayList<Integer> personArrLi;

	Person(int id, String surname, String name) {
		this.id = id;
		this.surname = surname;
		this.name = name;
		personArrLi = new ArrayList<Integer>();
	}
	
	public void addPersonKnows(int id) {
		personArrLi.add(id);
	}
	
	public int[] getPersonKnows() throws NoSuchElementException {
		if (personArrLi.size() > 0) {
			int[] personArr = new int[personArrLi.size()];
			for (int i = 0; i < personArr.length; i++) {
				personArr[i] = personArrLi.get(i);
			}
			return personArr;
		} else {
			throw new NoSuchElementException();
		}
	}
	
	public int getKnowsCount() {
		return personArrLi.size();
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