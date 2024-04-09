package game;

import city.cs.engine.DynamicBody;
import city.cs.engine.StaticBody;

import javax.swing.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class saveGame {
    private final GameWorld GameChapter;
    private String PortalList;
    private String AntiGravityList;
    private String ZombieList;
    private String GuardianList;

    public saveGame(JFrame frame, GameWorld GameChapter) throws IOException {
        this.GameChapter = GameChapter;
        GameChapter.stop();
        var SaveGame = JOptionPane.showConfirmDialog(null, "Would You Like To Save Game");
        if (SaveGame == 0) {
            JOptionPane.showMessageDialog(null, "Game Saved");
            saveBodies();
            saveFile();
            GameChapter.start();
        } else if (SaveGame == 1) {
            frame.dispose();
            new Menu();
        } else if (SaveGame == 2){
            GameChapter.start();
        }
    }
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

    public void saveBodies(){
        List<DynamicBody> DynamicBodies = GameChapter.getDynamicBodies();
        List<StaticBody> StaticBodies = GameChapter.getStaticBodies();

        List<Float> Guardian = new ArrayList<Float>();
        List<Float> Zombie = new ArrayList<Float>();
        List<Float> Portal = new ArrayList<Float>();
        List<Float> AntiGravity  = new ArrayList<Float>();


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
            if (staticBody instanceof Portal) {
                Portal.add(staticBody.getPosition().x);
                Portal.add(staticBody.getPosition().y);
            } else if (staticBody instanceof AntiGravity) {
                AntiGravity.add(staticBody.getPosition().x);
                AntiGravity.add(staticBody.getPosition().y);
            }
        }

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
    }

        public void saveFile() throws IOException {
        clearFile();
        boolean append = false;
        try (FileWriter writer = new FileWriter("SavedGame", append)) {
            writer.write("Chapter:" + "," + GameChapter.getChapterName() + "\n");
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
        }
    }
}
