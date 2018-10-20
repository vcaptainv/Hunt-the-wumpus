/**
 * File: Hunter.java
 * Author: Yusheng Hu
 * Date: 12/07/2016
 */
 
import java.awt.Graphics;
import java.awt.Color;
import javax.imageio.ImageIO;
import java.util.*;

import java.awt.image.BufferedImage;
import java.io.IOException;
 
 public class Hunter extends Agent{
 		//Stores the current vertex of the hunter
 		private Vertex location;
 				
 		private BufferedImage image;
 		private BufferedImage imageb;
 		private BufferedImage imagec;
 		//three important fields
 		private boolean visible = true;
 		private boolean shoot = false;
 		private boolean live = true;
 		
 		public Hunter(int x0, int y0){
			super(x0,y0);
			this.location = new Vertex(x0,y0);
			try{
				image = ImageIO.read(getClass().getResourceAsStream("/Hunter.jpeg"));
				imageb = ImageIO.read(getClass().getResourceAsStream("/Arrow.png"));
				imagec = ImageIO.read(getClass().getResourceAsStream("/skull.png"));
			}
			catch(IOException e){
				e.printStackTrace();
			}
		}
		//set the hunter's location
		public void setVertex(Vertex v){
			this.location = v;
		}
		//return the hunter's location
		
		public void setLive(boolean a){
			this.live = a;
		}
		
		public boolean getLive(){
			return this.live;
		}
		
		public Vertex getVertex(){
			return this.location;
		}
		//set the visibility of the hunter
		public void setVisible(boolean vis){
			this.visible = vis;
		}
		//return the visibility of the hunter
		public boolean getVisible(){
			return this.visible;
		}
		
		public void setShoot(boolean a){
			this.shoot = a;
		}
		
		public boolean getShoot(){
			return this.shoot;
		}
		
		//draw the hunter
		public void draw(Graphics g, int gridScale){
			if(!this.visible){
				return;
			}
			int xpos = location.getCol()*gridScale;
			int ypos = location.getRow()*gridScale;
			int border = 2;
			int half = gridScale / 2;
			int eighth = gridScale / 8;
			int sixteenth = gridScale / 16;
			if (this.live == true){
				if (this.shoot == false)
					g.drawImage(image, xpos + border, ypos + border, gridScale - 2*border, gridScale - 2 * border, null);
				if (this.shoot== true){
					g.drawImage(imageb, xpos + border, ypos + border, gridScale - 2*border, gridScale - 2 * border, null);
				}
			}
			else {
				g.drawImage(imagec, xpos + border, ypos + border, gridScale - 2*border, gridScale - 2 * border, null);
			}
			
		}
 		
 }