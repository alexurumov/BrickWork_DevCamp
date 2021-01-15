package com.company;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Parser {
    public static int[][] parseInputToMatrix(int rows, int cols, Scanner in, int firstBrick, int lastBrick) throws IOException {

        // Validate rows and cols input data
        if (!Validator.ValidateRowsAndCols(rows, cols)) {
            throw new IOException("Invalid input! Rows/Columns are not correct numbers!");
        }

        int[][] matrix = new int[rows][cols];
        for (int i = 0; i < rows; i++) {

            // Collect input data as a whole row
            String rowString = in.nextLine();

            // Validate if input has actual rows
            if (Validator.IsRowEmpty(rowString)) {
                throw new IOException("Invalid input! Row is empty!");
            }

            // Parse data from row into an array of integers
            int[] row = Arrays.stream(rowString
                    .split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            // Validate input has actual columns
            if (!Validator.IsRowFull(row, cols)) {
                throw new IOException("Invalid input! Row is not full!");
            }

            matrix[i] = row;
        }

        // Validate there are no bricks spanning 3 rows/columns
        if (!Validator.IsBrickSpanValid(rows, cols, matrix, firstBrick, lastBrick)) {
            throw new IOException("Invalid input! Brick spanning into more than 2 rows/columns");
        }

        return matrix;
    }
}
