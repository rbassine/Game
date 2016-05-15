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
    private Scanner fileScan;

    public GamePanel() {
        ButtonListener listener = new ButtonListener();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        dialog = new File("dialog.txt");
        try {
            fileScan = new Scanner(dialog);

            outputText = fileScan.nextLine();

            messageWindow = new JTextPane();
            messageWindow.setPreferredSize(new Dimension(500, 400));
            messageWindow.setEditable(false);

            JScrollPane scrollPane = new JScrollPane(messageWindow,
                    ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                    ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

            // Used to center the Text filed
            StyledDocument doc = messageWindow.getStyledDocument();
            SimpleAttributeSet center = new SimpleAttributeSet();
            StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
            doc.setParagraphAttributes(0, doc.getLength(), center, false);

            messageWindow.setText(outputText);

            nextButton = new JButton(">");
            nextButton.addActionListener(listener);
            nextButton.setAlignmentX((float) 0.5);

            this.add(scrollPane);
            this.add(nextButton);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private class ButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            // Currently only one button

            outputText += "\n\n" + fileScan.nextLine();
            messageWindow.setText(outputText);
            if(!fileScan.hasNextLine()){
                nextButton.setEnabled(false);
            }
        }
    }
}
