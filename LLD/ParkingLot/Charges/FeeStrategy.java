package LLD.ParkingLot.Charges;

public interface FeeStrategy {
    double calculateFee(long durationInHours);
}
