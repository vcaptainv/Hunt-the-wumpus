/**
 * File: Wumpus.java
 * Author: Yusheng Hu
 * Date: 12/07/2016
 */
 import java.awt.Graphics;
 import java.awt.Color;
 import java.util.*;

 import javax.imageio.ImageIO;

 import java.awt.image.BufferedImage;
 import java.io.IOException;
 
 
 
 public class Wumpus extends Agent{
	private Vertex home;
	private boolean life;
	private boolean visible;
	private BufferedImage image;
	private BufferedImage imagea;
	
	public Wumpus(int x0,int y0){
		super(x0,y0);
		this.home = new Vertex(x0,y0);
		this.life = true;
		this.visible = false;
		try{
			image = ImageIO.read(getClass().getResourceAsStream("/Wumpus.jpeg"));
			imagea = ImageIO.read(getClass().getResourceAsStream("/dead.png"));
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
	
	//set the location of the wumpus
	public void setVertex(Vertex p){
		this.home = p;
	}
	//return the location of the wumpus
	public Vertex getVertex(){
		return this.home;
	}
	//set whether the wumpus is alive
	public void setAlive(boolean a){
		this.life = a;
	}
	//check whether is wumpus is alive
	public boolean getAlive(){
		return this.life;
	}
	//set the visibility of the wumpus
	public void setVisible(boolean vis){
		this.visible = vis;
	}
	//return the visibility of the wumpus
	public boolean getVisible(){
		return this.visible;
	}
	
	
	//draw the wumpus
	public void draw(Graphics g, int gridScale){
		if(!this.visible){
			return;
		}	
		int xpos = home.getCol()*gridScale;
		int ypos = home.getRow()*gridScale;
		int border = 2;
		int half = gridScale / 2;
		int eighth = gridScale / 8;
		int sixteenth = gridScale / 16;
		if (life == true)	
			g.drawImage(image, xpos + border, ypos + border, gridScale - 2*border, gridScale - 2 * border, null);
		if (life == false)
			g.drawImage(imagea, xpos + border, ypos + border, gridScale - 2*border, gridScale - 2 * border, null);
	
	}		
		
  	
}