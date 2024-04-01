package game;

import city.cs.engine.*;
import city.cs.engine.Shape;
import org.jbox2d.common.Vec2;

import javax.swing.*;
import java.awt.*;

public class Chapter1 extends GameWorld{
    private final float[] uniqueNumbers = new float[10];
    private float Ypos = 0;
    private float MaxLevel = 0;
    private double Difficulty = 0;
    private static final BodyImage Cloud = new BodyImage("data/Cloud.png", 7f);
    private static final BodyImage purpleCloud = new BodyImage("data/Purple Cloud.png", 7f);
    private static final BodyImage MovementInfo = new BodyImage("data/GameMessages/Movement-Info.png", 2f);
    private static final BodyImage purpleCloudMessage = new BodyImage("data/GameMessages/purpleCloudInfo.png", 2f);
    private static final BodyImage ShootingMessage1 = new BodyImage("data/GameMessages/ShootingMessage1.png", 1f);
    private static final BodyImage ShootingMessage2 = new BodyImage("data/GameMessages/ShootingMessage2.png", 1f);
    public Chapter1(Game game){
        super();
        getCharacter().setPosition(new Vec2(0, 3));

        //Displays score with collisions
        CharacterCollisions Collisions = new CharacterCollisions(getCharacter(), game);
        getCharacter().addCollisionListener(Collisions);

        //make a starting platform
        Shape shape1 = new BoxShape(6, 0.5f);
        StaticBody platform1 = new StaticBody(this, shape1);
        platform1.setPosition(new Vec2(0f, 0));
        platform1.addImage(Cloud);

        //HOW TO MOVE
        Shape movementText = new BoxShape(6, 0.5f);
        StaticBody movementTextBody = new StaticBody(this, movementText);
        movementTextBody.getFixtureList().removeFirst().destroy();
        GhostlyFixture movementTextFixture = new GhostlyFixture(movementTextBody, movementText);
        movementTextBody.setPosition(new Vec2(0f, -3));
        movementTextBody.addImage(MovementInfo);

        //Basic Jumps
        Shape shape2 = new BoxShape(6, 0.5f);
        StaticBody platform2 = new StaticBody(this, shape2);
        platform2.setPosition(new Vec2(6f, 8));
        platform2.addImage(Cloud);

        Shape shape3 = new BoxShape(6, 0.5f);
        StaticBody platform3 = new StaticBody(this, shape3);
        platform3.setPosition(new Vec2(-6f, 16));
        platform3.addImage(Cloud);

        //Jump Boost Text
        Shape jumpBoostText = new BoxShape(6, 0.5f);
        StaticBody jumpBoostTextBody = new StaticBody(this, jumpBoostText);
        jumpBoostTextBody.getFixtureList().removeFirst().destroy();
        GhostlyFixture jumpBoostTextFixture = new GhostlyFixture(jumpBoostTextBody, jumpBoostText);
        jumpBoostTextBody.setPosition(new Vec2(5f, 20));
        jumpBoostTextBody.addImage(purpleCloudMessage);


        //Purple Cloud
        Shape shape4 = new BoxShape(6, 0.5f);
        StaticBody BouncyPlatform = new StaticBody(this, shape4);
        BouncyPlatform.setPosition(new Vec2(8f, 24));
        BouncyPlatform.addImage(Cloud);
        SolidFixture platformFixture = new SolidFixture(BouncyPlatform, shape4);
        platformFixture.setRestitution(3);
        BouncyPlatform.addImage(purpleCloud);

        //Killing Enemies
        Shape shape5 = new BoxShape(6, 0.5f);
        StaticBody platform4 = new StaticBody(this, shape5);
        platform4.setPosition(new Vec2(-8f, 36));
        platform4.addImage(Cloud);
        Quiver quiver = new Quiver(this, platform4);

        //HOW TO SHOOT
        Shape ShootText = new BoxShape(6, 0.5f);
        StaticBody ShootTextBody = new StaticBody(this, ShootText);
        ShootTextBody.getFixtureList().removeFirst().destroy();
        GhostlyFixture ShootFixture = new GhostlyFixture(ShootTextBody, ShootText);
        ShootTextBody.setPosition(new Vec2(8f, 39));
        ShootTextBody.addImage(ShootingMessage1);

        Shape ShootText2 = new BoxShape(6, 0.5f);
        StaticBody ShootTextBody2 = new StaticBody(this, ShootText2);
        ShootTextBody2.getFixtureList().removeFirst().destroy();
        GhostlyFixture ShootFixture2 = new GhostlyFixture(ShootTextBody2, ShootText2);
        ShootTextBody2.setPosition(new Vec2(8f, 38));
        ShootTextBody2.addImage(ShootingMessage2);

        //Spawn enemy
        Shape shape6 = new BoxShape(6, 0.5f);
        StaticBody platform5 = new StaticBody(this, shape6);
        platform5.setPosition(new Vec2(10f, 42));
        platform5.addImage(Cloud);
        Enemy Enemy = new Zombie(this, platform5, getCharacter());
        EnemyDamage enemyDamage = new EnemyDamage(Enemy, getCharacter());
        Enemy.addCollisionListener(enemyDamage);

        //Spawn Portal
        Shape shape7 = new BoxShape(6, 0.5f);
        StaticBody platform6 = new StaticBody(this, shape7);
        platform6.setPosition(new Vec2(-8f, 48));
        platform6.addImage(Cloud);
        Portal portal = new Portal(this, platform6);

        //Spawn arrows at spawn
        //Quiver = new Quiver(getCharacter().getWorld(), ground);

        //Create an array of unique x values for the platforms
        //createUniqueNumbers();

        //if the most recent platform is bouncy, it's index is stored
        //int isPurple = -1;

        //Create 3 Levels
        //for (int j = 0; j < 3; j++) {


            //create the platforms
            //for (int i = 1; i < 6; i++) {

                //Shape groundShape = new BoxShape(6, 0.5f);
                //StaticBody ground = new StaticBody(this, groundShape);
                //SolidFixture platformFixture = new SolidFixture(ground, groundShape);
                //ground.setPosition(new Vec2(uniqueNumbers[i], Ypos + 7 * i));
                //ground.addImage(Cloud);


                //sets the position of the last platform and loads Arrows
               // if (i == 5) {
                //    Quiver quiver = new Quiver(this, ground);
                  //  MaxLevel = Ypos + 7 * i;
              //  } else {

                   // float Random = (float) Math.random();

                    // chance of Arrows spawning
                    //if (Random > 0.9) {
                   //     Arrows arrows = new Arrows(this, ground);
                   // }

                    //chance of bouncy cloud spawning and checks if the previous cloud is bouncy
                   // if (Random > 0.2 && Random < 0.3 && i > isPurple + 1) {
                   //     platformFixture.setRestitution(3);
                   //     ground.removeAllImages();
                   //     ground.addImage(purpleCloud);
                   //     isPurple = i;
                  //  }

                    //loads enemy on a condition
                  //  if (Random < Difficulty) {
                 //       Enemy Enemy = new Enemy(this, ground);
                 //       EnemyDamage enemyDamage = new EnemyDamage(Enemy, getCharacter());
                 //       Enemy.addCollisionListener(enemyDamage);

                //    }
               // }
           // }

            // Gets the position of the highest platform
            //Ypos = getMaxLevel();

            // Increases difficulty(spawn rates) for every level by roughly 20%
            //Difficulty += 0.2F;
        //}
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
