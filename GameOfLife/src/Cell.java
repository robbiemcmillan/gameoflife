/**
 * Game of Life Exercise 
 * Cell.java 
 * Purpose: Constructs the Cell object. 
 * Holds an integer value that keeps track of how many neighbour cells each cell has.
 * Also holds the status of the Cell, a value of 1 meaning alive, a value of 0 refers to a dead Cell.
 * 
 * @author William Robbie McMillan
 * @version 1.0 07/02/19
 */
package GameOfLife.src;

public class Cell {
	
	int status;
	public int neighbours;;
	
	public Cell() {
		
		neighbours = 0;
		status = 0;
		
	}
	
	/**
	 * @return status
	 */
	public int getStatus() {
		return status;
	}
	
	/**
	 * @param status
	 */
	public void setStatus(int status) {
		this.status = status;
	}
	
	/**
	 * @return neighbours
	 */
	public int getNeighbours() {
		return neighbours;
	}
	
	
	/**
	 * @param neighbours
	 */
	public void setNeighbours(int neighbours) {
		this.neighbours = neighbours;
	}
	
	/**
	 * Checks whether a Cell is alive or dead.
	 * 
	 * @return boolean
	 */
	public boolean isAlive() {
		
		if(status == 1) {
			return true;
		}
		else {
			return false;
		}
	}
	
	
	

	

}
