
public class Location {

	public int x;
	public int y;

	/**
	 * Constructor.
	 */
	public Location()
	{
		x = 0;
		y = 0;
	}
	
	/**
	 * Copy constructor.
	 */
	public Location(Location other)
	{
		this.x = other.getX();
		this.y = other.getY();
	}
	
	/**
	 * Sets the x-cord to the input value. 
	 * @param input
	 * @return boolean
	 * 		   true - if the function was able to assign the valid input.
	 * 		   false - otherwise.
	 */
	public boolean setX(int input)
	{		
		//if(input >= 0 && input < ChessBoard.chessBoardBase)
		{
			x = input;
			return true;
		}
		//else
			//return false;
	}
	
	/**
	 * Sets the y-cord to the input value. 
	 * @param input
	 * @return boolean
	 * 		   true  - if the function was able to assign the valid input.
	 * 		   false - otherwise.
	 */
	public boolean setY(int input)
	{
		//if(input >= 0 && input < ChessBoard.chessBoardHeight)
		{
			y = input;
			return true;
		}
		//else
			//return false;
	}
	
	public int getX()
	{
		return this.x;
	}
	
	public int getY()
	{
		return this.y;
	}
	
	/**
	 * Set the position to the new values of the x and the y cords.
	 * @param inputX
	 * 		  inputY
	 * @return boolean
	 * 		   true  - if the function was able to assign the valid input.
	 * 		   false - otherwise
	 */
	public boolean setLocation(int inputX, int inputY)
	{
		boolean validX = setX(inputX);
		boolean validY = setY(inputY);
		
		if(validX && validY)
			return true;
		else
			return false;
	}
	
	/**
	 * Set the position to the new values of the x and the y cords.
	 * @param Location
	 * @return boolean
	 * 		   true  - if the function was able to assign the valid input.
	 * 		   false - otherwise
	 */
	public boolean setLocation(Location location)
	{
		return setLocation(location.getX(), location.getY());
	}	
	
	/**
	 * Updates the position to the new values for the x and y cords.
	 * @param newX
	 * 		  newY
	 * @return boolean
	 * 		   true  - if the function was able to assign the valid input.
	 * 		   false - otherwise
	 */
	public boolean changeLocation(int newX, int newY)
	{
		return setLocation(newX, newY);
	}
	
	/**
	 * Updates the position to the new values for the x and y cords.
	 * @param newX
	 * 		  newY
	 * @return boolean
	 * 		   true  - if the function was able to assign the valid input.
	 * 		   false - otherwise
	 */
	public boolean changeLocation(Location newLocation)
	{
		return setLocation(newLocation.getX(), newLocation.getY());
	}

	public void printLocation()
	{
		System.out.println("Location in terms of (x,y) is - (" + this.x + ", " + this.y + ").");
	}
}