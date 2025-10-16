package game;

import java.awt.*;

/**
 * Enum for different types of obstacles with penalties and colors.
 */
enum ObstacleType {
    SPIKE(-150, Color.RED);

    private final int penalty;
    private final Color color;

    /**
     * Constructs an ObstacleType with a penalty and color.
     * @param penalty The point penalty for collision
     * @param color The color to display the obstacle
     */
    ObstacleType(int penalty, Color color) {
        this.penalty = penalty;
        this.color = color;
    }

    /**
     * Returns the penalty value of the obstacle.
     * @return The penalty value
     */
    public int getPenalty() { return penalty; }

    /**
     * Returns the color of the obstacle.
     * @return The Color of the obstacle
     */
    public Color getColor() { return color; }
}