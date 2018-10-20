/**
 * File: Graph.java
 * Author: Yusheng Hu
 * Date: 12/09/2016
 */
 
import java.util.ArrayList;
import java.util.Comparator;
import java.util.*;

public class Graph{
	public ArrayList<Vertex> graph;
	
	public Graph(){
		this.graph = new ArrayList<Vertex>();
	}
	//return the size of the graph 
	public int vertexCount(){
		return graph.size();
	
	}
	//connect two Vertexes with one direction and an opposite direction
	public void addEdge(Vertex v1, Vertex.Direction dir, Vertex v2){
		if(!this.graph.contains(v1)){	
			this.graph.add(v1);
		}
		if(!this.graph.contains(v2)){	
			this.graph.add(v2);
		}
		v1.connect(v2,dir);
		v2.connect(v1,v1.oppo(dir));
	}
	//add a new vertex to the graph
	public void addVertex(Vertex v){
		graph.add(v);
	}
	//Find the shortest distance from the all other vertexes to v0
	public void shortestPath(Vertex v0){
		for (Vertex a: graph){
			a.setCost(Integer.MAX_VALUE);
		}
		PriorityQueue<Vertex> q = new PriorityQueue<Vertex>(new VertexComparator());
		v0.setCost(0);
		q.add(v0);
		
		while(q.size()!=0){
			Vertex v=q.remove();
			for (Vertex w: v.getNeighbors()){
				if (w.getVisible()==false && v.getCost()+1< w.getCost()){
					w.setCost(v.getCost()+1);
					q.add(w);
				
				}
			}
		}
	}
	
	
	
	public static void main( String[] args ) {

	}
}

//place the least on the top
class VertexComparator implements Comparator<Vertex> {
    public int compare( Vertex i1, Vertex i2 ) {
        float diff = i1.getCost() - i2.getCost();
        if (diff == 0.0)
            return 0;
        if (diff < 0.0)
            return 1;
        else
            return -1;
    }
}

