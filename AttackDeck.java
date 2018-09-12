public class AttackDeck {
    private final int deckSize = 20;
    private AttackCard[] deck = new AttackCard[deckSize];

    public AttackCard draw() { //later implement to randomize and put in stack so no repeats
        int rand = (int)(Math.random() * (this.deckSize));
        return this.deck[rand];
    }

    public void setDeck(int index, AttackCard card){
        this.deck[index] = card;
    }
}