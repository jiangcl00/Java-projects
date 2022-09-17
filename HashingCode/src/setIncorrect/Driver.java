package setIncorrect;

public class Driver {
	public static void main(String[] args) {
		Roster section0101 = new Roster();

		section0101.addPerson("Mary", 10);
		section0101.addPerson("Peter", 20);
		section0101.addPerson("Jose", 7);

		if (section0101.findPerson("Peter", 20)) {
			System.out.println("Found Peter");
		} else {
			System.out.println("Peter not found");
		}
	}
}
