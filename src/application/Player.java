/**
 * @author Nathan Able
 * @version 1.0
 */
package application;
import java.util.Random;
import java.util.ArrayList;

public class Player extends Ship {
	
	private String name;
	private Gameboard shipTracker;
	private Gameboard attackTracker;
	private int score;
	private char firstAttack;
	private String firstAttackCords;
	private char secondAttack;
	private String secondAttackCords;
	private char thirdAttack;
	private String thirdAttackCords;
	ArrayList<String> priorAttacks;
	
	//Constructor
	/**
	 * Builds the player object for the game (String playerName, String shipName, int shipSize, int shipGuns)
	 * 
	 * @param playerName Name of the player
	 * @param shipName Type of ship. This is determined by the game difficulty selected in game play
	 * @param shipSize Size of the ship. This is determined by the game difficulty selected in game play
	 * @param shipGuns Number of guns for the ship. Guns determines the number of shots take per turn. Determined by the difficulty selected in game play
	 * @param shipTracker Gameboard object that represents the ocean where the players ship(s) reside
	 * @param attackTracker Gameboard object that represents the ocean where opponents ship(s) reside
	 * 
	 * @param score Default value: 0 - Sum of the hits against opponent
	 * @param firstAttack Default value: ~ - Results of Attack 1 for computer attack
	 * @param firstAttackCords Default value: (empty string) -  Coordinates of Attack 1
	 * @param secondAttack Default value: ~ - Results of Attack 2 for computer attack
	 * @param secondAttackCords Default value: (empty string) -  Coordinates of Attack 2
	 * @param thirdAttack Default value: ~ - Results of Attack 3 for computer attack
	 * @param thirdAttackCords Default value: (empty string) -  Coordinates of Attack 3
	 * @param priorAttacks Default value: (empty array) -  ArrayList of coordinates attacked
	 * @see {@link Gameboard}
	 * @see {@link ArrayList}
	 */
	public Player(String playerName, String shipName, int shipSize, int shipGuns) {
		super(shipName, shipSize, shipGuns);
		this.name = playerName;
		this.shipTracker = new Gameboard();
		this.attackTracker = new Gameboard();
		this.score = 0;
		this.firstAttack = '~';
		this.firstAttackCords = "";
		this.secondAttack = '~';
		this.secondAttackCords = "";
		this.thirdAttack = '~';
		this.thirdAttackCords = "";
		priorAttacks = new ArrayList<>();
	}
	
	//Getters and Setters
	/**
	 * Get player score
	 * @return Score value
	 */
	public int getScore() {
		return score;
	}
	/**
	 * Increase score by 1
	 */
	public void incrementScore() {
		this.score ++;
	}
	/**
	 * Get the player name
	 * @return Name of the player
	 */
	public String getName() {
		return name;
	}
	/**
	 * 
	 * @return Gameboard object of the players ocean
	 */
	public Gameboard getShipTracker() {
		return shipTracker;
	}
	/**
	 * 
	 * @return Gameboard object of attacks to the opponents ocean
	 */
	public Gameboard getAttackTracker() {
		return attackTracker;
	}
	
