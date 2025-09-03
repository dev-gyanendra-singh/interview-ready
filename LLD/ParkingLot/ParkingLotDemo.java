package LLD.ParkingLot;

import LLD.ParkingLot.Charges.HourlyBasis;
import LLD.ParkingLot.VehicleFactory.*;

public class ParkingLotDemo {
        public static void main(String[] args) {
            ParkingFloorManager lot = new ParkingFloorManager(new HourlyBasis(20));
            lot.addFloor(2, 2);  // 2 car spots, 2 bike spots
            lot.addFloor(1, 3);  // 1 car spot, 3 bike spots

            Vehicle car1 = VehicleFactory.createVehicle("KA01AB1234", VehicleType.CAR);
            Vehicle bike1 = VehicleFactory.createVehicle("KA05XY4321", VehicleType.BIKE);
            Vehicle car2 = VehicleFactory.createVehicle("KA01AB9999", VehicleType.CAR);

            //


            lot.parkVehicle(car1);
            lot.parkVehicle(bike1);
            lot.parkVehicle(car2);

            System.out.println("Pay the charges: " + lot.unParkVehicle("KA01AB1234"));
            lot.parkVehicle(VehicleFactory.createVehicle("KA01AB0001", VehicleType.CAR));
        }
}
