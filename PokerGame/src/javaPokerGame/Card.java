package javaPokerGame;

public class Card {

	private int suit;
	private int rank;
	
	//						0			1		2		3
	String[] Suits = { "Hearts", "Diamonds", "Spades", "Clubs" };
	//					 0	   1	2	 3	  4	   5	6	 7	  8	   9	  10	  11	   12
	String[] Ranks = { "Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", };

	public Card(int rank, int siut) {
		this.suit = siut;
		this.rank = rank;
	}

	public int getSuit() {
		return suit;
	}

	public int getRank() {
		return rank;
	}
	
	public String toString() {
		return Ranks[rank] + " of " + Suits[suit]; 
	}
}
