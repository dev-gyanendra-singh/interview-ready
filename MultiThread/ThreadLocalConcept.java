package MultiThread;

public class ThreadLocalConcept {
    // Irrespective of the shared resources---> this ThreadLocal ensures the thread-local resources will be bound to each thread SEPARATELY

    // Theory: 1. User sessions 2. Database connections 3. DateFormat instances (which are not thread-safe)

    private static ThreadLocal<Integer> threadLocalValue = ThreadLocal.withInitial(() -> 1);

    public static void main(String[] args) {

        Runnable task = () -> {
            System.out.println(Thread.currentThread().getName() + " initial: " + threadLocalValue.get());
            threadLocalValue.set(threadLocalValue.get() + 1);
            System.out.println(Thread.currentThread().getName() + " after set: " + threadLocalValue.get());
        };

        Thread t1 = new Thread(task, "Thread-A");
        Thread t2 = new Thread(task, "Thread-B");

        t1.start();
        t2.start();
    }
}
