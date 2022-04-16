package TankGameFiles;

import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.Image;
import edu.macalester.graphics.events.Key;
import edu.macalester.graphics.events.KeyboardEvent;


public class Cannon extends Image{
    private boolean working = true;
    private double angle = 0; // Is in radians.
    public static final double MAX_WIDTH = 288;
    

    public Cannon(double x, double y, String imagePath){
        super(x, y, imagePath);
        setMaxWidth(MAX_WIDTH);
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

    public boolean isWorking() {
        return working;
    }

    public void switchWorking() {
        if (working) {
            working = false;
        }
        else {
            working = true;
        }
    }
    
    // public Point getLeftcannon(){
    //     return new Point(getCenterX(), getCenterY());
    // }
    public static double getMaxWidth() {
        return MAX_WIDTH;
    }
}
