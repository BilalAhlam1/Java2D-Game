package game;

import city.cs.engine.UserView;
import city.cs.engine.Walker;
import city.cs.engine.World;
import org.jbox2d.common.Vec2;

import javax.swing.*;
import javax.swing.text.View;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Movement extends JPanel {
    World world;
    double Difficulty = 0;
    float lastYpos = 0;
    UserView view;
    Character Character;

    public Movement(Character Character, UserView view, World world) {
        this.view = view;
        this.Character = Character;
        this.world = world;
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Gameview follows the character
                Camera();

                //creates level when the character is on the highest platform
                if (Character.getPosition().y > lastYpos) {
                    Level();
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

    public void Level() {
        Levels Level = new Levels(Character,lastYpos, world, Difficulty);
        Level.MakeLevel();
        System.out.println("Make level");

        //Gets the position of the highest platform
        lastYpos = Level.getMaxLevel();

        //Increases Difficulty for every level
        Difficulty = 0.1;

    }
}
