package sysImplementation;

/*
 * Do not modify this class, otherwise you will not pass release/secret tests
 */
public class Student implements Comparable<Student> {
	private String name;
	private int id;

	public Student(String name, int id) {
		this.name = name;
		this.id = id;
	}

	public String toString() {
		return "Name: " + name + ", Id: " + id;
	}

	public int compareTo(Student other) {
		if (id < other.id) {
			return -1;
		} else if (id == other.id) {
			return 0;
		} else {
			return 1;
		}
	}

	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		} else if (!(obj instanceof Student)) {
			return false;
		} else {
			return compareTo((Student) obj) == 0;
		}
	}

	/* If we override equals we must have correct hashCode */
	public int hashCode() {
		return id;
	}
}