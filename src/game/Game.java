package game;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

/**
 * Your main game entry point
 */
public class Game {
    private static final AudioInputStream BackgroundMusic;
    static {
        try {
            BackgroundMusic = AudioSystem.getAudioInputStream(new File("data/Sounds/Background Music.wav"));
        } catch (UnsupportedAudioFileException | IOException e) {
            throw new RuntimeException(e);
        }
    }
    private static final Clip clip;
    static {
        try {
            clip = AudioSystem.getClip();
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        }
    }

    private static GameWorld GameLevel;

    /**
     * Initialise a new Game.
     */
    public Game() throws LineUnavailableException, IOException {
        //1. make an empty game world
        //2. populate it with bodies (ex: platforms, collectibles, characters)
        GameLevel = new Chapter1();

        //3. make a view to look into the game world
        GameView view = new GameView(GameLevel, 800, 500);

        //start the world and set gravity
        GameLevel.setGravity(20);
        GameLevel.start();
    }

    public static GameWorld getGameWorld() {
        return GameLevel;
    }

    /** Run the game. */
    public static void main(String[] args) throws LineUnavailableException, IOException {
        try {
            clip.open(BackgroundMusic);
        } catch (LineUnavailableException | IOException ex) {
            throw new RuntimeException(ex);
        }
        clip.start();

        clip.loop(clip.LOOP_CONTINUOUSLY);

        Menu menu = new Menu();
    }
}


