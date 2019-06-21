package javaPokerGame;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {

	ArrayList<Card> deck = new ArrayList<Card>();
	static ArrayList<Card> hand = new ArrayList<Card>();

	public void drawCards() {

		for (int i = 0; i < 13; i++) {
			for (int j = 0; j < 4; j++) {
				Card nextCard;
				try {
					nextCard = new Card(i, j);
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
		DeckShuffle();
		if (!hand.isEmpty()) {
			hand.removeAll(hand);
		}
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

}
