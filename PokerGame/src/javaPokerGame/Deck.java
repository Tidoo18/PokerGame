package javaPokerGame;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {

	ArrayList<Card> deck = new ArrayList<Card>();
	ArrayList<Card> hand = new ArrayList<Card>();

	public void drawCards() {

		String[] Suits = { "Hearts", "Diamonds", "Spades", "Clubs" };
		String[] Ranks = { "Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", };

		for (int i = 0; i < Ranks.length; i++) {
			for (int j = 0; j < Suits.length; j++) {
				Card nextCard;
				try {
					nextCard = new Card(Ranks[i], Suits[j]);
					deck.add(nextCard);
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		}
	}

	public void DeckShuffle() {
		Collections.shuffle(deck);
	}

	public ArrayList<Card> dealHand() {
		for (int i = 0; i < 5; i++) {
			hand.add(deck.get(0));
			deck.remove(0);
		}
		return hand;

	}

	public Card getOneCard() {
		Card oneCard;
		oneCard = deck.get(0);
		deck.remove(0);

		return oneCard;
	}

	public Card draw() {
		return deck.remove(0);
	}

	// Nenaudojama kolkas
	public int getTotalCards() {
		return deck.size();

	}

}
