package game;


import city.cs.engine.*;
import city.cs.engine.Shape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.SolverData;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.swing.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.beans.EventSetDescriptor;
import java.io.IOException;
import java.security.Key;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.event.*;
import java.util.Random;

public class Levels {
    private final GameWorld world;
    private final UserView view;
    private final float Ypos;
    private float MaxLevel = 0;
    private final Character character;
    private final float[] uniqueNumbers = new float[20];
    private static final BodyImage Cloud = new BodyImage("data/Cloud.png", 7f);
    private static final BodyImage purpleCloud = new BodyImage("data/Purple Cloud.png", 7f);
    private double Difficulty = 0;

    public Levels(Character character, float yPos, GameWorld world, double Difficulty, UserView view){
        this.character = character;
        this.world = world;
        this.Ypos = yPos;
        this.Difficulty = Difficulty;
        this.view = view;
    }

    public void MakeLevel() throws LineUnavailableException, IOException {
        createUniqueNumbers();
        for (int i = 1; i < 20; i++){

            Shape platform = new BoxShape(6, 0.5f);
            StaticBody ground = new StaticBody(world, platform);
            SolidFixture platformFixture = new SolidFixture(ground, platform);
            ground.setPosition(new Vec2(uniqueNumbers[i], Ypos + 7 * i));
            ground.addImage(Cloud);

            float Random = (float) Math.random();

            //sets the position of the last platform and loads level xp
            if (i == 19){
                XP xp = new XP(world, ground, true);
                MaxLevel = Ypos + 7 * i;
            }

            // chance of XP spawning
            if (Random > 0.5){
                XP xp = new XP(world, ground, false);
            }

            //chance of bouncy cloud spawning
            if (Random>0.2 && Random<0.3){
                platformFixture.setRestitution((float) (2 + Difficulty*10));
                ground.removeAllImages();
                ground.addImage(purpleCloud);
            }
            //loads enemy on a condition
            if (Random < Difficulty){
                Enemies enemies = new Enemies(world, ground);
                EnemyDamage enemyDamage = new EnemyDamage(enemies, character, view);
                enemies.addCollisionListener(enemyDamage);
            }
        }
    }

    public void createUniqueNumbers(){
        int currentIndex = 0;

        while (currentIndex < 19) {
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
