/*
* File Name: Hand.java
* Author: Adam Carlson
* Date: 11/21/15 
*/

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;

// models the player's hand in the game
public class Hand extends Cell{

	private MyArrayList hand;
	private boolean yourTurn;
	
	// initializes the player hand
	public Hand(){
		super(20,300);
		this.hand = new MyArrayList();
		this.yourTurn = true;
	}
	
	// returns whether it is the player's turn
	public boolean getTurn(){
		return this.yourTurn;
	}
	
	// sets whether it is the player's turn
	public void setTurn(boolean b){
		this.yourTurn = b;
	}
	
	// adds a card to the player's hand
	public void addCard(Card c, Landscape scape){
		c.setPosition(20+this.hand.size()*20, 300);
		this.hand.add(c);
		scape.addAgent(c);
		
	}
	
	// returns the player's score
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
	
	// filler method so the class can extend cell
	public void updateState(Landscape scape){
		return;
	}
	
	// filler method so the class can extend cell
	public void draw(Graphics g, int x0, int y0, int scale){
	
	
		
		return;
	}
	
	// tests the class and its methods
	public static void main(String[] args){
		Hand h = new Hand();
		Landscape scape = new Landscape(400,400);
		Deck deck = new Deck();
		deck.initialize();
		scape.addDeck( deck );
		h.addCard( new Card(Suit.Spades, 14), scape ); // adds an ace
		System.out.println(h.getScore() );
		h.setTurn(false);
		System.out.println(h.getTurn());
		h.setTurn(true);
		h.updateState(scape); // doesn't have an effect on player, only adds card with button
		System.out.println(h.getScore() );
	}
}