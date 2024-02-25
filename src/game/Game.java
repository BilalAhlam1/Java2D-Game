package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;
import javax.swing.*;
import javax.swing.plaf.basic.BasicTreeUI;

import java.awt.*;

/**
 * Your main game entry point
 */
public class Game{

    /** Initialise a new Game. */
    public Game(){

        //1. make an empty game world
        //2. populate it with bodies (ex: platforms, collectibles, characters)
        GameWorld gameWorld = new GameWorld();
        Student studentWalker = new Student(gameWorld);
        studentWalker.setPosition(new Vec2(0, -11));
        //3. make a view to look into the game world
        GameView view = new GameView(gameWorld, 800, 500);
        view.setCentre(studentWalker.getPosition());

        //optional: draw a 1-metre grid over the view
        //view.setGridResolution(1);

        //4. create a Java window (frame) and add the game
        //   view to it
        JFrame frame = new JFrame("City Game");
        //Calls the camera function to follow the character
        Camera cam = new Camera(studentWalker, view, gameWorld);
        frame.setContentPane(cam);
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
        MouseController MouseHandler = new MouseController(gameWorld, view, studentWalker);

        frame.addMouseListener(MouseHandler);

        //Control Character
        frame.addKeyListener(new Control(studentWalker, view, gameWorld));

        //optional: uncomment this to make a debugging view
         JFrame debugView = new DebugViewer(gameWorld, 500, 800);

        view.setView(studentWalker.getPosition(), 20);
        gameWorld.setGravity(20);
        gameWorld.start();
        // start our game world simulation!
        //while (world.isRunning()) {
            //world.setGravity(8);
        //}
    }

    /** Run the game. */
    public static void main(String[] args){
        //Game game = new Game();
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Game();
            }
        });

    }
}


