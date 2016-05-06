import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
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

    private JButton nextButton;
    private JTextPane messageWindow;
    private String outputText;

    public GamePanel() {
        ButtonListener listener = new ButtonListener();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        dialog = new File("dialog.txt");
        outputText = "Hello World";

        messageWindow = new JTextPane();
        messageWindow.setPreferredSize(new Dimension(500, 400));
        messageWindow.setEditable(false);

        // Used to center the Text filed
        StyledDocument doc = messageWindow.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);

        messageWindow.setText(outputText);

        nextButton = new JButton(">");
        nextButton.addActionListener(listener);
        nextButton.setAlignmentX((float) 0.5);

        this.add(messageWindow);
        this.add(nextButton);
    }

    private class ButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            // Currently only one button
            /*
                TODO: Setup system to get text from a Java file and read it into the messageWindow
                TODO: after the nextButton is clicked. This would be easy if it was all in a text
                TODO: file, but inorder to make it so things like the Creature.java file work with it
                TODO: it will have to be done differently.
             */
            outputText += "\n\n a";
            messageWindow.setText(outputText);
        }
    }

//    public String readFile(File file) {
//        String output = "";
//        try {
//            Scanner fileScan = new Scanner(dialog);
//            boolean next = false;
//            if (fileScan.hasNextLine()) {
//                String inputLine = fileScan.nextLine();
//                if (inputLine.equalsIgnoreCase(START)) {
//                    next = true;
//                }
//                while (next == true) {
//                    output += inputLine;
//                    if (fileScan.hasNextLine()) {
//                        inputLine = fileScan.nextLine();
//                    }
//                    if (inputLine == END) {
//                        next = false;
//                    }
//                }
//                messageWindow = new JLabel(output);
//
//            }
//
//            fileScan.close();
//        } catch (FileNotFoundException e) {
//            System.out.println("Dialog file not found!");
//        }
//
//        return output;
//    }
}
