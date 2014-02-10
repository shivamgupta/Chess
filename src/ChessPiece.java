public class ChessPiece 
{
	String img;
	boolean color;
	int x;
	int y;
	
	//copy ctor
	public ChessPiece(ChessPiece orig)
	{
		this.x     =    orig.getX();
		this.y     =    orig.getY();
		this.img   =    orig.getImg();
		this.color =    orig.getColor();
	}
	
	public ChessPiece()
	{
		
	}

	public String getImg()
	{
		return this.img;
	}
	
	public boolean getColor()
	{
		return this.color;
	}

	public boolean isMoveLegal(int x1, int y1, int x2, int y2, ChessBoard b)
	{
		return true;
	}

	public void setX(int n)
	{
		x = n;
	}
	
	public void setY(int n)
	{
		y = n;
	}

	public int getX()
	{
		return x;
	}
	
	public int getY()
	{
		return y;
	}

}//end of class