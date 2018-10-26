import java.util.Queue;
import java.util.LinkedList;

public class NaturalDisasterDeck {
   private final int deckSize = 20;
   private NaturalDisasterCard[] deck = new NaturalDisasterCard[deckSize];
   private Queue<NaturalDisasterCard> shuffledDeck = new LinkedList<>();
   
   public NaturalDisasterCard draw() { //later implement to randomize and put in stack so no repeats
      if(shuffledDeck.isEmpty()){
         shuffleDeck();
      }
      return shuffledDeck.remove();
   }
   
   public void setDeck(int index, NaturalDisasterCard card){
      this.deck[index] = card;
   }

   public void shuffleDeck(){
      NaturalDisasterCard[] tempDeck = deck.clone();
      while(shuffledDeck.size() < deckSize) {
         int rand = (int) (Math.random() * (this.deckSize));
         if(tempDeck[rand] != null) {
            shuffledDeck.add(tempDeck[rand]);
            tempDeck[rand] = null;
         }
      }
   }
}