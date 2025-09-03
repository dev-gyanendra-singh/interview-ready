package NeetCode150;

import java.util.Arrays;

public class ArrayAndHashing_ProductOfArrayExceptSelf {
    public static void main(String[] args) {
        int[] prodArray = {0, 1, 10, 2, 3, 4, 9, 6};
        System.out.println(Arrays.toString(new ArrayAndHashing_ProductOfArrayExceptSelf().productExceptSelf(prodArray)));
    }

    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        result[0] = 1;
        for (int i = 1; i < n; i++) {
            result[i] = result[i - 1] * nums[i - 1];
        }
        int product = 1;
        for (int i = n-1; i>=0; i--) {
            result[i] *= product;
            product *= nums[i];
        }
        return result;
    }
}
