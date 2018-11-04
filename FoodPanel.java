import javax.swing.*;
import java.awt.*;

public class FoodPanel extends JPanel {
    // lower level components
    private JPanel display;
    private JLabel iconLabel;
    private JLabel foodLabel;

    // utility variables
    private Player[] players;
    private Color[] colors = {Color.red, Color.blue, Color.green, Color.yellow};
    private JPanel[] displays;

    // constructor
    public FoodPanel(Player[] players) {
        this.players = players;
        displays = new JPanel[players.length];
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
            display = createDisplay(players[i], colors[i]);
            displays[i] = display;
            add(display, gbc);
        }
    }

    private JPanel createDisplay(Player p, Color c) {
        // layout tools
        JPanel j = new JPanel(new GridBagLayout());
        GridBagConstraints gbc;

        // icon label
        iconLabel = new JLabel(new ImageIcon(getClass().getResource("/com/sun/deploy/resources/image/aboutjava.png")));
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        j.add(iconLabel, gbc);

        // food label
        foodLabel = new JLabel(String.valueOf(p.getFoodTokens()));
        foodLabel.setForeground(c);
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        j.add(foodLabel, gbc);

        // border
        j.setBorder(BorderFactory.createLineBorder(c, 2));

        return j;
    }
}