	/**
	 * Get the results of the first attack in the last turn taken by the computer
	 * @return Results of computers first attack
	 */
	public char getFirstAttack() {
		return firstAttack;
	}
	/**
	 * Update with the results of the first attack in the computers last turn
	 * @param firstAttack
	 */
	public void setFirstAttack(char firstAttack) {
		this.firstAttack = firstAttack;
	}
	/**
	 * Get the coordinate of the first attack on the computers last turn
	 * @return String representation of the coordinates of the first attack on computers last turn
	 */
	public String getFirstAttackCords() {
		return firstAttackCords;
	}
	/**
	 * Set the coordinate of the first attack of the computers last turn
	 * @param firstAttackCords
	 */
	public void setFirstAttackCords(String firstAttackCords) {
		this.firstAttackCords = firstAttackCords;
	}
	/**
	 * Get the results of the second attack in the last turn taken by the computer
	 * @return Results of computers second attack
	 */
	public char getSecondAttack() {
		return secondAttack;
	}
	/**
	 * Update with the results of the second attack in the computers last turn
	 * @param firstAttack
	 */
	public void setSecondAttack(char secondAttack) {
		this.secondAttack = secondAttack;
	}
	/**
	 * Get the coordinate of the second attack on the computers last turn
	 * @return String representation of the coordinates of the second attack on computers last turn
	 */
	public String getSecondAttackCords() {
		return secondAttackCords;
	}
	/**
	 * Set the coordinate of the second attack of the computers last turn
	 * @param firstAttackCords
	 */
	public void setSecondAttackCords(String secondAttackCords) {
		this.secondAttackCords = secondAttackCords;
	}
	/**
	 * Get the results of the third attack in the last turn taken by the computer
	 * @return Results of computers third attack
	 */
	public char getThirdAttack() {
		return thirdAttack;
	}
	/**
	 * Update with the results of the third attack in the computers last turn
	 * @param firstAttack
	 */
	public void setThirdAttack(char thirdAttack) {
		this.thirdAttack = thirdAttack;
	}
	/**
	 * Get the coordinate of the third attack on the computers last turn
	 * @return String representation of the coordinates of the third attack on computers last turn
	 */
	public String getThirdAttackCords() {
		return thirdAttackCords;
	}
	/**
	 * Set the coordinate of the third attack of the computers last turn
	 * @param firstAttackCords
	 */
	public void setThirdAttackCords(String thirdAttackCords) {
		this.thirdAttackCords = thirdAttackCords;
	}
	/**
	 * Add the attack coordinates to the priorAttacks ArrayList
	 * @param attackCords String representation of the coordinates from the last attack
	 */
	public void addAttack(String attackCords) {
		this.priorAttacks.add(attackCords);
	}
	/**
	 * Check if the coordinates provided are in the ArrayList of the prior attacks
	 * @param coordinates String representation of the coordinates
	 * @return Boolean true if coordinates are present
	 */
	public boolean checkPriorAttack(String coordinates) {
		return this.priorAttacks.contains(coordinates);
	}

	//
	/**
	 * Test if ship placement will fit on the board
	 * @param coordinates String representation of the starting coordinate of the ship
	 * @param size Integer representation of the size of the ship
	 * @param isHorizontal Boolean true if ship is horizontal and false if ship is vertical
	 * @return Boolean true if the ship will fit
	 */
	public static boolean willFit(String coordinates, int size, boolean isHorizontal) {
		int col = Gameboard.convertColumn(coordinates);
		int row = Gameboard.convertRow(coordinates);

		if (col < 1 || col > 10) {
            return false;
        }
		if (row < 0 || row > 10) {
			return false;
		}
		if (!isHorizontal) {
			if (row + size <= 10) {
				return true;
			}else {
				return false;
			}
		}else {

			if (col + size <= 10) {
				return true;
			}else {
				return false;
			}
		}
		
	}
	
	//
	/**
	 * Place the ship on the gameboard. Updates the values of the players ocean gameboard object from '~' to 'S'
	 * @param coordinates String representation of the starting coordinate of the ship
	 * @param size Integer size of the ship to determine coordinates the ship will reside in
	 * @param isHorizontal Boolean true if the ship is horizontal and false if the ship is vertical
	 * @param gameboard Object of the player ocean to place the ship on
	 */
	public void placeShip (String coordinates, int size, boolean isHorizontal, Gameboard gameboard) {
		int col = Gameboard.convertColumn(coordinates);
		int row = Gameboard.convertRow(coordinates);
		if(isHorizontal) {
			for(int j = col; j < col+size; j++) {
				gameboard.setCoordinateValue(row, j, 'S');
			}
		}else {
			for(int i = row; i < row + size; i++) {
				gameboard.setCoordinateValue(i, col, 'S');
			}
		}
	}
	
	
	/**
	 * Generate coordinate for computer user
	 * Generates two random numbers from 1-10 for letters and 0-9 for the number
	 * @return String representation of the coordinate
	 */
	public static String generateRandomCoordinate() {
		Random rand = new Random();
		int randInt = rand.nextInt(10)+ 1;		
		char letter = (char)('a' + randInt -1);
		randInt = rand.nextInt(10);
		
		String coordinate = ""+letter+randInt;
		return coordinate;
		
	}
	
