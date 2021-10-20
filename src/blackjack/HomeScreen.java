package blackjack;

import javax.swing.*;

public class HomeScreen extends JFrame {
    private JButton playButton;
    private JButton howtoplayButton;
    private JFrame frame;
    private JLabel label;

    HomeScreen() {
        try {
            frame = new JFrame("BlackJack");
            ImageIcon imagetest = new ImageIcon("homepicproject151.png");//homescreen picture *will change later if bad*
            label = new JLabel(imagetest);
            label.setBounds(0, 0, 800, 700);//size of background picture

            playButton = new JButton("Play!");//button design changed later
            playButton.addActionListener(e -> playButton.setText("placeholder"));//performs action when pressed
            playButton.setBounds(35, 365, 125, 50);//dimensions of play button
            howtoplayButton = new JButton("How to Play");//button design changed later
            howtoplayButton.addActionListener(e -> howtoplayButton.setText("placeholder lol"));//performs action when pressed
            howtoplayButton.setBounds(245, 365, 125, 50);//dimensions of howtoplay button


            frame.add(playButton);
            frame.add(howtoplayButton);
            frame.add(label);
            frame.setLayout(null);
            frame.setSize(425, 550);//size of frame
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setResizable(false);//homescreen not resizeable to prevent improper scalability with buttons and images
            frame.setVisible(true);

        } catch (Exception e) {
            System.out.println("file not found!");
        }


    }


    public static void main(String[] args) {
        HomeScreen homeScreen = new HomeScreen();

    }
}
