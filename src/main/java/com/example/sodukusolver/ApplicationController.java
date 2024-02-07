package com.example.sodukusolver;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ApplicationController {
    GridController game = new GridController();

    @FXML
    private Label generateButtonLabel;
    @FXML
    private Label playButtonLabel;
    @FXML
    private Button playButton;
    @FXML
    private TextField inputTextField;
    @FXML
    private TextField XTextField;
    @FXML
    private TextField YTextField;
    @FXML
    private Label generateSolvedButtonLabel;

    @FXML
    protected void OnGenerateButtonClick() {
        generateButtonLabel.setText("Generated Grid in your Console!");
        playButton.setDisable(false);

        String numbers = "570010048081600075009700201094008102802106004060007890000073080308009000950840000";
        game.generateGrid(numbers);

        game.printGrid();
    }

    @FXML
    protected void OnPlayButtonClick(){
        int input, X, Y;
        try{
            input = Integer.parseInt(inputTextField.getText());
            X = Integer.parseInt(XTextField.getText());
            Y = Integer.parseInt(YTextField.getText());
        } catch(Exception e) {
            playButtonLabel.setText("Input Error.");
            return;
        }

        boolean isValid = game.playMove(input, X, Y);

        if (isValid) {
            playButtonLabel.setText("Move was valid");
        } else {
            playButtonLabel.setText("Move was invalid!");
        }
    }

    @FXML
    protected void OnGenerateSolvedButtonClick(){
        playButton.setDisable(false);

        GridController testingGame = new GridController();

        String numbers = "570010048081600075009700201094008102802106004060007890000073080308009000950840000";
        testingGame.generateGrid(numbers);

        testingGame.printGrid();

        boolean validSolution = testingGame.validateCompleteGrid();
        if(validSolution){
            generateSolvedButtonLabel.setText("Testing Grid is in Solvable State");
        }
        else{
            generateSolvedButtonLabel.setText("Testing Grid isn't Solvable!");
        }

    }
}