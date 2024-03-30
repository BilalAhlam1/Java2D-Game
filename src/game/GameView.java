package game;

import city.cs.engine.DebugViewer;
import city.cs.engine.UserView;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class GameView extends UserView {
    private final Image chapter1Background = new ImageIcon("data/Background/Clouds_GIF.gif").getImage();
    private final Image chapter2Background = new ImageIcon("data/Background/moon world gif.gif").getImage();
    private final Image chapter3Background = new ImageIcon("data/Background/flying island gif.gif").getImage();
    private final Image heart = new ImageIcon("data/Loot/Seperate/Heart.png").getImage();
    private final Image newImage = heart.getScaledInstance(30, 30, Image.SCALE_DEFAULT);
    private final Movement movement;
    private Character Character;
    private final Scene Camera;
    private final Shooting shooting;
    private int Chapter;

    public GameView(GameWorld w, int Chapter, int width, int height) throws LineUnavailableException, IOException, UnsupportedAudioFileException {
        super(w, width, height);

        this.Chapter = Chapter;

        //get the character
        Character = w.getCharacter();

        //make a JFrame
        JFrame frame = new JFrame("Links Adventure");

        //Calls the function to follow the character and load the scene(levels)
        Camera = new Scene(Character, this);
        w.addStepListener(Camera);
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

        shooting = new Shooting(w, this, Character);
        frame.addMouseListener(shooting);

        movement = new Movement(Character);
        //Control Character
        frame.addKeyListener(movement);

        //optional: uncomment this to make a debugging view
        JFrame debugView = new DebugViewer(w, 500, 500);

        frame.getContentPane();
        setLayout(null);

        //Add Game Score and Health to the user view
        //add(w.getStatistics().getArrows());
        //add(w.getStatistics().getHealth());
        //add(w.getStatistics().getScore());
    }

    public void updateCharacter(Character character){
        this.Character = character;
    }

    public void setChapter(int chapter) {
        Chapter = chapter;
    }

    public Scene getCamera() {
        return Camera;
    }

    public Shooting getShooting() {
        return shooting;
    }

    public Movement getMovement() {
        return movement;
    }

    @Override
    protected void paintBackground(Graphics2D g) {
        //adds the background onto the user view
        if (Chapter == 1) {
            g.drawImage(chapter1Background, 0, 0, this);
        } else if (Chapter == 2) {
            g.drawImage(chapter2Background, -10, -15, this);
        }
        for (int i = 0; i < Character.getLives(); i++){
            g.drawImage(newImage, 25 * i, 10, this);
        }

        Font font = new Font("Arial", Font.BOLD, 20);
        g.setFont(font);
        g.drawString("Arrows = " + Character.getArrowCount(), 100, 30);
        g.drawString("Score = " + Character.getScoreCount(), 350, 30);
        g.drawString("Health = " + Character.getHealthCount(), 665, 30);
    }
}
