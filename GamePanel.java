import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class GamePanel extends JPanel {
    // layout tools
    private CardLayout cl;

    // top level menu components
    private JPanel splash;
    private JLabel splashImage;
    private JLabel continueLabel;

    private JPanel mainMenuPanel;
    private JLabel titleLabel;
    private JLabel brachImage;
    private JLabel tRexImage;
    private JLabel playRulesQuitLabel;
    private JLabel triPlayImage;
    private boolean playbool;
    private JLabel triRulesImage;
    private boolean rulesbool;
    private JLabel triQuitImage;
    private boolean quitbool;
    private int menuSem = 0;

    private JPanel rulesPanel;
    private JTextArea rulesText;
    private JScrollPane scroll;
    private JLabel rulesLabel;
    private JButton back;
    private int backSem;

    // top level gameplay components
    private JPanel gameplayPanel;
    private BoardPanel board;
    private DinoCardPanel dinoCards;
    private FoodPanel food;
    private JButton buttonRoll;
    private JButton buttonHelp;

    // utility variables
    private Color backColor = new Color(188, 116, 88);
    private IconRef ir;
    private Player[] players;
    private Dinosaur[] dinosaurs;
    private Space[] spaces;
    private int rollSem;
    private int splashSem;
    private final double scale;
    private int btnSem;

    // constructor
    public GamePanel(Dinosaur[] dinosaurs, double scale, Space[] spaces) {
        this.dinosaurs = dinosaurs;
        this.scale = scale;
        this.spaces = spaces;
        cl = new CardLayout();
        setLayout(cl);
        add(makeSplash(), "splash");
    }

    public Player[] executeMenu(boolean firstTime) {
        if (firstTime) {
            ir = new IconRef(scale);
            continueLabel.setForeground(Color.BLACK);
            splashSem = 0;
            while (splashSem == 0) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        add(makeMainMenu(), "main");
        add(makeRules(), "rules");

        waitAtMenu();

        add(makeGameplay(), "gameplay");

        cl.show(this, "gameplay");
        gameplayPanel.requestFocus();

        return players;
    }

    private void waitAtMenu() {
        cl.show(this, "main");
        mainMenuPanel.requestFocus();
        menuSem = 0;
        while (menuSem == 0) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        switch (menuSem) {
            case 1:
                players = initializePlayers(dinosaurs);
                if (players == null) {
                    waitAtMenu();
                }
                break;
            case 2:
                showRules("main");
                mainMenuPanel.requestFocus();
                break;
            case 3:
                System.exit(0);
                break;
            default:
                break;
        }
    }

    public void setPlayers(Player[] players) {
        this.players = players;
        add(makeGameplay(), "gameplay");
        cl.show(this, "gameplay");
    }

    private JPanel makeSplash() {
        splash = new JPanel();
        splash.setBackground(backColor);

        // layout tools
        splash.setLayout(new GridBagLayout());
        GridBagConstraints gbc;

        try {
            splashImage = new JLabel(new ImageIcon(ImageIO.read(getClass().getResource("/resources/Other/splash_image.png")).getScaledInstance((int) (1350 * scale), (int) (900 * scale), Image.SCALE_SMOOTH)));
            gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridwidth = 2;
            gbc.gridheight = 2;
            gbc.weightx = 1.0;
            gbc.weighty = 1.0;
            gbc.anchor = GridBagConstraints.SOUTHWEST;
            splash.add(splashImage, gbc);
        } catch (IOException e) {
            e.printStackTrace();
        }

        continueLabel = new JLabel("<html><center>PRESS ANY KEY<br>TO CONTINUE</html>");
        continueLabel.setFont(new Font("Showcard Gothic", Font.PLAIN, 60));
        continueLabel.setForeground(backColor.darker());
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.insets = new Insets((int) (250 * scale), 0, 0, (int) (150 * scale));
        gbc.anchor = GridBagConstraints.NORTHEAST;
        splash.add(continueLabel, gbc);

        splash.setFocusable(true);
        splash.addKeyListener(new SplashListener());

        return splash;
    }

    private JPanel makeMainMenu() {
        mainMenuPanel = new JPanel();
        mainMenuPanel.setBackground(backColor);

        mainMenuPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc;

        titleLabel = new JLabel();
        titleLabel.setFont(new Font("Showcard Gothic", Font.PLAIN, (int) (100 * scale)));
        titleLabel.setText("<html><center>THE DINOSAUR GAME<br><font size=" + (int) (50 * scale) + ">SURVIVAL OR EXTINCTION</html>");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        gbc.gridheight = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.insets = new Insets((int) (100 * scale), (int) (35 * scale), 0, 0);
        mainMenuPanel.add(titleLabel, gbc);

        tRexImage = new JLabel(ir.getMainMenuImage(0));
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 3;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.SOUTHWEST;
        mainMenuPanel.add(tRexImage, gbc);

        brachImage = new JLabel(ir.getMainMenuImage(1));
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 4;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.SOUTHEAST;
        mainMenuPanel.add(brachImage, gbc);

        triPlayImage = new JLabel(ir.getMainMenuImage(2));
        playbool = true;
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.insets = new Insets(0, 0, (int) (25 * scale), 0);
        gbc.anchor = GridBagConstraints.SOUTHEAST;
        mainMenuPanel.add(triPlayImage, gbc);

        triRulesImage = new JLabel(ir.getMainMenuImage(3));
        rulesbool = false;
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        mainMenuPanel.add(triRulesImage, gbc);

        triQuitImage = new JLabel(ir.getMainMenuImage(3));
        quitbool = false;
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.weightx = 1.0;
        gbc.weighty = 0.89;
        gbc.insets = new Insets((int) (30 * scale), 0, 0, 0);
        gbc.anchor = GridBagConstraints.NORTHEAST;
        mainMenuPanel.add(triQuitImage, gbc);

        playRulesQuitLabel = new JLabel("<html><left>PLAY<br>RULES<br>QUIT");
        playRulesQuitLabel.setFont(new Font("Showcard Gothic", Font.PLAIN, (int) (80 * scale)));
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 3;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        mainMenuPanel.add(playRulesQuitLabel, gbc);

        mainMenuPanel.setFocusable(true);
        mainMenuPanel.addKeyListener(new MainListener());

        return mainMenuPanel;
    }

    private JPanel makeRules() {
        rulesPanel = new JPanel();
        rulesPanel.setBackground(backColor);

        rulesPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc;

        rulesLabel = new JLabel("RULES");
        rulesLabel.setFont(new Font("Showcard Gothic", Font.PLAIN, (int) (75 * scale)));
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 0.25;
        rulesPanel.add(rulesLabel, gbc);

        rulesText = new JTextArea("Overview\n" +
                "Millions of years ago dinosaurs ruled the earth. They constantly searched for food, struggling to survive. In this game you become one of those dinosaurs! As a dinosaur player you will face many dangers including: running out of food tokens, attacks from other dinosaur players and natural disasters that may cause you to become extinct. Pay close attention to improve your chances for survival.\n" +
                "\nObjective\n" +
                "To be the first dinosaur player to reach the FINISH square with at least 1 food token.\n" +
                "\nStarting Out\n" +
                "At the start, each player chooses a dinosaur to represent them in the game. Each dinosaur has a name, habitat, and characteristics, which are used to compare dinosaurs to one another. Many parts of the game will refer to your characteristics.\n" +
                "\nFood Squares\n" +
                "Dinosaur players need to collect food to survive! You get food tokens at the start of the game and when you land on food squares that match your dinosaur’s diet – brown bones for carnivores (meat-eaters) and green leaves for herbivores (plant-eaters).\n" +
                "\nNATURAL DISASTER! Squares\n" +
                "Dinosaurs encountered many natural disasters. When you land on a reddish-brown NATURAL DISASTER! square, you will be shown a NATURAL DISASTER! card. Instructions on the card will tell you what happens to your dinosaur, or if you can avoid the disaster. Some of the cards say HABITAT SAFE. If you draw one of these cards while in your own habitat, nothing will happen!\n" +
                "\nDANGER ZONE Squares\n" +
                "There are six yellow DANGER ZONES on the board. When you land on these your dinosaur will take a large penalty, so be careful.\n" +
                "\nCHALLENGE! Squares\n" +
                "When you land on a blue CHALLENGE! square, you will be shown a CHALLENGE! card. You may have to make a choice, or you may take a penalty. Your dinosaur might even evolve!\n" +
                "\nATTACK! Situations\n" +
                "Watch out for attacking dinosaurs! Any time you land on a square occupied by one or more players, an ATTACK! situation occurs. In an attack situation you ignore what would normally happen on that square, and instead see an ATTACK! card. The two involved dinosaurs will be pitted against each other, and one of their characteristics will be compared.\n" +
                "In the event that there is a tie, the player in their own habitat wins. If neither or both of the players are in their own habitat, a new ATTACK! card is shown. If there is still a tie, the ATTACK! situation ends, and the next player takes their turn.\n" +
                "\nHabitats\n" +
                "The four different habitats on the board are SWAMP, DESERT, PLAINS, and FOREST. When you are in your own habitat, you may have an advantage in certain NATURAL DISASTER! and ATTACK! situations.\n" +
                "\nRunning Out of Food\n" +
                "If you run out of food tokens, you get once chance to try again. The first time you run out you will receive 3 food tokens from the bank and be moved back 10 spaces. The second time, your dinosaur goes extinct and you are out of the game.\n" +
                "\nWinning the Game\n" +
                "The first dinosaur player who reaches the END space with at least 1 food token wins! To reach this space you must land on it exactly.\n");
        rulesText.setFont(new Font(rulesText.getFont().getName(), Font.PLAIN, rulesText.getFont().getSize() * 2));
        rulesText.setBackground(backColor.brighter());
        rulesText.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        rulesText.setLineWrap(true);
        rulesText.setWrapStyleWord(true);
        rulesText.setEditable(false);

        scroll = new JScrollPane(rulesText);
        scroll.setBackground(backColor.brighter());
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.ipadx = 7;
        gbc.ipady = 7;
        gbc.insets = new Insets(0, 100, 0, 100);
        gbc.fill = GridBagConstraints.BOTH;
        rulesPanel.add(scroll, gbc);

        back = new JButton("BACK");
        back.setFont(new Font("Showcard Gothic", Font.PLAIN, (int) (50 * scale)));
        back.setBackground(backColor.darker());
        back.setFocusable(false);
        back.addActionListener(new BackListener());
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 0.1;
        gbc.insets = new Insets(0, 0, 20, 120);
        gbc.anchor = GridBagConstraints.SOUTHEAST;
        rulesPanel.add(back, gbc);

        return rulesPanel;
    }

    private JPanel makeGameplay() {
        gameplayPanel = new JPanel();
        gameplayPanel.setBackground(backColor);

        // layout tools
        gameplayPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc;

        // configure board display
        board = new BoardPanel(players, ir);
        board.setBorder(BorderFactory.createLineBorder(Color.BLACK, 10));
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 2;
        gbc.weightx = 0.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        gameplayPanel.add(board, gbc);

        // configure dino card display
        dinoCards = new DinoCardPanel(players, ir, backColor);
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        dinoCards.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        gameplayPanel.add(dinoCards, gbc);

        // configure food display
        food = new FoodPanel(players, ir, backColor);
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        gameplayPanel.add(food, gbc);

        // configure buttons
        buttonRoll = new JButton();
        buttonRoll.setText("ROLL!");
        buttonRoll.setFont(new Font("Showcard Gothic", Font.PLAIN, (int) (50 * scale)));
        buttonRoll.setBackground(backColor.darker());
        buttonRoll.setFocusable(false);
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        buttonRoll.addActionListener(new RollListener());
        gameplayPanel.add(buttonRoll, gbc);

        buttonHelp = new JButton();
        buttonHelp.setText("HELP");
        buttonHelp.setFont(new Font("Showcard Gothic", Font.PLAIN, (int) (50 * scale)));
        buttonHelp.setBackground(backColor.darker());
        buttonHelp.setFocusable(false);
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        buttonHelp.addActionListener(new HelpListener());
        gameplayPanel.add(buttonHelp, gbc);

        return gameplayPanel;
    }

    public void assertExtinct(Player p) {
        food.assertExtinct(p);
    }

    public void evolve(Player p, int i) {
        dinoCards.evolve(p, i);
    }

    private class RollListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            btnSem = 1;
            rollSem = (int) (Math.random() * 6 + 1);
        }
    }

    private class HelpListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            btnSem = 2;
        }
    }

    private class BackListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            backSem = 1;
        }
    }

    private void showRules(String returnTo) {
        cl.show(this, "rules");
        System.out.println("waiting for rules");
        backSem = 0;
        while (backSem == 0) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        cl.show(this, returnTo);
    }

    public void takeTurn(Dinosaur d) {
        dinoCards.show(d);
    }

    public void showAttack(AttackCard c) {
        board.showAttack(c);
    }

    public int showChallenge(ChallengeCard c, Player p) {
        return board.showChallenge(c, p);
    }

    public void showNaturalDisaster(NaturalDisasterCard c) {
        board.showNaturalDisaster(c);
    }

    public void showComparison(Player attacker, Player defender, String stat) {
        dinoCards.showComparison(attacker, defender, stat);
    }

    public void refreshTokens() {
        board.repaint();
    }

    public void refreshFood() {
        food.updateFood();
    }

    public int getRollorRules() {
        btnSem = 0;
        while (btnSem == 0) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (btnSem == 1) {
            JOptionPane rollConfirm = new JOptionPane();
            rollConfirm.showMessageDialog(this, "You rolled a " + rollSem + "!", "Roll Result!", JOptionPane.PLAIN_MESSAGE);
        } else if (btnSem == 2) {
            showRules("gameplay");
            gameplayPanel.requestFocus();
            return getRollorRules();
        }
        return rollSem;
    }

    private class SplashListener implements KeyListener {
        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {

        }

        @Override
        public void keyReleased(KeyEvent e) {
            splashSem = 1;
        }
    }

    private class MainListener implements KeyListener {
        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {

        }

        @Override
        public void keyReleased(KeyEvent e) {
            int i = e.getKeyCode();
            switch (i) {
                case KeyEvent.VK_UP:
                    if (playbool) {
                        // do nothing
                    }
                    else if (rulesbool) {
                        triRulesImage.setIcon(ir.getMainMenuImage(3));
                        triPlayImage.setIcon(ir.getMainMenuImage(2));
                        rulesbool = false;
                        playbool = true;
                    }
                    else if (quitbool) {
                        triQuitImage.setIcon(ir.getMainMenuImage(3));
                        triRulesImage.setIcon(ir.getMainMenuImage(2));
                        quitbool = false;
                        rulesbool = true;
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if (playbool) {
                        triPlayImage.setIcon(ir.getMainMenuImage(3));
                        triRulesImage.setIcon(ir.getMainMenuImage(2));
                        playbool = false;
                        rulesbool = true;
                    }
                    else if (rulesbool) {
                        triRulesImage.setIcon(ir.getMainMenuImage(3));
                        triQuitImage.setIcon(ir.getMainMenuImage(2));
                        rulesbool = false;
                        quitbool = true;
                    }
                    else if (quitbool) {
                        // do nothing
                    }
                    break;
                case KeyEvent.VK_ENTER:
                    if (playbool) {
                        menuSem = 1;
                    }
                    else if (rulesbool) {
                        menuSem = 2;
                    }
                    else if (quitbool) {
                        menuSem = 3;
                    }
                default:
                    break;
            }
        }
    }

    /**
     * Get player dino choices from user input and initialize players array.
     * @param dinoCards The array storing all possible dinosaurs the players
     *                  have to choose from.
     * @return An array storing all players, initialized with their chosen dinosaurs.
     * @see Player
     * @see Dinosaur
     * @see java.util.Scanner
     */
    private Player[] initializePlayers(Dinosaur[] dinoCards) {
        JOptionPane menuOptions = new JOptionPane();

        // take in input for how many players
        int playerCount = -1;
        String playerCountStr;
        while (playerCount < 1 || playerCount > 4) {
            playerCount = -1;
            playerCountStr = menuOptions.showInputDialog(
                    this,
                    "How many players are playing? (1-4)",
                    "The Dinosaur Game",
                    JOptionPane.PLAIN_MESSAGE);
            if (playerCountStr == null) {
                return null;
            }
            try {
                playerCount = Integer.valueOf(playerCountStr);
            }
            catch (NumberFormatException nfe) {}
            if (playerCount < 1 || playerCount > 4) {
                if (menuOptions.showOptionDialog(
                        this,
                        "Invalid player amount. Input the number of players. (1 - 4)",
                        "The Dinosaur Game",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.ERROR_MESSAGE,
                        null,
                        null,
                        null) == JOptionPane.CLOSED_OPTION) {
                    return null;
                }
            }
        }

        // print all the dinosaurs the players can choose from
        String dinoMsg = "The following are the Dinosaurs to choose from:\n";
        for (int i = 0; i < dinoCards.length; i++) {
            dinoMsg += (i + 1) + ". " + dinoCards[i].getName() + "\n";
        }

        Player[] players = new Player[playerCount];
        int[] chosenDinos = new int[playerCount];
        for (int i = 0; i < playerCount; i++) {
            int num = -1;
            String dinoChoice;
            while (num < 1 || num > 16) {
                num = -1;
                dinoChoice = menuOptions.showInputDialog(
                        this,
                        "Player " + (i + 1) + " input the number for the dinosaur you want.\n" + dinoMsg,
                        "The Dinosaur Game",
                        JOptionPane.PLAIN_MESSAGE);
                if (dinoChoice == null) {
                    return null;
                }
                try {
                    num = Integer.valueOf(dinoChoice);
                }
                catch (NumberFormatException nfe) {}
                if (num < 1 || num > 16) {
                    if (menuOptions.showOptionDialog(
                            this,
                            "Invalid dinosaur number. Input the number for the dinosaur you want (1 - 16).",
                            "The Dinosaur Game",
                            JOptionPane.DEFAULT_OPTION,
                            JOptionPane.ERROR_MESSAGE,
                            null,
                            null,
                            null) == JOptionPane.CLOSED_OPTION) {
                        System.exit(0);
                    }
                }
                int j = 0;
                while (j < i) {
                    if (chosenDinos[j] == num - 1) {
                        if (menuOptions.showOptionDialog(
                                this,
                                "Chosen dinosaur already chosen by a previous player. Choose another dinosaur.",
                                "The Dinosaur Game",
                                JOptionPane.DEFAULT_OPTION,
                                JOptionPane.ERROR_MESSAGE,
                                null,
                                null,
                                null) == JOptionPane.CLOSED_OPTION) {
                            return null;
                        }
                        num = -1;
                    }
                    else j++;
                }
            }
            chosenDinos[i] = num - 1;
            players[i] = new Player(dinoCards[num - 1], 5, spaces);
        }

        return players;
    }
}
