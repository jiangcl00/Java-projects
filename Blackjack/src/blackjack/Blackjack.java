package blackjack;

import java.util.*;

public class Blackjack implements BlackjackEngine {

	/**
	 * Constructor you must provide. Initializes the player's account to 200 and
	 * the initial bet to 5. Feel free to initialize any other fields. Keep in
	 * mind that the constructor does not define the deck(s) of cards.
	 * 
	 * @param randomGenerator
	 * @param numberOfDecks
	 */

	private Random random = new Random();
	private int numberOfDecks;
	private int numberOfCards = 52 * numberOfDecks;
	private int topCard = 0;
	private int accountAmount = 200;
	private int betAmount = 5;
	private int gameStatus;
	private int dealerValue;
	private int playerValue;
	private int dealerStatus;
	private int playerStatus;

	private ArrayList<Card> gameDeck = new ArrayList<Card>();
	private Card[] Deck = gameDeck.toArray(new Card[gameDeck.size()]);

	private ArrayList<Card> dealerCards = new ArrayList<Card>();
	private Card[] dealer = dealerCards.toArray(new Card[dealerCards.size()]);

	private ArrayList<Card> playerCards = new ArrayList<Card>();
	private Card[] player = playerCards.toArray(new Card[playerCards.size()]);

	public Blackjack(Random randomGenerator, int numberOfDecks) {
		this.random = randomGenerator;
		this.numberOfDecks = numberOfDecks;
	}

	public int getNumberOfDecks() {
		return numberOfDecks;
	}

	public void createAndShuffleGameDeck() {

		for (CardSuit suit : CardSuit.values()) {
			for (CardValue value : CardValue.values()) {
				Card Card = new Card(value, suit);
				gameDeck.add(Card);
			}
		}
		Collections.shuffle(gameDeck, random);
	}

	public Card[] getGameDeck() {
		return Deck;
	}

	public void deal() {
		createAndShuffleGameDeck();

		System.out.println("assign to players");
		playerCards.add(gameDeck.get(topCard));
		topCard++;

		dealerCards.add(gameDeck.get(topCard));
		topCard++;
		dealerCards.get(0).setFaceDown();

		playerCards.add(gameDeck.get(topCard));
		topCard++;

		dealerCards.add(gameDeck.get(topCard));
		topCard++;

		gameStatus = GAME_IN_PROGRESS;

		accountAmount = accountAmount - betAmount;
	}

	public Card[] getDealerCards() {
		return dealer;
	}

	public int[] getDealerCardsTotal() {

		int firstTotal = 0;
		int secondTotal = firstTotal + 10;
		boolean acePresent = false;
		
		for (int i = 0; i < getDealerCards().length; i++) { //check if Ace is present
			if(dealer[i].getValue().getIntValue() == 1) {
				acePresent = true;
			}
		}
		if(acePresent = false) {
			for (int i = 0; i < getDealerCards().length; i++) { //get the only value when there is no Ace
				firstTotal = firstTotal + dealer[i].getValue().getIntValue();
			}
			if(firstTotal > 21) { //check if the value is greater than 21
				return null;
			}else {
				int[] dealerCardsTotal = {firstTotal};
				playerValue = firstTotal;
				return dealerCardsTotal;
			}
		}else {
			for (int i = 0; i < getDealerCards().length; i++) { //get two values when there is Ace
				firstTotal = firstTotal + dealer[i].getValue().getIntValue();
			}
			if(firstTotal > 21) { //check if the first value is greater than 21
				return null;
			}else {
				if(secondTotal > 21) { //check if the second value is greater than 21
					int[] dealerCardsTotal = {firstTotal};
					playerValue = firstTotal;
					return dealerCardsTotal;
				}else {
					int[] dealerCardsTotal = {firstTotal, secondTotal};
					playerValue = secondTotal;
					return dealerCardsTotal;
				}			
			}
		}
	}

