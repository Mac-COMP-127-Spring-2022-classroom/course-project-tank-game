package TankGameFiles;

import edu.macalester.graphics.Image;

public class Cannon extends Image {

    private double angle = 0; // Is in radians.


    public Cannon(double x, double y, String imagePath) {
        super(x, y, imagePath);
    }

    /**
     * Returns the cannon's angle in radians.
     */
    public double getAngle() {
        return angle;
    }

    public void setAngle(double input) {
        angle = input;
    }
}
