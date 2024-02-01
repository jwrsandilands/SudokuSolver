package com.example.sodukusolver;

public class GridController {
    public int[][] grid;

    public void GenerateGrid(){
        grid = new int[9][9];

        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid.length; j++){
                grid[i][j] = 1;
            }
        }
    }
}
