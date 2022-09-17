package sysImplementation;

public class SampleDriver {

	public static void main(String[] args) {
		
		LinkedList<Integer> intList = new LinkedList<Integer>();
		LinkedList<String> strList = new LinkedList<String>();
		LinkedList<Student> studentList = new LinkedList<Student>();
		
		String answer = "";
	
		answer += "===================intList Output=======================\n";
		intList.add(5).add(8).add(14).add(16).add(7).add(21).add(17);
		answer+= "Before: "+ intList + "\n";
		intList.replaceLess(15);
		answer+= " After: "+ intList + "\n";
		

		answer += "===================strList Output=======================\n";
		strList.add("dog").add("deer").add("bird").add("turtle").add("fish").add("bear").add("fox");
		answer+= "Before: "+ strList + "\n";
		strList.replaceLess("eel");
		answer+= " After: "+ strList + "\n";
		
		answer += "===================strList Output=======================\n";
		studentList.add(new Student("Joe", 351)).add(new Student("Tom", 100)).add(new Student("Kate", 51));
		studentList.add(new Student("David", 75)).add(new Student("Joe", 580)).add(new Student("Kevin", 85));
		studentList.add(new Student("Lisa", 175));
		answer+= "Before: "+ studentList + "\n";
		studentList.replaceLess(new Student("Amy", 100));
		answer+= " After: "+ studentList + "\n";

		System.out.println(answer);
	}

}