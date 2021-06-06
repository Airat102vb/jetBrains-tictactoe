package tictactoe;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        char[][] d3 = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                d3[i][j] = ' ';
            }
        }

        System.out.println("---------");
        System.out.println("|       |");
        System.out.println("|       |");
        System.out.println("|       |");
        System.out.println("---------");

        if (!impossible(d3)) {
            System.out.println("Impossible");
            return;
        }

        char q = 'X';
        Scanner ceil;

        do {
            System.out.print("Enter the coordinates: ");
            ceil = new Scanner(System.in);

            if (!ceil.hasNextInt()) {
                System.out.println("You should enter numbers!");
            } else {
                int step_1 = ceil.nextInt();
                int step_2 = ceil.nextInt();
                if (step_1 < 1 || step_1 > 3 || step_2 < 1 || step_2 > 3) {
                    System.out.println("Coordinates should be from 1 to 3!");
                } else {
                    if (d3[step_1 - 1][step_2 - 1] == ' ') {
                        d3[step_1 - 1][step_2 - 1] = q;
                        System.out.println("---------");
                        System.out.println("| " + d3[0][0] + " " + d3[0][1] + " " + d3[0][2] + " |");
                        System.out.println("| " + d3[1][0] + " " + d3[1][1] + " " + d3[1][2] + " |");
                        System.out.println("| " + d3[2][0] + " " + d3[2][1] + " " + d3[2][2] + " |");
                        System.out.println("---------");
                        if (q != 'X') {
                            q = 'X';
                        } else {
                            q = 'O';
                        }

                        //horizontally
                        int g_first = d3[0][0] + d3[0][1] + d3[0][2];
                        int g_second = d3[1][0] + d3[1][1] + d3[1][2];
                        int g_third = d3[2][0] + d3[2][1] + d3[2][2];

                        //vertically
                        int v_first = d3[0][0] + d3[1][0] + d3[2][0];
                        int v_second = d3[0][1] + d3[1][1] + d3[2][1];
                        int v_third = d3[0][2] + d3[1][2] + d3[2][2];

                        //diagonal
                        int d_up = d3[2][0] + d3[1][1] + d3[0][2];
                        int d_down = d3[0][0] + d3[1][1] + d3[2][2];

                        int XWin = checkRows(264, g_first, g_second, g_third, v_first, v_second, v_third, d_up, d_down);
                        int OWin = checkRows(237, g_first, g_second, g_third, v_first, v_second, v_third, d_up, d_down);

                        if (XWin > OWin) {
                            System.out.println("X wins");
                            return;
                        } else if (XWin < OWin) {
                            System.out.println("O wins");
                            return;
                        }

                        if (isFull(d3)) {
                            System.out.println("Draw");
                            return;
                        }

                    } else {
                        System.out.println("This cell is occupied! Choose another one!");
                    }
                }
            }
        } while (isDone(d3));

        ceil.close();
    }

    private static boolean isDone(char[][] chars) {
        return Arrays.stream(chars).anyMatch(x -> {
            boolean result = true;
            for (char c : x) {
                if (c == '_') {
                    result = false;
                }
            }
            return result;
        });
    }

    private static boolean isFull(char[][] chars) {
        for (char[] ch : chars) {
            for (char c : ch) {
                if (c == 32) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean impossible(char[][] chars) {
        int X = 0;
        int O = 0;
        for (char[] ch : chars) {
            for(char c : ch) {
                if (c == 'X') {
                    X++;
                } else if (c == 'O') {
                    O++;
                }
            }
        }

        if (Math.abs(X - O) > 1) {
            return false;
        }
        return true;
    }

    private static int checkRows(int numb, int ... args) {
        int n = 0;
        for (int i : args)
        if ( i == numb ) {
            n++;
        }
        return n;
    }
}
