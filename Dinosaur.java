/**
 * Data type for Dinosaur Description Card. This stores all attributes of a particular species of dinosaur.
 *
 * @author Dylan Briggs
 * @version 1.0
 * @since 2018-10-11
 */

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

    /**
     * Constructor for initializing a Dinosaur with all its attributes.
     * @param name This is the dinosaur species.
     * @param isHerbivore This is a boolean to store whether this dinosaur is an herbivore or carnivore.
     * @param habitat This is what terrain the dinosaur's habitat is.
     * @param speed This is the speed ability, either -1, 0, or 1
     * @param size This is the size ability, either -1, 0, or 1
     * @param intelligence This is the intelligence ability, either -1, 0, or 1
     * @param defenses This is the defenses ability, either -1, 0, or 1
     * @param weapons This is the weapons ability, either -1, 0, or 1
     * @param senses This is the senses ability, either -1, 0, or 1
     * @param ror This is the rate of reproduction, either -1, 0, or 1
     * @param ata This is the ability to adapt, either -1, 0, or 1
     */
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