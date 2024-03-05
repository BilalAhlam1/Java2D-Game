package game;

import city.cs.engine.UserView;
import city.cs.engine.World;
import org.jbox2d.common.Vec2;

import javax.sound.sampled.LineUnavailableException;
import javax.swing.*;
import javax.swing.text.View;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Scene extends JPanel {
    private final UserView view;
    private final Character Character;
    private JProgressBar progressBar;

    public Scene(Character Character, UserView view) {
        this.view = view;
        this.Character = Character;

        //GameView follows the character
        Camera();

        // Create and add progress bar
        progressBar = new JProgressBar(JProgressBar.VERTICAL,0, 4); // Assuming max value is 100
        progressBar.setStringPainted(true); // Display the value
        this.add(progressBar, BorderLayout.SOUTH);
    }

    public void Camera() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Gameview follows the character
                view.setView(new Vec2(0, Character.getPosition().y), 20);

                //checks the level and updates progress bar
                progressBar.setValue(Character.getLevelNum());

                //resets the character if it falls below the levels
                if (Character.getPosition().y < -20) {
                    Character.reset();
                }
            }
        };
        Timer timer = new Timer(15, al);
        timer.start();
    }
}
