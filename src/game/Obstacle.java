package game;

import java.awt.*;
import java.util.Random;

/**
 * Class representing an obstacle in the game.
 */
class Obstacle implements GameObject {
    protected Polygon shape;
    private ObstacleType type;
    private static final Random random = new Random();

    public Obstacle(Point position, ObstacleType type) {
        Point[] points = {
            new Point(0, 0),
            new Point((int)(15 * 1.2), 0),  // Scaled up by 1.2
            new Point((int)(7.5 * 1.2), (int)(15 * 1.2))
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
        // No rotation for obstacles in bare bones
    }

    @Override
    public Point getPosition() { return shape.position; }

    @Override
    public Polygon getShape() { return shape; } // Added to comply with interface

    public int getPenalty() { return type.getPenalty(); }
}