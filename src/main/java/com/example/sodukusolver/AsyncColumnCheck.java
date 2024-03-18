package com.example.sodukusolver;

public class AsyncColumnCheck {
    private final int row;
    private final int column;
    private final int number;
    private final int[][] playGrid;
    private boolean answer;

    AsyncColumnCheck(int row, int column, int number, int[][] playGrid){
        this.row = row;
        this.column = column;
        this.number = number;
        this.playGrid = playGrid;
    }

    public synchronized void checkColumnForNumber(){
        boolean numberFound = false;

        for(int checkRow = 0; checkRow < 9; checkRow++){
            if((playGrid[checkRow][column] == number) && (checkRow != row)){
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
