package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

public class Chapter1 extends GameWorld{
    private final float[] uniqueNumbers = new float[10];
    private float Ypos = 0;
    private float MaxLevel = 0;
    private double Difficulty = 0;
    private static final BodyImage Cloud = new BodyImage("data/Cloud.png", 7f);
    private static final BodyImage purpleCloud = new BodyImage("data/Purple Cloud.png", 7f);
    public Chapter1(){
        super();

        getCharacter().setPosition(new Vec2(0, 3));

        //Displays score with collisions
        CharacterCollisions Collisions = new CharacterCollisions(getCharacter());
        getCharacter().addCollisionListener(Collisions);

        //make a starting platform
        Shape shape = new BoxShape(6, 0.5f);
        StaticBody platform = new StaticBody(this, shape);
        platform.setPosition(new Vec2(0f, 0));
        platform.addImage(Cloud);

        //Spawn arrows at spawn
        //Quiver = new Quiver(getCharacter().getWorld(), ground);

        //Create an array of unique x values for the platforms
        createUniqueNumbers();

        //if the most recent platform is bouncy, it's index is stored
        int isPurple = -1;

        //Create 3 Levels
        for (int j = 0; j < 3; j++) {


            //create the platforms
            for (int i = 1; i < 6; i++) {

                Shape groundShape = new BoxShape(6, 0.5f);
                StaticBody ground = new StaticBody(this, groundShape);
                SolidFixture platformFixture = new SolidFixture(ground, groundShape);
                ground.setPosition(new Vec2(uniqueNumbers[i], Ypos + 7 * i));
                ground.addImage(Cloud);


                //sets the position of the last platform and loads Arrows
                if (i == 5) {
                    Quiver quiver = new Quiver(this, ground);
                    MaxLevel = Ypos + 7 * i;
                } else {

                    float Random = (float) Math.random();

                    // chance of Arrows spawning
                    if (Random > 0.9) {
                        Arrows arrows = new Arrows(this, ground);
                    }

                    //chance of bouncy cloud spawning and checks if the previous cloud is bouncy
                    if (Random > 0.2 && Random < 0.3 && i > isPurple + 1) {
                        platformFixture.setRestitution((float) (2 + Difficulty * 10));
                        ground.removeAllImages();
                        ground.addImage(purpleCloud);
                        isPurple = i;
                    }

                    //loads enemy on a condition
                    if (Random < Difficulty) {
                        Enemy Enemy = new Enemy(this, ground);
                        EnemyDamage enemyDamage = new EnemyDamage(Enemy, getCharacter());
                        Enemy.addCollisionListener(enemyDamage);

                    }
                }
            }

            // Gets the position of the highest platform
            Ypos = getMaxLevel();

            // Increases difficulty(spawn rates) for every level by roughly 20%
            Difficulty += 0.2F;
        }
    }

    public void createUniqueNumbers(){
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

    private static float generateRandomNumber() {
        //generates random number between 11 and -11
        return (float) Math.floor(Math.random() * ((float) 11 - (float) -11 + 1) + (float) -11);
    }

    private static boolean contains(float[] array, int length, float value) {
        //returns true if the value is in the array
        for (int i = 0; i < length; i++) {
            if (array[i] == value) {
                return true;
            }
        }
        //returns false if the value is not in the array
        return false;
    }

    public float getMaxLevel() {
        //returns the y position of the last platform
        return MaxLevel;
    }
}
