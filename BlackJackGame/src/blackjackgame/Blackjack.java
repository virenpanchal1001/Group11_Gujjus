package blackjackgame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Blackjack {
    
    private static final int BLACKJACK_VALUE = 21;
    private static final int DEALER_HIT_LIMIT = 17;
    
    private List<Card> deck;
    private List<Card> playerCards;
    private List<Card> dealerCards;
    
    public static void main(String[] args) {
        Blackjack blackjack = new Blackjack();
        blackjack.play();
    }
    
    public Blackjack() {
        deck = new ArrayList<>();
        playerCards = new ArrayList<>();
        dealerCards = new ArrayList<>();
        
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                deck.add(new Card(suit, rank));
            }
        }
        
        Collections.shuffle(deck);
    }
    
    public void play() {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Welcome to Blackjack!");
        System.out.println("----------------------");
        System.out.println();
        
        dealInitialCards();
        
        while (true) {
            printGameState();
            
            if (isBust(playerCards)) {
                System.out.println("You busted! Dealer wins.");
                break;
            }
            
            if (isBlackjack(playerCards)) {
                System.out.println("Blackjack! You win!");
                break;
            }
            
            System.out.print("Do you want to hit or stand? (h/s): ");
            String choice = scanner.nextLine().trim().toLowerCase();
            
            while (!choice.equals("h") && !choice.equals("s")) {
                System.out.print("Invalid input. Do you want to hit or stand? (h/s): ");
                choice = scanner.nextLine().trim().toLowerCase();
            }
            
            if (choice.equals("h")) {
                playerCards.add(deck.remove(0));
            } else {
                while (calculateHandValue(dealerCards) < DEALER_HIT_LIMIT) {
                    dealerCards.add(deck.remove(0));
                }
                
                printFinalGameState();
                
                if (isBust(dealerCards)) {
                    System.out.println("Dealer busted! You win!");
                } else if (calculateHandValue(playerCards) > calculateHandValue(dealerCards)) {
                    System.out.println("You win!");
                } else if (calculateHandValue(playerCards) == calculateHandValue(dealerCards)) {
                    System.out.println("Push.");
                } else {
                    System.out.println("Dealer wins.");
                }
                
                break;
            }
        }
        
        scanner.close();
    }
    
    private void dealInitialCards() {
        playerCards.add(deck.remove(0));
        dealerCards.add(deck.remove(0));
        playerCards.add(deck.remove(0));
        dealerCards.add(deck.remove(0));
    }
    
    private void printGameState() {
        System.out.println("Player Cards: " + playerCards);
        System.out.println("Dealer Cards: [" + dealerCards.get(0) + ", *]");
        System.out.println();
    }
    
    private void printFinalGameState() {
        System.out.println("Player Cards: " + playerCards);
        System.out.println("Dealer Cards: " + dealerCards);
        System.out.println();
    }
    
   private int calculateHandValue(List<Card> hand) {
    int handValue = 0;
    int numAces = 0;
    for (Card card : hand) {
        int cardValue = card.getValue();
        if (cardValue == 11) {
            numAces++;
        }
        handValue += cardValue;
    }
    while (numAces > 0 && handValue > BLACKJACK_VALUE) {
        handValue -= 10;
        numAces--;
    }
    return handValue;
}


    private boolean isBust(List<Card> hand) {
    int handValue = calculateHandValue(hand);
    return handValue > BLACKJACK_VALUE;
}

   private boolean isBlackjack(List<Card> hand) {
    return hand.size() == 2 && calculateHandValue(hand) == BLACKJACK_VALUE;
}

}

//update
