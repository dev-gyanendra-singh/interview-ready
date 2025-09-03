package ReviseAgain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Graph_Pacific_Atlantic_Pacific {

    public static void main(String[] args) {
        Graph_Pacific_Atlantic_Pacific sol = new Graph_Pacific_Atlantic_Pacific();

        int[][] heights = {
                {1,2,2,3,5},
                {3,2,3,4,4},
                {2,4,5,3,1},
                {6,7,1,4,5},
                {5,1,1,2,4}
        };

        List<List<Integer>> result = sol.pacificAtlantic(heights);
        System.out.println("Cells flowing to both oceans: " + result);
    }
    private static final int[][] DIRS = {{0,1},{1,0},{0,-1},{-1,0}};
    private List<List<Integer>> pacificAtlantic(int[][] heights) {
        List<List<Integer>> result = new ArrayList<>();
        if (heights == null || heights.length == 0) return result;

        int m = heights.length, n = heights[0].length;
        boolean[][] pacific = new boolean[m][n];
        boolean[][] atlantic = new boolean[m][n];

        for(int i = 0; i < m; i++) {
            dfs(heights, pacific, i, 0);
            dfs(heights, atlantic, i, n-1);
        }

        for(int i = 0; i < n; i++) {
            dfs(heights, pacific, 0, i);
            dfs(heights, atlantic, m-1, i);
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (pacific[i][j] && atlantic[i][j]) {
                    result.add(Arrays.asList(i, j));
                }
            }
        }

        return result;



    }

    private void dfs(int[][] heights, boolean[][] visited, int row, int col) {
        int m = heights.length, n = heights[0].length;
        visited[row][col] = true;
        for(int[] dir : DIRS) {
            int newRow = row + dir[0], newCol = col + dir[1];
            if (newRow < 0 || newRow >= m || newCol < 0 || newCol >= n || visited[newRow][newCol]) continue;

            if(heights[newRow][newCol] >= heights[row][col]) {
                dfs(heights, visited, newRow, newCol);
            }

        }
    }
}
