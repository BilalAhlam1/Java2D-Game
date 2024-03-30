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

    /**
     * Initialise a new Game.
     */
    public Game(int Chapter) throws LineUnavailableException, IOException, UnsupportedAudioFileException {

        //Initialise Chapter
        if (Chapter == 1) {
            GameLevel = new Chapter1(this);

            //Make a view
            view = new GameView(GameLevel, Chapter, 800, 500);
        } else if (Chapter == 2) {
            GameLevel = new Chapter2(this,0, 0, 100, 3);

            //Make a view
            view = new GameView(GameLevel, Chapter, 800, 500);
        }

        //start the world and set gravity
        GameLevel.setGravity(20);
        GameLevel.start();
    }

    public void goToNextChapter(int Arrows, int Score, int Health, int Lives){
        if (GameLevel instanceof Chapter1){
            GameLevel.stop();
            GameLevel = new Chapter2(this, Arrows, Score, Health, Lives);

            view.setChapter(2);
            updateWorld();

            //start the world
            GameLevel.start();
        }
    }

    public void updateWorld(){
        view.setWorld(GameLevel);
        view.getCamera().updateCharacter(GameLevel.getCharacter());
        GameLevel.addStepListener(view.getCamera());
        view.getMovement().updateCharacter(GameLevel.getCharacter());
        view.getShooting().setWorld(GameLevel);
        view.getShooting().setView(view);
        view.getShooting().updateCharacter(GameLevel.getCharacter());
        view.updateCharacter(GameLevel.getCharacter());

        //set gravity
        GameLevel.setGravity(20);
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


