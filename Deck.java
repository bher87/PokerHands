import java.util.*;

public class Deck {
    private ArrayList<Card> cards;
    
    Deck() {
        cards = new ArrayList<Card>();

        for (short i = 0; i <= 3; i++) {
            for (short j = 0; j <= 12; j++) {
               cards.add(new Card(i, j));
            }
        }
		
	int size = 52;	// Expected size of deck
	int index1; 	// Create indexes for random generator
        int index2; 	// Create indexes for random generator
        Random randomCards = new Random();
        Card temp;
		
	/* 100 pairs of cards are taken randomly, then they are switched 
        shuffled into the deck */
        for (short i = 0; i < 100; i++) {
            index1 = randomCards.nextInt(size);
            index2 = randomCards.nextInt(size);

            temp = (Card) cards.get(index2);
            cards.set(index2, cards.get(index1));
            cards.set(index1, temp);
        }
    }

    public Card drawFromDeck() {       
        return cards.remove(cards.size() - 1);
    }

    public int getTotalCards() {
        return cards.size();
    }
}
