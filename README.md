# Sudoku Solver

This was made entirely in Intelli J IDEA, although multiple versions were used.

Current implementation can do 3 things:
 - Generate Empty Grid from number string (Hardcoded)
 - Test Made "plays" to see if they are valid with what may go in a cell
 - Test the Grid to Check the solve-ability state
      - Also works to test if finished grids are valid
      - This compiles the grid the same way as the first step

When run the program will create a small FXML/JavaFX window that has 3 inputs, one for each of these actions. This is purely for debugging the actions. 
While some input validation is present please do not expect full data validation as this window is not intended for end user input.

All function outputs are printed to the editor console. Here is where you will see the result of your inputs.

# Function Overview

## Generate Empty Grid 

This function calls a method with a hardcoded string of 81 numbers and converts it into a 2D int array. 
In this same call it calls the a method to find the possible solutions or "hints" to every cell in the grid. This is mainly utilised in the second function,
but will print all of the hints for every cell all the same; mostly for debug.

After this, the grid is formatted for reading and printed to the console.

Note: You must do this before making test plays.

## Test Play for Validation

Here, you should give an input (the number you want to set) and the co-ordinates (0-8, 0-8) of the grid of where you want to set it.
The system will check the hints for that cell, and return a message to the window to let you know your input is valid/invalid.
The updated grid with your number will be re-printed in your console 

Note: You cannot change the numbers that were originally set in the grid on creation.

## Check Solve-ability

This function uses a hardcoded string (by default the exact same string of numbers as the generate function) and will test if the grid can ever be solved.
This also doubles up as a way to check if a solved grid is actually valid.
