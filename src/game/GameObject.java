package game;

import java.awt.*;

/**
 * Interface for game objects that can be drawn, updated, and checked for collisions.
 */
interface GameObject {
    void draw(Graphics brush);
    void update(double deltaTime);
    Point getPosition();
    Polygon getShape(); // Added to access the shape's position
    default boolean collidesWith(GameObject other) {
        if (other instanceof Player) {
            Player p = (Player) other;
            double dx = this.getPosition().x - p.getPosition().x;
            double dy = this.getPosition().y - p.getPosition().y;
            double distance = Math.sqrt(dx * dx + dy * dy);
            return distance < 20; // Distance-based for immediate touch detection
        }
        return false;
    }
}