package game;
import city.cs.engine.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Control extends KeyAdapter {

    Walker Character;
    UserView view;
    Camera Cam;
    World world;

    public Control(Walker StudentWalker, UserView View, World world){
        this.Character = StudentWalker;
        this.view = View;
        this.world = world;
    }
    @Override
    public void keyPressed(KeyEvent e) {
        int Key = e.getKeyCode();
        //Camera Cam = new Camera(Character.getPosition().x, Character.getPosition().y, view, world);
        //Cam.Move();
        if (Key == KeyEvent.VK_RIGHT) {
            Character.startWalking(5);
            //Cam.Move();
        } else if (Key == KeyEvent.VK_LEFT) {
            Character.startWalking(-5);
            //Cam.Move();
        } else if (Key == KeyEvent.VK_UP) {
            Character.jump(20);
            //Cam.Move();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //Camera Cam = new Camera(Character.getPosition().x, Character.getPosition().y, view, world);
        if(e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_RIGHT) {
            Character.stopWalking();
            //Cam.Move();
        }
        //Cam.Move();
    }
}
