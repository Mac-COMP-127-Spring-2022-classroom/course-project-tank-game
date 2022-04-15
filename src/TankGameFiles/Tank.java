package TankGameFiles;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.GraphicsObject;
import edu.macalester.graphics.Image;
import edu.macalester.graphics.Line;
import edu.macalester.graphics.Point;
import edu.macalester.graphics.Rectangle;
import edu.macalester.graphics.events.Key;
import edu.macalester.graphics.events.KeyboardEvent;
import java.util.List;

public class Tank extends Image{              
    private boolean working = true;
    private Image body;


    public Tank(double x, double y, String imagePath) {
        super(x, y, imagePath);
    }

    /**
     * Moves Tank
     * @param key
     */
    public void moveTank(KeyboardEvent key) {
        // if(body.getLeftPoint())
            if (key.getKey().equals(Key.valueOf("LEFT_ARROW"))){
                moveBy(-5, 0);
            }
            if (key.getKey().equals(Key.valueOf("RIGHT_ARROW"))){
                moveBy(5, 0);
            }
    }

    // public double getCannonAngle() {
    //     return angle;
    // }

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

    // public Line getCannon() {
    //     return cannon;
    // }

    public double getCenterX(){
        return getPosition().getX() + getWidth()/2;
    }
    public double getCenterY(){
        return getPosition().getY() + getHeight()/2;
    }
    // public Point getLeftPoint(){
    //     return new Point(body.getPosition().getX(), getCenterY());
    // }
    // public Point getRightPoint(){
    //     return new Point(getCenterX()+75, getCenterY());
    // }
    //Should fire cannon be here or in TankGame? (Marcus)
}
