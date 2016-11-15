package nbcn.termin2.shared;


public class RedBlackTreeOC {
	public long cnt;
	
	private static final boolean RED = true;
	private static final boolean BLACK = false;
	
	private TreeNode sentinel;
	private TreeNode root;
		
	
	public RedBlackTreeOC(){
		sentinel = new TreeNode(0, null);
		sentinel.color = BLACK;
		root = sentinel;
		cnt = 0;
	}
	

	
	public void insert(long key){
		TreeNode adjoiningNode = new TreeNode(key, null);
		
		TreeNode tempSentinel = sentinel;
		cnt++;
		TreeNode tempRoot = root;
		cnt++;
		while (tempRoot != sentinel){
			tempSentinel = tempRoot;
			cnt++;
			
			if (adjoiningNode.key < tempRoot.key){
				tempRoot = tempRoot.leftChild;
				cnt++;
			} else {
				tempRoot = tempRoot.rightChild;
				cnt++;
			}
		}
		adjoiningNode.parent = tempSentinel;
		cnt++;
		if (tempSentinel == sentinel){
			root = adjoiningNode;
			cnt++;
		} else if (adjoiningNode.key < tempSentinel.key){
			tempSentinel.leftChild = adjoiningNode;
			cnt++;
		} else {
			tempSentinel.rightChild = adjoiningNode;
			cnt++;
		}
		adjoiningNode.leftChild = sentinel;
		cnt++;
		adjoiningNode.rightChild = sentinel;
		cnt++;
		adjoiningNode.color = RED;
		cnt++;
		treeInsertionFixUp(adjoiningNode);
	}



	public void delete(long key){
		TreeNode removingNode = getNode(key);
		if (removingNode == null)
			return;
		
		TreeNode tempRemovingNode = removingNode;
		TreeNode childOfRemovingNode = null;
		boolean removingNodeOriginalColor = tempRemovingNode.color;
		if (removingNode.leftChild == sentinel){
			childOfRemovingNode = removingNode.rightChild;
			subtreeTransplant(removingNode, removingNode.rightChild);
		} else if (removingNode.rightChild == sentinel) {
			childOfRemovingNode = removingNode.leftChild;
			subtreeTransplant(removingNode, removingNode.leftChild);
		} else {
			tempRemovingNode = treeMinimum(removingNode.rightChild);
			removingNodeOriginalColor = tempRemovingNode.color;
			childOfRemovingNode = tempRemovingNode.rightChild;
			if (tempRemovingNode.parent == removingNode){
				childOfRemovingNode.parent = tempRemovingNode;
			} else {
				subtreeTransplant(tempRemovingNode, tempRemovingNode.rightChild);
				tempRemovingNode.rightChild = removingNode.rightChild;
				tempRemovingNode.rightChild.parent = tempRemovingNode;
			}
			subtreeTransplant(removingNode, tempRemovingNode);
			tempRemovingNode.leftChild = removingNode.leftChild;
			tempRemovingNode.leftChild.parent = tempRemovingNode;
			tempRemovingNode.color = removingNode.color;
		}
		if (removingNodeOriginalColor == BLACK){
			treeDeletionFixUp(childOfRemovingNode);
		}
	}



	private TreeNode getNode(long key){
		TreeNode node = root;
		while (node != null){
			if (key < node.key)
				node = node.leftChild;
			else if (key > node.key)
				node = node.rightChild;
			else
				return node;
		}
		return null;
	}
	
	
	
