package examples;

public class ListDriver {
	public static void main(String[] args) {
		MyLinkedList<String> myList = new MyLinkedList<>(); /* No String on right side */
		myList.add("Zoe").add("Dylan").add("Jeremy").add("Carlos").add("Dylan");
		
		myList.printList();
	
		System.out.println("Found Jeremy: " + myList.find("Jeremy"));
		System.out.println("Found Laura: " + myList.find("Laura"));
		System.out.println("Dylan Instances: " + myList.getInstances("Dylan"));
		System.out.println("Allyson Instances: " + myList.getInstances("Allyson"));
		System.out.println("Data between: " + myList.getDataBetween("Carlos", "Dylan"));
	}
}