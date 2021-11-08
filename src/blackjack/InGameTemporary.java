package blackjack;

import javax.swing.*;

public class InGameTemporary {

    JFrame framePlay;
    JLabel labelPlay;
    JButton buttonHit;
    JButton buttonStand;

    InGameTemporary() {
        framePlay = new JFrame("Playing BlackJack");
        ImageIcon imageInstructions = new ImageIcon("bjtable.jpg");//table picture
        labelPlay = new JLabel(imageInstructions);
        labelPlay.setBounds(-85, -40, 850, 550);//size of background picture

        buttonHit = new JButton("Hit!");//hit buttn
        buttonStand = new JButton("Stand!");//stand button

        buttonHit.setBounds(190, 365, 125, 50);//dimensions of play button
        buttonStand.setBounds(380, 365, 125, 50);//dimensions of play button

        framePlay.add(buttonHit);
        framePlay.add(buttonStand);
        framePlay.add(labelPlay);
        framePlay.setLayout(null);
        framePlay.setSize(700, 500);//size of instructions
        framePlay.setResizable(false);
        framePlay.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
