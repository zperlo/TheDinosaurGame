public class Space {

    private String habitat;
    private String currentType; // challenge, natural disaster, danger zone, attack, start, or finish
    private String trueType; // whatever it was without something occupying it

    public Space(String habitat, String trueType) {
        this.habitat = habitat;
        this.trueType = trueType;
    }

    public void occupy() {
        currentType = "attack";
    }

    public void leave() {
        currentType = trueType;
    }

    public String getCurrentType() {
        return currentType;
    }

    public String getHabitat() {
        return habitat;
    }
}
