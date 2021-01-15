package com.company;

import java.util.Arrays;

public class Validator {

    // Checks if input data is correct
    public static boolean ValidateRowsAndCols (int rows, int cols) {
        return rows >= 0 && rows <= 100 && rows % 2 == 0 && cols >= 0 && cols <= 100 && cols % 2 == 0;
    }

    // Checks if row is empty
    public static boolean IsRowEmpty (String rowString) {
        return rowString.trim().equals("");
    }

    // Checks if row is not missing a column
    public static boolean IsRowFull (int[] row, int cols) {
        return row.length == cols;
    }

    // Combines Row and Col Span check
    public static boolean IsBrickSpanValid(int rows, int cols, int[][] layer1, int firstBrick, int lastBrick) {
        return IsRowBrickSpanValid(rows, cols, layer1, firstBrick, lastBrick)
                && IsColumnBrickSpanValid(rows, cols, layer1, firstBrick, lastBrick);
    }

    // Checks on each row of a matrix if there are more than 2 occurrences of the same cell ID
    private static boolean IsRowBrickSpanValid(int rows, int cols, int[][] layer1, int firstBrick, int lastBrick) {
        for (int i = 0; i < rows; i++) {
            for (int j = firstBrick; j < lastBrick; j++) {
                int currentBrick = j;
                long occurrences = Arrays.stream(layer1[i])
                        .filter(br -> br == currentBrick)
                        .count();
                if (occurrences > 2) {
                    return false;
                }
            }
        }
        return true;
    }

    // Inverts the matrix and uses IsRowBrickSpanValid method
    private static boolean IsColumnBrickSpanValid(int rows, int cols, int[][] layer1, int firstBrick, int lastBrick) {
        // Invert the matrix, so we can check for occurrences in each column
        int[][] inverted = new int[cols][rows];
        for (int i = 0; i < cols; i++) {
            int[] column = new int[rows];
            for (int j = 0; j < rows; j++) {
                //initialize array that holds all of the columns
                column[j] = layer1[j][i];
            }
            inverted[i] = column;
        }
        return IsRowBrickSpanValid(rows, cols, inverted, firstBrick, lastBrick);
    }

}
