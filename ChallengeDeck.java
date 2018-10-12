public class ChallengeDeck {
    private final int deckSize = 20;
    private ChallengeCard[] deck = new ChallengeCard[deckSize];

    public ChallengeCard draw() { //later implement to randomize and put in stack so no repeats
        int rand = (int)(Math.random() * (this.deckSize));
        return this.deck[rand];
    }

    public void setDeck(int index, ChallengeCard card){
        this.deck[index] = card;
    }
}