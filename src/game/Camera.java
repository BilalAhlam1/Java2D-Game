package game;

import city.cs.engine.UserView;
import city.cs.engine.World;
import org.jbox2d.common.Vec2;

import javax.swing.*;
import javax.swing.text.View;

public class Camera extends JFrame {
    float xPos;
    float yPos;
    Vec2 Vec;
    World world;
    float LMultiple = 0;
    public float PreyPos = 0;
    Levels Platform;

    UserView view;
    public Camera(float xPos, float yPos, UserView view, World world){
        this.xPos = xPos;
        this.yPos = yPos;
        this.view = view;
        this.world = world;
    }


    public void Move() {
        while (world.isRunning()) {
            view.setView(new Vec2(0, yPos), 20);
            System.out.println(yPos);

            // If the current position (Rounded to 10) is bigger than the previous height(yPos) then create a platform
            float a = (yPos % 5);
            if (a < 3) {
                if (yPos > PreyPos) {
                    this.PreyPos = yPos;
                    Platform = new Levels(yPos, world, view);
                    Platform.MakeLevel();
                }
            }
        }
    }
}
