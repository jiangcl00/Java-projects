package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import listClasses.BasicLinkedList;
import listClasses.SortedLinkedList;
import java.util.*;

public class StudentTests {

	@Test // getSize of 3
	public void getSizeOne() {
		BasicLinkedList<String> one = new BasicLinkedList<String>();
		one.addToFront("A").addToFront("B").addToFront("C");// A-B-C
		int expectedResults = 3;
		assertTrue(expectedResults == one.getSize());
	}

	@Test // getSize of 0
	public void getSizeTwo() {
		BasicLinkedList<String> one = new BasicLinkedList<String>();

		int expectedResults = 0;
		assertTrue(expectedResults == one.getSize());
	}

	@Test // addToEnd 3 elements
	public void addToEndOne() {
		BasicLinkedList<String> one = new BasicLinkedList<String>();
		one.addToEnd("A").addToEnd("B").addToEnd("C");// A-B-C

		String expectedResults1 = "C";
		String expectedResults2 = "A";

		assertTrue(one.getLast() == expectedResults1
				&& one.getFirst() == expectedResults2);
	}

	@Test // addToEnd 1 element
	public void addToEndTwo() {
		BasicLinkedList<String> one = new BasicLinkedList<String>();
		one.addToEnd("A");// A

		String expectedResults = "A";

		assertTrue(one.getLast() == expectedResults);
	}

	@Test // addToFront 3 elements
	public void addToFrontOne() {
		BasicLinkedList<String> one = new BasicLinkedList<String>();
		one.addToFront("A").addToFront("B").addToFront("C");// A-B-C

		String expectedResults1 = "A";
		String expectedResults2 = "C";

		assertTrue(one.getLast() == expectedResults1
				&& one.getFirst() == expectedResults2);
	}

	@Test // getFirst from a list contains 3 elements
	public void getFirstOne() {
		BasicLinkedList<String> one = new BasicLinkedList<String>();
		one.addToFront("A").addToEnd("B").addToFront("C"); // C-A-B

		String expectedResults = "C";

		assertTrue(one.getFirst() == expectedResults);
	}

	@Test // getFirst from an empty list
	public void getFirstTwo() {
		BasicLinkedList<String> one = new BasicLinkedList<String>();// empty

		String expectedResults = null;

		assertEquals(expectedResults, one.getFirst());

	}

	@Test // getLast from a list contains 3 elements
	public void getLastOne() {
		BasicLinkedList<String> one = new BasicLinkedList<String>();
		one.addToFront("A").addToEnd("B").addToFront("C"); // C-A-B

		String expectedResults = "B";

		assertTrue(one.getLast() == expectedResults);
	}

	@Test // getLast from an empty list
	public void getLastTwo() {
		BasicLinkedList<String> one = new BasicLinkedList<String>();// empty

		String expectedResults = null;

		assertEquals(expectedResults, one.getLast());

	}

	@Test // retrieveFirstElement from a list contains 3 elements
	public void retrieveFirstElementOne() {
		BasicLinkedList<String> one = new BasicLinkedList<String>();
		one.addToFront("A").addToEnd("B").addToFront("C"); // C-A-B
		String first = one.retrieveFirstElement();

		String expectedResults1 = "C";
		String expectedResults2 = "A";

		assertTrue(expectedResults1 == first
				&& expectedResults2 == one.getFirst());
	}

	@Test // retrieveFirstElement from a list contains only 1 element
	public void retrieveFirstElementTwo() {
		BasicLinkedList<String> one = new BasicLinkedList<String>();
		one.addToFront("A"); // A
		String first = one.retrieveFirstElement();

		String expectedResults1 = "A";
		String expectedResults2 = null;

		assertTrue(expectedResults1 == first
				&& expectedResults2 == one.getFirst());
	}

	@Test // retrieveFirstElement from an empty list
	public void retrieveFirstElementThree() {
		BasicLinkedList<String> one = new BasicLinkedList<String>();// empty

		String expectedResults = null;

		assertEquals(expectedResults, one.retrieveFirstElement());
	}

