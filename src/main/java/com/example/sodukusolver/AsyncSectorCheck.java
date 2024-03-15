package com.example.sodukusolver;

public class AsyncSectorCheck {
    private final int sector;
    private final int number;
    private final int row;
    private final int column;
    private final int[][] playGrid;
    private boolean answer;

    AsyncSectorCheck(int sector, int number, int row, int column, int[][] playGrid){
        this.sector = sector;
        this.number = number;
        this.row = row;
        this.column = column;
        this.playGrid = playGrid;
    }

    public synchronized void checkSectorForNumber(){
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

        answer = numberFound;
    }

    public boolean getAnswer(){
        return answer;
    }
}
