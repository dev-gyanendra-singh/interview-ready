package LLD.TicTacToe;

import java.util.Scanner;

public class TicTacToe {
    private static final char EMPTY = ' ';
    private static final int SIZE = 3;
    private static char[][] board = new char[SIZE][SIZE];
    private static char currentPlayer = 'X';
   // Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        initBoard();
        printBoard();

        while (true) {
            System.out.println("Player " + currentPlayer + ", enter row and column (1-3 1-3): ");
            int age = scanner.nextInt();
            int row = scanner.nextInt() - 1;
            int col = scanner.nextInt() - 1;

            if (!isValidMove(row, col)) {
                System.out.println("Invalid move. Try again.");
                continue;
            }

            board[row][col] = currentPlayer;
            printBoard();

            if (checkWin()) {
                System.out.println("Player " + currentPlayer + " wins!");
                break;
            }

            if (isBoardFull()) {
                System.out.println("It's a draw!");
                break;
            }

            switchPlayer();
        }

        scanner.close();
    }

    private static void initBoard() {
        for (int i = 0; i < SIZE; i++)
            for (int j = 0; j < SIZE; j++)
                board[i][j] = EMPTY;
    }

    private static void printBoard() {
        System.out.println("\nBoard:");
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                System.out.print(" " + board[i][j]);
                if (j < SIZE - 1) System.out.print(" |");
            }
            System.out.println();
            if (i < SIZE - 1) System.out.println("---+---+---");
        }
        System.out.println();
    }

    private static boolean isValidMove(int row, int col) {
        return row >= 0 && row < SIZE && col >= 0 && col < SIZE && board[row][col] == EMPTY;
    }

    private static void switchPlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    private static boolean checkWin() {
        // Check rows and columns
        for (int i = 0; i < SIZE; i++) {
            if ((board[i][0] == currentPlayer &&
                    board[i][1] == currentPlayer &&
                    board[i][2] == currentPlayer) ||
                    (board[0][i] == currentPlayer &&
                            board[1][i] == currentPlayer &&
                            board[2][i] == currentPlayer))
                return true;
        }

        // Check diagonals
        return (board[0][0] == currentPlayer &&
                board[1][1] == currentPlayer &&
                board[2][2] == currentPlayer) ||
                (board[0][2] == currentPlayer &&
                        board[1][1] == currentPlayer &&
                        board[2][0] == currentPlayer);
    }

    private static boolean isBoardFull() {
        for (int i = 0; i < SIZE; i++)
            for (int j = 0; j < SIZE; j++)
                if (board[i][j] == EMPTY)
                    return false;
        return true;
    }
}
