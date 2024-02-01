package com.example.sodukusolver;

public class GridController {
    public int[][] grid;

    public void GenerateGrid(){
        grid = new int[9][9];

        String numbers = "570010048081600075009700201094008102802106004060007890000073080308009000950840000";
        int cellCounter = 0;

        for(int row = 0; row < grid.length; row++){
            for(int column = 0; column < grid.length; column++){
                grid[row][column] = Character.getNumericValue(numbers.toCharArray()[cellCounter]);
                cellCounter++;
            }
        }
    }

    public void PrintGrid(){
        System.out.println("Your Generated Grid is:");
        for (int[] rows : grid) {
            for (int cell : rows) {
                if(cell == 0){
                    System.out.print(" " + "." + " ");
                }
                else{
                    System.out.print(" " + cell + " ");
                }
            }
            System.out.println();
        }
    }
}
