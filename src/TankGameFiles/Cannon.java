package TankGameFiles;

import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.Image;
import edu.macalester.graphics.events.Key;
import edu.macalester.graphics.events.KeyboardEvent;


public class Cannon extends Image{
    private Image cannon;
    private boolean working = true;
    private int angle = 0; // Moved angle here so that we can always move the cannon from the last position noted.
    

    public Cannon(double x, double y, String imagePath){
        super(x, y, imagePath);
    }


    public double getAngle() {
        return angle;
    }

    public double setAngle(int input) {
        return angle += input;
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
    
}
