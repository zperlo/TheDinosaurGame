import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.*;

public class GamePanel extends JPanel {
    // top level gameplay components
    private BoardPanel board;
    private DinoCardPanel dinoCards;
    private FoodPanel food;
    private JButton buttonRoll;
    private JButton buttonHelp;

    // utility variables
    private Player[] players;
    private int rollSem;
    private int splashSem;

    // constructor
    public GamePanel() {}

    public void executeMenu() {
        splash();
    }

    private void splash() {
        // layout tools
        setLayout(new GridBagLayout());
        GridBagConstraints gbc;

        setBackground(new Color(188, 116, 88));

        setFocusable(true);
        addKeyListener(new SplashListener());
        splashSem = 0;
        while (splashSem == 0) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.exit(0);
    }

    // initialize and set up top level components
    private void gameSetup() {
        // layout tools
        setLayout(new GridBagLayout());
        GridBagConstraints gbc;

        // configure board display
        board = new BoardPanel(players);
        board.setBorder(BorderFactory.createLineBorder(Color.BLACK, 10));
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        add(board, gbc);

        // configure dino card display
        dinoCards = new DinoCardPanel(players);
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        dinoCards.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        add(dinoCards, gbc);

        // configure food display
        food = new FoodPanel(players);
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        add(food, gbc);

        // configure buttons
        buttonRoll = new JButton();
        buttonRoll.setText("Roll!");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        buttonRoll.addActionListener(new RollListener());
        add(buttonRoll, gbc);

        buttonHelp = new JButton();
        buttonHelp.setText(/*"Help"*/"Quit");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        buttonHelp.addActionListener(new HelpListener());
        add(buttonHelp, gbc);
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

    /*private class SplashListener implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            splashSem = 1;
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }*/
}
