package game;


import city.cs.engine.*;
import city.cs.engine.Shape;
import org.jbox2d.common.Vec2;
import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;

public class Levels {
    private final GameWorld world;
    private final float Ypos;
    private float MaxLevel = 0;
    private final Character character;
    private final float[] uniqueNumbers = new float[10];
    private static final BodyImage Cloud = new BodyImage("data/Cloud.png", 7f);
    private static final BodyImage purpleCloud = new BodyImage("data/Purple Cloud.png", 7f);
    private double Difficulty = 0;

    public Levels(Character character, float yPos, GameWorld world, double Difficulty){
        this.character = character;
        this.world = world;
        this.Ypos = yPos;
        this.Difficulty = Difficulty;
    }

    public void MakeLevel() throws LineUnavailableException, IOException {

        //Create an array of unique x values for the platforms
        createUniqueNumbers();

        //if the most recent platform is bouncy, it's index is stored
        int isPurple = -1;

        //create the platforms
        for (int i = 1; i < 10; i++){

            Shape platform = new BoxShape(6, 0.5f);
            StaticBody ground = new StaticBody(world, platform);
            SolidFixture platformFixture = new SolidFixture(ground, platform);
            ground.setPosition(new Vec2(uniqueNumbers[i], Ypos + 7 * i));
            ground.addImage(Cloud);

            //sets the position of the last platform and loads Arrows
            if (i == 9){
                Quiver quiver = new Quiver(world, ground);
                MaxLevel = Ypos + 7 * i;
            }

            float Random = (float) Math.random();

            // chance of Arrows spawning
            if (Random > 0.8){
                Arrows arrows = new Arrows(world, ground);
            }

            //chance of bouncy cloud spawning and checks if the previous cloud is bouncy
            if (Random>0.2 && Random<0.3 && i > isPurple + 1){
                platformFixture.setRestitution((float) (2 + Difficulty*10));
                ground.removeAllImages();
                ground.addImage(purpleCloud);
                isPurple = i;
            }
            //loads enemy on a condition
            if (Random < Difficulty){
                Enemy Enemy = new Enemy(world, ground);
                EnemyDamage enemyDamage = new EnemyDamage(Enemy, character);
                Enemy.addCollisionListener(enemyDamage);

            }
        }
    }
    public void createUniqueNumbers(){
        int currentIndex = 0;

        while (currentIndex < 10) {
            float randomNumber = generateRandomNumber(-11, 11);
            if (!contains(uniqueNumbers, currentIndex, randomNumber)) {
                uniqueNumbers[currentIndex] = randomNumber;
                currentIndex++;
            }
        }
    }

    private static float generateRandomNumber(float min, float max) {
        return (float) Math.floor(Math.random() * (max - min + 1) + min);
    }

    private static boolean contains(float[] array, int length, float value) {
        for (int i = 0; i < length; i++) {
            if (array[i] == value) {
                return true;
            }
        }
        return false;
    }

    public float getMaxLevel() {
        return MaxLevel;
    }
}
