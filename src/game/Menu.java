package game;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Menu extends JFrame {
    public static final String TITLE = "Links Adventure";
    private JLabel welcomeLabel = new JLabel(TITLE, SwingConstants.CENTER);
    private ImageIcon backgroundImage = new ImageIcon("data//Background/Clouds_GIF.gif");
    private JLabel background = new JLabel(backgroundImage);
    JButton Play = new JButton("Play");
    JButton NewGame = new JButton("New Game");
    JButton LoadGame = new JButton("Load Saved Game");
    JButton LoadChapter = new JButton("Chapter");
    JButton Chapter1 = new JButton("Chapter 1 - A Breeze");
    JButton Chapter2 = new JButton("Chapter 2 - MoonFall");
    JButton Chapter3 = new JButton("Chapter 3 - Mountaineer");
    JButton Help = new JButton("Help");
    JButton Quit = new JButton("Quit");
    JButton Back = new JButton("Back");
    private JPanel MainMenu;

    public Menu() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationByPlatform(true);
        // don't let the frame be resized
        setResizable(false);
        pack();
        setSize(400, 400);
        setLayout(new BorderLayout());
        setVisible(true);

        add(background);

        setMenu();

        //PLAY
        Play.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setPlay();
            }
        });

        NewGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new Game(1);
                } catch (LineUnavailableException | IOException | UnsupportedAudioFileException ex) {
                    throw new RuntimeException(ex);
                }
                dispose(); //remove main menu
            }
        });

        LoadChapter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setChapter();
            }
        });

        Chapter1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new Game(1);
                } catch (LineUnavailableException | IOException | UnsupportedAudioFileException ex) {
                    throw new RuntimeException(ex);
                }
                dispose(); //remove main menu
            }
        });

        Chapter2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new Game(2);
                } catch (LineUnavailableException | IOException | UnsupportedAudioFileException ex) {
                    throw new RuntimeException(ex);
                }
                dispose(); //remove main menu
            }
        });

        Chapter3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new Game(3);
                } catch (LineUnavailableException | IOException | UnsupportedAudioFileException ex) {
                    throw new RuntimeException(ex);
                }
                dispose(); //remove main menu
            }
        });

        //HELP Button
        Help.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setHelp();
            }
        });

        Back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setMenu();
            }
        });

        //QUIT
        Quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    public void setHelp(){
        background.removeAll();

        Back.setBounds(5, 5, 100, 30);

        JLabel Controls = new JLabel("Controls", SwingConstants.CENTER);
        Controls.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 25));
        Controls.setBounds(-40, 40, 500, 30);

        JLabel movement = new JLabel("Movement - WASD or Arrow Keys", SwingConstants.CENTER);
        movement.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 10));
        movement.setBounds(-40, 80, 500, 30);

        JLabel Shoot = new JLabel("Shoot - Left/Right Mouse Click", SwingConstants.CENTER);
        Shoot.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 10));
        Shoot.setBounds(-40, 100, 500, 30);

        JLabel Exit = new JLabel("Exit To Main Menu - ESC Key", SwingConstants.CENTER);
        Exit.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 10));
        Exit.setBounds(-40, 120, 500, 30);

        background.add(Controls);
        background.add(movement);
        background.add(Shoot);
        background.add(Exit);
        background.add(Back);

    }

    public void setPlay(){
        background.removeAll();

        Back.setBounds(5, 5, 100, 30);

        JLabel Play = new JLabel("Play", SwingConstants.CENTER);
        Play.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 25));
        Play.setBounds(-45, 40, 500, 30);

        // Set the button position on the frame
        NewGame.setBounds(150, 130, 100, 30);
        LoadGame.setBounds(150, 160, 100, 30);
        LoadChapter.setBounds(150, 190, 100, 30);

        background.add(Play);
        background.add(NewGame);
        background.add(LoadGame);
        background.add(LoadChapter);
        background.add(Back);
    }

    public void setChapter() {
        background.removeAll();

        Back.setBounds(5, 5, 100, 30);

        JLabel Chapter = new JLabel("Chapter", SwingConstants.CENTER);
        Chapter.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 25));
        Chapter.setBounds(-45, 40, 500, 30);

        // Set the button position on the frame
        Chapter1.setBounds(75, 130, 250, 30);
        Chapter2.setBounds(75, 180, 250, 30);
        Chapter3.setBounds(75, 230, 250, 30);

        background.add(Back);
        background.add(Chapter);
        background.add(Chapter1);
        background.add(Chapter2);
        background.add(Chapter3);
    }

    public void setMenu(){
        background.removeAll();
        welcomeLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 25));
        welcomeLabel.setBounds(-40, 40, 500, 30);


        // Set the button position on the frame
        Play.setBounds(150, 130, 100, 30);
        Help.setBounds(150, 160, 100, 30);
        Quit.setBounds(150, 190, 100, 30);

        background.add(Play);
        background.add(Help);
        background.add(Quit);
        background.add(welcomeLabel, BorderLayout.CENTER);
    }
}
