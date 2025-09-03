package LLD.ParkingLot.VehicleFactory;

public class VehicleFactory {
    public static Vehicle createVehicle(String vehicleNumber, VehicleType type) {
        switch (type) {
            case CAR:
                return new Car(vehicleNumber);
            case BIKE:
                return new Bike(vehicleNumber);
            default:
                throw new IllegalArgumentException("Unknown vehicle type: " + type);
        }
    }
}
