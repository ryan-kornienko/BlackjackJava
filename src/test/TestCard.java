package test;

import blackjack.Card;
import blackjack.CardDistributor;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestCard
{
    @Test
    public void testCardObjects() throws Exception {
        Card c = new Card('1');
        assertEquals(1,c.getValue());
        assertEquals('1',c.getCardFace());

        c = new Card('9');
        assertEquals(9,c.getValue());
        assertEquals('9',c.getCardFace());
        assertTrue(1 != c.getValue());

        c = new Card('0');
        assertEquals(10,c.getValue());
        assertEquals('0',c.getCardFace());
        assertTrue('9' != c.getCardFace());

        c = new Card('k');
        assertEquals(10,c.getValue());
        assertEquals('k',c.getCardFace());

        c = new Card('a');
        assertEquals(11,c.getValue());
        assertEquals('a',c.getCardFace());
    }

    @Test
    public void testCardDistributor() throws Exception {
        CardDistributor distributor = new CardDistributor();
        distributor.initialization();
        ArrayList<Card> player = distributor.getPlayerHand();
        ArrayList<Card> dealer = distributor.getDealerHand();
    }
}