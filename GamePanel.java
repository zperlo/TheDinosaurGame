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

    private JPanel dinoSelect;

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

    // constructor
    public GamePanel(Dinosaur[] dinosaurs, double scale, Space[] spaces) {
        this.dinosaurs = dinosaurs;
        this.scale = scale;
        this.spaces = spaces;
        cl = new CardLayout();
        setLayout(cl);
        add(splash(), "splash");
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

        add(mainMenu(), "main");
        //add(dinoSelect(), "dinoSelect");

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
                break;
            case 2:
                players = initializePlayers(dinosaurs);
                break;
            case 3:
                System.exit(0);
                break;
            default:
                break;
        }
        add(gameplay(), "gameplay");

        cl.show(this, "gameplay");
        gameplayPanel.requestFocus();

        return players;
    }

    public void setPlayers(Player[] players) {
        this.players = players;
        add(gameplay(), "gameplay");
        cl.show(this, "gameplay");
    }

    private JPanel splash() {
        splash = new JPanel();

        // layout tools
        splash.setLayout(new GridBagLayout());
        GridBagConstraints gbc;

        splash.setBackground(backColor);

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
        continueLabel.setForeground(new Color(115, 82, 69));
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

    private JPanel mainMenu() {
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

    private JPanel dinoSelect() {
        dinoSelect = new JPanel();
        return dinoSelect;
    }

    private JPanel gameplay() {
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
        gbc.weightx = 1.0;
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
        buttonRoll.setText("Roll!");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        buttonRoll.addActionListener(new RollListener());
        gameplayPanel.add(buttonRoll, gbc);

        buttonHelp = new JButton();
        buttonHelp.setText(/*"Help"*/"Quit");
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
            rollSem = (int) (Math.random() * 6 + 1);
        }
    }

    private class HelpListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            System.exit(0);
        }
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

    public int getRoll() {
        rollSem = 0;
        while (rollSem == 0) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        JOptionPane rollConfirm = new JOptionPane();
        rollConfirm.showMessageDialog(this, "You rolled a " + rollSem + "!", "Roll Result!", JOptionPane.PLAIN_MESSAGE);

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
        String playerCountStr = "";
        while (playerCount < 1 || playerCount > 4) {
            playerCount = -1;
            playerCountStr = menuOptions.showInputDialog(
                    this,
                    "How many players are playing? (1-4)",
                    "The Dinosaur Game",
                    JOptionPane.PLAIN_MESSAGE);
            try {
                playerCount = Integer.valueOf(playerCountStr);
            }
            catch (NumberFormatException nfe) {}
            if (playerCount == -1) {
                System.exit(0);
            }
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
                    System.exit(0);
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
            String dinoChoice = "";
            while (num < 1 || num > 16) {
                num = -1;
                dinoChoice = menuOptions.showInputDialog(
                        this,
                        "Player " + (i + 1) + " input the number for the dinosaur you want.\n" + dinoMsg,
                        "The Dinosaur Game",
                        JOptionPane.PLAIN_MESSAGE);
                try {
                    num = Integer.valueOf(dinoChoice);
                }
                catch (NumberFormatException nfe) {}
                if (num == -1) {
                    System.exit(0);
                }
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
                            System.exit(0);
                        }
                        num = -1;
                    }
                    else j++;
                }
            }
            chosenDinos[i] = num - 1;
            players[i] = new Player(dinoCards[num - 1], 3, spaces);
        }

        return players;
    }
}
