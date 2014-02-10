public class King extends ChessPiece
{
	private String img;
	public boolean color;
	int x;
	int y;
	
	public King(King orig)
	{
		this.x     = orig.getX();
		this.y     = orig.getY();
		this.img   = orig.getImg();
		this.color = orig.getColor();
	}

	public King(boolean white,int x, int y)
	{
		this.x = x;
		this.y = y;
		
		if (white)
			img = "wking.png";
		else
			img = "bking.png";
		color = white;
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
		if(x1 == x2   && y2 == y1+1)     return true;
		if(x1 == x2   && y2 == y1-1)     return true;
		if(x1 == x2+1 && y2 == y1)       return true;
		if(x1 == x2+1 && y2 == y1+1)     return true;
		if(x1 == x2+1 && y2 == y1-1)     return true;
		if(x1 == x2-1 && y2 == y1)       return true;
		if(x1 == x2-1 && y2 == y1+1)     return true;
		if(x1 == x2-1 && y2 == y1-1)     return true;
		
		return false;
	}
}//end of class