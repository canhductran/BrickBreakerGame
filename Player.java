
/**
 * 
 * @author (Canh Duc Tran (Chris)) 
 * @version (31/08/2016)
 */
public class Player
{
    // instance variables - replace the example below with your own
    private int lives;
    private int score;

    /**
     * Constructor for objects of class Player
     */
    public Player()
    {
        this.lives = 3;
        this.score = 0;
    }
    
    public int getLives() {
        return this.lives;
    }
    
    public void setLives(int lives) {
        this.lives = lives;
    }
    
    public int getScore() {
        return this.score;
    }
    
    public void setScore(int score) {
        this.score = score;
    }
}
