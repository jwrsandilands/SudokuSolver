package com.example.sodukusolver;

public class GridController {
    public int[][] grid;

    public void GenerateGrid(){
        grid = new int[9][9];

        for(int row = 0; row < grid.length; row++){
            for(int column = 0; column < grid.length; column++){
                grid[row][column] = 1;
            }
        }
    }

    public void PrintGrid(){
        System.out.println("Your Generated Grid is:");
        for (int[] rows : grid) {
            for (int cell : rows) {
                System.out.print(" " + rows[cell] + " ");
            }
            System.out.println();
        }
    }
}
