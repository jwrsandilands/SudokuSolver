package com.example.sodukusolver;

public class AsyncRowCheck {
    private final int row;
    private final int column;
    private final int number;
    private final int[][] playGrid;
    private boolean answer;

    AsyncRowCheck(int row, int column, int number, int[][] playGrid){
        this.row = row;
        this.column = column;
        this.number = number;
        this.playGrid = playGrid;
    }

    public synchronized void checkRowForNumber(){
        boolean numberFound = false;

        for(int checkColumn = 0; checkColumn < 9; checkColumn++){
            if((playGrid[row][checkColumn] == number) && (checkColumn != column)){
                numberFound = true;
                break;
            }
        }

        answer = numberFound;
    }

    public boolean getAnswer(){
        return answer;
    }
}