	@Test // retrieveLastElement from a list contains 3 elements
	public void retrieveLastElementOne() {
		BasicLinkedList<String> one = new BasicLinkedList<String>();
		one.addToFront("A").addToEnd("B").addToFront("C"); // C-A-B
		String first = one.retrieveLastElement();

		String expectedResults1 = "B";
		String expectedResults2 = "A";

		assertTrue(
				expectedResults1 == first && expectedResults2 == one.getLast());
	}

	@Test // retrieveLastElement from a list contains only 1 element
	public void retrieveLastElementTwo() {
		BasicLinkedList<String> one = new BasicLinkedList<String>();
		one.addToFront("A"); // A
		String last = one.retrieveLastElement();

		String expectedResults1 = "A";
		String expectedResults2 = null;
		assertTrue(
				expectedResults1 == last && expectedResults2 == one.getLast());
	}

	@Test // retrieveLastElement from an empty list
	public void retrieveLastElementThree() {
		BasicLinkedList<String> one = new BasicLinkedList<String>();// empty
		String last = one.retrieveLastElement();

		String expectedResults = null;
		int e = 0;
		assertTrue(expectedResults == last && e == one.getSize());
	}

	@Test // remove the 2nd element from the list
	public void removeOne() {
		BasicLinkedList<String> one = new BasicLinkedList<String>();
		one.addToFront("A").addToEnd("B"); // A-B
		one.remove("A", String.CASE_INSENSITIVE_ORDER);

		String expectedResults1 = "B";
		String expectedResults2 = "B";

		assertTrue(expectedResults1 == one.getFirst()
				&& expectedResults2 == one.getLast());
	}

	@Test // remove the head from the list
	public void removeTwo() {
		BasicLinkedList<String> one = new BasicLinkedList<String>();
		one.addToFront("A").addToEnd("B").addToFront("C"); // C-A-B
		one.remove("C", String.CASE_INSENSITIVE_ORDER);

		String expectedResults1 = "A";
		String expectedResults2 = "B";

		assertTrue(expectedResults1 == one.getFirst()
				&& expectedResults2 == one.getLast());
	}

	@Test // remove the tail from the list
	public void removeThree() {
		BasicLinkedList<String> one = new BasicLinkedList<String>();
		one.addToFront("A").addToEnd("B").addToFront("C"); // C-A-B
		one.remove("B", String.CASE_INSENSITIVE_ORDER);

		String expectedResults1 = "C";
		String expectedResults2 = "A";

		assertTrue(expectedResults1 == one.getFirst()
				&& expectedResults2 == one.getLast());
	}

	@Test // remove all occurrences of C in the list
	public void removeFour() {
		BasicLinkedList<String> one = new BasicLinkedList<String>();
		one.addToFront("A").addToEnd("C").addToFront("C").addToEnd("C")
				.addToFront("C"); // C-C-A-C-C
		one.remove("C", String.CASE_INSENSITIVE_ORDER);

		String expectedResults = "A";

		assertTrue(expectedResults == one.getFirst()
				&& expectedResults == one.getLast());
	}

	// throws exception when calling iterator().remove()
	@Test(expected = UnsupportedOperationException.class)
	public void iteratorOne() throws UnsupportedOperationException {
		BasicLinkedList<String> one = new BasicLinkedList<String>();
		one.addToFront("A").addToEnd("B").addToFront("C"); // C-A-B

		one.iterator().remove();

	}

	@Test //print out the list
	public void iteratorTwo() {
		BasicLinkedList<String> one = new BasicLinkedList<String>();
		one.addToFront("A").addToEnd("B").addToFront("C"); // C-A-B
		String list = "";
		for (String element : one) {
			list += element + " ";
		}

		String expectedResults = "C A B ";

		assertEquals(expectedResults, list);
	}

	@Test //getReverseArrayList for a list that contains 3 elements
	public void getReverseArrayListOne() {
		BasicLinkedList<String> one = new BasicLinkedList<String>();
		one.addToFront("A").addToEnd("B").addToFront("C"); // C-A-B
		ArrayList<String> arrayList = new ArrayList<String>();
		arrayList = one.getReverseArrayList();
		String list = "";
		for (String element : arrayList) {
			list += element + " ";
		}

		String expectedResults = "B A C ";

		assertEquals(expectedResults, list);
	}

