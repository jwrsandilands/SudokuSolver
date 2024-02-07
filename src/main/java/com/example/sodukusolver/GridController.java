package com.example.sodukusolver;

import java.util.Vector;

public class GridController {
    public int[][] grid;
    Vector<Vector<Vector<NumberHint>>> gridHints = new Vector<>();

    public void generateGrid(){
        grid = new int[9][9];
        gridHints.clear();

        String numbers = "570010048081600075009700201094008102802106004060007890000073080308009000950840000";
        int cellCounter = 0;

        for(int row = 0; row < grid.length; row++){
            for(int column = 0; column < grid.length; column++){
                grid[row][column] = Character.getNumericValue(numbers.toCharArray()[cellCounter]);
                cellCounter++;
            }
        }

        calculateAndCompileHints();
    }

    public void printGrid(){
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

    public void calculateCellAnswers(int column, int row){
        if(grid[row][column] != 0){
            System.out.println("Your Cell Number is: " + grid[row][column]);
        }

        calculateCellHints(column, row);
    }

    public Vector<NumberHint> calculateCellHints(int column, int row){
        Vector<NumberHint> hints = new Vector<>();

        if(grid[row][column] != 0){
            System.out.println("Your Cell Number is: " + grid[row][column]);
        }

        int sector = findCellSector(column, row);

        boolean possibleNumber;
        for(int number = 1; number <= 9; number++){
            if(number == grid[row][column]){
                possibleNumber = true;
                hints.add(new NumberHint(number, possibleNumber));
            }
            else{
                possibleNumber = !(checkSectorForNumber(sector, number) || checkRowForNumber(row, number) || checkColumnForNumber(column, number));
                hints.add(new NumberHint(number, possibleNumber));
            }
        }

        System.out.println("The numbers that can go in this cell are: ");
        for(NumberHint hint : hints){
            if(hint.possibleNumber){
                System.out.print(hint.checkedNumber + " ");
            }
        }
        System.out.println("");

        return hints;
    }

    private void calculateAndCompileHints(){
        for(int row = 0; row <= 8; row++){
            Vector<Vector<NumberHint>> rowHints = new Vector<>();
            for(int column = 0; column <= 8; column++){
                rowHints.add(calculateCellHints(column, row));
            }
            gridHints.add(rowHints);
        }
    }

    public boolean validateMove(int input, int row, int column){
        boolean isValid = false;
        Vector<Vector<NumberHint>> hintRow = gridHints.get(row);
        Vector<NumberHint> hintCell = hintRow.get(column);

        NumberHint guess = new NumberHint(input, true);

        if(hintCell.stream().anyMatch(e -> (e.checkedNumber == input) && (e.possibleNumber))){
            isValid = true;
        }

        return isValid;
    }

    private int findCellSector(int row, int column){
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

    private boolean checkSectorForNumber(int sector, int number){
        boolean numberFound = false;
        int rowStart, rowEnd ;
        int colStart, colEnd ;

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
                rowStart = 0;
                rowEnd = 0;
                colStart = 0;
                colEnd = 0;
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

    private boolean checkRowForNumber(int row, int number){
        boolean numberFound = false;

        for(int column = 0; column < 9; column++){
            if(grid[row][column] == number){
                numberFound = true;
                break;
            }
        }

        return numberFound;
    }

    private boolean checkColumnForNumber(int column, int number){
        boolean numberFound = false;

        for(int row = 0; row < 9; row++){
            if(grid[row][column] == number){
                numberFound = true;
                break;
            }
        }

        return numberFound;
    }
}
