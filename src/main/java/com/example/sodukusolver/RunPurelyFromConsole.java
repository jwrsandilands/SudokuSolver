package com.example.sodukusolver;

import java.io.IOException;
import java.util.Scanner;

public class RunPurelyFromConsole {

    public static void main(String[] args) throws InterruptedException, IOException {
        final GridController game = new GridController();

        String numbers = "570010048081600075009700201094008102802106004060007890000073080308009000950840000";

        final var t1 = new Thread(() -> game.generateGrid(numbers));
        t1.start();
        t1.join();

        game.printGrid();

        Scanner input = new Scanner( System.in );
        while(true){
            System.out.println("Type a number from 1 to 9 to set a row");
            int row = input.nextInt() - 1;
            System.out.println("Type a number from 1 to 9 to set a column");
            int col = input.nextInt()  - 1;
            System.out.println("Type a number from 1 to 9 to play it here");
            int number = input.nextInt() ;

            boolean isValid = game.playMove(number, row, col);

            if (isValid) {
                System.out.println("Move was good");
            } else {
                System.out.println("Move was bad!");
            }
        }
    }
}
