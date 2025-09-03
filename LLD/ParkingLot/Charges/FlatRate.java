package LLD.ParkingLot.Charges;

public class FlatRate implements FeeStrategy{
    private final double flatRate;
    public FlatRate(double flatRate) {
        this.flatRate = flatRate;
    }
    @Override
    public double calculateFee(long durationInHours) {
        return flatRate;
    }
}
