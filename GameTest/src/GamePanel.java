import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by rb129 on 4/8/2016.
 */
public class GamePanel extends JPanel {
    private File dialog;
    private Scanner fileScan;

    private Player tempPlayer;  // will be changed to account for a team later
    private String playerName;

    private JButton nextButton;
    private JTextPane messageWindow;
    private String outputText;
    private JPanel mainPanel;

    //Temp menu components
    private JPanel tempMenuPanel;
    private JButton testBattleButton;
    private JButton statsButton;
    private boolean statsShowing;

    //Ballte components
    private JPanel battleMenuPanel;

    private JButton swordButton;
    private JButton gunButton;
    private JButton personaButton;
    private JButton communicateButton;
    private JButton fleeButton;



    public GamePanel() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        dialog = new File("dialog.txt");
        try {
            fileScan = new Scanner(dialog);
            lineCount = 0;
            outputText = fileScan.nextLine();
            lineCount++;

            statsShowing = false;

            namePrompt();

            tempPlayer = new Player(playerName, 1);

            makeMessagePanel();
            makeTempMenuPanel();
            this.add(mainPanel);

        } catch (FileNotFoundException e) {
            System.out.println("File Not Found: " + dialog);
            outputText = "File Not Found: " + dialog;
            makeMessagePanel();
            nextButton.setEnabled(false);
            this.add(mainPanel);
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

        mainPanel.add(messagePanel, BorderLayout.CENTER);
    }

    private void makeTempMenuPanel(){
        tempMenuPanel = new JPanel();
        tempMenuPanel.setLayout(new BoxLayout(tempMenuPanel, BoxLayout.Y_AXIS));
        tempMenuPanel.setPreferredSize(new Dimension(150, 500));

        TempMenuListener listener = new TempMenuListener();

        JLabel menuLabel = new JLabel("Temp Menu");
        menuLabel.setFont(new Font("Arial", Font.BOLD, 20));
        menuLabel.setAlignmentX((float) 0.5);

        testBattleButton = new JButton("Test Battle");
        testBattleButton.setAlignmentX((float) 0.5);
        testBattleButton.addActionListener(listener);

        statsButton = new JButton("Stats");
        statsButton.setAlignmentX((float) 0.5);
        statsButton.addActionListener(listener);

        tempMenuPanel.add(Box.createVerticalStrut(10));
        tempMenuPanel.add(menuLabel);
        tempMenuPanel.add(Box.createVerticalStrut(15));
        tempMenuPanel.add(testBattleButton);
        tempMenuPanel.add(Box.createVerticalStrut(15));
        tempMenuPanel.add(statsButton);
        tempMenuPanel.add(Box.createVerticalStrut(15));

        mainPanel.add(tempMenuPanel, BorderLayout.EAST);
    }

    private void makeBattleMenuPanel(){
        battleMenuPanel = new JPanel();
        battleMenuPanel.setLayout(new BoxLayout(battleMenuPanel, BoxLayout.Y_AXIS));
        battleMenuPanel.setPreferredSize(new Dimension(150, 500));

        BattleMenuListener listener = new BattleMenuListener();

        JLabel label = new JLabel("Battle Menu");
        label.setFont(new Font("Arial", Font.BOLD, 20));
        label.setAlignmentX((float) 0.5);

        swordButton = new JButton("Sword");
        swordButton.setAlignmentX((float) 0.5);
        swordButton.addActionListener(listener);

        gunButton = new JButton("Gun");
        gunButton.setAlignmentX((float) 0.5);
        gunButton.addActionListener(listener);
        gunButton.setEnabled(false);    //TODO: make it so there are sword and gun attacks

        personaButton = new JButton("Persona");
        personaButton.setAlignmentX((float) 0.5);
        personaButton.addActionListener(listener);
        personaButton.setEnabled(false);

        communicateButton = new JButton("Communicate");
        communicateButton.setAlignmentX((float) 0.5);
        communicateButton.addActionListener(listener);
        communicateButton.setEnabled(false);

        fleeButton = new JButton("Flee");
        fleeButton.setAlignmentX((float) 0.5);
        fleeButton.addActionListener(listener);

        battleMenuPanel.add(Box.createVerticalStrut(10));
        battleMenuPanel.add(label);
        battleMenuPanel.add(Box.createVerticalStrut(15));
        battleMenuPanel.add(swordButton);
        battleMenuPanel.add(Box.createVerticalStrut(15));
        battleMenuPanel.add(gunButton);
        battleMenuPanel.add(Box.createVerticalStrut(15));
        battleMenuPanel.add(personaButton);
        battleMenuPanel.add(Box.createVerticalStrut(15));
        battleMenuPanel.add(communicateButton);
        battleMenuPanel.add(Box.createVerticalStrut(15));
        battleMenuPanel.add(fleeButton);
        battleMenuPanel.add(Box.createVerticalStrut(15));

        mainPanel.add(battleMenuPanel, BorderLayout.EAST);

    }

