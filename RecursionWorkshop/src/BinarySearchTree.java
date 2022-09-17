public class BinarySearchTree {
	
	private class Node {
		private Integer data;
		private Node left, right;

		public Node(Integer data) {
			this.data = data;
		}
	}
	
	private Node root;
	
	public BinarySearchTree () {
		root = null;
	}
	
	public boolean add(Integer data) {
		if (root == null) {
			root = new Node(data);
			return true;
		} else {
			return addAux(data, root);
		}
	}
	
	private boolean addAux(Integer data, Node rootAux) {
		int comparison = data.compareTo(rootAux.data);

		if (comparison == 0) {
			rootAux.data = data;
			return false;
		} else if (comparison < 0) {
			if (rootAux.left == null) {
				rootAux.left = new Node(data);
				return true;
			} else {
				return addAux(data, rootAux.left);
			}
		} else {
			if (rootAux.right == null) {
				rootAux.right = new Node(data);
				return true;
			} else {
				return addAux(data, rootAux.right);
			}
		}
	}
	
	@Override
	public String toString() {
	    return toString(new StringBuilder(), this.root).toString();
	}
	private StringBuilder toString(StringBuilder string, Node node) {
	    string.append('{');
	    if (node != null) {
	        string.append(node.data);
	        toString(string.append(", "), node.left);
	        toString(string.append(", "), node.right);
	    }
	    return string.append('}');
	}
	
	/* DO NOT MODIFY ANYTHING ABOVE THIS LINE */
	
	/* Question 1: Find the minimum depth of a binary tree using recursion and
	 * 			   only one parameter. The minimum depth is the number of nodes
	 * 			   along the shortest path from the root node down to the nearest 
	 * 			   leaf node. You may have one helper method in which you can 
	 * 			   decide the return type. */
	
	public int minDepth () {
		
	}
	
	/* Question 2: Given two binary trees imagine that when you put one of them
	 *             to cover the other, some nodes of the two trees are 
	 *             overlapped while the others are not. You need to merge them 
	 *             into a new binary tree. The merge rule is that if two nodes 
	 *             overlap, then sum node values up as the new value of the merged 
	 *             node. Otherwise, the NOT null node will be used as the node of 
	 *             the new tree. Create this new binary tree using recursion and 
	 *             only 2 parameters. You may have one helper method in which you can 
	 * 			   decide the return type. (Hint: In Place) */
	
	public static void mergeTrees (BinarySearchTree a, BinarySearchTree b) {
		
	}
}