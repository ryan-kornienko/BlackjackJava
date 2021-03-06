package blackjack;

import javax.swing.event.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * CardDistributor will manage the 52 card deck and the hands of the player and dealer
 */
public class CardDistributor {
    private final ArrayList<ChangeListener> listeners; //list of listeners that are added a CardDistributor object
    private final ArrayList<Card> deck; //collection of 52 cards, four of each possible CardFace
    private final ArrayList<Card> playerHand; //list of the cards in the player's hand
    private final ArrayList<Card> dealerHand; //list of the cards in the dealer's hand
    char[] faces = {'2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J', 'Q', 'K', 'A'}; //list of all possible faces for the card
    Random randomizer; //Random object that is responsible for generating random indexes to remove from the deck
    String result; //String object that stores the result as a string that can be used by the UI

    /**
     * CardDistributor constructor will initialize the list of listeners, deck of cards, player/dealer hands, and randomizer object
     */
    public CardDistributor() {
        listeners = new ArrayList<>(); //initialize array of listeners, the deck of cards, and dealer and player hands
        deck = new ArrayList<>();
        dealerHand = new ArrayList<>();
        playerHand = new ArrayList<>();
        randomizer = new Random();
    }

    /**
     * initialization will create the deck of cards containing 4 of each card face
     * also initializes player and dealer hands by removing two cards for each hand at a random index
     * @throws Exception if the character for a new card is invalid
     */
    public void initialization () throws Exception {
        for (char face : faces) { //fill the deck with four of each face
            for (int i = 0; i < 4; i++)
                deck.add(new Card(face));
        }
        playerHand.add(deck.remove(randomizer.nextInt(deck.size()))); //deal two cards to each player hand and dealer hand
        dealerHand.add(deck.remove(randomizer.nextInt(deck.size()))); //cards are removed from the deck so that there are no multiples
        playerHand.add(deck.remove(randomizer.nextInt(deck.size()))); //they are removed by generating a random index in the range of the current size of the deck
        dealerHand.add(deck.remove(randomizer.nextInt(deck.size())));
    }

    /**
     * playerHit will add another card to the players hand by removing a random index from the deck
     * if the resulting total of the player hand is over 21 then the player folds and the result is calculated
     */
    public void playerHit(){
        playerHand.add(deck.remove(randomizer.nextInt(deck.size()))); //player is dealt a random card from the deck which is removed
        if(getTotal(playerHand) > 21) //checks if the total of the player's hand is over 21
            calcResult(); //if their hand is over 21, then the game is over and the program calculates the result (automatic loss)
    }

    /**
     * getTotal will calculate the total of the inputted hand
     * @param cards is the specified hand that needs to be calculated
     * @return total of the hand as an integer
     */
    public int getTotal(ArrayList<Card> cards) {
        int total = 0;
        for (Card c : cards) //adding the value of each card
            total += c.getValue();
        for(Card c : cards){ //handling the ace being 11 or 1, by default ace is 11 but is reduced to 1 if the players hand is over 21
            if(c.getCardFace() == 'A' && c.getValue() == 11 && total > 21){ //the card must have a face value of A, the current value of the card is 11 and total must be over 21 in order for the Ace to be converted to a 1
                total = total - 10; //total is updated to resemble the value being 1
                c.changeAce(); //Card's value is updated to 1
            }
        }
        return total;
    }

    /**
     * playDealer will play out the rest of the dealer's hand by hitting until the dealers hand is greater than 17
     */
    public void playDealer(){
        while (getTotal(dealerHand) < 17) //dealer will hit until it's total is over 17
            dealerHand.add(deck.remove(randomizer.nextInt(deck.size())));
        calcResult(); //dealer being done signifies the end of the game so the game result will get calculated
    }

    /**
     * gameReset will clear deck, player hand, dealer hand and initialize new cards again
     * @throws Exception will be thrown if an invalid character is inputted when deck is created
     */
    public void gameReset() throws Exception {
        deck.clear(); //clear the deck and hands
        playerHand.clear();
        dealerHand.clear();
        this.initialization(); //initialize a new deck
    }

    /**
     * calcResult will calculate the result of the game based on the dealer and player totals
     */
    public void calcResult(){
        int dealerTot = getTotal(dealerHand); //retrieving the totals of the player and dealer
        int playerTot = getTotal(playerHand);
        if((playerTot > 21 || dealerTot > playerTot) && dealerTot <= 21) //player loses if the player folds (over 21) or if the dealer has a better/higher hand than the player and the dealer has a hand less than or equal to 21
            result = "You Lose! Try Again!";
        else if(dealerTot > 21 || dealerTot < playerTot) //player wins if the dealer folds (over 21) or if the player has a better/higher hand than the dealer
            result = "You Win! Great Job!";
        else //player and dealer draw if neither hands are better than each other
            result =  "Draw! Try Again!";
        ChangeEvent event = new ChangeEvent(this); //listeners will be notified when the game is over
        for(ChangeListener c : listeners) {
            c.stateChanged(event);
        }
    }

    /**
     * getPlayerHand will return the player's hand
     * @return player's hand as an ArrayList
     */
    public ArrayList<Card> getPlayerHand(){
        return (ArrayList<Card>) playerHand.clone();
    }

    /**
     * getDealerHand will return the dealer's hand
     * @return dealer's hand as an ArrayList
     */
    public ArrayList<Card> getDealerHand(){
        return (ArrayList<Card>) dealerHand.clone();
    }

    /**
     * getResult will return the result of the game
     * @return result is the restult of the game as a string
     */
    public String getResult(){
        return result;
    }

    /**
     * addChangeListener will add the listener to the listeners list that will be notified when the game is over
     * @param listener is a listener object to be added to the list of listeners
     */
    public void addChangeListener(ChangeListener listener){
        listeners.add(listener);
    }
}