	/**
	 * Generate a 1 or 2 for computer user for random yes/no answers
	 * @return 1 or 2
	 */
	public static int generateRandomOneTwo() {
		Random rand = new Random();
		int randInt = rand.nextInt(3)+1;
		return randInt;
	}
	
	
	/**
	 * Creates a string representation of the players shipTracker and attackTracker gameboard objects in a sise by 
	 * side format
	 * @return printable string of the gameboards combined
	 */
	public String displayGameboards() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < shipTracker.getGridsize(); i++) {
            for (int j = 0; j < shipTracker.getGridsize(); j++) {
                result.append(shipTracker.getCoordinateValue(i, j)).append(" ");
            }
            result.append(" *|* ");
            for (int j = 0; j < attackTracker.getGridsize(); j++) {
                result.append(attackTracker.getCoordinateValue(i, j)).append(" ");
            }
            result.append("\n");
        }
        result.append("Player2 Attacks            Your Attacks");
        return result.toString();
    }
	
	
	/**
	 * Update gameboards with attack results indicating hit or miss
	 * @param coordinate String representation of the coordinate
	 * @param opponent Player object of the opponent
	 */
	public void attackOpponent(String coordinate, Player opponent) {
		char coordinateResult;
		int col = Gameboard.convertColumn(coordinate);
		int row = Gameboard.convertRow(coordinate);
		coordinateResult = opponent.shipTracker.getCoordinateValue(row, col);
		if (coordinateResult == '~') {
			this.attackTracker.setCoordinateValue(row, col, 'X');	
			opponent.shipTracker.setCoordinateValue(row, col, 'X');
		}else if(coordinateResult == 'S') {
			this.attackTracker.setCoordinateValue(row, col, 'H');
			opponent.shipTracker.setCoordinateValue(row, col, 'H');
			this.incrementScore();
		}
	}
	
	
	/**
	 * Attack decision maker. If the last attack was not a hit then choses a random coordinate to attack
	 * else the attack coordinate is based of the last hit to randomly check north/south/east/west of the last hit
	 * @param lastAttackCords String representation of the attack coordinates from the prior turn
	 * @param lastAttack The result of the prior attack
	 * @return String representation of the coordinates to attack
	 */
	public String getComputerAttackCoordinates(String lastAttackCords, char lastAttack) {
		String attackCord;
		boolean validAttack;
		int chooseDirection, row, col;
		Random rand = new Random();
		char letter;
		//If the computer hasn't attacked previously or missed on the last attack the attack a random coordinate
		if ( lastAttack == '~' || lastAttack == 'X') {
			validAttack = false;
			do {
			attackCord = generateRandomCoordinate();
			col = Gameboard.convertColumn(attackCord);
			row = Gameboard.convertRow(attackCord);
			if (this.attackTracker.getCoordinateValue(row, col) == '~' && !this.checkPriorAttack(attackCord)) {
				validAttack = true;

			}
			}while(!validAttack);
			this.addAttack(attackCord);
			return attackCord;
		}else {
			//If the prior attack hit use a semi random decision maker to attack in probable coordinates
			col = Gameboard.convertColumn(lastAttackCords);
			row = Gameboard.convertRow(lastAttackCords);
			validAttack = false;
			do {
				chooseDirection = rand.nextInt(4);
				if(chooseDirection == 0 && row -1 >0 && this.attackTracker.getCoordinateValue(row-1, col) == '~') {
					letter = (char)('a' + col - 1);
					attackCord = "" + letter + (row-1);
					validAttack = true;
					
				}else if (chooseDirection == 2 && row +1 <10 && this.attackTracker.getCoordinateValue(row+1, col) == '~') {
					letter = (char)('a' + col-1);
					attackCord = "" + letter + (row+1);
					validAttack = true;
				}else if(chooseDirection == 3 && col -1 >0 && this.attackTracker.getCoordinateValue(row, col-1) == '~') {
					letter = (char)('a' + col-2);
					attackCord = "" + letter + row;
					validAttack = true;
				}else if(chooseDirection == 4 && col +1 <10 && this.attackTracker.getCoordinateValue(row, col+1) == '~') {
					letter = (char)('a' + col);
					attackCord = "" + letter + row;
					validAttack = true;
				}else {
					attackCord = "";
				}

			}while(!validAttack);
			this.addAttack(attackCord);

			return attackCord;
		}
	}	
}
