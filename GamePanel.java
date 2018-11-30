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

    private JPanel mainMenu;
    private JLabel titleLabel;
    private JLabel brachImage;
    private JLabel tRexImage;
    private JLabel playRulesQuitLabel;
    private JLabel triPlayImage;
    private JLabel triRulesImage;
    private JLabel triQuitImage;

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
    private int rollSem;
    private int splashSem;

    // constructor
    public GamePanel(Player[] players) {
        this.players = players;
        cl = new CardLayout();
        setLayout(cl);
        add(splash(), "splash");
    }

    public void executeMenu() {
        ir = new IconRef();
        //add(mainMenu(), "main");
        //add(dinoSelect(), "dinoSelect");
        add(gameplay(), "gameplay");
        continueLabel.setForeground(Color.BLACK);
        splashSem = 0;
        while (splashSem == 0) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        cl.next(this);
    }

    private JPanel splash() {
        splash = new JPanel();

        // layout tools
        splash.setLayout(new GridBagLayout());
        GridBagConstraints gbc;

        splash.setBackground(backColor);

        try {
            splashImage = new JLabel(new ImageIcon(ImageIO.read(getClass().getResource("/resources/Other/splash_image.png")).getScaledInstance(1350, 900, Image.SCALE_SMOOTH)));
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
        gbc.insets = new Insets(250, 0, 0, 150);
        gbc.anchor = GridBagConstraints.NORTHEAST;
        splash.add(continueLabel, gbc);

        splash.setFocusable(true);
        splash.addKeyListener(new SplashListener());

        return splash;
    }

    private JPanel mainMenu() {
        mainMenu = new JPanel();
        mainMenu.setBackground(backColor);

        mainMenu.setLayout(new GridBagLayout());
        GridBagConstraints gbc;

        titleLabel = new JLabel();
        titleLabel.setFont(new Font("Showcard Gothic", Font.PLAIN, 100));
        titleLabel.setText("<html><center>THE DINOSAUR GAME<br><font size=50>SURVIVAL OR EXTINCTION</html>");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        gbc.gridheight = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.insets = new Insets(100, 35, 0, 0);
        mainMenu.add(titleLabel, gbc);

        tRexImage = new JLabel(ir.getMainMenuImage(0));
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 3;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.SOUTHWEST;
        mainMenu.add(tRexImage, gbc);

        brachImage = new JLabel(ir.getMainMenuImage(1));
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 4;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.SOUTHEAST;
        mainMenu.add(brachImage, gbc);

        triPlayImage = new JLabel(ir.getMainMenuImage(2));
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.insets = new Insets(0, 0, 25, 0);
        gbc.anchor = GridBagConstraints.SOUTHEAST;
        mainMenu.add(triPlayImage, gbc);

        triRulesImage = new JLabel(ir.getMainMenuImage(2));
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        mainMenu.add(triRulesImage, gbc);

        triQuitImage = new JLabel(ir.getMainMenuImage(2));
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.weightx = 1.0;
        gbc.weighty = 0.89;
        gbc.insets = new Insets(30, 0, 0, 0);
        gbc.anchor = GridBagConstraints.NORTHEAST;
        mainMenu.add(triQuitImage, gbc);

        playRulesQuitLabel = new JLabel("<html><left>PLAY<br>RULES<br>QUIT");
        playRulesQuitLabel.setFont(new Font("Showcard Gothic", Font.PLAIN, 80));
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 3;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        mainMenu.add(playRulesQuitLabel, gbc);

        //mainMenu.addlisteners

        return mainMenu;
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

    public int showChallenge(ChallengeCard c) {
        return board.showChallenge(c);
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
        rollConfirm.showMessageDialog(this, "you rolled a " + rollSem + "!", "dinogame", JOptionPane.PLAIN_MESSAGE);

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
}
