package nbcn.termin4.algorithms;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JPanel;

import nbcn.termin4.FibonacciHeap;
import nbcn.termin4.Main;
import nbcn.termin4.graph.Edge;
import nbcn.termin4.graph.Graph;
import nbcn.termin4.graph.Vertex;
import nbcn.termin4.graph.VisualGraph;

public class AStarAlgorithm extends JPanel{
	private static final long serialVersionUID = 1L;
	
	private static final double SCALE = VisualGraph.SUBWINDOW_SCALE;


	private Graph graph;
	private ArrayList<Vertex> log;
	private ArrayList<Edge> routeEdges;
	private ArrayList<Vertex> routeNodes;

	
	
	public  AStarAlgorithm(Graph graph, Vertex startNode, Vertex endNode){
		this.graph = graph;
		this.log = new ArrayList<>();
		this.routeEdges = new ArrayList<>();
		this.routeNodes = new ArrayList<>();
		
		if (aStar(graph, startNode, endNode)){
			constructRoute(startNode, endNode);
		}
	}	

	
	
	private boolean aStar(Graph graph, Vertex startNode, Vertex endNode){
		FibonacciHeap openList = new FibonacciHeap();
		ArrayList<Vertex>closedList = new ArrayList<>();
		
		for (Vertex n : graph.getNodes()){
			n.setKey(Double.POSITIVE_INFINITY);
		}
		
		startNode.setKey(0.0);
		openList.insert(startNode);
		log.add(startNode);
		
		while ( ! openList.isEmpty() ){
			Vertex u = openList.extractMin();
			log.remove(u);
			
			if (u == endNode){
				return true;
			}
			closedList.add(u);
			expandNode (graph, closedList, openList, u, startNode);
		}
		return false;
	}
	
	private void expandNode(Graph graph, ArrayList<Vertex> closedList, FibonacciHeap openList, Vertex u, Vertex startNode){
		for (Vertex v : u.getNeighbors()){
			if (closedList.contains(v) ){
				continue;
			}
			double g = u.getKey() + graph.getVertexWeight(u, v);
			if (log.contains(v)   &&   g >= v.getKey()){
				continue;
			}
			v.setParent(null);
			double f = g + graph.getNodeDistance(startNode, u);
			v.setKey(f);
			if ( ! log.contains(v) ){
				openList.insert(v);
				log.add(v);
			}
		}
	}
	
	private void constructRoute (Vertex startNode, Vertex endNode){
		Vertex temp = endNode;
		while (temp != startNode   &&   temp.getNeighbors() != null   &&   !routeNodes.contains(temp)){
			Vertex minNode = temp.getNeighbors().get(0);
			for (Vertex n : temp.getNeighbors()){
				if (n.getKey() < minNode.getKey()){
					minNode = n;
				}
			}
			minNode.setHighlighted(true);
			double weight = graph.getVertexWeight(temp, minNode);
			routeEdges.add(new Edge(temp, minNode, weight));
			routeNodes.add(temp);
			temp = minNode;
		}
		for (int i = routeEdges.size() -2   ;   i >= 0   ;   i--){
			double weight = routeEdges.get(i).getWeight();
			routeEdges.get(i).setWeight(weight + routeEdges.get(i+1).getWeight());
		}
		repaint();
	}

	

	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		drawCoordinateSystem(g2d);
		drawElements(g2d);
		g2d.setColor(Color.BLACK);
		g2d.setFont(new Font("TimesRoman", Font.BOLD, 18));
		g2d.drawString("Single Source Shortest Path - A* Algorithm", 20, 20);
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
		for (Edge e : graph.getVertices()){
			e.drawEdge(g2d, SCALE, " ");
		}
		
		for (Edge e : routeEdges){
			e.setHighlighted(true);
			e.drawEdge(g2d, SCALE, "");
		}
		
		for (Vertex n : graph.getNodes()){
			String key = String.format("%4.0f", n.getKey());
			n.drawCustomNode(g2d, SCALE, key);;
		}
	}
	

		
}
