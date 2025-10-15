package game;

import java.awt.*;

/**
 * Enum for different types of jewels with values and colors.
 */
enum JewelType {
    GOLD(100, Color.YELLOW),
    DIAMOND(200, Color.CYAN),
    EMERALD(300, Color.GREEN);

    private final int value;
    private final Color color;

    JewelType(int value, Color color) {
        this.value = value;
        this.color = color;
    }

    public int getValue() { return value; }
    public Color getColor() { return color; }
}