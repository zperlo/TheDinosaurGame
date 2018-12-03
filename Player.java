/**
 * Player is a class to represent a single player, including their dinosaur, location, and food tokens.
 *
 * @author Tyler Anderson
 * @version 1.0
 * @since 2018-10-18
 */
public class Player {
    private Dinosaur myDino;
    private int foodTokens;
    private Space[] board;
    private int location = 0;
    private Space space = null;
    private boolean evolveCardSenInt = false;
    private boolean evolveCardSpdSiz = false;
    private int lostTurns = 0;
    private boolean secondChance = true;
    private boolean isExtinct = false;

    /**
     * Constructor to initialize the player attributes.
     * @param myDino This is the dinosaur that the player is playing as.
     * @param foodTokens This is the number of food tokens the player starts with.
     * @see Dinosaur
     */
    public Player(Dinosaur myDino, int foodTokens, Space[] board) {
        this.myDino = myDino;
        this.foodTokens = foodTokens;
        this.board = board;
    }

    public Dinosaur getDino(){
        return myDino;
    }

    public int getFoodTokens(){
        return foodTokens;
    }

    public int getLocation(){
        return location;
    }

    public Space getSpace() {
        return space;
    }

    public boolean isEvolveCardSenInt(){
        return evolveCardSenInt;
    }

    public boolean isEvolveCardSpdSiz(){
        return evolveCardSpdSiz;
    }

    public int getLostTurns() {
        return lostTurns;
    }

    public boolean getSecondChance() {
        return secondChance;
    }

    public boolean isExtinct() {
        return isExtinct;
    }

    /**
     * Moves the player numSpaces spaces. If numSpaces is positive, the player moves forward that many spaces.
     * If numSpaces is negative, then the player moves backwards that many spaces.
     * @param numSpaces This is how many spaces that a player moves.
     */
    public void move(int numSpaces){
        // if the player went behind the start space, put them back on the start space
        if ((location + numSpaces) < 0) {
            location = 0;
        }

        // allow player to move so long as they don't go past the finish space
        else if (location + numSpaces <= 109) {
            location = location + numSpaces;

        }

        // if they were to move past the finish, don't let them move at all.
        // They get a chance to make it to the finish next turn if they roll the correct number

        space = board[location];
    }

    /**
     * Alters the player's count of food tokens. If numChange is positive, the player gains that many food tokens.
     * If numChange is negative, then the player loses that many food tokens.
     * @param numChange This is how many food tokens are added or taken away.
     */
    public void changeFood(int numChange){
        foodTokens = foodTokens + numChange;
        if (foodTokens < 0)
            foodTokens = 0;
    }

    public void setEvolveCardSenInt(boolean set){
        evolveCardSenInt = set;
    }

    public void setEvolveCardSpdSiz(boolean set){
        evolveCardSpdSiz = set;
    }

    public void setLostTurns(int newLostTurns) {
        lostTurns = newLostTurns;
    }

    public void setSecondChance(boolean secondChance) {
        this.secondChance = secondChance;
    }

    public void setFoodTokens(int foodTokens) {
        this.foodTokens = foodTokens;
    }

    public void setExtinct(boolean extinct) {
        isExtinct = extinct;
    }

    public boolean isInHabitat() {
        return getDino().getHabitat().equalsIgnoreCase(getSpace().getHabitat());
    }
}
