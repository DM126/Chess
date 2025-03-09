package board;

public class Pawn extends Piece
{	
	public Pawn(Color color)
	{
		super(color);
	}
	
	@Override
	public boolean canMove(Board board, int startRow, int startCol, int endRow, int endCol)
	{
		if (color == Color.BLACK)
		{
			if (board.isEmptySpace(endRow, endCol))
			{
				//Movement must be vertical if space is empty
				if (startCol == endCol)
				{
					//pawns can move 1 space forward
					if (endRow - startRow == 1)
					{
						return true;
					}
					else if (startRow == 1 && endRow == 3 && board.isEmptySpace(endRow, endCol))
					{
						//pawns can move 2 spaces if at the starting row and
						//no piece is in between.
						return true;
					}
				}
			}
			else if (Math.abs(startCol - endCol) == 1 && endRow - startRow == 1)
			{
				//space not empty, must be 1 space forward diagonally
				return true;
			}
		}
		else //white
		{
			if (board.isEmptySpace(endRow, endCol))
			{
				//movement must be vertical if the space is empty
				if (startCol == endCol)
				{
					//pawns can move 1 space forward
					if (startRow - endRow == 1)
					{
						return true;
					}
					else if (startRow == Board.BOARD_LENGTH - 2 && 
							endRow == startRow - 2 && 
							board.isEmptySpace(startRow - 1,  startCol))
					{
						//pawns can move 2 spaces if at the starting row and no
						//piece is in between.
						return true;
					}
				}
			}
			else if (Math.abs(startCol - endCol) == 1 && startRow - endRow == 1)
			{
				//space not empty, must be 1 space forward diagonally
				return true;
			}
		}
		
		return false;
	}
	
	@Override
	public String toString()
	{
		return super.toString() + "P";
	}
}
