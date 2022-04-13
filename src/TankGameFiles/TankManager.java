package TankGameFiles;
import java.util.ArrayList;
import java.util.List;

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
        for (int i = 0; i<2; i++){
            Tank tank = new Tank(0, 0);
            tank.setPosition(x, y);
            x+=800;
            canvas.add(tank);
            tanks.add(tank);
        }
    }
}
