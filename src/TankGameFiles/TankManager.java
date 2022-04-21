package TankGameFiles;

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
    private Tank redTank;
    private Tank blueTank;

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
                Tank redTank = new Tank(x, y, "RedTank.png", 92, cannonY, "RedCannon.png");
                redTank.setMaxHeight(50);
                redTank.getCannon().setMaxWidth(50);
                // System.out.println(redTank.getCenterX());
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
                System.out.println(redTank.getHP());
            } 
            else {
                Tank blueTank = new Tank(x, y, "BlueTank.png", 617.5 - 80.5, cannonY + 1 ,"BlueCannon.png");
                blueTank.getCannon().setAngle(180);
                blueTank.setMaxHeight(50);
                blueTank.getCannon().setMaxWidth(50);
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
            // getWorkingCannon().setAnchor(getWorkingTank().getCenter());
            getWorkingCannon().rotateBy(5);
            getWorkingCannon().setAngle(getWorkingCannon().getAngle() - 5);
            getWorkingCannon().setCenter(25 * Math.cos(Math.toRadians(getWorkingCannon().getAngle())) + getWorkingTank().getCenterX(),  - 25 * Math.sin(Math.toRadians(getWorkingCannon().getAngle())) +getWorkingTank().getY() +7.5);
        }
        if (key.getKey().equals(Key.valueOf("UP_ARROW")) && getWorkingCannon().getAngle()<180){
            getWorkingCannon().rotateBy(-5);
            getWorkingCannon().setAngle(getWorkingCannon().getAngle() + 5);
            getWorkingCannon().setCenter(25 * Math.cos(Math.toRadians(getWorkingCannon().getAngle())) + getWorkingTank().getCenterX(),   - 25 * Math.sin(Math.toRadians(getWorkingCannon().getAngle()))+getWorkingTank().getY() + 7.5);

        }
        // cannon.setRotation(cannon.getAngle());
    }

    public void fireCannon(KeyboardEvent key, double initialSpeed) {
        Cannonball ball = new Cannonball((25+(getWorkingCannon().getImageWidth()/2)) * Math.cos(Math.toRadians(getWorkingCannon().getAngle())) + getWorkingTank().getCenterX(),   (25+(getWorkingTank().getImageWidth()/2)) * -Math.sin(Math.toRadians(getWorkingCannon().getAngle()))+getWorkingTank().getY() +7.5, initialSpeed, getWorkingCannon().getAngle(), canvas.getWidth(), canvas.getHeight());
        if (key.getKey().equals(Key.valueOf("SPACE"))) {
            switchWorkingTank();
            boolean ballIsOnCanvas = ball.addToCanvas(canvas);
            while (ball.updatePosition(0.1)) {
                if (intersectsWithBottomPoint(ball)||intersectsWithLeftOrRightPoint(ball)||intersectsWithTopPoint(ball)){
                    ballIsOnCanvas = ball.removeFromCanvas(canvas);
                    getWorkingTank().reduceHP();
                    System.out.println(getWorkingTank().getHP() + "\t" + getWorkingTank());
                }
                canvas.draw();
            }
            if (!ballIsOnCanvas) {
            } else {        
                ball.removeFromCanvas(canvas);
            }
        }
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

     /**
     * Checks if brick intersects with the top point. If it does the ball reverses Y direction and
     * breaks the brick.
     */
    public boolean intersectsWithTopPoint(Cannonball ball) {
        if (canvas.getElementAt(ball.getTopPoint()) instanceof Tank) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Checks if brick intersects with the left or right point. If it does the ball reverses X direction
     * and breaks the brick.
     */
    public boolean intersectsWithLeftOrRightPoint(Cannonball ball) {
        if (canvas.getElementAt(ball.getRightPoint()) instanceof Tank) {
            return true;
        }
        if (canvas.getElementAt(ball.getLeftPoint()) instanceof Tank) {
            return true;
        }
        return false;
    }

    /**
     * Checks if tank intersects with the bottom point of the ball. If it does the ball is destroyed and a life is lost.
     */
    public boolean intersectsWithBottomPoint(Cannonball ball) {
        if (canvas.getElementAt(ball.getBottomPoint()) instanceof Tank) {
            return true;
        } else {
            return false;
        }
    }
    


    public Cannon getWorkingCannon(){
        return getWorkingTank().getCannon();
    } 
}
