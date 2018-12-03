import javax.swing.*;
import java.awt.*;

public class BoardPanel extends JLayeredPane{
    // board items
    private JLabel boardLabel;
    private CardPanel card;
    private PlayerToken[] tokens;

    // utility variables
    private final int BOARD_HEIGHT = (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 0.90);
    private final double SCALE_FACTOR = BOARD_HEIGHT / 1000.0;
    private final Dimension CARD_DIM = new Dimension((int) (500 * SCALE_FACTOR), (int) (300 * SCALE_FACTOR));
    private Dinosaur[] dinos;
    private Player[] players;
    private Color[] colors = {new Color(230, 59, 46), new Color(67, 124, 144), new Color(143, 206, 41), new Color (255, 119, 51)};
    private IconRef ir;

    // constructor
    public BoardPanel(Player[] players, IconRef ir) {
        this.players = players;
        this.ir = ir;
        dinos = new Dinosaur[players.length];
        for (int i = 0; i < players.length; i++) {
            dinos[i] = players[i].getDino();
        }
        tokens = new PlayerToken[players.length];
        for (int i = 0; i < players.length; i++) {
            tokens[i] = new PlayerToken(players[i], colors[i]);
        }
        setup();
    }

    // initialize components
    private void setup() {
        // layout tools
        setLayout(new GridBagLayout());
        GridBagConstraints gbc;

        // get scaled image of board
        final Image board = ir.getBoard();

        // board label
        boardLabel = new JLabel(new ImageIcon(board)) {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(board, 0, 0, null);
                for (PlayerToken t : tokens) {
                    if (!t.getPlayer().isExtinct()) {
                        boolean drawAwayFromCenter = false;
                        for (Player p : players) {
                            if (t.getPlayerLocation() == p.getLocation() && !t.getPlayer().equals(p)) {
                                drawAwayFromCenter = true;
                            }
                        }
                        g.setColor(t.getColor());
                        if (drawAwayFromCenter) {
                            // randomly move the drawing so the tokens don't completely overlap
                            int x = (int) (Math.random() * 10 + 11);
                            if (Math.random() - 0.5 < 0) {
                                x *= -1;
                            }
                            int y = (int) (Math.random() * 10 + 11);
                            if (Math.random() - 0.5 < 0) {
                                y *= -1;
                            }
                            g.fillOval(t.getX() + x, t.getY() + y, t.DIAM, t.DIAM);
                        } else {
                            g.fillOval(t.getX(), t.getY(), t.DIAM, t.DIAM);
                        }
                    }
                }
            }
        };
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        gbc.gridheight = 3;
        add(boardLabel, gbc, JLayeredPane.DEFAULT_LAYER);

        // card
        card = new CardPanel();
        card.setMinimumSize(CARD_DIM);
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.CENTER;
        add(card, gbc, JLayeredPane.DEFAULT_LAYER - 1);
    }

    public void showAttack(AttackCard c) {
        moveToFront(card);
        card.showAttack(c);
        moveToBack(card);
    }

    public int showChallenge(ChallengeCard c, Player p) {
        moveToFront(card);
        int r = card.showChallenge(c, p);
        moveToBack(card);
        return r;
    }

    public void showNaturalDisaster(NaturalDisasterCard c) {
        moveToFront(card);
        card.showNaturalDisaster(c);
        moveToBack(card);
    }
}
