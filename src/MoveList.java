public class MoveList 
{
	public Move[] moves = new Move[0];
	
	public MoveList()
	{
		moves = new Move[0];
	}

	public void add(Move m)
	{
		Move[] temp = new Move[moves.length+1];
		for (int i = 0; i < moves.length; i++)
			temp[i] = moves[i];
		
		temp[temp.length-1] = m;
		moves = temp;
	}

	public void add(int x1, int y1, int x2, int y2)
	{
		add(new Move(x1, y1, x2, y2));
	}
	
	public void print()
	{
		for(int i = 0; i < moves.length; i++)
			moves[i].printMove();
	}

	public boolean exists(int x2, int y2)
	{
		for (int i = 0; i < moves.length; i++)
			if(moves[i].x2 == x2 && moves[i].y2 == y2) 
				return true;
		return false;
	}
}//end of class