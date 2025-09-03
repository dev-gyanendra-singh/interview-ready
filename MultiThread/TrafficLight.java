package MultiThread;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TrafficLight {
    // Using thread pool ex service


    public class TrafficLightWithExecutor {
        public static void main(String[] args) {
            ExecutorService executor = Executors.newSingleThreadExecutor();

            Runnable trafficCycle = () -> {
                while (true) {
                    System.out.println("🔴 Red light ON");
                    sleep(3000);

                    System.out.println("🟢 Green light ON");
                    sleep(3000);

                    System.out.println("🟡 Yellow light ON");
                    sleep(1000);
                }
            };

            executor.submit(trafficCycle);
            // executor.shutdown(); // optional if you want to stop it eventually
        }

        private static void sleep(int ms) {
            try {
                Thread.sleep(ms);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }




    // without thread pool ex


    public class TrafficLightNoExecutor {
        private static final Object lock = new Object();
        private static int current = 0; // 0 = red, 1 = green, 2 = yellow

        public static void main(String[] args) {
            Thread red = new Thread(() -> runLight(0, "🔴 Red", 3000));
            Thread green = new Thread(() -> runLight(1, "🟢 Green", 3000));
            Thread yellow = new Thread(() -> runLight(2, "🟡 Yellow", 1000));

            red.start();
            green.start();
            yellow.start();
        }

        private static void runLight(int id, String color, int delay) {
            while (true) {
                synchronized (lock) {
                    while (current != id) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }

                    System.out.println(color + " light ON");
                    try {
                        Thread.sleep(delay);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }

                    current = (current + 1) % 3; // move to next light
                    lock.notifyAll();
                }
            }
        }
    }

}
