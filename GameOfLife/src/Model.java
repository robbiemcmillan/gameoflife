/**
 * Game of Life Exercise 
 * Model.java
 * Purpose: Provides a means of generating the initial grid, recording the amount of neighbours each cell has & 
 * includes a method to generate the next grid evolution based on the neighbours recorded. 
 * Also provides special methods to handle certain scenarios, namely horizontal & vertical lines as outlined in the instructions given.
 * 
 * @author William Robbie McMillan
 * @version 1.0 07/02/19
 */

package GameOfLife.src;

import java.util.Arrays;

public class Model {

	int cellRows;
	int cellColumns;
	Cell[][] cellGrid;
	Cell[][] verticalLineAlive;
	Cell[][] horizontalLineAlive;

	public Model() {

		cellRows = 3;
		cellColumns = 3;
		cellGrid = new Cell[cellRows][cellColumns];
		verticalLineAlive = new Cell[cellRows][cellColumns];
		horizontalLineAlive = new Cell[cellRows][cellColumns];

	}

	/*
	 * Loops through the 2D array adding a new Cell object and setting its status
	 * based on the seed.
	 */

	public void generateInitialGrid() {

		for (int i = 0; i < cellGrid.length; i++) {
			for (int j = 0; j < cellGrid[i].length; j++) {

				int n = (int) Math.round(Math.random()); // Either generates a 1 or 0 to populate the initial grid.
				cellGrid[i][j] = new Cell();
				cellGrid[i][j].setStatus(n);
				System.out.print(cellGrid[i][j].getStatus() + " "); // Print for checking grids contents/debugging
																	// purposes.

			}

			System.out.println();
		}
		System.out.println();

	}

	/**
	 * 
	 * @return cellGrid
	 */

	public Cell[][] getInitialGrid() {
		return cellGrid;

	}

	/**
	 * @return cellRows
	 */
	public int getCellRows() {
		return cellRows;
	}

	/**
	 * @return cellColumns
	 */
	public int getCellColumns() {
		return cellColumns;
	}

	/**
	 * @param cellRows
	 */
	public void setCellRows(int cellRows) {
		this.cellRows = cellRows;

	}

	/**
	 * @param cellColumns
	 */
	public void setCellColumns(int cellColumns) {
		this.cellColumns = cellColumns;
	}

	/**
	 * 
	 * Method to count the amount of live neighbours each Cell in the grid has. When
	 * looping through the cellGrid, if a Cell is alive, increment neighbours.
	 * Try/Catch blocks deal with the issue of out of bounds neighbours.
	 * 
	 * @param row
	 * @param col
	 */
	public void recordNeighbours(int row, int col) {

		try {

			if (cellGrid[row - 1][col - 1].isAlive()) {
				cellGrid[row][col].neighbours++;

			}
		} catch (ArrayIndexOutOfBoundsException e) {

		}
		try {
			if (cellGrid[row - 1][col].isAlive()) {
				cellGrid[row][col].neighbours++;

			}
		} catch (ArrayIndexOutOfBoundsException e) {

		}
		try {

			if (cellGrid[row - 1][col + 1].isAlive()) {
				cellGrid[row][col].neighbours++;

			}
		} catch (ArrayIndexOutOfBoundsException e) {

		}
		try {
			if (cellGrid[row][col - 1].isAlive()) {
				cellGrid[row][col].neighbours++;

			}
		} catch (ArrayIndexOutOfBoundsException e) {

		}
		try {

			if (cellGrid[row][col + 1].isAlive()) {
				cellGrid[row][col].neighbours++;

			}
		} catch (ArrayIndexOutOfBoundsException e) {

		}
		try {
			if (cellGrid[row + 1][col - 1].isAlive()) {
				cellGrid[row][col].neighbours++;

			}
		} catch (ArrayIndexOutOfBoundsException e) {

		}
		try {

			if (cellGrid[row + 1][col].isAlive()) {
				cellGrid[row][col].neighbours++;

			}
		} catch (ArrayIndexOutOfBoundsException e) {

		}
		try {
			if (cellGrid[row + 1][col + 1].isAlive()) {
				cellGrid[row][col].neighbours++;

			}
		} catch (ArrayIndexOutOfBoundsException e) {

		}
	}

	
	/**
	 * 
	 * A more condensed method for counting the amount of neighbours a cell has,
	 * the same functionlity as recordNeighbours() but the neighbours are stored
	 * in the NeighbourLocation enum and called via a for each loop.
	 * 
	 * @param row
	 * @param col
	 */
	
	public void countNeighbours(int row, int col) {

		for (NeighbourLocation direction : NeighbourLocation.values()) {
			try {

				if (cellGrid[row + direction.nlx][col + direction.nly].isAlive()) {
					cellGrid[row][col].neighbours++;
				}

			} catch (ArrayIndexOutOfBoundsException e) {

			}

		}

	}

