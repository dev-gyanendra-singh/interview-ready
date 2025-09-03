package LLD.ParkingLot.VehicleFactory;

public class Bike implements Vehicle {
    String vehicleName;

    @Override
    public VehicleType getVehicleType() {
        return VehicleType.BIKE;
    }

    @Override
    public String getVehicleName() {
        return vehicleName;
    }

    public Bike(String vehicleName) {
        this.vehicleName = vehicleName;
    }
}
