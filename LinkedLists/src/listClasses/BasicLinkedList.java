package listClasses;

import java.util.*;

public class BasicLinkedList<T> extends java.lang.Object
		implements java.lang.Iterable<T> {
	protected class Node {
		protected T data;
		protected Node next;

		protected Node(T data) {
			this.data = data;
			next = null;
		}
	}

	protected Node head, tail;
	protected int size = 0;

	public BasicLinkedList() {
		head = null;
		tail = null;
	}

	public int getSize() {
		return size;
	}

	public BasicLinkedList<T> addToEnd(T data) {
		Node newNode = new Node(data);
		if (head == null) {
			head = newNode;
			tail = newNode;
		} else if (head == tail) {
			head.next = newNode;
			tail = newNode;
		} else {
			tail.next = newNode;
			tail = newNode;
		}
		size++;

		return this;
	}

	public BasicLinkedList<T> addToFront(T data) {
		Node newNode = new Node(data);
		if (head == null) {
			head = newNode;
			tail = newNode;
		} else if (head == tail) {
			newNode.next = tail;
			head = newNode;
		} else {
			newNode.next = head;
			head = newNode;
		}

		size++;
		return this;
	}

	public T getFirst() {
		if (head == null) {
			return null;
		} else {
			return head.data;
		}

	}

	public T getLast() {
		if (head == null) {
			return null;
		} else {
			return tail.data;
		}
	}

	public T retrieveFirstElement() {
		if (head == null) {
			return null;
		}
		T first = getFirst();
		if (head == tail) {
			head = null;
			tail = null;
		} else {
			head = head.next;
		}
		size--;

		return first;
	}

	public T retrieveLastElement() {
		if (head == null) {
			return null;
		}
		T last = getLast();
		Node prev = null, curr = head;
		if (head == tail) {
			head = null;
			tail = null;
			size--;
		} else {
			while (curr.next != null) {
				prev = curr;
				curr = curr.next;
			}
			prev.next = tail.next;
			tail = prev;
			size--;
		}

		return last;
	}

	public BasicLinkedList<T> remove(T targetData,
			java.util.Comparator<T> comparator) {
		Node curr = head;
		while (head != null && comparator.compare(head.data, targetData) == 0) {
			if (head == tail) {
				head = null;
				tail = null;
			} else {
				head = head.next;
			}
			size--;
		}
		if (head == null) {
			return null;
		}

		while (curr.next != tail && curr.next != null) {
			if (comparator.compare(curr.next.data, targetData) == 0) {
				curr.next = curr.next.next;
				size--;
			} else {
				curr = curr.next;
			}
		}
		if (comparator.compare(tail.data, targetData) == 0) {
			curr.next = tail.next;
			tail = curr;
			size--;
		}

		return this;
	}

	public java.util.Iterator<T> iterator() {
		return new Iterator<T>() {
			private Node curr = head;

			public boolean hasNext() {
				return (curr != null);
			}

			public T next() {
				if (hasNext()) {
					T data = curr.data;
					curr = curr.next;
					return data;
				}
				return null;

			}

			public void remove() {
				throw new UnsupportedOperationException("Invalid operation");
			}
		};
	}

	public java.util.ArrayList<T> getReverseArrayList() {
		ArrayList<T> answer = new ArrayList<T>();
		if(head !=null) {
			getReverseArrayListAux(head, answer);
		}
		return answer;
	}

	// getReverseArrayList() method calls this auxiliary method to get reversed
	// array list
	private void getReverseArrayListAux(Node headAux, ArrayList<T> answer) {
		if (headAux.next != null) {
			getReverseArrayListAux(headAux.next, answer);
		}
		answer.add(headAux.data);
	}

	public BasicLinkedList<T> getReverseList() {
		BasicLinkedList<T> reverseList = new BasicLinkedList<T>();
		getReverseListAux(head, reverseList);
		return reverseList;
	}

	// getReverseList() method calls this auxiliary method to get reversed list
	private void getReverseListAux(Node headAux, BasicLinkedList<T> list) {
		if (headAux != null) {
			list.addToFront(headAux.data);
			getReverseListAux(headAux.next, list);
		}

	}
}
