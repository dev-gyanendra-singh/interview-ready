package ReviseAgain;

public class BinarySearch_MediunOfTwoSortedArray {
    public static void main(String[] args) {
        BinarySearch_MediunOfTwoSortedArray binarySearch = new BinarySearch_MediunOfTwoSortedArray();
        int[] nums1 = {1, 2};
        int[] nums2 = {3, 4};
        System.out.println(binarySearch.findMedianSortedArrays(nums1, nums2));
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            // required --> otherwise --> maan k chalo... phle wala bda ho gya to--->      j = (m+n+1) /2 - (i) --> bot negative ho jaiga
            return findMedianSortedArrays(nums2, nums1);
        }

        // poora ye scene h ki: left me aur right me same or left me ak jyada ho skta h.... combined array ki baat ho rhi

        int m = nums1.length;
        int n = nums2.length;
        int low = 0, high = m;
        while (low <= high) {
            int i = (low + high) / 2;
            int j = (m + n + 1) / 2 - i;
            // in case left1 me se koi elements lete hi nahi to?
            int maxLeft1 = (i == 0) ? Integer.MIN_VALUE : nums1[i - 1];
            // right me end me jaake baar lga dete
            int minRight1 = (i == m) ? Integer.MAX_VALUE : nums1[i];

            int maxLeft2 = (j == 0) ? Integer.MIN_VALUE : nums2[j - 1];
            int minRight2 = (j == n) ? Integer.MAX_VALUE : nums2[j];

            if (maxLeft1 <= minRight2 && maxLeft2 <= minRight1) {
                if ((m + n) % 2 == 0) {
                    return ((double) Math.max(maxLeft1, maxLeft2) + Math.min(minRight1, minRight2)) / 2;
                } else {
                    return (double) Math.max(maxLeft1, maxLeft2);
                }
            } //maxLeft1 bada hai right minRight2 se meaning is maxLeft1 ko minRight me jana chahiye the
            else if (maxLeft1 > minRight2) {
                high = i - 1;
            }
            // Too far on the left side in nums1, move right
            else {
                low = i + 1;
            }
        }
        return 0;

    }
}
