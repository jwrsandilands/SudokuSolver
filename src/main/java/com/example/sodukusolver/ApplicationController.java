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

        System.out.println(game.grid);
    }
}