/**
 * File: HuntTheWumpus.java
 * Author: Yusheng Hu
 * Date: 12/07/2016
 */
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.util.*;

public class HuntTheWumpus{
	private Landscape landscape;
	private LandscapeDisplay display;
	public enum State {PLAY, PAUSE, QUIT, END}
	private Graph graph;
	private State state;
	private Hunter hunter;
	private Wumpus wumpus;
	private int height;
	private int width;
	private Vertex[][] vgrid;
	private boolean shoot;
	public JLabel message;
	
	//Constructor
	public HuntTheWumpus(int width, int height){
		//Initiate all the fields
		this.message = new JLabel("Game Start");
		this.height = height;
		this.width = width;
		this.graph = new Graph();
		this.vgrid = new Vertex[width][height];
		this.landscape = new Landscape(height, width);
		this.display = new LandscapeDisplay(landscape, 64);
		
		if (this.display != null){
			this.display.dispose();
		}
		this.state = State.PLAY;
		this.shoot = false;
		this.generate();
		
		//make sure that the hunter is not very near from the wumpus
		while(this.hunter.getVertex().getCost()<=3 ){
			this.landscape.reset();
			this.generate();
			this.graph.shortestPath(this.wumpus.getVertex());
		}
		
		this.setupUI();
		display.setVisible(true);	
			
	}
	
	public void setShoot(boolean s){
		this.hunter.setShoot(s);
	}
	//return whether the hunter has shot the arrow
	public boolean getShoot(){
		return this.hunter.getShoot();
	}
	//return the state of the game
	public State getState(){
		return this.state;
	}
	
	//reset the whole game
	//same as the constructor
	public void reset(){
		if (this.display != null){
			this.display.dispose();
		}
		this.graph = new Graph();
		this.vgrid = new Vertex[width][height];
		this.landscape = new Landscape(height,width);
		this.display = new LandscapeDisplay(landscape, 64);
		this.state = State.PLAY;
		this.shoot = false;
		this.generate();
		this.graph.shortestPath(this.wumpus.getVertex());
		while(this.hunter.getVertex().getCost()<=3 ){
			this.landscape.reset();
			this.generate();
			this.graph.shortestPath(this.wumpus.getVertex());
		}
		this.wumpus.setVisible(false);
		this.setupUI();
	}
	
	//Add all the vertexes, hunter, and wumpus inside the landscape
	public void generate(){
		Random r = new Random();
		for (int i = 0; i < width; i++){
			for (int j = 0; j < height; j++){
				this.vgrid[i][j] = new Vertex(i,j);
				vgrid[i][j].setVisible(false);
			}
		}
		for (int i = 0; i < height; i++){
			for (int j = 0; j < width; j++){
				if(i != width-1){
					graph.addEdge(vgrid[i][j], Vertex.Direction.South, vgrid[i+1][j]);
				}
				if (j != height-1){
					graph.addEdge(vgrid[i][j], Vertex.Direction.East, vgrid[i][j+1]);
				}
			}
		
		}
		
		for (int i = 0; i < width; i++){
			for (int j = 0; j < height; j++){
				landscape.addVertex(vgrid[i][j]);
			}
		}
		ArrayList<Vertex> list = landscape.getVertexes();
		Random ran = new Random();
 		this.wumpus = new Wumpus(3,3);
		Vertex wu = list.get(ran.nextInt(list.size()-1));
 		wumpus.setVertex(wu);
 		this.hunter = new Hunter(0,0);
 		Vertex hu = list.get(ran.nextInt(list.size()-1));
 		hunter.setVertex(hu);
 		hu.setVisible(true);
 		
 		this.landscape.addAgent(wumpus);
 		this.landscape.addAgent(hunter);
 		this.graph.shortestPath(this.wumpus.getVertex());
	}
	
	private void setupUI(){
		//set up the user interface
		this.message = new JLabel("Game Start");
		//adds the bottons
		JButton pause = new JButton("Pause");
		JButton quit = new JButton("Quit");
		JButton resume = new JButton("Resume");
		JButton replay  = new JButton("Replay");
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		panel.add(this.message);
		panel.add(pause);
		panel.add(resume);
		panel.add(quit);
		panel.add(replay);
		this.display.add(panel, BorderLayout.SOUTH);
		this.display.pack();
		Control control = new Control();
		//pass bottons to the control class
		pause.addActionListener(control);
		quit.addActionListener(control);
		replay.addActionListener(control);
		resume.addActionListener(control);
		this.display.addKeyListener(control);
		this.display.setFocusable(true);
		this.display.requestFocus();

	}
	
