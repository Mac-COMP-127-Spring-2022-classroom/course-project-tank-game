package TankGameFiles;

import edu.macalester.graphics.CanvasWindow;

public class TankGame {
    private final int CANVAS_HEIGHT = 600;
    private final int CANVAS_WIDTH = 1000;
    private CanvasWindow canvas;

    public TankGame() {
        canvas = new CanvasWindow("Tanks!", CANVAS_WIDTH, CANVAS_HEIGHT);
    }

    public void cannonControls() {
        canvas.onKeyDown(event -> tank.setCannonAngle(event -> tank.setCannonAngle(event)));
    }

    public static void main(String[] args) {
        new TankGame();
    }
}
