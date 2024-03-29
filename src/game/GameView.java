package game;

import city.cs.engine.DebugViewer;
import city.cs.engine.UserView;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class GameView extends UserView {
    private final Image background = new ImageIcon("data/Clouds_GIF.gif").getImage();
    private final Image heart = new ImageIcon("data/Loot/Seperate/Heart.png").getImage();
    private final Image newImage = heart.getScaledInstance(30, 30, Image.SCALE_DEFAULT);
    private final Movement movement;
    private final Character Character;

    public GameView(GameWorld w, int width, int height) throws LineUnavailableException, IOException, UnsupportedAudioFileException {
        super(w, width, height);

        //get the character
        Character = w.getCharacter();

        //make a JFrame
        JFrame frame = new JFrame("Links Adventure");

        //Calls the function to follow the character and load the scene(levels)
        Scene Movement = new Scene(Character, this);
        w.addStepListener(Movement);
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

        movement = new Movement(Character);
        //Control Character
        frame.addKeyListener(movement);

        //optional: uncomment this to make a debugging view
        JFrame debugView = new DebugViewer(w, 500, 500);

        frame.getContentPane();
        setLayout(null);

        //Add Game Score and Health to the user view
        add(Character.getArrows());
        add(Character.getHealth());
        add(Character.getScore());
    }
    public Movement getMovement() {
        return movement;
    }

    @Override
    protected void paintBackground(Graphics2D g) {
        //adds the background onto the user view
        g.drawImage(background, 0, 0, this);
        for (int i = 0; i < Character.getLives(); i++){
            g.drawImage(newImage, 20 * i, 10, this);
        }
    }
}