	public void iterate()
	{
		//this.iteration++;
		if (this.state == State.PLAY)
		{			
			this.display.repaint();
		}
		else if(this.state == State.QUIT){
			System.exit(0);
		}
		try
		{
			Thread.sleep(100);
		}
		catch (InterruptedException ie)
		{
			ie.printStackTrace();
		}
	}
	
	
	
	
	//control class to handle key action and buttons action
	private class Control extends KeyAdapter implements KeyListener, ActionListener{
		//keys' actions
		public void keyPressed(KeyEvent e){
			//Some basic keyboard command
			Vertex hpos = hunter.getVertex();
			Vertex wpos = wumpus.getVertex();			
			if (("" + e.getKeyChar()).equalsIgnoreCase("p") && state == State.PLAY){
				state = State.PAUSE;
				System.out.println("PAUSE");
			}
			if (("" + e.getKeyChar()).equalsIgnoreCase("p") && state == State.PAUSE){
				state = State.PLAY;
				System.out.println("RESUME");
			}
			if (("" + e.getKeyChar()).equalsIgnoreCase("q")){
				state = State.QUIT;
				System.out.println("QUIT");
			}
			
			//The game will continue if the game state is play
			if (state == State.PLAY){
				// if the shoot is false, then the hunter will definitely not be moving
				// move command
				if(getShoot() == false){
					if (("" + e.getKeyChar()).equalsIgnoreCase("w")){
						if(hpos.getNeighbor(Vertex.Direction.North)!=null){
							Vertex v = hpos.getNeighbor(Vertex.Direction.North);
							hunter.setVertex(v);
							
							v.setVisible(true);
							message.setText("Go North");
							display.repaint();
						}
					}
					else if (("" + e.getKeyChar()).equalsIgnoreCase("s")){
						if(hpos.getNeighbor(Vertex.Direction.South)!=null){
							Vertex v = hpos.getNeighbor(Vertex.Direction.South);
							hunter.setVertex(v);
							v.setVisible(true);
							message.setText("Go South");
							display.repaint();
					
						}
					}
					else if (("" + e.getKeyChar()).equalsIgnoreCase("d")){
						if(hpos.getNeighbor(Vertex.Direction.East)!=null){
							Vertex v = hpos.getNeighbor(Vertex.Direction.East);
							hunter.setVertex(v);
							v.setVisible(true);
							message.setText("Go East");
							display.repaint();
					
						}
					}
					else if (("" + e.getKeyChar()).equalsIgnoreCase("a")){
						if(hpos.getNeighbor(Vertex.Direction.West)!=null){
							Vertex v = hpos.getNeighbor(Vertex.Direction.West);
							hunter.setVertex(v);
							v.setVisible(true);
							message.setText("Go West");
							display.repaint();
						}
					}
					else if (("" + e.getKeyChar()).equalsIgnoreCase(" ")){
						setShoot(true);
						message.setText("Ready to Shoot");
					}					
					if(hpos.getCost() == 0){
						System.out.println("You are dead");
						wumpus.setVisible(true);
						message.setText("You enter the room of Wumpus!!");
						hunter.setLive(false);
						display.repaint();
						state = State.END;
					
					}
				}
				//if shoot is true, the hunter is in shooting mode
				//The next move is the movement of the arrow
				//The game will end whether the arrow hits or misses.
				else{
					if (("" + e.getKeyChar()).equalsIgnoreCase("w")){
						if(hpos.getNeighbor(Vertex.Direction.North) == wumpus.getVertex()){
							wumpus.setVisible(true);
							wumpus.setAlive(false);
							message.setText("You killed the Wumpus!!!");
							display.repaint();
							state = State.END;
						}
						else{
							wumpus.setVisible(true);
							message.setText("You missed the shoot!!");
							hunter.setLive(false);
							display.repaint();
							state = State.END;
						}
					}
					else if (("" + e.getKeyChar()).equalsIgnoreCase("d")){
						if(hpos.getNeighbor(Vertex.Direction.East) == wumpus.getVertex()){
							wumpus.setVisible(true);
							message.setText("You killed the Wumpus!!!");
							wumpus.setAlive(false);
							display.repaint();
							state = State.END;
						}
						else{
							wumpus.setVisible(true);
							message.setText("You missed the shoot!!");
							hunter.setLive(false);
							display.repaint();
							state = State.END;
						}
					}					
					else if (("" + e.getKeyChar()).equalsIgnoreCase("s")){				
						if(hpos.getNeighbor(Vertex.Direction.South) == wumpus.getVertex()){
							wumpus.setVisible(true);
							message.setText("You killed the Wumpus!!!");
							wumpus.setAlive(false);
							wumpus.setVisible(true);
							display.repaint();
							state = State.END;
						}
						else{
							wumpus.setVisible(true);
							message.setText("You missed the shoot!!");
							hunter.setLive(false);
							display.repaint();
							state = State.END;
						}
					}
					else if (("" + e.getKeyChar()).equalsIgnoreCase("a")){
						if(hpos.getNeighbor(Vertex.Direction.West) == wumpus.getVertex()){
							wumpus.setVisible(true);
							message.setText("You killed the Wumpus!!!");
							wumpus.setAlive(false);
							display.repaint();
							state = State.END;
						}
						else{
							wumpus.setVisible(true);
							message.setText("You missed the shoot!!");
							hunter.setLive(false);
							display.repaint();
							state = State.END;
						}
					}					
					//double press space to disarm
					else if (("" + e.getKeyChar()).equalsIgnoreCase(" ") && state == State.PLAY){
						setShoot(false);
						message.setText("You have disarmed; Continue your search");
					}
				}
			}	
		}		
	
		//Controls the botton action
		public void actionPerformed(ActionEvent event){
			System.out.println(event.getActionCommand());
			
			if (event.getActionCommand().equalsIgnoreCase("Pause") &&
				state == State.PLAY){
				state = State.PAUSE;
				System.out.println(state);
			}
			if (event.getActionCommand().equalsIgnoreCase("Resume") &&
				state == State.PAUSE){
				state = State.PLAY;
				display.requestFocus();
				System.out.println(state);
			}
			
			if (event.getActionCommand().equalsIgnoreCase("Quit")){
				state = State.QUIT;
			}
			
			if (event.getActionCommand().equalsIgnoreCase("Replay")){
				state = State.PLAY;
				reset();
				display.requestFocus();
			}
			
		}	
	}
	
	//main function
	public static void main(String[] args) {	
		int row = Integer.parseInt(args[0]);
		int col = Integer.parseInt(args[1]);
		HuntTheWumpus sim = new HuntTheWumpus(row,col);

		while (sim.getState() != State.QUIT)
		{
			//System.out.println("iterate");
			sim.iterate();
			
		}
		sim.display.dispose();
	}
	
	
	
	




} 
 
