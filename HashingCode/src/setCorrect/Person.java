package setCorrect;

public class Person {
	private String name;
	private int id;

	public Person(String name, int id) {
		this.name = name;
		this.id = id;
	}

	public String toString() {
		return "Name: " + name + " Id: " + id;
	}

	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof Person))
			return false;
		else
			return name.equals(((Person) obj).name);
	}

	public int hashCode() {
		return (int) name.charAt(0);
	}
}