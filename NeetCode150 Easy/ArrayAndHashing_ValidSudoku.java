package NeetCode150;

import java.util.*;

public class ArrayAndHashing_ValidSudoku {
    public static void main(String[] args) {
        char[][] board = {
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };

        System.out.println("Is it as valid Sudoku matrix: " + new ArrayAndHashing_ValidSudoku().isValidSudoku(board));
    }

    public boolean isValidSudoku(char[][] board) {
        Set<Character>[] cols = new HashSet[9];
        Set<Character>[] rows = new HashSet[9];
        Set<Character>[] boxes = new HashSet[9];
        for (int i = 0; i < 9; i++) {
            cols[i] = new HashSet<>();
            rows[i] = new HashSet<>();
            boxes[i] = new HashSet<>();
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] != '.') {
                    if (rows[i].contains(board[i][j]))
                        return false;
                    rows[i].add(board[i][j]);
                    if (cols[j].contains(board[i][j]))
                        return false;
                    cols[j].add(board[i][j]);
                    int index = (i / 3) * 3 + j / 3;
                    if (boxes[index].contains(board[i][j])) {
                        return false;
                    }
                    boxes[index].add(board[i][j]);
                }
            }
        }
        return true;

    }
}
