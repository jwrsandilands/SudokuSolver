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
        if(grid[column][row] != 0){
            System.out.println("Your Cell Number is: " + grid[column][row]);
        }

        int sector = FindCellSector(row, column);

        for(int number = 1; number < 9; number++){
            CheckSectorForNumber(sector, number);
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
        switch(sector){
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                break;
            case 7:
                break;
            case 8:
                break;
            case 9:
                break;
            default:
                break;
        }
        return false;
    }
}
