public class Space {

    private String habitat;
    private String type; // challenge, natural disaster, danger zone, start, finish, carnivore, herbivore

    public Space(String habitat, String type) {
        this.habitat = habitat;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public String getHabitat() {
        return habitat;
    }
}
