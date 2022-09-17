package listClasses;

import java.util.*;

public class SortedLinkedList<T> extends BasicLinkedList<T> {

	private Comparator<T> comparator;

	public SortedLinkedList(java.util.Comparator<T> comparator) {
		super();
		this.comparator = comparator;
	}

	public SortedLinkedList<T> add(T element) {
		Node newNode = new Node(element);
		Node prev = null, curr = head;

		if (head == null) {
			head = newNode;
			tail = newNode;
		} else if (comparator.compare(newNode.data, curr.data) <= 0) {
			newNode.next = head;
			head = newNode;
		} else if (comparator.compare(newNode.data, tail.data) >= 0) {
			tail.next = newNode;
			tail = newNode;
		} else {
			while (comparator.compare(newNode.data, curr.data) > 0) {
				prev = curr;
				curr = curr.next;
			}
			prev.next = newNode;
			newNode.next = curr;
		}
		size++;

		return this;
	}

	public SortedLinkedList<T> remove(T targetData) {
		super.remove(targetData, comparator);
		return this;
	}

	public BasicLinkedList<T> addToEnd(T data) {
		throw new UnsupportedOperationException(
				"Invalid operation for sorted list.");
	}

	public BasicLinkedList<T> addToFront(T data) {
		throw new UnsupportedOperationException(
				"Invalid operation for sorted list.");
	}
}
