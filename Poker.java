import java.util.*;

public class Poker {
    public static void main(String[] args) {
        pokerHand();
    }
    
    public static void pokerHand() {
        for (int i = 0; i < 1; i++) {
            Deck deck = new Deck();
            Hand hand = new Hand(deck);
            hand.showAllHands(); 		// Shows all the individual cards in the hand
            hand.summaryOfHand(); 	// Shows the summary of the hand               
        }
    }
}