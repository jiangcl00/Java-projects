package trees;

public class BinarySearchTree<K extends Comparable<K>, V> {
	private class Node {
		private K key;
		private V data;
		private Node left, right;

		private Node(K key, V data) {
			this.key = key;
			this.data = data;
		}
	}

	private Node root;

	public static void main(String[] args) {
		BinarySearchTree<Integer, String> bst = createBalancedTree();

		System.out.println(bst);
		System.out.println("Find: " + bst.find(10));
		System.out.println("Find: " + bst.find(85));
		System.out.println("Size: " + bst.size());
		System.out.println("Height: " + bst.height());
	}

	public boolean add(K key, V data) {
		if (root == null) {
			root = new Node(key, data);
			return true;
		} else {
			return addAux(key, data, root);
		}
	}

	private boolean addAux(K key, V data, Node rootAux) {
		int comparison = key.compareTo(rootAux.key);

		if (comparison == 0) {
			rootAux.data = data;
			return false;
		} else if (comparison < 0) {
			if (rootAux.left == null) {
				rootAux.left = new Node(key, data);
				return true;
			} else {
				return addAux(key, data, rootAux.left);
			}
		} else {
			if (rootAux.right == null) {
				rootAux.right = new Node(key, data);
				return true;
			} else {
				return addAux(key, data, rootAux.right);
			}
		}
	}

	public String toString() {
		return toStringAux(root);
	}

	private String toStringAux(Node rootAux) {
		return rootAux == null ? ""
				: toStringAux(rootAux.left) + "{" + rootAux.key + ":" + rootAux.data + "}" + toStringAux(rootAux.right);
	}

	public boolean find(K key) {
		return find(key, root);
	}

	public boolean find(K key, Node rootAux) {
		if (rootAux == null) {
			return false;
		} else {
			int comparison = key.compareTo(rootAux.key);
			if (comparison == 0) {
				return true;
			} else if (comparison < 0) {
				return find(key, rootAux.left);
			} else {
				return find(key, rootAux.right);
			}
		}

	}

	public int size() {
		return sizeAux(root);
	}

	public int sizeAux(Node rootAux) {
		return rootAux == null ? 0 : 1 + sizeAux(rootAux.left) + sizeAux(rootAux.right);
	}

	/* Note: The height of an empty tree is not defined. */
	/* Source: https://xlinux.nist.gov/dads/HTML/height.html */
	public int height() {
		if (root != null) {
			return heightAux(root);
		}
		return -1;
	}
	
	public int heightAux(Node rootAux) {
		if (rootAux.left == null && rootAux.right == null) {
			return 0;
		} else if (rootAux.left != null && rootAux.right == null) {
			return 1 + heightAux(rootAux.left);
		} else if (rootAux.left == null && rootAux.right != null) {
			return 1 + heightAux(rootAux.right);
		} else {
			return 1 + Math.max(heightAux(rootAux.left), heightAux(rootAux.right));
		}
	}
	
	public static BinarySearchTree<Integer, String> createBalancedTree() {
		BinarySearchTree<Integer, String> tree = new BinarySearchTree<Integer, String>();

		tree.add(40, "Forty");
		tree.add(20, "Twenty");
		tree.add(60, "Sixty");
		tree.add(10, "Ten");
		tree.add(30, "Thirty");
		tree.add(50, "Fifty");
		tree.add(70, "Seventy");
		tree.add(71, "Seventyone");

		return tree;
	}
}