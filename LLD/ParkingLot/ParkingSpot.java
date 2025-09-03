package LLD.ParkingLot;

import LLD.ParkingLot.VehicleFactory.Vehicle;
import LLD.ParkingLot.VehicleFactory.VehicleType;

class ParkingSpot {
    int spotId;
    VehicleType type;
    boolean isOccupied;
    Vehicle parkedVehicle;

    public ParkingSpot(int spotId, VehicleType type) {
        this.spotId = spotId;
        this.type = type;
        this.isOccupied = false;
    }

    public synchronized boolean park(Vehicle v) {
        if (!isOccupied && v.getVehicleType() == this.type) {
            this.parkedVehicle = v;
            this.isOccupied = true;
            return true;
        }
        return false;
    }

    public boolean unpark() {
        if (isOccupied) {
            this.parkedVehicle = null;
            this.isOccupied = false;
            return true;
        }
        return false;
    }
}
