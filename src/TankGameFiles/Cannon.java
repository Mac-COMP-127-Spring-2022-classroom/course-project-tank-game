package TankGameFiles;

import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.Image;
import edu.macalester.graphics.Point;
import edu.macalester.graphics.events.Key;
import edu.macalester.graphics.events.KeyboardEvent;


public class Cannon extends Image{
    private Point point;
    private double angle = 0; // Is in radians.
    

    public Cannon(double x, double y, String imagePath){
        super(x, y, imagePath);
    }

    /**
     * Returns the cannon's angle in radians.
     * @return
     */
    public double getAngle() {
        return angle;
    }

    public void setAngle(double input) {
        angle = input;
    }

    // public Point getPoint() {
    //     return point;
    // }

    // public void setPoint(Point p) {
    //     point = p;
    // }
    
    // public Point getLeftcannon(){
    //     return new Point(getCenterX(), getCenterY());
    // }

}
