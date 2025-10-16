package game;

import java.awt.*;
import java.util.Random;

/**
 * Class representing a collectible jewel in the game.
 */
class Collectible implements GameObject {
    protected Polygon shape;
    private JewelType type;
    private double rotationSpeed = 5.0;
    private static final Random random = new Random();

    /**
     * Constructs a Collectible at the specified position with the given jewel type.
     * @param position The starting position of the collectible
     * @param type The type of jewel (GOLD, DIAMOND, or EMERALD)
     */
    public Collectible(Point position, JewelType type) {
        Point[] points = {new Point(0, 0), new Point(11, 6), new Point(0, 11), new Point(-11, 6)
        };
        this.shape = new Polygon(points, position, random.nextInt(360)); //Random initial rotation
        this.type = type;
    }

    /**
     * Draws the collectible using the provided graphics context.
     * @param brush The Graphics object to draw with
     */
    @Override
    public void draw(Graphics brush) {
        brush.setColor(type.getColor());
        Point[] pts = shape.getPoints();
        int[] x = new int[pts.length], y = new int[pts.length];
        for (int i = 0; i < pts.length; i++) { x[i] = (int)pts[i].x; y[i] = (int)pts[i].y; }
        brush.fillPolygon(x, y, pts.length);
    }

    /**
     * Updates the collectible's state, including rotation.
     * @param deltaTime The time elapsed since the last update in seconds
     */
    @Override
    public void update(double deltaTime) {
        shape.rotate((int)(rotationSpeed * deltaTime * 60));
    }

    /**
     * Returns the position of the collectible.
     * @return The Point representing the collectible's position
     */
    @Override
    public Point getPosition() { return shape.position; }

    /**
     * Returns the shape of the collectible.
     * @return The Polygon representing the collectible's shape
     */
    @Override
    public Polygon getShape() { return shape; }

    /**
     * Returns the point value of the collectible.
     * @return The value of the collectible
     */
    public int getValue() { return type.getValue(); }
}