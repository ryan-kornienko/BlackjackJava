package blackjack;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Run {
    public static void main(String[] args) throws Exception {
        BlockingQueue<Message> queue = new LinkedBlockingQueue<>();
        CardDistributor distributor = new CardDistributor();
        View view = new View(queue, distributor);
        Controller controller = new Controller(queue, view, distributor);
        controller.mainLoop();
    }
}
