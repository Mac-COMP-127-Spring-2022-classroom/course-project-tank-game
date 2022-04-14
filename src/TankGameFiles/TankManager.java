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
                Tank redtank = new Tank(0, 0, "RedTank.png", "RedCannon.png");
                // Cannon redcannon = new Cannon (0,0,"RedCannon.png");
                redtank.setPosition(x, y);
                // redcannon.setPosition(x,y);
                x += 800;
                canvas.add(redtank);
                // canvas.add(redcannon);
                tanks.add(redtank);
            } 
            else {
                Tank bluetank = new Tank(0, 0, "BlueTank.png", "BlueCannon.png");
                // Cannon bluecannon = new Cannon (0,0,"BlueCannon.png");
                bluetank.setPosition(x, y);
                x += 800;
                // canvas.add(bluecannon);
                canvas.add(bluetank);
                tanks.add(bluetank);
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
