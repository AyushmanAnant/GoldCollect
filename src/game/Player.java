package game;

import java.awt.*;

/**
 * Class representing the player in the game.
 */
class Player implements GameObject {
    protected Polygon shape;
    private double speed = 7.0;
    private Point velocity = new Point(0, 0);
    protected boolean up, down, left, right;

    /**
     * Constructs a Player at the specified position.
     * @param position The starting position of the player
     */
    public Player(Point position) {
        Point[] points = {new Point(0, -10), new Point(10, 10), new Point(-10, 10)};
        this.shape = new Polygon(points, position, 0);
    }

    /**
     * Draws the player using the provided graphics context.
     * @param brush The Graphics object to draw with
     */
    @Override
    public void draw(Graphics brush) {
        brush.setColor(Color.BLUE);
        Point[] pts = shape.getPoints();
        int[] x = new int[pts.length], y = new int[pts.length];
        for (int i = 0; i < pts.length; i++) { x[i] = (int)pts[i].x; y[i] = (int)pts[i].y; }
        brush.fillPolygon(x, y, pts.length);
    }

    /**
     * Updates the player's state, including movement and rotation.
     * @param deltaTime The time elapsed since the last update in seconds
     */
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
            shape.rotation = angle + 90; //Rotate towards movement
        }
    }

    /**
     * Returns the position of the player.
     * @return The Point representing the player's position
     */
    @Override
    public Point getPosition() { return shape.position; }

    /**
     * Returns the shape of the player.
     * @return The Polygon representing the player's shape
     */
    @Override
    public Polygon getShape() { return shape; }

    /**
     * Checks if this player collides with another game object (not used, handled by others).
     * @param other The other GameObject to check collision with
     * @return false as player doesn't check collisions
     */
    @Override
    public boolean collidesWith(GameObject other) {
        return false; //Player doesn't check collisions, others check against it
    }

    /**
     * Sets the movement states for the player.
     * @param up Whether the up key is pressed
     * @param down Whether the down key is pressed
     * @param left Whether the left key is pressed
     * @param right Whether the right key is pressed
     */
    public void setMovement(boolean up, boolean down, boolean left, boolean right) {
        this.up = up;
        this.down = down;
        this.left = left;
        this.right = right;
    }
}