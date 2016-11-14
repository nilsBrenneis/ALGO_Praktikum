package nbcn.termin2.aufgabe3;


public class RedBlackTree {
	private static final boolean RED = true;
	private static final boolean BLACK = false;
	
	public TreeNode sentinel;
	public TreeNode root;
		
	
	public RedBlackTree(){
		sentinel = new TreeNode(0, null);
		sentinel.color = BLACK;
		root = sentinel;
	}
	
	
	
	public boolean contains(long key)				{		return getNode(key) != null;		}
	public long getKey(TreeNode node)				{		return node.key;					}
	
	public boolean isRed(TreeNode node)				{		return node.color;					}
	public boolean isBlack(TreeNode node)			{		return !node.color;					}
	
	public TreeNode getLeftChild(TreeNode node)		{		return node.leftChild;				}
	public TreeNode getRightChild(TreeNode node)	{		return node.rightChild;				}
	
	
	
	public TreeNode getNode(long key){
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



	public void insert(long key){
		TreeNode adjoiningNode = new TreeNode(key, null);
		
		TreeNode tempSentinel = sentinel;
		TreeNode tempRoot = root;
		while (tempRoot != sentinel){
			tempSentinel = tempRoot;
			if (adjoiningNode.key < tempRoot.key){
				tempRoot = tempRoot.leftChild;
			} else {
				tempRoot = tempRoot.rightChild;
			}
		}
		adjoiningNode.parent = tempSentinel;
		if (tempSentinel == sentinel){
			root = adjoiningNode;
		} else if (adjoiningNode.key < tempSentinel.key){
			tempSentinel.leftChild = adjoiningNode;
		} else {
			tempSentinel.rightChild = adjoiningNode;
		}
		adjoiningNode.leftChild = sentinel;
		adjoiningNode.rightChild = sentinel;
		adjoiningNode.color = RED;
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



	public long size(TreeNode node){
		if (node == sentinel)
			return 0;
		else
			return size(node.leftChild) + size(node.rightChild) +1;
	}
	
	
	
	private void treeInsertionFixUp(TreeNode adjointNode){
		while (adjointNode.parent.color == RED){
			if (adjointNode.parent == adjointNode.parent.parent.leftChild){
				TreeNode uncleNode = adjointNode.parent.parent.rightChild;
				if (uncleNode.color == RED){
					adjointNode.parent.color = BLACK;
					uncleNode.color = BLACK;
					adjointNode.parent.parent.color = RED;
					adjointNode = adjointNode.parent.parent;
				} else if (adjointNode == adjointNode.parent.rightChild){
					adjointNode = adjointNode.parent;
					leftRotation(adjointNode);
				} else {
					adjointNode.parent.color = BLACK;
					adjointNode.parent.parent.color = RED;
					rightRotation(adjointNode.parent.parent);
				}
			} else {
				TreeNode uncleNode = adjointNode.parent.parent.leftChild;
				if (uncleNode.color == RED){
					adjointNode.parent.color = BLACK;
					uncleNode.color = BLACK;
					adjointNode.parent.parent.color = RED;
					adjointNode = adjointNode.parent.parent;
				} else if (adjointNode == adjointNode.parent.leftChild) {
					adjointNode = adjointNode.parent;
					rightRotation(adjointNode);
				} else {
					adjointNode.parent.color = BLACK;
					adjointNode.parent.parent.color = RED;
					leftRotation(adjointNode.parent.parent);
				}
			}
		}
		root.color = BLACK;
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
		oldSubtreeRoot.rightChild = newSubtreeRoot.leftChild;
		if (newSubtreeRoot.leftChild != sentinel){
			newSubtreeRoot.leftChild.parent = oldSubtreeRoot;
		}
		newSubtreeRoot.parent = oldSubtreeRoot.parent;
		if (oldSubtreeRoot.parent == sentinel){
			root = newSubtreeRoot;
		} else if (oldSubtreeRoot == oldSubtreeRoot.parent.leftChild){
			oldSubtreeRoot.parent.leftChild = newSubtreeRoot;
		} else {
			oldSubtreeRoot.parent.rightChild = newSubtreeRoot;
		}
		newSubtreeRoot.leftChild = oldSubtreeRoot;
		oldSubtreeRoot.parent = newSubtreeRoot;
	}

	
	private void rightRotation(TreeNode oldSubtreeRoot){
		TreeNode newSubtreeRoot = oldSubtreeRoot.leftChild;
		oldSubtreeRoot.leftChild = newSubtreeRoot.rightChild;
		if (newSubtreeRoot.rightChild != sentinel){
			newSubtreeRoot.rightChild.parent = oldSubtreeRoot;
		}
		newSubtreeRoot.parent = oldSubtreeRoot.parent;
		if (oldSubtreeRoot.parent == sentinel){
			root = newSubtreeRoot;
		} else if (oldSubtreeRoot == oldSubtreeRoot.parent.rightChild){
			oldSubtreeRoot.parent.rightChild = newSubtreeRoot;
		} else {
			oldSubtreeRoot.parent.leftChild = newSubtreeRoot;
		}
		newSubtreeRoot.rightChild = oldSubtreeRoot;
		oldSubtreeRoot.parent = newSubtreeRoot;
	}
	

	
	
	private void subtreeTransplant(TreeNode oldSubtreeRoot, TreeNode newSubtreeRoot){
		if (oldSubtreeRoot.parent == sentinel){
			root = newSubtreeRoot;
		} else if (oldSubtreeRoot == oldSubtreeRoot.parent.leftChild) {
			oldSubtreeRoot.parent.leftChild = newSubtreeRoot;
		} else {
			oldSubtreeRoot.parent.rightChild = newSubtreeRoot;
		}
		newSubtreeRoot.parent = oldSubtreeRoot.parent;
	}
	
	
	
	private TreeNode treeMinimum(TreeNode node){
		while (node.leftChild != sentinel){
			node = node.leftChild;
		}
		return node;
	}
	
	public int getBlack(TreeNode node) {
		int cnt = 0;
		do {
			if (node.color == BLACK) {
				cnt++;
			}
			node = node.parent;
		} while (node.parent != null);
		return cnt;
	}
	
	
	public class TreeNode{
		public long key;
		public TreeNode leftChild;
		public TreeNode rightChild;
		public TreeNode parent;
		public boolean color;
		
		public TreeNode(long key, TreeNode parent){
			this.key = key;
			this.parent = parent;
			leftChild = sentinel;
			rightChild = sentinel;
		}
	}
}
