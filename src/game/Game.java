package game;

import city.cs.engine.SoundClip;
import javax.sound.sampled.*;
import java.io.IOException;

/**
 * @author bilal, ahlam, bilal.ahlam@city.ac.uk
 * Main game entry point
 */
public class Game {
    private static GameWorld GameLevel;
    //GameWorld Class
    private static GameView view;
    //GameView Class

    /**
     * Initialise a new Game.
     * @param Chapter Chapter To Launch
     * @param Arrows Arrows Count
     * @param Score Score Count
     * @param Health Health Count
     * @param Lives Lives Count
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

    /**
     * Getter to return GameWorld
     * @return GameWorld Class
     */
    public GameWorld getGameLevel() {
        return GameLevel;
    }

    /**
     * Changes Current State To The Next GameWorld
     * @param Arrows Arrows Count
     * @param Score Score Count
     * @param Health Health Count
     * @param Lives Lives Count
     */
    public void goToNextChapter(int Arrows, int Score, int Health, int Lives){
        if (GameLevel instanceof Chapter1){
            GameLevel.stop();
            GameLevel = new Chapter2(this, Arrows, Score, Health, Lives);

            //Sets Timer And Chapter
            view.setSeconds(60);
            view.setChapter(2);
            updateWorld();

            //start the world
            GameLevel.start();
        } else if (GameLevel instanceof Chapter2){
            GameLevel.stop();
            GameLevel = new Chapter3(this, Arrows, Score, Health, Lives);

            //Sets Timer And Chapter
            view.setSeconds(120);
            view.setChapter(3);
            updateWorld();

            //start the world
            GameLevel.start();
        } else if (GameLevel instanceof Chapter3){
            //Make Platforms
            ((Chapter3) GameLevel).makePlatforms();
        }
    }

    /**
     * Updates Classes To The New GameWorld
     * <p>Set View World To The New GameWorld. Update Camera, Step Listener, Shooting and Movement Class With The New Character</p>
     */
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

    /**
     * Getter For The UserView
     * @return UserView
     */
    public GameView getView() {
        return view;
    }

    /**
     * Initialises Main Menu
     */
    public static void mainMenu(){
        new Menu();
    }

    /** Run the Main Menu. */
    public static void main(String[] args) throws LineUnavailableException, IOException {
        mainMenu();
    }
}


