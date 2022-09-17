package examples;

import java.util.ArrayList;

public class MyLinkedList<T extends Comparable<T>> { /* Notice the parameter */
	private class Node {
		private T data;
		private Node next;

		private Node(T data) {
			this.data = data;
			next = null; /* do we really need to do this? */
		}
	}

	/* List head pointer */
	private Node head;

	/* We don't actually need it */
	public MyLinkedList() {
		head = null;
	}

	/* Adding at the front of the list */
	public MyLinkedList<T> add(T data) {
		Node newNode = new Node(data);
		newNode.next = head;
		head = newNode;

		return this;
	}

	public void printList() {
		printListAux(head);
	}

	private void printListAux(Node headAux) {
		if (headAux != null) {
			System.out.println(headAux.data);
			printListAux(headAux.next);
		}
	}

	public boolean find(T target) {
		return findAux(head, target);
	}

	public boolean findAux(Node headAux, T target) {
		if (headAux != null) {
			int result = headAux.data.compareTo(target);
			return result == 0 ? true : findAux(headAux.next, target);
		}

		return false;
	}

	public int getInstances(T target) {
		return getInstancesAux(head, target);
	}

	private int getInstancesAux(Node headAux, T target) {
		if (headAux != null) {
			if (target.compareTo(headAux.data) == 0) {
				return 1 + getInstancesAux(headAux.next, target);
			}
			return 0 + getInstancesAux(headAux.next, target);
		}

		return 0;
	}

	public ArrayList<T> getDataBetween(T start, T end) {
		ArrayList<T> answer = new ArrayList<T>();

		getDataBetweenAux(head, start, end, answer);

		return answer;
	}

	private void getDataBetweenAux(Node headAux, T start, T end, ArrayList<T> answer) {
		if (headAux != null) {
			if (headAux.data.compareTo(start) >= 0 && headAux.data.compareTo(end) <= 0) {
				answer.add(headAux.data);
			}
			getDataBetweenAux(headAux.next, start, end, answer);
		}
	}
}