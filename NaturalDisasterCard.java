public class NaturalDisasterCard {
   private String text;
   private int habitatSafe;
   private String stat;
   private int[] safeStatValues = new int[0];
   private int foodTokensLost = 0;
   // 0-7 are normal stats, 8 is no stat required
   private String[] stats = {"Speed", "something1", "something2", "something3", "something4", "something5", "something6", "none"};
   
   public NaturalDisasterCard(String text, int habitatSafe, int statNum){
      this.text = text;
      this.habitatSafe = habitatSafe;
      this.stat = this.stats[statNum];
   }
   
   public NaturalDisasterCard(String text, int habitatSafe, int statNum, int[] safeStatValues, int foodTokensLost){
      this.text = text;
      this.habitatSafe = habitatSafe;
      this.stat = this.stats[statNum];
      this.safeStatValues = safeStatValues;
      this.foodTokensLost = foodTokensLost;
   }
   
   public String getText(){
      return this.text;
   }
   
   public int getHabitatSafe(){
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
   
   public static void main(String[] potato){
      int[] safeStats = {0, 1};
      NaturalDisasterCard flood = new NaturalDisasterCard("Flood, watch out", 0, 0, safeStats, 3);
      System.out.println("If you have no " + flood.getStat() + " then you lose " + flood.getFoodLost() + " food tokens");
   }
}