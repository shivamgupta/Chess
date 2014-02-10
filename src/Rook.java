public class Rook extends ChessPiece
{
	private String img;
	public boolean color;
	int x;
	int y;
	
	public Rook(Rook p)
	{
		this.x     = p.getX();
		this.y     = p.getY();
		this.img   = p.getImg();
		this.color = p.getColor();
	}
	
	public Rook(boolean white,int x, int y)
	{
		this.x = x;
		this.y = y;
		if (white)
			img = "wrook.png";
		else
			img = "brook.png";
		
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
		if(y1==y2 && b.clearPathHorizontal(x1,y1,x2,y2))
			return true;
		if(x1==x2 && b.clearPathVertical(x1,y1,x2,y2)) 
			return true;
		//System.out.println("Illegal Rook Move");
		return false;
	}
}