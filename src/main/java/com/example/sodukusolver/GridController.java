package com.example.sodukusolver;

import java.util.Vector;

public class GridController {
    public int[][] grid;
    private Vector<NumberHint> hints = new Vector<>();

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
        if(grid[column][row] != 0){
            System.out.println("Your Cell Number is: " + grid[column][row]);
        }

        int sector = FindCellSector(row, column);

        boolean foundNumber = false;
        for(int number = 1; number <= 9; number++){
            foundNumber = CheckSectorForNumber(sector, number);
            hints.add(new NumberHint(number, foundNumber));
        }
    }

    public int FindCellSector(int row, int column){
        int[] sectorCoords = new int[2];
        sectorCoords[0] = column/3;
        sectorCoords[1] = row/3;

        int count = 1;
        boolean countFound= false;
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                if((sectorCoords[0] == i) && (sectorCoords[1] == j)){
                    countFound = true;
                    break;
                }
                count++;
            }
            if(countFound){
                break;
            }
        }

        System.out.println("Your Cell Sector is: " + count);

        return count;
    }

    public boolean CheckSectorForNumber(int sector, int number){
        boolean numberFound = false;
        int rowStart = 0, rowEnd = 2;
        int colStart = 0, colEnd = 2;

        switch(sector){
            case 1:
                rowStart = 0;
                rowEnd = 2;
                colStart = 0;
                colEnd = 2;
                break;
            case 2:
                rowStart = 0;
                rowEnd = 2;
                colStart = 3;
                colEnd = 5;
                break;
            case 3:
                rowStart = 0;
                rowEnd = 2;
                colStart = 6;
                colEnd = 8;
                break;
            case 4:
                rowStart = 3;
                rowEnd = 5;
                colStart = 0;
                colEnd = 2;
                break;
            case 5:
                rowStart = 3;
                rowEnd = 5;
                colStart = 3;
                colEnd = 5;
                break;
            case 6:
                rowStart = 3;
                rowEnd = 5;
                colStart = 6;
                colEnd = 8;
                break;
            case 7:
                rowStart = 6;
                rowEnd = 8;
                colStart = 0;
                colEnd = 2;
                break;
            case 8:
                rowStart = 6;
                rowEnd = 8;
                colStart = 3;
                colEnd = 5;
                break;
            case 9:
                rowStart = 6;
                rowEnd = 8;
                colStart = 6;
                colEnd = 8;
                break;
            default:
                break;
        }

        for(int row = rowStart; row <= rowEnd; row++){
            for(int column = colStart; column <= colEnd; column++){
                if (grid[row][column] == number) {
                    numberFound = true;
                    break;
                }
            }
        }

        return numberFound;
    }
}
