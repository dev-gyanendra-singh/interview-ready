package NeetCode150;

public class Graph_Max_Area_In_Grid {

    public static void main(String[] args) {
        Graph_Max_Area_In_Grid s = new Graph_Max_Area_In_Grid();

        int[][] grid = {
                {0,0,1,0,0},
                {0,1,1,1,0},
                {0,0,1,0,0},
                {1,0,0,0,0}
        };

        int result = s.maxAreaOfIsland(grid);
        System.out.println("Max Area of Island: " + result); // Expected: 5
    }

    private int maxAreaOfIsland(int[][] grid) {
        int maxArea = 0;
        int rows = grid.length, cols = grid[0].length;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 1) {
                    int area = dfs(grid, i, j);
                    maxArea = Math.max(maxArea, area);
                }
            }
        }

        return maxArea;
    }
    private int dfs(int[][] grid, int i, int j) {
        // Out of bounds or water
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length
                || grid[i][j] == 0) return 0;

        grid[i][j] = 0; // mark as visited

        int area = 1;
        area += dfs(grid, i - 1, j); // up
        area += dfs(grid, i + 1, j); // down
        area += dfs(grid, i, j - 1); // left
        area += dfs(grid, i, j + 1); // right

        return area;
    }

}
