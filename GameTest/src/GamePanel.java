import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * Created by rb129 on 4/8/2016.
 */
public class GamePanel extends JPanel {
    private String output;

    private JLabel messageWindow;   // temporary, will be used to test until some graphics are added
    private JButton nextButton;

    public GamePanel(){
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        output = "Persona";     // See note in button listener

        messageWindow = new JLabel(output);
        nextButton = new JButton(">");

        this.add(messageWindow);
        this.add(nextButton);
    }

    private class ButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            // Currently only one button
            /*
                TODO: Setup system to get text from a Java file and read it into the messageWindow
                TODO: after the nextButton is clicked. This would be easy if it was all in a text
                TODO: file, but inorder to make it so things like the Creature.java file work with it
                TODO: it will have to be done differently.
             */

        }
    }
}
