/**
 * Space is a data type class that stores all attributes of one space on the game board.
 *
 * @author Dylan Briggs
 * @version 1.0
 * @since 2018-10-8
 */

public class Space {

    private String habitat; // Possible values: plains, forest, desert, swamp
    private String type; // Possible values: challenge, natural disaster, danger zone, start, finish, carnivore, herbivore

    /**
     * Constructor to initialize an instance of Space.
     * @param habitat This is a string representing which habitat the space is tied to.
     * @param type This is a string representing what happens when a player lands on this space.
     */
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
