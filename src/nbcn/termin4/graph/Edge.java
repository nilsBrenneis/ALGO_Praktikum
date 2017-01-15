package nbcn.termin4.graph;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Stroke;

public class Edge {
		private Vertex a;
		private Vertex b;
		private double weight;
		private int num;
		private boolean highlight;
		

		
		public Edge (Vertex a, Vertex b, Double weight){
			this.a = a;
			this.b = b;
			this.weight = weight;
			this.num = 0;
			this.highlight = false;
		}
		
		
		
		public Vertex getA()				{ 	return this.a;			}
		public Vertex getB()				{ 	return this.b;			}
		public Double getWeight()		{ 	return this.weight;		}
		public int getNum()				{	return this.num;		}
		public boolean isHighlighted()	{	return this.highlight;	}
		
		
		public void setWeight(Double weight)		{	this.weight = weight;		}
		public void setNum(int num)					{	this.num = num;				}
		public void setHighlighted (boolean bool)	{	this.highlight = bool;		}
		
			
		public void drawEdge(Graphics2D g2d, double scale, String text){
			int colValA = (int) Math.round(a.getColVal() * scale);
			int rowValA = (int) Math.round(a.getRowVal() * scale);
			int colValB = (int) Math.round(b.getColVal() * scale);
			int rowValB = (int) Math.round(b.getRowVal() * scale);
			
			Stroke st = g2d.getStroke();
			if (highlight){
				g2d.setColor(Color.CYAN);
				g2d.setStroke(new BasicStroke(Math.round(15* scale)));
				g2d.drawLine(colValA, rowValA, colValB, rowValB);
			}
			
			g2d.setColor(Color.GRAY);
			g2d.setStroke(new BasicStroke(Math.round(5*scale)));
			g2d.drawLine(colValA, rowValA, colValB, rowValB);
			g2d.setStroke(st);

			int colStart = (colValA < colValB) ? (colValA) : (colValB);
			int rowStart = (rowValA < rowValB) ? (rowValA) : (rowValB);
			int colOffset = (colValA < colValB) ? (colValB - colValA) : (colValA - colValB);
			int rowOffset = (rowValA < rowValB) ? (rowValB - rowValA) : (rowValA - rowValB);
			
			g2d.setColor(Color.BLACK);
			if (text == null){
				g2d.setFont(new Font("TimesRoman", Font.BOLD, 12));
				g2d.drawString(String.format("%4.2f", weight), colStart + (colOffset/2)-15, rowStart + (rowOffset/2));
			} else if (text == ""){
				g2d.setFont(new Font("TimesRoman", Font.BOLD, 10));
				g2d.drawString(String.format("%4.1f", weight), colStart + (colOffset/2)-15, rowStart + (rowOffset/2));
			} else {
				g2d.setFont(new Font("TimesRoman", Font.BOLD, 12));
				g2d.drawString(text, colStart + (colOffset/2)-15, rowStart + (rowOffset/2));				
			}
		}
		

}
