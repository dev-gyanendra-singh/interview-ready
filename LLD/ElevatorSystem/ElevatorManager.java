package LLD.ElevatorSystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ElevatorManager {
    private List<Elevator> elevators;

    public ElevatorManager(int numberOfElevators) {
        elevators = new ArrayList<>();
        for (int i = 0; i < numberOfElevators; i++) {
            elevators.add(new Elevator(i));
        }
    }
    public void requestElevator(int requestedFloor, int currentFloor) {

        Direction direction = requestedFloor - currentFloor < 0 ? Direction.DOWN : Direction.UP;
        Elevator bestElevator = null;

        for (Elevator elevator : elevators) {
            if (elevator.willServe(requestedFloor, direction)) {
                bestElevator = elevator;
                break;
            }
        }

        if (bestElevator == null) {
            bestElevator = elevators.getFirst(); // fallback
        }

        System.out.println("Request at floor " + requestedFloor + " assigned to Elevator " + bestElevator.getId());
        bestElevator.addRequest(requestedFloor);
    }

}
