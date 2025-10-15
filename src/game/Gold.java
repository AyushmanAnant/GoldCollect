import java.awt.*; 

public class Gold extends Polygon implements Collectible {
    //Setting the value of each Gold bar at 100
    private final int value = 100; 

    //Gold Constructor 
    public Gold(Point[] shape, Point position, double rotation) {
        super(shape, position, rotation);
    }

    @Override
    public int getValue() {
        return value; 
    }

    @Override 
    public void paint(Graphics brush) {
        bursh.setColor(Color.YELLOW);
        Point[] points = getPoints();
        //Array for the x coordinates: 
        int[] x = new int[points.length];
        //Array for y coordinates  
        int[] y = new int[points.length];
        for (int i = 0; i < points.length; i++) {
            x[i] = (int) points[i].x;
            y[i] = (int) points[i].y;
        }
        brush.fillPolygon(x, y, points.length);
    }
}