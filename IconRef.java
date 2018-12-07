import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.HashMap;

/**
 * A loader for all images required by the game
 *
 * @author Jacob Rich
 * @version 1.6
 * @since 2018-12-6
 */
public class IconRef {
    // images
    private Image board;
    private HashMap<String, ImageIcon> compares;
    private HashMap<String, ImageIcon> icons;
    private HashMap<String, ImageIcon> iconsWithX;
    private HashMap<Integer, ImageIcon> mainMenuImages;

    // utility variables
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

    /**
     * Set scale factor and load all images
     *
     * @param scale the scale at which to render images
     * @see java.util.HashMap
     */
    public IconRef(double scale) {
        SCALE_FACTOR = scale;
        compares = new HashMap<>();
        icons = new HashMap<>();
        iconsWithX = new HashMap<>();
        mainMenuImages = new HashMap<>();

        board = makeBoard();

        mainMenuImages.put(0, makeMainMenuImages(0));
        mainMenuImages.put(1, makeMainMenuImages(1));
        mainMenuImages.put(2, makeMainMenuImages(2));
        mainMenuImages.put(3, makeMainMenuImages(3));

        String s;
        for (int i = 0; i < DINO_NAMES.length; i++) {
            s = DINO_NAMES[i];
            compares.put(s, makeCompareToHuman(s));
            icons.put(s, makeIcon(s));
            iconsWithX.put(s, makeIconWithX(s));
        }
    }

    /**
     * Make the board image
     *
     * @return a scaled version of the image
     * @see javax.imageio.ImageIO
     * @see java.awt.Image
     * @see java.io.IOException
     */
    private Image makeBoard() {
        try {
            board = ImageIO.read(getClass().getResource("/resources/Other/Board.jpeg"));
            board = board.getScaledInstance((int) (1000 * SCALE_FACTOR), (int) (1000 * SCALE_FACTOR), Image.SCALE_SMOOTH);
            return board;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Make the main menu images
     *
     * @param i the index of the image to generate
     * @return a scaled version of the specified image
     * @see javax.imageio.ImageIO
     * @see java.awt.Image
     * @see java.io.IOException
     */
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
            case 3:
                imageName = "tri_cover.png";
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

    /**
     * Make the size comparison images
     *
     * @param str the name of the dinosaur whose image is to be created
     * @return a scaled version of the specified image
     * @see javax.imageio.ImageIO
     * @see java.awt.Image
     * @see java.io.IOException
     */
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

    /**
     * Make the dino icons
     *
     * @param str the name of the dino whose icon is to be made
     * @return a scaled version of the specified image
     * @see javax.imageio.ImageIO
     * @see java.awt.Image
     * @see java.io.IOException
     */
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

    /**
     * Make the extinct icons
     *
     * @param str the name of the dino whose icon is to be made
     * @return a scaled version of the image
     * @see javax.imageio.ImageIO
     * @see java.awt.Image
     * @see java.io.IOException
     */
    private ImageIcon makeIconWithX(String str) {
        Image icon;
        try {
            icon = ImageIO.read(getClass().getResource("/resources/Dinosaurs/" + str + "/icon_x.jpg"));
            icon = icon.getScaledInstance((int) (100 * SCALE_FACTOR), (int) (100 * SCALE_FACTOR), Image.SCALE_SMOOTH);
            return new ImageIcon(icon);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Get the board image
     *
     * @return the board image
     */
    public Image getBoard() {
        return board;
    }

    /**
     * Get the specified size comparison image
     *
     * @param d the dinosaur whose image is needed
     * @return the specified image
     * @see java.util.HashMap
     */
    public ImageIcon getCompareToHuman(Dinosaur d) {
        return compares.get(d.getName());
    }

    /**
     * Get the specified dinosaur icon
     *
     * @param d the dinosaur whose image is needed
     * @return the specified image
     * @see java.util.HashMap
     */
    public ImageIcon getIcon(Dinosaur d) {
        return icons.get(d.getName());
    }

    /**
     * Get the specified extinct icon
     *
     * @param d the dinosaur whose image is needed
     * @return the specified image
     * @see java.util.HashMap
     */
    public ImageIcon getIconWithX(Dinosaur d) {
        return iconsWithX.get(d.getName());
    }

    /**
     * Get the specified main menu image
     *
     * @param i the main menu image needed
     * @return the specified image
     * @see java.util.HashMap
     */
    public ImageIcon getMainMenuImage(int i) {
        return mainMenuImages.get(i);
    }
}
