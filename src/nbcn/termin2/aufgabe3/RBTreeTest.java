package nbcn.termin2.aufgabe3;

import static org.junit.Assert.*;
import org.junit.Test;


public class RBTreeTest {

	@Test
	public void test() {
		RedBlackTree tree = new RedBlackTree();
		assertTrue(tree.root == tree.sentinel);
		assertTrue(tree.isBlack(tree.root));
		
		
		tree.insert(1);
		assertTrue(tree.getKey(tree.root) == 1L );
		assertTrue(tree.isBlack(tree.root));
		
		
		tree.insert(2);
		assertTrue(tree.getKey(tree.root) == 1L);
		assertTrue(tree.getKey(tree.getRightChild(tree.root)) == 2L);
		
		assertTrue(tree.isBlack(tree.root));
		assertTrue(tree.isRed(tree.getRightChild(tree.root)));
		
		
		tree.insert(5);
		assertTrue(tree.getKey(tree.root) == 2L);
		assertTrue(tree.getKey(tree.getLeftChild(tree.root)) == 1L);
		assertTrue(tree.getKey(tree.getRightChild(tree.root)) == 5L);
		
		assertTrue(tree.isBlack(tree.root));
		assertTrue(tree.isRed(tree.getLeftChild(tree.root)));
		assertTrue(tree.isRed(tree.getRightChild(tree.root)));
		
		
		tree.insert(7);
		assertTrue(tree.getKey(tree.root) == 2L);
		assertTrue(tree.getKey(tree.getLeftChild(tree.root)) == 1L);
		assertTrue(tree.getKey(tree.getRightChild(tree.root)) == 5L);
		assertTrue(tree.getKey(tree.getRightChild(tree.getRightChild(tree.root))) == 7L);
		
		assertTrue(tree.isBlack(tree.root));
		assertTrue(tree.isBlack(tree.getLeftChild(tree.root)));
		assertTrue(tree.isBlack(tree.getRightChild(tree.root)));
		assertTrue(tree.isRed(tree.getRightChild(tree.getRightChild(tree.root))));		
		
		
		tree.insert(8);
		assertTrue(tree.getKey(tree.root) == 2L);
		assertTrue(tree.getKey(tree.getLeftChild(tree.root)) == 1L);
		assertTrue(tree.getKey(tree.getRightChild(tree.root)) == 7L);
		assertTrue(tree.getKey(tree.getLeftChild(tree.getRightChild(tree.root)))== 5L);
		assertTrue(tree.getKey(tree.getRightChild(tree.getRightChild(tree.root))) == 8L);
		
		assertTrue(tree.isBlack(tree.root));
		assertTrue(tree.isBlack(tree.getLeftChild(tree.root)));
		assertTrue(tree.isBlack(tree.getRightChild(tree.root)));
		assertTrue(tree.isRed(tree.getRightChild(tree.getRightChild(tree.root))));	
		assertTrue(tree.isRed(tree.getLeftChild(tree.getRightChild(tree.root))));
		
		
		tree.insert(11);
		assertTrue(tree.getKey(tree.root) == 2L);
		assertTrue(tree.getKey(tree.getLeftChild(tree.root)) == 1L);
		assertTrue(tree.getKey(tree.getRightChild(tree.root)) == 7L);
		assertTrue(tree.getKey(tree.getLeftChild(tree.getRightChild(tree.root)))== 5L);
		assertTrue(tree.getKey(tree.getRightChild(tree.getRightChild(tree.root))) == 8L);
		assertTrue(tree.getKey(tree.getRightChild(tree.getRightChild(tree.getRightChild(tree.root)))) == 11L);
		
		assertTrue(tree.isBlack(tree.root));
		assertTrue(tree.isBlack(tree.getLeftChild(tree.root)));
		assertTrue(tree.isRed(tree.getRightChild(tree.root)));
		assertTrue(tree.isBlack(tree.getRightChild(tree.getRightChild(tree.root))));	
		assertTrue(tree.isBlack(tree.getLeftChild(tree.getRightChild(tree.root))));
		assertTrue(tree.isRed(tree.getRightChild(tree.getRightChild(tree.getRightChild(tree.root)))));

		
		tree.insert(14);
		assertTrue(tree.getKey(tree.root) == 2L);
		assertTrue(tree.getKey(tree.getLeftChild(tree.root)) == 1L);
		assertTrue(tree.getKey(tree.getRightChild(tree.root)) == 7L);
		assertTrue(tree.getKey(tree.getLeftChild(tree.getRightChild(tree.root)))== 5L);
		assertTrue(tree.getKey(tree.getRightChild(tree.getRightChild(tree.root))) == 11L);
		assertTrue(tree.getKey(tree.getLeftChild(tree.getRightChild(tree.getRightChild(tree.root)))) == 8L);
		assertTrue(tree.getKey(tree.getRightChild(tree.getRightChild(tree.getRightChild(tree.root)))) == 14L);
		
		assertTrue(tree.isBlack(tree.root));
		assertTrue(tree.isBlack(tree.getLeftChild(tree.root)));
		assertTrue(tree.isRed(tree.getRightChild(tree.root)));
		assertTrue(tree.isBlack(tree.getRightChild(tree.getRightChild(tree.root))));	
		assertTrue(tree.isBlack(tree.getLeftChild(tree.getRightChild(tree.root))));
		assertTrue(tree.isRed(tree.getLeftChild(tree.getRightChild(tree.getRightChild(tree.root)))));
		assertTrue(tree.isRed(tree.getRightChild(tree.getRightChild(tree.getRightChild(tree.root)))));
		
		
		
		tree.insert(15);
		assertTrue(tree.getKey(tree.root) == 7L);
		assertTrue(tree.getKey(tree.getLeftChild(tree.root)) == 2L);
		assertTrue(tree.getKey(tree.getLeftChild(tree.getLeftChild(tree.root))) == 1L);
		assertTrue(tree.getKey(tree.getRightChild(tree.getLeftChild(tree.root))) == 5L);
		assertTrue(tree.getKey(tree.getRightChild(tree.root)) == 11L);
		assertTrue(tree.getKey(tree.getLeftChild(tree.getRightChild(tree.root))) == 8L);
		assertTrue(tree.getKey(tree.getRightChild(tree.getRightChild(tree.root))) == 14L);
		assertTrue(tree.getKey(tree.getRightChild(tree.getRightChild(tree.getRightChild(tree.root)))) == 15L);
		
		assertTrue(tree.isBlack(tree.root));
		assertTrue(tree.isRed(tree.getLeftChild(tree.root)));
		assertTrue(tree.isRed(tree.getRightChild(tree.root)));
		assertTrue(tree.isBlack(tree.getRightChild(tree.getRightChild(tree.root))));	
		assertTrue(tree.isBlack(tree.getLeftChild(tree.getRightChild(tree.root))));
		assertTrue(tree.isBlack(tree.getLeftChild(tree.getRightChild(tree.getRightChild(tree.root)))));
		assertTrue(tree.isRed(tree.getRightChild(tree.getRightChild(tree.getRightChild(tree.root)))));		
		
		
		
		
		
		
		
		tree.delete(11);
		assertTrue(tree.isBlack(tree.root));
		assertTrue(tree.isRed(tree.getLeftChild(tree.root)));
		assertTrue(tree.isRed(tree.getRightChild(tree.root)));
		assertTrue(tree.isBlack(tree.getRightChild(tree.getRightChild(tree.root))));	
		assertTrue(tree.isBlack(tree.getLeftChild(tree.getRightChild(tree.root))));
		assertTrue(tree.isBlack(tree.getLeftChild(tree.getRightChild(tree.getRightChild(tree.root)))));
		assertTrue(tree.isBlack(tree.getRightChild(tree.getRightChild(tree.getRightChild(tree.root)))));		
	}

}
