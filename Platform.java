import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.awt.BorderLayout;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
/**
 * 
 * @author (Canh Duc Tran (Chris)) 
 * @version (31/08/2016)
 */
public class Platform extends JPanel
{
    private BufferedImage backgroundImage;
    private JLabel scoreLabel;
    private JLabel livesLabel;
    private JFrame currentGame = new JFrame();
    private Player player;
    private Level level;
    private ArrayList<Rectangle> bricks = new ArrayList<Rectangle>();
    private Rectangle ball = new Rectangle(300,170,5,5);
    private Paddle paddle = new Paddle(285,245,40,5);
    private boolean firstDrop = true;
   
    /**
     * Constructor for objects of class Platform
     */
    public Platform(Level level, Player player)
    {
        this.level = level;
        this.player = player;
        //currentGame.setContentPane(new JLabel(new ImageIcon("background.jpg")));
        currentGame.setLayout(new BorderLayout());  
        this.initializePlatform();
        
        currentGame.setTitle("Brick Breaker");
        currentGame.setSize(600,300);
        currentGame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        currentGame.setResizable(false);
        

        currentGame.add(this);
        currentGame.setVisible(true);
        
        this.runGame();
        
        if (this.player.getLives() <= 0)
            JOptionPane.showMessageDialog(null, "You lose","Message",JOptionPane.INFORMATION_MESSAGE);
        else if (this.player.getScore() >= this.level.getBricksQuantity())
            JOptionPane.showMessageDialog(null, "You won","Message",JOptionPane.INFORMATION_MESSAGE);
    }
    
    public void initializePlatform() {  
        this.setVisible(true);
        this.initializeBricks();
        this.makeScore();
        this.makeLives();
        this.addKeyListener(this.paddle);
        this.setFocusable(true);
        
        try 
        {
            this.backgroundImage = ImageIO.read(new File("background.jpg")); // eventually C:\\ImageTest\\pic2.jpg
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }
    
    public void makeLives() {
        this.livesLabel = new JLabel();
        this.livesLabel.setBounds(30,100,90,35);
        makeLabel(this.livesLabel, " ♥ ♥ ♥ ");
        currentGame.add(this.livesLabel, BorderLayout.SOUTH);
        revalidate();
        repaint();
    }
    
    public void minusLives() {
        String lives = "";
        for (int i = 0; i < this.player.getLives(); i++) {
            lives += " ♥ ";
        }
        this.livesLabel.setText(lives);
    }
    
    public void makeScore() {
        this.scoreLabel = new JLabel();
        this.scoreLabel.setBounds(30,100,90,35);
        makeLabel(this.scoreLabel, "Score: " + this.player.getScore());
        currentGame.add(this.scoreLabel, BorderLayout.NORTH);
        revalidate();
        repaint();
    }
    
    public void initializeBricks()  {
        int initialX = 55, currentX = 55;
        int intialY = 50, currentY = 50;
        int width = 70;
        int height = 20;
        int rows = 7;
        int columns = 3;
 
        for (int i = 1; i <= this.level.getBricksQuantity(); i++) {
            bricks.add(new Rectangle(currentX, currentY, width, height));
            currentX += width + 3;
            if (i%rows == 0) {
                currentX = initialX;
                currentY = (currentY + height + 4);
            }
        }
    }
    
    public void paint(Graphics graphics) {
        //graphics.clearRect(0, 0, getWidth(), getHeight());
        graphics.drawImage(this.backgroundImage, 0, 0, this);
        this.paintBricks(graphics);
        this.paintBall(graphics);
        this.paintPaddle(graphics);
    }
    
    private void paintPaddle(Graphics graphics) {
        graphics.setColor(new Color(70,130,180));
        graphics.fillRect(paddle.x, paddle.y, paddle.width, paddle.height);
    }
    
    private void paintBricks(Graphics graphics) {
        graphics.setColor(new Color(34,139,34));
        for (int i = 0; i < bricks.size(); i++) {
            if (bricks.get(i) != null)
                graphics.fill3DRect(bricks.get(i).x, bricks.get(i).y, bricks.get(i).width, bricks.get(i).height, true);
        }
    }
    
    private void paintBall(Graphics graphics) {
        graphics.setColor(new Color(153,0,0));
        graphics.fillOval(ball.x, ball.y, ball.width, ball.height);
    }
    
    public void runGame() {
        this.ball = new Rectangle(300,170,5,5);
        this.paddle = new Paddle(285,245,40,5);
    
        //try {
        //    Thread.sleep(3000);                 //1000 milliseconds is one second.
        //} catch(InterruptedException ex) {
        //    Thread.currentThread().interrupt();
        //}
        
        int counterX = -1;
        int counterY = -1;
        
        while (this.player.getLives() > 0 && this.player.getScore() < this.level.getBricksQuantity()) {
            for (int i = 0; i < bricks.size(); i++) {
                if (bricks.get(i) != null) {
                    if (bricks.get(i).intersects(ball)) {
                        bricks.set(i, null);
                        counterY = -counterY;
                        this.player.setScore(this.player.getScore() + 1);
                        this.scoreLabel.setText("Score: " + this.player.getScore());
                    }
                }
            }
            
            repaint();
            
            if (firstDrop) {
                this.ball.y -= counterY;
            }
            else {
               this.ball.x += counterX;
               this.ball.y += counterY;
            }
            this.checkPaddleMovement();
            
            if (this.paddle.intersects(ball)) {
                if (firstDrop)
                    firstDrop = false;
                else
                    counterY = -counterY;
            }
            
            this.checkPaddleEdges();
            
            if(this.ball.x >= 600 || this.ball.x <= 0) {
                counterX = -counterX;
            }
            
            if(this.ball.y <= 0) {
                counterY = -counterY;
            }
            
            if (this.ball.y >= 300) {
                this.player.setLives(this.player.getLives() - 1);  
                this.minusLives();
                this.firstDrop = true;
                this.runGame();
            }
            
            try {
                Thread.sleep(this.level.getBallSpeed());
            } 
            catch (Exception ex) {
            }// try catch ends here
            
        }
    }
    
    public void checkPaddleMovement() {
        if (this.paddle.isMovingLeft() == true) {
            paddle.x -= 3;
            paddle.setMovingRight(false);
        }
        else if (this.paddle.isMovingRight() == true) {
            paddle.x += 3;
            paddle.setMovingLeft(false);
        }
    }
    
    public void checkPaddleEdges() {
        if(this.paddle.x >= 565) {
            this.paddle.x = 565;
        } 
        else if(this.paddle.x <= 0) {
            this.paddle.x = 0;
        }
    }
    
    public void makeLabel(JLabel label, String text)
    {   
        try
        {
            label.setBackground(new Color(255,255,255));    //white
            //label.setForeground(new Color(0,0,0));  //black
            label.setOpaque(true);
            label.setText(text);
            label.setHorizontalAlignment(SwingConstants.CENTER);
            label.setVisible(true);
        }
        catch(Exception e)
        {
              JOptionPane.showMessageDialog(null,e.toString(),"Error!",JOptionPane.ERROR_MESSAGE);
        }
    }
}
