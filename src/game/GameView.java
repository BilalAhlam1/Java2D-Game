package game;

import city.cs.engine.DebugViewer;
import city.cs.engine.UserView;
import javax.sound.sampled.LineUnavailableException;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class GameView extends UserView {
    private final Image background = new ImageIcon("data/Clouds_GIF.gif").getImage();

    public GameView(GameWorld w, int width, int height) throws LineUnavailableException, IOException {
        super(w, width, height);

        //get the character
        Character Character = Game.getGameWorld().getCharacter();

        //make a JFrame
        JFrame frame = new JFrame("Links Adventure");

        //Calls the function to follow the character and load the scene(levels)
        Scene Movement = new Scene(Character, this);
        frame.setContentPane(Movement);
        frame.add(this);

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

        Shooting MouseHandler = new Shooting(w, this, Character);
        frame.addMouseListener(MouseHandler);

        //Control Character
        frame.addKeyListener(new Movement(Character, this, w));

        //optional: uncomment this to make a debugging view
        JFrame debugView = new DebugViewer(w, 500, 500);

        frame.getContentPane();
        this.setLayout(null);

        //Add Game Score and Health to the user view
        this.add(Character.getArrows());
        this.add(Character.getHealth());
        this.add(Character.getLivesLabel());
        this.add(Character.getScore());
    }

    @Override
    protected void paintBackground(Graphics2D g) {
        //adds the background onto the user view
        g.drawImage(background, 0, 0, this);
    }
}
