import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class SubFoodPanel extends JPanel {
    // lower level components
    private JLabel iconLabel;
    private JLabel foodLabel;

    // utility variables
    private Player player;
    private Color color;
    private IconRef ir = new IconRef();

    // constructor
    public SubFoodPanel(Player player, Color color) {
        this.player = player;
        this.color = color;
        try {
            setup();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setup() throws IOException {
        // layout tools
        setLayout(new GridBagLayout());
        GridBagConstraints gbc;

        // icon label
        iconLabel = new JLabel(ir.getIcon(player.getDino()));
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(iconLabel, gbc);

        // food label
        foodLabel = new JLabel(String.valueOf(player.getFoodTokens()));
        foodLabel.setForeground(color);
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        add(foodLabel, gbc);

        // border
        setBorder(BorderFactory.createLineBorder(color, 2));
    }

    public void updateFood() {
        foodLabel.setText(String.valueOf(player.getFoodTokens()));
    }
}
