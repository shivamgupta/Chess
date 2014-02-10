public class Knight extends ChessPiece
{
	private String img;
	public boolean color;
	int x;
	int y;

	public Knight(boolean white, int x, int y)
	{
		this.x   = x;
		this.y   = y;
		if (white)
			img="wknight.png";
		else 
			img="bknight.png";
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
	
	public boolean isMoveLegal(int x1, int y1, int x2, int y2,ChessBoard b)
	{
		if(x1+1==x2 && y1+2==y2) return true;	
		if(x1+1==x2 && y1-2==y2) return true;	
		if(x1-1==x2 && y1+2==y2) return true;	
		if(x1-1==x2 && y1-2==y2) return true;	
		if(x1+2==x2 && y1+1==y2) return true;	
		if(x1+2==x2 && y1-1==y2) return true;	
		if(x1-2==x2 && y1+1==y2) return true;
		if(x1-2==x2 && y1-1==y2) return true;	
		//System.out.println("Illegal knight move");
		return false;
	}
}//end of class