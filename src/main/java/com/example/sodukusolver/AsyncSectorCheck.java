package com.example.sodukusolver;

public class AsyncSectorCheck {
    private final int sector;
    private final int number;
    private final int row;
    private final int column;
    private final int[][] playGrid;
    private boolean answer;

    private final SectorBounds[] sectorBoundsArray = {
            new SectorBounds(0 ,2, 0, 2),
            new SectorBounds(0 ,2, 3, 5),
            new SectorBounds(0 ,2, 6, 8),
            new SectorBounds(3 ,5, 0, 2),
            new SectorBounds(3 ,5, 3, 5),
            new SectorBounds(3 ,5, 6, 8),
            new SectorBounds(6 ,8, 0, 2),
            new SectorBounds(6 ,8, 3, 5),
            new SectorBounds(6 ,8, 6, 8),
    };

    AsyncSectorCheck(int sector, int number, int row, int column, int[][] playGrid){
        this.sector = sector;
        this.number = number;
        this.row = row;
        this.column = column;
        this.playGrid = playGrid;
    }

    public synchronized void checkSectorForNumber(){
        boolean numberFound = false;

        SectorBounds sectorBounds = sectorBoundsArray[sector - 1];

        for(int checkRow = sectorBounds.rowStart; checkRow <= sectorBounds.rowEnd; checkRow++){
            for(int checkColumn = sectorBounds.colStart; checkColumn <= sectorBounds.colEnd; checkColumn++){
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
