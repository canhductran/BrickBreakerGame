import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
/**
 * 
 * @author (Canh Duc Tran (Chris)) 
 * @version (31/08/2016)
 */
public class Paddle extends Rectangle implements KeyListener
{
    private static boolean movingLeft;
    private static boolean movingRight;
    /**
     * Constructor for objects of class Paddle
     */
    public Paddle(int x, int y, int width, int height)
    {
        super(x, y, width, height);
    }
    
    public boolean isMovingLeft() {
        return movingLeft;
    }
    
    public boolean isMovingRight() {
        return movingRight;
    }
    
    public void setMovingLeft(boolean movingLeft) {
        this.movingLeft = movingLeft;
    }
    
    public void setMovingRight(boolean movingRight) {
        this.movingRight = movingRight;
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        
        if (keyCode == KeyEvent.VK_LEFT) {
            this.movingLeft = true;
        }
    
        if (keyCode == KeyEvent.VK_RIGHT) {
            this.movingRight = true;
        }
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_LEFT) {
            this.movingLeft = false;
        }
        
        if (keyCode == KeyEvent.VK_RIGHT) {
            this.movingRight = false;
        }
    }

    @Override
    public void keyTyped(KeyEvent arg0) {
    
    }
}
