import java.awt.*;

public class PlayerToken{
    // utility variables
    private Color color;
    private Player player;
    private Point location;
    private final Point[] locations = new Point[]{new Point(163, 861),
            new Point(259, 864),
            new Point(369, 867),
            new Point(478, 865),
            new Point(592, 866),
            new Point(701, 861),
            new Point(809, 840),
            new Point(877, 755),
            new Point(897, 654),
            new Point(896, 548),
            new Point(885, 441),
            new Point(865, 338),
            new Point(835, 248),
            new Point(743, 214),
            new Point(720, 307),
            new Point(746, 403),
            new Point(769, 504),
            new Point(783, 604),
            new Point(769, 710),
            new Point(687, 755),
            new Point(617, 695),
            new Point(610, 588),
            new Point(624, 485),
            new Point(632, 379),
            new Point(627, 273),
            new Point(556, 237),
            new Point(515, 334),
            new Point(504, 453),
            new Point(509, 555),
            new Point(514, 649),
            new Point(463, 741),
            new Point(346, 767),
            new Point(232, 762),
            new Point(132, 729),
            new Point(108, 629),
            new Point(138, 529),
            new Point(236, 562),
            new Point(309, 641),
            new Point(403, 630),
            new Point(369, 516),
            new Point(355, 416),
            new Point(426, 331),
            new Point(388, 245),
            new Point(310, 310),
            new Point(234, 381),
            new Point(133, 361),
            new Point(109, 260),
            new Point(158, 176),
            new Point(248, 134),
            new Point(350, 120),
            new Point(461, 116),
            new Point(563, 114),
            new Point(664, 117),
            new Point(766, 118),
            new Point(898, 95)};

    // constructor
    public PlayerToken(Player player, Color color) {
        this.color = color;
        this.player = player;
    }
    public Color getColor() {
        return color;
    }

    public int getX() {
        return 200;
    }

    public int getY() {
        return 200;
    }
}
