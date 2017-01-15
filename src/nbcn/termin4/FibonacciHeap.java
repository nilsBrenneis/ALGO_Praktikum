package nbcn.termin4;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import nbcn.termin4.graph.Vertex;

public final class FibonacciHeap {

    private Vertex min;
    private int size;
    private final String noVertices = "No vertices in heap.";

    
    public FibonacciHeap(){
    	min = null;
    	size = 0;
    }
    
    
    
    public void insert(Vertex vertex) {
    	if (vertex == null){
    		throw new UnsupportedOperationException("Can't insert null.");
    	}
    	
    	if (min == null){
    		min = vertex;
    	} else {
            Vertex minNext = min.getNext();
            min.setNext(vertex.getNext());
            min.getNext().setPrev(min);
            vertex.setNext(minNext);
            vertex.getNext().setPrev(vertex);
        
            if (min.getKey() >= vertex.getKey()){
            	min = vertex;
            }
        }
        
        size++;
    }



    public void delete(Vertex vertex) {
	    decreaseKey(vertex, Double.NEGATIVE_INFINITY);
	    extractMin();
	}



	public Vertex getMin() {
        if (isEmpty())
            throw new NoSuchElementException(noVertices);
        return min;
    }


    
    public boolean isEmpty() {
        return min == null;
    }



    public int size() {
        return size;
    }


    
    public static FibonacciHeap uniteHeaps(FibonacciHeap one, FibonacciHeap two) {
    	if (one.isEmpty()   &&   two.isEmpty()){
    		throw new UnsupportedOperationException("Can't merge two empty heaps");
    	} else if (one.isEmpty()   &&   ! two.isEmpty()){
    		return two;
    	} else if ( ! one.isEmpty()   &&   two.isEmpty() ){
    		return one;
    	} else {
    		 FibonacciHeap union = new FibonacciHeap();
    		
             Vertex oneNext = one.min.getNext();
             one.min.setNext(two.min.getNext());
             one.min.getNext().setPrev(one.min);
             two.min.setNext(oneNext);
             two.min.getNext().setPrev(two.min);
             
             if (one.min.getKey() < two.min.getKey()){
            	 union.min = one.min;
             } else {
            	 union.min = two.min;
             }
             
             union.size = one.size + two.size;

             one.size = two.size = 0;
             one.min  = two.min = null;
             
             return union;
    	}
    }


    
    public Vertex extractMin() {
        if (isEmpty()){
            throw new NoSuchElementException(noVertices);
        }
        
        size--;

        Vertex tempMin = min;

        if (min.getNext() == min) { 
            min = null;
        }
        else {
        	min.getPrev().setNext(min.getNext());
            min.getNext().setPrev(min.getPrev());
            min = min.getNext();
        }

        if (tempMin.getChild() != null) {
            Vertex tempChild = tempMin.getChild();
            do {
                tempChild.setParent(null);
                tempChild = tempChild.getNext();
            } while (tempChild != tempMin.getChild());
        }
        
        if (min == null && tempMin.getChild() == null) { 
            min = null;
        } else if (min == null && tempMin.getChild() != null) {
            min =  tempMin.getChild();
        } else if (min != null && tempMin.getChild() == null) {
        	
        } else {
            Vertex oneNext = min.getNext();
            min.setNext(tempMin.getChild().getNext());
            min.getNext().setPrev(min);
            tempMin.getChild().setNext(oneNext);
            tempMin.getChild().getNext().setPrev(tempMin.getChild());
        
            if (min.getKey() >= tempMin.getChild().getKey()){
            	min = tempMin.getChild();
            }
        }
        
        if (min == null){
        	return tempMin;
        }

        ArrayList<Vertex> tempList = new ArrayList<>();
        ArrayList<Vertex> rootList = new ArrayList<>();

        Vertex runner = min;
        do {
        	rootList.add(runner);
        	runner = runner.getNext();
        } while (runner != min);
        
        for (Vertex curr: rootList) {
            while (true) {
                while (curr.getDegree() >= tempList.size())
                    tempList.add(null);

                if (tempList.get(curr.getDegree()) == null) {
                    tempList.set(curr.getDegree(), curr);
                    break;
                }

                Vertex other = tempList.get(curr.getDegree());
                tempList.set(curr.getDegree(), null);

                Vertex min = (other.getKey() < curr.getKey())? other : curr;
                Vertex max = (other.getKey() < curr.getKey())? curr  : other;

                max.getNext().setPrev(max.getPrev());
                max.getPrev().setNext(max.getNext());

                max.setNext(max);
                max.setPrev(max);
                           
                if (min.getChild() == null   &&   max != null){
                	min.setChild(max);
                } else if (min.getChild() != null   &&   max == null){
                	
                } else {
                    Vertex oneNext = min.getChild().getNext();
	                min.getChild().setNext(max.getNext());
	                min.getChild().getNext().setPrev(min.getChild());
	                max.setNext(oneNext);
	                max.getNext().setPrev(max);
	                
	                if ( min.getChild().getKey() < max.getKey()){
	                	min.setChild(min.getChild());
	                } else {
	                	min.setChild(max);
	                }
                }

                max.setParent(min);
                max.setMarked(false);

                min.incDegree();

                curr = min;
            }
            if (curr.getKey() <= min.getKey()) min = curr;
        }
        return tempMin;
    }

    
    
    public void decreaseKey(Vertex vertex, double newKey) {
    	if (newKey > vertex.getKey()){
	            throw new IllegalArgumentException("Key value isn't allowed to go UP");
		}

		vertex.setKey(newKey);
	    
		if (vertex.getParent() != null   &&   (vertex.getKey() <= vertex.getParent().getKey()) )
	        cutNode(vertex);

		if (vertex.getKey() <= min.getKey())
	        min = vertex;
	}


    
    private void cutNode(Vertex vertex) {
        vertex.setMarked(false);

        if (vertex.getParent() == null){
        	return;
        }

        if (vertex.getNext() != vertex) {
        	vertex.getNext().setPrev(vertex.getPrev());
            vertex.getPrev().setNext(vertex.getNext());
        }

        if (vertex.getParent().getChild() == vertex) {
            if (vertex.getNext() != vertex) {
                vertex.getParent().setChild(vertex.getNext());
            } else {
                vertex.getParent().setChild(null);
            }
        }

        vertex.getParent().decDegree();

        vertex.setPrev(vertex);
        vertex.setNext(vertex);
        
   
        Vertex minNext = min.getNext();
        min.setNext(vertex.getNext());
        min.getNext().setPrev(min);
        vertex.setNext(minNext);
        vertex.getNext().setPrev(vertex);
        
        if (min.getKey() >= vertex.getKey()){
        	min = vertex;
        }

        if (vertex.getParent().isMarked()){
            cutNode(vertex.getParent());
        } else {
        	vertex.getParent().setMarked(true);
        }

        vertex.setParent(null);
    }
}