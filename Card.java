import java.util.*;

public class Card {
    
    private short rank, suit;

    private static String[] suits = {"Hearts", "Spades", "Diamonds", "Clubs"};

    private static String[] ranks  = {"Ace", "2", "3", "4", "5", "6", "7", "8",
        "9", "10", "Jack", "Queen", "King"};

    public static String rankAsString(int rank_a) {
        return ranks[rank_a];
    }
    
    Card(short suit, short rank) { 	//setters
        this.rank = rank;
        this.suit = suit;
    }

    public @Override String toString() {
        return ranks[rank] + " of " + suits[suit];
    }

    public short getRank() {		//getters
        return rank;
    }

    public short getSuit() {		//getters
        return suit;
    }
    
}