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

    public void CalculateCellAnswers(int row, int column){
        int[] sectorCoords = new int[2];
        sectorCoords[0] = column/3;
        sectorCoords[1] = row/3;

        System.out.println("Your Cell Number is:");
        System.out.println(grid[column][row]);
        System.out.println("Your Cell Sector is:");
        System.out.println(sectorCoords[0] + " " + sectorCoords[1]);
    }
}
