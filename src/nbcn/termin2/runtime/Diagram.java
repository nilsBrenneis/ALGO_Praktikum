package nbcn.termin2.runtime;


import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JComponent;



public class Diagram extends JComponent{
	private static final long serialVersionUID = 1L;
	
	public static final int H_STEP = 100;
	public static final int V_STEP = 50;
	public static final int H_OFFSET = 30;
	public static final int V_OFFSET = 20;
	
	public static final int V_SCALE = 5;
	
	private int h_scale = 1;
	
	
	
	public Diagram(){
	}

	
	
	public void paint(Graphics g){
		super.paint(g);
		
		int tmpScale = Main.PROBLEM_SIZE / Main.WINDOW_WIDTH;
		if (tmpScale > 1)
			this.h_scale = tmpScale;
	
		paintAxes(g);
		paintAnnotations(g);
		paintMinAvgMax(g);
		paintLogBase2(g);
	}
	
	
	
	private void paintAxes(Graphics g){
		g.setColor(Color.BLACK);
		
		g.drawLine(0, this.getHeight()-(V_OFFSET), this.getWidth(), this.getHeight()-(V_OFFSET));
		g.drawLine(H_OFFSET, 0, H_OFFSET, this.getHeight());
		
		//Beschriftung der x-Achse
		for (int i = 0   ;   i <= this.getWidth()   ;   i += H_STEP){
			String str = "";
			int val = i * h_scale;
			if (val != 0)
				str += val;
			g.drawString(str, i, (this.getHeight()-8));
		}
		
		//Beschriftung der y-Achse
		for (int i = 0   ;   i < this.getHeight()   ;   i += V_STEP){
			String str = "";
			int val = i/V_SCALE;
			if (val != 0)
				str += val;
			g.drawString(str, 8, (this.getHeight()-i));
		}
	}
	
	
	
	private void paintAnnotations(Graphics g){
		g.setColor(Color.RED);
		g.drawString("Minimum/Maximum", this.getWidth()-150, 50);
		
		g.setColor(Color.BLACK);
		g.drawString("Durchschnitt", this.getWidth()-150, 65);
		
		g.setColor(Color.BLUE);
		g.drawString("Log zur Basis 2", this.getWidth()-150, 80);
	}
	
	
	
	private void paintMinAvgMax(Graphics g){
		g.setColor(Color.BLACK);
		
		for (int i = 0   ;   i < Main.PROBLEM_SIZE   ;   i += h_scale){
			int xCoord = (i/h_scale) + H_OFFSET;
			int yCoord = this.getHeight() - ((Main.average[i] * V_SCALE) + V_OFFSET);
			g.fillOval(xCoord, yCoord, 3, 3);
		}
		
		g.setColor(Color.RED);
		
		for (int i = 0   ;   i < Main.PROBLEM_SIZE   ;   i += h_scale){
			int xCoord = (i/h_scale) + H_OFFSET;
			int yCoord = this.getHeight() - ((Main.minimum[i] * V_SCALE) + V_OFFSET);
			g.fillOval(xCoord, yCoord, 2, 2);
		}
		
		for (int i = 0   ;   i < Main.PROBLEM_SIZE   ;   i += h_scale){
			int xCoord = (i/h_scale) + H_OFFSET;
			int yCoord = this.getHeight() - ((Main.maximum[i] * V_SCALE) + V_OFFSET);
			g.fillOval(xCoord, yCoord, 2, 2);
		}
	}
	
	
	
	private void paintLogBase2 (Graphics g){
		g.setColor(Color.BLUE);
		for (int i = 0   ;   i < Main.PROBLEM_SIZE   ;   i += h_scale){
			int xCoord = (i/h_scale) + H_OFFSET;
			int yCoord = this.getHeight() - ((Main.logarithm[i] * V_SCALE / 100) + V_OFFSET);
			g.fillOval(xCoord, yCoord, 3, 3);
		}
	}
	
	
	
}
