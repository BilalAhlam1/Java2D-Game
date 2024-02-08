package game;
import city.cs.engine.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Control extends KeyAdapter {

    Walker Character;
    UserView view;
    public Control(Walker StudentWalker, UserView View){
        this.Character = StudentWalker;
        this.view = View;
    }
    @Override
    public void keyPressed(KeyEvent e) {
        int Key = e.getKeyCode();
        if (Key == KeyEvent.VK_RIGHT) {
            Character.startWalking(7);
            Camera Cam = new Camera(Character.getPosition().x, Character.getPosition().y, view);
            Cam.Move();
        } else if (Key == KeyEvent.VK_LEFT) {
            Character.startWalking(-7);
        } else if (Key == KeyEvent.VK_UP) {
            Character.jump(7);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_RIGHT) {
            Character.stopWalking();
        }
    }
}
