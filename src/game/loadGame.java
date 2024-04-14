package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Initialises loading procedure
 */
public class loadGame {
    private final ArrayList<Float> ZombieList = new ArrayList<Float>();
    private final ArrayList<Float> GuardianList = new ArrayList<Float>();
    private final ArrayList<Float> PortalList = new ArrayList<Float>();
    private final ArrayList<Float> AntiGravityList = new ArrayList<Float>();
    private final ArrayList<Float> QuiverList = new ArrayList<Float>();
    private final ArrayList<Float> PlatformList = new ArrayList<Float>();
    //ArrayLists

    private static final BodyImage Cloud = new BodyImage("data/Blue Cloud.png", 7f);
    //Chapter 3 Platform

    /**
     * Constructor Initialises load game
     * <p>Reads Text File and stores each line into tokens that are extracted into arrays of each object, platforms are only stored for chapter 3</p>
     */
    public loadGame() throws IOException {
        try (FileReader fr = new FileReader("SavedGame"); BufferedReader reader = new BufferedReader(fr)) {
            String line = reader.readLine();

            // extract objects
            String[] tokens1 = line.split(",");
            int Chapter = Integer.parseInt((tokens1[1]));
            line = reader.readLine();

            String[] tokens2 = line.split(",");
            float timeLeft = Float.parseFloat(tokens2[1]);
            line = reader.readLine();


            String[] tokens3 = line.split(",");
            float characterXPosition = Float.parseFloat(tokens3[1]);
            line = reader.readLine();

            String[] tokens4 = line.split(",");
            float characterYPosition = Float.parseFloat(tokens4[1]);
            line = reader.readLine();

            String[] tokens5 = line.split(",");
            int Health = Integer.parseInt(tokens5[1]);
            line = reader.readLine();

            String[] tokens6 = line.split(",");
            int Lives = Integer.parseInt(tokens6[1]);
            line = reader.readLine();

            String[] tokens7 = line.split(",");
            int Arrows = Integer.parseInt(tokens7[1]);
            line = reader.readLine();

            String[] tokens8 = line.split(",");
            int Score = Integer.parseInt(tokens8[1]);
            line = reader.readLine();

            //ENEMIES
            String[] tokens9 = line.split(",");
            for (int i = 1; i < tokens9.length; i++) {
                ZombieList.add(i - 1, Float.valueOf(tokens9[i]));
            }
            line = reader.readLine();

            String[] tokens10 = line.split(",");
            for (int i = 1; i < tokens10.length; i++) {
                GuardianList.add(i - 1, Float.valueOf(tokens10[i]));
            }
            line = reader.readLine();

            //PORTALS
            String[] tokens11 = line.split(",");
            for (int i = 0; i < tokens11.length - 1; i++) {
                PortalList.add(i, Float.valueOf(tokens11[i + 1]));
            }
            line = reader.readLine();

            //ANTIGRAVITY
            String[] tokens12 = line.split(",");
            for (int i = 0; i < tokens12.length - 1; i++) {
                AntiGravityList.add(i, Float.valueOf(tokens12[i + 1]));
            }
            line = reader.readLine();

            //QUIVER
            String[] tokens13 = line.split(",");
            for (int i = 0; i < tokens13.length - 1; i++) {
                QuiverList.add(i, Float.valueOf(tokens13[i + 1]));
            }
            line = reader.readLine();

            //Load platforms if loading chapter 3
            if (Chapter == 3) {
                String[] tokens14 = line.split(",");
                for (int i = 0; i < tokens14.length - 5; i++) {
                    PlatformList.add(i, Float.valueOf(tokens14[i + 1]));
                }
                line = reader.readLine();
            }

            //Call and set chapter
            loadChapter(Chapter, (int) timeLeft, characterXPosition, characterYPosition, Health, Lives, Arrows, Score);

        } catch (IOException | UnsupportedAudioFileException | LineUnavailableException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Loads Dynamic Bodies
     * <p>Destroys all current enemies loaded by default, and loads enemies from the text files positions</p>
     * @param game Game Where Bodies Are Loaded
     */
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

    /**
     * Loads Static Bodies
     * <p>Destroys all current static bodies loaded by default - portals, AntiGravity, and loads all static bodies from the text files positions.
     * If the current chapter is chapter 3, platforms and quivers are removed and loaded from the text file</p>
     * @param game Game Where Bodies Are Loaded
     */
    public void setStaticBodies(Game game, int Chapter) {
        List<StaticBody> StaticBodies = game.getGameLevel().getStaticBodies();
        for (StaticBody staticBody : StaticBodies) {
            if (staticBody instanceof Portal) {
                staticBody.destroy();
            } else if (staticBody instanceof AntiGravity) {
                staticBody.destroy();
            } else if (staticBody instanceof Quiver) {
                staticBody.destroy();
            } else if (staticBody != null && Chapter == 3) {
                staticBody.destroy();
            }
        }

        //SET ANTIGRAVITY
        for (int i = 1; i < AntiGravityList.size(); i++) {
            AntiGravity antiGravity = new AntiGravity(game.getGameLevel(), new Vec2(AntiGravityList.get(i - 1), AntiGravityList.get(i) + 2));
            i++;
        }

        //SET PORTAL
        for (int i = 1; i < PortalList.size(); i++) {
            Portal portal = new Portal(game.getGameLevel(), new Vec2(PortalList.get(i - 1), PortalList.get(i)));
            i++;
        }

        if (Chapter == 3) {
            //SET QUIVER
            for (int i = 1; i < QuiverList.size(); i++) {
                Quiver quiver = new Quiver(game.getGameLevel(), new Vec2(QuiverList.get(i - 1), QuiverList.get(i)));
                i++;
            }
            for (int i = 1; i < PlatformList.size(); i++) {
                Shape groundShape = new BoxShape(6, 0.5f);
                StaticBody ground = new StaticBody(game.getGameLevel(), groundShape);
                SolidFixture platformFixture = new SolidFixture(ground, groundShape);
                ground.setPosition(new Vec2(PlatformList.get(i - 1), PlatformList.get(i)));
                ground.addImage(Cloud);
                i++;
            }
            game.getGameLevel().setMaxPlatformPosition(PlatformList.get(1));
        }
    }

    /**
     * Loads a new game based on the values of the objects rom the text file
     * <p>Creates a new game, sets the statistics, sets the character position spawn and chapter countdown timer. Sets Dynamic and Static Bodies
     * throughout the gameWorld</p>
     * @param Chapter Chapter to be initialised
     * @param timeLeft Countdown Timer to initialise
     * @param CharacterXPosition Character x position
     * @param CharacterYPosition Character y position
     * @param Health Health Count
     * @param Lives Lives Count
     * @param Arrows Arrows Count
     * @param Score Score Count
     */
    public void loadChapter(int Chapter,int timeLeft, float CharacterXPosition, float CharacterYPosition, int Health, int Lives, int Arrows, int Score) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        //Set game
        Game game = new Game(Chapter, Arrows, Score, Health, Lives);
        game.getGameLevel().getCharacter().setPosition(new Vec2(CharacterXPosition, CharacterYPosition));
        game.getGameLevel().getCharacter().setSpawn();
        game.getView().setSeconds(timeLeft);

        //Set Static and Dynamic Bodies
        setDynamicBodies(game);
        setStaticBodies(game, Chapter);
    }
}
