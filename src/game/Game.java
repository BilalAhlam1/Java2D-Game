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

/**
 * Your main game entry point
 */
public class Game{
    public static Walker studentWalker;
    /** Initialise a new Game. */
    public Game() {

        //1. make an empty game world
        World world = new World();

        //2. populate it with bodies (ex: platforms, collectibles, characters)

        //make a ground platform
        Shape shape = new BoxShape(30, 0.5f);
        StaticBody ground = new StaticBody(world, shape);
        ground.setPosition(new Vec2(0f, -11.5f));

        //make a roof platform
        Shape roof = new BoxShape(30, 0.5f);
        StaticBody roofbody = new StaticBody(world, roof);
        roofbody.setPosition(new Vec2(0f, 11.5f));

        //make a Left Wall platform
        Shape leftWall = new BoxShape(0.5f, 15);
        StaticBody leftWallBody = new StaticBody(world, leftWall);
        leftWallBody.setPosition(new Vec2(-11.5f, 0f));

        //make a Right Wall platform
        Shape rightWall = new BoxShape(0.5f, 15);
        StaticBody rightWallBody = new StaticBody(world, rightWall);
        rightWallBody.setPosition(new Vec2(11.5f, 0f));

        // make a suspended platform
        Shape platformShape = new BoxShape(3, 0.5f);
        StaticBody platform1 = new StaticBody(world, platformShape);
        platform1.setPosition(new Vec2(-8, -4f));

        //make a character (with an overlaid image)
        Shape studentShape = new BoxShape(1,2);
        DynamicBody student = new DynamicBody(world, studentShape);
        student.setPosition(new Vec2(0,0));
        student.addImage(new BodyImage("data/student.png", 4));
        studentWalker = new Walker(world, studentShape);
        studentWalker.jump(7);


        //Football
        Shape Circle = new CircleShape(1);
        DynamicBody CircleBody = new DynamicBody(world, Circle);
        CircleBody.setPosition(new Vec2(0,0));
        CircleBody.addImage(new BodyImage("data/football.png", 4));
        SolidFixture CircleFixture = new SolidFixture(CircleBody, Circle);
        CircleFixture.setDensity(2);


        //3. make a view to look into the game world
        UserView view = new UserView(world, 500, 500);


        //optional: draw a 1-metre grid over the view
        //view.setGridResolution(1);


        //4. create a Java window (frame) and add the game
        //   view to it
        final JFrame frame = new JFrame("City Game");
        frame.add(view);

        // enable the frame to quit the application
        // when the x button is pressed
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationByPlatform(true);
        // don't let the frame be resized
        frame.setResizable(false);
        // size the frame to fit the world view
        frame.pack();
        // finally, make the frame visible
        frame.setVisible(true);

        //optional: uncomment this to make a debugging view
         JFrame debugView = new DebugViewer(world, 500, 500);


        // start our game world simulation!
        while (true) {
            world.setGravity(8);
            world.getSimulationSettings().setTargetFrameRate(60);
            world.start();
        }

    }

    public void KeyPressed(KeyEvent e){
        char Key = e.getKeyChar();
        System.out.println(Key);
        if(Key == 'd'){
            studentWalker.startWalking(2);
        }
    }
    /** Run the game. */
    public static void main(String[] args){

        new Game();

    }
}


