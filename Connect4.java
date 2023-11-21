import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        final String ANSI_RED = "\u001B[31m";
        final String ANSI_YELLOW = "\u001B[33m";
        final String ANSI_RESET = "\u001B[0m";
        int cols = 7;
        int rows = 6;
        String[][] board = new String[rows][cols];

        Scanner scanner = new Scanner(System.in);

        int currentPlayer = 1; // Player 1 (Red) starts

        while (true) {
            printBoard(board);

            System.out.printf("Player %d, drop a %c disk (1-7): ", currentPlayer, currentPlayer == 1 ? 'R' : 'Y');
            int column = scanner.nextInt() - 1;

            if (column < 0 || column >= cols) {
                System.out.println("Invalid column number!");
                continue;
            }

            if (!dropDisk(board, column, currentPlayer == 1 ? 'R' : 'Y')) {
                System.out.println("Column is full!");
                continue;
            }

            if (checkWin(board, currentPlayer == 1 ? 'R' : 'Y')) {
                printBoard(board);
                System.out.printf("Player %d wins!\n", currentPlayer);
                break;
            } else if (checkDraw(board)) {
                printBoard(board);
                System.out.println("It's a draw!");
                break;
            }

            currentPlayer = 3 - currentPlayer; // Switch players (1 -> 2 or 2 -> 1)
        }
    }

    public static void printBoard(String[][] board) {
        for (int i = 0; i < board.length; i++) {
            System.out.print("|");
            for (int j = 0; j < board[0].length; j++) {
                String disc = board[i][j];
                if (disc == null) {
                    disc = " ";
                }
                System.out.printf(" %s |", disc);
            }
            System.out.println();
        }
        System.out.println("+-----------------------------+");
        System.out.println("  1   2   3   4   5   6   7");
    }

    public static boolean dropDisk(String[][] board, int column, char disc) {
        for (int i = board.length - 1; i >= 0; i--) {
            if (board[i][column] == null) {
                board[i][column] = String.valueOf(disc);
                return true;
            }
        }
        return false;
    }

    public static boolean checkWin(String[][] board, char disc) {
        int rows = board.length;
        int cols = board[0].length;
        int count = 0;

        // Check horizontal
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (board[i][j] == null || board[i][j].charAt(0) != disc) {
                    count = 0;
                } else {
                    count++;
                    if (count == 4) {
                        return true;
                    }
                }
            }
            count = 0;
        }

        // Check vertical
        for (int j = 0; j < cols; j++) {
            for (int i = 0; i < rows; i++) {
                if (board[i][j] == null || board[i][j].charAt(0) != disc) {
                    count = 0;
                } else {
                    count++;
                    if (count == 4) {
                        return true;
                    }
                }
            }
            count = 0;
        }

        // Check diagonal (from top-left to bottom-right)
        for (int i = 0; i < rows - 3; i++) {
            for (int j = 0; j < cols - 3; j++) {
                for (int k = 0; k < 4; k++) {
                    if (board[i + k][j - k] == null || board[i + k][j - k].charAt(0) != disc) {
                        break;
                    }
                    if (k == 3) {
                        return true;
                    }
                }
            }
        }

        // Check diagonal (from top-right to bottom-left)
        for (int i = 0; i < rows - 3; i++) {
            for (int j = cols - 1; j >= 3; j--) {
                for (int k = 0; k < 4; k++) {
                    if (board[i + k][j - k] == null || board[i + k][j - k].charAt(0) != disc) {
                        break;
                    }
                    if (k == 3) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public static boolean checkDraw(String[][] board) {
        for (int i = 0; i < board[0].length; i++) {
            if (board[0][i] == null) {
                return false; // At least one column is not full
            }
        }
        return true; // All columns are full, and no player has won
    }
}
