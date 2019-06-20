package javaPokerGame;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Hand {

	Scanner sc = new Scanner(System.in);

	// To make payout table;
	private static int[] winningSum = { 0, 1, 2, 3, 4, 6, 9, 25, 50, 800 };
	private static String[] winningHands = { "All other", "Jacks or Better", "Two Pair ", "Three of a kind", "Straight",
			"Flush", "Full House", "Four of a kind", "Straight Flush", "Royal Flush" };

	ArrayList<Card> hand = new ArrayList<Card>();
	Deck deck = new Deck();
	Card card;

	ArrayList<List> handForSorting = new ArrayList<>();

	public void play() {

		displayWinningTable();
		deck.drawCards();
		deck.DeckShuffle();
		hand = deck.dealHand();
//		sortCards();
		print("-----------------\n");
		System.out.println("Your cards: ");
		for (int i = 0; i < hand.size(); i++) {
			card = hand.get(i);
			System.out.println(card.getRank() + " " + card.getSuit());
//			System.out.println("You got " + evaluateHand());
//			System.out.println();
		}
		redrawCard();
	}

	// Nepadaryta
	public List<String> sortCards(ArrayList<Card> hand) {
		return null;

	}

	// Nepadaryta. I while neuzeina;
	public void redrawCard() {
		int changeCard = 0;
		String anotherCard = "";
		System.out.println("\nWould you like to change one or few Cards? yes / no");
		String answer = sc.nextLine();

		if (answer.toLowerCase().equals("yes")) {
			do {
				changeCard++;
				System.out.println("Please tell which card would you like to change?");
				System.out.println("Please enter number from fisrt to last. \nExp.: '1', '2','3','4','5'");
				int changeCardRank = sc.nextInt();

				for (int i = 0; i <= hand.size(); i++) {
					if (i == changeCardRank - 1) {
						hand.remove(i);
						hand.add(deck.getOneCard());
						break;
					} else if (i > 4) {
						System.out.println("Card can not be found");
						changeCard--;
					}
				}

				System.out.println("Your cards are: ");
				for (int i = 0; i < hand.size(); i++) {
					card = hand.get(i);
					System.out.println(card.getRank() + " " + card.getSuit());
				}
				print("\nWould you like to change another Card? yes / no");
				anotherCard = sc.nextLine();
			} while (anotherCard.toLowerCase().equals("no") && changeCard != 5);
			if (changeCard == 5) {
				System.out.println();
				System.out.println("You can not change anymore cards, only 5 cards can be changed.");
				System.out.println("--------------------------------------------------------------");
			}
		} else {
			System.out.println("No cards Removed");
		}
	}

//	Dalis variaciju neveikia, per String reiktu tikrinti; Arba konvertuoti, dar nezinau kaip darysiu.
//	
//	"All other", "Jacks or Better", "Two Pair ", "Three of a kind", "Straight",
//	"Flush", "Full House", "Four of a kind", "Straight Flush", "Royal Flush" 

	public int jacksOrBetter() {
		String[] jackOrBettter = { "Jack", "Queen", "King" };
		for (int i = 0; i < hand.size(); i++) {
			if (hand.get(i).getRank() == jackOrBettter[0] || hand.get(i).getRank() == jackOrBettter[1]
					|| hand.get(i).getRank() == jackOrBettter[2]) {
				return 1;
			}
		}
		return 0;
	}

	public int twoPair() {
		int count = 0;
		for (int i = 0; i < 5; i++) {
			if (hand.get(i).getRank() == hand.get(i).getRank()) {
				count++;
			}
		}
		if (count == 2) {
			return 1;
		} else {
			return 0;
		}
	}

	public int threeOfAKind() {

		if (hand.get(0).getRank() == hand.get(1).getRank()
				|| hand.get(1).getRank() == hand.get(2).getRank() && hand.get(1).getRank() == hand.get(2).getRank()
				|| hand.get(2).getRank() == hand.get(3).getRank() && hand.get(2).getRank() == hand.get(3).getRank()
				|| hand.get(3).getRank() == hand.get(4).getRank() && hand.get(3).getRank() == hand.get(4).getRank()
				|| hand.get(4).getRank() == hand.get(5).getRank()) {

			return 1;
		}
		return 0;
	}

	public int straight() {

		for (int i = 1; i < 5; i++) {
			if (hand.get(i - 1).getRank() != hand.get(i--).getRank()) {
				return 0;
			}

		}
		return 1;
	}

	public int flush() {
		for (int i = 0; i < 5; i++) {
			if (hand.get(i).getSuit() != hand.get(i).getSuit()) {
				return 0;
			}
		}
		return 1;
	}

	public int fullHouse() {
		int posibility = 0;
		for (int i = 0; i < hand.size(); i++) {
			if (hand.get(i - 1).getRank() == hand.get(i--).getRank()) {
				posibility++;
			}
		}
		if (posibility == 3) {
			return 1;
		} else {
			return 0;
		}
	}

	public int fourOfaKind() {
		if (hand.get(0).getRank() != hand.get(3).getRank() && hand.get(1).getRank() != hand.get(4).getRank()) {
			return 0;
		} else {
			return 1;
		}
	}

	public int straightFlush() {

		for (int i = 0; i < hand.size(); i++) {
			if (hand.get(0).getSuit() != hand.get(i).getSuit()) {
				return 0;
			}
		}
		for (int i = 0; i < hand.size(); i++) {
			if (hand.get(i - 1).getRank() != (hand.get(i - 1).getRank())) {
				return 0;
			}
		}
		return 1;
	}

	public int royalFLush() {
		if (hand.get(0).getRank().equals("1") && hand.get(1).getRank().equals("10")
				&& hand.get(2).getRank().equals("11") && hand.get(3).getRank().equals("12")
				&& hand.get(4).getRank().equals("13")) {
			return 1;
		} else {
			return 0;
		}
	}

	// Del to jog variacijos neveikia, patikrinimas irgi kolkas neveikia;
	public String evaluateHand() {
		String evaluationHand = "";

		if (royalFLush() == 1) {
			evaluationHand = "Royal Flush";
			return evaluationHand;
		}
		if (straightFlush() == 1) {
			evaluationHand = "Straight Flush";
			return evaluationHand;
		}
		if (fourOfaKind() == 1) {
			evaluationHand = "Four of a Kind";
			return evaluationHand;
		}
		if (fullHouse() == 1) {
			evaluationHand = "Full House";
			return evaluationHand;
		}
		if (flush() == 1) {
			evaluationHand = "Flush";
			return evaluationHand;
		}
		if (straight() == 1) {
			evaluationHand = "Straight";
			return evaluationHand;
		}
		if (threeOfAKind() == 1) {
			evaluationHand = "Three of a Kind";
			return evaluationHand;
		}
		if (twoPair() == 1) {
			evaluationHand = "2 Pair";
			return evaluationHand;
		}
		if (jacksOrBetter() == 1) {
			evaluationHand = "Jack or Better";
			return evaluationHand;
		}
		return evaluationHand;
	}

	public void displayWinningTable() {
		print("Payout Table \n----------------------");
		for (int payout = 0; payout < winningSum.length; payout++) {
			print(winningHands[payout] + " = " + winningSum[payout]);
		}
	}

	public void print(String string) {
		System.out.println(string);
	}

}