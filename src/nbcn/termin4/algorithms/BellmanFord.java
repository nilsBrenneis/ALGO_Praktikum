package nbcn.termin4.algorithms;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JPanel;

import nbcn.termin4.Main;
import nbcn.termin4.graph.Edge;
import nbcn.termin4.graph.Graph;
import nbcn.termin4.graph.Node;
import nbcn.termin4.graph.VisualGraph;

public class BellmanFord extends JPanel{
	private static final long serialVersionUID = 1L;
	
	private static final double SCALE = VisualGraph.SUBWINDOW_SCALE;
	
	private ArrayList<Edge> finalEdges;

	private Graph graph;


	
	public  BellmanFord(Graph graph, Node startNode){
		this.graph = graph;
		finalEdges  = new ArrayList<>();
		bellmanFord(graph, startNode);
		for (Node n : graph.getNodes()){
			if (n.getParent() != null){
				finalEdges.add(new Edge(n, n.getParent(), graph.getEdgeLength(n, n.getParent())));
			}
		}
	}	

	
	
	
	private boolean bellmanFord(Graph graph, Node startNode){
		initializeSingleSource(graph, startNode);
		for (int i = 1   ;   i < graph.getNodes().size()   ;   i++){
			for (Edge e : graph.getEdges()){
				relax(e.getA(), e.getB());
			}
		}

		for (Edge e : graph.getEdges()){
			if (e.getA().getKey() > ( e.getB().getKey() + graph.getEdgeLength(e.getA(), e.getB()))){
				return false;
			}		
		}
		return true;
	}
	
	
	
	private void initializeSingleSource(Graph graph, Node startNode){
		for (Node n : graph.getNodes()){
			n.setKey(Double.POSITIVE_INFINITY);
			n.setParent(null);
		}
		startNode.setKey(0.0);
	}
	
	
	
	private void relax(Node u, Node v){
		if (v.getKey() > (u.getKey() + graph.getEdgeLength(u, v))){
			v.setKey(u.getKey() + graph.getEdgeLength(u, v));
			v.setParent(u);
		}
	}
	

	
	
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		drawCoordinateSystem(g2d);
		drawElements(g2d);
		g2d.setColor(Color.BLACK);
		g2d.setFont(new Font("TimesRoman", Font.BOLD, 18));
		g2d.drawString("Bellman-Ford Algorithmus", 20, 20);		
	}
	

	
	private void drawCoordinateSystem(Graphics2D g2d){
		int startCol = (int) Math.round(VisualGraph.SIDE_OFFSET * SCALE);
		int startRow = (int) Math.round(VisualGraph.SIDE_OFFSET * SCALE);
		int windowWidth = (int) Math.round(Main.WINDOW_WIDTH * SCALE);
		int windowHeight = (int) Math.round(Main.WINDOW_HEIGHT * SCALE);;
		int coordStepsize = (int) Math.round(VisualGraph.COORD_STEPSIZE * SCALE);
		
		for (int col = startCol ; col <= windowWidth ; col += coordStepsize){
			g2d.setColor(Color.LIGHT_GRAY);
			g2d.drawLine(col, 0, col, windowHeight);
		}
		
		for (int row = startRow ; row <= windowHeight ; row += coordStepsize){
			g2d.setColor(Color.LIGHT_GRAY);
			g2d.drawLine(0, row, windowWidth, row);
		}
	}
	

	
	private void drawElements(Graphics2D g2d){
		for (Edge e :graph.getEdges()){
			e.drawEdge(g2d, SCALE, " ");
		}
		
		g2d.setColor(new Color(255,255,255,160));
		g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		for (Edge e : finalEdges){
			e.drawEdge(g2d, SCALE, " ");
		}
	
		for (Node n : graph.getNodes()){
			String key = String.format("%4.0f", n.getKey());
			n.drawCustomNode(g2d, SCALE, key);;
		}
	}		
	
	
}
