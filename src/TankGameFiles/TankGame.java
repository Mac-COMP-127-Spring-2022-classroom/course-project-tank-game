package TankGameFiles;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.events.Key;
import edu.macalester.graphics.events.KeyboardEvent;

public class TankGame {
    private final int CANVAS_HEIGHT = 600;
    private final int CANVAS_WIDTH = 1200;
    private TankManager tankManager;
    private CanvasWindow canvas;
    private Cannonball cannonBall;
    private Terrain terrain;

    public TankGame() {
        canvas = new CanvasWindow("Tanks!", CANVAS_WIDTH, CANVAS_HEIGHT);
        tankManager = new TankManager(canvas);
        tankManager.generateTanks();
        terrain = new Terrain(canvas);
        terrain.generateTerrain(CANVAS_WIDTH);
        canvas.draw();
        tankControls(tankManager.getWorkingTank(), tankManager.getWorkingCannon());
        cannonControls(tankManager.getWorkingTank(), tankManager.getWorkingCannon());
        
    }

    public void tankControls(Tank tank, Cannon cannon) {
        canvas.onKeyDown(event -> tankManager.moveTank(event));
    }

    public void cannonControls(Tank tank, Cannon cannon) {
        canvas.onKeyDown(event -> tankManager.setCannonAngle(event));
        canvas.onKeyDown(event -> tankManager.fireCannon(event, 50));
    }


    public boolean testHit(Cannonball ball) {
        if (tankManager.intersectsWithBottomPoint(ball) || tankManager.intersectsWithLeftOrRightPoint(ball)
            || tankManager.intersectsWithTopPoint(ball)) {
            tankManager.getWorkingTank().reduceHP();
            System.out.println(tankManager.getWorkingTank().getHP());
            return true;
        }
        return false;
    }
    
    // We will bring back the run method later. We just don't need it at this time.

    public static void main(String[] args) {
        TankGame game = new TankGame();             
    }
}
