import javax.swing.JOptionPane;

public class ChessMain
{
	public static void main(String[] args) 
	{
		ChessBoard b = new ChessBoard();
		Zen.create(800,800, "stretch");
        Zen.drawImage("chessboard.png", 0, 0);
        drawBoard(b);
        Zen.flipBuffer();
        while(Zen.isRunning())
        {
        	 Zen.drawImage("chessboard.png", 0, 0);
        	 drawBoard(b);
        	 Zen.flipBuffer();
        	 
        	 while(movePiece(b)==false);//do nothing
        	 
        	 MoveList m=b.findAllMoves();
        	b.move(m.moves[(int)(Math.random()*m.moves.length-1)]);
        	//m.print();
        	System.out.println(""+m.moves.length) ;
        	//Zen.sleep(300);
        	if(b.isBlackKingAttacked()){ System.out.println("black attacked");}
        	if(b.isWhiteKingAttacked())	{  System.out.println("white attacked");}
        } 
	}//end of main 


	public static void drawBoard(ChessBoard b)
	{
		for (int i=0; i<8; i++)
		{
			for (int j=0; j<8; j++)
			{
				if (b.board[i][j] != null)
					Zen.drawImage(b.board[i][j].getImg(), i*100 , j*100);
			}
		}
		Zen.drawImage("lastmovedto.png", b.lastMovedToX  *100, b.lastMovedToY  *100);
		Zen.drawImage("lastmovedto.png", b.lastMovedFromX*100, b.lastMovedFromY*100);
	}

	public static boolean movePiece(ChessBoard b) 
	{
		Zen.waitForClick();
		int x1 = (int) Zen.getMouseClickX()/100;
		int y1 = (int) Zen.getMouseClickY()/100;
		
		if (b.board[x1][y1] == null) 
		{	//clicked on empty square
			//System.out.println("Please don't play chess in the air.");
			JOptionPane.showMessageDialog(null, "Please don't play chess in the air."); 
			return false;
		} 
		
		else 
		{
			if (b.board[x1][y1].getColor() != b.whitesMove)
			{ 
				System.out.println("Nah! Not your turn.");
				return false;
			}

			Zen.waitForClick();
			
			int x2 = (int)Zen.getMouseClickX()/100;
			int y2 = (int)Zen.getMouseClickY()/100;
			
			if (b.isLegalWithChecks(x1, y1, x2, y2)) 
			{
				b.move(x1,y1,x2,y2);
				return true;
			}

			return false;//illegal move
			
		}
	} //End of Move Piece
}//end of class