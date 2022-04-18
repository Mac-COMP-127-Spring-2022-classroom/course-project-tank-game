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


     /**
     * Checks if brick intersects with the top point. If it does the ball reverses Y direction and
     * breaks the brick.
     */
    public boolean intersectsWithTopPoint() {
        if (canvas.getElementAt(cannonBall.getTopPoint()) instanceof Tank) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Checks if brick intersects with the left or right point. If it does the ball reverses X direction
     * and breaks the brick.
     */
    public boolean intersectsWithLeftorRightPoint() {

        if (canvas.getElementAt(cannonBall.getRightPoint()) instanceof Tank) {
            return true;
        }
        if (canvas.getElementAt(cannonBall.getLeftPoint()) instanceof Tank) {

            return true;
        }
        return false;
    }

    /**
     * Checks if tank intersects with the bottom point of the ball. If it does the ball is destroyed and a life is lost.
     */
    public boolean intersectsWithBottomPoint() {
        if (canvas.getElementAt(cannonBall.getBottomPoint()) instanceof Tank) {

            return true;
        } else {
            return false;
        }
    }
    
    public boolean testHit(Cannonball ball) {

        if (intersectsWithBottomPoint() || intersectsWithLeftorRightPoint()
            || intersectsWithTopPoint()) {
            return true;

        }
        return false;
    }
    
    // We will bring back the run method later. We just don't need it at this time.

    public static void main(String[] args) {
        TankGame game = new TankGame();             
    }
}
