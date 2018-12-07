import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;

/**
 * An individual dinosaur's food token display
 *
 * @author Jacob Rich
 * @version 1.10
 * @since 2018-12-7
 */
public class SubFoodPanel extends JPanel {
    // lower level components
    private JLabel iconLabel;
    private JLabel foodLabel;

    // utility variables
    private Player player;
    private Color color;
    private IconRef ir;
    private Color bg;

    /**
     * Set global variables and the background color
     *
     * @param player the player associated with the display
     * @param color the color associated with the display
     * @param ir a preloaded IconRef
     * @param bg the background color
     */
    public SubFoodPanel(Player player, Color color, IconRef ir, Color bg) {
        this.player = player;
        this.color = color;
        this.ir = ir;
        this.bg = bg;
        setBackground(bg);
        setup();
    }

    /**
     * Creates and places all the components of the display
     *
     * @see java.awt.GridBagLayout
     * @see java.awt.GridBagConstraints
     * @see javax.swing.JLabel
     * @see IconRef
     * @see Player
     * @see javax.swing.BorderFactory
     * @see java.lang.String
     * @see java.awt.Font
     */
    private void setup() {
        // layout tools
        setLayout(new GridBagLayout());
        GridBagConstraints gbc;

        // icon label
        iconLabel = new JLabel(ir.getIcon(player.getDino()));
        iconLabel.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        iconLabel.setBackground(bg);
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
        setBorder(BorderFactory.createLineBorder(color, 4));
    }

    /**
     * Update the food count on the display
     *
     * @see javax.swing.JLabel
     * @see java.lang.String
     * @see Player
     */
    public void updateFood() {
        foodLabel.setText(String.valueOf(player.getFoodTokens()));
    }

    /**
     * Set the icon to the extinct version
     *
     * @see javax.swing.JLabel
     * @see IconRef
     * @see Player
     */
    public void assertExtinct() {
        iconLabel.setIcon(ir.getIconWithX(player.getDino()));
    }
}
