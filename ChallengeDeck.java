import java.util.Queue;
import java.util.LinkedList;

public class ChallengeDeck {
    private final int deckSize = 20;
    private ChallengeCard[] deck = new ChallengeCard[deckSize];
    private Queue<ChallengeCard> shuffledDeck = new LinkedList<>();

    public ChallengeCard draw() { //later implement to randomize and put in stack so no repeats
        if(shuffledDeck.isEmpty()){
            shuffleDeck();
        }
        return shuffledDeck.remove();
    }

    public void setDeck(int index, ChallengeCard card){
        this.deck[index] = card;
    }

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