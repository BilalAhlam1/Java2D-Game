package game;

import city.cs.engine.SoundClip;
import javax.sound.sampled.*;
import java.io.IOException;

/**
 * Your main game entry point
 */
public class Game {
    private static GameWorld GameLevel;
    private static GameView view;

    /**
     * Initialise a new Game.
     */
    public Game(int Chapter, int Arrows, int Score, int Health, int Lives) throws LineUnavailableException, IOException, UnsupportedAudioFileException {

        //Initialise Chapter
        if (Chapter == 1) {
            GameLevel = new Chapter1(this, Arrows,  Score,  Health,  Lives);
        } else if (Chapter == 2) {
            GameLevel = new Chapter2(this, Arrows,  Score,  Health,  Lives);
        } else if (Chapter == 3) {
            GameLevel = new Chapter3(this, Arrows,  Score,  Health,  Lives);
        }

        //Make a view
        view = new GameView(this, GameLevel, Chapter, 800, 500);

        //start the world and set gravity
        GameLevel.setGravity(20);
        GameLevel.start();
    }

    public GameWorld getGameLevel() {
        return GameLevel;
    }

    public void goToNextChapter(int Arrows, int Score, int Health, int Lives){
        if (GameLevel instanceof Chapter1){
            GameLevel.stop();
            GameLevel = new Chapter2(this, Arrows, Score, Health, Lives);

            view.setSeconds(45);
            view.setChapter(2);
            updateWorld();

            //start the world
            GameLevel.start();
        } else if (GameLevel instanceof Chapter2){
            GameLevel.stop();
            GameLevel = new Chapter3(this, Arrows, Score, Health, Lives);

            view.setSeconds(120);
            view.setChapter(3);
            updateWorld();

            //start the world
            GameLevel.start();
        } else if (GameLevel instanceof Chapter3){
            ((Chapter3) GameLevel).makePlatforms();
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

        //GameLevel.removeStaticBodies();
        //set gravity
        GameLevel.setGravity(20);
    }

    public GameView getView() {
        return view;
    }

    public static void mainMenu(){
        new Menu();
    }

    /** Run the game. */
    public static void main(String[] args) throws LineUnavailableException, IOException {
        mainMenu();
    }
}


