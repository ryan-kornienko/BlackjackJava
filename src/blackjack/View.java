package blackjack;

import javax.swing.*;
import java.awt.*;
import java.util.Random;
import java.util.concurrent.BlockingQueue;

/**
 * View class creates and facilitates all elements associated with UI and java swing
 * View object will initialize all UI elements and establish functionality for each button
 * initializePlayerHand and initializeDealerHand are private functions that display the first two cards of each hand
 * playerHit and displayDealerHand are functions used by controller when hit and stand buttons are pushed
 * displayCard is a private function that returns the image icon of the specified card
 */
public class View extends JFrame {
    private final JLabel playersTotalHandValue;
    private final JLabel dealerTotalHandValue;
    private final JLabel playerFirstCard; private final JLabel playerSecondCard; private final JLabel playerThirdCard; private final JLabel playerFourthCard; private final JLabel playerFifthCard;
    private final JLabel dealerFirstCard; private final JLabel dealerSecondCard; private final JLabel dealerThirdCard; private final JLabel dealerFourthCard; private final JLabel dealerFifthCard;
    private final CardDistributor distributor;
    private boolean firstTime;
    private int playerIndex;

    /**
     * View class creates and facilitates all elements associated with UI and java swing
     * @param queue is the BlockingQueue object that is shared with Controller so that View and Controller can communicate with one another by passing messages
     * @param distributor is the CardDistributor object that is shared with the Controller so that View can update UI based on what is contained in the CardDistributor object
     */
    public View(BlockingQueue<Message> queue, CardDistributor distributor) {
        super("Blackjack");
        this.distributor = distributor;

        JLabel homeBackDrop = new JLabel(new ImageIcon("homepicproject151.png")); //home screen backdrop
        homeBackDrop.setBounds(0, 0, 800, 700);
        JLabel instructionsBackDrop = new JLabel(new ImageIcon("InstructionsforBlackJack.png")); //instructions backdrop
        instructionsBackDrop.setBounds(0, 0, 818, 567);
        JLabel playTableBackDrop = new JLabel(new ImageIcon("bjtable.jpg")); //playing table backdrop
        playTableBackDrop.setBounds(-85, -40, 850, 550);//size of background picture

        JButton buttonPlay = new JButton("Play!"); //creating play button
        buttonPlay.setBackground(new Color(128, 0, 0));
        buttonPlay.setForeground(Color.WHITE);
        buttonPlay.setFocusPainted(false);
        buttonPlay.setFont(new Font("Tahoma", Font.BOLD, 12));
        buttonPlay.setBounds(35, 365, 125, 50);

        JButton returnBtn = new JButton("Return to Home"); //creating return to home button
        returnBtn.setBackground(new Color(255, 208, 23));
        returnBtn.setForeground(Color.BLACK);
        returnBtn.setFocusPainted(false);
        returnBtn.setFont(new Font("Tahoma", Font.BOLD, 12));
        returnBtn.setBounds(570,450,135, 50);

        JButton buttonInstructions = new JButton("How to Play"); //creating How To Play/Instructions button
        buttonInstructions.setBackground(new Color(128, 0, 0));
        buttonInstructions.setForeground(Color.WHITE);
        buttonInstructions.setFocusPainted(false);
        buttonInstructions.setFont(new Font("Tahoma", Font.BOLD, 12));
        buttonInstructions.setBounds(245, 365, 125, 50);

        JButton buttonHit = new JButton("Hit!"); //creating hit button
        buttonHit.setBackground(new Color(7, 185, 13));
        buttonHit.setForeground(Color.WHITE);
        buttonHit.setFocusPainted(false);
        buttonHit.setFont(new Font("Tahoma", Font.BOLD, 12));
        buttonHit.setBounds(190, 375, 125, 50);

        JButton buttonStand = new JButton("Stand!"); //creating stand button
        buttonStand.setBackground(new Color(188, 23, 23));
        buttonStand.setForeground(Color.white);
        buttonStand.setFocusPainted(false);
        buttonStand.setFont(new Font("Tahoma", Font.BOLD, 12));
        buttonStand.setBounds(380, 375, 125, 50); //dimensions of play button

        JButton buttonReturnHome = new JButton("Return to Home"); //creating return to home button
        buttonReturnHome.setBackground(new Color(128, 0, 0));
        buttonReturnHome.setForeground(Color.WHITE);
        buttonReturnHome.setFocusPainted(false);
        buttonReturnHome.setFont(new Font("Tahoma", Font.BOLD, 12));
        buttonReturnHome.setBounds(380, 375, 135, 50);

        JButton buttonPlayAgain = new JButton("Play Again"); //create play again button
        buttonPlayAgain.setBackground(new Color(128, 0, 0));
        buttonPlayAgain.setForeground(Color.WHITE);
        buttonPlayAgain.setFocusPainted(false);
        buttonPlayAgain.setFont(new Font("Tahoma", Font.BOLD, 12));
        buttonPlayAgain.setBounds(190, 375, 125, 50);

        JLabel labelResult = new JLabel(" "); //creating result label
        labelResult.setFont(new Font("Tahoma", Font.BOLD, 12));
        labelResult.setBounds(450, 12, 150, 50);
        labelResult.setForeground(Color.YELLOW);
        playersTotalHandValue = new JLabel(" "); //initializing player's hand value label
        playersTotalHandValue.setFont(new Font("Tahoma", Font.BOLD, 12));
        playersTotalHandValue.setBounds(40, 20, 200, 34);
        playersTotalHandValue.setForeground(Color.YELLOW);
        dealerTotalHandValue = new JLabel(" "); //initializing dealer's hand value label
        dealerTotalHandValue.setFont(new Font("Tahoma", Font.BOLD, 12));
        dealerTotalHandValue.setBounds(250, 20, 200, 34);
        dealerTotalHandValue.setForeground(Color.YELLOW);
        //all five possible card slots are created for player
        playerFirstCard = new JLabel();
        playerFirstCard.setBounds(50, 240, 90, 115);
        playerSecondCard = new JLabel();
        playerSecondCard.setBounds(150, 240, 90, 115);
        playerThirdCard = new JLabel();
        playerThirdCard.setBounds(250, 240, 90, 115);
        playerFourthCard = new JLabel();
        playerFourthCard.setBounds(350, 240, 90, 115);
        playerFifthCard = new JLabel();
        playerFifthCard.setBounds(450, 240, 90, 115);
        //all five possible card slots are created for player
        dealerFirstCard = new JLabel();
        dealerFirstCard.setBounds(50, 100, 90, 115);
        dealerSecondCard = new JLabel();
        dealerSecondCard.setBounds(150, 100, 90, 115);
        dealerThirdCard = new JLabel();
        dealerThirdCard.setBounds(250, 100, 90, 115);
        dealerFourthCard = new JLabel();
        dealerFourthCard.setBounds(350, 100, 90, 115);
        dealerFifthCard = new JLabel();
        dealerFifthCard.setBounds(450, 100, 90, 115);

        buttonInstructions.addActionListener(e -> { //displays instructions when clicked
            buttonPlay.setVisible(false); //adds all instruction UI elements and removes all HomeScreen UI elements
            buttonInstructions.setVisible(false);
            homeBackDrop.setVisible(false);
            returnBtn.setVisible(true);
            instructionsBackDrop.setVisible(true);
            this.setSize(818, 595);
        });

        returnBtn.addActionListener(e->{ //returns to home screen when clicked
            returnBtn.setVisible(false); //adds all HomeScreen UI elements and removes all instruction UI elements
            instructionsBackDrop.setVisible(false);
            buttonPlay.setVisible(true);
            buttonInstructions.setVisible(true);
            homeBackDrop.setVisible(true);
            this.setSize(424, 550);
        });

        firstTime = true;
        buttonPlay.addActionListener(e -> { //takes user from homescreen to playing table when clicked
            if(firstTime) { //only initialize the first time this is clicked
                try { //when user clicks game over or goes home after playing, gameOver is called which re-initializes the deck & hands
                    distributor.initialization();
                } catch (Exception ignored) { }
                firstTime = false;
            }
            initializePlayerHand(); //initalize playerHand and dealerHand
            initializeDealerHand();
            buttonPlay.setVisible(false); //adds all playing table UI elements and removes all homescreen UI elements
            buttonInstructions.setVisible(false);
            homeBackDrop.setVisible(false);
            buttonHit.setVisible(true);
            buttonStand.setVisible(true);
            playTableBackDrop.setVisible(true);
            playerFirstCard.setVisible(true);
            dealerTotalHandValue.setVisible(true);
            playersTotalHandValue.setVisible(true);
            this.setSize(695, 498);
        });

        buttonHit.addActionListener(e -> { //when clicked view sends hit message to blocking queue
            HitMessage msg = new HitMessage();
            try { queue.put(msg); }
            catch (InterruptedException ignored) { }
        });

        buttonStand.addActionListener(e -> { //when clicked view sends stand message to blocking queue
            StandMessage msg = new StandMessage();
            try { queue.put(msg); }
            catch (InterruptedException ignored) { }
        });

        buttonReturnHome.addActionListener(e-> { //when clicked takes user from playing table to home screen
            try {
                distributor.gameReset();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            buttonHit.setVisible(false); //adds all homescreen UI elements and removes all playing table UI elements
            buttonStand.setVisible(false);
            buttonReturnHome.setVisible(false);
            buttonPlayAgain.setVisible(false);
            playerFirstCard.setVisible(false);
            playerSecondCard.setVisible(false);
            playerThirdCard.setVisible(false);
            playerFourthCard.setVisible(false);
            playerFifthCard.setVisible(false);
            dealerFirstCard.setVisible(false);
            dealerSecondCard.setVisible(false);
            dealerThirdCard.setVisible(false);
            dealerFourthCard.setVisible(false);
            dealerFifthCard.setVisible(false);
            labelResult.setVisible(false);
            playersTotalHandValue.setVisible(false);
            dealerTotalHandValue.setVisible(false);
            buttonPlay.setVisible(true);
            buttonInstructions.setVisible(true);
            homeBackDrop.setVisible(true);
            this.setSize(424, 550);
        });

        buttonPlayAgain.addActionListener(e-> { //when clicked playing table will reset for a new game
            try {
                distributor.gameReset();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            buttonHit.setVisible(true); //hit & stand buttons become visible again
            buttonStand.setVisible(true);
            buttonPlayAgain.setVisible(false); //playAgain & returnHome buttons are set invisible
            buttonReturnHome.setVisible(false);
            labelResult.setVisible(false); //remove result label
            playersTotalHandValue.setVisible(false); //remove all passed index 0 & 1
            dealerThirdCard.setVisible(false);
            dealerFourthCard.setVisible(false);
            dealerFifthCard.setVisible(false);
            playerThirdCard.setVisible(false);
            playerFourthCard.setVisible(false);
            playerFifthCard.setVisible(false);
            initializeDealerHand(); //initialize new player and dealer hands
            initializePlayerHand();
        });

        distributor.addChangeListener(e -> { //Observer pattern, changeListener will be notified when the game is over
            labelResult.setText(distributor.getResult());
            buttonHit.setVisible(false);
            buttonStand.setVisible(false);
            buttonReturnHome.setVisible(true);
            buttonPlayAgain.setVisible(true);
            labelResult.setVisible(true);
        });

        //adding all elements to the UI and only setting home screen elements visible at the moment
        this.add(playerFirstCard);
        playerFirstCard.setVisible(false);
        this.add(playerSecondCard);
        playerSecondCard.setVisible(false);
        this.add(playerThirdCard);
        playerThirdCard.setVisible(false);
        this.add(playerFourthCard);
        playerFourthCard.setVisible(false);
        this.add(playerFifthCard);
        playerFifthCard.setVisible(false);
        this.add(dealerFirstCard);
        dealerFirstCard.setVisible(false);
        this.add(dealerSecondCard);
        dealerSecondCard.setVisible(false);
        this.add(dealerThirdCard);
        dealerThirdCard.setVisible(false);
        this.add(dealerFourthCard);
        dealerFourthCard.setVisible(false);
        this.add(dealerFifthCard);
        dealerFifthCard.setVisible(false);
        this.add(labelResult);
        labelResult.setVisible(false);
        this.add(buttonPlay);
        this.add(buttonInstructions);
        this.add(returnBtn);
        returnBtn.setVisible(false);
        this.add(instructionsBackDrop);
        instructionsBackDrop.setVisible(false);
        this.add(buttonReturnHome);
        buttonReturnHome.setVisible(false);
        this.add(buttonPlayAgain);
        buttonPlayAgain.setVisible(false);
        this.add(buttonHit);
        buttonHit.setVisible(false);
        this.add(buttonStand);
        buttonStand.setVisible(false);
        this.add(playersTotalHandValue);
        playersTotalHandValue.setVisible(false);
        this.add(dealerTotalHandValue);
        dealerTotalHandValue.setVisible(false);
        this.add(homeBackDrop);
        this.add(playTableBackDrop);
        playTableBackDrop.setVisible(false);

        //setting default attributes to the frame
        this.setLayout(null);
        this.setSize(424, 550);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
    }

    /**
     * displayCard method will create the image of the specified card value creating the filepath to specified card value and creating a scaled instance of said card image, can only be used within View
     * @param cardValue is the specified card value that needs to be shown in the UI, method will create a filepath to specified file path and a scaled instance of said card image will be created
     * @return Image is the Image of the specified card value that is scaled to the proper size
     */
    private Image displayCard(char cardValue) {
        Random random = new Random();
        // generate random number from  1 to 4 to choose a random card face
        int RNGnumber = random.nextInt(4) + 1;
        String filePath = "";
        switch (cardValue) {//displays player card
            case '2':
                filePath = "cards/2/2 (" + RNGnumber + ").png";//chooses random two's card from 2 file
                break;
            case '3':
                filePath = "cards/3/3 (" + RNGnumber + ").png";//chooses random two's card from 2 file
                break;
            case '4':
                filePath = "cards/4/4 (" + RNGnumber + ").png";//chooses random two's card from 2 file
                break;
            case '5':
                filePath = "cards/5/5 (" + RNGnumber + ").png";//chooses random two's card from 2 file
                break;
            case '6':
                filePath = "cards/6/6 (" + RNGnumber + ").png";//chooses random two's card from 2 file
                break;
            case '7':
                filePath = "cards/7/7 (" + RNGnumber + ").png";//chooses random two's card from 2 file
                break;
            case '8':
                filePath = "cards/8/8 (" + RNGnumber + ").png";//chooses random two's card from 2 file
                break;
            case '9':
                filePath = "cards/9/9 (" + RNGnumber + ").png";//chooses random two's card from 2 file
                break;
            case 'T':
                filePath = "cards/10/10 (" + RNGnumber + ").png";//chooses random two's card from 2 file
                break;
            case 'J': //jack, queen, and king face cards all have a value of 10
                filePath = "cards/face/jack (" + RNGnumber + ").png";//chooses random two's card from 2 file
                break;
            case 'Q':
                filePath = "cards/face/queen (" + RNGnumber + ").png";//chooses random two's card from 2 file
                break;
            case 'K':
                filePath = "cards/face/king (" + RNGnumber + ").png";//chooses random two's card from 2 file
                break;
            case 'A':
                filePath = "cards/ace/ace (" + RNGnumber + ").png";//chooses random two's card from 2 file
                break;
        }
        return new ImageIcon(filePath).getImage().getScaledInstance(84, 115, Image.SCALE_DEFAULT);

    }

    /**
     * initializePlayerHand is a private method that is used by view to display the first two cards of the player's hand to the UI and display the total of the player's hand so far
     * this is called twice in view when the player goes from the home screen to the playing table and when the user clicks reset game to initialize a new game
     */
    private void initializePlayerHand(){
        playerIndex = 0; //initialize the first index at 0
        playersTotalHandValue.setText("Player Total Hand Value: " + distributor.getTotal(distributor.getPlayerHand()));//initialize the players total hand value to the current total
        playersTotalHandValue.setVisible(true);
        char cardValue1 = distributor.getPlayerHand().get(playerIndex).getCardFace(); //displays Player card 1
        playerFirstCard.setIcon(new ImageIcon(displayCard(cardValue1)));
        playerFirstCard.setVisible(true);
        playerIndex++;
        char cardValue2 = distributor.getPlayerHand().get(playerIndex).getCardFace(); //displays Player card 2
        playerSecondCard.setIcon(new ImageIcon(displayCard(cardValue2)));
        playerSecondCard.setVisible(true);
        playerIndex++;
    }

    /**
     * initializeDealerHand is a private method that is used by view to display the first card of the dealer's hand and a face down card for the second card to the UI; also displays the total hand value to "..." since the user isn't supposed to know yet
     * this is called twice in view when the player goes from the home screen to the playing table and when the user clicks reset game to initialize a new game
     */
    private void initializeDealerHand() {
        dealerTotalHandValue.setText("Dealer Total Hand Value: ..."); //initialize text of dealer hand value to not show the total yet
        dealerTotalHandValue.setVisible(true);
        char dealerCardValue1 = distributor.getDealerHand().get(0).getCardFace();//grabs dealers first card and sets the icon to visible
        dealerFirstCard.setIcon(new ImageIcon(displayCard(dealerCardValue1)));
        dealerFirstCard.setVisible(true);
        ImageIcon faceDownDealer = new ImageIcon("cards/dealerfacedowncardiniital.png"); //sets the second card to a face down image since the dealer only shows one card at first
        dealerSecondCard.setIcon(new ImageIcon(faceDownDealer.getImage().getScaledInstance(84, 115, Image.SCALE_DEFAULT)));
        dealerSecondCard.setVisible(true);
    }

    /**
     * playerHit is a protected method that will display the next card in the players hand after they want to hit (take another card) and updates the players score
     */
    protected void playerHit(){
        playersTotalHandValue.setText("Player Total Hand Value: " + distributor.getTotal(distributor.getPlayerHand())); //update the total
        try {
            char newCardValue = distributor.getPlayerHand().get(playerIndex).getCardFace(); //get value of the next card
            if(playerIndex == 2) { //set the proper card in the UI based on which index it is currently on
                playerThirdCard.setIcon(new ImageIcon(displayCard(newCardValue)));
                playerThirdCard.setVisible(true);
            } else if(playerIndex == 3) {
                playerFourthCard.setIcon(new ImageIcon(displayCard(newCardValue)));
                playerFourthCard.setVisible(true);
            } else if(playerIndex == 4) {
                playerFifthCard.setIcon(new ImageIcon(displayCard(newCardValue)));
                playerFifthCard.setVisible(true);
            }
            playerIndex++; //increase player index
        } catch (IndexOutOfBoundsException e){
            System.out.println("Index out of bounds");
        }

    }

    /**
     * displayDealerHand will display the rest of the dealers hand. The face down card will be replaced by the face up card and if there are anymore cards in the hand, it will display the rest of them
     * it also displays the value of the dealer's hand
     */
    protected void displayDealerHand(){
        dealerTotalHandValue.setText("Dealer Total Hand Value: " + distributor.getTotal(distributor.getDealerHand())); //update the dealers total
        dealerSecondCard.setIcon(new ImageIcon(displayCard(distributor.getDealerHand().get(1).getCardFace()))); //display the second card in the deck
        dealerSecondCard.setVisible(true); //index is not checked bc the deck is guaranteed to have at least two cards
        for(int i = 2; i < distributor.getDealerHand().size(); i++){ //loop through all cards remaining in dealer hand
            char dealerCardNextValue = distributor.getDealerHand().get(i).getCardFace();
            if(i == 2) { //set the proper card in the UI based on which index it is currently on
                dealerThirdCard.setIcon(new ImageIcon(displayCard(dealerCardNextValue)));
                dealerThirdCard.setVisible(true);
            } else if(i == 3) {
                dealerFourthCard.setIcon(new ImageIcon(displayCard(dealerCardNextValue)));
                dealerFourthCard.setVisible(true);
            } else if(i == 4) {
                dealerFifthCard.setIcon(new ImageIcon(displayCard(dealerCardNextValue)));
                dealerFifthCard.setVisible(true);
            }
        }
    }
}

