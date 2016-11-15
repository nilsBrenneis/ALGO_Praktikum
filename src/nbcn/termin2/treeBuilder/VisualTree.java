package nbcn.termin2.treeBuilder;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


import javax.swing.JComponent;

import nbcn.termin2.shared.RedBlackTree;


public class VisualTree extends JComponent{
	private static final long serialVersionUID = 1L;
	
	public VisualTree(){
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Main.toggleRunStatus();
			}
		});
	}
	
	
	public void paint(Graphics g){
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		RedBlackTree tree = Main.getTree();
		
		tree.drawTree(g2d);
	}
}
