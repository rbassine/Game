import javax.swing.*;

/**
 * Created by rb129 on 4/8/2016.
 */
public class Game {
    public static void main(String[] args){
        JFrame frame = new JFrame("GameTest");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GamePanel panel = new GamePanel();
        frame.getContentPane().add(panel);

        frame.pack();
        frame.setVisible(true);
    }
}
