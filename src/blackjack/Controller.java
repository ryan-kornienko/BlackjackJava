package blackjack;

import java.util.concurrent.BlockingQueue;

/**
 * Controller class will facilitate all interaction between view and model (cardDistributor)
 * mainLoop will cycle as long as the view is visible waiting for a hit or stand action to occur
 */
public class Controller {
    private final BlockingQueue<Message> queue;
    private final View view;
    private final CardDistributor distributor;

    /**
     * Controller object will initialize all items need to facilitate interaction
     * @param queue is the same blocking queue that is shared with view, is used to put and take messages into the queue based on events from the UI
     * @param view is the view object created for the UI what is used to call methods on after certain events
     * @param distributor is the same cardDistributor object is shared with view, controller will change the cardDistributor object
     */
    public Controller(BlockingQueue<Message> queue, View view, CardDistributor distributor) {
        this.queue = queue;
        this.view = view;
        this.distributor = distributor;
    }

    /**
     * mainLoop will cycle through as long as the view is visible to wait for a hit or stand message
     * if a message is sent, then the class of the message is checked and actions are preformed based on what type of message it is
     */
    public void mainLoop(){
        while (view.isVisible()) {
            Message message = null;
            try {
                message = queue.take();
            } catch (InterruptedException ignored) { }
            assert message != null;
            if (message.getClass() == HitMessage.class) { //if the new message is a HitMessage, the controller calls playerHit on distributor and view
                distributor.playerHit();
                view.playerHit();
            } else if (message.getClass() == StandMessage.class) { //if the new message is a StandMessage, the controller calls play Dealer on distributor and display dealer hand on view
                distributor.playDealer();
                view.displayDealerHand();
            }
        }
    }
}
