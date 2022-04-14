package TankGameFiles;

import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.GraphicsObject;
import edu.macalester.graphics.Line;
import edu.macalester.graphics.Rectangle;
import edu.macalester.graphics.events.Key;
import edu.macalester.graphics.events.KeyboardEvent;

public class Tank extends GraphicsGroup{
    private final double BARREL_LENGTH = 60;
    private final int BARREL_X_OFFSET = 25;
    private final int BARREL_Y_OFFSET = 15;
    private final int TANK_WIDTH = 50;
    private final int TANK_HEIGHT = 30;
    private int angle = 0;                  // Moved angle here so that we can always move the cannon from the last position noted.
    private boolean working = true;
    private double centerX;
    private double centerY;
    private Line cannon;
    private Tank tank;
    private Rectangle body;

    public Tank(double centerX, double centerY) {
        this.centerX = centerX;
        this.centerY = centerY;
        body = new Rectangle(centerX, centerY, TANK_WIDTH, TANK_HEIGHT);
        cannon = new Line(centerX+BARREL_X_OFFSET, centerY+BARREL_Y_OFFSET, centerX+BARREL_X_OFFSET+BARREL_LENGTH, centerY+BARREL_Y_OFFSET);
        add(body);
        add(cannon);
    }

    public void moveTank(KeyboardEvent key) {
        if (key.getKey().equals(Key.valueOf("LEFT_ARROW"))){
            moveBy(-5, 0);
        }
        if (key.getKey().equals(Key.valueOf("RIGHT_ARROW"))){
            moveBy(5, 0);
        }
    }

    public void setCannonAngle(KeyboardEvent key) {
        if (key.getKey().equals(Key.valueOf("DOWN_ARROW")) && angle>0){
            angle -= 5;
        }
        if (key.getKey().equals(Key.valueOf("UP_ARROW")) && angle<180){
            angle += 5;
        }
        double x2 = cannon.getX1() + BARREL_LENGTH * Math.cos(Math.toRadians(angle));
        double y2 = cannon.getY1() + BARREL_LENGTH * -Math.sin(Math.toRadians(angle));
        cannon.setEndPosition(x2, y2);
    }

    public boolean isWorking() {
        return working;
    }

    public void fireCannon() {
        working = false;
    }
    //Should fire cannon be here or in TankGame? (Marcus)
}
