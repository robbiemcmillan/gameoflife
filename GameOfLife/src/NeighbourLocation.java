package GameOfLife.src;

/**
 * Game of Life Exercise 
 * NeighbourLocation.java
 * Purpose: Stores the possible locations of a Cells neighbours, 
 * to be call when recording a Cells live neighbours.
 * 
 * @author William Robbie McMillan
 * @version 1.0 07/02/19
 */

enum NeighbourLocation {
	
	 	NORTHWEST(-1, -1),
	    NORTH(0, -1),
	    NORTHEAST(1, -1),
	    EAST(1, 0),
	    SOUTHEAST(1, 1),
	    SOUTH(0, 1),
	    SOUTHWEST(-1, 1),
	    WEST(-1, 0),
	    ;

	    final int nlx;
	    final int nly;

	  NeighbourLocation(int nlx, int nly) {
	        this.nlx = nlx;
	        this.nly = nly;
	    }

}
