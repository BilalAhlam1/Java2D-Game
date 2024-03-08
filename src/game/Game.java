package game;

import javax.sound.sampled.LineUnavailableException;

import java.awt.*;
import java.io.IOException;

/**
 * Your main game entry point
 */
public class Game{

    private static GameWorld gameWorld;

    /** Initialise a new Game. */
    public Game() throws LineUnavailableException, IOException {

        //1. make an empty game world
        //2. populate it with bodies (ex: platforms, collectibles, characters)
        gameWorld = new GameWorld();

        //3. make a view to look into the game world
        GameView view = new GameView(gameWorld, 800, 500);

        //start the world and set gravity
        gameWorld.setGravity(20);
        gameWorld.getSimulationSettings().setTargetFrameRate(60);
        gameWorld.start();

    }

    public static GameWorld getGameWorld() {
        return gameWorld;
    }

    /** Run the game. */
    public static void main(String[] args){
        //Game game = new Game();
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new Game();
                } catch (LineUnavailableException | IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

    }
}


