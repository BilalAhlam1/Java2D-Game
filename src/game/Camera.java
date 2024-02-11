package game;

import city.cs.engine.UserView;
import org.jbox2d.common.Vec2;

import javax.swing.text.View;

public class Camera {
    float xPos;
    float yPos;
    Vec2 Vec;

    UserView view;
    public Camera(float xPos, float yPos, UserView view){
        this.xPos = xPos;
        this.yPos = yPos;
        this.view = view;
    }


    public void Move(){
        view.setView(new Vec2(0, yPos), 20);
    }
}
