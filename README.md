# GameOfLife
Java implementation of the Game of Life coding exercise.

## Overview/Task

To build a programme that exibits the game of life, 
an ever evolving two dimensional grid, 
that evolves over time based on a set of rules. 

The rules are as follows:

   * Any live cell with fewer than two live neighbours dies, as if caused by underpopulation.
   * Any live cell with more than three live neighbours dies, as if by overcrowding.
   * Any live cell with two or three live neighbours lives on to the next generation.
   * Any dead cell with exactly three live neighbours becomes a live cell.

## Assumptions

Through the completion of this exercise a series of assumptions were made:

   * For scenario 6, after the initial state and two evolutions shown
     the cell grid would continually flip between a horizontal and vertical line of alive cells.
   * The number of alive cells for scenario 6 would be set using the overall length of the cell grid.
   * The output of each evolution is shown in both the console of the IDE and also a visual representation 
     via the GUI.

## Issues & Possible Solutions

As I was not sure of the best way to implement an "infinite grid", I decided to make the grid fixed so I could continue on with the exercise. Furthermore, this created issues when counting neighbours as I had to deal with neighbours outwidth the boundaries of the grid. A possible way of rectifying this would be to create a much larger grid via a 2d arraylist that could be dynamically added to, and choose a section to show through the GUI. By doing this, the programme would not have to deal with boundary issues and could better mimic the "infinite grid".
