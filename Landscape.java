// Bruce Maxwell
// CS 231 Fall 2012
// Project 7
//
import java.util.*;

public class Landscape {
		private double width;  // change to doubles
		private double height;
		private MyLinkedList<Cell> agents;
		private Deck deck; // stores the deck
		private Asthetics a; // stores the asthetics
		private Hand h; // stores the player hand

		public Landscape(int rows, int cols) {
				agents = new MyLinkedList<Cell>();
				height = (double)rows;
				width = (double)cols;
				deck = null;
				a = null;
				h = null;
		}

		// add a constructor for a double size
		public Landscape(double rows, double cols) {
				agents = new MyLinkedList<Cell>();
				height = (double)rows;
				width = (double)cols;
				deck = null;
				a = null;
				h = null;
		}

		public void reset() {
				// get rid of all of the agents
				agents.clear();
				this.deck = null;
				this.a = null;
				this.h = null;
		}

		// modify to round
		public int getRows() {
				return (int)(height + 0.5);
		}

		// add method
		public double getHeight() {
				return height;
		}

		// modify to round
		public int getCols() {
				return (int)(width + 0.5);
		}

		// add method
		public double getWidth() {
				return width;
		}

		public void addAgent(Cell a) {
				agents.add(a);
				if( a instanceof Asthetics){
					this.a = (Asthetics)a;
				}
				if( a instanceof Hand){
					this.h = (Hand)a;
				}
		}
		
		// returns asthetics
		public Asthetics getAsthetics(){
			return this.a;
		}
		 // returns the player hand
		public Hand getHand(){
			return this.h;
		}
		
		// adds a deck to the landscape
		public void addDeck(Deck d){
			this.deck = d;
		}
		
		// returns the game's deck
		public Deck getDeck(){
			return this.deck;
		}
		


		public void removeAgent( Cell a ) {
				agents.remove(a);
		}

		public ArrayList<Cell> getAgents() {
				return agents.toArrayList();
		}

		public String toString() {
				ArrayList<String> s = new ArrayList<String>();

				for(int i=0;i<height;i++) {
						for(int j=0;j<width;j++) {
								s.add(" ");
						}
						s.add("\n");
				}

				for( Cell item: agents ) {
						int r = item.getRow();
						int c = item.getCol();

						if(r >= 0 && r < height && c >= 0 && c < width ) {
								int index = r * (this.getCols() + 1) + c;
								s.set( index, item.toString() );
						}
				}

				String sout = "";
				for( String a: s ) {
						sout += a;
				}

				return sout;
		}

		public void advance() {
				// put the agents in random oder
				ArrayList<Cell> items = agents.toShuffledList();

				// update the state of each agent
				for(Cell item: items ) {
						item.updateState( this );
				}
		}
		
		// tests the methods I added to the class
		public static void main(String argv[]) {
				int rows = 30;
				int cols = 70;
				int N = 300;
				Landscape scape = new Landscape(rows, cols);
				Random gen = new Random();
				scape.addAgent( new Asthetics() );
				scape.addAgent( new Hand() );
				scape.addDeck( new Deck() );
				System.out.println( scape.getAsthetics() );
				System.out.println( scape.getHand() );
				System.out.println( scape.getDeck() );
				scape.reset();
				System.out.println( scape.getDeck() );
		}

};
