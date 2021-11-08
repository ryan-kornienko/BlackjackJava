package blackjack;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeScreen extends JFrame {
    private JButton playButton;
    private JButton howtoplayButton;
    private JFrame frameHome;
    private JFrame frameInstructions;

    private JLabel label;
    private JLabel labelInstructions;

    HomeScreen() {
        try {
            frameHome = new JFrame("BlackJack");
            frameInstructions = new JFrame("How to Play");
            ImageIcon imagehome = new ImageIcon("homepicproject151.png");//homescreen picture
            ImageIcon imageInstructions = new ImageIcon("InstructionsforBlackJack.png");//instructions pic
            label = new JLabel(imagehome);
            labelInstructions = new JLabel(imageInstructions);
            label.setBounds(0, 0, 800, 700);//size of background picture
            labelInstructions.setBounds(0, 0, 818, 567);//size of background picture


            playButton = new JButton("Play!");//button design changed later

            playButton.setBounds(35, 365, 125, 50);//dimensions of play button
            howtoplayButton = new JButton("How to Play");//button design changed later
            howtoplayButton.addActionListener(e -> frameInstructions.setVisible(true));//displays instructions if how to play is pressed
            howtoplayButton.setBounds(245, 365, 125, 50);//dimensions of howtoplay button


            frameHome.add(playButton);
            frameHome.add(howtoplayButton);
            frameHome.add(label);
            frameHome.setLayout(null);
            frameHome.setSize(425, 550);//size of frame
            frameHome.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frameHome.setResizable(false);//homescreen not resizeable to prevent improper scalability with buttons and images
            frameHome.setVisible(true);

            frameInstructions.add(labelInstructions);
            frameInstructions.setLayout(null);
            frameInstructions.setSize(818, 600);//size of instructions
            frameInstructions.setResizable(false);
            frameInstructions.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        } catch (Exception e) {
            System.out.println("file not found!");
        }


    }


    public static void main(String[] args) {
        HomeScreen homeScreen = new HomeScreen();
        InGameTemporary inGame = new InGameTemporary();
        homeScreen.playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inGame.framePlay.setVisible(true); //makes play screen visible if "play is clicked"
                homeScreen.frameHome.setVisible(false);//hides homescreen when play is clicked
            }
        });
    }

}

