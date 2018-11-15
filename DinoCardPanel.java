import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class DinoCardPanel extends JPanel {
    // static card level components

    // title label
    private JLabel title;

    // info labels
    private JLabel labelName;
    private JLabel labelDiet;
    private JLabel labelHabitat;

    // stat labels
    private JLabel labelSpe;
    private JLabel labelSiz;
    private JLabel labelInt;
    private JLabel labelDef;
    private JLabel labelWep;
    private JLabel labelSen;
    private JLabel labelROR;
    private JLabel labelATA;

    // dynamic card level components

    // info labels
    private JLabel dinoName;
    private JLabel dinoDiet;
    private JLabel dinoHabitat;

    // stat header
    private JLabel labelStatHeader;

    // stat value labels
    private JLabel dinoSpe;
    private JLabel dinoSiz;
    private JLabel dinoInt;
    private JLabel dinoDef;
    private JLabel dinoWep;
    private JLabel dinoSen;
    private JLabel dinoROR;
    private JLabel dinoATA;

    // image label
    private JLabel imageLabel;

    // half card components
    private JLabel vsLabel;
    private JLabel hLabelImage;
    private JLabel hLabelName;
    private JLabel hLabelStat;
    private JLabel hLabelValue;

    // utility variables
    private final String compareCardName = "compare";
    private CardLayout cl;
    private JPanel compareCard;
    private Player[] players;
    private Image icon;
    private Image sizeCompareToHuman;

    // constructor
    public DinoCardPanel(Player[] players) {
        this.players = players;
        try {
            setup();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // initialize and set up top component
    private void setup() throws IOException {
        // use card layout
        cl = new CardLayout();
        setLayout(cl);

        sizeCompareToHuman = ImageIO.read(getClass().getResource("/resources/Styrac_Test_Wide.jpg")).getScaledInstance(320, 180, Image.SCALE_SMOOTH);
        icon = ImageIO.read(getClass().getResource("/resources/Styrac_Test_Square.jpg")).getScaledInstance(100, 100, Image.SCALE_SMOOTH);

        // add a card for each dino
        for (int i = 0; i < players.length; i++) {
            add(createCard(players[i]), players[i].getDino().getName());
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
        GridBagConstraints gbc;

        // attacker half card
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        card.add(createHalfCard(attacker, stat), gbc);

        // VERSUS
        vsLabel = new JLabel("IS ATTACKING");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        card.add(vsLabel, gbc);

        // defender half card
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        card.add(createHalfCard(defender, stat), gbc);

        return card;
    }

    private JPanel createHalfCard(Player p, String stat) {
        Dinosaur d = p.getDino();

        // create return value and layout tools
        JPanel halfCard = new JPanel(new GridBagLayout());
        GridBagConstraints gbc;

        // icon label
        hLabelImage = new JLabel(new ImageIcon(icon));
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 2;
        halfCard.add(hLabelImage, gbc);

        // name
        hLabelName = new JLabel(d.getName());
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        halfCard.add(hLabelName, gbc);

        // stat
        hLabelStat = new JLabel(stat.toUpperCase() + ": ");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        halfCard.add(hLabelStat, gbc);

        // value
        String str;
        switch (stat.toUpperCase()) {
            case "SPEED":
                str = getMinusZeroPlus(d.getSpeed());
                break;
            case "SIZE":
                str = getMinusZeroPlus(d.getSize());
                break;
            case "INTELLIGENCE":
                str = getMinusZeroPlus(d.getIntelligence());
                break;
            case "DEFENSES":
                str = getMinusZeroPlus(d.getDefenses());
                break;
            case "WEAPONS":
                str = getMinusZeroPlus(d.getWeapons());
                break;
            case "SENSES":
                str = getMinusZeroPlus(d.getSenses());
                break;
            case "ROR":
                str = getMinusZeroPlus(d.getRor());
                break;
            case "ATA":
                str = getMinusZeroPlus(d.getAta());;
                break;
            case "HABITAT":
                str = d.getHabitat();
                break;
            default:
                str = "X";
                break;
        }
        hLabelValue = new JLabel(str);
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 1;
        halfCard.add(hLabelValue, gbc);

        return halfCard;
    }

    private JPanel createCard(Player p) {
        Dinosaur d = p.getDino();

        // create return value and layout tools
        JPanel card = new JPanel(new GridBagLayout());
        GridBagConstraints gbc;

        // static label configuration

        // title label
        title = new JLabel("DINOSAUR DATA CARD");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        card.add(title, gbc);
        // info labels
        // labelName
        labelName = new JLabel("Name:");
        gbc = gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        card.add(labelName, gbc);
        // labelDiet
        labelDiet = new JLabel("Diet:");
        gbc = gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        card.add(labelDiet, gbc);
        // labelHabitat
        labelHabitat = new JLabel("Habitat:");
        gbc = gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        card.add(labelHabitat, gbc);
        // stat labels
        // labelSpe
        labelSpe = new JLabel("SPEED");
        gbc = gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        card.add(labelSpe, gbc);
        // labelSiz
        labelSiz = new JLabel("SIZE");
        gbc = gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        card.add(labelSiz, gbc);
        // labelInt
        labelInt = new JLabel("INTELLIGENCE");
        gbc = gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        card.add(labelInt, gbc);
        // labelDef
        labelDef = new JLabel("DEFENSES");
        gbc = gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 8;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        card.add(labelDef, gbc);
        // labelWep
        labelWep = new JLabel("WEAPONS");
        gbc = gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 9;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        card.add(labelWep, gbc);
        // labelSen
        labelSen = new JLabel("SENSES");
        gbc = gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 10;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        card.add(labelSen, gbc);
        // labelROR
        labelROR = new JLabel("RATE OF REPRODUCTION");
        gbc = gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 11;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        card.add(labelROR, gbc);
        // labelATA
        labelATA = new JLabel("ABILITY TO ADAPT");
        gbc = gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 12;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        card.add(labelATA, gbc);

        // dynamic label configuration

        // info labels
        // dinoName
        dinoName = new JLabel(d.getName());
        gbc = gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        card.add(dinoName, gbc);
        // dinoDiet
        dinoDiet = new JLabel((d.isHerbivore()) ? "Herbivore" : "Carnivore");
        gbc = gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        card.add(dinoDiet, gbc);
        // dinoHabitat
        dinoHabitat = new JLabel(d.getHabitat());
        gbc = gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        card.add(dinoHabitat, gbc);
        // stat header
        labelStatHeader = new JLabel(d.getName() + " Statistics:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 3;
        gbc.anchor = GridBagConstraints.WEST;
        card.add(labelStatHeader, gbc);
        // stat labels
        // dinoSpe
        dinoSpe = new JLabel(getMinusZeroPlus(d.getSpeed()));
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.WEST;
        card.add(dinoSpe, gbc);
        // dinoSiz
        dinoSiz = new JLabel(getMinusZeroPlus(d.getSize()));
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.anchor = GridBagConstraints.WEST;
        card.add(dinoSiz, gbc);
        // dinoInt
        dinoInt = new JLabel(getMinusZeroPlus(d.getIntelligence()));
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.anchor = GridBagConstraints.WEST;
        card.add(dinoInt, gbc);
        // dinoDef
        dinoDef = new JLabel(getMinusZeroPlus(d.getDefenses()));
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.anchor = GridBagConstraints.WEST;
        card.add(dinoDef, gbc);
        // dinoWep
        dinoWep = new JLabel(getMinusZeroPlus(d.getWeapons()));
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 9;
        gbc.anchor = GridBagConstraints.WEST;
        card.add(dinoWep, gbc);
        // dinoSen
        dinoSen = new JLabel(getMinusZeroPlus(d.getSenses()));
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 10;
        gbc.anchor = GridBagConstraints.WEST;
        card.add(dinoSen, gbc);
        // dinoROR
        dinoROR = new JLabel(getMinusZeroPlus(d.getRor()));
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 11;
        gbc.anchor = GridBagConstraints.WEST;
        card.add(dinoROR, gbc);
        // dinoATA
        dinoATA = new JLabel(getMinusZeroPlus(d.getAta()));
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 12;
        gbc.anchor = GridBagConstraints.WEST;
        card.add(dinoATA, gbc);
        // image label
        imageLabel = new JLabel(new ImageIcon(sizeCompareToHuman));
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 13;
        gbc.gridwidth = 3;
        card.add(imageLabel, gbc);

        return card;
    }

    private String getMinusZeroPlus(int statValue) {
        switch(statValue) {
            case -1: return "-";
            case 0: return "0";
            case 1: return "+";
        }
        return "X";
    }
}
