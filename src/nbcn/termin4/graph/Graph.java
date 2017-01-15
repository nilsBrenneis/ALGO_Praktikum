package nbcn.termin4.graph;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.NoSuchElementException;



public class Graph{
	
	private ArrayList<Vertex> vertices;
	private ArrayList<Edge> edges;
	
	
	
	public Graph(){
		vertices = new ArrayList<>();
		edges = new ArrayList<>();
	}
		

	

	
	
	public boolean isEmpty(){
		return vertices.size() == 0;
	}
	
	

	
	/***********************************/
	/*****     NODE OPERATIONS     *****/
	/***********************************/
	
	public ArrayList<Vertex> getNodes()		{		return vertices;					}
	public int getNumberOfNodes()			{		return vertices.size();			}
	public boolean containsNode(Vertex vertex)	{		return vertices.contains(vertex);	}

	
	public void addNode(Vertex vertex){
		if ( ! vertices.contains(vertex)){
			vertices.add(vertex);
		}
	}
	
	
	public void removeNode(Vertex vertex){
		ArrayList<Edge> toBeDeleted = new ArrayList<>();
		for (Edge e : edges){
			if (e.getA() == vertex    ||    e.getB() == vertex){
				e.getA().dropNeighbor(vertex);
				e.getB().dropNeighbor(vertex);
				toBeDeleted.add(e);
			}
		}
		edges.removeAll(toBeDeleted);
		vertices.remove(vertex);
	}
	
	
	public ArrayList<Vertex> getNeighbors (Vertex vertex){
		if (vertices.contains(vertex)){
			ArrayList<Vertex> neighbors = new ArrayList<>();
			for (Edge e : edges){
				if (e.getA() == vertex){
					neighbors.add(e.getA());
				}
			}
			return neighbors;
		} else {
			throw new NoSuchElementException("Vertex isn't part of this graph");
		}
	}



	
	
	
	
	/***********************************/
	/*****     EDGE OPERATIONS     *****/
	/***********************************/

	
	public ArrayList<Edge> getVertices()			{		return edges;						}
	public int getNumberOfEdges()				{		return edges.size();				}
	public boolean containsEdge(Edge edge)		{		return edges.contains(edge);		}
	public boolean containsEdge(Vertex a, Vertex b)	{		return getVertexWeight(a,b) != null;	}
	
	
	
	
	public void addEdge(Edge edge){
		if (vertices.contains(edge.getA())   &&   vertices.contains(edge.getB())){
			edges.add(edge);
		} else {
			throw new NoSuchElementException("At least one vertex missing");
		}
	}
	
	
	public void addEdge(Vertex a, Vertex b){
		if (vertices.contains(a)   &&   vertices.contains(b)){
			addDirectedEdge(a, b);
			addDirectedEdge(b, a);
		} else {
			throw new NoSuchElementException("At least one vertex missing");
		}
	}
	
	
	
	public void addDirectedEdge(Vertex a, Vertex b){
		if (vertices.contains(a)   &&   vertices.contains(b)){
			double weight = calcEdgeWeight(a, b);
			edges.add(new Edge(a, b, weight));
			a.addNeighbor(b);
		} else {
			throw new NoSuchElementException("At least one vertex missing");
		}
	}
	
	
		
	public void removeEdge (Edge edge){
		if (edges.contains(edge)){
			edges.remove(edge);
		} else {
			throw new NoSuchElementException("Edge is not part of this Graph");
		}
	}
		
	
	public void removeEdge (Vertex a, Vertex b){
		if (vertices.contains(a)   &&   vertices.contains(b)){
			ArrayList<Edge> toBeRemoved = new ArrayList<>();
			for (Edge e : edges){
				if ( ((e.getA() == a)   &&   (e.getB() == b))   ||   ((e.getA() == b)   &&   (e.getB() == a)) ){
					e.getA().dropNeighbor(b);
					e.getB().dropNeighbor(a);
					toBeRemoved.add(e);
				}
			}
			edges.removeAll(toBeRemoved);
		} else {
			throw new NoSuchElementException("At least one vertex missing");
		}
	}

	
	
	
	public void setEdgeHighlighted (Vertex a, Vertex b, boolean bool){
		if (vertices.contains(a)   &&   vertices.contains(b)){
			for (Edge e : edges){
				if ( ((e.getA() == a)   &&   (e.getB() == b))   ||   ((e.getA() == b)   &&   (e.getB() == a)) ){
					e.setHighlighted(bool);
				}
			}
		} else {
			throw new NoSuchElementException("At least one vertex missing");

		}
	}

	
	public boolean isEdgeHighlighted (Vertex a, Vertex b){
		if (vertices.contains(a)   &&   vertices.contains(b)){
			for (Edge e : edges){
				if ( ((e.getA() == a)   &&   (e.getB() == b))   ||   ((e.getA() == b)   &&   (e.getB() == a)) ){
					return e.isHighlighted();
				}
			}
			throw new NoSuchElementException("No known Edge between given vertices");
		} else {
			throw new NoSuchElementException("At least one vertex missing");
		}
	}
	
	
	
	
	public Double getVertexWeight(Vertex a, Vertex b){
		if (vertices.contains(a)   &&   vertices.contains(b)){
			for (Edge e : edges){
				if ( ((e.getA() == a)   &&   (e.getB() == b))   ||   ((e.getA() == b)   &&   (e.getB() == a)) ){
					return e.getWeight();
				}
			}
			return null;
		} else {
			throw new NoSuchElementException("At least one vertex missing");
		}
	}
	
	
	public Double getNodeDistance(Vertex a, Vertex b){
		if (vertices.contains(a)   &&   vertices.contains(b)){
			return calcEdgeWeight(a, b);
		} else {
			throw new NoSuchElementException("At least one vertex missing");
		}
	}
	
	
	
	
	public Edge getDirectedEdge(Vertex a, Vertex b){
		if (vertices.contains(a)   &&   vertices.contains(b)){
			for (Edge e : edges){
				if ( ((e.getA() == a)   &&   (e.getB() == b)) ) {
					return e;
				}
			}
		}
		return null;
	}
	
	
	public ArrayList<Edge> getDirectedEdgesTo (Vertex a){
		ArrayList<Edge> result = new ArrayList<>();
		if (vertices.contains(a)){
			for (Edge e : edges){
				if (e.getB() == a){
					result.add(e);
				}
			}
		} 
		return result;
	}

	
	
	
	private double calcEdgeWeight(Vertex a, Vertex b){
		int colDiff = Math.abs(a.getColVal() - b.getColVal());
		int rowDiff = Math.abs(a.getRowVal() - b.getRowVal());
		return (Math.sqrt((colDiff*colDiff) + (rowDiff*rowDiff))) / VisualGraph.PIXEL_PER_STEP;
	}
	
	
	
	
	public void drawEdge(Graphics2D g2d, double scale, Vertex a, Vertex b, String text){
		if (vertices.contains(a)   &&   vertices.contains(b)){
			Edge e1 = null;
			Edge e2 = null;
			for (Edge e : edges){
				if (e.getA() == a   &&   e.getB() == b){
					e1 = e;
				}
				if (e.getA() == b   &&   e.getB() == a){
					e2 = e;
				}
			}
			if (e1 != null   &&   e2 != null){
				e1.drawEdge(g2d, scale, text);
				e2.drawEdge(g2d, scale, "");
			} else {
				throw new NoSuchElementException("No known edge between given vertices");
			}
		} else {
			throw new NoSuchElementException("At least one vertex missing");
		}
	}
		
}
