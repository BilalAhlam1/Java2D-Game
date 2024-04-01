package game;

import city.cs.engine.*;
import city.cs.engine.Shape;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class GameView extends UserView {

    //CHAPTER1 BACKGROUND
    private final Image chapter1Background = new ImageIcon("data/Background/Clouds_GIF.gif").getImage();

    //CHAPTER2 BACKGROUND
    private final Image chapter2 = new ImageIcon("data/Background/moon world gif.gif").getImage();
    private final Image chapter2Background = chapter2.getScaledInstance(900, 600, Image.SCALE_DEFAULT);

    //CHAPTER3 BACKGROUND
    private final Image chapter3Background = new ImageIcon("data/Background/flying island gif.gif").getImage();

    //LIVE COUNT
    private final Image heart = new ImageIcon("data/Loot/Seperate/Heart.png").getImage();
    private final Image heartImage = heart.getScaledInstance(40, 40, Image.SCALE_DEFAULT);

    //ARROW COUNT
    private final Image arrow = new ImageIcon("data/Loot/Seperate/tile132.png").getImage();
    private final Image arrowImage = arrow.getScaledInstance(60, 60, Image.SCALE_DEFAULT);

    //POWER-UPS
    private static final Image AntiGravity = new ImageIcon("data/Loot/Seperate/tile281.png").getImage();
    private final Image AntiGravityImage = AntiGravity.getScaledInstance(60, 60, Image.SCALE_DEFAULT);

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

        movement = new Movement(Character, frame);
        //Control Character
        frame.addKeyListener(movement);

        //optional: uncomment this to make a debugging view
        //JFrame debugView = new DebugViewer(w, 500, 500);

        frame.getContentPane();
        setLayout(null);
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
    protected void paintForeground(Graphics2D g) {
        super.paintForeground(g);
        for (int i = 0; i < Character.getLives(); i++){
            g.drawImage(heartImage, 30 * i, 10, this);
        }

        //Displays Arrow count
        Font font1 = new Font("Monospaced", Font.BOLD, 60);
        g.setFont(font1);
        g.drawImage(arrowImage, -10, 60, this);
        g.drawString("" + Character.getArrowCount(), 40, 110);

        //Displays Score
        Font font2 = new Font("Monospaced", Font.BOLD, 20);
        g.setFont(font2);
        g.drawString("Score = " + Character.getScoreCount(), 350, 30);

        g.setColor(Color.BLACK);
        g.drawRect(575, 10, 200, 30);
        g.setColor(Color.GREEN);
        g.fillRect(575, 10, Character.getHealthCount()*2, 30);

        g.setColor(Color.BLACK);
        g.drawString("Health", 640, 30);

        //Displays Anti Gravity in inventory if in use
        if (Character.getAntiGravity()){
            g.drawImage(AntiGravityImage, -10, 110, this);
        }
    }

    @Override
    protected void paintBackground(Graphics2D g) {
        //adds the background onto the user view
        if (Chapter == 1) {
            g.drawImage(chapter1Background, 0, 0, this);
        } else if (Chapter == 2) {
            g.drawImage(chapter2Background, -10, -15, this);
        }
    }
}
