package blackjack;

import javax.swing.event.*;
import java.util.ArrayList;

public class CardDistributor {
    private ArrayList<ChangeListener> listeners;

    public void addChangeListener(ChangeListener listener){
        listeners.add(listener);
    }

    public CardDistributor(){
        listeners = new ArrayList<>();
    }

    public ArrayList<Card> Initialization () {
        return null;
    }

    public Card hit(){
        return null;
    }

    public int getTotal(ArrayList<Card> cards){
        return 0;
    }

    public void gameReset(){

    }
    public void promptLoss(){
        ChangeEvent event = new ChangeEvent(this); //listeners will be notified when the game is over
        for(ChangeListener c : listeners) {
            c.stateChanged(event);
        }
    }
}
