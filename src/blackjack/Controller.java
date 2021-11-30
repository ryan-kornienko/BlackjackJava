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
        while(view.frameHome.isVisible() || view.frameInstructions.isVisible() || view.framePlay.isVisible()) {
            if(view.frameHome.isVisible()) {
                while (view.framePlay.isVisible()) {
                    Message message = null;
                    try {
                        message = queue.take();
                        System.out.println("Take");
                    } catch (InterruptedException ignored) {
                        System.out.println("ERROR");
                    }
                    assert message != null;
                    if (message.getClass() == HitMessage.class) {
                        distributor.playerHit();
                        System.out.println("Controller Hit");
                    } else if (message.getClass() == StandMessage.class) {
                        distributor.playDealer();
                        System.out.println("Controller Stand");
                    }
                }
            }
        }
    }
}
