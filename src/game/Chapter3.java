package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

/**
 * Provides platforms, enemies and pickup objects for the third chapter in the world
 */
public class Chapter3 extends GameWorld{
    private final float[] uniqueNumbers = new float[10];
    //Array of unique X-Coordinate Values
    private double Difficulty = 0;
    //Difficulty value
    private static final BodyImage Cloud = new BodyImage("data/Blue Cloud.png", 7f);
    //Cloud Platform
    private static final BodyImage purpleCloud = new BodyImage("data/Purple Cloud.png", 7f);
    //Bouncy Platform
    private static final BodyImage HowHighCanYouFlyMessage = new BodyImage("data/GameMessages/HowHighCanYouFlyMessage.png", 2f);
    //Message

    /**
     * Constructor Loads/generates more complex platforms, alternating positions, more enemies and power ups through auto generation
     * @param game Game Class
     * @param Arrows Arrows Count
     * @param Score Score Count
     * @param Health Health Count
     * @param Lives Lives Count
     */
    public Chapter3(Game game, int Arrows, int Score, int Health, int Lives) {
        super();

        //Move Statistics from previous level to current Level
        setStatistics(Arrows, Score, Health, Lives);

        //Set Character Position
        getCharacter().setPosition(new Vec2(0, 3));

        //Displays score with collisions
        CharacterCollisions Collisions = new CharacterCollisions(getCharacter(), game);
        getCharacter().addCollisionListener(Collisions);

        //Message
        Shape Message = new BoxShape(6, 0.5f);
        StaticBody MessageBody = new StaticBody(this, Message);
        MessageBody.getFixtureList().removeFirst().destroy();
        GhostlyFixture MessageFixture = new GhostlyFixture(MessageBody, Message);
        MessageBody.setPosition(new Vec2(0f, -4));
        MessageBody.addImage(HowHighCanYouFlyMessage);

        //Make a Starting Platform
        Shape shape1 = new BoxShape(6, 0.5f);
        StaticBody platform1 = new StaticBody(this, shape1);
        platform1.setPosition(new Vec2(0f, 0));
        platform1.addImage(Cloud);

        //Generate Platforms
        makePlatforms();
    }

    /**
     * Auto Generates Platforms with different object types dependent on the difficulty
     * <p>Creates Platforms, Enemies, Power Ups and Arrows, Through Auto-Generated Positions And Spawn Rates, Which Are Dependant On the
     * Difficulty, Which Increments After Each Sub Chapter</p>
     */
    public void makePlatforms(){

        getCharacter().setEnemiesKilled(0);
        setEnemies(0);

        //Create an array of unique x values for the platforms
        createUniqueNumbers();

        //if the most recent platform is bouncy, it's index is stored
        int isPurple = -1;

        //create the platforms
        for (int i = 1; i < 10; i++) {

            Shape groundShape = new BoxShape(6, 0.5f);
            StaticBody ground = new StaticBody(this, groundShape);
            SolidFixture platformFixture = new SolidFixture(ground, groundShape);
            ground.setPosition(new Vec2(uniqueNumbers[i], getMaxPlatformPosition() + 8 * i));
            ground.addImage(Cloud);


            //sets the position of the last platform and loads Arrows
            if (i == 9) {
                Portal portal = new Portal(this, ground.getPosition());
                setMaxPlatformPosition(getMaxPlatformPosition() + 8 * i);
            } else {

                float Random = (float) Math.random();

                // chance of Arrows spawning
                if (Random > 0.6) {
                    ResupplyArrows(this, ground);
                }

                //chance of bouncy cloud spawning and checks if the previous three platforms were bouncy
                else if (Random > 0.5 && Random < 0.6 && i > isPurple + 2) {
                    platformFixture.setRestitution(3);
                    ground.removeAllImages();
                    ground.addImage(purpleCloud);
                    isPurple = i;
                }
                else if (Random > 0 && Random < 0.2) {
                    AntiGravity antiGravity = new AntiGravity(this, ground.getPosition());
                }
                else if (Random > 0.2 && Random < 0.35 && Random < Difficulty) {
                    Enemy Guardian = new Guardian(this, ground.getPosition(), getCharacter());
                    EnemyDamage guardianDamage = new EnemyDamage(Guardian, getCharacter());
                    Guardian.addCollisionListener(guardianDamage);
                    setEnemies(getEnemies() + 1);
                    movePlatform(ground, 20, 0);
                }
                //loads enemy on a condition
                if (Random > 0.35 && Random < 0.5 && Random < Difficulty) {
                    Enemy Enemy = new Zombie(this, ground.getPosition(), getCharacter());
                    EnemyDamage enemyDamage = new EnemyDamage(Enemy, getCharacter());
                    Enemy.addCollisionListener(enemyDamage);
                    setEnemies(getEnemies() + 1);
                }
            }
        }
        // Increases difficulty(spawn rates) for every level by roughly 20%
        Difficulty += 0.2F;
    }


    /**
     * Generates unique numbers
     * <p>Generates Unique Numbers For X Positions Of The Platforms</p>
     */
    public void createUniqueNumbers () {
        int currentIndex = 0;

        while (currentIndex < 10) {

            //Generate a random number between -11 and 11
            float randomNumber = generateRandomNumber();

            //checks if the number is in the array and returns a boolean
            //value, if the value is false(the number is unique), it is added to the array
            if (!contains(uniqueNumbers, currentIndex, randomNumber)) {
                uniqueNumbers[currentIndex] = randomNumber;
                currentIndex++;
            }
        }
    }

    /**
     * Generates a random number
     * @return returns a float between 11 and -11
     */
    private static float generateRandomNumber () {
        //generates random number between 11 and -11
        return (float) Math.floor(Math.random() * ((float) 11 - (float) -11 + 1) + (float) -11);
    }

    /**
     * Checks if the value is unique
     * <p>Checks Value Against The Array, To Check If They Are Equal</p>
     * @return True if value is equal, and false if else
     */
    private static boolean contains ( float[] array, int length, float value){
        //returns true if the value is in the array
        for (int i = 0; i < length; i++) {
            if (array[i] == value) {
                return true;
            }
        }
        //returns false if the value is not in the array
        return false;
    }
}
