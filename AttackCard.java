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