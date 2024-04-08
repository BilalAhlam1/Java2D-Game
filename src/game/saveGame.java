package game;

import javax.swing.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

public class saveGame {
    private final GameWorld GameChapter;

    public saveGame(JFrame frame, GameWorld GameChapter) throws IOException {
        this.GameChapter = GameChapter;
        GameChapter.stop();
        var SaveGame = JOptionPane.showConfirmDialog(null, "Would You Like To Save Game");
        if (SaveGame == 0) {
            JOptionPane.showMessageDialog(null, "Game Saved");
            saveFile();
            GameChapter.start();
        } else if (SaveGame == 1) {
            frame.dispose();
            new Menu();
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

        public void saveFile() throws IOException {
        clearFile();
        boolean append = false;
        try (FileWriter writer = new FileWriter("SavedGame", append)) {
            writer.write("Chapter:" + "," + GameChapter.getChapterName() + ",");
            writer.write("Character xPosition" + "," + GameChapter.getCharacter().getPosition().x + ",");
            writer.write("Character yPosition" + "," + GameChapter.getCharacter().getPosition().y + ",");
            writer.write("Character Health" + "," + GameChapter.getCharacter().getHealthCount() + ",");
            writer.write("Character Lives" + "," + GameChapter.getCharacter().getLives() + ",");
            writer.write("Character Arrows" + "," + GameChapter.getCharacter().getArrowCount() + ",");
            writer.write("Character Score" + "," + GameChapter.getCharacter().getScoreCount() + ",");
        }
    }
}
