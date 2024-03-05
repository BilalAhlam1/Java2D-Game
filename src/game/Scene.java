package game;

import city.cs.engine.UserView;
import city.cs.engine.World;
import org.jbox2d.common.Vec2;

import javax.sound.sampled.LineUnavailableException;
import javax.swing.*;
import javax.swing.text.View;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Scene extends JPanel {
    GameWorld world;
    double Difficulty = 0;
    float lastYpos = 0;
    UserView view;
    Character Character;

    public Scene(Character Character, UserView view, GameWorld world) {
        this.view = view;
        this.Character = Character;
        this.world = world;
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Gameview follows the character
                Camera();

                //creates level when the character is on the highest platform
                if (Character.getPosition().y > lastYpos - 20) {
                    try {
                        Level();
                    } catch (LineUnavailableException | IOException ex) {
                        throw new RuntimeException(ex);
                    }
                } else if (Character.getPosition().y < -20) {
                    System.out.println("Died");
                    Character.reset();
                }

            }
        };
        Timer timer = new Timer(15, al);
        timer.start();

        //creates level 1
        //Level1();
    }

    public void Camera() {
        view.setView(new Vec2(0, Character.getPosition().y), 20);
    }

    public void Level() throws LineUnavailableException, IOException {
        Levels Level = new Levels(Character,lastYpos, world, Difficulty, view);
        Level.MakeLevel();
        System.out.println("Make level");

        //Gets the position of the highest platform
        lastYpos = Level.getMaxLevel();

        //Increases Difficulty for every level by 20%
        Difficulty = 0.2 + Difficulty;
    }
}
