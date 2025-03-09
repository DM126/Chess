package board;

public class Queen extends Piece
{
	public Queen(Color color)
	{
		super(color);
	}
	
	@Override
	public boolean canMove(Board board, int startRow, int startCol, int endRow, int endCol)
	{
		//cannot move to current position
		if (startRow == endRow && startCol == endCol)
		{
			return false;
		}
		
		//Can move diagonally, horizontally, or vertically
		//TODO code duplicated from bishop and rook, refactor?
		if (Math.abs(startRow - endRow) == Math.abs(startCol - endCol))
		{
			//check for pieces between start and end.
			//only one of these loops will actually be entered, depending
			//on which direction the queen is moving.
			for (int i = 1; startRow + i < endRow && startCol + i < endCol; i++)
			{
				//down-right
				if (!board.isEmptySpace(startRow + i,  startCol + i))
				{
					return false;
				}
			}
			for (int i = 1; startRow + i < endRow && startCol - i > endCol; i++)
			{
				//down-left
				if (!board.isEmptySpace(startRow + i,  startCol - i))
				{
					return false;
				}
			}
			for (int i = 1; startRow - i > endRow && startCol - i > endCol; i++)
			{
				//up-left
				if (!board.isEmptySpace(startRow - i,  startCol - i))
				{
					return false;
				}
			}
			for (int i = 1; startRow - i > endRow && startCol + i < endCol; i++)
			{
				//up-right
				if (!board.isEmptySpace(startRow - i,  startCol + i))
				{
					return false;
				}
			}
			
			return true;
		}
		else if ((startRow == endRow) != (startCol == endCol))
		{
			//check for pieces between start and end.
			//only one of these for loops will actually be entered, depending
			//on whether the queen is going horizontal or vertical.
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
		return super.toString() + "Q";
	}
}
