package game;

import java.awt.*;

/**
 * Enum for different types of obstacles with penalties and colors.
 */
enum ObstacleType {
    SPIKE(-150, Color.RED);

    private final int penalty;
    private final Color color;

    ObstacleType(int penalty, Color color) {
        this.penalty = penalty;
        this.color = color;
    }

    public int getPenalty() { return penalty; }
    public Color getColor() { return color; }
}