package MultiThread;

public class WaitSleepDemo {
 /*
 *
wait() ==> 	Used for inter-thread communication; releases monitor lock and waits to be notified
while sleep() Pauses thread execution for fixed time; does NOT release any locks
*
*yield() ==> A hint to the thread scheduler that the current thread is
*  willing to pause to let other threads of the same priority run.
* */

    private static final Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        Object lock = new Object();

        Thread t1 = new Thread(() -> {
            synchronized (lock) {
                try {
                    System.out.println("Thread 1 waiting...");
                    lock.wait(); // releases lock and waits for notify
                    System.out.println("Thread 1 resumed after wait");
                } catch (InterruptedException e) { }
            }
        });

        Thread t2 = new Thread(() -> {
            System.out.println("Thread 2 sleeping...");
            try {
                Thread.sleep(2000);  // sleep 2 seconds ... releasing lock
            } catch (InterruptedException e) { }
            System.out.println("Thread 2 awake");
        });

        Thread t3 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.println("Thread 3 yielding...");
                Thread.yield();  // hint scheduler to switch thread
            }
        });

        t1.start();
        t2.start();
        t3.start();

        Thread.sleep(3000);
        System.out.println("Main thread exiting");
        synchronized (lock) {
            lock.notify();  // notify waiting thread 1
        }


        t1.join();
        t2.join();
        t3.join();
        System.out.println("Main thread: Done");
    }
}

