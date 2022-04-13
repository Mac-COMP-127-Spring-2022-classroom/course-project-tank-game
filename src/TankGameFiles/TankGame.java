package TankGameFiles;

import edu.macalester.graphics.CanvasWindow;

public class TankGame {
    private final int CANVAS_HEIGHT = 600;
    private final int CANVAS_WIDTH = 1000;
    private static TankGame game = new TankGame();
    private TankManager tankManager;
    private CanvasWindow canvas;

    public TankGame() {
        canvas = new CanvasWindow("Tanks!", CANVAS_WIDTH, CANVAS_HEIGHT);
        tankManager = new TankManager(canvas);
    }

    // public void cannonControls() {
    //     canvas.onKeyDown(event -> tank.setCannonAngle(event -> tank.setCannonAngle(event)));
    // }

    public void mainLoop(){
        game.run();
    }

    public void run() {
        tankManager.generateTanks();
        canvas.draw();
    }

    public static void main(String[] args) {
        game.mainLoop();
    }
}
