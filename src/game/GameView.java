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
    private final Image chapter1 = new ImageIcon("data/Background/Mountain.png").getImage();
    private final Image chapter1Background = chapter1.getScaledInstance(900, 600, Image.SCALE_DEFAULT);

    //CHAPTER2 BACKGROUND
    private final Image chapter2Background = new ImageIcon("data/Background/Clouds_GIF.gif").getImage();

    //CHAPTER3 BACKGROUND
    private final Image chapter3 = new ImageIcon("data/Background/moon world gif.gif").getImage();
    private final Image chapter3Background = chapter3.getScaledInstance(900, 600, Image.SCALE_DEFAULT);

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
    private final JFrame frame;
    private final int[] Seconds = {30};
    private Timer timer1;

    public GameView(Game game, GameWorld w, int Chapter, int width, int height) throws LineUnavailableException, IOException, UnsupportedAudioFileException {
        super(w, width, height);

        this.Chapter = Chapter;

        //get the character
        Character = w.getCharacter();

        //make a JFrame
        frame = new JFrame("Links Adventure");

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

        movement = new Movement(Character, frame, game, this);
        //Control Character
        frame.addKeyListener(movement);

        //Start Timer in chapter 3
        if (Chapter == 1) {
            Seconds[0] = 30;
            startTimer();
        } else if (Chapter == 2){
            Seconds[0] = 45;
            startTimer();
        } else if (Chapter == 3){
            Seconds[0] = 120;
            startTimer();
        }


        //optional: uncomment this to make a debugging view
        //JFrame debugView = new DebugViewer(w, 500, 500);

        frame.getContentPane();
        setLayout(null);
    }

    public void startTimer(){
        ActionListener a = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Seconds[0]--;
                if (Seconds[0] == 0){
                    if (Chapter == 3) {
                        Menu menu = new Menu();
                        frame.dispose();
                        timer1.stop();
                        menu.setScoreMessage(3, Character.getScoreCount());
                    } else {
                        frame.dispose();
                        timer1.stop();
                        try {
                            new Game(Chapter, 0, Character.getScoreCount() - 1, 100, Character.getLives());
                        } catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
                if (Character.getLives() == 0){
                    Character.setLives(-1);
                    Menu menu = new Menu();
                    menu.setScoreMessage(Chapter, Character.getScoreCount());
                    frame.dispose();
                }
            }
        };

        timer1 = new Timer(1000, a);
        timer1.start();
    }

    public void updateCharacter(Character character){
        this.Character = character;
    }

    public void setChapter(int chapter) {
        Chapter = chapter;
    }

    public int getChapter() {
        return Chapter;
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

    public int getSeconds() {
        return Seconds[0];
    }

    public Timer getTimer1() {
        return timer1;
    }

    public void setSeconds(int seconds) {
        Seconds[0] = seconds;
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

        //HealthBar
        g.setColor(Color.BLACK);
        g.drawRect(575, 10, 200, 30);
        g.setColor(Color.GREEN);
        g.fillRect(575, 10, Character.getHealthCount()*2, 30);

        g.setColor(Color.BLACK);
        g.drawString("Health", 640, 30);

        //Display Timer
        g.drawString("" + Seconds[0], 375, 60);


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
        } else if (Chapter == 3) {
            g.drawImage(chapter3Background, -10, -15, this);
        }
    }
}