	/*
	 * Loops through the 2D array, calling the recordNeighbours method for each
	 * cell.
	 */

	public void documentNeighbours() {

		for (int i = 0; i < cellGrid.length; i++) {
			for (int j = 0; j < cellGrid[i].length; j++) {

				// recordNeighbours(i, j);
				countNeighbours(i, j);

			}

		}
	}

	/*
	 * Loops through the 2D array, clearing each Cells neighbours list.
	 */

	public void clearNeighbours() {

		for (int i = 0; i < cellGrid.length; i++) {
			for (int j = 0; j < cellGrid[i].length; j++) {

				cellGrid[i][j].setNeighbours(0);
				;

			}
		}

	}

	/*
	 * Method to evolve the grid based on the recorded neighbours of each cell.
	 * 
	 */

	public void generateEvolution() {

		if (Arrays.deepEquals(cellGrid, getVerticalLineAlive()) == true) { // Special circumstances: if cellGrid
																			// matches, set as horizontal line of alive
																			// cells.

			cellGrid = getHorizontalLineAlive();

		}

		else if (Arrays.deepEquals(cellGrid, getHorizontalLineAlive()) == true) { // Special circumstances: if cellGrid
																					// matches, set as vertical line of
																					// alive cells.

			cellGrid = getVerticalLineAlive();

		}

		else {

			for (int i = 0; i < cellGrid.length; i++) {
				for (int j = 0; j < cellGrid[i].length; j++) {

					if (cellGrid[i][j].getStatus() == 1 && cellGrid[i][j].neighbours < 2) { // Underpopulation: if a
																							// cell is alive and has
																							// less than 2 neighbours,
																							// it dies.
						cellGrid[i][j].setStatus(0);

					} else if (cellGrid[i][j].getStatus() == 1 && cellGrid[i][j].neighbours > 3) {// Overcrowding: if a
																									// cell has more
																									// than 3
																									// neighbours, it
																									// dies.
						cellGrid[i][j].setStatus(0);
					}

					else if (cellGrid[i][j].getStatus() == 0 && cellGrid[i][j].neighbours == 3) {// Creation of Life: if
																									// a dead cell has
																									// exactly 3
																									// neighbours, it
																									// comes alive.
						cellGrid[i][j].setStatus(1);
					}

				}

			}

		}

		viewGrid(cellGrid);
		clearNeighbours();

	}

	/**
	 * @return cellGrid
	 */
	public Cell[][] getEvolution() {
		return cellGrid;
	}

	/**
	 * Creates a grid with a vertical line of alive cells through the middle.
	 * 
	 * @return verticalLineAlive
	 */
	public Cell[][] getVerticalLineAlive() {

		for (int i = 0; i < verticalLineAlive.length; i++) {
			for (int j = 0; j < verticalLineAlive[i].length; j++) {

				verticalLineAlive[i][j] = new Cell();

			}

		}
		for (int k = verticalLineAlive.length; k > 0; k--) {

			verticalLineAlive[verticalLineAlive.length - k][verticalLineAlive.length / 2].setStatus(1);

		}
		return verticalLineAlive;

	}

	/**
	 * Creates a grid made up of a horizontal line of alive cells through the
	 * middle.
	 * 
	 * @return horizontalLineAlive
	 */
	public Cell[][] getHorizontalLineAlive() {

		for (int i = 0; i < horizontalLineAlive.length; i++) {
			for (int j = 0; j < horizontalLineAlive[i].length; j++) {

				horizontalLineAlive[i][j] = new Cell();

			}

		}
		for (int k = horizontalLineAlive.length; k > 0; k--) {

			horizontalLineAlive[horizontalLineAlive.length / 2][horizontalLineAlive.length - k].setStatus(1);

		}

		return horizontalLineAlive;

	}

	/*
	 * Method to set initial grid for testing the system for each scenario.
	 */

	public void setInitialGrid(Cell[][] cellGrid) {

		this.cellGrid = cellGrid;

		viewGrid(cellGrid);

		System.out.println();
	}

	/*
	 * Method to view the cellGrid after it is altered in the console, for debugging
	 * purposes.
	 */

	public void viewGrid(Cell[][] cellGrid) {

		for (int i = 0; i < cellGrid.length; i++) {
			for (int j = 0; j < cellGrid[i].length; j++) {

				System.out.print(cellGrid[i][j].getStatus() + " ");

			}

			System.out.println();
		}

		System.out.println();

	}

	/*
	 * Main method for testing the functionality of the program.
	 */

	public static void main(String[] args) {

		Model m = new Model();
		m.generateInitialGrid();
		m.documentNeighbours();
		m.generateEvolution();
		m.documentNeighbours();
		m.generateEvolution();

	}

}
