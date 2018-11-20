import java.util.Queue;
import java.util.LinkedList;
import java.util.ArrayList;

/**
 * This is a class that represents a deck of cards.
 * These cards can be of type AttackCard, NaturalDisasterCard, or ChallengeCard.
 * T is used to represent which of these types of card the deck is for.
 * Deck stores all card objects in an array, and uses a
 * queue to store the order of the shuffled cards.
 *
 * @author Dylan Briggs
 * @version 1.0
 * @since 2018-11-19
 */
public class Deck<T> {
    private final int deckSize = 20;
    private ArrayList<T> deck = new ArrayList<>();
    private Queue<T> shuffledDeck = new LinkedList<>();

    /**
     * This method draws a card from the shuffled deck queue.
     * If the queue is empty we call shuffleDeck() to refill the shuffledDeck
     * queue in a new randomized order than before.
     * @return The top card in the queue.
     * @see AttackCard
     * @see ChallengeCard
     * @see NaturalDisasterCard
     * @see java.util.Queue
     * @see java.util.LinkedList
     */
    public T draw() {
        if(shuffledDeck.isEmpty()){
            shuffleDeck();
        }
        return shuffledDeck.remove();
    }

    public void setDeck(int index, T card){
        if (deck.size() >= deckSize)
            deck.set(index, card);
        else this.deck.add(index, card);
    }

    /**
     * This method populates the shuffledDeck queue with every card in deck,
     * in a random order.
     * @see AttackCard
     * @see java.util.Queue
     * @see ChallengeCard
     * @see NaturalDisasterCard
     * @see java.util.LinkedList
     */
    public void shuffleDeck() {
        ArrayList<T> tempDeck = (ArrayList<T>)(deck.clone());
        while (shuffledDeck.size() < deckSize) {
            int rand = (int)(Math.random() * (deckSize));
            if (tempDeck.get(rand) != null) {
                shuffledDeck.add(tempDeck.get(rand));
                tempDeck.set(rand, null);
            }
        }
    }
}