
public class Move 
{
	public int x1;
	public int y1;
	public int x2;
	public int y2;

	public Move(int x1,int y1,int x2,int y2)
	{
		this.x1=x1;
		this.x2=x2;
		this.y1=y1;
		this.y2=y2;
	}

	public void printMove()
	{
		System.out.println("From (" + x1 + "," + y1 + ") to (" + x2 + "," + y2 + ").");
	}
	
}//end of class
