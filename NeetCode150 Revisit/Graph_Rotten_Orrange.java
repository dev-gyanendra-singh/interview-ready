package ReviseAgain;

import java.util.LinkedList;
import java.util.Queue;

public class Graph_Rotten_Orrange {
    private static final int[][] DIRS = {
            {1, 0}, {-1, 0}, {0, 1}, {0, -1}
    };

    public int orangesRotting(int[][] grid) {

        int r = grid.length;;
        int c = grid[0].length;

        Queue<int[]> q = new LinkedList<>();
        int fresh = 0;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (grid[i][j] == 1) {
                    fresh++;
                } else if (grid[i][j] == 2) {
                    q.add(new int[]{i, j});
                }
            }
        }
        if (fresh == 0) return 0;
        int time = 0;
        while (!q.isEmpty()) {
            time++;
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int[] cur = q.poll();
                int x = cur[0];
                int y = cur[1];
                for (int[] dir : DIRS) {
                    int newX = x + dir[0];
                    int newY = y + dir[1];
                    if(newX >= 0 && newX < r && newY >= 0 && newY < c && grid[newX][newY] == 1) {
                        grid[newX][newY] = 2;
                        q.offer(new int[]{newX, newY});
                        fresh--;
                    }
                }
            }

        }

        return fresh == 0 ? time : -1;


    }


    public static void main(String[] args) {
        Graph_Rotten_Orrange sol = new Graph_Rotten_Orrange();

        int[][] grid = {
                {2,1,1},
                {1,1,0},
                {0,1,1}
        };

        int result = sol.orangesRotting(grid);
        System.out.println("Minutes to rot all oranges: " + result);  // Output: 4
    }

}
