package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

public class Chapter3 extends GameWorld{
    private final float[] uniqueNumbers = new float[10];
    //private float currentYPos = 0;
    private final float MaxLevel = 0;
    private double Difficulty = 0;
    private static final BodyImage Cloud = new BodyImage("data/Blue Cloud.png", 7f);
    private static final BodyImage purpleCloud = new BodyImage("data/Purple Cloud.png", 7f);

    public Chapter3(Game game, int Arrows, int Score, int Health, int Lives) {
        super();

        //Make an array of positions,
        //set objects and empty array
        //if array is empty run condition to make platforms

        //setChapter(3);
        //Move Statistics from previous level to current Level
        setStatistics(Arrows, Score, Health, Lives);

        //could have the camera keep moving
        getCharacter().setPosition(new Vec2(0, 3));

        //Displays score with collisions
        CharacterCollisions Collisions = new CharacterCollisions(getCharacter(), game);
        getCharacter().addCollisionListener(Collisions);

        //make a starting platform
        Shape shape1 = new BoxShape(6, 0.5f);
        StaticBody platform1 = new StaticBody(this, shape1);
        platform1.setPosition(new Vec2(0f, 0));
        platform1.addImage(Cloud);

        makePlatforms();
    }

    public void makePlatforms(){
        getCharacter().setEnemiesKilled(0);
        setEnemies(0);
        setChapter(3);
        //Create an array of unique x values for the platforms
        createUniqueNumbers();

        //if the most recent platform is bouncy, it's index is stored
        int isPurple = -1;

        //create the platforms
        for (int i = 1; i < 10; i++) {

            Shape groundShape = new BoxShape(6, 0.5f);
            StaticBody ground = new StaticBody(this, groundShape);
            SolidFixture platformFixture = new SolidFixture(ground, groundShape);
            ground.setPosition(new Vec2(uniqueNumbers[i], getCurrentYPos() + 8 * i));
            ground.addImage(Cloud);


            //sets the position of the last platform and loads Arrows
            if (i == 9) {
                Portal portal = new Portal(this, ground.getPosition());
                setCurrentYPos(getCurrentYPos() + 8 * i);
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
                    AntiGravity antiGravity = new AntiGravity(this, ground.getPosition(), getCharacter());
                }
                else if (Random > 0.2 && Random < 0.35 && Random < Difficulty) {
                    Enemy Guardian = new Guardian(this, ground.getPosition(), getCharacter());
                    EnemyDamage guardianDamage = new EnemyDamage(Guardian, getCharacter());
                    Guardian.addCollisionListener(guardianDamage);
                    setEnemies(getEnemies() + 1);
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

    private static float generateRandomNumber () {
        //generates random number between 11 and -11
        return (float) Math.floor(Math.random() * ((float) 11 - (float) -11 + 1) + (float) -11);
    }

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
