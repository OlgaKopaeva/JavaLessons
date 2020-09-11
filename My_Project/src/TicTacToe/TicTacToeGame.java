package TicTacToe;

import sun.font.FontRunIterator;

import java.util.Random;
import java.util.Scanner;

public class TicTacToeGame {
    public static final int SIZE = 5;
    public static int dotsToWin;
    public static final char DOT_HUMAN = 'X';
    public static final char DOT_PC = 'O';
    public static final char DOT_EMPTY = '•';
    public static final String FIRST_EMPTY_CHAR = "  ";
    public static final String EMPTY_CHAR = " ";
    public static char[][] map = new char[SIZE][SIZE];
    public static final Scanner scanner = new Scanner(System.in);
    public static final Random random = new Random();
    public static char symbol;

    public static void main(String[] args) {
        startGame(map);
        while (true) {
            humanTurn();
            printMap(map);
            ifCheckEnd(DOT_HUMAN);

            pcTurn();
            printMap(map);
            ifCheckEnd(DOT_PC);

        }
    }

    private static void startGame(char[][] map) {
        createMap();
        printMap(map);
    }

    private static void createMap() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                map[i][j] = DOT_EMPTY;
            }
        }
    }

    private static void printMap(char[][] map) {
        printHeader();
        for (int i = 0; i < SIZE; i++) {
            System.out.print(i + 1 + EMPTY_CHAR);
            for (int j = 0; j < SIZE; j++) {
                System.out.print(map[i][j] + EMPTY_CHAR);
            }
            System.out.println();
        }
    }

    private static void printHeader() {
        System.out.print(FIRST_EMPTY_CHAR);
        for (int i = 0; i < SIZE; i++) {
            System.out.print(i + 1 + EMPTY_CHAR);
        }
        System.out.println();
    }

    private static void humanTurn() {
        int rowNumber, columnNumber;
        symbol = DOT_HUMAN;
        do {
            System.out.print("Ход игрока. Введите номер строки = ");
            rowNumber = scanner.hasNextInt() ? scanner.nextInt() : 0;
            System.out.print("Введите номер столбца = ");
            columnNumber = scanner.hasNextInt() ? scanner.nextInt() : 0;
        } while (!isCellValid(symbol, rowNumber, columnNumber));

        map[rowNumber - 1][columnNumber - 1] = DOT_HUMAN;
    }

    private static boolean isCellValid(char symbol, int rowNumber, int columnNumber) {
        boolean isHuman = symbol == DOT_HUMAN;
        if (isHuman && ((rowNumber < 1) || (rowNumber > SIZE) || (columnNumber < 1) || (columnNumber > SIZE))) {
            System.out.println("Проверьте значения. Попробуйте еще раз.");
            return false;
        } else {
            if (map[rowNumber - 1][columnNumber - 1] != DOT_EMPTY) {
                if (isHuman) {
                    System.out.println("Вы выбрали занятую ячейку! Попробйте снова.");
                }
                return false;
            }
        }
        return true;
    }

    private static void pcTurn() {
        int rowNumber, columnNumber;
        symbol = DOT_PC;
        System.out.println("Ход компьютера:");
        do {
            rowNumber = random.nextInt(SIZE) + 1;
            columnNumber = random.nextInt(SIZE) + 1;
        } while (!isCellValid(symbol, rowNumber, columnNumber));
        map[rowNumber - 1][columnNumber - 1] = DOT_PC;
    }

    private static boolean ifCheckEnd(char symbol) {
        if (isMapFull()) {
            System.out.println("Ничья!");
            return true;
        }
        if (checkWin(symbol) == true) {
            System.out.println((symbol == DOT_HUMAN) ? "Поздравляем! Вы выиграли!" : "Вы проиграли!");
            System.exit(0);
        }
        return false;
    }

    private static boolean isMapFull() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == DOT_EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean checkWin(char symbol) {
        setDotsToWin();
        return (checkHorizontal(symbol) || checkVertical(symbol) || checkDiagonal(symbol) || checkDownRightToLeftDiagonal(symbol));
    }

    private static void setDotsToWin() {
        if (SIZE <= 6) {
            dotsToWin = 3;
        } else if (SIZE <= 10) {
            dotsToWin = 4;
        } else dotsToWin = 5;
    }

    private static boolean checkHorizontal(char symbol) {
        int symbolCounter = 0;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                symbolCounter = (map[i][j] == symbol) ? (symbolCounter + 1) : 0;
                if (symbolCounter == dotsToWin) {
                    return true;
                }
            }
            symbolCounter = 0;
        }
        return false;
    }

    public static boolean checkVertical(char symbol) {
        int symbolCounter = 0;
        for (int j = 0; j < SIZE; j++) {
            for (int i = 0; i < SIZE; i++) {
                symbolCounter = (map[i][j] == symbol) ? (symbolCounter + 1) : 0;
                if (symbolCounter == dotsToWin) {
                    return true;
                }
            }
            symbolCounter = 0;
        }
        return false;
    }

    public static boolean checkDiagonal(char symbol) {
        return (checkUpLeftToRightDiagonal(symbol) || checkDownLeftToRightDiagonal(symbol) || checkUpRightToLeftDiagonal(symbol));
    }

    private static boolean checkUpLeftToRightDiagonal(char symbol) {
        int symbolCounter = 0;
        for (int k = 0; k <= SIZE - dotsToWin; k++) {
            for (int i = 0; i < SIZE; i++) {
                if (i + k < SIZE) {
                    symbolCounter = (map[i][i + k] == symbol) ? (symbolCounter + 1) : 0;
                }
                if (symbolCounter == dotsToWin) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean checkDownLeftToRightDiagonal(char symbol) {
        int symbolCounter = 0;
        for (int k = 1; k <= SIZE - dotsToWin; k++) {
            for (int i = 1; i < SIZE; i++) {
                if (i - k >= 0) {
                    symbolCounter = (map[i][i - k] == symbol) ? (symbolCounter + 1) : 0;
                    if (symbolCounter == dotsToWin) {
                        return true;
                    }
                }
            }
            symbolCounter = 0;
        }
        return false;
    }

    private static boolean checkUpRightToLeftDiagonal(char symbol) {
        int symbolCounter = 0;
        for (int k = 0; k <= dotsToWin; k++) {
            for (int i = 0; i < SIZE; i++) {
                if (SIZE - 1 - i - k >= 0) {
                    symbolCounter = (map[i][SIZE - 1 - i - k] == symbol) ? (symbolCounter + 1) : 0;
                    if (symbolCounter == dotsToWin) {
                        return true;
                    }
                }
            }
            symbolCounter = 0;
        }
        return false;
    }

    private static boolean checkDownRightToLeftDiagonal(char symbol) {
        int symbolCounter = 0;
        for (int k = 1; k <= SIZE - dotsToWin; k++) {
            for (int i = 1; i < SIZE; i++) {
                if (i + k - 1 < SIZE) {
                    symbolCounter = (map[i + k - 1][SIZE - i] == symbol) ? (symbolCounter + 1) : 0;
                    if (symbolCounter == dotsToWin) {
                        return true;
                    }
                }
            }
            symbolCounter = 0;
        }
        return false;
    }

}
