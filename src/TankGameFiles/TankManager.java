package TankGameFiles;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import edu.macalester.graphics.events.Key;
import edu.macalester.graphics.events.KeyboardEvent;
import edu.macalester.graphics.Ellipse;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Rectangle;

public class TankManager {
    private CanvasWindow canvas;
    private List<Tank> tanks;
    private List<Cannon> cannons;

    public TankManager(CanvasWindow canvas) {
        tanks = new ArrayList<>();
        cannons = new ArrayList<>();
        this.canvas = canvas;
    }

    public void generateTanks() {
        int x = 50;
        int y = 400;
        int cannonY=y+5;
        for (int i = 0; i < 2; i++) {
            if (i < 1) {
                Tank redTank = new Tank(x, y, "RedTank.png", cannonY, "RedCannon.png");
                // Rectangle rect = new Rectangle(x, y, redTank.getWidth(), redTank.getHeight());
                // Rectangle rect2 = new Rectangle(x, y, redCannon.getWidth(), redCannon.getHeight());
                // rect2.setStrokeColor(Color.BLUE);
                // redCannon.setAnchor(redCannon.getX(), redCannon.getY() + redCannon.getHeight()/2);
                x += 500;
                canvas.add(redTank.getCannon());
                canvas.add(redTank);
                // canvas.add(rect);
                // canvas.add(rect2);
                tanks.add(redTank);
            } 
            else {
                Tank blueTank = new Tank(x, y, "BlueTank.png", cannonY ,"BlueCannon.png");
                blueTank.getCannon().setAngle(180);
                x += 500;
                canvas.add(blueTank.getCannon());
                canvas.add(blueTank);
                tanks.add(blueTank);
                blueTank.switchWorking();
            }
        }
    }
       /**
     * Moves Tank
     * @param key
     */
    public void moveTank(KeyboardEvent key) {
        if(getWorkingTank().getCenterX()-75 > 0 ){ 
            if (key.getKey().equals(Key.valueOf("LEFT_ARROW"))){
                getWorkingTank().moveBy(-5, 0);
                getWorkingCannon().moveBy(-5, 0);
            }
        }
        if(getWorkingTank().getCenterX()+75 < 1200 ){
            if (key.getKey().equals(Key.valueOf("RIGHT_ARROW"))){
                getWorkingTank().moveBy(5, 0);
                getWorkingCannon().moveBy(5, 0);
            }
        }
    }

    /**
    * Changes cannon angle
    * @param key
    * @param cannon
    */
    public void setCannonAngle(KeyboardEvent key) {
        if (key.getKey().equals(Key.valueOf("DOWN_ARROW")) && getWorkingCannon().getAngle()>0){
            getWorkingCannon().rotateBy(5);
            getWorkingCannon().setAngle(getWorkingCannon().getAngle() - 5);
            getWorkingCannon().setCenter(50 * Math.cos(Math.toRadians(getWorkingCannon().getAngle())) + getWorkingTank().getCenterX(),  - 50 * Math.sin(Math.toRadians(getWorkingCannon().getAngle())) +getWorkingTank().getY() +15);
        }
        if (key.getKey().equals(Key.valueOf("UP_ARROW")) && getWorkingCannon().getAngle()<180){
            getWorkingCannon().rotateBy(-5);
            getWorkingCannon().setAngle(getWorkingCannon().getAngle() +5);
            getWorkingCannon().setCenter(50 * Math.cos(Math.toRadians(getWorkingCannon().getAngle())) + getWorkingTank().getCenterX(),   - 50 * Math.sin(Math.toRadians(getWorkingCannon().getAngle()))+getWorkingTank().getY() + 15);

        }
        // cannon.setRotation(cannon.getAngle());
    }

    public void fireCannon(KeyboardEvent key, double initialSpeed) {
        Cannonball ball = new Cannonball((50+(getWorkingCannon().getImageWidth()/2)) * Math.cos(Math.toRadians(getWorkingCannon().getAngle())) + getWorkingTank().getCenterX(),   (50+(getWorkingTank().getImageWidth()/2)) * -Math.sin(Math.toRadians(getWorkingCannon().getAngle()))+getWorkingTank().getY() +15, initialSpeed, getWorkingCannon().getAngle(), canvas.getWidth(), canvas.getHeight());
        ball.addToCanvas(canvas);
        if (key.getKey().equals(Key.valueOf("SPACE"))) {
            switchWorkingTank();
            while (ball.updatePosition(0.1)) {
                canvas.draw();
            }
        }
        ball.removeFromCanvas(canvas);
    }

    public List<Tank> getTanks() {
        return tanks;
    }

    public Tank getWorkingTank() {
        for (Tank t : tanks) {
            if (t.isWorking()) {
                return t;
            }
        }
        return null;
    }

    public void switchWorkingTank(){
        for (Tank t : tanks) {
            t.switchWorking();
        }
    }


    public Cannon getWorkingCannon(){
        return getWorkingTank().getCannon();
    }

    public boolean testHit(Cannonball ball) {

        if (ball.intersectsWithBottomPoint() || ball.intersectsWithLeftorRightPoint()
            || ball.intersectsWithTopPoint()) {
            return true;

        }
        return false;
    }

 
}
