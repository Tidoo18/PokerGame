package javaPokerGame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
	String answer;
	String anotherCard;
	int sum;

	// Play main method, to launch
	public void play() {

		displayWinningTable();
		deck.drawCards();
		hand = deck.dealHand();
		print("-----------------\n");
		print("Your cards: ");
		for (int i = 0; i < hand.size(); i++) {
			card = hand.get(i);
			System.out.println(card.toString());
		}

		while (true) {
			print("\nWould you like to change one or few Cards? yes / no");
			answer = sc.nextLine();
			if (answer.toLowerCase().equals("yes")) {
				changeCards();
				print("You got " + evaluateHand());
				break;
			} else if (answer.toLowerCase().equals("no")) {
				print("You got " + evaluateHand());
				break;
			} else {
				System.out.print("Didin't catch that. Please try again..");
				continue;
			}
		}
		System.out.println();
		playAgain();

	}

	// Sorting method
	private List<Card> sortedHand() {
		List<Card> sortedHand = new ArrayList<Card>();
		for (int i = 0; i < hand.size(); i++) {
			sortedHand.add(hand.get(i));
		}

		Collections.sort(sortedHand, new Comparator<Card>() {
			@Override
			public int compare(Card card1, Card card2) {
				return card1.getRank() - card2.getRank();
			}
		});

		return sortedHand;
	}

	// card discarding method
	public void changeCards() {
		String ne = "no";
		String taip = "yes";
		print("Please tell which card would you like to change?");
		print("Please enter number which card would you like to change. \nExp.: '1', '2','3','4','5'");
		int changeCardRank = sc.nextInt();

		for (int i = 0; i <= hand.size(); i++) {
			if (i == (changeCardRank - 1)) {
				hand.remove(i);
				hand.add(deck.getOneCard());
				sum += 1;
			}
		}
		sortedHand();
		print("Your cards are: ");
		for (int i = 0; i < hand.size(); i++) {
			card = hand.get(i);
			print(card.toString());
		}
		print("\nWould you like to change another Card? yes / no");
		while (true) {
			anotherCard = sc.nextLine();

			if (anotherCard.toLowerCase().equals(taip)) {
				changeCards();
				break;
			} else if (anotherCard.toLowerCase().equals(ne)) {
				break;
			} else if (sum >= 3) {
				print("You can only change 3 times");
				print("You got " + evaluateHand());
				break;
			}
		}
	}

	public int royalFLush() {
		int count = 0;
		if ((sortedHand().get(0).getRank() == 1) && (sortedHand().get(1).getRank() == 10)
				&& (sortedHand().get(2).getRank() == 11) && (sortedHand().get(3).getRank() == 12)
				&& (sortedHand().get(4).getRank() == 13)) {
			for (int i = 0; i < 4; i++) {
				if (sortedHand().get(i).getSuit() == sortedHand().get(i + 1).getSuit()) {
					count++;
				}
			}
		}

		if (count == 4) {
			return 10;
		} else {
			return 0;
		}
	}

	public int straightFlush() {

		int countRank = 0;
		int countSuit = 0;
		for (int i = 0; i < 4; i++) {
			if (sortedHand().get(i).getSuit() == sortedHand().get(i + 1).getSuit()) {
				countSuit++;
				if ((sortedHand().get(i).getRank() + 1) != sortedHand().get(i + 1).getRank()) {
					countRank++;
				}
			}
		}

		if (countSuit == 4 && countRank > 0) {
			return 9;
		} else {
			return 0;
		}
	}

	public int fourOfaKind() {
		int posibility = 0;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if ((sortedHand().get(i).getRank() == (sortedHand().get(j).getRank())) && (i != j))
					posibility++;
			}
		}
		if (posibility == 3) {
			return 8;
		} else {
			return 0;
		}
	}

	public int fullHouse() {
		int posibility = 0;
		List<Integer> Rank = new ArrayList<Integer>();
		for (int j = 0; j < 4; j++) {

			if (sortedHand().get(j).getRank() == sortedHand().get(j + 1).getRank()) {
				Rank.add(sortedHand().get(j).getRank());
				posibility++;
			}
		}

		if (posibility == 3 && (Rank.get(0) == Rank.get(1) && Rank.get(1) != Rank.get(2)))

		{
			return 7;
		} else {
			return 0;
		}
	}

	public int flush() {
		int countRank = 0;
		int countSuit = 0;
		for (int i = 0; i < 4; i++) {
			if (sortedHand().get(i).getSuit() == sortedHand().get(i + 1).getSuit()) {
				countSuit++;
				if ((sortedHand().get(i).getRank() + 1) != sortedHand().get(i + 1).getRank()) {
					countRank++;
				}
			}
		}

		if (countSuit == 4 && countRank > 0) {
			return 6;
		} else {
			return 0;
		}
	}

	public int straight() {
		int countRank = 0;
		int countSuit = 0;
		for (int i = 0; i < 4; i++) {
			if ((sortedHand().get(i).getRank() + 1) == sortedHand().get(i + 1).getRank()) {
				countRank++;
				if (sortedHand().get(i).getSuit() != sortedHand().get(i + 1).getSuit()) {
					countSuit++;
				}
			}
		}

		if (countRank == 4 && countSuit > 0) {
			return 5;
		} else {
			return 0;
		}
	}

	public int threeOfAKind() {
		if (sortedHand().get(0).getRank() == sortedHand().get(1).getRank()
				&& sortedHand().get(1).getRank() == sortedHand().get(2).getRank()
				|| sortedHand().get(2).getRank() == sortedHand().get(3).getRank()
						&& sortedHand().get(3).getRank() == sortedHand().get(4).getRank()) {
			return 4;
		} else {
			return 0;
		}
	}

	public int twoPair() {
		int count = 0;
		for (int i = 0; i < 4; i++) {
			if (sortedHand().get(i).getRank() == sortedHand().get(i + 1).getRank()) {
				count++;
			}
		}
		if (count == 2) {
			return 3;
		} else {
			return 0;
		}
	}

	public int jacksOrBetter() {
		sortedHand();
		int count = 0;
		for (int j = 0; j < 5; j++) {

			if (sortedHand().get(j).getRank() == 10) {
				count++;
			} else if (sortedHand().get(j).getRank() == 11) {
				count++;
			} else if (sortedHand().get(j).getRank() == 12) {
				count++;
			}
			if (count >= 1) {
				return 2;
			}
		}
		return 0;
	}

	public int allOther() {
		return 1;
	}

	// evaluating hand
	public String evaluateHand() {
		String evaluationHand = "";

		if (royalFLush() == 10) {
			evaluationHand = "Royal Flush" + "\nWou won " + winningSum[9] + " Cent";
			return evaluationHand;
		}
		if (straightFlush() == 9) {
			evaluationHand = "Straight Flush" + "\nWou won " + winningSum[8] + " Cent";
			return evaluationHand;
		}
		if (fourOfaKind() == 8) {
			evaluationHand = "Four of a Kind" + "\nWou won " + winningSum[7] + " Cent";
			return evaluationHand;
		}
		if (fullHouse() == 7) {
			evaluationHand = "Full House" + "\nWou won " + winningSum[6] + " Cent";
			return evaluationHand;
		}
		if (flush() == 6) {
			evaluationHand = "Flush" + "\nWou won " + winningSum[5] + " Cent";
			return evaluationHand;
		}
		if (straight() == 5) {
			evaluationHand = "Straight" + "\nWou won " + winningSum[4] + " Cent";
			return evaluationHand;
		}
		if (threeOfAKind() == 4) {
			evaluationHand = "Three of a Kind" + "\nWou won " + winningSum[3] + " Cent";
			return evaluationHand;
		}
		if (twoPair() == 3) {
			evaluationHand = "2 Pair" + "\nWou won " + winningSum[2] + " Cent";
			return evaluationHand;
		}
		if (jacksOrBetter() == 2) {
			evaluationHand = "Jack or Better" + "\nWou won " + winningSum[1] + " Cent";
			return evaluationHand;
		}
		if (allOther() == 1) {
			evaluationHand = "All Other" + "\nWou won " + winningSum[0] + " Cent";
			return evaluationHand;
		}
		return evaluationHand;
	}

	public void playAgain() {
		print("Do you wish to play again? yes / no");
		String playAgain = sc.nextLine();

		if (playAgain.toLowerCase().equals("yes")) {
			card = null;
			play();
		}
	}

	// displays winning table
	public void displayWinningTable() {
		print("Payout Table \n----------------------");
		for (int payout = 0; payout < winningSum.length; payout++) {
			print(winningHands[payout] + " = " + winningSum[payout]);
		}
	}

	// shortcut for printing
	public void print(String string) {
		System.out.println(string);
	}

}