	@Test //getReverseArrayList for a list that contains 1 element
	public void getReverseArrayListTwo() {
		BasicLinkedList<String> one = new BasicLinkedList<String>();
		one.addToFront("A"); // A
		ArrayList<String> arrayList = new ArrayList<String>();
		arrayList = one.getReverseArrayList();
		String list = "";
		for (String element : arrayList) {
			list += element + " ";
		}

		String expectedResults = "A ";

		assertEquals(expectedResults, list);
	}

	@Test //getReverseList for a list that contains 3 elements
	public void getReverseListOne() {
		BasicLinkedList<String> one = new BasicLinkedList<String>();
		one.addToFront("A").addToEnd("B").addToFront("C"); // C-A-B
		BasicLinkedList<String> two = new BasicLinkedList<String>();
		two = one.getReverseList();
		String list = "";
		for (String element : two) {
			list += element + " ";
		}

		String expectedResults = "B A C ";

		assertEquals(expectedResults, list);
	}

	@Test //add 3 elements to a sorted linked list
	public void sortedAddOne() {
		SortedLinkedList<String> one = new SortedLinkedList<String>(
				String.CASE_INSENSITIVE_ORDER);
		one.add("B").add("C").add("A"); // A-B-C

		String list = "";
		for (String element : one) {
			list += element + " ";
		}

		String expectedResults = "A B C ";

		assertEquals(expectedResults, list);
	}

	@Test //add an element that is already in the sorted linked list
	public void sortedAddTwo() {
		SortedLinkedList<String> one = new SortedLinkedList<String>(
				String.CASE_INSENSITIVE_ORDER);
		one.add("B").add("C").add("B"); // B-B-C

		String list = "";
		for (String element : one) {
			list += element + " ";
		}

		String expectedResults = "B B C ";

		assertEquals(expectedResults, list);
	}
	
	@Test //add 3 elements to a sorted linked list
	public void sortedAddThree() {
		SortedLinkedList<String> one = new SortedLinkedList<String>(
				String.CASE_INSENSITIVE_ORDER);
		one.add("B").add("C").add("A").add("Z"); // A-B-C-Z

		String list = "";
		for (String element : one) {
			list += element + " ";
		}

		String expectedResults = "A B C Z ";

		assertEquals(expectedResults, list);
		assertTrue(one.getFirst() == "A" &&one.getLast() == "Z");
	}


	@Test //remove an element from the sorted linked list
	public void sortedRemoveOne() {
		SortedLinkedList<String> one = new SortedLinkedList<String>(
				String.CASE_INSENSITIVE_ORDER);
		one.add("B").add("C").add("B"); // B-B-C
		one.remove("C");
		String list = "";
		for (String element : one) {
			list += element + " ";
		}

		String expectedResults = "B B ";

		assertEquals(expectedResults, list);
	}

	@Test //remove all occurrence of an element from the sorted linked list
	public void sortedRemoveTwo() {
		SortedLinkedList<String> one = new SortedLinkedList<String>(
				String.CASE_INSENSITIVE_ORDER);
		one.add("B").add("C").add("B").add("B"); // B-B-B-C
		one.remove("B");
		String list = "";
		for (String element : one) {
			list += element + " ";
		}

		String expectedResults = "C ";

		assertEquals(expectedResults, list);
	}
	
	//throws exception when calling addToEnd() of SortedLinkedList
	@Test(expected = UnsupportedOperationException.class)
	public void sortedAddToEnd() throws UnsupportedOperationException {
		SortedLinkedList<String> one = new SortedLinkedList<String>(
				String.CASE_INSENSITIVE_ORDER);
		one.add("B").add("C").add("A"); // A-B-C

		one.addToEnd("B");

	}

	//throws exception when calling addToFront() of SortedLinkedList
	@Test(expected = UnsupportedOperationException.class)
	public void sortedAddToFront() throws UnsupportedOperationException {
		SortedLinkedList<String> one = new SortedLinkedList<String>(
				String.CASE_INSENSITIVE_ORDER);
		one.add("B").add("C").add("A"); // A-B-C

		one.addToFront("B");

	}

}
