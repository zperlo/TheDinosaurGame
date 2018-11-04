import java.awt.*;

public class PlayerToken{
    // utility variables
    private Color color;
    private Player player;
    private Point location;
    private final Point[] locations = new Point[]{new Point(88, 897),
            new Point(163, 861),
            new Point(210, 863),
            new Point(259, 864),
            new Point(314, 865),
            new Point(369, 867),
            new Point(426, 867),
            new Point(478, 865),
            new Point(534, 866),
            new Point(592, 866),
            new Point(647, 865),
            new Point(701, 861),
            new Point(764, 853),
            new Point(809, 840),
            new Point(850, 803),
            new Point(877, 755),
            new Point(890, 703),
            new Point(897, 654),
            new Point(899, 603),
            new Point(896, 548),
            new Point(893, 493),
            new Point(885, 441),
            new Point(876, 390),
            new Point(865, 338),
            new Point(851, 294),
            new Point(835, 248),
            new Point(796, 212),
            new Point(743, 214),
            new Point(715, 258),
            new Point(720, 307),
            new Point(736, 355),
            new Point(746, 403),
            new Point(755, 453),
            new Point(769, 504),
            new Point(778, 553),
            new Point(783, 604),
            new Point(783, 661),
            new Point(769, 710),
            new Point(731, 744),
            new Point(687, 755),
            new Point(645, 739),
            new Point(617, 695),
            new Point(604, 641),
            new Point(610, 588),
            new Point(620, 535),
            new Point(624, 485),
            new Point(629, 428),
            new Point(632, 379),
            new Point(633, 326),
            new Point(627, 273),
            new Point(601, 230),
            new Point(556, 237),
            new Point(527, 278),
            new Point(515, 334),
            new Point(507, 395),
            new Point(504, 453),
            new Point(503, 507),
            new Point(509, 555),
            new Point(514, 605),
            new Point(514, 649),
            new Point(501, 699),
            new Point(463, 741),
            new Point(405, 761),
            new Point(346, 767),
            new Point(289, 768),
            new Point(232, 762),
            new Point(175, 753),
            new Point(132, 729),
            new Point(112, 683),
            new Point(108, 629),
            new Point(113, 575),
            new Point(138, 529),
            new Point(190, 526),
            new Point(236, 562),
            new Point(269, 600),
            new Point(309, 641),
            new Point(358, 657),
            new Point(403, 630),
            new Point(400, 572),
            new Point(369, 516),
            new Point(345, 468),
            new Point(355, 416),
            new Point(395, 374),
            new Point(426, 331),
            new Point(434, 277),
            new Point(388, 245),
            new Point(343, 267),
            new Point(310, 310),
            new Point(279, 343),
            new Point(234, 381),
            new Point(180, 394),
            new Point(133, 361),
            new Point(111, 314),
            new Point(109, 260),
            new Point(126, 213),
            new Point(158, 176),
            new Point(203, 147),
            new Point(248, 134),
            new Point(297, 122),
            new Point(350, 120),
            new Point(405, 117),
            new Point(461, 116),
            new Point(512, 115),
            new Point(563, 114),
            new Point(612, 116),
            new Point(664, 117),
            new Point(716, 115),
            new Point(766, 118),
            new Point(820, 116),
            new Point(898, 95)};
    public final int DIAM = 30;

    // constructor
    public PlayerToken(Player player, Color color) {
        this.color = color;
        this.player = player;
        location = locations[player.getLocation()];
    }

    public Color getColor() {
        return color;
    }

    public Player getPlayer() {
        return player;
    }

    public int getX() {
        return location.x - DIAM / 2;
    }

    public int getY() {
        return location.y - DIAM / 2;
    }

    public int getPlayerLocation() {
        location = locations[player.getLocation()];
        return player.getLocation();
    }
}
