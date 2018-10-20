/**
 * File: Vertex.java
 * Author: Yusheng Hu
 * Date: 12/07/2016
 */
import java.util.ArrayList;
import java.util.Collection;
import java.awt.Color;
import java.awt.Graphics;
import java.util.HashMap;
import java.util.Comparator;

public class Vertex extends Agent{
	public HashMap<Direction, Vertex> map;
	private int cost;
	private boolean visible;
	public enum Direction{North, East, South, West;}
	public Vertex(int r, int c){
		super(r,c);
		this.map= new HashMap<Direction, Vertex>();
		this.visible=false;
	}
//Define a public static method in Vertex with signature public Direction opposite(Direction d) 
//that returns the compass opposite of a direction (i.e. South for North...). 

	public static Direction oppo(Direction d){
		switch(d){
			case North:
				return Direction.South;
			case South:
				return Direction.North;
			case East:
				return Direction.West;
			case West:
				return Direction.East;
			default:
				System.out.println("unknown direction");
				return null;
		
		}
	
	
	}
	
	public void connect(Vertex other, Direction dir){
		this.map.put(dir,other);
	}
	
	
	public Vertex getNeighbor(Direction dir){
		return (Vertex)this.map.get(dir);
	}

	public Collection<Vertex> getNeighbors(){
		return this.map.values();
	}
	
	public int getCost(){return this.cost;}
	public void setCost(int c){this.cost=c;}
	//set the visibility of the wumpus
	public void setVisible(boolean vis){
		this.visible = vis;
	}
	//return the visibility of the wumpus
	public boolean getVisible(){
		return this.visible;
	}
	
	public String toString(){
		int c= this.getNeighbors().size();
		System.out.println("there are "+ c +" neighbors");
		System.out.println("the cost is "+ this.cost);
		System.out.println("the marked value is "+ this.visible);
		String a= "there are"+ c +"neighbors"+"\n"+"the cost is"+ this.cost+"\n"+"the marked value is"+ this.visible;
		return a;	
	}
	
	public void draw(Graphics g, int gridScale ){
		if (!this.visible)
            return;
        int xpos = this.getCol()*gridScale;
        int ypos = this.getRow()*gridScale;
        int border = 2;
        int half = gridScale / 2;
        int eighth = gridScale / 8;
        int sixteenth = gridScale / 16;
        
        // draw rectangle for the walls of the cave
        if (this.cost <= 2)
            // wumpus is nearby
            g.setColor(Color.red);
        else
            // wumpus is not nearby
            g.setColor(Color.black);
        
        g.drawRect(xpos + border, ypos + border, gridScale - 2*border, gridScale - 2 * border);
        
        // draw doorways as boxes
        g.setColor(Color.black);
        if (this.map.containsKey(Direction.North))
            g.fillRect(xpos + half - sixteenth, ypos, eighth, eighth + sixteenth);
        if (this.map.containsKey(Direction.South))
            g.fillRect(xpos + half - sixteenth, ypos + gridScale - (eighth + sixteenth), eighth, eighth + sixteenth);
        if (this.map.containsKey(Direction.West))
            g.fillRect(xpos, ypos + half - sixteenth, eighth + sixteenth, eighth);
        if (this.map.containsKey(Direction.East))
            g.fillRect(xpos + gridScale - (eighth + sixteenth), ypos + half - sixteenth, eighth + sixteenth, eighth);
	}
	
	public static void main( String[] args ) {
		Vertex a= new Vertex(1,2);
		Vertex b= new Vertex(1,3);
		Vertex c= new Vertex(2,3);
		a.setCost(10);

		//System.out.println(a.oppo(Direction.North));
		a.connect(b,Direction.East);
		b.connect(c,Direction.South);
		c.connect(b,Direction.North);
		a.toString();
		b.toString();
		c.toString();
	}
}

