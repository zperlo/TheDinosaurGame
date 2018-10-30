import java.util.Queue;
import java.util.LinkedList;

/**
 * This is a class that represents a deck of attack cards.
 * It stores all AttackCard objects in an array, and uses a
 * queue to store the order of the shuffled cards.
 *
 * @author Zach Perlo
 * @version 1.0
 * @since 2018-10-26
 */
public class AttackDeck {
    private final int deckSize = 20;
    private AttackCard[] deck = new AttackCard[deckSize];
    private Queue<AttackCard> shuffledDeck = new LinkedList<>();

    /**
     * This method draws a card from the shuffled deck queue.
     * If the queue is empty we call shuffleDeck() to refill the shuffledDeck
     * queue in a new randomized order than before.
     * @return The top attack card in the queue.
     * @see AttackCard
     * @see java.util.Queue
     * @see java.util.LinkedList
     */
    public AttackCard draw() {
        if(shuffledDeck.isEmpty()){
            shuffleDeck();
        }
        return shuffledDeck.remove();
    }

    public void setDeck(int index, AttackCard card){
        this.deck[index] = card;
    }

    /**
     * This method populates the shuffledDeck queue with every attack card in deck,
     * in a random order.
     * @see AttackCard
     * @see java.util.Queue
     * @see java.util.LinkedList
     */
    public void shuffleDeck(){
        AttackCard[] tempDeck = deck.clone();
        while(shuffledDeck.size() < deckSize) {
            int rand = (int) (Math.random() * (this.deckSize));
            if(tempDeck[rand] != null) {
                shuffledDeck.add(tempDeck[rand]);
                tempDeck[rand] = null;
            }
        }
    }
}