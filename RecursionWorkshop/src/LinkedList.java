public class LinkedList <T extends Comparable<T>> {
	
	private class Node {
		private T data;
		private Node next;

		private Node(T data) {
			this.data = data;
			next = null;
		}
	}

	private Node head;

	public LinkedList() {
		head = null;
	}

	@Override
	public String toString() {
		String result = "\" ";
		Node curr = head;

		while (curr != null) {
			result += curr.data + " ";

			curr = curr.next;
		}

		return result + "\"";
	}
	
	/* DO NOT MODIFY ANYTHING ABOVE THIS LINE */
	
	/* Question 1: Inserting an item into a sorted LinkedList using recursion 
	 * 			   and only one parameter. You may have one helper method in which
	 * 			   you can decide the return type. */
	
	public void insertion (LinkedList<T> sortedList, Node insertThis) {

	}
	
	/* Question 2: Find and remove the largest element in an unsorted LinkedList using 
	 * 			   recursion and only one parameter. You may have one helper method 
	 * 			   in which you can decide the return type. */
	
	public void removeLargest (LinkedList<T> unsortedList) {

	}
	
	/* Question 3: Merge two sorted LinkedLists into a larger sorted LinkedList using 
	 * 			   recursion and only 2 parameters. You may have one helper method in 
	 * 			   which you can decide the return type. */
	
	public LinkedList<T> mergeLists (LinkedList<T> sortedListA, LinkedList<T> sortedListB) {

	}
	
}