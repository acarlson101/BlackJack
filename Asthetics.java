/*
* File Name: Asthetics.java
* Author: Adam Carlson
* Date: 11/21/15 
*/

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;

// models the asthetics of the blackjack game
public class Asthetics extends Cell{
	
	private String result; //stores the end result of the game
	
	// initializes the asthetics
	public Asthetics(){
		super(10,10);
		this.result = "none";
	}
	
	// filler method in order to allow the class to extend cell
	public void updateState(Landscape scape){
		
		return;
	}
	
	// sets the end game result
	public void setResult(String s){
		this.result = s;
	}
	
	// returns the game result
	public String getResult(){
		return this.result;
	}
	
	// draws the game asthetics
	public void draw(Graphics g, int x0, int y0, int scale){
	
		//the two green stripes
		g.setColor(new Color(0.0f, 1.0f, 0.0f));
		g.fillRect(0, 300*scale, 400*scale, 21*scale);
		g.setColor(new Color(0.0f, 1.0f, 0.0f));
		g.fillRect(0, 100*scale, 400*scale, 21*scale);
		
		// the middle of the table
		g.setColor(new Color(1.0f, 0.0f, 0.0f));
		g.fillRect(0, 121*scale, 400*scale, 179*scale);
		g.setFont(new Font("TimesRoman", Font.PLAIN, 50));
		g.setColor(new Color(255,215,0));
		g.fillOval(90*scale, 147*scale, 200*scale, 130*scale);
		g.setColor(new Color(0.0f, 0.0f, 0.0f));
		
		// changes the string in the middle of the table depending on the result
		if(result.equals("none")){ 
			g.drawString("BlackJack", 140*scale, 220*scale);
		}
		else if(result.equals("pBust")){ 
			g.drawString("Player Bust", 135*scale, 220*scale);
		}
		else if(result.equals("dBust")){ 
			g.drawString("Dealer Bust", 135*scale, 220*scale);
		}
		else if(result.equals("pWin")){ 
			g.drawString("Player Wins", 135*scale, 220*scale);
		}
		else if(result.equals("dWin")){ 
			g.drawString("Dealer Wins", 135*scale, 220*scale);
		}
		else if(result.equals("tie")){ 
			g.drawString("It is a Tie", 135*scale, 220*scale);
		}
		g.setFont(new Font("TimesRoman", Font.PLAIN, 15));
		
		return;
	}
	
	// tests the class and its methods
	public static void main(String[] args){
		Asthetics a = new Asthetics();
		a.setResult("pWin");
		System.out.println(a.getResult());
	}
	
}