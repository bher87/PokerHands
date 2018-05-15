import java.util.*;

public class Hand {
    private Card[] cards;
    private int[] value;
    
    Hand(Deck d) {
        value = new int[6];
        cards = new Card[5];
        
        // To establish a 5-card hand
        for (int i = 0; i < 5; i++) {
            cards[i] = d.drawFromDeck();
        }

        int[] ranks = new int[14];
        
        // Miscellaneous cards that are not otherwise significant
        int[] orderedRanks = new int[5];
        
        
        /* The sameCards begins at 1, if we find a rank of which there are two or more similar 
        cards, then it will keep record those of value into the GroupRank. This will help us to 
        encounter hands like: One Pair, Two Pair, Three-of-a-kind and etc. */
        int sameCards = 1;
        int sameCards2 = 1;
        int largeGroupRank = 0;
        int smallGroupRank = 0;
        int index = 0;
        

        for (int i = 0; i <= 13; i++) {
            ranks[i] = 0;
        }
        for (int i = 0; i <= 4; i++) {
            ranks[cards[i].getRank()]++;
        }
        boolean flush = true;	// If there is a flush
        for (int i = 0; i < 4; i++) {
            if ( cards[i].getSuit() != cards[i + 1].getSuit() )
                flush = false;
        }

        for (int i = 13; i >= 1; i--) {
            if (ranks[i] > sameCards) {
            	// If sameCards is not the default value
                if (sameCards != 1) {
                    sameCards2 = sameCards;
                    smallGroupRank = largeGroupRank;
                }  
                sameCards = ranks[i];
                largeGroupRank = i;
            } else if (ranks[i] > sameCards2) {
                sameCards2 = ranks[i];
                smallGroupRank = i;
            }
        }
		
	// The value of 1 = "Ace". If ace, then this statement is ran first.
        if (ranks[1] == 1) { 
            // Then the value of 14 will represent the "Ace" as highest card
            orderedRanks[index] = 14;
            index++;
        }

        for (int i = 13; i >= 2; i--) {
            if (ranks[i] == 1) {
                orderedRanks[index] = i; //if ace
                index++;
            }
        }
        
        /* If there is no straight, then we can't have straight with lowest value
        of more than 10 */
        int topStraightValue = 0;
        boolean straight = false;	
        for (int i = 1; i <= 9; i++) { 
            if (ranks[i] == 1 && ranks[i + 1] == 1 && ranks[i + 2] == 1 && 
                ranks[i + 3] == 1 && ranks[i + 4] == 1) {
                
                straight = true;
                //4 above from bottom value
                topStraightValue = i + 4; 
                break;
            }
        }

        if (ranks[10] == 1 && ranks[11] == 1 && ranks[12] == 1 && 
            ranks[13] == 1 && ranks[1] == 1) { //ace high
        	
            // If it's higher than the "King"
            straight = true;
            topStraightValue = 14; 
        }
        
        for (int i = 0; i <= 5; i++) {
            value[i] = 0;
        }

        // Initiates the hand evaluation
        if (sameCards == 1) {
            value[0] = 1;
            value[1] = orderedRanks[0];
            value[2] = orderedRanks[1];
            value[3] = orderedRanks[2];
            value[4] = orderedRanks[3];
            value[5] = orderedRanks[4];
        }

        if (sameCards == 2 && sameCards2 == 1) {
            value[0] = 2;
            value[1] = largeGroupRank; // Rank of One Pair
            value[2] = orderedRanks[0];
            value[3] = orderedRanks[1];
            value[4] = orderedRanks[2];
        }
		
	// If Two Pairs
        if (sameCards == 2 && sameCards2 == 2) { 
            value[0] = 3;
            //rank of greater pair
            value[1] = largeGroupRank > smallGroupRank ? largeGroupRank : smallGroupRank;
            value[2] = largeGroupRank < smallGroupRank ? largeGroupRank : smallGroupRank;
            value[3] = orderedRanks[0];  //extra card
        }

        if (sameCards == 3 && sameCards2 != 2) {
            value[0] = 4;
            value[1] = largeGroupRank;
            value[2] = orderedRanks[0];
            value[3] = orderedRanks[1];
        }

        if (straight && !flush) {
            value[0] = 5;
        }

        if (flush && !straight) {
            value[0] = 6;
            value[1] = orderedRanks[0]; //tie determined by ranks of cards
            value[2] = orderedRanks[1];
            value[3] = orderedRanks[2];
            value[4] = orderedRanks[3];
            value[5] = orderedRanks[4];
        }

        if (sameCards == 3 && sameCards2 == 2) {
            value[0] = 7;
            value[1] = largeGroupRank;
            value[2] = smallGroupRank;
        }

        if (sameCards == 4) {
            value[0] = 8;
            value[1] = largeGroupRank;
            value[2] = orderedRanks[0];
        }

        if (straight && flush) {
            value[0] = 9;
        }

    }

    void summaryOfHand() {
        System.out.println("\nOutput: ");
        String s;
        
        switch(value[0]) {

            case 1:
                s = "   High Card \n";
                break;
            case 2:
                s = "   One pair of: " + Card.rankAsString(value[1]) + "\n";
                break;
            case 3:
                s = "   Two Pair of: " + Card.rankAsString(value[1]) + " & " + 
                                Card.rankAsString(value[2]) + "\n";
                break;
            case 4:
                s = "   Three of a Kind: " + Card.rankAsString(value[1]) + "\n";
                break;
            case 5:
                s = Card.rankAsString(value[1]) + " Straight \n";
                break;
            case 6:
                s = "   Flush \n";
                break;
            case 7:
                s = "   Full House: " + Card.rankAsString(value[1]) + " over " + 
                                  Card.rankAsString(value[2]) + "\n";
                break;
            case 8:
                s = "   Four of a kind: " + Card.rankAsString(value[1]);
                break;
            case 9:
                s = "   Straight Flush: " + Card.rankAsString(value[1]) + " High \n";
                break;
            default:
                s = "   Error in Hand.display: value[0] contains invalid value";
        }
        
        System.out.println(s);
    }
	
    // To display cards on hand.
    void showAllHands() {		
        System.out.println("Input: ");
        for (int i = 0; i < 5; i++)
            System.out.println("   " + cards[i]);
    }

    int compareTo(Hand those) {
        for (int i = 0; i < 6; i++) {
            if (this.value[i] > those.value[i])
                return 1;
            else if (this.value[i] < those.value[i])
                return -1;
        }
        return 0; // If the hands are equal
    }
}
