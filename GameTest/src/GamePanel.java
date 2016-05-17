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
    private int lineCount;

    public GamePanel() {
        setLayout(new BorderLayout());

        dialog = new File("dialog.txt");
        try {
            fileScan = new Scanner(dialog);
            lineCount = 0;
            outputText = fileScan.nextLine();
            lineCount++;

            makeMessagePanel();
            makeTempMenuPanel();

        } catch (FileNotFoundException e) {
            System.out.println("File Not Found: " + dialog);
            outputText = "File Not Found: " + dialog;
            makeMessagePanel();
            nextButton.setEnabled(false);
        }
    }

    private void makeMessagePanel(){
        ControlButtonListener listener = new ControlButtonListener();

        JPanel messagePanel = new JPanel();
        messagePanel.setLayout(new BoxLayout(messagePanel, BoxLayout.Y_AXIS));

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

        messagePanel.add(scrollPane);
        messagePanel.add(nextButton);

        this.add(messagePanel, BorderLayout.CENTER);
    }

    private void makeTempMenuPanel(){
        JPanel tempMenuPanel = new JPanel();
        tempMenuPanel.setLayout(new BoxLayout(tempMenuPanel, BoxLayout.Y_AXIS));
        tempMenuPanel.setPreferredSize(new Dimension(150, 500));

        TempMenuListener listener = new TempMenuListener();

        JLabel menuLabel = new JLabel("Temp Menu");
        menuLabel.setFont(new Font("Arial", Font.BOLD, 20));
        menuLabel.setAlignmentX((float) 0.5);

        JButton testBattleButton = new JButton("Test Battle");
        testBattleButton.setAlignmentX((float) 0.5);

        tempMenuPanel.add(Box.createVerticalStrut(10));
        tempMenuPanel.add(menuLabel);
        tempMenuPanel.add(Box.createVerticalStrut(15));
        tempMenuPanel.add(testBattleButton);
        tempMenuPanel.add(Box.createVerticalStrut(15));

        this.add(tempMenuPanel, BorderLayout.EAST);
    }

    private class ControlButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            // Currently only one button

            outputText += "\n\n" + fileScan.nextLine();
            messageWindow.setText(outputText);
            lineCount++;
            if(!fileScan.hasNextLine()){
                nextButton.setEnabled(false);
            }
        }
    }

    private class TempMenuListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }
}
