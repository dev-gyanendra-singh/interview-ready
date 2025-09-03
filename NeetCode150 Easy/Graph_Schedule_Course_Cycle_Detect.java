package NeetCode150;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Graph_Schedule_Course_Cycle_Detect {

    public static void main(String[] args) {
        Graph_Schedule_Course_Cycle_Detect sol = new Graph_Schedule_Course_Cycle_Detect();

        int numCourses = 4;
        int[][] prerequisites = {
                {1, 0}, {2, 1}, {3, 2}
        };

        boolean result = sol.canFinish(numCourses, prerequisites);
        System.out.println("Can finish all courses? " + result); // true

        int[][] prerequisitesWithCycle = {
                {1, 0}, {0, 1}
        };
        System.out.println("Can finish all courses? " +
                sol.canFinish(2, prerequisitesWithCycle)); // false
    }

    private boolean canFinish(int numCourses, int[][] prerequisites) {

        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }
        int[] indegree = new int[numCourses];
        for (int[] prerequisite : prerequisites) {
            int req = prerequisite[1];
            int course = prerequisite[0];
            graph.get(req).add(course);
            indegree[course]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }

        int takenCourse = 0;
        int index = 0;
        int [] order = new int[numCourses];
        while (!queue.isEmpty()) {
            int course = queue.poll();
            // here if we want to return the order then
            // order[index++] = course
            takenCourse++;
            for (int neighbor : graph.get(course)) {
                indegree[neighbor]--;
                if (indegree[neighbor] == 0) {
                    queue.offer(neighbor);
                }
            }

        }
        // if order is required: takenCourse == numCourses ? order : new int[0]

        return takenCourse == numCourses;
    }
}
