import javax.swing.UIManager;
import javax.swing.UIManager.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
/**
 * 
 * @author (Canh Duc Tran (Chris)) 
 * @version (31/08/2016)
 */
public class Main
{
    private JFrame currentGame;
    
    public static void main(String [] args)
    {
        try {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            // If Nimbus is not available, you can set the GUI to another look and feel.
        }
        
        Player player = new Player();
        Level level = new Level(20, 28);
        Platform platform = new Platform(level, player);
    }
}
