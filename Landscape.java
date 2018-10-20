/**
 * File: Landscape.java
 * Author: Yusheng Hu
 * Date: 09/24/2016
 */
// import graphics and arraylist
import java.awt.Graphics;
import java.util.*;
 
 
 public class Landscape{
 	// rows and cols will store the rows and columns of the landscape array
 	private int rows;
 	private int cols;
 	private Random gen;
    
    private LinkedList<Agent> foreground;
    private LinkedList<Vertex> background;
 	
 	
 	public Landscape( int rows, int cols){ 		
 		//store the value of rows and cols to the field
 		this.rows = rows;
 		this.cols = cols;
 		gen = new Random();
 		//forergound: wumpus and hunter
 		//background: vertexes
 		foreground = new LinkedList<Agent>();
 		background = new LinkedList<Vertex>();
        	
 	}
 	
 	//Return the number of rows in the landscape
 	public int getRows(){
 		return this.rows;
 	}
 	
 	//Return the number of columns in the landscape
 	public int getCols(){
 		return this.cols;
 	}
 	
 	public void addAgent(Agent a) {
		foreground.add(a);
	}
	
	public void addVertex(Vertex a) {
		background.add(a);
	}
	
 	
 	public ArrayList<Vertex> getVertexes() {
		return background.toArrayList();
	}
	
	public void reset() {
		foreground.clear();
	}
	 
	
	//draw all of the Agent in the grid.
	
	public void draw( Graphics g, int gridScale){
        for (Agent a: foreground){
        	a.draw(g, gridScale);
        }
        
        for (Vertex b: background){
        	//System.out.println("draw");
        	b.draw(g, gridScale);
        }
        	
    }
	
 	public static void main(String[] args){

 	}
 }
 