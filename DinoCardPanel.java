import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.util.HashMap;

/**
 * A JPanel container for DinoCards that uses CardLayout
 *
 * @author Jacob Rich
 * @version 1.11
 * @since 2018-21-6
 */
public class DinoCardPanel extends JPanel {


    // half card components
    private JLabel vsLabel;
    private JLabel hLabelImage;
    private JLabel hLabelName;
    private JLabel hLabelStat;
    private JLabel hLabelValue;
    private JLabel hLabelHab;
    private JLabel hLabelHabValue;

    // utility variables
    private final String compareCardName = "compare";
    private CardLayout cl;
    private JPanel compareCard;
    private Player[] players;
    private HashMap<Player, DinoCard> dinoCards;
    private IconRef ir;
    private Color bg;

    /**
     * Sets global variables and the background color
     *
     * @param players the players
     * @param ir a preloaded IconRef
     * @param bg the background color
     */
    public DinoCardPanel(Player[] players, IconRef ir, Color bg) {
        this.players = players;
        this.ir = ir;
        this.bg = bg;
        setBackground(bg);
        setup();
    }

    /**
     * Creates and places all the components
     *
     * @see java.awt.CardLayout
     * @see java.util.HashMap
     * @see Player
     * @see Dinosaur
     */
    private void setup() {
        // use card layout
        cl = new CardLayout();
        setLayout(cl);

        // add a card for each dino
        dinoCards = new HashMap<>();
        for (int i = 0; i < players.length; i++) {
            DinoCard card = createCard(players[i]);
            dinoCards.put(players[i], card);
            add(card, players[i].getDino().getName());
        }

        compareCard = createCompareCard(players[0], players[0], "speed");
        add(compareCard, compareCardName);
    }

    /**
     * Shows a particular dinosaur's corresponding card
     *
     * @param d the dinosaur to be shown
     * @see Dinosaur
     */
    public void show(Dinosaur d) {
        cl.show(this, d.getName());
    }

    /**
     * Shows a comparison between two dinosaur's stats
     *
     * @param attacker the attacking player
     * @param defender the defending player
     * @param stat the relevant stat
     * @see java.awt.CardLayout
     */
    public void showComparison (Player attacker, Player defender, String stat) {
        compareCard = createCompareCard(attacker, defender, stat);
        add(compareCard, compareCardName);
        cl.show(this, compareCardName);
    }

    /**
     * Creates and places the components of the comparison card
     *
     * @param attacker the attacking player
     * @param defender the defending player
     * @param stat the relevant stat
     * @return a JPanel containing the elements of the comparison card
     * @see javax.swing.JPanel
     * @see java.awt.GridBagLayout
     * @see java.awt.GridBagConstraints
     */
    private JPanel createCompareCard(Player attacker, Player defender, String stat) {
        // create return value and layout tools
        JPanel card = new JPanel(new GridBagLayout());
        card.setBackground(bg);
        GridBagConstraints gbc;

        // attacker half card
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(7, 7, 7, 7);
        card.add(createHalfCard(attacker, stat), gbc);

        // VERSUS
        vsLabel = new JLabel("IS ATTACKING");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets(7, 7, 7, 7);
        card.add(vsLabel, gbc);

        // defender half card
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.insets = new Insets(7, 7, 7, 7);
        card.add(createHalfCard(defender, stat), gbc);

        return card;
    }

    /**
     * Creates and places the components of each half card of the comparison card.
     *
     * @param p the player to make the half card with
     * @param stat the relevant stat
     * @return A JPanel containing all thee elements of the half card
     * @see Player
     * @see java.util.HashMap
     * @see javax.swing.JPanel
     * @see java.awt.GridBagLayout
     * @see java.awt.GridBagConstraints
     * @see IconRef
     * @see javax.swing.BorderFactory
     */
    private JPanel createHalfCard(Player p, String stat) {
        Dinosaur d = p.getDino();
        DinoCard statCard = dinoCards.get(p);

        // create return value and layout tools
        JPanel halfCard = new JPanel(new GridBagLayout());
        halfCard.setBackground(bg);
        GridBagConstraints gbc;

        // icon label
        hLabelImage = new JLabel(ir.getIcon(p.getDino()));
        hLabelImage.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        hLabelImage.setBackground(bg);
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 3;
        gbc.insets = new Insets(7, 7, 7, 7);
        halfCard.add(hLabelImage, gbc);

        // name
        hLabelName = new JLabel(d.getName());
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(7, 7, 7, 7);
        halfCard.add(hLabelName, gbc);

        // stat
        hLabelStat = statCard.getLabel(stat, false);
        if (hLabelStat != null) {
            gbc = new GridBagConstraints();
            gbc.gridx = 1;
            gbc.gridy = 1;
            gbc.insets = new Insets(7, 7, 7, 7);
            halfCard.add(hLabelStat, gbc);
        }

        // value
        hLabelValue = statCard.getLabel(stat, true);
        if (hLabelValue != null) {
            gbc = new GridBagConstraints();
            gbc.gridx = 2;
            gbc.gridy = 1;
            gbc.insets = new Insets(7, 7, 7, 7);
            halfCard.add(hLabelValue, gbc);
        }

        hLabelHab = new JLabel("HABITAT");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.insets = new Insets(7, 7, 7, 7);
        halfCard.add(hLabelHab, gbc);

        hLabelHabValue = new JLabel(d.getHabitat());
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.insets = new Insets(7, 7, 7, 7);
        halfCard.add(hLabelHabValue, gbc);

        return halfCard;
    }

    /**
     * Creates a DinoCard.
     *
     * @param p the player to create the card from
     * @return the completed DinoCard
     * @see DinoCard
     */
    private DinoCard createCard(Player p) {
        DinoCard card = new DinoCard(p, ir, bg);
        return card;
    }

    /**
     * Passes an evolution request up the stack
     * @param p the player whose dino is to evolve
     * @param i an int indicating the type of evolution
     * @see java.util.HashMap
     * @see DinoCard
     */
    public void evolve(Player p, int i) {
        dinoCards.get(p).evolve(i);
    }
}
