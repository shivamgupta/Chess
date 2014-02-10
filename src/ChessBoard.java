public class ChessBoard 
{	
	public ChessPiece[][] board=new ChessPiece[8][8];
	
	public boolean whitesMove;
	public int whitePieceCount = 16;
	public int blackPieceCount = 16;
	public int lastMovedToX    = -2;
	public int lastMovedToY    = -2;
	public int lastMovedFromX  = -2;
	public int lastMovedFromY  = -2;
	
	//copy constructor
	public ChessBoard(ChessBoard orig)
	{
		for (int i=0; i<8; i++)
			for (int j=0; j<8; j++)
				board[i][j] = orig.board[i][j];

		whitesMove      = orig.whitesMove;
		whitePieceCount = orig.whitePieceCount;
		blackPieceCount = orig.blackPieceCount;
		lastMovedToX    = orig.lastMovedToX;
		lastMovedToY    = orig.lastMovedToY;
		lastMovedFromX  = orig.lastMovedFromX;
		lastMovedFromY  = orig.lastMovedFromY;
	}

	//constructor sets pieces to start position
	public ChessBoard()
	{
		whitesMove=true;
		board[0][0] = new Rook(false,0,0);
		board[1][0] = new Knight(false,1,0);
		board[2][0] = new Bishop(false,2,0);
		board[3][0] = new Queen(false,3,0);
		board[4][0] = new King(false,4,0);
		board[5][0] = new Bishop(false,5,0);
		board[6][0] = new Knight(false,6,0);
		board[7][0] = new Rook(false,7,0);
		board[0][1] = new Pawn(false,0,1);
		board[1][1] = new Pawn(false,1,1);
		board[2][1] = new Pawn(false,2,1);
		board[3][1] = new Pawn(false,3,1);
		board[4][1] = new Pawn(false,4,1);
		board[5][1] = new Pawn(false,5,1);
		board[6][1] = new Pawn(false,6,1);
		board[7][1] = new Pawn(false,7,1);
	
		board[0][7] = new Rook(true,0,7);
		board[1][7] = new Knight(true,1,7);
		board[2][7] = new Bishop(true,2,7);
		board[3][7] = new Queen(true,3,7);
		board[4][7] = new King(true,4,7);
		board[5][7] = new Bishop(true,5,7);
		board[6][7] = new Knight(true,6,7);
		board[7][7] = new Rook(true,7,7);
		board[0][6] = new Pawn(true,0,6);
		board[1][6] = new Pawn(true,1,6);
		board[2][6] = new Pawn(true,2,6);
		board[3][6] = new Pawn(true,3,6);
		board[4][6] = new Pawn(true,4,6);
		board[5][6] = new Pawn(true,5,6);
		board[6][6] = new Pawn(true,6,6);
		board[7][6] = new Pawn(true,7,6);
	}//end of constructor
	
	public boolean isWhiteKingAttacked()
	{
		MoveList m = findAllBlackMoves();
		return m.exists(getWhiteKingX(), getWhiteKingY());
	}

	public boolean isBlackKingAttacked()
	{
			MoveList m=findAllWhiteMoves();
			return m.exists(getBlackKingX(), getBlackKingY());
	}

	public int getWhiteKingX()
	{
		for (int i=0; i<8; i++)
			for (int j=0; j<8; j++)
				if(board[i][j]!=null && board[i][j].getImg().equals("wking.png"))
					return board[i][j].getX();
		return -1;//error
	}
	
	public int getWhiteKingY()
	{
		for (int i=0; i<8; i++)
			for (int j=0; j<8; j++)
				if(board[i][j]!=null && board[i][j].getImg().equals("wking.png"))
					return board[i][j].getY();
		return -1;//error	
	}

	public int getBlackKingX()
	{
		for (int i=0; i<8; i++)
			for (int j=0; j<8; j++)
				if(board[i][j]!=null && board[i][j].getImg().equals("bking.png"))
					return board[i][j].getX();
		return -1;//error
	}

	public int getBlackKingY()
	{
		for (int i=0; i<8; i++)
			for (int j=0; j<8; j++)
				if(board[i][j]!=null && board[i][j].getImg().equals("bking.png"))
					return board[i][j].getY();
		return -1;//error
	}
	
	public boolean isLegalWithChecks(int x1,int y1,int x2,int y2)
	{
		if (this.isLegal(x1, y1, x2, y2))
		{
			ChessBoard copy = new ChessBoard(this);
			copy.move(x1,y1,x2,y2);
			if ((copy.whitesMove && copy.isBlackKingAttacked())||(!copy.whitesMove&& copy.isWhiteKingAttacked()))
				return false;
			return true;
		}
		return false;//illegal
	}

	public boolean isLegal(int x1, int y1, int x2, int y2)
	{
		if (board[x1][y1]==null) 
			return false;//if location is null
		
		if (whitesMove!=board[x1][y1].getColor()) 
			return false;//if wrong color piece attempts to move
		
		ChessPiece p = board[x1][y1];
		
		if (board[x2][y2] != null && p.getColor() == board[x2][y2].getColor())
				return false;//attempt to take same color piece
		
		return (p.isMoveLegal(x1, y1, x2, y2, this));
	}//end of isLegal
	
	public void move(int x1, int y1, int x2, int y2)
	{//when human plays
		if(board[x1][y1]==null) 
			return;
		if (board[x2][y2]!=null)
		{//piece is being captured
			if (whitesMove)
				blackPieceCount--;
			else 
				whitePieceCount--;
		}
		whitesMove = !whitesMove;	
		board[x2][y2] = board[x1][y1];
		board[x2][y2].setX(x2); 
		board[x2][y2].setY(y2);
		lastMovedToX   = x2;
		lastMovedToY   = y2;
		lastMovedFromX = x1;
		lastMovedFromY = y1;
		board[x1][y1]  = null;
		
		if((y2==7||y2==0)&&(board[x2][y2].getImg().equals("wpawn.png")||board[x2][y2].getImg().equals("bpawn.png")))
		{
			//if a pawn is on the last rank
			boolean b=board[x2][y2].getColor();
			board[x2][y2]=new Queen(b,x2,y2);
		}
	}
	
	public void move(Move m)
	{//used when computer plays
		if (board[m.x2][m.y2]!=null)
		{//piece is being captured
			if (whitesMove)
				blackPieceCount--;
			else
				whitePieceCount--;	//decrease piece count if piece was captured
		}
		whitesMove=!whitesMove;//change turns
		board[m.x2][m.y2] = board[m.x1][m.y1];
		board[m.x2][m.y2].setX(m.x2);
		board[m.x2][m.y2].setY(m.y2);
		lastMovedToX=m.x2;
		lastMovedToY=m.y2;
		lastMovedFromX=m.x1;
		lastMovedFromY=m.y1;
		board[m.x1][m.y1] = null;
		
		if( (m.y2==7||m.y2==0) && (board[m.x2][m.y2].getImg().equals("wpawn.png")||board[m.x2][m.y2].getImg().equals("bpawn.png")) )
		{
			//if a pawn is on the last rank
			boolean colorToFill = board[m.x2][m.y2].getColor();
			board[m.x2][m.y2]   = new Queen(colorToFill, m.x2, m.y2);
		}
	}
	
	public ChessPiece[] getBlackPieces()
	{
		ChessPiece[]blackPieces=new ChessPiece[blackPieceCount];
		int a=0;
		for (int i=0; i<8; i++)
		{
			for (int j=0; j<8; j++)
			{
				if(board[i][j]!=null && board[i][j].getColor()==false)
				{
					blackPieces[a]=board[i][j]; 
					a++;
				}
			}
		}
		return blackPieces;
	}
	
	public ChessPiece[] getWhitePieces()
	{
		ChessPiece[]whitePieces=new ChessPiece[whitePieceCount];
		int a=0;
		for (int i=0; i<8; i++)
		{
			for (int j=0; j<8; j++)
			{
				if(board[i][j]!=null && board[i][j].getColor()==true)
				{
					whitePieces[a]=board[i][j]; 
					a++;
				}
			}
		}
		return whitePieces;
	}
	
	public MoveList findAllMoves()
	{
		if(!whitesMove)
		{//if blacks move
			MoveList allMoves=new MoveList();
			ChessPiece[] blackPieces= getBlackPieces();
			for (int p=0; p<blackPieces.length; p++)
				for (int i=0; i<8; i++)
					for (int j=0; j<8; j++)
						if (isLegal(blackPieces[p].getX(),blackPieces[p].getY(),i,j))//if move is legal 
							allMoves.add(blackPieces[p].getX(),blackPieces[p].getY(),i,j);
			return allMoves;
		}
		
		else
		{//if white's move
			MoveList allWhiteMoves=new MoveList();
			ChessPiece[] whitePieces= getWhitePieces();
			for (int p=0; p<whitePieces.length; p++)
				for (int i=0; i<8; i++)
					for (int j=0; j<8; j++)
						if (isLegal(whitePieces[p].getX(),whitePieces[p].getY(),i,j))//if move is legal 
							allWhiteMoves.add(whitePieces[p].getX(),whitePieces[p].getY(),i,j);
			return allWhiteMoves;
		}
	}//end of FindAllMoves
	
	public MoveList findAllBlackMoves()
	{
		boolean b=whitesMove;
		MoveList allMoves=new MoveList();
		ChessPiece[] blackPieces= getBlackPieces();
		for (int p=0; p<blackPieces.length; p++)
		{
			for (int i=0; i<8; i++)
			{
				for (int j=0; j<8; j++)
				{
					whitesMove=false;
					if (isLegal(blackPieces[p].getX(),blackPieces[p].getY(),i,j))//if move is legal 
						allMoves.add(blackPieces[p].getX(),blackPieces[p].getY(),i,j);
				}
			}
		}
		whitesMove=b;
		return allMoves;
	}
	
	
	public MoveList findAllWhiteMoves()
	{
		boolean b=whitesMove;
		MoveList allWhiteMoves=new MoveList();
		ChessPiece[] whitePieces= getWhitePieces();
		for (int p=0; p<whitePieces.length; p++)
		{
			for (int i=0; i<8; i++)
			{
				for (int j=0; j<8; j++)
				{
					whitesMove=true;
					if (isLegal(whitePieces[p].getX(),whitePieces[p].getY(),i,j))//if move is legal 
						allWhiteMoves.add(whitePieces[p].getX(),whitePieces[p].getY(),i,j);
				}
			}
		}
		whitesMove=b;
		return allWhiteMoves;
	}
	
	public boolean clearPathHorizontal(int x1, int y1, int x2, int y2)
	{//y's equal
		int start=Math.min(x1,x2);
		int end=Math.max(x1,x2);
		for (int i=start+1; i<end; i++)
		{
			if (board[i][y1]!=null)
				return false;
		}
		return true;
	}
	
	public boolean clearPathVertical(int x1, int y1, int x2, int y2)
	{//x's equal
		int start=Math.min(y1,y2);
		int end=Math.max(y1,y2);
		for (int i=start+1; i<end; i++)
		{
			if (board[x1][i]!=null) 
				return false;
		}
		return true;
	}
	
	/*public boolean clearPathDiagonal(int x1, int y1, int x2, int y2)
	{
		int a=x1;
		int b=y1;
		if(x1<x2 && y1<y2)
		{//Moved Right && Down
	        while(a < x2-1 && b < y2-1)
	        { 
	        	a++; 
	        	b++;
	            if(board[a][b]!=null) 
	            	return false;    
	        }
	    }
		
	    else if(x1>x2 && y1<y2)
	    {//Moved Left && Down
	        while(a > x2+1 && b < y2-1)
	        {
	            a--;
	            b++;
	            if(board[a][b]!=null) 
	            	return false;     
	        }
	    }
		
	    else if(x1<x2 && y1>y2) //Moved Right && Up
	    {
	        while(a < x2-1 && b > y2+1)
	        {
	            a = a+1;
	            b = b-1;
	            if(board[a][b]!=null) 
	            	return false;
	        }
	    }
		
	    else if(x1>x2 && y1>y2)//Moved Left && Up
	    {
	        while(a > x2+1 && b > y2+1)
	        {
	            a--;
	            b--;
	            if(board[a][b]!=null) 
	            	return false;       
	        }   
	    }   
		return true;
	}*///end of clearPathDiagonal
	
	public boolean clearPathDiagonal(int x, int y, int newX, int newY)
	{
		int horizontalPos = x;
		int verticlePos   = y;
		
		if(x < newX && y < newY)  //right-down movement
			return rightDownMovementPossible(horizontalPos, verticlePos, newX, newY);
		
	    else if(x > newX && y < newY)  //left-down movement
	    	return leftDownMovementPossible(horizontalPos, verticlePos, newX, newY);
		
	    else if(x > newX && y > newY)  //left-up movement
	    	return leftUpMovementPossible(horizontalPos, verticlePos, newX, newY);
		
	    else if(x < newX && y > newY)  //right-up movement
	    	return rightUpMovementPossible(horizontalPos, verticlePos, newX, newY);
	    else
	    	return true;
	}
	
	public boolean rightDownMovementPossible(int horizontalPos, int verticlePos, int newX, int newY)
	{
		while(horizontalPos < newX-1 && verticlePos < newY-1)
        {
        	horizontalPos++;
        	verticlePos++;
            if(board[horizontalPos][verticlePos] != null) 
            	return false;
        }
		return true;
	}
	
	public boolean leftDownMovementPossible(int horizontalPos, int verticlePos, int newX, int newY)
	{
		while(horizontalPos > newX+1 && verticlePos < newY-1) 
    	{
    		horizontalPos--; 
    		verticlePos++;
            if(board[horizontalPos][verticlePos] != null) 
            	return false;
    	}
		return true;
	}
	
	public boolean leftUpMovementPossible(int horizontalPos, int verticlePos, int newX, int newY)
	{
		while(horizontalPos > newX+1 && verticlePos > newY+1)
    	{
    		horizontalPos--;
    		verticlePos--;
            if(board[horizontalPos][verticlePos] != null) 
            	return false;
    	}
		return true;
	}
	
	public boolean rightUpMovementPossible(int horizontalPos, int verticlePos, int newX, int newY)
	{
    	while(horizontalPos < newX-1 && verticlePos > newY+1)
    	{
    		horizontalPos++; 
    		verticlePos--;
            if(board[horizontalPos][verticlePos] != null) 
            	return false;
    	}
		return true;
	}

	public MoveList findAllMovesWithChecks()
	{
		if(!whitesMove)
		{//if blacks move
			MoveList allMoves=new MoveList();
			ChessPiece[] blackPieces= getBlackPieces();
			for (int p=0; p<blackPieces.length; p++)
			{
				for (int i=0; i<8; i++)
				{
					for (int j=0; j<8; j++)
					{					
						if (isLegalWithChecks(blackPieces[p].getX(),blackPieces[p].getY(),i,j))//if move is legal 
							allMoves.add(blackPieces[p].getX(),blackPieces[p].getY(),i,j);
					}
				}
			}
			return allMoves;
		}
		else
		{//if white's move
			MoveList allWhiteMoves=new MoveList();
			ChessPiece[] whitePieces= getWhitePieces();
			for (int p=0; p<whitePieces.length; p++)
			{
				for (int i=0; i<8; i++)
				{
					for (int j=0; j<8; j++)
					{
						if (isLegalWithChecks(whitePieces[p].getX(),whitePieces[p].getY(),i,j))//if move is legal 
							allWhiteMoves.add(whitePieces[p].getX(),whitePieces[p].getY(),i,j);
					}
				}
			}
			return allWhiteMoves;
		}
	}//end of FindAllMoves
	
	public ChessPiece getPiece(int i,int j)
	{
		return board[i][j];
	}
		
}//end of class