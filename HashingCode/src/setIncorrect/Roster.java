package setIncorrect;

import java.util.*;

public class Roster {
	private HashSet<Person> roster = new HashSet<Person>();

	public void addPerson(String name, int id) {
		roster.add(new Person(name, id));
	}

	public boolean findPerson(String name, int id) {
		Person person = new Person(name, id);
		
		return roster.contains(person);
	}
}