# Tanks! by Bram Nutt, Eddie Chen, Marcus Monk Wallace

&emsp;&emsp;Originally, we had intended our game to involve 2 tanks moving across some randomly generated terrain and shooting over an obstacle, which we at the time determined to be a large tower. We managed to add some extra functionality such as having the tank angle itself as it travels along the generated terrain and in place, we added an opening and ending screen for the game, and we changed the color of the cannonball to be the color of whatever tank shot the cannonball. In the end, we decided not to have a large tower to block shots. Instead, we have decided to restrict the movement of the tanks beyond a certain point so that players who are looking for a slugfest can go at it in the middle of the terrain. Another thing that we decided not to do was randomize the background. Instead of randomizing the background, we decided to keep with one background picture because we couldn’t find another background that was to the quality that we wanted. Another thing that we weren’t able to implement was terrain deformation because we didn’t have enough time to figure it out. The last thing that we weren’t able to implement was adding more than 2 functioning tanks onto the screen.
  
&emsp;&emsp;To run our game, first run the main method inside the “TankGame” class. A window will pop up. Click on the “Start Game!” button. Two tanks will be displayed on the screen. The first tank to shoot will be the blue tank. To shoot, hold down the spacebar to increase the force at which the cannonball is shot out of the tank. Once the spacebar is let go of, the cannonball will be shot out of the tank. To angle the cannon of the tanks, use the UP and DOWN arrow keys or the “W” and “S” keys. If a cannonball hits a tank, that tank will lose 1 life out of the 5 that they originally start out with, even if it was the tank that shot the cannonball. To move the tank, use the LEFT and RIGHT arrows or use the “A” and “S” keys. After one tank shoots, the opposing tank shoots. This gameplay repeats until one tank has lost all its lives. Then an ending screen appears. The players will have 2 options: 1.) Exit the game 2.) Reset and play the game again.
  
&emsp;&emsp;There were many challenging aspects to this project, but we found the most challenging to be the methods of our code that utilized math. For instance, calculating the radius of the circle that the cannon needed to rotate around was very challenging as well as finding where to shoot the ball from the cannon. The most challenging portion of the project involving math was using the side-side-side theorem to calculate how the tank angles itself when going up or downhill. It was also difficult to figure out the logic to generate the terrain. The decision was made to shift from a Line object for terrain to a Path object to ensure that we could generate varied terrain options on command. The trick was thinking through the logic required to generate the terrain in a way that wouldn’t hinder player enjoyment (like very jagged terrain that would occur if it were not for code that forced the generation of terrain to stop for a period of time after generating a hill).
    
&emsp;&emsp;We drew some inspiration from the GamePigeon tank game and from “Worms: Armageddon”. We particularly drew on the idea of angling the tank's cannon and setting the force of the cannon from GamePigeon’s Tank game. Then we also added in tank movement from “Worms: Armageddon.” Some concepts that we utilized were lambda expressions, constants, keyboard events, array lists, class decomposition, and method decomposition.
