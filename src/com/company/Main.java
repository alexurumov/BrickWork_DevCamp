package com.company;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);

        // Parse input dimensions of Layer1
        String[] dimensions = in.nextLine().split(" ");

        // Number of rows
        int rows = Integer.parseInt(dimensions[0]);

        // Number of columns
        int cols = Integer.parseInt(dimensions[1]);

        // Determine first and last bricks, based on given surface measurements
        int firstBrick = 1;
        int lastBrick = rows * cols / 2;

        // Parse Layer1 input
        int[][] layer1;
        try {
            layer1 = Parser.parseInputToMatrix(rows, cols, in, firstBrick, lastBrick);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }

        // Build Layer2
        int[][] layer2 = Builder.buildLayer(rows, cols, firstBrick, layer1);

        PrintLayer2(layer2, rows, cols);
    }

    // Prints the finished upper layer
    private static void PrintLayer2(int[][] layer2, int rows, int cols) {
        for (int[] row : layer2) {
            for (int cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }

}
