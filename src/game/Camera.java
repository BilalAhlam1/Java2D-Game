package game;

import city.cs.engine.UserView;
import city.cs.engine.Walker;
import city.cs.engine.World;
import org.jbox2d.common.Vec2;

import javax.swing.*;
import javax.swing.text.View;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Camera extends JPanel {
    World world;
    public int PreyPos = -1;
    private int Y;
    Levels Level1;
    Levels Level2;
    UserView view;
    Character Character;
    public Camera(Character Character, UserView view, World world){
        this.view = view;
        this.Character = Character;
        this.world = world;
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Move();
                Level1();

            }
        };
        Timer timer = new Timer(15, al);
        timer.start();
    }

    public void Move() {
        view.setView(new Vec2(0, Character.getPosition().y), 20);
    }
    public void Level1(){
        int Y = (int) Character.getPosition().y;
        //System.out.println(Y);
        if (Y == 0 && Y > PreyPos) {
            PreyPos = Y;
            Level1 = new Levels(Y, world, view, 0);
            Level1.MakeLevel();
            System.out.println("Make level");
            Level2();
        } else if (Character.getPosition().y < -20) {
            System.out.println("Died");
            Character.reset();
        }
    }
    public void Level2(){
        float Y = Level1.getMaxLevel();
        //System.out.println(Y);
        if (Character.getPosition().y == Y) {
            Level2 = new Levels(Y, world, view, 0.2);
            Level2.MakeLevel();
            System.out.println("Make level");
        }
        if (Character.getPosition().y < -20) {
            System.out.println("Died");
            Character.reset();
        }
    }
}
