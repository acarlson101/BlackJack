/*
* File Name: Deck.java
* Author: Adam Carlson
* Date: 11/21/15 
*/

import java.util.Random;

// models a deck in the game
public class Deck{

	private MyQueue<Card> cards; // stores the cards in the deck
	
	// creates an empty deck
	public Deck(){
		cards = new MyQueue<Card>();	
	}
	
	// adds 52 cards in order into the deck
	public void initialize(){
		for(int i = 0; i<52; i++){
			if( i >= 0 && i <= 12){
				cards.add( new Card(Suit.Hearts, i+2) );
			}
			else if( i >= 13 && i <= 25){
				cards.add( new Card(Suit.Diamonds, i-11) );
			}
			else if( i >= 26 && i <= 38){
				cards.add( new Card(Suit.Spades, i-24) );
			}
			else{
				cards.add( new Card(Suit.Clubs, i-37) );
			}   
		}
	
	}
	
	// shuffles the cards
	public void shuffle(){
		MyLinkedList<Card> list = new MyLinkedList<>();
		Random rand = new Random(); 
		for(int i = 0; i<52; i++){
			list.add( cards.remove() );
		}
		System.out.println(cards.size());
		for(int i = 0; i<52; i++){
			Card c = list.get(rand.nextInt(list.size()) );
			list.remove(c);
			cards.add( c );
		}
		System.out.println(cards.size());
	}
	
	// returns the list of cards
	public MyQueue<Card> getCards(){
		return this.cards;
	}
	
	// removes and returns the top card in the deck
	public Card pop(){
		return cards.remove();
	}
	
	// tests the class and its methods
	public static void main(String[] args){
		Deck d = new Deck();
		d.initialize();
		for(int i = 0; i<52; i++){
			Card c = d.getCards().remove();
			System.out.println(c.getValue() + "," + c.getSuit() );
		}
		d.initialize();
		d.shuffle();
		for(int i = 0; i<52; i++){
			Card c = d.pop();
			System.out.println(c.getValue() + "," + c.getSuit() );
		}
	}

}