package blackjack;

import java.util.concurrent.BlockingQueue;

public class Controller {
    private final BlockingQueue<Message> queue;
    private final View view;
    private final CardDistributor distributor;
    public Controller(BlockingQueue<Message> queue, View view, CardDistributor distributor) {
        this.queue = queue;
        this.view = view;
        this.distributor = distributor;
    }

    public void mainLoop(){
        while (view.isVisible()) {
            Message message = null;
            try {
                message = queue.take();
            } catch (InterruptedException ignored) { }
            assert message != null;
            if (message.getClass() == HitMessage.class) {
                distributor.playerHit();
                view.playerHit();
            } else if (message.getClass() == StandMessage.class) {
                distributor.playDealer();
                view.displayDealerHand();
            }
        }
    }
}
