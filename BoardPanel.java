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
    //private Player[] players;
    private Color[] colors = {Color.red, Color.blue, Color.green, Color.yellow};

    // constructor
    public BoardPanel(Player[] players) {
        //this.players = players;
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
        final Image board;
        board = ImageIO.read(getClass().getResource("/resources/Game Board Final.jpeg")).getScaledInstance(1000, 1000, Image.SCALE_SMOOTH);

        // board label
        boardLabel = new JLabel(new ImageIcon(board)) {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(board, 0, 0, null);
                for (PlayerToken t : tokens) {
                    g.setColor(t.getColor());
                    g.fillOval(t.getX(), t.getY(), 30, 30);
                    System.out.println("drawn");
                }
            }
        };
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        gbc.gridheight = 3;
        add(boardLabel, gbc);

        // card (attack/challenge/natural disaster)
        card = new CardPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        card.setVisible(false);
        add(card, gbc);

        // paint tokens
        boardLabel.repaint();
    }
}