	private void treeInsertionFixUp(TreeNode adjointNode){
		while (adjointNode.parent.color == RED){
			if (adjointNode.parent == adjointNode.parent.parent.leftChild){
				TreeNode uncleNode = adjointNode.parent.parent.rightChild;
				cnt++;
				if (uncleNode.color == RED){
					adjointNode.parent.color = BLACK;
					cnt++;
					uncleNode.color = BLACK;
					cnt++;
					adjointNode.parent.parent.color = RED;
					cnt++;
					adjointNode = adjointNode.parent.parent;
					cnt++;
				} else if (adjointNode == adjointNode.parent.rightChild){
					adjointNode = adjointNode.parent;
					cnt++;
					leftRotation(adjointNode);
					cnt++;
				} else {
					adjointNode.parent.color = BLACK;
					cnt++;
					adjointNode.parent.parent.color = RED;
					cnt++;
					rightRotation(adjointNode.parent.parent);
					cnt++;
				}
			} else {
				TreeNode uncleNode = adjointNode.parent.parent.leftChild;
				cnt++;
				if (uncleNode.color == RED){
					adjointNode.parent.color = BLACK;
					cnt++;
					uncleNode.color = BLACK;
					cnt++;
					adjointNode.parent.parent.color = RED;
					cnt++;
					adjointNode = adjointNode.parent.parent;
					cnt++;
				} else if (adjointNode == adjointNode.parent.leftChild) {
					adjointNode = adjointNode.parent;
					cnt++;
					rightRotation(adjointNode);
					cnt++;
				} else {
					adjointNode.parent.color = BLACK;
					cnt++;
					adjointNode.parent.parent.color = RED;
					cnt++;
					leftRotation(adjointNode.parent.parent);
					cnt++;
				}
			}
		}
		root.color = BLACK;
		cnt++;
	}
	
	
	
	private void treeDeletionFixUp(TreeNode newSubtreeRoot){
		while( (newSubtreeRoot != root)   &&   (newSubtreeRoot.color == BLACK) ){
			if (newSubtreeRoot == newSubtreeRoot.parent.leftChild){
				TreeNode brotherOfNewSubtreeRoot = newSubtreeRoot.parent.rightChild;
				if (brotherOfNewSubtreeRoot.color == RED){
					brotherOfNewSubtreeRoot.color = BLACK;
					newSubtreeRoot.parent.color = RED;
					leftRotation(newSubtreeRoot.parent);
					brotherOfNewSubtreeRoot = newSubtreeRoot.parent.rightChild;
				}
				if ( (brotherOfNewSubtreeRoot.leftChild.color == BLACK)   &&   (brotherOfNewSubtreeRoot.rightChild.color == BLACK) ){
					brotherOfNewSubtreeRoot.color = RED;
					newSubtreeRoot = newSubtreeRoot.parent;
				} else if (brotherOfNewSubtreeRoot.rightChild.color == BLACK){
					brotherOfNewSubtreeRoot.leftChild.color = BLACK;
					brotherOfNewSubtreeRoot.color = RED;
					rightRotation(brotherOfNewSubtreeRoot);
					brotherOfNewSubtreeRoot = newSubtreeRoot.parent.rightChild;
				} else {
					brotherOfNewSubtreeRoot.color = newSubtreeRoot.parent.color;
					newSubtreeRoot.parent.color = BLACK;
					brotherOfNewSubtreeRoot.rightChild.color = BLACK;
					leftRotation(newSubtreeRoot.parent);
					newSubtreeRoot = root;
				}
			} else {
				TreeNode brotherOfNewSubtreeRoot = newSubtreeRoot.parent.leftChild;
				if (brotherOfNewSubtreeRoot.color == RED){
					brotherOfNewSubtreeRoot.color = BLACK;
					newSubtreeRoot.parent.color = RED;
					rightRotation(newSubtreeRoot.parent);	
					brotherOfNewSubtreeRoot = newSubtreeRoot.parent.leftChild;
				}
				if ( (brotherOfNewSubtreeRoot.rightChild.color == BLACK)   &&   (brotherOfNewSubtreeRoot.leftChild.color == BLACK) ){
					brotherOfNewSubtreeRoot.color = RED;
					newSubtreeRoot = newSubtreeRoot.parent;
				} else if (brotherOfNewSubtreeRoot.leftChild.color == BLACK) {
					brotherOfNewSubtreeRoot.rightChild.color = BLACK;
					brotherOfNewSubtreeRoot.color = RED;
					leftRotation(brotherOfNewSubtreeRoot);		
					brotherOfNewSubtreeRoot = newSubtreeRoot.parent.leftChild;
				} else {
					brotherOfNewSubtreeRoot.color = newSubtreeRoot.parent.color;
					newSubtreeRoot.parent.color = BLACK;
					brotherOfNewSubtreeRoot.leftChild.color = BLACK;
					rightRotation(newSubtreeRoot.parent);
					newSubtreeRoot = root;
				}
			}
		}
		newSubtreeRoot.color = BLACK;
	}



