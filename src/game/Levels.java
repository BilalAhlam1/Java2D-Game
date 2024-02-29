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
    UserView view;
    World world;
    float Ypos;
    float MaxLevel = 0;
    private static final BodyImage Cloud = new BodyImage("data/Cloud Platform.png", 7f);
    double Difficulty = 0;

    public Levels(float yPos, World world, UserView view, double Difficulty){
        this.world = world;
        this.view = view;
        this.Ypos = yPos;
        this.Difficulty = Difficulty;
    }

    public void MakeLevel(){
        for (int i = 1; i < 20; i++){
            float x = 0;

            float min = -11; // Minimum value of range
            float max = 11; // Maximum value of range
            x = (float) Math.floor(Math.random() * (max - min + 1) + min);


            Shape platform = new BoxShape(6, 0.5f);
            StaticBody ground = new StaticBody(world, platform);
            ground.setPosition(new Vec2(x, Ypos + 7 * i));
            ground.addImage(Cloud);

            float RandomXP = (float) Math.random();
            System.out.println(RandomXP);
            // chance of XP spawning
            if (i == 19){
                XP xp = new XP(world, ground, true);
                MaxLevel = Ypos + 7 * i;
            } if (RandomXP > 0.5){
                XP xp = new XP(world, ground, false);
            } if (RandomXP < Difficulty){
                Enemies enemies = new Enemies(world, ground);
                EnemyDamage enemyDamage = new EnemyDamage(enemies);
                enemies.addCollisionListener(enemyDamage);
            }

        }
    }

    public float getMaxLevel() {
        return MaxLevel;
    }
}
