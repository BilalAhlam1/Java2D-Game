package game;

import org.jbox2d.common.Vec2;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.*;

public class loadGame {
    public loadGame() throws IOException {
        FileReader fr = null;
        BufferedReader reader = null;
        try {
            System.out.println("Reading " + "SavedGame" + " ...");
            fr = new FileReader("SavedGame");
            reader = new BufferedReader(fr);
            String line = reader.readLine();
            while (line != null) {
                // file is assumed to contain one name, score pair per line
                String[] tokens = line.split(",");
                int Chapter = Integer.parseInt((tokens[1]));
                float characterXPosition = Float.parseFloat(tokens[3]);
                float characterYPosition = Float.parseFloat(tokens[5]);
                int Health = Integer.parseInt(tokens[7]);
                int Lives = Integer.parseInt(tokens[9]);
                int Arrows = Integer.parseInt(tokens[11]);
                int Score = Integer.parseInt(tokens[13]);
                System.out.println("Chapter: " + Chapter + ", CharacterPositionX: " + characterXPosition +
                        "Health = " + Health + "Lives = " + Lives + "Arrows = " + Arrows + "Score = " + Score);
                line = reader.readLine();
                loadChapter(Chapter, characterXPosition, characterYPosition, Health, Lives, Arrows, Score);
            }
            System.out.println("...done.");
        } catch (IOException | UnsupportedAudioFileException | LineUnavailableException e) {
            throw new RuntimeException(e);
        } finally {
            if (reader != null) {
                reader.close();
            }
            if (fr != null) {
                fr.close();
            }
        }
    }
    public void loadChapter(int Chapter, float CharacterXPosition, float CharacterYPosition, int Health, int Lives, int Arrows, int Score) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        Game game = new Game(Chapter, Arrows, Score, Health, Lives);
        game.getGameLevel().getCharacter().setPosition(new Vec2(CharacterXPosition, CharacterYPosition));
        game.getGameLevel().getCharacter().setSpawn();
    }
}
