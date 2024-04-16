package game;

import city.cs.engine.*;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Initialises GameView With The UserView
 */
public class GameView extends UserView {
    private final Image chapter1 = new ImageIcon("data/Background/Mountain.png").getImage();
    private final Image chapter1Background = chapter1.getScaledInstance(900, 600, Image.SCALE_DEFAULT);
    //Chapter 1 Background

    private final Image chapter2Background = new ImageIcon("data/Background/Clouds_GIF.gif").getImage();
    //Chapter 2 Background

    private final Image chapter3 = new ImageIcon("data/Background/moon world gif.gif").getImage();
    private final Image chapter3Background = chapter3.getScaledInstance(900, 600, Image.SCALE_DEFAULT);
    //Chapter 3 Background

    private final Image heart = new ImageIcon("data/Loot/Seperate/Heart.png").getImage();
    private final Image heartImage = heart.getScaledInstance(40, 40, Image.SCALE_DEFAULT);
    //Lives

    private final Image arrow = new ImageIcon("data/Loot/Seperate/tile132.png").getImage();
    private final Image arrowImage = arrow.getScaledInstance(60, 60, Image.SCALE_DEFAULT);
    //Arrows

    private static final Image AntiGravity = new ImageIcon("data/Loot/Seperate/tile281.png").getImage();
    private final Image AntiGravityImage = AntiGravity.getScaledInstance(60, 60, Image.SCALE_DEFAULT);
    //Power - Ups

    private final Movement movement;
    //Movement Class

    private Character Character;
    //Character Class

    private final Scene Camera;
    //Scene Class

    private final Shooting shooting;
    //Shooting Class

    private int Chapter;
    //Current Chapter

    private final JFrame frame;
    //Game Frame

    private final int[] Seconds = {30};
    //Timer Seconds

    private Timer timer1;
    //Timer

    /**
     * Constructor Initialises GameView
     * <p>Sets The Frame And In-Game Timer</p>
     * @param game Game Class
     * @param w GameWorld Class
     * @param Chapter Chapter To Launch
     * @param width UserView Width
     * @param height UserView Height
     */
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
            Seconds[0] = 60;
            startTimer();
        } else if (Chapter == 3){
            Seconds[0] = 120;
            startTimer();
        }

        frame.getContentPane();
        setLayout(null);
    }

    /**
     * CountDown Timer
     * <p>Restarts Game If Timer Reaches 0, Under Chapters Other Than 3. Main Menu Launches If Chapter is 3 And Countdown Completes</p>
     */
    public void startTimer(){
        ActionListener a = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                //De-Increments Seconds
                Seconds[0]--;
                if (Seconds[0] == 0){

                    //When Chapter is 3, Countdown Launches Main menu and Score
                    if (Chapter == 3) {
                        Menu menu = new Menu();
                        frame.dispose();
                        timer1.stop();
                        menu.setScoreMessage(3, Character.getScoreCount());
                    }

                    //Countdown Launches a New Game If Else
                    else {
                        frame.dispose();
                        timer1.stop();
                        try {
                            new Game(Chapter, 0, Character.getScoreCount() - 1, 100, Character.getLives());
                        } catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }

                //Launches Main Menu And Score Board If Lives Reach 0
                if (Character.getLives() == 0){
                    Character.setLives(-1);
                    Menu menu = new Menu();
                    menu.setScoreMessage(Chapter, Character.getScoreCount());
                    frame.dispose();
                }
            }
        };

        //Repeats Every Second
        timer1 = new Timer(1000, a);
        timer1.start();
    }

    /**
     * Setter For a New Character Class
     * @param character New Character Class
     */
    public void updateCharacter(Character character){
        this.Character = character;
    }

    /**
     * Setter For The Current Chapter
     * @param chapter Current Chapter
     */
    public void setChapter(int chapter) {
        Chapter = chapter;
    }

    /**
     * Getter For The Current Chapter
     * @return Current Chapter
     */
    public int getChapter() {
        return Chapter;
    }

    /**
     * Getter For The Step Listener Camera
     * @return Current Step Listener
     */
    public Scene getCamera() {
        return Camera;
    }

    /**
     * Getter For The Shooting Class
     * @return Current Shooting Class
     */
    public Shooting getShooting() {
        return shooting;
    }

    /**
     * Getter For The Movement Class
     * @return Current Movement Class
     */
    public Movement getMovement() {
        return movement;
    }

    /**
     * Getter For The Current Second
     * @return Current Second
     */
    public int getSeconds() {
        return Seconds[0];
    }

    /**
     * Getter For The Timer Method
     * @return Timer Method
     */
    public Timer getTimer1() {
        return timer1;
    }

    /**
     * Setter For The Current Seconds
     * @param seconds Current Countdown Seconds
     */
    public void setSeconds(int seconds) {
        Seconds[0] = seconds;
    }

    /**
     * Foreground Statistics
     * <p>Draw Lives, Arrows, Score, Health, Timer And Anti-Gravity Statistics</p>
     */
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

        g.drawString("Chapter:" + Chapter, 620, 80);

        //Display Timer
        g.drawString("" + Seconds[0], 375, 60);


        //Displays Anti Gravity in inventory if in use
        if (Character.getAntiGravity()){
            g.drawImage(AntiGravityImage, -10, 110, this);
        }
    }

    /**
     * Game Background
     * <p>Sets Background For Each Chapter</p>
     */
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
