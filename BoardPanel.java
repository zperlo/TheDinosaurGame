import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class BoardPanel extends JPanel{
    // board items
    private JLabel boardLabel;
    private CardPanel card;
    private PlayerToken[] tokens;

    // utility variables
    private Dinosaur[] dinos;
    private Player[] players;
    private Color[] colors = {Color.red, Color.blue, Color.green, Color.yellow};

    // constructor
    public BoardPanel(Player[] players) {
        this.players = players;
        dinos = new Dinosaur[players.length];
        for (int i = 0; i < players.length; i++) {
            dinos[i] = players[i].getDino();
        }
        tokens = new PlayerToken[players.length];
        for (int i = 0; i < players.length; i++) {
            tokens[i] = new PlayerToken(players[i], colors[i]);
        }

        try {
            setup();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // initialize components
    private void setup() throws IOException {
        // layout tools
        setLayout(new GridBagLayout());
        GridBagConstraints gbc;

        // create scaled image of board
        final Image board = ImageIO.read(getClass().getResource("/resources/Game Board Final.jpeg")).getScaledInstance(1000, 1000, Image.SCALE_SMOOTH);

        // board label
        boardLabel = new JLabel(new ImageIcon(board)) {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(board, 0, 0, null);
                for (PlayerToken t : tokens) {
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
                    }
                    else {
                        g.fillOval(t.getX(), t.getY(), t.DIAM, t.DIAM);
                    }
                }
            }
        };
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        gbc.gridheight = 3;
        add(boardLabel, gbc);
    }

    public void showAttack(AttackCard c) {
        card = new CardPanel();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(card, gbc);
        card.showAttack(c);
    }

    public int showChallenge(ChallengeCard c) {
        card = new CardPanel();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(card, gbc);
        return card.showChallenge(c);
    }

    public void showNaturalDisaster(NaturalDisasterCard c) {
        card = new CardPanel();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(card, gbc);
        card.showNaturalDisaster(c);
    }
}
