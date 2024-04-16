package game;

import city.cs.engine.DynamicBody;
import city.cs.engine.StaticBody;

import javax.swing.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Initialise Save Game
 * <p>Saves the current state of the game into a text file</p>
 */
public class saveGame {
    private final GameWorld GameChapter;
    //GameWorld
    private final GameView view;
    //GameView
    private String PortalList;
    private String AntiGravityList;
    private String ZombieList;
    private String GuardianList;
    private String QuiverList;
    private String PlatformList;
    //String Lists
    private int Seconds;
    //Countdown Seconds

    /**
     * Constructor Initialise Save Game
     * <p>Option Pane Displayed To Save Game/p>
     */
    public saveGame(JFrame frame, Game game, GameView view) throws IOException {
        this.GameChapter = game.getGameLevel();
        this.view = view;
        GameChapter.stop();
        view.getTimer1().stop();
        var SaveGame = JOptionPane.showConfirmDialog(null, "Would You Like To Save Game");
        if (SaveGame == 0) {
            JOptionPane.showMessageDialog(null, "Game Saved");
            saveFile();
            GameChapter.start();
            view.getTimer1().start();
        } else if (SaveGame == 1) {
            GameChapter.start();
            view.getTimer1().start();
        } else if (SaveGame == 2){
            GameChapter.start();
            view.getTimer1().start();
        }
    }

    /**
     * Empties Save File
     * <p>Opens Current Save And Empties The File</p>
     */
    public void clearFile() {
        try {
            FileWriter fw = new FileWriter("SavedGame", false);
            PrintWriter pw = new PrintWriter(fw, false);
            pw.flush();
            pw.close();
            fw.close();
        } catch (Exception exception) {
            System.out.println("Exception have been caught");
        }
    }

    /**
     * Stores state of bodies
     * <p>Saves state of each static and dynamic bodies into the string list</p>
     */
    public void saveBodies(){
        List<DynamicBody> DynamicBodies = GameChapter.getDynamicBodies();
        List<StaticBody> StaticBodies = GameChapter.getStaticBodies();

        List<Float> Guardian = new ArrayList<Float>();
        List<Float> Zombie = new ArrayList<Float>();
        List<Float> Portal = new ArrayList<Float>();
        List<Float> AntiGravity  = new ArrayList<Float>();
        List<Float> Quiver  = new ArrayList<Float>();
        List<Float> Platforms  = new ArrayList<Float>();


        for (DynamicBody dynamicBody : DynamicBodies) {
            if (dynamicBody instanceof Zombie) {
                Zombie.add(((Zombie) dynamicBody).getPlatformPosition().x);
                Zombie.add(((Zombie) dynamicBody).getPlatformPosition().y);
            } else if (dynamicBody instanceof Guardian) {
                Guardian.add(((Guardian) dynamicBody).getPlatformPosition().x);
                Guardian.add(((Guardian) dynamicBody).getPlatformPosition().y);
            }
        }
        for (StaticBody staticBody : StaticBodies) {
            switch (staticBody) {
                case Portal portal -> {
                    Portal.add(staticBody.getPosition().x);
                    Portal.add(staticBody.getPosition().y);
                }
                case AntiGravity antiGravity -> {
                    AntiGravity.add(staticBody.getPosition().x);
                    AntiGravity.add(staticBody.getPosition().y - 2);
                }
                case Quiver quiver -> {
                    Quiver.add(staticBody.getPosition().x);
                    Quiver.add(staticBody.getPosition().y);
                }
                case null, default -> {
                    assert staticBody != null;
                    Platforms.add(staticBody.getPosition().x);
                    Platforms.add(staticBody.getPosition().y);
                }
            }
        }

        //remove the right bracket
        //remove the left bracket
        Seconds = view.getSeconds();

        ZombieList = Zombie.toString()
                .replace("[", "")  //remove the right bracket
                .replace("]", "")  //remove the left bracket
                .trim();           //remove trailing spaces from partially initialized arrays
        GuardianList = Guardian.toString()
                .replace("[", "")  //remove the right bracket
                .replace("]", "")  //remove the left bracket
                .trim();
        PortalList = Portal.toString()
                .replace("[", "")  //remove the right bracket
                .replace("]", "")  //remove the left bracket
                .trim();           //remove trailing spaces from partially initialized arrays
        AntiGravityList = AntiGravity.toString()
                .replace("[", "")  //remove the right bracket
                .replace("]", "")  //remove the left bracket
                .trim();           //remove trailing spaces from partially initialized arrays
        QuiverList = Quiver.toString()
                .replace("[", "")  //remove the right bracket
                .replace("]", "")  //remove the left bracket
                .trim();           //remove trailing spaces from partially initialized arrays
        PlatformList = Platforms.toString()
                .replace("[", "")  //remove the right bracket
                .replace("]", "")  //remove the left bracket
                .trim();           //remove trailing spaces from partially initialized arrays
    }

    /**
     * Saves state of string lists
     * <p>Saves the string lists into the saved file line by line</p>
     */
    public void saveFile() throws IOException {
        saveBodies();
        clearFile();
        boolean append = false;
        try (FileWriter writer = new FileWriter("SavedGame", append)) {
            writer.write("Chapter:" + "," + view.getChapter() + "\n");
            writer.write("Time Left" + "," + Seconds + "\n");
            writer.write("Character xPosition" + "," + GameChapter.getCharacter().getPosition().x + "\n");
            writer.write("Character yPosition" + "," + GameChapter.getCharacter().getPosition().y + "\n");
            writer.write("Character Health" + "," + GameChapter.getCharacter().getHealthCount() + "\n");
            writer.write("Character Lives" + "," + GameChapter.getCharacter().getLives() + "\n");
            writer.write("Character Arrows" + "," + GameChapter.getCharacter().getArrowCount() + ",\n");
            writer.write("Character Score" + "," + GameChapter.getCharacter().getScoreCount() + "\n");
            writer.write("EnemyList Bodies" + "," + ZombieList + "\n");
            writer.write("GuardianList Bodies" + "," + GuardianList + "\n");
            writer.write("PortalList Bodies" + "," +  PortalList + "\n");
            writer.write("AntiGravityList Bodies" + "," +  AntiGravityList + "\n");
            writer.write("QuiverList Bodies" + "," +  QuiverList + "\n");
            writer.write("PlatformList Bodies" + "," +  PlatformList + "\n");
        }
    }
}
