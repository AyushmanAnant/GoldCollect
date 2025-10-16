package game;

import java.awt.*;
import java.util.Random;

/**
 * Class representing an obstacle in the game.
 */
class Obstacle implements GameObject {
    protected Polygon shape;
    private ObstacleType type;
    private double rotationSpeed = 2.0;
    private static final Random random = new Random();

    /**
     * Constructs an Obstacle at the specified position with the given obstacle type.
     * @param position The starting position of the obstacle
     * @param type The type of obstacle (SPIKE)
     */
    public Obstacle(Point position, ObstacleType type) {
        Point[] points = {new Point(0, 0), new Point(16, 0), new Point(8,16)
        };
        this.shape = new Polygon(points, position, random.nextInt(360)); //Random initial rotation
        this.type = type;
    }

    /**
     * Draws the obstacle using the provided graphics context.
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
     * Updates the obstacle's state, including rotation.
     * @param deltaTime The time elapsed since the last update in seconds
     */
    @Override
    public void update(double deltaTime) {
        shape.rotate((int)(rotationSpeed * deltaTime * 60));
    }

    /**
     * Returns the position of the obstacle.
     * @return The Point representing the obstacle's position
     */
    @Override
    public Point getPosition() { return shape.position; }

    /**
     * Returns the shape of the obstacle.
     * @return The Polygon representing the obstacle's shape
     */
    @Override
    public Polygon getShape() { return shape; }

    /**
     * Returns the penalty value of the obstacle.
     * @return The penalty value
     */
    public int getPenalty() { return type.getPenalty(); }
}