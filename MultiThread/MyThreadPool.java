package MultiThread;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class MyThreadPool {

    /*
    * Problem Statement:

Design and implement a basic thread pool in Java that:

Accepts tasks (Runnable) to execute.

Has a fixed number of worker threads.

Gracefully handles shutdown.

Queues tasks if all threads are busy.
* */

    private final BlockingQueue<Runnable> taskQueue;

    private final Worker[] workers;
    private volatile boolean isShutdown = false;

    public MyThreadPool(int numThreads) {
        taskQueue = new LinkedBlockingQueue<>();
        workers = new Worker[numThreads];

        for (int i = 0; i < numThreads; i++) {
            workers[i] = new Worker("Thread-" + i);
            workers[i].start();
        }
    }


    private class Worker extends Thread {
        public Worker(String name) {
            super(name);
        }

        public void run() {
            while (!isShutdown || !taskQueue.isEmpty()) {
                try {
                    Runnable task = taskQueue.take();
                    task.run();
                } catch (InterruptedException e) {
                    // Allow thread to exit if shutdown is requested
                    if (isShutdown) break;
                }
            }
        }

    }

    public void submit(Runnable task) {
        if (!isShutdown) {
            taskQueue.offer(task);
        }
    }

    public void shutdown() {
        isShutdown = true;
        for (Worker worker : workers) {
            worker.interrupt(); // interrupt blocking take()
        }
    }

    public static void main(String[] args) throws InterruptedException {
        MyThreadPool pool = new MyThreadPool(3);

        for (int i = 0; i < 10; i++) {
            final int taskId = i;
            pool.submit(() -> {
                System.out.println(Thread.currentThread().getName() + " executing task " + taskId);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ignored) {}
            });
        }

        Thread.sleep(5000); // Let some tasks complete
        pool.shutdown();
    }
}
