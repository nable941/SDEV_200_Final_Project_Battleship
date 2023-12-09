/**
 * This class implements the gameboard that operates as the playing field for the game.
 * @author Nathan Able
 * @version 1.0
 * 
 */
package application;

public class Gameboard {
	private int gridSize;
	private char[][] gameboard;
	
	//Constructors
	/**
	 * Default constructor with a build size of 11
	 */
	public Gameboard() {
		this(11);
	}
	
	/**
	 * This is a future use constructor for flex goals and is not currently in use
	 * @param size sets the size of the gameboard
	 */
	public Gameboard(int size) {
		if (size < 11) {
			throw new IllegalArgumentException("The gameboard size must be 10x10 or greater.");
		}
		initializeArray(size);
		gridSize = size;
	}
	
	//Generate base gameboard data
	/**
	 * This sets initial data for the gameboard. Is only functional for the current 10x10 version
	 * @param size of the gameboard object
	 */
    private void initializeArray(int size) {
    	gameboard = new char[size][size];
    	for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i == 0){
                    switch (j){
                        case 1: gameboard[i][j] = 'A'; break;
                        case 2: gameboard[i][j] = 'B'; break;
                        case 3: gameboard[i][j] = 'C'; break;
                        case 4: gameboard[i][j] = 'D'; break;
                        case 5: gameboard[i][j] = 'E'; break;
                        case 6: gameboard[i][j] = 'F'; break;
                        case 7: gameboard[i][j] = 'G'; break;
                        case 8: gameboard[i][j] = 'H'; break;
                        case 9: gameboard[i][j] = 'I'; break;
                        case 10: gameboard[i][j] = 'J'; break;
                        default: gameboard[i][j] = ' ';
                    }
                }
                else if(j == 0){
                    switch(i){
                        case 1: gameboard[i][j] = '0'; break;
                        case 2: gameboard[i][j] = '1'; break;
                        case 3: gameboard[i][j] = '2'; break;
                        case 4: gameboard[i][j] = '3'; break;
                        case 5: gameboard[i][j] = '4'; break;
                        case 6: gameboard[i][j] = '5'; break;
                        case 7: gameboard[i][j] = '6'; break;
                        case 8: gameboard[i][j] = '7'; break;
                        case 9: gameboard[i][j] = '8'; break;
                        case 10: gameboard[i][j] = '9'; break;
                        default: gameboard[i][j] = ' ';
                    }
                }else
                gameboard[i][j] = '~';
            }
        }
    }
	//Getters & Setters
    /**
     * Get size of board
     * @return Size of the board
     */
	public int getGridsize() {
		return gridSize;
	}
	/**
	 * Set gameboard size
	 * @param gridsize Size of the gameboard
	 */
	public void setGridsize(int gridsize) {
		this.gridSize = gridsize;
	}
	/**
	 * Get the gameboard object
	 * @return gameboard object
	 */
	public char[][] getGameboard() {
		return gameboard;
	}
	/**
	 * Set Gameboard object
	 * @param gameboard
	 */
	public void setGameboard(char[][] gameboard) {
		this.gameboard = gameboard;
	}
	/**
	 * Get the value of an individual coordinate of the gameboard
	 * @param row gameboard row
	 * @param col gameboard column
	 * @return Value of the coordinate
	 */
	public char getCoordinateValue(int row, int col) {
		if (row <0 ||
				row > gameboard.length ||
				col < 0 ||
				col > gameboard.length) {
				throw new IllegalArgumentException("Invalid coordinates from get " + col + " " + row);
			}
		return gameboard[row][col];
	}
	/**
	 * Set the value of the coordinate
	 * @param row gameboard row
	 * @param col gameboard column
	 * @param state new value of the coordinate
	 */
	public void setCoordinateValue(int row, int col, char state) {
		if (row <0 ||
			row > gameboard.length ||
			col < 0 ||
			col > gameboard.length) {
			throw new IllegalArgumentException("Invalid coordinates from set " + col + " " + row);
		}
		gameboard[row][col] = state;
	}
	/**
	 * Create a string version of the array in a printable format
	 * @return String of the gameboard array
	 */
	@Override
    public String toString() {
    	StringBuilder result = new StringBuilder();
        for (int i = 0; i < gameboard.length; i++) {
            for (int j = 0; j < gameboard[i].length; j++) {
                result.append(gameboard[i][j]);
                if (j < gameboard[i].length - 1) {
                    result.append(" "); 
                }
            }

            if (i < gameboard.length - 1) {
                result.append("\n"); 
            }
        }

        return result.toString();
    }
	
	/**
	 * Check if entered coordinate is a valid coordinate for the gameboard
	 * @param coordinates String representation of a column and row
	 * @return Boolean value if the coordinates are valid or not
	 */
	public static boolean coordinatesValid(String coordinates) {
		int col = convertColumn(coordinates);
		int row = convertRow(coordinates);
		if (col < 1 || col > 10 || row < 1 || row > 10) {
            return false;
        }else {
        	return true;
        }
		
	}
	
	/**
	 * Extract letter from coordinate string and convert it to the corresponding column index
	 * @param coordinate 
	 * @return Index of the coordinate that the letter represents
	 */
	public static int convertColumn(String coordinate) {
		char columnLetter = Character.toLowerCase(coordinate.charAt(0));
		return columnLetter -  'a' +1;
	}
	
	/**
	 * Extract number from coordinate string and convert it to the corresponding row index
	 * @param coordinate String representation of the column and row in letter number format
	 * @return Index of the coordinate that the number of the coordinate represents
	 */
	public static int convertRow(String coordinate) {
		char row = coordinate.charAt(1);
		int intValue = Character.getNumericValue(row)+1;
		return intValue;
	}

	}
	
	

