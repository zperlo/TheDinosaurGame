/**
 * This is a data type that stores aspects of a challenge card.
 * This is what is drawn when a player lands on a challenge space.
 * There are multiple kinds of challenge cards. The most common is choice cards,
 * where the player is given two options and chooses one of the two. If it is not
 * a choice card, then the choice1 variable represents the effect.
 *
 * @author Tyler Anderson
 * @version 1.0
 * @since 2018-10-12
 */

public class ChallengeCard {
    private String choice1;
    private String middlePara; // for choice cards, this is "OR"
    private String choice2;
    private int id; // identification code unique to each card.
    private int type; // 0 - 3 for different types of challenge cards. This is used by the GUI to display the card.

    /**
     * Constructor to initialize a challenge card.
     * @param choice1 The first choice the player has to choose from.
     * @param middlePara The text on the card between the two options. It is "OR" for choice cards.
     * @param choice2 The second choice the player has to choose from.
     * @param id This is an identification code for which card is which. Due to the variance among
     *           challenge cards effects, their effects are handled in Game based on this id.
     * @param type This represents which of 4 possible types (0 - 3) the card looks like.
     *             This is used by the GUI to display the card.
     */
    public ChallengeCard(String choice1, String middlePara, String choice2, int id, int type) {
        this.choice1 = choice1;
        this.middlePara = middlePara;
        this.choice2 = choice2;
        this.id = id;
        this.type = type;
    }

    public String getChoice1(){
        return this.choice1;
    }

    public String getMiddlePara(){
        return this.middlePara;
    }

    public String getChoice2(){
        return this.choice2;
    }

    public int getId() {
        return this.id;
    }

    public int getType() {
        return this.type;
    }
}