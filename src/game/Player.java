package game;

import java.awt.*;

/**
 * Class representing the player in the game.
 */
class Player implements GameObject {
	protected Polygon shape;
    private double speed = 7.0; // Increased from 5.0 to 10.0 for faster movement
    private Point velocity = new Point(0, 0);
    protected boolean up, down, left, right;

    public Player(Point position) {
        Point[] points = {new Point(0, -10), new Point(10, 10), new Point(-10, 10)};
        this.shape = new Polygon(points, position, 0);
    }

    @Override
    public void draw(Graphics brush) {
        brush.setColor(Color.BLUE);
        Point[] pts = shape.getPoints();
        int[] x = new int[pts.length], y = new int[pts.length];
        for (int i = 0; i < pts.length; i++) { x[i] = (int)pts[i].x; y[i] = (int)pts[i].y; }
        brush.fillPolygon(x, y, pts.length);
    }

    @Override
    public void update(double deltaTime) {
        velocity.x = 0;
        velocity.y = 0;
        if (left) velocity.x -= speed;
        if (right) velocity.x += speed;
        if (up) velocity.y -= speed;
        if (down) velocity.y += speed;

        shape.position.x += velocity.x * deltaTime * 60;
        shape.position.y += velocity.y * deltaTime * 60;

        if (velocity.x != 0 || velocity.y != 0) {
            double angle = Math.toDegrees(Math.atan2(velocity.y, velocity.x));
            shape.rotation = angle + 90; // Rotate towards movement
        }
    }

    @Override
    public Point getPosition() { return shape.position; }

    @Override
    public Polygon getShape() { return shape; } // Added to comply with interface

    @Override
    public boolean collidesWith(GameObject other) {
        return false; // Player doesn't check collisions, others check against it
    }

    public void setMovement(boolean up, boolean down, boolean left, boolean right) {
        this.up = up;
        this.down = down;
        this.left = left;
        this.right = right;
    }
}