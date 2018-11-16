import javax.swing.*;
import java.awt.*;

public class FoodPanel extends JPanel {
    // lower level components
    private SubFoodPanel subPanel;

    // utility variables
    private Player[] players;
    private Color[] colors = {Color.red, Color.blue, new Color(71, 209, 71), new Color (255, 204, 0)};
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
            subPanel = new SubFoodPanel(players[i], colors[i]);
            subPanels[i] = subPanel;
            gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = i;
            gbc.insets = new Insets(7, 7, 7, 7);
            add(subPanel, gbc);
        }
    }

    public void updateFood() {
        for (SubFoodPanel s : subPanels) {
            s.updateFood();
        }
    }
}
