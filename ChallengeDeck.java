import java.util.Queue;
import java.util.LinkedList;

/**
 * This is a class that represents a deck of challenge cards.
 * It stores all ChallengeCard objects in an array, and uses a
 * queue to store the order of the shuffled cards.
 *
 * @author Tyler Anderson
 * @version 1.0
 * @since 2018-10-26
 */
public class ChallengeDeck {
    private final int deckSize = 20;
    private ChallengeCard[] deck = new ChallengeCard[deckSize];
    private Queue<ChallengeCard> shuffledDeck = new LinkedList<>();

    /**
     * This method draws a card from the shuffled deck queue.
     * If the queue is empty we call shuffleDeck() to refill the shuffledDeck
     * queue in a new randomized order than before.
     * @return The top challenge card in the queue.
     * @see ChallengeCard
     * @see java.util.Queue
     * @see java.util.LinkedList
     */
    public ChallengeCard draw() { //later implement to randomize and put in stack so no repeats
        if(shuffledDeck.isEmpty()){
            shuffleDeck();
        }
        return shuffledDeck.remove();
    }

    public void setDeck(int index, ChallengeCard card){
        this.deck[index] = card;
    }

    /**
     * This method populates the shuffledDeck queue with every challenge card in deck,
     * in a random order.
     * @see ChallengeCard
     * @see java.util.Queue
     * @see java.util.LinkedList
     */
    public void shuffleDeck(){
        ChallengeCard[] tempDeck = deck.clone();
        while(shuffledDeck.size() < deckSize) {
            int rand = (int) (Math.random() * (this.deckSize));
            if(tempDeck[rand] != null) {
                shuffledDeck.add(tempDeck[rand]);
                tempDeck[rand] = null;
            }
        }
    }
}