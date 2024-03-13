package com.example.sodukusolver;

public class RunPurelyFromConsole {

    public static void main(String[] args) throws InterruptedException {
        final GridController game = new GridController();

        String numbers = "570010048081600075009700201094008102802106004060007890000073080308009000950840000";
        //game.generateGrid(numbers);

        final var t1 = new Thread(() -> game.generateGrid(numbers));
        t1.start();
        t1.join();

        game.printGrid();



        //game.playMove(3, 0, 2);
    }
}
