import java.awt.*; 

public class Obstacle extends Polygon {
    
    //Color sets a specific color to it
    private Color color; 
    //Active boolean checks if the obstacle is present in the game
    private boolean active; 

    public Obstacle(Point[] shape, Point position, double rotation, Color color) {
        super(shape, position, rotation);
        this.color = color; 
        //Default setting for active is true
        this.active = true; 
    }

    public void paint(Graphics brush) {
        //Checking if the obstacle is active in the session
        if(active == false ) {
            //Likewise returnin gfrom the method 
            return; 
        }

        brush.setColor(color);
        Point[] points = getPoints();
        int[] x = new int[points.length];
        int[] y = new int[points.length];
        for (int i = 0; i < points.length; i++) {
            x[i] = (int) points[i].x;
            y[i] = (int) points[i].y;
        }
        brush.fillPolygon(x, y, points.length);
        
    }

    //Deactivate method changes the active status to false
    public void deactivate() {
        this.active = false;
    }

    //Activate method changes the active status to true
    public void activate() {
        this.active = true; 
    }

    //getActive returns the active status of the obstacle
    public boolean getActive() {
        return this.active; 
    }

}