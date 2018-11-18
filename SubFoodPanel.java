import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;

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
        setup();
    }

    private void setup() {
        // layout tools
        setLayout(new GridBagLayout());
        GridBagConstraints gbc;

        // icon label
        iconLabel = new JLabel(ir.getIcon(player.getDino()));
        iconLabel.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(7, 7, 7, 0);
        add(iconLabel, gbc);

        // food label
        foodLabel = new JLabel(String.valueOf(player.getFoodTokens()));
        foodLabel.setForeground(color);
        foodLabel.setFont(new Font(foodLabel.getFont().getName(), Font.BOLD, foodLabel.getFont().getSize() * 5));
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.insets = new Insets(7, 7, 7, 7);
        add(foodLabel, gbc);

        // border
        setBorder(BorderFactory.createLineBorder(color, 2));
    }

    public void updateFood() {
        foodLabel.setText(String.valueOf(player.getFoodTokens()));
    }
}
