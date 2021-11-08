package blackjack;

import javax.swing.*;
import java.util.concurrent.BlockingQueue;

public class View extends JFrame {
    private JFrame frameHome;
    private JButton playButton;
    private JButton howtoplayButton;

    private JFrame frameInstructions;
    private JLabel label;
    private JLabel labelInstructions;

    private JFrame framePlay;
    private JLabel labelPlay;
    private JButton buttonHit;
    private JButton buttonStand;

    private BlockingQueue<Message> queue;
    private CardDistributor distributor;

    public View(BlockingQueue<Message> queue, CardDistributor distributor) {
        try {
            this.queue = queue;
            this.distributor = distributor;
            //Homescreen frame
            frameHome = new JFrame("BlackJack");
            ImageIcon imagehome = new ImageIcon("homepicproject151.png");//homescreen picture
            label = new JLabel(imagehome);
            label.setBounds(0, 0, 800, 700);//size of background picture
            playButton = new JButton("Play!");//button design changed later
            playButton.setBounds(35, 365, 125, 50);//dimensions of play button
            howtoplayButton = new JButton("How to Play");//button design changed later
            howtoplayButton.setBounds(245, 365, 125, 50);//dimensions of howtoplay button


            frameHome.add(playButton);
            frameHome.add(howtoplayButton);
            frameHome.add(label);
            frameHome.setLayout(null);
            frameHome.setSize(425, 550);//size of frame
            frameHome.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frameHome.setResizable(false);//homescreen not resizeable to prevent improper scalability with buttons and images
            frameHome.setVisible(true);


            //Instructions Frame
            frameInstructions = new JFrame("How to Play");
            ImageIcon imageInstructions = new ImageIcon("InstructionsforBlackJack.png");//instructions pic
            labelInstructions = new JLabel(imageInstructions);
            labelInstructions.setBounds(0, 0, 818, 567);//size of background picture

            frameInstructions.add(labelInstructions);
            frameInstructions.setLayout(null);
            frameInstructions.setSize(818, 600);//size of instructions
            frameInstructions.setResizable(false);
            frameInstructions.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            howtoplayButton.addActionListener(e -> frameInstructions.setVisible(true));//displays instructions if how to play is clicked

            //Playing Table Frame
            framePlay = new JFrame("Playing BlackJack");
            ImageIcon bjTable = new ImageIcon("bjtable.jpg");//table picture
            labelPlay = new JLabel(bjTable);
            labelPlay.setBounds(-85, -40, 850, 550);//size of background picture
            buttonHit = new JButton("Hit!");//hit buttn
            buttonHit.setBounds(190, 365, 125, 50);//dimensions of play button
            buttonHit.addActionListener(e->{
                try{
                    HitMessage msg = new HitMessage();
                    queue.put(msg);
                } catch(InterruptedException ignored){ }
            });
            buttonStand = new JButton("Stand!");//stand button
            buttonStand.setBounds(380, 365, 125, 50);//dimensions of play button
            buttonStand.addActionListener(e->{
                try{
                    StandMessage msg = new StandMessage();
                    queue.put(msg);
                } catch(InterruptedException ignored){ }
            });

            framePlay.add(buttonHit);
            framePlay.add(buttonStand);
            framePlay.add(labelPlay);
            framePlay.setLayout(null);
            framePlay.setSize(700, 500);//size of instructions
            framePlay.setResizable(false);
            framePlay.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            playButton.addActionListener(e-> {
                framePlay.setVisible(true);
                frameHome.setVisible(false);
            });//displays playing table if play button is clicked
        } catch (Exception e) {
            System.out.println("file not found!");
        }
    }
    public void DisplayCard(Card c){
        char face = c.getCardFace();
    }
}

