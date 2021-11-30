package blackjack;

import javax.swing.*;
import java.awt.*;
import java.util.Random;
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

            //frame for homescreen
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
            buttonHit.addActionListener(e -> {
                try {
                    HitMessage msg = new HitMessage();
                    queue.put(msg);
                } catch (InterruptedException ignored) {
                }
            });
            buttonStand = new JButton("Stand!");//stand button
            buttonStand.setBounds(380, 365, 125, 50);//dimensions of play button
            buttonStand.addActionListener(e -> {
                try {
                    StandMessage msg = new StandMessage();
                    queue.put(msg);
                } catch (InterruptedException ignored) {
                }
            });

            distributor.initialization();


            //displays Player card 1
            String cardValue1 = distributor.getPlayerHand().get(0).toString();
            ImageIcon HandOne = null;
            ImageIcon setTwos = new ImageIcon(DisplayCard(cardValue1, HandOne));
            JLabel PlayerCurrentHand1 = new JLabel(setTwos);
            PlayerCurrentHand1.setBounds(250, 240, 90, 115);

            //displays Player card 2
            String cardValue2 = distributor.getPlayerHand().get(1).toString();//grabs players second hand
            ImageIcon HandTwo = null;
            ImageIcon set2Hand = new ImageIcon(DisplayCard(cardValue2, HandTwo));
            JLabel PlayerCurrentHand2 = new JLabel(set2Hand);
            PlayerCurrentHand2.setBounds(350, 240, 90, 115);

            //displays Dealer card1
            String dealerCardValue1 = distributor.getDealerHand().get(0).toString();//grabs dealers first hand
            ImageIcon DealerHandOne = null;
            ImageIcon setDealer1Hand = new ImageIcon(DisplayCard(dealerCardValue1, DealerHandOne));
            JLabel DealerHand1 = new JLabel(setDealer1Hand);
            DealerHand1.setBounds(250, 75, 90, 115);

            //displays Dealer face down card initial
            ImageIcon facedownDealer = new ImageIcon("cards/dealerfacedowncardiniital.png");
            ImageIcon scaledcardfacedown = new ImageIcon(facedownDealer.getImage().getScaledInstance(84, 115, Image.SCALE_DEFAULT));
            JLabel facedownD = new JLabel(scaledcardfacedown);
            facedownD.setBounds(350, 75, 90, 115);

            JLabel DealerTotalHandValue = new JLabel("Dealer Total Hand: " + distributor.getTotal(distributor.getDealerHand()));//displays dealers total hand
            DealerTotalHandValue.setBounds(400, 23, 121, 34);
            DealerTotalHandValue.setForeground(Color.YELLOW);
            JLabel playersTotalHandValue = new JLabel("Player Total Hand: " + distributor.getTotal(distributor.getPlayerHand()));//displays players total hand
            playersTotalHandValue.setBounds(40, 23, 121, 34);
            playersTotalHandValue.setForeground(Color.YELLOW);


            distributor.addChangeListener(e -> { //Observer pattern, changeListener will be notified when the game is over
                JLabel labelResult = new JLabel(distributor.getResult()); //create a label that shows the result of the game
                labelResult.setBounds(350, 100, 200, 150);
                //Add two buttons: play again or return to homescreen


            });

            framePlay.add(buttonHit);
            framePlay.add(buttonStand);
            framePlay.add(playersTotalHandValue);
            framePlay.add(DealerTotalHandValue);
            framePlay.add(PlayerCurrentHand1);
            framePlay.add(PlayerCurrentHand2);
            framePlay.add(DealerHand1);
            framePlay.add(facedownD);
            framePlay.add(labelPlay);
            framePlay.setLayout(null);
            framePlay.setSize(700, 500);//size of playing table
            framePlay.setResizable(false);
            framePlay.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            playButton.addActionListener(e -> {
                framePlay.setVisible(true);
                frameHome.setVisible(false);
            });//displays playing table if play button is clicked
        } catch (Exception e) {
            System.out.println("file not found!");
        }
    }

    public Image DisplayCard(String cardValue, ImageIcon Hand) {
        Random random = new Random();
        // generate random number from  1 to 4 to choose a random card face
        int RNGnumber = random.nextInt(4 - 1 + 1) + 1;


        switch (cardValue) {//displays player card
            case "2":
                String twos = "cards/2/2 (" + RNGnumber + ").png";//chooses random two's card from 2 file
                Hand = new ImageIcon(twos);
                break;
            case "3":
                String threes = "cards/3/3 (" + RNGnumber + ").png";//chooses random two's card from 2 file
                Hand = new ImageIcon(threes);
                break;
            case "4":
                String fours = "cards/4/4 (" + RNGnumber + ").png";//chooses random two's card from 2 file
                Hand = new ImageIcon(fours);
                break;
            case "5":
                String fives = "cards/5/5 (" + RNGnumber + ").png";//chooses random two's card from 2 file
                Hand = new ImageIcon(fives);
                break;
            case "6":
                String sixes = "cards/6/6 (" + RNGnumber + ").png";//chooses random two's card from 2 file
                Hand = new ImageIcon(sixes);
                break;
            case "7":
                String sevens = "cards/7/7 (" + RNGnumber + ").png";//chooses random two's card from 2 file
                Hand = new ImageIcon(sevens);
                break;

            case "8":
                String eights = "cards/8/8 (" + RNGnumber + ").png";//chooses random two's card from 2 file
                Hand = new ImageIcon(eights);
                break;
            case "9":
                String nines = "cards/9/9 (" + RNGnumber + ").png";//chooses random two's card from 2 file
                Hand = new ImageIcon(nines);
                break;
            case "0":
                String tens = "cards/10/10 (" + RNGnumber + ").png";//chooses random two's card from 2 file
                Hand = new ImageIcon(tens);
            case "J": //jack, queen, and king face cards all have a value of 10
                String jacks = "cards/face/jack (" + RNGnumber + ").png";//chooses random two's card from 2 file
                Hand = new ImageIcon(jacks);
                break;
            case "Q":
                String queens = "cards/face/queen (" + RNGnumber + ").png";//chooses random two's card from 2 file
                Hand = new ImageIcon(queens);
                break;
            case "K":
                String kings = "cards/face/king (" + RNGnumber + ").png";//chooses random two's card from 2 file
                Hand = new ImageIcon(kings);
                break;
            case "A":
                String aces = "cards/ace/ace (" + RNGnumber + ").png";//chooses random two's card from 2 file
                Hand = new ImageIcon(aces);
                break;
        }
        return Hand.getImage().getScaledInstance(84, 115, Image.SCALE_DEFAULT);

    }
    /*public void DisplayCard(Card c){
        char face = c.getCardFace();
    }*/
}