	public int getDealerCardsEvaluation() {
		if (getDealerCardsTotal().length == 0) {
			dealerStatus = BUST;
		} else if (getDealerCardsTotal().length == 1) {
			dealerStatus = LESS_THAN_21;
		} else if (getDealerCardsTotal().length == 2) {
			if (getDealerCardsTotal()[1] == 21) {
				if (dealer.length == 2) {
					dealerStatus = BLACKJACK;
				} else {
					dealerStatus = HAS_21;
				}

			} else {
				dealerStatus = LESS_THAN_21;
			}
		}
		return dealerStatus;
	}

	public Card[] getPlayerCards() {
		return player;
	}

	public int[] getPlayerCardsTotal() {

		int firstTotal = 0;
		int secondTotal = firstTotal + 10;
		boolean acePresent = false;
		
		for (int i = 0; i < getPlayerCards().length; i++) { //check if Ace is present
			if(player[i].getValue().getIntValue() == 1) {
				acePresent = true;
			}
		}
		if(acePresent = false) {
			for (int i = 0; i < getPlayerCards().length; i++) { //get the only value when there is no Ace
				firstTotal = firstTotal + player[i].getValue().getIntValue();
			}
			if(firstTotal > 21) { //check if the value is greater than 21
				return null;
			}else {
				int[] playerCardsTotal = {firstTotal};
				playerValue = firstTotal;
				return playerCardsTotal;
			}
		}else {
			for (int i = 0; i < getPlayerCards().length; i++) { //get two values when there is Ace
				firstTotal = firstTotal + player[i].getValue().getIntValue();
			}
			if(firstTotal > 21) { //check if the first value is greater than 21
				return null;
			}else {
				if(secondTotal > 21) { //check if the second value is greater than 21
					int[] playerCardsTotal = {firstTotal};
					playerValue = firstTotal;
					return playerCardsTotal;
				}else {
					int[] playerCardsTotal = {firstTotal, secondTotal};
					playerValue = secondTotal;
					return playerCardsTotal;
				}			
			}
		}
	}

	public int getPlayerCardsEvaluation() {
		if (getPlayerCardsTotal().length == 0) {
			playerStatus = BUST;
		} else if (getPlayerCardsTotal().length == 1) {
			playerStatus = LESS_THAN_21;
		} else if (getPlayerCardsTotal().length == 2) {
			if (getPlayerCardsTotal()[1] == 21) {
				if (player.length == 2) {
					playerStatus = BLACKJACK;
				} else {
					playerStatus = HAS_21;
				}

			} else {
				playerStatus = LESS_THAN_21;
			}
		}else {
			System.out.println("player cards error");
		}
		return playerStatus;
	}

	public void playerHit() {
		playerCards.add(gameDeck.get(topCard));
		topCard++;
		if(getPlayerCardsEvaluation() == BUST) {
			gameStatus = DEALER_WON;
		}else {
			gameStatus = GAME_IN_PROGRESS;
		}
	}

	public void playerStand() {
		dealerCards.get(0).setFaceUp();
		while(dealerValue < 16) {
			dealerCards.add(gameDeck.get(topCard));
			topCard++;
		}
		if(getPlayerCardsEvaluation() == BUST) {
			gameStatus = PLAYER_WON;
		}
		
			while(dealerValue < playerValue) {
				dealerCards.add(gameDeck.get(topCard));
				topCard++;
				if(getPlayerCardsEvaluation() == BUST) {
					gameStatus = PLAYER_WON;
				}else if(dealerValue > playerValue) {
					gameStatus = DEALER_WON;
				}else if(dealerValue == playerValue) {
				gameStatus = DRAW;
				}
			}
	}

	public int getGameStatus() {
		return gameStatus;
	}

	public void setBetAmount(int amount) {
		this.betAmount = amount;
	}

	public int getBetAmount() {
		return betAmount;
	}

	public void setAccountAmount(int amount) {
		this.accountAmount = amount;
	}

	public int getAccountAmount() {
		return accountAmount;
	}

	/* Feel Free to add any private methods you might need */
}