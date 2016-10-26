
/**
 * 
 * @author (Canh Duc Tran (Chris)) 
 * @version (31/08/2016)
 */
public class Level
{
    private int ballSpeed;
    private int bricksQuantity;
    /**
     * Constructor for objects of class Level
     */
    public Level(int ballSpeed, int bricksQuantity)
    {
        this.ballSpeed = ballSpeed;
        this.bricksQuantity = bricksQuantity;
    }

    public int getBallSpeed()
    {
        return this.ballSpeed;
    }
    
    public int getBricksQuantity()
    {
        return this.bricksQuantity;
    }
}
