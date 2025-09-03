package ReviseAgain;

import java.util.Arrays;

public class Stack_FleetCar {
    public static void main(String[] args) {
        Stack_FleetCar sf = new Stack_FleetCar();
        System.out.println(sf.carFleet(10, new int[]{1, 4}, new int[]{3, 2}));
    }

    public int carFleet(int target, int[] position, int[] speed) {
        int n = position.length;
        double[][] fleet = new double[n][2];
        for (int i = 0; i < n; i++) {
            fleet[i][0] = position[i];
            fleet[i][1] = ((double) (target - position[i])) / (double) speed[i];
        }

        Arrays.sort(fleet, (a, b) -> Double.compare(a[0], b[0]));

        int res = 1;
        double currFleetTime = fleet[n - 1][1];
        for (int i = n - 2; i >= 0; i--) {
            if (fleet[i][1] >= currFleetTime) {
                res++;
                currFleetTime = fleet[i][1];
            }
        }
        return res;
    }
}
