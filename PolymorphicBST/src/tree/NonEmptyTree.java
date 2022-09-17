package tree;

import java.util.Collection;

/**
 * This class represents a non-empty search tree. An instance of this class
 * should contain:
 * <ul>
 * <li>A key
 * <li>A value (that the key maps to)
 * <li>A reference to a left Tree that contains key:value pairs such that the
 * keys in the left Tree are less than the key stored in this tree node.
 * <li>A reference to a right Tree that contains key:value pairs such that the
 * keys in the right Tree are greater than the key stored in this tree node.
 * </ul>
 * 
 */
public class NonEmptyTree<K extends Comparable<K>, V> implements Tree<K, V> {

	/* Provide whatever instance variables you need */

	/**
	 * Only constructor we need.
	 * 
	 * @param key
	 * @param value
	 * @param left
	 * @param right
	 */
	private K key;
	private V value;
	private Tree<K, V> left, right;

	public NonEmptyTree(K key, V value, Tree<K, V> left, Tree<K, V> right) {
		this.key = key;
		this.value = value;
		this.left = left;
		this.right = right;
	}

	public V search(K key) {
		if (key.compareTo(this.key) == 0) {
			return this.value;
		} else if (key.compareTo(this.key) < 0) {
			return left.search(key);
		} else {
			return right.search(key);
		}

	}

	public NonEmptyTree<K, V> insert(K key, V value) {
		if (key.compareTo(this.key) == 0) {
			this.value = value;
		} else if (key.compareTo(this.key) < 0) {
			this.left = this.left.insert(key, value);
		} else {
			this.right = this.right.insert(key, value);
		}
		return this;
	}

	public Tree<K, V> delete(K key) {
		if (this.key.compareTo(key) == 0) {
			try {
				K max = left.max();
				this.key = max;
				value = left.search(max);
				left = left.delete(max);

			} catch (TreeIsEmptyException e) {
				try {
					K min = right.min();
					this.key = min;
					value = right.search(min);
					right = right.delete(min);
				} catch (TreeIsEmptyException ex) {
					return EmptyTree.getInstance();
				}
			}
		}
		if (this.key.compareTo(key) > 0) {
			this.left = left.delete(key);
		}
		if (this.key.compareTo(key) < 0) {
			this.right = right.delete(key);
		}
		return this;
	}

	public K max() {
		try {
			K value = this.right.max();
			return value;
		} catch (TreeIsEmptyException e) {
			return this.key;
		}
	}

	public K min() {
		try {
			K value = this.left.min();
			return value;
		} catch (TreeIsEmptyException e) {
			return this.key;
		}
	}

	public int size() {
		return 1 + this.left.size() + this.right.size();
	}

	public void addKeysToCollection(Collection<K> c) {
		c.add(this.key);
		this.left.addKeysToCollection(c);
		this.right.addKeysToCollection(c);
	}

	public Tree<K, V> subTree(K fromKey, K toKey) {
		if (this.key.compareTo(fromKey) < 0) {
			return this.right.subTree(fromKey, toKey);
		} else if (this.key.compareTo(toKey) > 0) {
			return this.left.subTree(fromKey, toKey);
		} else {
			return new NonEmptyTree<K, V>(this.key, this.value,
					this.left.subTree(fromKey, toKey),
					this.right.subTree(fromKey, toKey));
		}
	}

	public int height() {
		return 1 + Math.max(this.left.height(), this.right.height());
	}

	public void inorderTraversal(TraversalTask<K, V> p) {
		this.left.inorderTraversal(p);
		p.performTask(this.key, this.value);
		this.right.inorderTraversal(p);
	}

	public void rightRootLeftTraversal(TraversalTask<K, V> p) {
		this.right.rightRootLeftTraversal(p);
		p.performTask(this.key, this.value);
		this.left.rightRootLeftTraversal(p);
	}
}