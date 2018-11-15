import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class IconRef {
    // utility variables
    final int SIZE;
    final double SCALE_FACTOR;

    // constructor
    public IconRef() {
        SIZE = (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 0.90);
        SCALE_FACTOR = SIZE / 1000.0;
    }

    public Image getBoard() {
        Image board;
        try {
            board = ImageIO.read(getClass().getResource("/resources/Other/Board.jpeg"));
            board = board.getScaledInstance(SIZE, SIZE, Image.SCALE_SMOOTH);
            return board;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ImageIcon getCompareToHuman(Dinosaur d) {
        Image c;
        try {
            c = ImageIO.read(getClass().getResource("/resources/Dinosaurs/" + d.getName() + "/compare.jpg"));
            double compareScale = 320.0 / c.getWidth(null);
            c = c.getScaledInstance((int) (320 * SCALE_FACTOR), (int) (c.getHeight(null) * compareScale * SCALE_FACTOR), Image.SCALE_SMOOTH);
            return new ImageIcon(c);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ImageIcon getIcon(Dinosaur d) {
        Image i;
        try {
            i = ImageIO.read(getClass().getResource("/resources/Dinosaurs/" + d.getName() + "/icon.jpg"));
            i = i.getScaledInstance((int) (100 * SCALE_FACTOR), (int) (100 * SCALE_FACTOR), Image.SCALE_SMOOTH);
            return new ImageIcon(i);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
