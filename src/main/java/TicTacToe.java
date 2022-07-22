/**
 * This is a tic-tac-toe program =)
 * @author Kler
 * @version 1.0
 */
import java.util.Scanner;

public class TicTacToe {

    /**
     * The method to draw the current state of the grid.
     * @param cells   the array of grid elements.
     */
    static void printCells(String[][] cells) {
        final String dashes = "---------";
        System.out.println(dashes);
        for (String[] cell : cells) {
            String line = "| " + cell[0] + " " + cell[1] + " " + cell[2] + " |";
            System.out.println(line);
        }
        System.out.println(dashes);
    }

    /**
     * The method to make a step.
     * @param cells   the array of grid elements.
     * @param order   X or O
     * @param scanner
     */
    static void makeStep(String[][] cells, String order, Scanner scanner) {
        char coorX;
        char coorY;
        int x, y;
        boolean wrongCoordinates = true;
        while (wrongCoordinates) {
            coorY = scanner.next().charAt(0);
            coorX = scanner.next().charAt(0);
            if (coorX > '9' || coorX < '0' || coorY > '9' || coorY < '0') {
                System.out.println("You should enter numbers!");
            } else if (coorX == '0' || coorX >= '4' || coorY == '0' || coorY >= '4') {
                System.out.println("Coordinates should be from 1 to 3!");
            } else {
                x = (int) coorX - 49;
                y = (int) coorY - 49;
                if (!cells[y][x].equals(" ")) {
                    System.out.println("This cell is occupied! Choose another one!");
                } else {
                    wrongCoordinates = false;
                    cells[y][x] = order;
                }
            }
        }
    }

    /**
     * The method to output the current status of the game.
     * @param cells   the array of grid elements.
     * @return
     */
    static String printStatus(String[][] cells) {
        boolean hasEmpty = false;
        int numX;
        int numO;
        int totalX = 0;
        int totalO = 0;
        int rowsX = 0;
        int rowsO = 0;
        for (int i = 0; i < cells.length; i++) {
            numX = 0;
            numO = 0;
            for (int j = 0; j < cells[i].length; j++) {
                if (cells[i][j].equals(" ")) {
                    hasEmpty = true;
                    continue;
                } else if (cells[i][j].equals("X")) {
                    ++numX;
                } else {
                    ++numO;
                }
                if (i == 0 && cells[i][j].equals(cells[i + 1][j]) && cells[i][j].equals(cells[i + 2][j])) {
                    if (cells[i][j].equals("X")) {
                        ++rowsX;
                    } else {
                        ++rowsO;
                    }
                }
            }
            totalO += numO;
            totalX += numX;
            if (numX == 3) {
                ++rowsX;
            }
            if (numO == 3) {
                ++rowsO;
            }
        }
        if (!cells[0][0].equals(" ") && cells[0][0].equals(cells[1][1]) && cells[0][0].equals(cells[2][2])) {
            if (cells[1][1].equals("X")) {
                ++rowsX;
            } else {
                ++rowsO;
            }
        } else if (!cells[0][2].equals(" ") && cells[0][2].equals(cells[1][1]) && cells[0][2].equals(cells[2][0])) {
            if (cells[1][1].equals("X")) {
                ++rowsX;
            } else {
                ++rowsO;
            }
        }

        String result = "";
        if (rowsX > 0 && rowsO > 0 || totalO > totalX + 1 || totalX > totalO + 1) {
            result = "Impossible";
        }
        else if (rowsX == 0 && rowsO == 0 && !hasEmpty) {
            result = "Draw";
        } else if (rowsX == 0 && rowsO == 0 && hasEmpty) {
            result = "Game not finished";
        } else if (rowsX > 0) {
            result = "X wins";
        } else if (rowsO > 0) {
            result = "O wins";
        }
        return result;
    }

    /**
     * Program entry point.
     * First goes X, then O. Just enter the (x,y) coordinates.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        final int size = 3;
        String[][] cells = new String[size][size];
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                cells[i][j] = " ";
            }
        }

        printCells(cells);
        boolean gameNotFinished = true;
        String status = "";
        String order = "X";
        while (gameNotFinished) {
            makeStep(cells, order, scanner);
            printCells(cells);
            status = printStatus(cells);
            if (!status.equals("Game not finished")) {
                gameNotFinished = false;
            }
            switch (order) {
                case "X":
                    order = "O";
                    break;
                case "O":
                    order = "X";
                    break;
            }
        }
        System.out.println(status);
    }
}