	private void leftRotation (TreeNode oldSubtreeRoot){
		TreeNode newSubtreeRoot = oldSubtreeRoot.rightChild;
		cnt++;
		oldSubtreeRoot.rightChild = newSubtreeRoot.leftChild;
		cnt++;
		if (newSubtreeRoot.leftChild != sentinel){
			newSubtreeRoot.leftChild.parent = oldSubtreeRoot;
			cnt++;
		}
		newSubtreeRoot.parent = oldSubtreeRoot.parent;
		cnt++;
		if (oldSubtreeRoot.parent == sentinel){
			root = newSubtreeRoot;
			cnt++;
		} else if (oldSubtreeRoot == oldSubtreeRoot.parent.leftChild){
			oldSubtreeRoot.parent.leftChild = newSubtreeRoot;
			cnt++;
		} else {
			oldSubtreeRoot.parent.rightChild = newSubtreeRoot;
			cnt++;
		}
		newSubtreeRoot.leftChild = oldSubtreeRoot;
		cnt++;
		oldSubtreeRoot.parent = newSubtreeRoot;
		cnt++;
	}

	
	private void rightRotation(TreeNode oldSubtreeRoot){
		TreeNode newSubtreeRoot = oldSubtreeRoot.leftChild;
		cnt++;
		oldSubtreeRoot.leftChild = newSubtreeRoot.rightChild;
		cnt++;
		if (newSubtreeRoot.rightChild != sentinel){
			newSubtreeRoot.rightChild.parent = oldSubtreeRoot;
			cnt++;
		}
		newSubtreeRoot.parent = oldSubtreeRoot.parent;
		cnt++;
		if (oldSubtreeRoot.parent == sentinel){
			root = newSubtreeRoot;
			cnt++;
		} else if (oldSubtreeRoot == oldSubtreeRoot.parent.rightChild){
			oldSubtreeRoot.parent.rightChild = newSubtreeRoot;
			cnt++;
		} else {
			oldSubtreeRoot.parent.leftChild = newSubtreeRoot;
			cnt++;
		}
		newSubtreeRoot.rightChild = oldSubtreeRoot;
		cnt++;
		oldSubtreeRoot.parent = newSubtreeRoot;
		cnt++;
	}
	

	
	
	private void subtreeTransplant(TreeNode oldSubtreeRoot, TreeNode newSubtreeRoot){
		if (oldSubtreeRoot.parent == sentinel){
			root = newSubtreeRoot;
			cnt++;
		} else if (oldSubtreeRoot == oldSubtreeRoot.parent.leftChild) {
			oldSubtreeRoot.parent.leftChild = newSubtreeRoot;
			cnt++;
		} else {
			oldSubtreeRoot.parent.rightChild = newSubtreeRoot;
			cnt++;
		}
		newSubtreeRoot.parent = oldSubtreeRoot.parent;
		cnt++;
	}
	
	
	
	private TreeNode treeMinimum(TreeNode node){
		while (node.leftChild != sentinel){
			node = node.leftChild;
			cnt++;
		}
		return node;
	}
	

	
	
	private class TreeNode{
		private long key;
		private TreeNode leftChild;
		private TreeNode rightChild;
		private TreeNode parent;
		private boolean color;
		
		public TreeNode(long key, TreeNode parent){
			this.key = key;
			this.parent = parent;
			leftChild = sentinel;
			rightChild = sentinel;
		}
	}
}
