package game;

import javax.sound.sampled.LineUnavailableException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Your main game entry point
 */
public class Game {

    private static GameWorld gameWorld;

    /**
     * Initialise a new Game.
     */
    public Game() throws LineUnavailableException, IOException {
        //1. make an empty game world
        //2. populate it with bodies (ex: platforms, collectibles, characters)
        gameWorld = new GameWorld();

        //3. make a view to look into the game world
        GameView view = new GameView(gameWorld, 800, 500);

        //start the world and set gravity
        gameWorld.setGravity(20);
        gameWorld.start();
    }

    public static void MainMenu() throws LineUnavailableException, IOException {
        Menu menu = new Menu();
        menu.Play.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new Game();
                } catch (LineUnavailableException | IOException ex) {
                    throw new RuntimeException(ex);
                }
                menu.dispose();
            }
        });

        menu.Help.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menu.setHelp();
            }
        });

        menu.Back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menu.setMenu();
            }
        });

        menu.Quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    public static GameWorld getGameWorld() {
        return gameWorld;
    }

    /** Run the game. */
    public static void main(String[] args) throws LineUnavailableException, IOException {
        MainMenu();
    }
}


