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

    public NaturalDisasterCard(String para1, String para2, String para3, boolean habitatSafe, String stat){
        this.para1 = para1;
        this.para2 = para2;
        this.para3 = para3;
        this.habitatSafe = habitatSafe;
        this.stat = stat;
    }

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