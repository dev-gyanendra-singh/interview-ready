package MultiThread;

public class OddEvenPrinter {
    private final int max;
    private int number = 1;
    private final Object lock = new Object();

    public OddEvenPrinter(int max) {
        this.max = max;
    }

    public void printOdd() throws InterruptedException {
        synchronized (lock) {
            while (number <= max) {
                while (number % 2 == 0) {
                    lock.wait();
                }
                System.out.println("Odd Thread: " + number);
                number++;
                lock.notify();
            }
        }
    }

    public void printEven() throws InterruptedException {
        synchronized (lock) {
            while (number <= max) {
                while (number % 2 == 1) {
                    lock.wait();
                }
                System.out.println("Even Thread: " + number);
                number++;
                lock.notify();
            }
        }
    }

    public static void main(String[] args) {
        OddEvenPrinter printer = new OddEvenPrinter(10);

        Thread oddThread = new Thread(() -> {
            try {
                printer.printOdd();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        Thread evenThread = new Thread(() -> {
            try {
                printer.printEven();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        oddThread.start();
        evenThread.start();
    }
}

