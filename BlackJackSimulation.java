/*
* File Name: BlackJackSimulation.java
* Updated By: Adam Carlson
* Date: 11/21/15 
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
import sun.audio.*;
import java.io.*;
import java.util.Random;

// initializes the simulation
public class BlackJackSimulation
{
	private Hand hand; //stores the player hand
	private DealerHand dHand; // stores the dealer hand
	private Deck deck; //stores the deck
	private Asthetics asthetics; // stores the asthetics
	private Landscape landscape;
	private LandscapeDisplay display;
	private JLabel textMessage;
	private JLabel textMessage2;
	
	// controls whether the simulation is playing, paused, or exiting
	private enum PlayState { PLAY, PAUSE, STOP }
	private PlayState state;
		
	// simulation control
	private int iteration;
	// the number of milliseconds to pause between iterations.
	private int pause;
	
	// initialize all the needed components of the game
	public BlackJackSimulation()
	{
		this.pause = 1000;
		
		
		this.landscape = new Landscape(400, 400);
		
		
		// create the display
		if (this.display != null)
			this.display.dispose();
		this.display = new LandscapeDisplay(landscape, 2);

		this.state = PlayState.PLAY;
		
		
		Random rand = new Random();
		
	
		this.hand = new Hand();
		this.dHand = new DealerHand();
		this.deck = new Deck();
		this.asthetics = new Asthetics();
		this.landscape.addDeck( this.deck );
		this.landscape.addAgent( this.asthetics );
		this.deck.initialize();
		this.deck.shuffle();
		this.dHand.addCard(this.deck.pop(), this.landscape);
		this.hand.addCard( this.deck.pop(), this.landscape);
		this.hand.addCard( this.deck.pop(), this.landscape);
		this.landscape.addAgent( this.hand );
		this.landscape.addAgent( this.dHand );
		
		
		
	
		
		this.setupUI();
	}
	


	
	
	/**
	 * Sets up the UI controls for the elevator simulation.
	 */
	private void setupUI()
	{
		// add elements to the UI
		this.textMessage = new JLabel("Your Score: "+this.hand.getScore() + " | ");
		this.textMessage2 = new JLabel("Dealer Score: "+this.dHand.getScore());
		JButton hit = new JButton("Hit");
		JButton stay = new JButton("Stay");
		JButton replay = new JButton("Replay");
		JButton quit = new JButton("Quit");
		
		
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		panel.add(this.textMessage);
		panel.add(this.textMessage2);
		panel.add(hit);
		panel.add(stay);
		panel.add(replay);
		panel.add(quit);
		
		
		this.display.add(panel, BorderLayout.SOUTH);
		this.display.pack();
		
		// listen for keystrokes
		Control control = new Control();
		replay.addActionListener(control);
		quit.addActionListener(control);
		hit.addActionListener(control);
		stay.addActionListener(control);
		this.display.addKeyListener(control);
		this.display.setFocusable(true);
		this.display.requestFocus();
	}
	
	/**
	 * Implements one iteration (time step) of the elevator simulation.
	 */
	public void iterate()
	{
		this.iteration++;

		if (this.state == PlayState.PLAY)
		{
			
			
			// update the landscape, display
			this.landscape.advance();
			this.display.repaint();
		
			
			
		}

		// pause for refresh
		try
		{
			Thread.sleep(this.pause);
		}
		catch (InterruptedException ie)
		{
			// do threads get insomnia?
			ie.printStackTrace();
		}
	}
	
		/**
	 * Provides simple keyboard control to the simulation by implementing
	 * the KeyListener interface.
	 */
	private class Control extends KeyAdapter implements ActionListener
	{
	

		/**
		 * Controls the simulation in response to key presses.
		 */
		public void keyTyped(KeyEvent e)
		{
	
				
					 
			if (("" + e.getKeyChar()).equalsIgnoreCase("p") && 
				state == PlayState.PLAY)
			{
				state = PlayState.PAUSE;
				System.out.println("*** Simulation paused ***");
			}
			else if (("" + e.getKeyChar()).equalsIgnoreCase("p") && 
				state == PlayState.PAUSE)
			{
				state = PlayState.PLAY;
				System.out.println("*** Simulation resumed ***");
			}
			else if (("" + e.getKeyChar()).equalsIgnoreCase("q"))
			{
				state = PlayState.STOP;
				System.out.println("*** Simulation ended ***");
			}
		
			
					
					
		
		

			    
		}
		


		public void actionPerformed(ActionEvent event)
		{
			System.out.println(event.getActionCommand());
			
			// resets all the components of the game
			if (event.getActionCommand().equalsIgnoreCase("Replay") &&
				state == PlayState.PLAY)
			{
				landscape.reset();
				hand = new Hand();
				dHand = new DealerHand();
				deck = new Deck();
				asthetics = new Asthetics();
				landscape.addDeck( deck );
				landscape.addAgent( asthetics );
				deck.initialize();
				deck.shuffle();
				dHand.addCard(deck.pop(), landscape);
				hand.addCard( deck.pop(), landscape);
				hand.addCard( deck.pop(), landscape);
				landscape.addAgent(hand );
				landscape.addAgent( dHand );
				hand.setTurn(true);
				dHand.setTurn(false);
				asthetics.setResult("none");
				textMessage.setText("Your Score: "+hand.getScore() + " | ");
			}
			else if (event.getActionCommand().equalsIgnoreCase("Play") &&
				state == PlayState.PAUSE)
			{
				state = PlayState.PLAY;
				((JButton) event.getSource()).setText("Pause");
			}
			else if (event.getActionCommand().equalsIgnoreCase("Quit"))
			{
				state = PlayState.STOP;
			}
			// adds a card to player hand unless he has already busted
			else if (event.getActionCommand().equalsIgnoreCase("Hit"))
			{
				if(hand.getTurn()){			
					hand.addCard( deck.pop(), landscape);
					textMessage.setText("Your Score: "+hand.getScore() + " | ");
					if(hand.getScore() > 21){
						asthetics.setResult("pBust");
						hand.setTurn(false);
					}
				}
			}
			// begins the dealer's turn unless player has already lost due to a bust
			else if (event.getActionCommand().equalsIgnoreCase("Stay"))
			{
				if(! (asthetics.getResult().equals("pBust"))){
					hand.setTurn(false);
					dHand.setTurn(true);
				}
			}
		}	
	}

	/**
	 * Provides simple keyboard control to the simulation by implementing
	 * the KeyListener interface.
	 */
		/*
	private class Control extends KeyAdapter
	{
		public void keyTyped(KeyEvent e)
		{
			if (("" + e.getKeyChar()).equalsIgnoreCase("p") && 
				state == PlayState.PLAY)
			{
				state = PlayState.PAUSE;
				System.out.println("*** Simulation paused ***");
			}
			else if (("" + e.getKeyChar()).equalsIgnoreCase("p") && 
				state == PlayState.PAUSE)
			{
				state = PlayState.PLAY;
				System.out.println("*** Simulation resumed ***");
			}
			else if (("" + e.getKeyChar()).equalsIgnoreCase("q"))
			{
				state = PlayState.STOP;
				System.out.println("*** Simulation ended ***");
			}
		}	
	}
*/
	// runs the simulation
	public static void main(String[] args) throws IOException
	{
	
		
		// initialize the simulation
		BlackJackSimulation sim = new BlackJackSimulation();
		sim.pause = 500;
	
	

	

	

		System.out.println("Init => " + sim);
		
		// run simulation until terminated
		while (sim.state != PlayState.STOP)
		{
			sim.iterate();
			sim.textMessage2.setText("Dealer score: "+ sim.dHand.getScore());
		}
		
		
		sim.display.dispose();
	}



}
