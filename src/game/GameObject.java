package game;

import java.awt.*;

/**
 * Interface for game objects that can be drawn, updated, and checked for collisions.
 */
interface GameObject {
    /**
     * Draws the game object using the provided graphics context.
     * @param brush The Graphics object to draw with
     */
    void draw(Graphics brush);

    /**
     * Updates the game object's state based on the elapsed time.
     * @param deltaTime The time elapsed since the last update in seconds
     */
    void update(double deltaTime);

    /**
     * Returns the position of the game object.
     * @return The Point representing the object's position
     */
    Point getPosition();

    /**
     * Returns the shape of the game object.
     * @return The Polygon representing the object's shape
     */
    Polygon getShape();

    /**
     * Checks if this object collides with another game object.
     * @param other The other GameObject to check collision with
     * @return true if a collision occurs, false otherwise
     */
    default boolean collidesWith(GameObject other) {
        if (other instanceof Player) {
            Player p = (Player) other;
            double dx = this.getPosition().x - p.getPosition().x;
            double dy = this.getPosition().y - p.getPosition().y;
            double distance = Math.sqrt(dx * dx + dy * dy);
            return distance < 20; //Distance-based for immediate touch detection
        }
        return false;
    }
}