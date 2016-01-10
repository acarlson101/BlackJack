/*
* File Name: DealerHand.java
* Author: Adam Carlson
* Date: 11/21/15 
*/

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

// models the dealer's hand in the game
public class DealerHand extends Cell{

	private MyArrayList hand; // stores the list of dealer cards
	private boolean dealerTurn; // stores whether it is the dealer's turn
	private Card faceDown; // stores the dealer's facedown card
	private int counter; // counter in order to add face down card to hand only once
	
	// initializes the dealer hand
	public DealerHand(){
		super(20,100);
		this.hand = new MyArrayList();
		this.dealerTurn = false;
		Suit[] suits = Suit.values();
		Random rand = new Random();
		Suit s = suits[rand.nextInt(3)];
		this.faceDown = null;
		this.counter = 0;
	}
	
	// sets whether it is the dealer's turn
	public void setTurn(boolean b){
		this.dealerTurn = b;
	}
	
	// adds a card to the dealer hand
	public void addCard(Card c, Landscape scape){
		if(dealerTurn){
			c.setPosition(40+(this.hand.size()-1)*20, 100);
		}
		else{
			c.setPosition(40+this.hand.size()*20, 100);
		}
		this.hand.add(c);
		scape.addAgent(c);
		
	}
	
	// returns the dealer's score
	public int getScore(){
		//ensure you don't bust from two initial aces
		if(this.hand.size() == 2){
			Card c = (Card)this.hand.get(0);
			Card c2 = (Card)this.hand.get(1);
			if(c.getValue() + c2.getValue() == 22){
				return 21;
			}
		}
		int score = 0;
		for(int i = 0; i<this.hand.size(); i++){
			Card c = (Card) this.hand.get(i);
			score += c.getValue();
		}
		return score;
	}
	
	// updates the dealer's hand 
	public void updateState(Landscape scape){
		if(this.dealerTurn){
			if(this.counter == 0){
				this.faceDown = scape.getDeck().pop();
				this.hand.add(this.faceDown);
				scape.addAgent(this.faceDown);
				faceDown.setPosition(20,100);
				this.counter++;
			}
			// adds cards until greater than or equal to 17
			if(this.getScore() < 17){
				this.addCard( scape.getDeck().pop(), scape );
			}
			// see who wins
			else if(this.getScore() >= 17 && this.getScore() <= 21){
				if(this.getScore() > scape.getHand().getScore()){
					scape.getAsthetics().setResult("dWin");
				}
				else if(this.getScore() < scape.getHand().getScore()){
					scape.getAsthetics().setResult("pWin");
				}
				else{
					scape.getAsthetics().setResult("tie");
				}
			}
			// dealer busts
			else{
				scape.getAsthetics().setResult("dBust");
			}
		}
	}
	
	// draws the dealer's facedown card if it is player's turn
	public void draw(Graphics g, int x0, int y0, int scale){
		int x = x0 + (int)(this.getX() * scale);
		int y = y0 + (int)(this.getY() * scale);
		
		if(! dealerTurn){
			g.setColor(new Color(1.0f, 0.0f, 0.0f));
			g.fillRect(x, y, 10*scale, 20*scale);
		}
		
		
		
		
		return;
	}
	
	// tests the class and its methods
	public static void main(String[] args){
		DealerHand d = new DealerHand();
		Landscape scape = new Landscape(400,400);
		Deck deck = new Deck();
		deck.initialize();
		scape.addDeck( deck );
		d.addCard( new Card(Suit.Spades, 14), scape ); // adds an ace
		System.out.println(d.getScore() );
		d.setTurn(true);
		d.updateState(scape); // flips over 2 and adds a 3 from deck
		System.out.println(d.getScore() );
	}
}