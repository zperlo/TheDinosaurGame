public class Player {
    private Dinosaur myDino;
    private int foodTokens;
    private int location = 0;
    private boolean evolveCardSenInt = false;
    private boolean evolveCardSpdSiz = false;
    private int lostTurns = 0;

    public Player(Dinosaur myDino, int foodTokens){
        this.myDino = myDino;
        this.foodTokens=foodTokens;
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

    public boolean isEvolveCardSenInt(){
        return evolveCardSenInt;
    }

    public boolean isEvolveCardSpdSiz(){
        return evolveCardSpdSiz;
    }

    public int getLostTurns() {
        return lostTurns;
    }

    public void move(int numSpaces){
        location = location + numSpaces;
    }

    public void changeFood(int numChange){
        foodTokens = foodTokens + numChange;
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
}
