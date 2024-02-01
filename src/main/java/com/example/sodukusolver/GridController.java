package com.example.sodukusolver;

public class GridController {
    public static SudokuGrid[] grid;
    public static void GenerateGrid(){
        SudokuGrid[] newRow= new SudokuGrid[9];

        for(int i = 0; i < newRow.length; i++){
            newRow[i].setCell(1);
        }
        grid = newRow;
    }
}
