package com.company;

import java.io.IOException;

public class Builder {
    // Build Layer2
    public static int[][] buildLayer(int rows, int cols, int firstBrick, int[][] layer1) throws IOException {

        // Initialize empty 2nd layer, with same dimensions like layer1
        int[][] layer2 = new int[rows][cols];

        // Set current brick to the first brick
        int currentBrick = firstBrick;

        //Loop until there are free spaces in layer2
        while (AreThereEmptyCells(layer2)) {

            // Find first empty cell coordinates
            int[] position = FindTopLeftFreeCell(layer2, rows, cols);

            // Place a new brick horizontally, if possible
            if (CanIPlaceHorizontalBrick(position, layer1)) {
                PlaceHorizontalBrick(position, layer2, currentBrick);
                // If successfully placed, increment the current brick number/ID
                currentBrick++;
                continue;
            }

            // Place a new brick horizontally, if possible
            if (CanIPlaceVerticalBrick(position, rows)) {
                PlaceVerticalBrick(position, layer2, currentBrick);
                // If successfully placed, increment the current brick number/ID
                currentBrick++;
                continue;
            }

            // If no bricks can be placed, neither horizontally, nor vertically, and there are still free cells, then no solution exists!
            throw new IOException("\"-1: No solution exists!\"");
        }

        return layer2;
    }

    // Checks upper layer for remaining empty cells
    private static boolean AreThereEmptyCells(int[][] layer2) {
        for (int[] row : layer2) {
            for (int cell : row) {
                if (cell == 0) {
                    return true;
                }
            }
        }
        return false;
    }

    // Find top-left available free cell
    private static int[] FindTopLeftFreeCell(int[][] layer2, int rows, int cols) {
        // Initialize empty coordinates holder array
        int[] position = new int[2];

        // Set default value of coordinates
        position[0] = -1;
        position[1] = -1;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (layer2[i][j] == 0) {
                    // If we find an empty cell, we copy it's coordinates
                    position[0] = i;
                    position[1] = j;
                    return position;
                }
            }
        }

        return position;
    }

    // Checks layer1 if a horizontal brick lays exactly below the current position
    private static boolean CanIPlaceHorizontalBrick(int[] position, int[][] layer1) {
        // Extract coordinates from coordinates holder array
        int y = position[0];
        int x = position[1];

        // Not possible to place a brick horizontally, if current cell is positioned on the last column
        if (layer1[0].length - x < 2) {
            return false;
        }

        return layer1[y][x] != layer1[y][x + 1];
    }

    // Checks if placement of a vertical is possible in the upper layer
    private static boolean CanIPlaceVerticalBrick(int[] position, int rows) {
        // Not possible to place a brick vertically, if current cell is positioned on the last row
        return rows - position[0] >= 2;
    }

    // Places a horizontal brick at the current position in upper layer
    private static void PlaceHorizontalBrick(int[] position, int[][] layer2, int currentBrick) {
        // Extract coordinates from coordinates holder array
        int y = position[0];
        int x = position[1];

        layer2[y][x] = currentBrick;
        layer2[y][x + 1] = currentBrick;
    }

    // Places a vertical brick at the current position in upper layer
    private static void PlaceVerticalBrick(int[] position, int[][] layer2, int currentBrick) {
        // Extract coordinates from coordinates holder array
        int y = position[0];
        int x = position[1];

        layer2[y][x] = currentBrick;
        layer2[y + 1][x] = currentBrick;
    }
}
