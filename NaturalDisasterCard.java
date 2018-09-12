public class NaturalDisasterCard {
   private String text;
   private boolean habitatSafe;
   private int[] safeStatValues = new int[0];
   private int foodTokensLost = 0;
   private String stat; //one of below:
   // 0-7 are normal stats, 8 is no stat required
   //{"speed", "size", "intelligence", "defenses",
   //    "weapons", "senses", "ror", "ata", "none"}
   
   public NaturalDisasterCard(String text, boolean habitatSafe, String stat){
      this.text = text;
      this.habitatSafe = habitatSafe;
      this.stat = stat;
   }
   
   public NaturalDisasterCard(String text, boolean habitatSafe, String stat, int[] safeStatValues, int foodTokensLost){
      this.text = text;
      this.habitatSafe = habitatSafe;
      this.stat = stat;
      this.safeStatValues = safeStatValues;
      this.foodTokensLost = foodTokensLost;
   }
   
   public String getText(){
      return this.text;
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
   
   public static void main(String[] potato){
      NaturalDisasterCard flood = new NaturalDisasterCard("Flood, watch out", true, "speed", new int[] {0,1}, 3);
      System.out.println("If you have no " + flood.getStat() + " then you lose " + flood.getFoodLost() + " food tokens");
   }
}