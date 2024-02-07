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
        game.generateGrid();

        game.printGrid();

        System.out.println(game.validateMove(4, 0, 2));
    }
}