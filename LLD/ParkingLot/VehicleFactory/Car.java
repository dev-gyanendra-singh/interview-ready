package LLD.ParkingLot.VehicleFactory;

public class Car implements Vehicle {
    String vehicleName;

    @Override
    public VehicleType getVehicleType() {
        return VehicleType.CAR;
    }

    @Override
    public String getVehicleName() {
        return vehicleName;
    }

    public Car(String vehicleName) {
        this.vehicleName = vehicleName;
    }
}
