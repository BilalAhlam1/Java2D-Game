package game;

import city.cs.engine.DebugViewer;
import city.cs.engine.UserView;
import city.cs.engine.World;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class Menu extends JMenu {

    JButton Play = new JButton("Play");;
    JButton Help = new JButton("Help");
    JButton Quit = new JButton("Quit");
    private final Image background = new ImageIcon("data/Clouds_GIF.gif").getImage();

    public Menu() {
        JFrame frame = new JFrame();

        // Set the button position on the frame
        Play.setBounds(150, 130, 100, 30);
        Help.setBounds(150, 160, 100, 30);
        Quit.setBounds(150, 190, 100, 30);


        frame.add(Play);
        frame.add(Help);
        frame.add(Quit);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLayout(null);
        frame.setVisible(true);
    }
}
