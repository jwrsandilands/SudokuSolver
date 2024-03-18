package com.example.sodukusolver;

public class SectorBounds {
    public int rowStart;
    public int rowEnd;
    public int colStart;
    public int colEnd;

    SectorBounds(int rowStart, int rowEnd, int colStart, int colEnd){
        this.rowStart = rowStart;
        this.rowEnd = rowEnd;
        this.colStart = colStart;
        this.colEnd = colEnd;
    }
}
