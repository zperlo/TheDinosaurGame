import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * A custom JPanel to display the situation cards (challenge/natural disaster/attack).
 *
 * @author Jacob Rich
 * @version 1.18
 * @since 2018-12-6
 */
public class CardPanel extends JPanel {
    // card components
    private JLabel labelTop;
    private JLabel labelMid;
    private JLabel labelBot;
    private JLabel labelHab;

    // utility variables
    private int labelClickSem;
    private int panelClickSem;
    private Font defaultFont;
    private Font hoverFont;

    /**
     * Calls the setup method.
     */
    public CardPanel() {
        setup();
    }

    /**
     * Creates and places the components of the card.
     *
     * @see java.awt.GridBagLayout
     * @see java.awt.GridBagConstraints
     * @see javax.swing.JLabel
     * @see java.awt.Font
     * @see LabelListener
     * @see PanelListener
     */
    private void setup() {
        // layout tools
        setLayout(new GridBagLayout());
        GridBagConstraints gbc;

        // labelTop
        labelTop = new JLabel();
        labelTop.setHorizontalAlignment(JLabel.CENTER);
        labelTop.setFont(new Font(labelTop.getFont().getName(), Font.PLAIN, (int) (labelTop.getFont().getSize() * 1.5)));
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(7, 7, 7, 7);
        labelTop.addMouseListener(new LabelListener());
        add(labelTop, gbc);

        // labelMid
        labelMid = new JLabel();
        labelMid.setHorizontalAlignment(JLabel.CENTER);
        labelMid.setFont(new Font(labelMid.getFont().getName(), Font.PLAIN, (int) (labelMid.getFont().getSize() * 1.5)));
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(7, 7, 7, 7);
        add(labelMid, gbc);

        // labelBot
        labelBot = new JLabel();
        labelBot.setHorizontalAlignment(JLabel.CENTER);
        labelBot.setFont(new Font(labelBot.getFont().getName(), Font.PLAIN, (int) (labelBot.getFont().getSize() * 1.5)));
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(7, 7, 7, 7);
        labelBot.addMouseListener(new LabelListener());
        add(labelBot, gbc);

        // labelHab
        labelHab = new JLabel();
        labelHab.setHorizontalAlignment(JLabel.CENTER);
        labelHab.setFont(new Font(labelHab.getFont().getName(), Font.PLAIN, (int) (labelHab.getFont().getSize() * 1.5)));
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(7, 7, 7, 7);
        add(labelHab, gbc);

        addMouseListener(new PanelListener());

        defaultFont = labelTop.getFont();
        hoverFont = new Font(defaultFont.getName(), Font.BOLD, defaultFont.getSize() + 2);
    }

    /**
     * Sets the card up tp be displayed as an attack
     *
     * @param c the card to be displayed
     * @see javax.swing.JLabel
     * @see java.lang.Thread
     * @see java.lang.InterruptedException
     */
    public void showAttack(AttackCard c) {
        setBackground(Color.green);
        labelTop.setText(c.getPara1());
        labelMid.setText(null);
        labelBot.setText(c.getPara2());
        labelHab.setText(null);

        panelClickSem = 0;
        while (panelClickSem == 0) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Sets the card up to display and handle a challenge card
     *
     * @param c the card to be displayed
     * @param p the player whom the card will affect
     * @return an int representing the player's choice
     * @see javax.swing.JLabel
     * @see ChallengeCard
     * @see LabelListener
     * @see Player
     * @see java.lang.Thread
     * @see java.lang.InterruptedException
     */
    public int showChallenge(ChallengeCard c, Player p) {
        setBackground(Color.CYAN);
        labelTop.setText(c.getChoice1());
        labelMid.setText(c.getMiddlePara());
        labelBot.setText(c.getChoice2());
        labelHab.setText(null);

        int r = -1;
        if (c.getType() == 0) {
            LabelListener top = (LabelListener) labelTop.getMouseListeners()[0];
            top.activate();
            LabelListener bot = (LabelListener) labelBot.getMouseListeners()[0];
            bot.activate();

            labelClickSem = 0;
            while (labelClickSem == 0) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            r = labelClickSem;

            top.assertMouseExited(labelTop);
            bot.assertMouseExited(labelBot);
            top.deactivate();
            bot.deactivate();
        }
        else if (c.getType() == 1) {
            boolean conditionMet = false;
            switch (c.getId()) {
                case 0:
                    conditionMet = p.isInHabitat();
                    break;
                case 4:
                    conditionMet = !p.isInHabitat();
                    break;
                case 5:
                    conditionMet = p.getFoodTokens() >= 6;
                    break;
                default:
                    break;
            }

            LabelListener top = new LabelListener();
            LabelListener bot = (LabelListener) labelBot.getMouseListeners()[0];
            bot.activate();
            if (conditionMet) {
                top = (LabelListener) labelTop.getMouseListeners()[0];
                top.activate();
            }

            labelClickSem = 0;
            while (labelClickSem == 0) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            r = labelClickSem;

            if (conditionMet) {
                top.assertMouseExited(labelTop);
                top.deactivate();
            }
            bot.assertMouseExited(labelBot);
            bot.deactivate();
        }
        else if (c.getType() == 2 || c.getType() == 3) {
            panelClickSem = 0;
            while (panelClickSem == 0) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            r = panelClickSem;
        }

        return r;
    }

    /**
     * Sets the card up to display as a natural disaster card
     * @param c the card to be displayed
     * @see javax.swing.JLabel
     * @see NaturalDisasterCard
     * @see java.lang.Thread
     * @see java.lang.InterruptedException
     */
    public void showNaturalDisaster(NaturalDisasterCard c) {
        setBackground(Color.PINK);
        labelTop.setText(c.getPara1());
        labelMid.setText(c.getPara2());
        labelBot.setText(c.getPara3());
        if ((c.getHabitatSafe())) {
            labelHab.setText("HABITAT SAFE");
        } else {
            labelHab.setText(null);
        }

        panelClickSem = 0;
        while (panelClickSem == 0) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * An implementation of MouseListener that highlights labels for challenge cards as they are moused over.
     *
     * @see java.awt.event.MouseListener
     * @see java.awt.event.MouseEvent
     * @see java.awt.Component
     */
    private class LabelListener implements MouseListener {
        private boolean active = false;

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {
            if (active) {
                e.getComponent().setFont(hoverFont);
            }
        }

        @Override
        public void mouseExited(MouseEvent e) {
            if (active) {
                e.getComponent().setFont(defaultFont);
            }
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            panelClickSem = 1;
            if (e.getComponent().equals(labelTop) && active) {
                labelClickSem = 1;
            }
            else if (e.getComponent().equals(labelBot) && active) {
                labelClickSem = 2;
            }
        }

        public void activate() {
            active = true;
        }

        public void deactivate() {
            active = false;
        }

        public void assertMouseExited(Component c) {
            if (active) {
                c.setFont(defaultFont);
            }
        }
    }

    /**
     * An implementation of MouseListener that allows card to be dismissed by clicking on them.
     *
     * @see java.awt.event.MouseListener
     * @see java.awt.event.MouseEvent
     */
    private class PanelListener implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            panelClickSem = 1;
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
    }
}
