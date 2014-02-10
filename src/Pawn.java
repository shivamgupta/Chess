public class Pawn extends ChessPiece
{
	private String img;
	public boolean color;
	int x;
	int y;
	public Pawn(Pawn p)
	{
		this.x	   = p.getX();
		this.y     = p.getY();
		this.img   = p.getImg();
		this.color = p.getColor();
	}

	public Pawn(boolean white, int x, int y)
	{
		this.x = x;
		this.y = y;
		if (white)
			img ="wpawn.png";
		else
			img ="bpawn.png";
	    color = white;
	}
	
	public int getX()
	{
		return x;
	}
	
	public int getY()
	{
		return y;
	}
	
	public void setX(int n)
	{
		x = n;
	}
	
	public void setY(int n)
	{
		y = n;
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
		if (this.getColor())
		{//if its white
			if (x1==x2 && y1-1==y2 && b.board[x2][y2]==null) 
				return true;//move one forward
			if (y1==6  && x1==x2 && y1==y2+2 && b.board[x2][y2]==null && b.clearPathVertical(x1,y1,x2,y2)) 
				return true; //move two forward
			if((x2==x1-1 || x2==x1+1) && y1==y2+1 && b.board[x2][y2]!=null && b.board[x2][y2].getColor()==false) 
				return true;//capture
			//System.out.println("illegal pawn move");
			return false;
		}//end of if white
		
		else
		{//if black
			if (x1==x2 && y1+1==y2 && b.board[x2][y2]==null) 
				return true;//move one forward
			if(y1==1 && x1==x2 && y1==y2-2 && b.board[x2][y2]==null&& b.clearPathVertical(x1,y1,x2,y2)) 
				return true;//move two forward
			if((x2==x1+1 || x2==x1-1) && y1==y2-1 && b.board[x2][y2]!=null && b.board[x2][y2].getColor()) 
				return true;//capture
		}
		
		return false;
	}
}//end of class