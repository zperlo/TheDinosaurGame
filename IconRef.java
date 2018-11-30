import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;

public class IconRef {
    // images
    private Image board;
    private HashMap<String, ImageIcon> compares;
    private HashMap<String, ImageIcon> icons;
    private HashMap<Integer, ImageIcon> mainMenuImages;

    // utility variables
    final int BOARD_SIZE;
    final double SCALE_FACTOR;
    final String[] DINO_NAMES = {
            "Allosaurus",
            "Ankylosaurus",
            "Apatosaurus",
            "Brachiosaurus",
            "Compsognathus",
            "Deinonychus",
            "Dilophosaurus",
            "Iguanodon",
            "Parasaurolophus",
            "Protoceratops",
            "Spinosaurus",
            "Stegosaurus",
            "Styracosaurus",
            "Triceratops",
            "Tyrannosaurus",
            "Velociraptor"};

    // constructor
    public IconRef() {
        BOARD_SIZE = (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 0.90);
        SCALE_FACTOR = BOARD_SIZE / 1000.0;
        compares = new HashMap<>();
        icons = new HashMap<>();
        mainMenuImages = new HashMap<>();

        board = makeBoard();

        mainMenuImages.put(0, makeMainMenuImages(0));
        mainMenuImages.put(1, makeMainMenuImages(1));
        mainMenuImages.put(2, makeMainMenuImages(2));

        String s;
        for (int i = 0; i < DINO_NAMES.length; i++) {
            s = DINO_NAMES[i];
            compares.put(s, makeCompareToHuman(s));
            icons.put(s, makeIcon(s));
        }
    }

    private Image makeBoard() {
        try {
            board = ImageIO.read(getClass().getResource("/resources/Other/Board.jpeg"));
            board = board.getScaledInstance(BOARD_SIZE, BOARD_SIZE, Image.SCALE_SMOOTH);
            return board;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private ImageIcon makeMainMenuImages(int i) {
        String imageName;
        switch (i) {
            case 0:
                imageName = "trex_head.png";
                break;
            case 1:
                imageName = "brach_head.png";
                break;
            case 2:
                imageName = "tri_cursor.png";
                break;
            default:
                imageName = "";
                break;
        }
        try {
            Image main = ImageIO.read(getClass().getResource("/resources/Other/" + imageName));
            main = main.getScaledInstance((int) (main.getWidth(null) * SCALE_FACTOR * ((i / 2) * -1 + 1.25)), (int) (main.getHeight(null) * SCALE_FACTOR * ((i / 2) * -1 + 1.25)), Image.SCALE_SMOOTH);
            return new ImageIcon(main);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private ImageIcon makeCompareToHuman(String str) {
        try {
            Image compare = ImageIO.read(getClass().getResource("/resources/Dinosaurs/" + str + "/compare.jpg"));
            double compareScale = 320.0 / compare.getWidth(null);
            compare = compare.getScaledInstance((int) (320 * SCALE_FACTOR), (int) (compare.getHeight(null) * compareScale * SCALE_FACTOR), Image.SCALE_SMOOTH);
            return new ImageIcon(compare);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private ImageIcon makeIcon(String str) {
        Image icon;
        try {
            icon = ImageIO.read(getClass().getResource("/resources/Dinosaurs/" + str + "/icon.jpg"));
            icon = icon.getScaledInstance((int) (100 * SCALE_FACTOR), (int) (100 * SCALE_FACTOR), Image.SCALE_SMOOTH);
            return new ImageIcon(icon);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Image getBoard() {
        return board;
    }

    public ImageIcon getCompareToHuman(Dinosaur d) {
        return compares.get(d.getName());
    }

    public ImageIcon getIcon(Dinosaur d) {
        return icons.get(d.getName());
    }

    public ImageIcon getMainMenuImage(int i) {
        return mainMenuImages.get(i);
    }
}
