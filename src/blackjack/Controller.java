package blackjack;

import java.util.concurrent.BlockingQueue;

public class Controller {
    BlockingQueue<Message> queue;
    View view;
    CardDistributor distributor;
    public Controller(BlockingQueue<Message> queue, View view, CardDistributor distributor) {
        this.queue = queue;
        this.view = view;
        this.distributor = distributor;
    }

    public void mainLoop(){
        while(view.isDisplayable()){
            Message message = null;
            try{
                message = queue.take();
            } catch (InterruptedException ignored){ }
            if(message.getClass() == HitMessage.class){
                distributor.playerHit();
            }
            else if(message.getClass() == StandMessage.class){
                distributor.playDealer();
            }
        }
    }
}
