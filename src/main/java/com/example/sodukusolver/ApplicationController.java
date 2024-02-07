package com.example.sodukusolver;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ApplicationController {
    @FXML
    private Label generateButtonLabel;

    @FXML
    protected void OnGenerateButtonClick() {
        generateButtonLabel.setText("Generated Grid in your Console!");
        GridController game = new GridController();
        game.GenerateGrid();

        game.PrintGrid();

/*        game.CalculateCellAnswers(0, 0);*/
        game.CalculateCellAnswers(0, 4);
/*        game.CalculateCellAnswers(7, 6);*/
    }
}