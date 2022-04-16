package TankGameFiles;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import edu.macalester.graphics.events.Key;
import edu.macalester.graphics.events.KeyboardEvent;

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
                Tank redTank = new Tank(x, y, "RedTank.png");
                Cannon redCannon = new Cannon (redTank.getCenterX(), cannonY, "RedCannon.png");
                // Rectangle rect = new Rectangle(x, y, redTank.getWidth(), redTank.getHeight());
                // Rectangle rect2 = new Rectangle(x, y, redCannon.getWidth(), redCannon.getHeight());
                // rect2.setStrokeColor(Color.BLUE);
                // redCannon.setAnchor(redCannon.getX(), redCannon.getY() + redCannon.getHeight()/2);
                x += 500;
                canvas.add(redCannon);
                canvas.add(redTank);
                // canvas.add(rect);
                // canvas.add(rect2);
                tanks.add(redTank);
                cannons.add(redCannon);
            } 
            else {
                Tank blueTank = new Tank(x, y, "BlueTank.png");
                Cannon blueCannon = new Cannon (x, y - 30,"BlueCannon.png");
                x += 500;
                canvas.add(blueCannon);
                canvas.add(blueTank);
                tanks.add(blueTank);
                cannons.add(blueCannon);
            }
        }
    }
       /**
     * Moves Tank
     * @param key
     */
    public void moveTank(KeyboardEvent key, Tank tank, Cannon cannon) {
        if(tank.getCenterX()-75 > 0 ){ //not working properly
            if (key.getKey().equals(Key.valueOf("LEFT_ARROW"))){
                tank.moveBy(-5, 0);
                cannon.moveBy(-5, 0);
            }
            if (key.getKey().equals(Key.valueOf("RIGHT_ARROW"))){
                tank.moveBy(5, 0);
                cannon.moveBy(5, 0);
            }
        }
    }

    /**
    * Changes cannon angle
    * @param key
    * @param cannon
    */
    public void setCannonAngle(KeyboardEvent key, Tank tank, Cannon cannon) {
        if (key.getKey().equals(Key.valueOf("DOWN_ARROW"))){
            cannon.rotateBy(5);
            cannon.setAngle(cannon.getAngle() - 5);
            cannon.setCenter(50 * Math.cos(Math.toRadians(cannon.getAngle())) + tank.getCenterX(),  - 50 * Math.sin(Math.toRadians(cannon.getAngle()))+tank.getY() +15);
        }
        if (key.getKey().equals(Key.valueOf("UP_ARROW"))){
            cannon.rotateBy(-5);
            cannon.setAngle(cannon.getAngle() +5);
            cannon.setCenter(50 * Math.cos(Math.toRadians(cannon.getAngle())) + tank.getCenterX(),   - 50 * Math.sin(Math.toRadians(cannon.getAngle()))+tank.getY() + 15);

        }
        // cannon.setRotation(cannon.getAngle());
    }

    public void fireCannon(KeyboardEvent key, Cannon cannon, double initialSpeed) {
        Cannonball ball = new Cannonball(getWorkingTank().getX() + getWorkingTank().getWidth(), getWorkingTank().getY(), initialSpeed, cannon.getAngle(), canvas.getWidth(), canvas.getHeight());
        ball.addToCanvas(canvas);
        if (key.getKey().equals(Key.valueOf("SPACE"))) {
            System.out.println(getWorkingTank().getX());
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

    public Cannon getWorkingCannon() {
        for (Cannon c : cannons) {
            if (c.isWorking()) {
                return c;
            }
        }
        return null;
    }
}
