import java.util.Queue;
import java.util.LinkedList;

public class AttackDeck {
    private final int deckSize = 20;
    private AttackCard[] deck = new AttackCard[deckSize];
    private Queue<AttackCard> shuffledDeck = new LinkedList<>();

    public AttackCard draw() { //later implement to randomize and put in stack so no repeats
        if(shuffledDeck.isEmpty()){
            shuffleDeck();
        }
        return shuffledDeck.remove();
    }

    public void setDeck(int index, AttackCard card){
        this.deck[index] = card;
    }

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