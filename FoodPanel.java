import javax.swing.*;
import java.awt.*;

public class FoodPanel extends JPanel {
    // lower level components
    private SubFoodPanel subPanel;

    // utility variables
    private Player[] players;
    private Color[] colors = {new Color(230, 59, 46), new Color(67, 124, 144), new Color(143, 206, 41), new Color (255, 119, 51)};
    private SubFoodPanel[] subPanels;
    private JLabel title;
    private IconRef ir;
    private Color bg;

    // constructor
    public FoodPanel(Player[] players, IconRef ir, Color bg) {
        this.players = players;
        this.ir = ir;
        this.bg = bg;
        setBackground(bg);
        subPanels = new SubFoodPanel[players.length];
        setup();
    }

    // initialize and set up top level component
    private void setup() {
        // layout tools
        setLayout(new GridBagLayout());
        GridBagConstraints gbc;

        // add lower level elements
        title = new JLabel("<html><center>FOOD<br>TOKENS</html>");
        title.setFont(new Font("Showcard Gothic", Font.PLAIN, (int) (title.getFont().getSize() * 2.5)));
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(7, 7, 7, 7);
        add(title, gbc);

        for (int i = 0; i < players.length; i++) {
            subPanel = new SubFoodPanel(players[i], colors[i], ir, bg);
            gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = i + 1;
            gbc.insets = new Insets(7, 7, 7, 7);
            add(subPanel, gbc);
            subPanels[i] = subPanel;
        }
    }

    public void updateFood() {
        for (SubFoodPanel s : subPanels) {
            s.updateFood();
        }
    }

    public void assertExtinct(Player p) {
        for (int i = 0; i < players.length; i++) {
            if (players[i].equals(p)) {
                subPanels[i].assertExtinct();
                break;
            }
        }
    }
}
