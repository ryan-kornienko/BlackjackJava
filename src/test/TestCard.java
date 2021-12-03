package test;

import blackjack.Card;
import blackjack.CardDistributor;
import org.junit.Test;
import java.util.ArrayList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestCard {
    /**
     * testCardObjects will examine the getValue() and getCardFace() methods in card class
     * @throws Exception if a new card is created with an invalid character
     */
    @Test
    public void testCardObjects() throws Exception {
        Card c = new Card('9'); //checking that the inputted card faces match the proper value
        assertEquals(9,c.getValue());
        assertEquals('9',c.getCardFace());

        c = new Card('T');
        assertEquals(10,c.getValue());
        assertEquals('T',c.getCardFace());

        c = new Card('K');
        assertEquals(10,c.getValue());
        assertEquals('K',c.getCardFace());

        c = new Card('A');
        assertEquals(11,c.getValue());
        assertEquals('A',c.getCardFace());
        c.changeAce(); //checking that changeAce will change the value of an Ace to 1
        assertEquals(1, c.getValue());
        assertEquals('A',c.getCardFace());
    }

    /**
     * testCardDistributor will examine the initialization(), playerHit(), playDealer() and getTotal() methods of CardDistributor
     * @throws Exception if new card is created with an invalid character
     */
    @Test
    public void testCardDistributor() throws Exception {
        CardDistributor distributor = new CardDistributor();
        distributor.initialization();
        assertEquals(2, distributor.getPlayerHand().size()); //checking that playerHand and dealerHand are initialized to two cards
        assertEquals(2, distributor.getDealerHand().size());

        distributor.playerHit(); //checking that hit adds a card to player hand
        assertEquals(3, distributor.getPlayerHand().size());

        distributor.playDealer(); //checking that the dealer's hand is over 16 after playing the dealer
        assertTrue(distributor.getTotal(distributor.getDealerHand()) > 16);

        distributor.gameReset(); //checking that the game result will be a loss of the total is over 21
        while(distributor.getTotal(distributor.getPlayerHand()) < 21){ //hit as long as player hand is under 21
            distributor.playerHit();
        }
        assertTrue(distributor.getResult().equalsIgnoreCase("You Lose! Try Again!")); //result must be a loss since the player is going over 21

        ArrayList<Card> sampleDeck = new ArrayList<>(); //checking that getTotal returns the proper totals
        sampleDeck.add(new Card('K'));
        sampleDeck.add(new Card('A'));
        assertEquals(21, distributor.getTotal(sampleDeck));

        sampleDeck.clear();
        sampleDeck.add(new Card('5'));
        sampleDeck.add(new Card('6'));
        sampleDeck.add(new Card('2'));
        assertEquals(13, distributor.getTotal(sampleDeck));

        sampleDeck.clear();
        sampleDeck.add(new Card('9'));
        sampleDeck.add(new Card('A'));
        sampleDeck.add(new Card('5')); //value of ace will change to 1 after 5 is inserted since total goes over 21
        assertEquals(15, distributor.getTotal(sampleDeck));

        sampleDeck.clear();
        sampleDeck.add(new Card('J'));
        sampleDeck.add(new Card('Q'));
        sampleDeck.add(new Card('K'));
        assertTrue(distributor.getTotal(sampleDeck)>21);
    }
}