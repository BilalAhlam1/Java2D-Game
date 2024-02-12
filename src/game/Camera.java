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
    float LMultiple = 0;
    public float PreyPos = 0;
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

        // If the current position (Rounded to 10) is bigger than the previous height(yPos) then create a platform
        float a = (studentWalker.getPosition().y % 5);
        if (a < 3) {
            if (studentWalker.getPosition().y > PreyPos) {
                this.PreyPos = studentWalker.getPosition().y;
                Platform = new Levels(studentWalker.getPosition().y, world, view);
                Platform.MakeLevel();
            }
        }
    }
}
