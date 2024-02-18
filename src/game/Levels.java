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

    public Levels(float yPos, World world, UserView view){
        this.world = world;
        this.view = view;
    }

    public void MakeLevel(){
        Random rand = new Random();
        float x = rand.nextFloat();
        Shape platform = new BoxShape(5,  0.5f);
        StaticBody ground = new StaticBody(world, platform);
        ground.setPosition(new Vec2(11, view.getY() + 5));
    }
}
