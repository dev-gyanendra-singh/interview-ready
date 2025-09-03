package LLD.ElevatorSystem;

import java.util.Random;

public class ElevatorDemo {
    public static void main(String[] args) {
        ElevatorManager manager = new ElevatorManager(2);
        Random random = new Random();
        for (int i = 1; i <= 500; i++) {
            manager.requestElevator(random.nextInt(i), random.nextInt(i));
        }

    }
}
