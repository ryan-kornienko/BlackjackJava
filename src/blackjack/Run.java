package blackjack;

import java.util.concurrent.*;

/**
 * Run will initialize all shared objects between the BlockingQueue, CardDistributor, View, and Controller, and run the mainLoop on the controller that will start the UI to play BlackJack
 */
public class Run {
    public static void main(String[] args) {
        BlockingQueue<Message> queue = new LinkedBlockingQueue<>();
        CardDistributor distributor = new CardDistributor();
        View view = new View(queue, distributor);
        Controller controller = new Controller(queue, view, distributor);
        controller.mainLoop();
    }
}
