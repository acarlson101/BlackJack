/*
* File Name: Card.java
* Author: Adam Carlson
* Date: 11/21/15 
*/

import java.awt.Color;
import java.awt.Graphics;

// models a card in the game
public class Card extends Cell{
	
	private Suit suit; //stores the card's suit
	private int value; // stores the value of the card
	
	// initializes the card
	public Card(Suit s, int v){
		super(50,50);
		this.suit = s;
		this.value = v;
	}
	
	// returns the value of the card
	public int getValue(){
	
		if(this.value == 11 || this.value == 12 || this.value == 13){ // for face cards
			return 10;
		}
		else if(this.value == 14){ // for an ace
			return 11;
		}
		return this.value;
	}
	
	// returns the card's suit
	public Suit getSuit(){
		return this.suit;
	}
	
	// filler method so the card can extend cell
	public void updateState(Landscape scape){
		return;
	}
	
	// draws the card to the visual display
	public void draw(Graphics g, int x0, int y0, int scale){
		
		// draws the rectangle
		int x = x0 + (int)(this.getX() * scale);
		int y = y0 + (int)(this.getY() * scale);
		g.setColor(new Color(1.0f, 1.0f, 1.0f));
		g.fillRect(x, y, 10*scale, 20*scale);
		g.setColor(new Color(0.0f, 0.0f, 0.0f, 1.0f));
		g.drawRect(x, y, 10*scale, 20*scale);
		
		// draws the suit
		if(this.suit == Suit.Hearts){
			g.setColor(new Color(1.0f, 0.0f, 0.0f));
			g.drawString("\u2665", x+3*scale, y+8*scale);
		}
		else if(this.suit == Suit.Diamonds){
			g.setColor(new Color(1.0f, 0.0f, 0.0f));
			g.drawString("\u2666", x+3*scale, y+8*scale);
		}
		else if(this.suit == Suit.Spades){
			g.setColor(new Color(0.0f, 0.0f, 0.0f));
			g.drawString("\u2660", x+3*scale, y+8*scale);
		}
		else{
			g.setColor(new Color(0.0f, 0.0f, 0.0f));
			g.drawString("\u2663", x+3*scale, y+8*scale);
		}
		
		// draws the value
		if(this.value == 11){
			g.drawString("J", x+4*scale, (int)(y+15*scale));
		}
		else if(this.value == 12){
			g.drawString("Q", x+2*scale, (int)(y+15*scale));
		}
		else if(this.value == 13){
			g.drawString("K", x+3*scale, (int)(y+15*scale));
		}
		else if(this.value == 14){
			g.drawString("A", x+2*scale, (int)(y+15*scale));
		}
		else if(this.value == 10){
			g.drawString(Integer.toString(this.value), x+scale, (int)(y+15*scale));
		}
		else{
			g.drawString(Integer.toString(this.value), x+3*scale, (int)(y+15*scale));
		}	
		return;
	}
	
	// tests the class and its methods
	public static void main(String[] args){
		Card c = new Card(Suit.Hearts, 6);
		System.out.println(c.getSuit() + "," + c.getValue() );
	}

	

}

