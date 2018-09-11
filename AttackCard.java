public class AttackCard {
   private String text;
   private String stat; //one of below:
   // 0-7 are normal stats, 8 is no stat required
   //{"speed", "size", "intelligence", "defenses",
   //    "weapons", "senses", "ror", "none"}
   private String penalty; //either move back or lose food
   private int penaltyAmount; //amount of spaces moved or food lost
   
   public AttackCard(String text, String stat, String penalty, int penaltyAmount){
      this.text = text;
      this.stat = stat;
      this.penalty = penalty;
      this.penaltyAmount = penaltyAmount;
   }
   
   public String getText(){
      return this.text;
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
}