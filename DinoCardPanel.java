import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.io.IOException;
import java.util.HashMap;

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

    // constructor
    public DinoCardPanel(Player[] players, IconRef ir, Color bg) {
        this.players = players;
        this.ir = ir;
        this.bg = bg;
        setBackground(bg);
        setup();
    }

    // initialize and set up top component
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

    public void show(Dinosaur d) {
        cl.show(this, d.getName());
    }

    public void showComparison (Player attacker, Player defender, String stat) {
        compareCard = createCompareCard(attacker, defender, stat);
        add(compareCard, compareCardName);
        cl.show(this, compareCardName);
    }

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

    private DinoCard createCard(Player p) {
        DinoCard card = new DinoCard(p, ir, bg);
        return card;
    }

    public void evolve(Player p, int i) {
        dinoCards.get(p).evolve(i);
    }
}
