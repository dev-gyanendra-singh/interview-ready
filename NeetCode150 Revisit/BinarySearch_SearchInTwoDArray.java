package ReviseAgain;

public class BinarySearch_SearchInTwoDArray {
    public static void main(String[] args) {
        BinarySearch_SearchInTwoDArray main = new BinarySearch_SearchInTwoDArray();
        int[][] arr = new int[][]{
                {1, 2, 4, 8},
                {10, 11, 12, 13},
                {14, 20, 30, 40}};
        System.out.println(main.searchMatrix(arr, 9));
    }

    public boolean searchMatrix(int[][] matrix, int target) {
        int n = matrix.length;
        int m = matrix[0].length;
        int l = 0, r = n * m - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            int row = mid / m;
            int col = mid % m;
            if (matrix[row][col] == target) {
                return true;
            }
            if (matrix[row][col] < target) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return false;
    }
}
