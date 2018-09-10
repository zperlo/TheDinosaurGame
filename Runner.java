public class Runner {
   public static void main(String[] args) {
      Dinosaur[] dinoCards = new Dinosaur[16];
      
      dinoCards[0].name = "Parasaurolophus";
      dinoCards[0].isHerbivore = true;
      dinoCards[0].habitat = "Forest";
      dinoCards[0].speed = 0;
      dinoCards[0].size = 0;
      dinoCards[0].intelligence = -1;
      dinoCards[0].defenses = -1;
      dinoCards[0].weapons = -1;
      dinoCards[0].senses = 0;
      dinoCards[0].ror = 1;
      dinoCards[0].ata = 1;
      
      dinoCards[1].name = "Triceratops";
      dinoCards[1].isHerbivore = true;
      dinoCards[1].habitat = "Plains";
      dinoCards[1].speed = 0;
      dinoCards[1].size = 0;
      dinoCards[1].intelligence = 0;
      dinoCards[1].defenses = 1;
      dinoCards[1].weapons = 0;
      dinoCards[1].senses = 1;
      dinoCards[1].ror = -1;
      dinoCards[1].ata = -1;
      
      dinoCards[2].name = "Velociraptor";
      dinoCards[2].isHerbivore = false;
      dinoCards[2].habitat = "Desert";
      dinoCards[2].speed = 1;
      dinoCards[2].size = -1;
      dinoCards[2].intelligence = 1;
      dinoCards[2].defenses = 0;
      dinoCards[2].weapons = 1;
      dinoCards[2].senses = 0;
      dinoCards[2].ror = 0;
      dinoCards[2].ata = -1;
      
      dinoCards[3].name = "Parasaurolophus";
      dinoCards[3].isHerbivore = true;
      dinoCards[3].habitat = "Forest";
      dinoCards[3].speed = 0;
      dinoCards[3].size = 0;
      dinoCards[3].intelligence = -1;
      dinoCards[3].defenses = -1;
      dinoCards[3].weapons = -1;
      dinoCards[3].senses = 0;
      dinoCards[3].ror = 1;
      dinoCards[3].ata = 1;
   }
}