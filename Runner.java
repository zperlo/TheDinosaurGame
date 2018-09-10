public class Runner {
   public static void main(String[] args) {
      Dinosaur[] dinoCards = new Dinosaur[16];
      
      dinoCards[0] = new Dinosaur("Velociraptor", false, "Desert", 1,-1,
              1,0,1,0,0,-1);
      System.out.println("Player 1 is a " + dinoCards[0].name);
   }
}