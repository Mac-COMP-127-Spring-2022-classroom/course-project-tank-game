package TankGameFiles;

import edu.macalester.graphics.CanvasWindow;

public class TankGame {
    private final int CANVAS_HEIGHT = 600;
    private final int CANVAS_WIDTH = 1000;    

    public TankGame() {
        CanvasWindow canvas = new CanvasWindow("Tanks!", CANVAS_WIDTH, CANVAS_HEIGHT);
    }
    public static void main(String[] args) {
        new TankGame();
    }
}
