/**
 * File: Agent.java
 * Author: Yusheng Hu
 * Date: 10/23/2016
 */
 import java.awt.Graphics;
 
 public class Agent{
	//Stores the position of Agent
	private int row;
	private int col;
	
	
	//Constructor, assign field to the input 
	public Agent(int r, int c){
		this.row = r;
		this.col = c;
	}
 	//return the row 
 	public int getRow(){
 		return this.row;
 	}
 	//return the col
 	public int getCol(){
 		return this.col;
 	}
 	
 	public void setPosition(int tx, int ty) {
		this.row = ty;
		this.col = tx;
	}
 	
 	// assign row field to new double
 	public void setRow( int newRow ){
 		this.row = newRow;
 	}
 	
 	// assign col field to new double
 	public void setCol( int newCol ){
 		this.col  = newCol;
 	}
 	// print the position out
 	public String toString(){
 		String a = "(" + this.row +" ," + this.col + " )";
 		return a;
 	}
 	//It will be covered by the function in the Gatherer
 	// public void updateState( Landscape scape ){
//  		;
//  	}
 	
 	//It will be covered by the function in the mushroom
 	public void draw(Graphics g, int a){
 		;
 	}
 	
 	public static void main(String[] argv) {
 		Agent a = new Agent(10, 10);
 		System.out.println(a.toString());
 		a.setRow(5);
 		a.setCol(5);
 		System.out.println(a.toString());
 	}
 }