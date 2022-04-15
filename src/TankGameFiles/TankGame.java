package TankGameFiles;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.events.Key;
import edu.macalester.graphics.events.KeyboardEvent;

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
        tankControls(tankManager.getWorkingTank(), tankManager.getWorkingCannon());
        cannonControls(tankManager.getWorkingCannon());
        // cannonShoot(tankManager.getWorkingTank());
        // canvas.animate(() -> System.out.println(canvas.getKeysPressed()));
        
    }

    public void tankControls(Tank tank, Cannon cannon) {
        canvas.onKeyDown(event -> tankManager.moveTank(event, tank, cannon));
    }

    public void cannonControls(Cannon cannon) {
        canvas.onKeyDown(event -> tankManager.setCannonAngle(event, cannon));
    }

    // public void cannonShoot(Tank tank) {
    //     canvas.onKeyDown(event -> fireCannon(event, tank, 1, canvas));
    // }

    // public void fireCannon(KeyboardEvent key, Tank tank, double initialSpeed, CanvasWindow canvas) {
    //     Cannonball ball = new Cannonball(tank.getCenterX(), tank.getCannon().getY2() + 400 - Cannonball.getRadius(), initialSpeed, tank. getCannonAngle(), CANVAS_WIDTH, CANVAS_HEIGHT);
    //     ball.addToCanvas(canvas);
    //     if (key.getKey().equals(Key.valueOf("SPACE"))) {
    //         System.out.println(ball.getCenterX());
    //         while (ball.updatePosition(0.1)) {
    //             canvas.draw();
    //         }   
    //     }
    //     tank.switchWorking();
    //     ball.removeFromCanvas(canvas);
    // }
    
    
    // We will bring back the run method later. We just don't need it at this time.

    public static void main(String[] args) {
        TankGame game = new TankGame();             
    }
}
