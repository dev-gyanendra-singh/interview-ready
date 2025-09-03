package ReviseAgain;

import WorkingModule.Main;

import java.util.Arrays;

public class BinarySearch_FindMinimumRateOfEatingPilesOfBananasWhengivenMaxHours {
    public static void main(String[] args) {
        BinarySearch_FindMinimumRateOfEatingPilesOfBananasWhengivenMaxHours main = new BinarySearch_FindMinimumRateOfEatingPilesOfBananasWhengivenMaxHours();
        int[] arr = new int[]{1, 4, 3, 2};
        System.out.println(main.minEatingSpeed(arr, 9));
    }

    public int minEatingSpeed(int[] piles, int h) {
        int left = 1, right = Arrays.stream(piles).max().getAsInt();
        int res = 0;

        while (left <= right) {
            int k = left + (right - left) / 2;

            int hours = calculateHours(k, piles);
            if (hours <= h) {
                res = k;
                right = k - 1;
            } else {
                left = k + 1;
            }
        }
        return res;
    }

    private int calculateHours(int k, int[] piles) {
        int hours = 0;
        for (int i = 0; i < piles.length; i++) {
            hours += (int) Math.ceil((double) piles[i] / (double) k);
        }
        return hours;
    }
}
