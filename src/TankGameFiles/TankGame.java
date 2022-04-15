package TankGameFiles;

import edu.macalester.graphics.CanvasWindow;

public class TankGame {
    private final int CANVAS_HEIGHT = 600;
    private final int CANVAS_WIDTH = 1000;
    private TankManager tankManager;
    private CanvasWindow canvas;

    public TankGame() { // Refactored the constructor to make code more readable. 
        canvas = new CanvasWindow("Tanks!", CANVAS_WIDTH, CANVAS_HEIGHT);
        tankManager = new TankManager(canvas);
        tankManager.generateTanks();
        canvas.draw();
        tankControls(tankManager.getWorkingTank());
        // cannonControls(tankManager.getWorkingTank());
    }

    public void tankControls(Tank tank) {
        canvas.onKeyDown(event -> tank.moveTank(event));
    }

    // public void cannonControls(Tank tank) {
    //     canvas.onKeyDown(event -> tank.setCannonAngle(event));
    // }

    
    
    // We will bring back the run method later. We just don't need it at this time.

    public static void main(String[] args) {
        TankGame game = new TankGame();             
    }
}
