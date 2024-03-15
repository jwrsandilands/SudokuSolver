package com.example.sodukusolver;

import java.util.Arrays;
import java.util.Vector;

public class GridController {
    public int[][] grid;
    public int[][] playGrid;
    Vector<Vector<Vector<NumberHint>>> gridHints = new Vector<>();

    public void generateGrid(String numbers){
        grid = new int[9][9];
        playGrid = new int[9][9];

        int cellCounter = 0;

        for(int row = 0; row < grid.length; row++){
            for(int column = 0; column < grid.length; column++){
                grid[row][column] = Character.getNumericValue(numbers.toCharArray()[cellCounter]);
                playGrid[row][column] = Character.getNumericValue(numbers.toCharArray()[cellCounter]);
                cellCounter++;
            }
        }

        calculateAndCompileHints();
    }

    public void printGrid(){
        System.out.println("Your Grid is now:");
        for (int[] rows : playGrid) {
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

        try {
            calculateCellHints(column, row);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public Vector<NumberHint> calculateCellHints(int column, int row) throws InterruptedException {
        Vector<NumberHint> hints = new Vector<>();

        if(playGrid[row][column] != 0){
            System.out.println("Your Cell Number is: " + playGrid[row][column]);
        }

        int sector = findCellSector(column, row);

        boolean possibleNumber;
        for(int number = 1; number <= 9; number++){
            int finalNumber = number;

            AsyncSectorCheck sectorCheck = new AsyncSectorCheck(sector, finalNumber, row, column, playGrid);
            AsyncRowCheck rowCheck = new AsyncRowCheck(row, column, finalNumber, playGrid);
            AsyncColumnCheck columnCheck = new AsyncColumnCheck(row, column, finalNumber, playGrid);

            final var t1 = new Thread(() -> sectorCheck.checkSectorForNumber());
            final var t2 = new Thread(() -> rowCheck.checkRowForNumber());
            final var t3 = new Thread(() -> columnCheck.checkColumnForNumber());

            t1.start();
            t2.start();
            t3.start();

            t1.join();
            t2.join();
            t3.join();

            possibleNumber = ! (sectorCheck.getAnswer()
                            || rowCheck.getAnswer()
                            || columnCheck.getAnswer());
            hints.add(new NumberHint(number, possibleNumber));
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
        gridHints.clear();
        for(int row = 0; row <= 8; row++){
            Vector<Vector<NumberHint>> rowHints = new Vector<>();
            for(int column = 0; column <= 8; column++){
                try {
                    rowHints.add(calculateCellHints(column, row));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            gridHints.add(rowHints);
        }
    }

    public boolean playMove(int input, int row, int column){
        if(grid[row][column] != 0){
            return false;
        }

        playGrid[row][column] = input;
        printGrid();

        boolean isValid = validateMove(input, row, column, false);
        return isValid;
    }

    private boolean validateMove(int input, int row, int column, boolean recompileHints){
        boolean isValid = false;
        Vector<Vector<NumberHint>> hintRow = gridHints.get(row);
        Vector<NumberHint> hintCell = hintRow.get(column);

        if((hintCell.stream().anyMatch(e -> (e.checkedNumber == input) && (e.possibleNumber))) || (playGrid[row][column] == 0)){
            isValid = true;
            if(recompileHints){
                calculateAndCompileHints();
            }
        }

        return isValid;
    }

    public boolean validateCompleteGrid(){
        boolean isValid = false;

        int row = 0, column = 0;
        for (int[] rows : playGrid) {
            for (int cell : rows) {
                isValid = validateMove(cell, row, column, false);
                if(!isValid){
                    break;
                }
                column++;
            }
            if(!isValid){
                break;
            }
            column = 0;
            row++;
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

    private boolean checkSectorForNumber(int sector, int number, int row, int column){
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

        for(int checkRow = rowStart; checkRow <= rowEnd; checkRow++){
            for(int checkColumn = colStart; checkColumn <= colEnd; checkColumn++){
                if ((playGrid[checkRow][checkColumn] == number) && (checkRow != row && checkColumn != column)) {
                    numberFound = true;
                    break;
                }
            }
        }

        return numberFound;
    }

    private boolean checkRowForNumber(int row, int column, int number){
        boolean numberFound = false;

        for(int checkColumn = 0; checkColumn < 9; checkColumn++){
            if((playGrid[row][checkColumn] == number) && (checkColumn != column)){
                numberFound = true;
                break;
            }
        }

        return numberFound;
    }

    private boolean checkColumnForNumber(int row, int column, int number){
        boolean numberFound = false;

        for(int checkRow = 0; checkRow < 9; checkRow++){
            if((playGrid[checkRow][column] == number) && (checkRow != row)){
                numberFound = true;
                break;
            }
        }

        return numberFound;
    }
}
