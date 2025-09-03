package LLD.ParkingLot.Charges;

public class HourlyBasis implements FeeStrategy {
    private final double ratePerHour;
    public HourlyBasis(double ratePerHour) {
        this.ratePerHour = ratePerHour;
    }
    @Override
    public double calculateFee(long durationInHours) {
        return ratePerHour * durationInHours;
    }
}
