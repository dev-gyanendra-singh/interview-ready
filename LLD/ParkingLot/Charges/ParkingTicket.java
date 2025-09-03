package LLD.ParkingLot.Charges;

import LLD.ParkingLot.VehicleFactory.Vehicle;

import java.util.concurrent.TimeUnit;

public class ParkingTicket {
    private final Vehicle vehicle;
    private final long entryTime;
    private long exitTime;
    private final FeeStrategy feeStrategy;

    public ParkingTicket(Vehicle vehicle, FeeStrategy feeStrategy) {
        this.vehicle = vehicle;
        this.entryTime = System.currentTimeMillis();
        this.feeStrategy = feeStrategy;
    }

    public void markExit() {
        this.exitTime = System.currentTimeMillis();
    }

    public double calculateFee() {
        long durationInHours = Math.max(1, TimeUnit.HOURS.toHours(exitTime - entryTime));
        return feeStrategy.calculateFee(durationInHours);
    }

    /*
    * long durationInMillis = exitTime - entryTime;
    long durationInHours = (long) Math.ceil(durationInMillis / (1000.0 * 60 * 60));
    return feeStrategy.calculateFee(durationInHours);
    * */

    public Vehicle getVehicle() {
        return vehicle;
    }
}

