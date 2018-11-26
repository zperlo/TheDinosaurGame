import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

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

    // constructor
    public CardPanel() {
        setup();
    }

    // initialize components
    private void setup() {
        // layout tools
        setLayout(new GridBagLayout());
        GridBagConstraints gbc;

        // labelTop
        labelTop = new JLabel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        labelTop.addMouseListener(new LabelListener());
        add(labelTop, gbc);

        // labelMid
        labelMid = new JLabel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(labelMid, gbc);

        // labelBot
        labelBot = new JLabel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        labelBot.addMouseListener(new LabelListener());
        add(labelBot, gbc);

        // labelHab
        labelHab = new JLabel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(labelHab, gbc);

        addMouseListener(new PanelListener());

        defaultFont = labelTop.getFont();
        hoverFont = new Font(defaultFont.getName(), Font.BOLD, defaultFont.getSize() + 2);
    }

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

    public int showChallenge(ChallengeCard c) {
        setBackground(Color.CYAN);
        labelTop.setText(c.getChoice1());
        labelMid.setText(c.getMiddlePara());
        labelBot.setText(c.getChoice2());
        labelHab.setText(null);

        int r = -1;
        if (c.getType() == 0 || c.getType() == 1) {
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
        } else if (c.getType() == 2 || c.getType() == 3) {
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
            if (e.getComponent().equals(labelTop)) {
                labelClickSem = 1;
            }
            else if (e.getComponent().equals(labelBot)) {
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
