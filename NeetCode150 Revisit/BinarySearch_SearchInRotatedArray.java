package ReviseAgain;

public class BinarySearch_SearchInRotatedArray {
    public static void main(String[] args) {
        BinarySearch_SearchInRotatedArray obj = new BinarySearch_SearchInRotatedArray();
        System.out.println(obj.search(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}, 8));

    }

    public int search(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[l] <= nums[mid]) {
                if (nums[l] <= target && target < nums[mid]) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            } else {
                if (nums[mid] < target && target <= nums[r]) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }

            }
        }
        return -1;
    }
}
