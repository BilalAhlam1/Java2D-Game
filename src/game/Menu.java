package game;

import city.cs.engine.UserView;

import javax.swing.*;
import java.awt.*;

public class Menu extends JFrame {
    public static final String TITLE = "Links Adventure";
    private JLabel welcomeLabel = new JLabel(TITLE, SwingConstants.CENTER);
    private ImageIcon backgroundImage = new ImageIcon("data/Clouds_GIF.gif");
    private JLabel background = new JLabel(backgroundImage);
    JButton Play = new JButton("Play");;
    JButton Help = new JButton("Help");
    JButton Quit = new JButton("Quit");
    JButton Back = new JButton("Back");

    public Menu() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setLayout(new BorderLayout());
        setVisible(true);

        add(background);

        setMenu();

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

        background.add(Controls);
        background.add(movement);
        background.add(Shoot);
        background.add(Back);

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
