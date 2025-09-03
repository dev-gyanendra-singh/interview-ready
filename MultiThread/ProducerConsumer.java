package MultiThread;

import java.util.LinkedList;
import java.util.Queue;

class SharedQueue {
    private final Queue<Integer> queue = new LinkedList<>();
    private final int LIMIT = 5;

    public synchronized void produce(int value) throws InterruptedException {
        while (queue.size() == LIMIT) {
            wait(); // Wait until space is available
        }
        queue.add(value);
        System.out.println("Produced: " + value);
        notify(); // Notify consumer
    }

    public synchronized int consume() throws InterruptedException {
        while (queue.isEmpty()) {
            wait(); // Wait until item is available
        }
        int val = queue.poll();
        System.out.println("Consumed: " + val);
        notify(); // Notify producer
        return val;
    }
}

public class ProducerConsumer {
    public static void main(String[] args) {
        SharedQueue sharedQueue = new SharedQueue();

        Thread producer = new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    sharedQueue.produce(i);
                    Thread.sleep(100);
                }
            } catch (InterruptedException e) {}
        });

        Thread consumer = new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    sharedQueue.consume();
                    Thread.sleep(150);
                }
            } catch (InterruptedException e) {}
        });

        producer.start();
        consumer.start();
    }
}

