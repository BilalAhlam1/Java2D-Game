package game;

import city.cs.engine.DynamicBody;
import city.cs.engine.StaticBody;
import org.jbox2d.common.Vec2;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class loadGame {
    private final ArrayList<Float> ZombieList = new ArrayList<Float>();
    private final ArrayList<Float> GuardianList = new ArrayList<Float>();
    private final ArrayList<Float> PortalList = new ArrayList<Float>();
    private final ArrayList<Float> AntiGravityList = new ArrayList<Float>();

    public loadGame() throws IOException {
        FileReader fr = null;
        BufferedReader reader = null;
        try {
            System.out.println("Reading " + "SavedGame" + " ...");
            fr = new FileReader("SavedGame");
            reader = new BufferedReader(fr);
            String line = reader.readLine();
            //while (line != null) {
                // file is assumed to contain one name, score pair per line
                String[] tokens1 = line.split(",");
                int Chapter = Integer.parseInt((tokens1[1]));
                line = reader.readLine();

                String[] tokens2 = line.split(",");
                float characterXPosition = Float.parseFloat(tokens2[1]);
                line = reader.readLine();

                String[] tokens3 = line.split(",");
                float characterYPosition = Float.parseFloat(tokens3[1]);
                line = reader.readLine();

                String[] tokens4 = line.split(",");
                int Health = Integer.parseInt(tokens4[1]);
                line = reader.readLine();

                String[] tokens5 = line.split(",");
                int Lives = Integer.parseInt(tokens5[1]);
                line = reader.readLine();

                String[] tokens6 = line.split(",");
                int Arrows = Integer.parseInt(tokens6[1]);
                line = reader.readLine();

                String[] tokens7 = line.split(",");
                int Score = Integer.parseInt(tokens7[1]);
                line = reader.readLine();

                //ENEMIES
                String[] tokens8 = line.split(",");
                for (int i = 1; i < tokens8.length; i++) {
                    ZombieList.add(i - 1, Float.valueOf(tokens8[i]));
                }
                line = reader.readLine();

                String[] tokens9 = line.split(",");
                for (int i = 1; i < tokens9.length; i++) {
                    GuardianList.add(i - 1, Float.valueOf(tokens9[i]));
                }
                line = reader.readLine();

                //PORTALS
                String[] tokens10 = line.split(",");
                for (int i = 0; i < tokens10.length - 1; i++) {
                    PortalList.add(i, Float.valueOf(tokens10[i + 1]));
                }
                line = reader.readLine();

                //ANTIGRAVITY
                String[] tokens11 = line.split(",");
                for (int i = 0; i < tokens11.length - 1; i++) {
                    AntiGravityList.add(i, Float.valueOf(tokens11[i + 1]));
                }
                line = reader.readLine();


                System.out.println("Chapter: " + Chapter + "\nCharacterPositionX: " + characterXPosition +
                        "\nHealth = " + Health + "\nLives = " + Lives + "\nArrows = " + Arrows + "\nScore = " + Score +
                        "\nEnemyList = " + ZombieList + "\nGuardianList" + GuardianList + "\nPortalList = " + PortalList);

                loadChapter(Chapter, characterXPosition, characterYPosition, Health, Lives, Arrows, Score);
            //}
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

    public void setDynamicBodies(Game game){
        List<DynamicBody> DynamicBodies = game.getGameLevel().getDynamicBodies();
        for (DynamicBody dynamicBody : DynamicBodies) {
            if (dynamicBody instanceof Enemy) {
                dynamicBody.destroy();
            }
        }
        for (int i = 1; i < ZombieList.size(); i++) {
            Enemy enemy = new Zombie(game.getGameLevel(), new Vec2(ZombieList.get(i-1), ZombieList.get(i)), game.getGameLevel().getCharacter());
            EnemyDamage enemyDamage = new EnemyDamage(enemy, game.getGameLevel().getCharacter());
            enemy.addCollisionListener(enemyDamage);
            i++;
        }
        for (int i = 1; i < GuardianList.size(); i++) {
            Enemy enemy = new Guardian(game.getGameLevel(), new Vec2(GuardianList.get(i-1), GuardianList.get(i)), game.getGameLevel().getCharacter());
            EnemyDamage enemyDamage = new EnemyDamage(enemy, game.getGameLevel().getCharacter());
            enemy.addCollisionListener(enemyDamage);
            i++;
        }
    }

    public void setStaticBodies(Game game, int Chapter){
        List<StaticBody> StaticBodies = game.getGameLevel().getStaticBodies();
        for (StaticBody staticBody : StaticBodies) {
            if (staticBody instanceof Portal) {
                staticBody.destroy();
            } else if (staticBody instanceof AntiGravity) {
                staticBody.destroy();
            } else if (staticBody instanceof Quiver) {
                staticBody.destroy();
            }
        }

        //SET ANTIGRAVITY
        for (int i = 1; i < AntiGravityList.size(); i++) {
            AntiGravity antiGravity = new AntiGravity(game.getGameLevel(), new Vec2(AntiGravityList.get(i-1), AntiGravityList.get(i)), game.getGameLevel().getCharacter());
            i++;
        }

        //SET PORTAL
        if (Chapter == 3){
            Portal portal = new Portal(game.getGameLevel(),new Vec2(game.getGameLevel().getCharacter().getPosition().x, game.getGameLevel().getCharacter().getPosition().y));
        } else {
            for (int i = 1; i < PortalList.size(); i++) {
                Portal portal = new Portal(game.getGameLevel(), new Vec2(PortalList.get(i - 1), PortalList.get(i)));
                i++;
            }
        }
    }
    public void loadChapter(int Chapter, float CharacterXPosition, float CharacterYPosition, int Health, int Lives, int Arrows, int Score) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        Game game = new Game(Chapter, Arrows, Score, Health, Lives);
        game.getGameLevel().getCharacter().setPosition(new Vec2(CharacterXPosition, CharacterYPosition));
        game.getGameLevel().getCharacter().setSpawn();

        setDynamicBodies(game);
        setStaticBodies(game, Chapter);
    }
}
