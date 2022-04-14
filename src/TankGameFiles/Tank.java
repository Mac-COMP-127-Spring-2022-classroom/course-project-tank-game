package TankGameFiles;

import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.GraphicsObject;
import edu.macalester.graphics.Image;
import edu.macalester.graphics.Line;
import edu.macalester.graphics.Point;
import edu.macalester.graphics.Rectangle;
import edu.macalester.graphics.events.Key;
import edu.macalester.graphics.events.KeyboardEvent;
import java.util.List;

public class Tank extends GraphicsGroup{
    
    private final double BARREL_LENGTH = 60;
    private final int BARREL_X_OFFSET = 25;
    private final int BARREL_Y_OFFSET = 15;
    // private final int TANK_WIDTH = 50;
    // private final int TANK_HEIGHT = 30;                 
    private boolean working = true;
    private double centerX;
    private double centerY;
    private Tank tank;
    private Image body;
    private GraphicsGroup group;
    private List<Tank> tanklist;

    public Tank(double centerX, double centerY, String imagePath) {
        this.centerX = centerX;
        this.centerY = centerY;  
        body = new Image(centerX, centerY, imagePath);
        body.setMaxHeight(200);
        body.setMaxWidth(150);
        add(body);
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

    public boolean isWorking() {
        return working;
    }

    public void fireCannon() {
        working = false;
    }

    public double getCenterX(){
        return body.getPosition().getX();
    }
    public double getCenterY(){
        return body.getPosition().getY();
    }
    public Point getLeftPoint(){
        return new Point(body.getPosition().getX(), getCenterY());
    }
    // public Point getRightPoint(){
    //     return new Point(getCenterX()+75, getCenterY());
    // }
    //Should fire cannon be here or in TankGame? (Marcus)
}
