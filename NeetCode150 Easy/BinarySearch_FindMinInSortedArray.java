package NeetCode150;

public class BinarySearch_FindMinInSortedArray {
    public static void main(String[] args) {
        int [] arr = new int [] {4,5,6,7,0,1,2};
        System.out.println("Minimum" + new BinarySearch_FindMinInSortedArray().findMin(arr));

    }

    public int findMin(int[] nums) {
        int l = 0, r = nums.length - 1;
        if(nums[l] <= nums[r]) {
            return nums[l];
        }
        // 6,7,8,1,2,3
        // l = 0
        // SanjulSHAR
        // Sanjul
        // MA
        // SanjulSharma
        // r = 5
        // mid = 2
        // usually l<= r tab krte hai jab hme target hit krna ho .... idhar kiya to overshoot hoke l and r dono barabar ho jainge fir infinite loop me ghumta rhega
        while(l < r) {
            int mid = l + (r - l)/2;
            if(nums[mid] > nums[r]) {
                l = mid + 1;
            } else {
                // minus nahi krenge... obv krenge to mid ko to chor hi diya na... upar wali condition se bhi... kyuki target hi ni krna hai to mid ko hmesha minimize krne ka soch rhe...isliye mid ka koi specific condition nahi hai
                r = mid;
            }
        }
        return nums[l];
    }
}
