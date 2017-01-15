package nbcn.termin4.graph;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.ArrayList;



public class Vertex {
		private char col;
		private int row;
		private int colVal;
		private int rowVal;
		private boolean highlight;
		private boolean markedForDel;
		
		private int degree;
		private boolean marked;
		private Vertex next;
		private Vertex prev;
		private Vertex parent;
		private Vertex child;
		private double key;
		
		private ArrayList<Vertex> neighbors;

		
	
		public Vertex(char col, int row, double key){
			this.col = col;
			this.row = row;
			this.colVal = ((this.col - 'A' +1) * VisualGraph.COORD_STEPSIZE) + VisualGraph.SIDE_OFFSET;
			this.rowVal = (this.row * VisualGraph.COORD_STEPSIZE) + VisualGraph.SIDE_OFFSET;
			this.highlight = false;
			this.markedForDel = false;
			
			this.degree = 0;
			this.marked = false;
			next = prev = this;
			this.parent = null;
			this.child = null;
			this.key = key;
			
			neighbors = new ArrayList<>();
		}
		

		
		
		public ArrayList<Vertex> getNeighbors(){
			return this.neighbors;		
		}
		
		public void addNeighbor(Vertex vertex){
			if (! this.neighbors.contains(vertex)){
				this.neighbors.add(vertex);
			}	
		}
		
		public void dropNeighbor (Vertex vertex){
			if (this.neighbors.contains(vertex)){
				this.neighbors.remove(vertex);
			}
		}
		
			
		
		
		public Vertex getNext()				{ 	return this.next;		}
		public Vertex getPrev()				{	return this.prev;		}
		public Vertex getChild()				{	return this.child;		}
		public Vertex getParent()				{	return this.parent;		}
		
		public void setNext(Vertex vertex)		{	this.next = vertex;		}
		public void setPrev(Vertex vertex)		{	this.prev = vertex;		}
		public void setChild(Vertex vertex)		{	this.child = vertex;		}
		public void setParent(Vertex vertex)	{	this.parent = vertex;		}
		
		
		public int getDegree()				{	return this.degree;		}
		public void setDegree(int dg)		{ 	this.degree = dg; 		}
		public void incDegree()				{	this.degree++;			}
		public void decDegree()				{	this.degree--;			}
		
		
		public boolean isMarked ()			{ 	return this.marked;		}
		public void setMarked (boolean b)	{ 	this.marked = b;		}
		
		
		public double getKey()				{	return this.key; 		}
		public void setKey(double key) 		{	this.key = key;			}
		
		
		public char getCol ()				{	return this.col;		}
		public int getRow()					{	return this.row;		}
		public int getColVal()				{	return this.colVal;		}
		public int getRowVal()				{	return this.rowVal;		}
				
		public void setCol(char col){
			this.col = col;
			this.colVal = ((this.col - 'A' +1) * VisualGraph.COORD_STEPSIZE) + VisualGraph.SIDE_OFFSET;
		}
		
		public void setRow (int row){
			this.row = row;
			this.rowVal = (this.row * VisualGraph.COORD_STEPSIZE) + VisualGraph.SIDE_OFFSET;
		}
		
			
		public boolean isHighlighted()					{ 		return this.highlight;			}
		public boolean isMarkedForDeletion()			{		return this.markedForDel;		}
		public void setHighlighted(boolean bool)		{		this.highlight = bool;			}
		public void setMarkedForDeletion(boolean bool)	{		this.markedForDel = bool;		}
		

		
		
		
		public void drawNode(Graphics2D g2d){
			int nr = VisualGraph.NODE_RADIUS;
			
			if (highlight){
				g2d.setColor(Color.CYAN);
				g2d.fillOval((this.colVal-nr-5), (this.rowVal-nr-5), (2*nr)+10, (2*nr)+10);	
			}
			String id = col + "" + row;
			g2d.setColor(Color.GRAY);
			g2d.fillOval((this.colVal-nr), (this.rowVal-nr), (2*nr), (2*nr));
			
			if (markedForDel){
				g2d.setColor(Color.RED);
			} else {
				g2d.setColor(Color.BLACK);
			}
			g2d.setFont(new Font("TimesRoman", Font.PLAIN, 20));
			g2d.drawString(id, this.colVal-14, this.rowVal+7);
		}
		
		public void drawCustomNode(Graphics2D g2d, double scale, String text){
			int nr = (int) Math.round(VisualGraph.NODE_RADIUS * scale);
			if (highlight){
				g2d.setColor(Color.ORANGE);
			} else {
				g2d.setColor(Color.GRAY);
			}
			if (key == Double.POSITIVE_INFINITY   || key == Double.NEGATIVE_INFINITY){
				g2d.setColor(Color.DARK_GRAY);
				text = "";
			}
			int colVal = (int) Math.round(this.colVal * scale);
			int rowVal = (int) Math.round(this.rowVal * scale);
			g2d.fillOval(colVal-nr, rowVal-nr, 2*nr, 2*nr);
			
			g2d.setColor(Color.BLACK);
			g2d.setFont(new Font("TimesRoman", Font.PLAIN, 12));
			g2d.drawString(text, colVal-12, rowVal+3);

		}
	
}