    private void namePrompt(){
        JPanel namePanel = new JPanel();
        namePanel.setLayout(new BoxLayout(namePanel, BoxLayout.Y_AXIS));

        JTextField nameField = new JTextField(30);

        namePanel.add(new JLabel("Player Name: "));
        namePanel.add(nameField);

        int result = JOptionPane.showConfirmDialog(null, namePanel, "Name Player",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if(result == JOptionPane.OK_OPTION){
//            if(nameField.getText() == null){
//                playerName = "Player";
//            }
//            else {
                String name = nameField.getText();
                playerName = name;
//            }
        }
        else if(result == JOptionPane.CANCEL_OPTION){

        }

    }

    private String battleMessage(Player player, Creature enemy){
        String message = "\n";

        StyledDocument doc = messageWindow.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_LEFT);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);

        String line1 = player.getName();
        String line1_2 = enemy.getName() + "\n";

        String line2 = "Level: " + player.getLevel();
        String line2_2 = "Level: " + enemy.getLevel() + "\n";

        String line3 = "HP: " + player.getSingleStat(0) + "/" + player.getSingleStat(1);
        String line3_2 = "HP: " + enemy.getSingleStat(0) + "/" + enemy.getSingleStat(1) + "\n";

        String line4 = "MP: " + player.getSingleStat(2) + "/" + player.getSingleStat(3);
        String line4_2 = "MP: " + enemy.getSingleStat(2) + "/" + enemy.getSingleStat(3) + "\n";

        String line5 = "Str: " + player.getSingleStat(4);
        String line5_2 = "Def: " + player.getSingleStat(5);
        String line5_3 = "Str: " + enemy.getSingleStat(4);
        String line5_4 = "Def: " + enemy.getSingleStat(5) + "\n";

        String line6 = "Spr: " + player.getSingleStat(6);
        String line6_2 = "MDef: " + player.getSingleStat(7);
        String line6_3 = "Spr: " + enemy.getSingleStat(6);
        String line6_4 = "MDef: " + enemy.getSingleStat(7) + "\n";

        String line7 = "Speed: " + player.getSingleStat(8);
        String line7_2 = "Speed: " + enemy.getSingleStat(8) + "\n";

        message += String.format("%20s %56s", line1, line1_2);
        message += String.format("%20s %50s", line2, line2_2);
        message += String.format("%20s %50s", line3, line3_2);
        message += String.format("%20s %50s", line4, line4_2);
        message += String.format("%14s %15s %35s %15s", line5, line5_2, line5_3, line5_4);
        message += String.format("%14s %15s %35s %15s", line6, line6_2, line6_3, line6_4);
        message += String.format("%20s %50s", line7, line7_2);

        /*
        TODO: Make the alignment look better
         */


        return message;
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
            if(e.getSource() == testBattleButton){
                mainPanel.remove(tempMenuPanel);
                makeBattleMenuPanel();
                revalidate();
                repaint();

                Random rand = new Random();
                int randLevel = rand.nextInt(3) + 1;
                Creature enemy = new Creature("Something", "???",  randLevel);

                String battleInfo = battleMessage(tempPlayer, enemy);
                nextButton.setEnabled(false);

                messageWindow.setText(battleInfo);
//                messageWindow.setFont(new Font());

            }
            else if(e.getSource() == statsButton){
                if(statsShowing == false){
                    messageWindow.setText(tempPlayer.toString());

                    statsButton.setText("Hide Stats");
                }
                else{
                    messageWindow.setText(outputText);
                    statsButton.setText("Stats");
                }
            }
        }
    }

    private class BattleMenuListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }
}
