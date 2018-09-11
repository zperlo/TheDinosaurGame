import java.util.Random;

public class NaturalDisasterDeck {
   private final int deckSize = 20;
   private NaturalDisasterCard[] deck = new NaturalDisasterCard[deckSize];
   
   public NaturalDisasterCard draw() { //later implement to randomize and put in stack so no repeats
      int rand = (int)(Math.random() * (this.deckSize));
      return this.deck[rand];
   }
}