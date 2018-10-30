/**
 * This is a data type that stores aspects of an Attack Card.
 * This is what is drawn when an attack situation occurs,
 * when a player lands on a space occupied by another player.
 *
 * @author Zach Perlo
 * @version 1.0
 * @since 2018-9-20
 */

public class AttackCard {
    private String para1;
    private String para2;
    private String stat; //one of below:
    // 0-7 are normal stats, 8 is in habitat
    //{"speed", "size", "intelligence", "defenses",
    //    "weapons", "senses", "ror", "ata", "habitat"}
    private String penalty; //either move, food
    private int penaltyAmount; //amount of spaces moved (food is always 1)
    private int winner; //0 if loser moves, 1 if winner moves

    /**
     * Constructor to initialize an attack card.
     * @param para1 The first paragraph of the card.
     * @param para2 The second paragraph of the card. Stored separately for simplicity in representing on the GUI.
     * @param stat The dinosaur stat that is compared between the two attacking players.
     * @param penalty What is given up or gained (moving or food tokens).
     * @param penaltyAmount How many spaces the appropriate player moves (food tokens are always 1).
     * @param winner Represents whether the loser moves back or the winner moves forward.
     */
    public AttackCard(String para1, String para2, String stat, String penalty, int penaltyAmount, int winner){
        this.para1 = para1;
        this.para2 = para2;
        this.stat = stat;
        this.penalty = penalty;
        this.penaltyAmount = penaltyAmount;
        this.winner = winner;
    }

    public String getPara1(){
        return this.para1;
    }

    public String getPara2(){
        return this.para2;
    }

    public String getStat(){
        return this.stat;
    }

    public String getPenalty(){
        return this.penalty;
    }

    public int getPenaltyAmount(){
        return this.penaltyAmount;
    }

    public int getWinner() {
        return this.winner;
    }
}