package game;
import city.cs.engine.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Control extends KeyAdapter {

    Walker Character;
    UserView view;
    Camera Cam;

    public Control(Walker StudentWalker, UserView View){
        this.Character = StudentWalker;
        this.view = View;
    }
    @Override
    public void keyPressed(KeyEvent e) {
        int Key = e.getKeyCode();
        Camera Cam = new Camera(Character.getPosition().x, Character.getPosition().y, view);
        Cam.Move();
        if (Key == KeyEvent.VK_RIGHT) {
            Character.startWalking(7);
            Cam.Move();
        } else if (Key == KeyEvent.VK_LEFT) {
            Character.startWalking(-7);
            Cam.Move();
        } else if (Key == KeyEvent.VK_UP) {
            Character.jump(15);
            Cam.Move();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        Camera Cam = new Camera(Character.getPosition().x, Character.getPosition().y, view);
        if(e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_RIGHT) {
            Character.stopWalking();
            Cam.Move();
        }
        Cam.Move();
    }
}
