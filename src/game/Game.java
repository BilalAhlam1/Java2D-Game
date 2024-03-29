package game;

import city.cs.engine.SoundClip;
import javax.sound.sampled.*;
import java.io.IOException;

/**
 * Your main game entry point
 */
public class Game {
    static SoundClip BackGroundMusic;

    static {
        try {
            BackGroundMusic = new SoundClip("data/Sounds/Background Music.wav");
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            throw new RuntimeException(e);
        }
    }

    private static GameWorld GameLevel;
    private static GameView view;
    private static Movement movement;

    /**
     * Initialise a new Game.
     */
    public Game(int Chapter) throws LineUnavailableException, IOException, UnsupportedAudioFileException {

        //Initialise Chapter
        if (Chapter == 1) {
            GameLevel = new Chapter1(this);
        } else if (Chapter == 2) {
            GameLevel = new Chapter1(this);
        }

        //Make a view
        view = new GameView(GameLevel, 800, 500);

        //movement = new Movement(GameLevel.getCharacter());
        //view.addKeyListener(movement);

        //start the world and set gravity
        GameLevel.setGravity(20);
        GameLevel.start();
    }

    public void goToNextChapter(){
        if (GameLevel instanceof Chapter1){
            GameLevel.stop();
            GameLevel = new Chapter2(this);

            view.setWorld(GameLevel);
            view.getMovement().updateCharacter(GameLevel.getCharacter());

            //start the world and set gravity
            GameLevel.setGravity(20);
            GameLevel.start();
        }
    }

    public static void mainMenu(){
        Menu menu = new Menu();
        BackGroundMusic.play();
        BackGroundMusic.loop();
    }

    /** Run the game. */
    public static void main(String[] args) throws LineUnavailableException, IOException {
        mainMenu();
    }
}


