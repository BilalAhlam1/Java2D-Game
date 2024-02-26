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
    float Y;
    float preX = 1;
    private static final BodyImage Cloud = new BodyImage("data/Cloud Platform.png", 7f);

    public Levels(float yPos, World world, UserView view){
        this.world = world;
        this.view = view;
        this.Y = yPos;
    }

    public void MakeLevel(){
        for (int i = 1; i < 20; i++){
            float x = 0;

            float min = -11; // Minimum value of range
            float max = 11; // Maximum value of range
            x = (float) Math.floor(Math.random() * (max - min + 1) + min);


            Shape platform = new BoxShape(6, 0.5f);
            StaticBody ground = new StaticBody(world, platform);
            ground.setPosition(new Vec2(x, Y + 7 * i));
            ground.addImage(Cloud);

            float RandomXP = (float) Math.random();
            System.out.println(RandomXP);
            // chance of XP spawning
            if (RandomXP < 0.5){
                XP xp = new XP(world, ground);
            }

        }
    }
}
