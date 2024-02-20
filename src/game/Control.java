package game;
import city.cs.engine.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Control extends KeyAdapter {

    Walker Character;
    UserView view;
    Camera Cam;
    World world;
    private static final BodyImage runRight = new BodyImage("data/Adventurer/Sprites/adventurer-run-00.png", 4f);
    private static final BodyImage runLeft = new BodyImage("data/Adventurer/Sprites/Run Left.png", 4f);
    private static final BodyImage jump = new BodyImage("data/Adventurer/Sprites/adventurer-fall-00.png", 4f);
    private static final BodyImage idle = new BodyImage("data/Adventurer/Sprites/adventurer-idle-01.png", 4f);

    public Control(Walker StudentWalker, UserView View, World world){
        this.Character = StudentWalker;
        this.view = View;
        this.world = world;
    }
    @Override
    public void keyPressed(KeyEvent e) {
        int Key = e.getKeyCode();
        if (Key == KeyEvent.VK_RIGHT) {
            Character.startWalking(7);
            Character.removeAllImages();
            Character.addImage(runRight);
        } else if (Key == KeyEvent.VK_LEFT) {
            Character.startWalking(-7);
            Character.removeAllImages();
            Character.addImage(runLeft);
        } else if (Key == KeyEvent.VK_UP) {
            Character.jump(20);
            Character.removeAllImages();
            Character.addImage(jump);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_RIGHT) {
            Character.stopWalking();
            Character.removeAllImages();
            Character.addImage(idle);
        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
            Character.removeAllImages();
            Character.addImage(idle);
        }
    }
}
