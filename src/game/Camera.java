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
    Levels Platform;
    UserView view;
    Walker studentWalker;
    public Camera(Student studentWalker, UserView view, World world){
        this.view = view;
        this.studentWalker = studentWalker;
        this.world = world;
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Move();
            }
        };
        Timer timer = new Timer(15, al);
        timer.start();
    }


    public void Move() {
        view.setView(new Vec2(0, studentWalker.getPosition().y), 20);
        int Y = (int) studentWalker.getPosition().y;
        System.out.println(Y);
        if (Y == 0 && Y > PreyPos) {
            PreyPos = Y;
            Platform = new Levels(Y, world, view);
            Platform.MakeLevel();
            System.out.println("Make level");
        }
    }
}
