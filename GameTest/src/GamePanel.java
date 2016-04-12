import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by rb129 on 4/8/2016.
 */
public class GamePanel extends JPanel {
    private File dialog;
    private final String START = "---START---";
    private final String END = "---END---";

    private JLabel messageWindow;   // temporary, will be used to test until some graphics are added
    private JButton nextButton;

    public GamePanel(){
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        messageWindow = new JLabel();
        nextButton = new JButton(">");

        try {
            Scanner fileScan = new Scanner(dialog);
            boolean next = false;
            String output = "";
            if(fileScan.hasNextLine()){
                String inputLine = fileScan.nextLine();
                if(inputLine.equalsIgnoreCase(START)){
                    next = true;
                }
                while(next == true){
                    ;output += inputLine;
                    if(fileScan.hasNextLine()){
                        inputLine = fileScan.nextLine();
                    }
                    if(inputLine == END){
                        next = false;
                    }
                }
            }

            fileScan.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("Dialog file not found!");
        }

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
