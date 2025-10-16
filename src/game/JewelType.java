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

    /**
     * Constructs a JewelType with a value and color.
     * @param value The point value of the jewel
     * @param color The color to display the jewel
     */
    JewelType(int value, Color color) {
        this.value = value;
        this.color = color;
    }

    /**
     * Returns the point value of the jewel.
     * @return The value of the jewel
     */
    public int getValue() { return value; }

    /**
     * Returns the color of the jewel.
     * @return The Color of the jewel
     */
    public Color getColor() { return color; }
}