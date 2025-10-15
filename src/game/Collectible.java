package game;

import java.awt.*;
import java.util.Random;

/**
 * Class representing a collectible jewel in the game.
 */
class Collectible implements GameObject {
	protected Polygon shape;
    private JewelType type;
    private double rotationSpeed = 5.0; // Rotation speed in degrees per update
    private static final Random random = new Random();

    public Collectible(Point position, JewelType type) {
        Point[] points = {
            new Point(0, 0),
            new Point((int)(10 * 1.2), (int)(5 * 1.2)),  // Scaled up by 1.2
            new Point(0, (int)(10 * 1.2)),
            new Point((int)(-10 * 1.2), (int)(5 * 1.2))
        };
        this.shape = new Polygon(points, position, random.nextInt(360)); // Random initial rotation
        this.type = type;
    }

    @Override
    public void draw(Graphics brush) {
        brush.setColor(type.getColor());
        Point[] pts = shape.getPoints();
        int[] x = new int[pts.length], y = new int[pts.length];
        for (int i = 0; i < pts.length; i++) { x[i] = (int)pts[i].x; y[i] = (int)pts[i].y; }
        brush.fillPolygon(x, y, pts.length);
    }

    @Override
    public void update(double deltaTime) {
        shape.rotate((int)(rotationSpeed * deltaTime * 60)); // Rotate based on deltaTime and 60 FPS
    }

    @Override
    public Point getPosition() { return shape.position; }

    @Override
    public Polygon getShape() { return shape; } // Added to comply with interface

    public int getValue() { return type.getValue(); }
}