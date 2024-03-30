package game;
import city.cs.engine.*;

public abstract class GameWorld extends World {

    //Load platform image
    private static final BodyImage Cloud = new BodyImage("data/Cloud.png", 7f);
    private final Character Character;
    public GameWorld(){
        super(60);

        //Creates Character
        Character = new Character(this);

    }

    //Set Statistics
    public void setStatistics(int Arrows, int Score, int Health, int Lives){
        Character.setArrowCount(Arrows);
        Character.setScoreCount(Score);
        Character.setHealthCount(Health);
        Character.setLives(Lives);
    }

    public Character getCharacter() {
        return Character;
    }
}
