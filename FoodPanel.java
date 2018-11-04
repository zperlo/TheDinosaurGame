import javax.swing.*;
import java.awt.*;

public class FoodPanel extends JPanel {
    // lower level components
    private SubFoodPanel subPanel;

    // utility variables
    private Player[] players;
    private Color[] colors = {Color.red, Color.blue, Color.green, Color.yellow};
    private SubFoodPanel[] subPanels;

    // constructor
    public FoodPanel(Player[] players) {
        this.players = players;
        subPanels = new SubFoodPanel[players.length];
        setup();
    }

    // initialize and set up top level component
    private void setup() {
        // layout tools
        setLayout(new GridBagLayout());
        GridBagConstraints gbc;

        // add lower level elements
        for (int i = 0; i < players.length; i++) {
            gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = i;
            subPanel = new SubFoodPanel(players[i], colors[i]);
            subPanels[i] = subPanel;
            add(subPanel, gbc);
        }
    }

    public void updateFood() {
        for (SubFoodPanel s : subPanels) {
            s.updateFood();
        }
    }
}
