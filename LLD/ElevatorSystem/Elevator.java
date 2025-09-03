package LLD.ElevatorSystem;

import java.util.Collection;
import java.util.Collections;
import java.util.SortedSet;
import java.util.TreeSet;

public class Elevator {
    private int id;
    private int currentFloor;
    private Direction direction;
    private Collection<Integer> upStops;
    private Collection<Integer> downStops;
    private Thread workerThread = null;

    public Elevator(int id) {
        this.id = id;
        this.currentFloor = 0;
        this.direction = Direction.IDLE;
        this.upStops = Collections.synchronizedCollection(new TreeSet<>());
        this.downStops = Collections.synchronizedCollection(new TreeSet<>(Collections.reverseOrder()));
    }

    public int getId() {
        return id;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public Direction getDirection() {
        return direction;
    }

    public void addRequest(int floor) {
        if (floor > currentFloor) {
            upStops.add(floor);
            direction = Direction.UP;
        } else if (floor < currentFloor) {
            downStops.add(floor);
            direction = Direction.DOWN;
        } else {
            System.out.println("Elevator " + id + " already on floor " + floor);
        }

        resume();
    }

    public void step() {
        if (direction == Direction.UP && !upStops.isEmpty()) {
            currentFloor++;
            if (upStops.contains(currentFloor)) {
                upStops.remove(currentFloor);
                System.out.println("Elevator " + id + " stopped at floor " + currentFloor);
            }
        } else if (direction == Direction.DOWN && !downStops.isEmpty()) {
            currentFloor--;
            if (downStops.contains(currentFloor)) {
                downStops.remove(currentFloor);
                System.out.println("Elevator " + id + " stopped at floor " + currentFloor);
            }
        }

        if (upStops.isEmpty() && downStops.isEmpty()) {
            direction = Direction.IDLE;
        }
    }

    public boolean isIdle() {
        return direction == Direction.IDLE;
    }

    public boolean willServe(int floor, Direction requestDir) {
        if (direction == Direction.IDLE) return true;
        if (direction == requestDir) {
            if (direction == Direction.UP && floor >= currentFloor) return true;
            if (direction == Direction.DOWN && floor <= currentFloor) return true;
        }
        return false;
    }

    public void resume() {
        if (workerThread == null) {
            workerThread = new Thread(() -> {
                System.out.println("Elevator " + id + " starting");
                while (direction != Direction.IDLE) {
                    step();
                }
                workerThread = null;
            });
            workerThread.start();
        }
    }
}
