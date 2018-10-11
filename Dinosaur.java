// Dinosaur Description Card

public class Dinosaur {
   private String name;
   private boolean isHerbivore; // Herbivore or Carnivore
   private String habitat;
   
   // Characteristics:
   private int speed;
   private int size;
   private int intelligence;
   private int defenses;
   private int weapons;
   private int senses;
   private int ror; // Rate of Reproduction
   private int ata; // Ability to adapt
   
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

    public boolean isHerbivore() {
        return isHerbivore;
    }

    public int getAta() {
        return ata;
    }

    public int getDefenses() {
        return defenses;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public int getRor() {
        return ror;
    }

    public int getSenses() {
        return senses;
    }

    public int getSize() {
        return size;
    }

    public int getSpeed() {
        return speed;
    }

    public int getWeapons() {
        return weapons;
    }

    public String getHabitat() {
        return habitat;
    }

    public String getName() {
        return name;
    }
}