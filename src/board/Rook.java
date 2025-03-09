package board;

public class Rook extends Piece
{
	public Rook(Color color)
	{
		super(color);
	}
	
	@Override
	public boolean canMove(Board board, int startRow, int startCol, int endRow, int endCol)
	{
		//can move either horizontally or vertically
		if ((startRow == endRow) != (startCol == endCol))
		{
			//check for pieces between start and end.
			//only one of these for loops will actually be entered, depending
			//on whether the rook is going horizontal or vertical.
			for (int r = Math.min(startRow, endRow) + 1; r < Math.max(startRow, endRow); r++)
			{
				if (!board.isEmptySpace(r, startCol))
				{
					return false;
				}
			}
			for (int c = Math.min(startCol, endCol) + 1; c < Math.max(startCol, endCol); c++)
			{
				if (!board.isEmptySpace(startRow, c))
				{
					return false;
				}
			}
			
			return true;
		}
		
		return false;
	}
	
	@Override
	public String toString()
	{
		return super.toString() + "R";
	}
}
