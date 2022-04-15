package TankGameFiles;

import java.util.ArrayList;
import java.util.List;
import edu.macalester.graphics.events.Key;
import edu.macalester.graphics.events.KeyboardEvent;

import edu.macalester.graphics.CanvasWindow;

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
        for (int i = 0; i < 2; i++) {
            if (i < 1) {
                Tank redTank = new Tank(x, y, "RedTank.png");
                Cannon redCannon = new Cannon (x, y - 30, "RedCannon.png");
                x += 500;
                canvas.add(redCannon);
                canvas.add(redTank);
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
    public void setCannonAngle(KeyboardEvent key, Cannon cannon) {
        if (key.getKey().equals(Key.valueOf("UP_ARROW")) && cannon.getAngle()>-180){
            cannon.setAngle(-5);
        }
        if (key.getKey().equals(Key.valueOf("DOWN_ARROW")) && cannon.getAngle()<0){
            cannon.setAngle(5);
        }
        cannon.setRotation(cannon.getAngle());
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
