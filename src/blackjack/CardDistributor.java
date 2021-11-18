package blackjack;

import javax.swing.event.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class CardDistributor {
    private ArrayList<ChangeListener> listeners; //list of listeners that are added a CardDistributor object
    private ArrayList<Card> deck; //collection of 52 cards, four of each possible CardFace
    private ArrayList<Card> playerHand; //list of the cards in the player's hand
    private ArrayList<Card> dealerHand; //list of the cards in the dealer's hand
    char[] faces = {'2', '3', '4', '5', '6', '7', '8', '9', '0', 'J', 'Q', 'K', 'A'}; //list of all possible faces for the card
    Random randomizer; //Random object that is responsible for generating random indexes to remove from the deck
    String result; //String object that stores the result as a string that can be used by the UI

    public void addChangeListener(ChangeListener listener){
        listeners.add(listener);
    }

    public CardDistributor() {
        listeners = new ArrayList<>(); //initialize array of listeners, the deck of cards, and dealer and player hands
        deck = new ArrayList<>();
        dealerHand = new ArrayList<>();
        playerHand = new ArrayList<>();
        randomizer = new Random();
    }

    public void initialization () throws Exception {
        for(int j = 0; j < faces.length; j++) //fill the deck with four of each face
            for(int i = 0; i < 4; i++)
                deck.add(new Card(faces[j]));
        playerHand.add(deck.remove(randomizer.nextInt(deck.size()))); //deal two cards to each player hand and dealer hand
        dealerHand.add(deck.remove(randomizer.nextInt(deck.size()))); //cards are removed from the deck so that there are no multiples
        playerHand.add(deck.remove(randomizer.nextInt(deck.size()))); //they are removed by generating a random index in the range of the current size of the deck
        dealerHand.add(deck.remove(randomizer.nextInt(deck.size())));
    }

    public void playerHit(){
        playerHand.add(deck.remove(randomizer.nextInt(deck.size()))); //player is dealt a random card from the deck which is removed
        if(getTotal(playerHand) > 21) //checks if the total of the player's hand is over 21
            calcResult(); //if their hand is over 21, then the game is over and the program calculates the result (automatic loss)
    }

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

    public void playDealer(){
        while (getTotal(dealerHand) < 17) //dealer will hit until it's total is over 17
            dealerHand.add(deck.remove(randomizer.nextInt(deck.size())));
        calcResult(); //dealer being done signifies the end of the game so the game result will get calculated
    }

    public void gameReset() throws Exception {
        deck.clear(); //clear the deck and hands
        playerHand.clear();
        dealerHand.clear();
        this.initialization(); //initialize a new deck
    }

    public void calcResult(){
        int dealerTot = getTotal(dealerHand); //retrieving the totals of the player and dealer
        int playerTot = getTotal(playerHand);
        if((playerTot > 21 || dealerTot > playerTot) && dealerTot <= 21) //player loses if the player folds (over 21) or if the dealer has a better/higher hand than the player and the dealer has a hand less than or equal to 21
            result = "You Lose\nTry Again!";
        else if(dealerTot > 21 || dealerTot < playerTot) //player wins if the dealer folds (over 21) or if the player has a better/higher hand than the dealer
            result = "You Win\nGreat Job!";
        else //player and dealer draw if neither hands are better than each other
            result =  "Draw\nTry Again!";
        ChangeEvent event = new ChangeEvent(this); //listeners will be notified when the game is over
        for(ChangeListener c : listeners) {
            c.stateChanged(event);
        }
    }

    public ArrayList<Card> getPlayerHand(){
        return (ArrayList<Card>) playerHand.clone();
    }
    public ArrayList<Card> getDealerHand(){
        return (ArrayList<Card>) dealerHand.clone();
    }
    public String getResult(){
        return result;
    }

    public static void main(String[] args) throws Exception {
        CardDistributor distributor = new CardDistributor();
        distributor.initialization();
        System.out.println("Player Hand: " + distributor.getPlayerHand());
        System.out.println("Player total: " + distributor.getTotal(distributor.getPlayerHand()));

        System.out.println("Dealer First Card: " + distributor.getDealerHand().get(0));
        System.out.println("Enter H for hit S for stand");
        Scanner s = new Scanner(System.in);
        String choice = s.next();
        while(!choice.equals("S")) {
            if (choice.equals("H")) {
                distributor.playerHit();
                System.out.println("*HIT*\nNew Player Hand: " + distributor.getPlayerHand());
                System.out.println("New Player total: " + distributor.getTotal(distributor.getPlayerHand()));
                if(distributor.getTotal(distributor.getPlayerHand()) > 21)
                    break;
            }
            choice = s.next();
        }
        System.out.println("Final Player total: " + distributor.getTotal(distributor.getPlayerHand()));
        System.out.println("Dealer Hand: " + distributor.getDealerHand());
        System.out.println("Dealer total: " + distributor.getTotal(distributor.getDealerHand()));
        distributor.playDealer();
        System.out.println("Final Dealer Hand: " + distributor.getDealerHand());
        System.out.println("Final Dealer total: " + distributor.getTotal(distributor.getDealerHand()));
        System.out.print(distributor.getResult());
    }
}
