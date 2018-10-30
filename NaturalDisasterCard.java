/**
 * This is a data type that stores aspects of a natural disaster card.
 * This is what is drawn when a player lands on a natural disaster space.
 *
 * @author Zach Perlo
 * @version 1.0
 * @since 2018-10-11
 */

public class NaturalDisasterCard {
    private String para1;
    private String para2;
    private String para3;
    private boolean habitatSafe;
    private int[] safeStatValues = new int[0];
    private int foodTokensLost = 0;
    private String stat; //one of below:
    // 0-7 are normal stats, 8 is no stat required
    //{"speed", "size", "intelligence", "defenses",
    //    "weapons", "senses", "ror", "ata", "none"}

    /**
     * Constructor
     * @param para1 The first paragraph of the card's description.
     * @param para2 The second paragraph of the card's description.
     * @param para3 The third paragraph of the card's description.
     *              These paragraphs are stored separately to make it easier
     *              for the GUI to display.
     * @param habitatSafe Whether or not the player is safe from the effect if they are in their dinosaur habitat.
     * @param stat The stat of the dinosaur that is compared to see if the player is safe from the effect.
     */
    public NaturalDisasterCard(String para1, String para2, String para3, boolean habitatSafe, String stat){
        this.para1 = para1;
        this.para2 = para2;
        this.para3 = para3;
        this.habitatSafe = habitatSafe;
        this.stat = stat;
    }

    /**
     * Constructor
     * @param para1 The first paragraph of the card's description.
     * @param para2 The second paragraph of the card's description.
     * @param para3 The third paragraph of the card's description.
     *              These paragraphs are stored separately to make it easier
     *              for the GUI to display.
     * @param habitatSafe Whether or not the player is safe from the effect if they are in their dinosaur habitat.
     * @param stat The stat of the dinosaur that is compared to see if the player is safe from the effect.
     * @param safeStatValues The possible stat values (-1, 0, 1) that allow the player to be safe from the card's effect.
     * @param foodTokensLost The number of food tokens the player loses if they are not safe from the effect of the card.
     */
    public NaturalDisasterCard(String para1, String para2, String para3, boolean habitatSafe, String stat,
                               int[] safeStatValues, int foodTokensLost){
        this.para1 = para1;
        this.para2 = para2;
        this.para3 = para3;
        this.habitatSafe = habitatSafe;
        this.stat = stat;
        this.safeStatValues = safeStatValues;
        this.foodTokensLost = foodTokensLost;
    }

    public String getPara1(){
      return this.para1;
    }
    public String getPara2(){
        return this.para2;
    }
    public String getPara3(){
        return this.para3;
    }

    public boolean getHabitatSafe(){
      return this.habitatSafe;
    }

    public String getStat(){
      return this.stat;
    }

    public int[] getSafeStatValues(){
      return this.safeStatValues;
    }

    public int getFoodLost(){
      return this.foodTokensLost;
    }
}