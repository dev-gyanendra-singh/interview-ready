package MultiThread;

import java.util.List;
import java.util.Collection;
import java.util.concurrent.*;


// ExecutorService is more complex, but here is a very simple example that supports submit and shutdown:
public class SampleExecutorService implements ExecutorService {
    private volatile boolean shutdown = false;

    @Override
    public void execute(Runnable command) {
        if (shutdown) throw new RejectedExecutionException("Executor is shut down");
        new Thread(command).start();
    }

    @Override
    public <T> Future<T> submit(Callable<T> task) {
        if (shutdown) throw new RejectedExecutionException("Executor is shut down");
        FutureTask<T> future = new FutureTask<>(task);
        execute(future);
        return future;
    }

    // Implement submit(Runnable) to avoid UnsupportedOperationException
    @Override
    public Future<?> submit(Runnable task) {
        if (shutdown) throw new RejectedExecutionException("Executor is shut down");
        FutureTask<?> future = new FutureTask<>(task, null);
        execute(future);
        return future;
    }

    // Optional: submit(Runnable, result)
    @Override
    public <T> Future<T> submit(Runnable task, T result) {
        if (shutdown) throw new RejectedExecutionException("Executor is shut down");
        FutureTask<T> future = new FutureTask<>(task, result);
        execute(future);
        return future;
    }

    @Override
    public void shutdown() {
        shutdown = true;
    }

    @Override
    public List<Runnable> shutdownNow() {
        shutdown = true;
        // No queue to clear, so return empty list
        return new java.util.ArrayList<>();
    }

    @Override
    public boolean isShutdown() {
        return shutdown;
    }

    @Override
    public boolean isTerminated() {
        // Simplification: treat shutdown as terminated
        return shutdown;
    }

    @Override
    public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
        // Simplification: no real waiting, just return shutdown status
        return shutdown;
    }

    // Leave invokeAll, invokeAny unsupported for brevity
    @Override
    public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T> T invokeAny(Collection<? extends Callable<T>> tasks) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T> T invokeAny(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit) {
        throw new UnsupportedOperationException();
    }
}

// Separate class for testing
class TestSimpleExecutorService {
    public static void main(String[] args) throws Exception {
        ExecutorService executor = new SampleExecutorService();

        Future<String> future = executor.submit(() -> {
            Thread.sleep(500);
            return "Hello from callable";
        });

        System.out.println("Result: " + future.get());

        executor.shutdown();
    }
}
