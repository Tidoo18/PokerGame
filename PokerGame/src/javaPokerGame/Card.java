package javaPokerGame;

public class Card {

	private String suit;
	private String rank;

	public Card(String rank, String siut) {
		this.suit = siut;
		this.rank = rank;
	}

	public String getSuit() {
		return suit;
	}

	public String getRank() {
		return rank;
	}
}
