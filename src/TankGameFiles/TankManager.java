package TankGameFiles;

import java.util.ArrayList;
import java.util.List;
import edu.macalester.graphics.events.Key;
import edu.macalester.graphics.events.KeyboardEvent;

import edu.macalester.graphics.CanvasWindow;

public class TankManager {
    private CanvasWindow canvas;
    private List<Tank> tanks;

    public TankManager(CanvasWindow canvas) {
        tanks = new ArrayList<>();
        this.canvas = canvas;
    }

    public void generateTanks() {
        int x = 50;
        int y = 400;
        for (int i = 0; i < 2; i++) {
            if (i < 1) {
                Tank redTank = new Tank(x, y, "RedTank.png");
                Cannon redCannon = new Cannon (x, y - 30, "RedCannon.png");
                System.out.println(redCannon.getWidth());
                x += 500;
                canvas.add(redCannon);
                canvas.add(redTank);
                tanks.add(redTank);
            } 
            else {
                Tank blueTank = new Tank(x, y, "BlueTank.png");
                Cannon blueCannon = new Cannon (x, y - 30,"BlueCannon.png");
                blueTank.setPosition(x, y);
                canvas.add(blueCannon);
                canvas.add(blueTank);
                tanks.add(blueTank);
            }
        }
    }
       /**
     * Moves Tank
     * @param key
     */
    public void moveTank(KeyboardEvent key, Tank tank) {
        if(tank.getCenterX()-75 > 0 ){ //not working properly
            if (key.getKey().equals(Key.valueOf("LEFT_ARROW"))){
                tank.moveBy(-5, 0);
            }
            if (key.getKey().equals(Key.valueOf("RIGHT_ARROW"))){
                tank.moveBy(5, 0);
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
}
