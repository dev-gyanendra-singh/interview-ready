package LLD.ParkingLot;

import LLD.ParkingLot.VehicleFactory.VehicleType;

import java.util.ArrayList;
import java.util.List;

class ParkingFloor {
    int floorId;
    List<ParkingSpot> spots;

    public ParkingFloor(int floorId, int carSpots, int bikeSpots) {
        this.floorId = floorId;
        this.spots = new ArrayList<>();

        for (int i = 0; i < carSpots; i++) {
            spots.add(new ParkingSpot(i, VehicleType.CAR));
        }
        for (int i = 0; i < bikeSpots; i++) {
            spots.add(new ParkingSpot(i + carSpots, VehicleType.BIKE));
        }
    }

    public ParkingSpot getFreeSpot(VehicleType type) {
        for (ParkingSpot spot : spots) {
            if (!spot.isOccupied && spot.type == type) {
                return spot;
            }
        }
        return null;
    }
}
