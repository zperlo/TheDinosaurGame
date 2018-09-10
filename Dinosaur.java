// Dinosaur Description Card

public class Dinosaur {
   public String name;
   public boolean isHerbivore; // Herbivore or Carnivore
   public String habitat;
   
   // Characteristics:
   public int speed;
   public int size;
   public int intelligence;
   public int defenses;
   public int weapons;
   public int senses;
   public int ror; // Rate of Reproduction
   public int ata; // Ability to adapt
   
   public Dinosaur(String name, boolean isHerbivore, String habitat, int speed, int size, int intelligence,
                   int defenses, int weapons, int senses, int ror, int ata) {
      this.name = name;
      this.isHerbivore = isHerbivore;
      this.habitat = habitat;
      this.speed = speed;
      this.size = size;
      this.intelligence = intelligence;
      this.defenses = defenses;
      this.weapons = weapons;
      this.senses = senses;
      this.ror = ror;
      this.ata = ata;
   }
}