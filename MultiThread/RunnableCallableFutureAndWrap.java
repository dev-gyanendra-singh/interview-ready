package MultiThread;

import java.util.concurrent.*;

public class RunnableCallableFutureAndWrap {

/*
Creating a Callable task

Wrapping it in a Runnable

Submitting it to an ExecutorService

Retrieving the result via a Future

Also shows running a thread manually with the wrapped Runnable
* */

    public static void main(String[] args) {
        // 1. Define a Callable that returns a value

        Callable<String> callableTask = () -> {
            Thread.sleep(1000);
            return "Callable result after 1 second";
        };

        // 2. Wrap the Callable inside a Runnable (for manual Thread use)
        Runnable wrappedRunnable = () -> {
            try {
                String result = callableTask.call(); // manually calling
                System.out.println("[Thread] Result from wrapped Callable: " + result);
            } catch (Exception e) {
                e.printStackTrace();
            }
        };

        // 3. Run the wrapped Runnable using a Thread
        Thread thread = new Thread(wrappedRunnable);
        thread.start();

        // 4. Submit the Callable to an ExecutorService
        ExecutorService executor = Executors.newSingleThreadExecutor(); //one worker thread.. tasks will be submitted sequentially
        // ExecutorService executor = Executors.newFixedThreadPool(5); reusable pool of 5 threads
        // ExecutorService executor = Executors.newCachedThreadPool(); unbounded thread pool ...Creates new threads as needed, and reuses idle ones... good when threads are short lived
        // ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2); Can schedule tasks to run after a delay or periodically.
        // ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();... like above but single thread.. and sequentially
        // ExecutorService executor = new ThreadPoolExecutor(
        //    2,                   // core pool size
        //    4,                   // max pool size
        //    60L,                 // idle timeout
        //    TimeUnit.SECONDS,
        //    new LinkedBlockingQueue<>()
        //);

/*
* Sequential tasks → newSingleThreadExecutor()

Concurrent limited tasks → newFixedThreadPool(n)

Bursty or short tasks → newCachedThreadPool()

Scheduling tasks → newScheduledThreadPool(n)

Need maximum control → ThreadPoolExecutor*/

        Runnable runnableTask = () -> {
            System.out.println("[Thread] Callable result after 1 second");
        };

        Future<String> future = executor.submit(callableTask);

        try {
            System.out.println("[Main] Waiting for Callable result via Future...");
            String result = future.get(); // blocks until result is available
            System.out.println("[Main] Got result via Future: " + result);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        executor.shutdown();
    }
}
