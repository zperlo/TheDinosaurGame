public class NaturalDisasterCard {
   public String text;
   public boolean habitatSafe;
   public int stat; // 0-7 are normal stats, 8 is no stat required
   public int[] safeStatValues;
   public int foodTokensLost;
   
   public NaturalDisasterCard(String text, boolean habitatSafe, int stat){
      this.text = text;
      this.habitatSafe = habitatSafe;
      this.stat = stat;
   }
   
   public NaturalDisasterCard(String text, boolean habitatSafe, int stat, int[] safeStatValues, int foodTokensLost){
      this.text = text;
      this.habitatSafe = habitatSafe;
      this.stat = stat;
      this.safeStatValues = safeStatValues;
      this.foodTokensLost = foodTokensLost;
   }
}