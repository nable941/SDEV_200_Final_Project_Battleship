package application;
/**
 * Class representation of an individual ship for the game
 * @author Nathan Able
 * @version 1.0
 */
public abstract class Ship {
	protected String name;
	protected int size;
	protected int guns;
	
	
	/**
	 * Ship Constructor
	 * @param name String name of the ship type determined by the difficulty selection
	 * @param size Integer of the size of the ship
	 * @param guns Integer that represents the number of attacks per turn
	 */
	public Ship(String name, int size, int guns) {
		this.name = name;
		this.size = size;
		this.guns = guns;
	}
	//getters and Setters
	
	/**
	 * Get the name of the ship
	 * @return String name of the ship
	 */
	public String getName() {
		return name;
	}
	/**
	 * Get the size of the ship
	 * @return Integer representing the size of the ship
	 */
	public int getSize() {
		return size;
	}
	/**
	 * Get the number of guns for the ship
	 * @return Integer of the guns of the ship
	 */
	public int getGuns() {
		return guns;
	}
	/**
	 * Set the number of guns for the ship
	 * @param guns Integer that determines the number of attacks in a turn
	 */
	public void setGuns(int guns) {
		this.guns = guns;
	}


	
}
