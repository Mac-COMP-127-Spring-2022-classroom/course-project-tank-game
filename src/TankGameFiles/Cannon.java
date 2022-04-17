package TankGameFiles;

import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.Image;
import edu.macalester.graphics.events.Key;
import edu.macalester.graphics.events.KeyboardEvent;


public class Cannon extends Image{
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
    
    // public Point getLeftcannon(){
    //     return new Point(getCenterX(), getCenterY());
    // }

}
